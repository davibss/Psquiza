package com.tests;

import com.psquiza.controllers.*;
import com.psquiza.entidades.Buscador;
import com.psquiza.verificadores.Verificador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuscadorTest {

    private ControllerAtividade controllerAtividade;
    private ControllerPesquisa controllerPesquisa;
    private ControllerPesquisador controllerPesquisador;
    private ControllerObjetivo controllerObjetivo;
    private ControllerProblema controllerProblema;
    private Buscador buscador;

    @BeforeEach
    void criaBuscador(){
        controllerAtividade = new ControllerAtividade();
        controllerPesquisa = new ControllerPesquisa();
        controllerPesquisador = new ControllerPesquisador();
        controllerObjetivo = new ControllerObjetivo();
        controllerProblema = new ControllerProblema();
        buscador = new Buscador(controllerAtividade, controllerPesquisa, controllerPesquisador, controllerObjetivo, controllerProblema);
        cadastrarAtividades();
        cadastrarPesquisas();
        cadastrarPesquisadores();
        cadastrarObjetivos();
        cadastrarProblemas();
    }

    private void cadastrarAtividades() {
        controllerAtividade.cadastrarAtividades("Analise das condicoes de criacao de frutos do mar.", "MEDIO", "Eh necessario equipamento correto.");
        controllerAtividade.cadastrarAtividades("Coleta de dados de servicos de streaming.", "BAIXO", "Nao ha riscos.");
        controllerAtividade.cadastrarAtividades("Planejamento do design do projeto.", "BAIXO", "Equipe ausente.");
    }

    private void cadastrarPesquisas() {
        controllerPesquisa.cadastrarPesquisa("Aspectos da criacao de frutos do mar no Brasil.", "agropecuaria, culinaria");
        controllerPesquisa.cadastrarPesquisa("Analise comportamental de usuarios de streaming.", "computacao, estatistica");
        controllerPesquisa.cadastrarPesquisa("Projeto para gerenciamento de pesquisas.", "computacao, pesquisa");
    }

    private void cadastrarPesquisadores() {
        controllerPesquisador.cadastraPesquisador("Joel", "externo", "Interresado em fungos.", "thelastofus@2013", "https://Cordyceps");
        controllerPesquisador.cadastraPesquisador("Uzumaki naruto", "estudante", "Interresado nos efeitos da marginalizacao de individuos pelo sociedade e seus empactos sociais.", "borutofathers@1997", "https://dattebayo");
        controllerPesquisador.cadastraPesquisador("heisenberg", "professor", "Interresado nos efeitos da metafetamina e no estudo sobre o cancer. Pesquisador principal da pesquisa de radigrafia a fotons, peca fundamental na pesquisa que ganhou um premio nobel.", "breakingbad@2008", "https://iamthedanger");
    }

    private void cadastrarObjetivos() {
        controllerObjetivo.cadastraObjetivo("ESPECIFICO", "Obter a preferencia de filmes e series de um usuario.", 2, 3);
        controllerObjetivo.cadastraObjetivo("GERAL", "Saber qual epoca do ano eh melhor para criacao de frutos do mar.", 1,1);
        controllerObjetivo.cadastraObjetivo("ESPECIFICO", "Testar os conhecimentos dos alunos de P2 em OO.", 3,4);
    }

    private void cadastrarProblemas() {
        controllerProblema.cadastraProblema("Dificuldade no aprendizado de OO no 2 periodo.", 2);
        controllerProblema.cadastraProblema("Problema em manter area de conservacao das especies marinhas.", 1);
        controllerProblema.cadastraProblema("Dificuldade no aprendizado de OO no 2 periodo.", 2);
    }

    @Test
    void buscaGeralInvalido() {
        assertEquals("Campo termo nao pode ser nulo ou vazio.",
                Verificador.verificaExcecao(() -> buscador.buscaGeral("")));
        assertEquals("Campo termo nao pode ser nulo ou vazio.",
                Verificador.verificaExcecao(() -> buscador.buscaGeral(null)));
    }

    @Test
    void buscaGeral() {
        assertEquals("AGR1: Aspectos da criacao de frutos do mar no Brasil. |" +
                " O2: Saber qual epoca do ano eh melhor para criacao de frutos do mar. |" +
                " A1: Analise das condicoes de criacao de frutos do mar.", buscador.buscaGeral("criacao"));
        assertEquals("COM2: computacao, pesquisa | COM1: computacao, estatistica",
                buscador.buscaGeral("computacao"));
        assertEquals("COM1: Analise comportamental de usuarios de streaming. |" +
                " AGR1: Aspectos da criacao de frutos do mar no Brasil. | thelastofus@2013: Interresado em fungos. |" +
                " breakingbad@2008: Interresado nos efeitos da metafetamina e no estudo sobre o cancer. Pesquisador principal da pesquisa de radigrafia a fotons, peca fundamental na pesquisa que ganhou um premio nobel. |" +
                " borutofathers@1997: Interresado nos efeitos da marginalizacao de individuos pelo sociedade e seus empactos sociais. |" +
                " O3: Testar os conhecimentos dos alunos de P2 em OO. | O2: Saber qual epoca do ano eh melhor para criacao de frutos do mar. |" +
                " A2: Coleta de dados de servicos de streaming. | A2: Nao ha riscos. |" +
                " A1: Analise das condicoes de criacao de frutos do mar.", buscador.buscaGeral("os"));
    }

    @Test
    void buscaPorNumeroInvalido() {
        assertEquals("Campo termo nao pode ser nulo ou vazio.",
                Verificador.verificaExcecao(() -> buscador.buscaPorNumero("", 2)));
        assertEquals("Campo termo nao pode ser nulo ou vazio.",
                Verificador.verificaExcecao(() -> buscador.buscaPorNumero(null, 2)));
        assertEquals("Numero do resultado nao pode ser negativo",
                Verificador.verificaExcecao(() -> buscador.buscaPorNumero("computacao", -3)));
    }

    @Test
    void buscaPorNumero() {
        assertEquals("A1: Analise das condicoes de criacao de frutos do mar.",
                buscador.buscaPorNumero("criacao", 3));
        assertEquals("borutofathers@1997: Interresado nos efeitos da marginalizacao de individuos pelo sociedade e seus empactos sociais.",
                buscador.buscaPorNumero("os", 5));
    }

    @Test
    void contaResultadosInvalido() {
        assertEquals("Campo termo nao pode ser nulo ou vazio.",
                Verificador.verificaExcecao(() -> buscador.contaResultados("")));
        assertEquals("Campo termo nao pode ser nulo ou vazio.",
                Verificador.verificaExcecao(() -> buscador.contaResultados(null)));
        assertEquals("Nenhum resultado encontrado",
                Verificador.verificaExcecao(() -> buscador.contaResultados("supercalifragilisticexpialidocious")));
    }

    @Test
    void contaResultados() {
        assertEquals(3, buscador.contaResultados("criacao"));
        assertEquals(2, buscador.contaResultados("computacao"));
        assertEquals(10, buscador.contaResultados("os"));
    }
}