package com.tests;

import com.psquiza.controllers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Cdu12Parte2Test {

    private ControllerGeral controllerGeral;
    private ControllerPesquisa controllerPesquisa;
    private ControllerPesquisador controllerPesquisador;
    private ControllerObjetivo controllerObjetivo;
    private ControllerProblema controllerProblema;
    private ControllerAtividade controllerAtividade;

    @BeforeEach
    void criaControllers() {
        controllerGeral = new ControllerGeral();
        controllerPesquisa =  new ControllerPesquisa();
        controllerPesquisador = new ControllerPesquisador();
        controllerObjetivo = new ControllerObjetivo();
        controllerProblema = new ControllerProblema();
        controllerAtividade = new ControllerAtividade();
        controllerGeral.carrega();
    }

    @Test
    void testaPesquisas() {
        assertEquals("AST1 - Identificacao de buracos negros com uso de programacao - astronomia, computacao", controllerGeral.exibirPesquisa("AST1"));
        assertEquals("PSI1 - Alienacao Parental e o Sistema de Justica Brasileiro. - psicologia, sistema juridico, alienacao parental, brasil", controllerGeral.exibirPesquisa("PSI1"));
    }

    @Test
    void testaPesquisadores() {
        assertEquals("killua zoldyck (estudante) - Interessado em eletricidade, o terceiro de cinco filhos da famosa familia Zaoldyeck. - hunterxhunter@1998 - https://godspeed", controllerGeral.exibePesquisador("hunterxhunter@1998"));
        assertEquals("heisenberg (professor) - Interessado nos efeitos da metafetamina e no estudo sobre o cancer. Pesquisador principal da pesquisa de radigrafia a fotons, peca fundamental na pesquisa que ganhou um premio nobel." +
                " - breakingbad@2008 - https://iamthedanger", controllerGeral.exibePesquisador("breakingbad@2008"));
    }

    @Test
    void testaObjetivos() {
        assertEquals("O1 - ESPECIFICO - Obter a preferencia de filmes e series de um usuario. - 5",
                controllerGeral.exibeObjetivo("O1"));
        assertEquals("O2 - GERAL - Saber qual epoca do ano eh melhor para criacao de frutos do mar. - 2",
                controllerGeral.exibeObjetivo("O2"));
        assertEquals("O3 - ESPECIFICO - Testar os conhecimentos dos alunos de P2 em OO. - 7",
                controllerGeral.exibeObjetivo("O3"));
    }

    @Test
    void testaProblemas() {
        assertEquals("P1 - Dificuldade no aprendizado de OO no 2 periodo. - 2",
                controllerGeral.exibeProblema("P1"));
        assertEquals("P2 - Problema em manter area de conservacao das especies marinhas. - 1",
                controllerGeral.exibeProblema("P2"));
    }

    @Test
    void testaAtividades() {
        assertEquals("Realizar entrevistas com estudantes do primeiro periodo. (BAIXO - O risco de apenas realizar entrevistas não é elevado.) | PENDENTE - Entrevistas com alunos de Ciencia da Computacao" +
                " | PENDENTE - Entrevistas com alunos de Engenharia Eletrica", controllerGeral.exibeAtividade("A1"));
        assertEquals("Realizar o monitoramento de postes eletricos. (ALTO - Lidar com equipamentos eletricos de alta potencia pode levar a diversos acidentes.)", controllerGeral.exibeAtividade("A2"));
    }

}