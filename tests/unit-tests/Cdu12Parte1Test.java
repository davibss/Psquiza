package com.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.psquiza.controllers.ControllerGeral;
import com.psquiza.controllers.ControllerPesquisa;
import com.psquiza.controllers.ControllerPesquisador;
import com.psquiza.controllers.ControllerObjetivo;
import com.psquiza.controllers.ControllerProblema;
import com.psquiza.controllers.ControllerAtividade;

import static org.junit.jupiter.api.Assertions.*;


class Cdu12Parte1Test {

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
    }

    private void cadastrarPesquisas() {
        controllerGeral.cadastrarPesquisa("Identificacao de buracos negros com uso de programacao", "astronomia, computacao");
        controllerGeral.cadastrarPesquisa("Alienacao Parental e o Sistema de Justica Brasileiro.", "psicologia, sistema juridico, alienacao parental, brasil");
    }

    private void cadastrarPesquisadores() {
        controllerGeral.cadastraPesquisador("killua zoldyck", "estudante", "Interessado em eletricidade, o terceiro de cinco filhos da famosa familia Zaoldyeck.",
                "hunterxhunter@1998","https://godspeed");
        controllerGeral.cadastraPesquisador("heisenberg", "professor", "Interessado nos efeitos da metafetamina e no estudo sobre o cancer. Pesquisador principal da pesquisa de radigrafia a fotons, peca fundamental na pesquisa que ganhou um premio nobel.",
                "breakingbad@2008","https://iamthedanger");
    }

    private void cadastrarObjetivos() {
        controllerGeral.cadastraObjetivo("ESPECIFICO", "Obter a preferencia de filmes e series de um usuario.", 2, 3);
        controllerGeral.cadastraObjetivo("GERAL", "Saber qual epoca do ano eh melhor para criacao de frutos do mar.", 1,1);
        controllerGeral.cadastraObjetivo("ESPECIFICO", "Testar os conhecimentos dos alunos de P2 em OO.", 3,4);
    }

    private void cadastrarProblemas() {
        controllerGeral.cadastraProblema("Dificuldade no aprendizado de OO no 2 periodo.", 2);
        controllerGeral.cadastraProblema("Problema em manter area de conservacao das especies marinhas.", 1);
    }

    private void cadastraAtividades() {
        controllerGeral.cadastrarAtividade("Realizar entrevistas com estudantes do primeiro periodo.", "BAIXO", "O risco de apenas realizar entrevistas não é elevado.");
        controllerGeral.cadastrarAtividade("Realizar o monitoramento de postes eletricos.", "ALTO", "Lidar com equipamentos eletricos de alta potencia pode levar a diversos acidentes.");
        controllerGeral.cadastraItem("A1", "Entrevistas com alunos de Ciencia da Computacao");
        controllerGeral.cadastraItem("A1", "Entrevistas com alunos de Engenharia Eletrica");
    }
    @Test
    void salva() {
        cadastrarPesquisas();
        cadastrarPesquisadores();
        cadastrarObjetivos();
        cadastrarProblemas();
        cadastraAtividades();
        controllerGeral.salva();
    }
}