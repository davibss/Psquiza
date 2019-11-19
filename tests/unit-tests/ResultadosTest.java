package com.tests;

import com.psquiza.controllers.*;
import com.psquiza.entidades.Pesquisador;
import com.psquiza.verificadores.Verificador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.StringJoiner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Classe responsável por testar o caso de uso 11;
 */
public class ResultadosTest {

    private ControllerPesquisa controllerPesquisa;
    private ControllerAtividade controllerAtividade;
    private ControllerObjetivo controllerObjetivo;
    private ControllerProblema controllerProblema;
    private ControllerPesquisador controllerPesquisador;


    @BeforeEach
    void criarController(){
        controllerPesquisa = new ControllerPesquisa();
        controllerAtividade = new ControllerAtividade();
        controllerObjetivo = new ControllerObjetivo();
        controllerProblema = new ControllerProblema();
        controllerProblema = new ControllerProblema();
        controllerPesquisador = new ControllerPesquisador();
        controllerAtividade.cadastrarAtividades("Monitoramento de chats dos alunos de computacao do primeiro periodo.",
                "ALTO","Por se tratar de apenas um monitoramento, o risco nao e elevado.");
        controllerAtividade.cadastrarAtividades("Degustacao de uma nova remeca de cervejas, criadas a partir de um novo processo de fermentacao.",
                "MEDIO", "Degustadores podem sofrer com problemas de saude nessa atividade, tal como ser alergico a algum ingrediente da cerveja.");
        assertEquals("AST1", controllerPesquisa.cadastrarPesquisa("Identificacao de buracos negros com uso de programacao", "astronomia, computacao"));
        assertEquals("PSI1", controllerPesquisa.cadastrarPesquisa("Alienacao Parental e o Sistema de Justica Brasileiro.", "psicologia, sistema juridico, alienacao parental, brasil"));
        cadastrarObjetivos();
        cadastrarProblemas();
        cadastrarAtividade();
        cadastrarPesquisadores();
    }

    private void cadastrarAtividade() {
        controllerAtividade.cadastrarAtividades("Monitoramento de chats dos alunos de computacao do primeiro periodo.",
                "ALTO","Por se tratar de apenas um monitoramento, o risco nao e elevado.");
        controllerAtividade.cadastrarAtividades("Degustacao de uma nova remeca de cervejas, criadas a partir de um novo processo de fermentacao.",
                "MEDIO", "Degustadores podem sofrer com problemas de saude nessa atividade, tal como ser alergico a algum ingrediente da cerveja.");
        controllerAtividade.cadastraItem("A1", "Monitoramento facebook/messenger");
        controllerAtividade.cadastraItem("A1", "Monitoramento slack");
        controllerAtividade.cadastraItem("A1", "Monitoramento discord");
        controllerAtividade.cadastraItem("A1", "Monitoramento whatsapp");
        controllerAtividade.executaAtividade("A1", 1, 3);
    }

    private void cadastrarObjetivos() {
        controllerObjetivo.cadastraObjetivo("ESPECIFICO", "Obter a preferencia de filmes e series de um usuario.", 2, 3);
        controllerObjetivo.cadastraObjetivo("GERAL", "Saber qual epoca do ano eh melhor para criacao de frutos do mar.", 1,1);
        controllerObjetivo.cadastraObjetivo("ESPECIFICO", "Testar os conhecimentos dos alunos de P2 em OO.", 3,4);
    }

    private void cadastrarProblemas() {
        controllerProblema.cadastraProblema("Dificuldade no aprendizado de OO no 2 periodo.", 2);
        controllerProblema.cadastraProblema("Problema em manter area de conservacao das especies marinhas.", 1);
        //controllerProblema.cadastraProblema("Dificuldade no aprendizado de OO no 2 periodo.", 2);
    }

    private void cadastrarPesquisadores(){
        controllerPesquisador.cadastraPesquisador("killua zoldyck", "estudante", "Interessado em eletricidade, o terceiro de cinco filhos da famosa familia Zaoldyeck.",
                "hunterxhunter@1998","https://godspeed");
        controllerPesquisador.cadastraPesquisador("heisenberg", "professor", "Interessado nos efeitos da metafetamina e no estudo sobre o cancer. Pesquisador principal da pesquisa de radigrafia a fotons, peca fundamental na pesquisa que ganhou um premio nobel.",
                "breakingbad@2008","https://iamthedanger");
        controllerPesquisador.cadastraPesquisador("Prairie Johnson", "externo", "Interessada no estudo de multiplas dimensoes e no estudo dos sentidos humanos.",
                "theoa@2016","https://notblind");
        controllerPesquisador.cadastraPesquisador("Joel","externo","Interessado em fungos.","thelastofus@2013","https://Cordyceps");
    }

    @Test
    void associaPesquisador(){
        controllerPesquisa.associaPesquisador("AST1","email@email",
                controllerPesquisador.getPesquisador("hunterxhunter@1998"));
        controllerPesquisa.associaPesquisador("AST1","email@email",
                controllerPesquisador.getPesquisador("breakingbad@2008"));
        controllerPesquisa.associaPesquisador("AST1","maria@email",
                controllerPesquisador.getPesquisador("theoa@2016"));
    }

