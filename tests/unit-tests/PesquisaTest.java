package com.tests;

import com.psquiza.entidades.*;
import com.psquiza.verificadores.Verificador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PesquisaTest {

    private Pesquisa pesquisaTest;

    @BeforeEach
    void criarPesquisa(){
        pesquisaTest = new Pesquisa("COM1", "Autoavaliacao na Disciplina de Programacao Orientada a Objeto.", "computacao, poo");
    }

    @Test
    void criarPesquisaInvalido(){
        assertEquals("Campo codigo nao pode ser nulo ou vazio.",
                Verificador.verificaExcecao(() -> new Pesquisa("", "--", "computacao")));
        assertEquals("Campo descricao nao pode ser nulo ou vazio.",
                Verificador.verificaExcecao(() -> new Pesquisa("COM1", "", "computacao")));
        assertEquals("Campo campoInteresse nao pode ser nulo ou vazio.",
                Verificador.verificaExcecao(() -> new Pesquisa("COM1", "--", "")));
    }

    @Test
    void associaPesquisador() {
        assertTrue(pesquisaTest.associaPesquisador("kilua@email",
                new Pesquisador("Kilua", "estudante", "Gerador de raios.", "kilua@email", "https://kilua.png")));
        assertFalse(pesquisaTest.associaPesquisador("kilua@email",
                new Pesquisador("Kilua", "estudante", "Gerador de raios.", "kilua@email", "https://kilua.png")));
    }

    @Test
    void desassociaPesquisador() {
        associaPesquisador();
        assertTrue(pesquisaTest.desassociaPesquisador("kilua@email"));
        assertFalse(pesquisaTest.desassociaPesquisador("kilua@email"));
    }

    @Test
    void associaProblema() {
        assertTrue(pesquisaTest.associaProblema(
                new Problema("P1", "Um problema.", 2)));
        assertFalse(pesquisaTest.associaProblema(
                new Problema("P1", "Um problema.", 2)));
        assertEquals("Pesquisa ja associada a um problema.",
                Verificador.verificaExcecao(() -> pesquisaTest.associaProblema(
                        new Problema("P2", "Um outro problema", 3))));
    }

    @Test
    void desassociaProblema() {
        associaProblema();
        assertTrue(pesquisaTest.desassociaProblema());
        assertFalse(pesquisaTest.desassociaProblema());
    }

    @Test
    void associaObjetivo() {
        assertTrue(pesquisaTest.asssociaObjetivo(
                new Objetivo("O1", "GERAL", "Melhoria no ensino de OO.", 2, 3)));
        assertTrue(pesquisaTest.asssociaObjetivo(
                new Objetivo("O2", "GERAL", "Melhoria no ensino de P2.", 2, 3)));
        assertFalse(pesquisaTest.asssociaObjetivo(
                new Objetivo("O1", "GERAL", "Melhoria no ensino de OO.", 2, 3)));
    }

    @Test
    void desassociaObjetivo() {
        associaObjetivo();
        assertTrue(pesquisaTest.desassociaObjetivo("O1"));
        assertFalse(pesquisaTest.desassociaObjetivo("O1"));
    }

    @Test
    void getObjetivos() {
        associaObjetivo();
        assertEquals(2, pesquisaTest.getObjetivos());
    }

    @Test
    void maiorObjetivo() {
        pesquisaTest.asssociaObjetivo(new Objetivo("O1", "GERAL", "Melhoria no ensino de OO.", 2, 3));
        pesquisaTest.asssociaObjetivo(new Objetivo("O2", "GERAL", "Melhoria no ensino de P2.", 2, 3));
        assertEquals("O2", pesquisaTest.maiorObjetivo());
        // ERRO GRAVE GRAVE GRAVE AI
    }

    @Test
    void contemObjetivo() {
        associaObjetivo();
        assertTrue(pesquisaTest.contemObjetivo(new Objetivo("O1", "GERAL", "Melhoria no ensino de OO.", 2, 3)));
        assertTrue(pesquisaTest.contemObjetivo(new Objetivo("O2", "GERAL", "Melhoria no ensino de P2.", 2, 3)));
        assertFalse(pesquisaTest.contemObjetivo(new Objetivo("O3", "GERAL", "Melhoria no ensino de computação.", 2, 3)));
    }

    @Test
    void associaAtividade() {
        Atividade atividade = new Atividade("A1", "Monitoramento de chats dos alunos de computacao do primeiro periodo.", "ALTO", "É muito grave.");
        atividade.cadastraItem("Monitoramento facebook/messenger");
        atividade.cadastraItem("Monitoramento slack");
        atividade.cadastraItem("Monitoramento discord");
        atividade.cadastraItem("Monitoramento whatsapp");
        atividade.cadastraResultado("Resultado 1");
        atividade.cadastraResultado("Resultado 2");
        atividade.executaAtividade(1, 5);
        assertTrue(pesquisaTest.associaAtividade("A1", atividade));
        assertFalse(pesquisaTest.associaAtividade("A1", atividade));
    }

    @Test
    void desassociaAtividade() {
        associaAtividade();
        assertTrue(pesquisaTest.desassociaAtividade("A1"));
        assertFalse(pesquisaTest.desassociaAtividade("A1"));
        assertFalse(pesquisaTest.desassociaAtividade("A2"));
    }

    @Test
    void proximaAtividade() {
        associaAtividade();
        Atividade atividade2 = new Atividade("A2", "Degustacao de uma nova remeca de cervejas, criadas a partir de um novo processo" +
                " de fermentacao.", "MEDIO", "Degustadores podem sofrer com problemas de saude nessa atividade, tal como" +
                " ser alergico a algum ingrediente da cerveja.");
        atividade2.cadastraItem("Item 1");
        atividade2.cadastraItem("Item 2");
        atividade2.executaAtividade(1, 3);
        pesquisaTest.associaAtividade("A2", atividade2);

        assertEquals("A1", pesquisaTest.proximaAtividade("MAIS_ANTIGA"));
        assertEquals("A2", pesquisaTest.proximaAtividade("MENOS_PENDENCIAS"));
        assertEquals("A1", pesquisaTest.proximaAtividade("MAIOR_RISCO"));
        assertEquals("A1", pesquisaTest.proximaAtividade("MAIOR_DURACAO"));

    }

    @Test
    void hasAtividade() {
        associaAtividade();
        assertTrue(pesquisaTest.hasAtividade("A1"));
        assertFalse(pesquisaTest.hasAtividade("A2"));
    }

    @Test
    void testToString() {
        assertEquals("COM1 - Autoavaliacao na Disciplina de Programacao Orientada a Objeto. - computacao, poo", pesquisaTest.toString());
    }

    @Test
    void testEquals() {
        assertEquals(pesquisaTest, new Pesquisa("COM1", "Descricao", "campo, interesse"));
        assertNotEquals(pesquisaTest, new Pesquisa("COM2", "Descricao", "campo, interesse"));
    }

    @Test
    void testHashCode() {
        assertEquals(pesquisaTest.hashCode(), new Pesquisa("COM1", "Descricao", "campo, interesse").hashCode());
        assertNotEquals(pesquisaTest.hashCode(), new Pesquisa("COM2", "Descricao", "campo, interesse").hashCode());
    }

    @Test
    void listaPesquisadores() {
        associaPesquisador();
        assertEquals("        - Kilua (estudante) - Gerador de raios. - kilua@email - https://kilua.png", pesquisaTest.listaPesquisadores());
    }

    @Test
    void getProblemaResumo() {
        associaProblema();
        assertEquals("P1 - Um problema. - 2", pesquisaTest.getProblemaResumo());
    }

    @Test
    void getObjetivosResumo() {
        associaObjetivo();
        assertEquals("        - O1 - GERAL - Melhoria no ensino de OO. - 5\n" +
                              "        - O2 - GERAL - Melhoria no ensino de P2. - 5", pesquisaTest.getObjetivosResumo());
    }

    @Test
    void getAtividadesResumo() {
        associaAtividade();
        assertEquals("        - Monitoramento de chats dos alunos de computacao do primeiro periodo. (ALTO - É muito grave.)\n" +
                "            - REALIZADO - ITEM1\n" +
                "            - PENDENTE - ITEM2\n" +
                "            - PENDENTE - ITEM3\n" +
                "            - PENDENTE - ITEM4", pesquisaTest.getAtividadesResumo());
    }

    @Test
    void getAtividadesResultado() {
        associaAtividade();
        assertEquals("        - Monitoramento de chats dos alunos de computacao do primeiro periodo.\n" +
                "            - ITEM1 - 5\n" +
                "            - Resultado 1\n" +
                "            - Resultado 2", pesquisaTest.getAtividadesResultado());
    }
}