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
        assertEquals(1, pesquisaTest.getObjetivos());
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
        assertFalse(pesquisaTest.contemObjetivo(new Objetivo("O2", "GERAL", "Melhoria no ensino de OO.", 2, 3)));
    }

    @Test
    void associaAtividade() {
        assertTrue(pesquisaTest.associaAtividade("A1",
                new Atividade("A1", "Monitoramento facebook/messenger", "ALTO", "É muito grave.")));
        assertFalse(pesquisaTest.associaAtividade("A1",
                new Atividade("A1", "Monitoramento facebook/messenger", "ALTO", "É muito grave.")));
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
    }

    @Test
    void getObjetivosResumo() {
    }

    @Test
    void getAtividadesResumo() {
    }

    @Test
    void getAtividadesResultado() {
    }
}