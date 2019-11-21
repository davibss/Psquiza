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
        ControllerGeral controllerGeral = new ControllerGeral();
        ControllerPesquisa controllerPesquisa =  new ControllerPesquisa();
        ControllerPesquisador controllerPesquisador = new ControllerPesquisador();
        ControllerObjetivo controllerObjetivo = new ControllerObjetivo();
        ControllerProblema controllerProblema = new ControllerProblema();
        ControllerAtividade controllerAtividade = new ControllerAtividade();
    }

    private void cadastrarPesquisas() {
        controllerPesquisa.cadastrarPesquisa("Identificacao de buracos negros com uso de programacao", "astronomia, computacao");
        controllerPesquisa.cadastrarPesquisa("Alienacao Parental e o Sistema de Justica Brasileiro.", "psicologia, sistema juridico, alienacao parental, brasil");
    }

    private void cadastrarPesquisadores() {
        controllerPesquisador.cadastraPesquisador("killua zoldyck", "estudante", "Interessado em eletricidade, o terceiro de cinco filhos da famosa familia Zaoldyeck.",
                "hunterxhunter@1998","https://godspeed");
        controllerPesquisador.cadastraPesquisador("heisenberg", "professor", "Interessado nos efeitos da metafetamina e no estudo sobre o cancer. Pesquisador principal da pesquisa de radigrafia a fotons, peca fundamental na pesquisa que ganhou um premio nobel.",
                "breakingbad@2008","https://iamthedanger");
    }

    private void cadastrarObjetivos() {
        controllerObjetivo.cadastraObjetivo("ESPECIFICO", "Obter a preferencia de filmes e series de um usuario.", 2, 3);
        controllerObjetivo.cadastraObjetivo("GERAL", "Saber qual epoca do ano eh melhor para criacao de frutos do mar.", 1,1);
        controllerObjetivo.cadastraObjetivo("ESPECIFICO", "Testar os conhecimentos dos alunos de P2 em OO.", 3,4);
    }

    private void cadastrarProblemas() {
        controllerProblema.cadastraProblema("Dificuldade no aprendizado de OO no 2 periodo.", 2);
        controllerProblema.cadastraProblema("Problema em manter area de conservacao das especies marinhas.", 1);
    }

    private void cadastraAtividades() {
        controllerAtividade.cadastrarAtividades("Realizar entrevistas com estudantes do primeiro periodo.", "BAIXO", "O risco de apenas realizar entrevistas não é elevado.");
        controllerAtividade.cadastrarAtividades("Realizar o monitoramento de postes eletricos.", "ALTO", "Lidar com equipamentos eletricos de alta potencia pode levar a diversos acidentes.");
        controllerAtividade.cadastraItem("A1", "Entrevistas com alunos de Ciencia da Computacao");
        controllerAtividade.cadastraItem("A1", "Entrevistas com alunos de Engenharia Eletrica");
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