    @Test
    void associaProblema(){
        controllerPesquisa.associaProblema("AST1", controllerProblema.getProblema("P1"));
        //assertFalse(controllerPesquisa.associaProblema("AST1", controllerProblema.getProblema("P1")));
    }

    @Test
    void associaObjetivo(){
        controllerPesquisa.associaObjetivo("AST1", controllerObjetivo.getObjetivo("O1"));
        //assertFalse(controllerPesquisa.associaObjetivo("AST1", controllerObjetivo.getObjetivo("O1")));
    }

    @Test
    void associaAtividade(){
        controllerPesquisa.associaAtividade
                ("AST1", "A1", controllerAtividade.getAtividade("A1"));
//        assertFalse(controllerPesquisa.associaAtividade
//                ("AST1", "A1", controllerAtividade.getAtividade("A1")));
    }

    @Test
    void gravarResumoInvalido(){
        assertEquals("Pesquisa nao pode ser nula ou vazia.",
                Verificador.verificaExcecao(() -> {
                    try { controllerPesquisa.gravarResumo(""); } catch (IOException e) {
                        e.printStackTrace();
                    }
                }));
        assertEquals("Pesquisa nao encontrada.",
                Verificador.verificaExcecao(() -> {
                    try { controllerPesquisa.gravarResumo("COM8000"); } catch (IOException e) {
                        e.printStackTrace();
                    }
                }));
    }

    @Test
    void gravarResumo(){
        associaAtividade();
        associaObjetivo();
        associaPesquisador();
        associaProblema();
        try {
            controllerPesquisa.gravarResumo("AST1");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //("Identificacao de buracos negros com uso de programacao", "astronomia, computacao"));
        //"killua zoldyck", "estudante", "Interessado em eletricidade, o terceiro de cinco filhos da famosa familia Zaoldyeck.",
        //        "hunterxhunter@1998","https://godspeed");
        //"heisenberg", "professor", "Interessado nos efeitos da metafetamina e no estudo sobre o cancer. Pesquisador principal da pesquisa de radigrafia a fotons, peca fundamental na pesquisa que ganhou um premio nobel.",
        //        "breakingbad@2008","https://iamthedanger");
        //"Prairie Johnson", "externo", "Interessada no estudo de multiplas dimensoes e no estudo dos sentidos humanos.",
        //        "theoa@2016","https://notblind");
        //"Joel","externo","Interessado em fungos.","thelastofus@2013","https://Cordyceps");
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add("- Pesquisa: AST1 - Identificacao de buracos negros com uso de programacao - astronomia, computacao");
        joiner.add(String.format("%19s", "- Pesquisadores:"));
        joiner.add(String.format("%23s (%s) - %s - %s - %s", "- killua zoldyck", "estudante", "Interessado em eletricidade, o terceiro de cinco filhos da famosa familia Zaoldyeck.",
                "hunterxhunter@1998", "https://godspeed"));
        joiner.add(String.format("%23s (%s) - %s - %s - %s", "- heisenberg", "professor", "Interessado nos efeitos da metafetamina e no estudo sobre o cancer. Pesquisador principal da pesquisa de radigrafia a fotons, peca fundamental na pesquisa que ganhou um premio nobel.",
                "breakingbad@2008", "https://iamthedanger"));
        joiner.add(String.format("%23s (%s) - %s - %s - %s", "- Prairie Johnson", "externo", "Interessada no estudo de multiplas dimensoes e no estudo dos sentidos humanos.",
                "theoa@2016", "https://notblind"));
        joiner.add(String.format("%19s", "- Problema:"));
        joiner.add(String.format("%23s - %s - %d", "- P1", "Dificuldade no aprendizado de OO no 2 periodo.", 2));
        joiner.add(String.format("%19s", "- Objetivos:"));
        //("ESPECIFICO", "Obter a preferencia de filmes e series de um usuario.", 2, 3);
        joiner.add(String.format("%23s - %s - %s - %d", "- O1", "ESPECIFICO", "Obter a preferencia de filmes e series de um usuario.", 5));
        joiner.add(String.format("%19s", "- Atividades:"));
        //"Monitoramento de chats dos alunos de computacao do primeiro periodo.", "ALTO","Por se tratar de apenas um monitoramento, o risco nao e elevado.");
        joiner.add(String.format("%23s (%s - %s)", "- Monitoramento de chats dos alunos de computacao do primeiro periodo.", "ALTO", "Por se tratar de apenas um monitoramento, o risco nao e elevado."));
        joiner.add(String.format("%27s - %s", "- REALIZADO", "ITEM1"));
        joiner.add(String.format("%27s - %s", "- PENDENTE", "ITEM2"));
        joiner.add(String.format("%27s - %s", "- PENDENTE", "ITEM3"));
        joiner.add(String.format("%27s - %s", "- PENDENTE", "ITEM4"));
        // IMPLEMENTAR ARQUIVO
        System.out.println(joiner.toString());
    }

}
