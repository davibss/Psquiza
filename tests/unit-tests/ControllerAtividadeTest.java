package com.tests;

import com.psquiza.controllers.ControllerAtividade;
import com.psquiza.verificadores.Verificador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerAtividadeTest {

    private ControllerAtividade controller = new ControllerAtividade();

    public String verificaExcecao(Runnable runnable){
        try{
            runnable.run();
        } catch (IllegalArgumentException e){
            return e.getMessage();
        }
        return null;
    }

    @BeforeEach
    void criaAtividades() {
        controller.cadastrarAtividades("Realizar entrevistas com estudantes do primeiro periodo.", "BAIXO", "O risco de apenas realizar entrevistas não é elevado.");
        controller.cadastrarAtividades("Realizar o monitoramento de postes eletricos.", "ALTO", "Lidar com equipamentos eletricos de alta potencia pode levar a diversos acidentes.");
        controller.cadastraItem("A1", "Entrevistas com alunos de Ciencia da Computacao");
        controller.cadastraItem("A1", "Entrevistas com alunos de Engenharia Eletrica");
    }


    @Test
    void cadastrarAtividades() {
        controller.cadastrarAtividades("Realizar um mapeamento da UFCG", "MEDIO", "Essa atividade requer a relaizacao de medicoes em terrenos ingrimes.");
        assertEquals("Realizar um mapeamento da UFCG (MEDIO - Essa atividade requer a relaizacao de medicoes em terrenos ingrimes.)", controller.exibeAtividade("A3"));

        boolean excecoesOK;
        try {
            controller.cadastrarAtividades("Realizar um mapeamento da UFCG", "MEDIO", "Essa atividade requer a relaizacao de medicoes em terrenos ingrimes.");
        }catch (Exception e1) {
            excecoesOK = true;
        }

        try {
            controller.exibeAtividade("A4");
            excecoesOK = false;
        }catch (Exception e2) {
            excecoesOK = true;
        }
        // Q?
        //assertTrue(excecoesOK);
        assertFalse(excecoesOK);
    }

    @Test
    void apagarAtividade() {
        boolean excecoesOk = false;
        controller.apagarAtividade("A1");
        try {
            controller.exibeAtividade("A1");
        }catch(Exception e) {
            excecoesOk = true;
        }
        assertTrue(excecoesOk);

        assertEquals("Realizar o monitoramento de postes eletricos. (ALTO - Lidar com equipamentos eletricos de alta potencia pode levar a diversos acidentes.)", controller.exibeAtividade("A2"));
        controller.cadastrarAtividades("Realizar um mapeamento da UFCG", "MEDIO", "Essa atividade requer a relaizacao de medicoes em terrenos ingrimes.");
        assertEquals("Realizar um mapeamento da UFCG (MEDIO - Essa atividade requer a relaizacao de medicoes em terrenos ingrimes.)", controller.exibeAtividade("A3"));
    }

    @Test
    void cadastraItemInvalido() {
        controller.cadastraItem("A1", "Entrevistas com alunos de Engenharia Mecanica");
        assertEquals("Item ja cadastrado nesta atividade.",
                verificaExcecao(() -> controller.cadastraItem("A1", "Entrevistas com alunos de Engenharia Mecanica")));
    }

    @Test
    void cadastraItem() {
        controller.cadastraItem("A1", "Entrevistas com alunos de Engenharia Mecanica");
        controller.cadastraItem("A2", "Monitoramento de postes do bairro Universitario");
        assertEquals("Realizar entrevistas com estudantes do primeiro periodo. (BAIXO - O risco de apenas realizar entrevistas não é elevado.) | PENDENTE - Entrevistas com alunos de Ciencia da Computacao" +
                        " | PENDENTE - Entrevistas com alunos de Engenharia Eletrica | PENDENTE - Entrevistas com alunos de Engenharia Mecanica", controller.exibeAtividade("A1"));
        assertEquals("Realizar o monitoramento de postes eletricos. (ALTO - Lidar com equipamentos eletricos de alta potencia pode levar a diversos acidentes.) | PENDENTE - Monitoramento de postes do bairro Universitario",
                controller.exibeAtividade("A2"));
    }

    @Test
    void exibeAtividade() {
        assertEquals("Realizar entrevistas com estudantes do primeiro periodo. (BAIXO - O risco de apenas realizar entrevistas não é elevado.) | PENDENTE - Entrevistas com alunos de Ciencia da Computacao" +
                        " | PENDENTE - Entrevistas com alunos de Engenharia Eletrica", controller.exibeAtividade("A1"));
        assertEquals("Realizar o monitoramento de postes eletricos. (ALTO - Lidar com equipamentos eletricos de alta potencia pode levar a diversos acidentes.)", controller.exibeAtividade("A2"));
    }

    @Test
    void contaItensPendentes() {
        assertEquals(2, controller.contaItensPendentes("A1"));
        assertEquals(0, controller.contaItensPendentes("A2"));

        controller.cadastraItem("A1", "Entrevistas com alunos de Engenharia Mecanica");
        controller.cadastraItem("A2", "Monitoramento de postes do bairro Universitario");

        assertEquals(3, controller.contaItensPendentes("A1"));
        assertEquals(1, controller.contaItensPendentes("A2"));
    }

    @Test
    void contaItensRealizados() {
        assertEquals(0, controller.contaItensRealizados("A1"));
        assertEquals(0, controller.contaItensRealizados("A2"));

        controller.cadastraItem("A1", "Entrevistas com alunos de Engenharia Mecanica");
        controller.cadastraItem("A2", "Monitoramento de postes do bairro Universitario");

        assertEquals(0, controller.contaItensRealizados("A1"));
        assertEquals(0, controller.contaItensRealizados("A2"));
    }

    // TESTES DO CASO DE USO 7 (DAVI-AJUDANDO)

    @Test
    void executaAtividadeInvalido() {
        cadastrarAtividades();
        assertEquals("Item nao pode ser nulo ou negativo.",
                verificaExcecao(() -> controller.executaAtividade("A1", -1, 3)));
        assertEquals("Duracao nao pode ser nula ou negativa.",
                verificaExcecao(() -> controller.executaAtividade("A1", 1, -1)));
        assertEquals("Atividade nao encontrada",
                verificaExcecao(() -> controller.executaAtividade("A99", 1, 1)));
        assertEquals("Item nao encontrado.",
                verificaExcecao(() -> controller.executaAtividade("A1", 50, 1)));
        controller.executaAtividade("A1",1, 3);
        assertEquals("Item ja executado.",
                verificaExcecao(() -> controller.executaAtividade("A1", 1, 1)));
    }

    @Test
    void executaAtividade() {
        controller.executaAtividade("A1",1, 3);
    }

    @Test
    void cadastraResultadoInvalido() {
        assertEquals("Campo codigoAtividade nao pode ser nulo ou vazio.",
                verificaExcecao(() -> controller.cadastraResultado("", "Analise nao foi possivel.")));
        assertEquals("Campo codigoAtividade nao pode ser nulo ou vazio.",
                Verificador.verificaExcecao(() -> controller.cadastraResultado("", "Analise nao foi possivel.")));
        assertEquals("Resultado nao pode ser nulo ou vazio.",
                verificaExcecao(() -> controller.cadastraResultado("A1", "")));
        assertEquals("Atividade nao encontrada",
                verificaExcecao(() -> controller.cadastraResultado("A50", "Analise nao foi possivel.")));
    }

    @Test
    void cadastraResultado() {
        cadastrarAtividades();
        assertEquals(1,controller.cadastraResultado("A1", "Ocorreu sem problemas."));
    }

    @Test
    void removeResultadoInvalido() {
        cadastraResultado();
        assertEquals("Campo codigoAtividade nao pode ser nulo ou vazio.",
                verificaExcecao(() -> controller.removeResultado("", 3)));
        assertEquals("Campo codigoAtividade nao pode ser nulo ou vazio.",
                Verificador.verificaExcecao(() -> controller.removeResultado(null, 3)));
        assertEquals("Atividade nao encontrada",
                verificaExcecao(() -> controller.removeResultado("A99", 3)));
        assertEquals("numeroResultado nao pode ser nulo ou negativo.",
                verificaExcecao(() -> controller.removeResultado("A1", -3)));
        assertEquals("Resultado nao encontrado.",
                verificaExcecao(() -> controller.removeResultado("A1", 3)));
    }

    @Test
    void removeResultado() {
        cadastraResultado();
        assertTrue(controller.removeResultado("A1", 1));
    }

    @Test
    void listaResultadosInvalido() {
        assertEquals("Campo codigoAtividade nao pode ser nulo ou vazio.",
                verificaExcecao(() -> controller.listaResultados("")));
        assertEquals("Campo codigoAtividade nao pode ser nulo ou vazio.",
                Verificador.verificaExcecao(() -> controller.listaResultados("")));
        assertEquals("Atividade nao encontrada",
                verificaExcecao(() -> controller.listaResultados("A55")));
    }

    @Test
    void listaResultados() {
        cadastraResultado();
        assertEquals("Ocorreu sem problemas.", controller.listaResultados("A1"));
        assertEquals("", controller.listaResultados("A2"));
    }

    @Test
    void getDuracaoInvalido() {
        assertEquals("Campo codigoAtividade nao pode ser nulo ou vazio.",
                verificaExcecao(() -> controller.getDuracao("")));
        assertEquals("Campo codigoAtividade nao pode ser nulo ou vazio.",
                Verificador.verificaExcecao(() -> controller.getDuracao("")));
        assertEquals("Atividade nao encontrada",
                verificaExcecao(() -> controller.listaResultados("A55")));
    }

    @Test
    void getDuracao() {
        executaAtividade();
        assertEquals(3, controller.getDuracao("A1"));
    }

    @Test
    void contaProximos(){
        controller.cadastrarAtividades("Realizar o transporte de alimentos até a creche.", "MEDIO", "O risco de acidentes durante o transporte.");
        controller.defineProximaAtividade("A2","A1");
        controller.defineProximaAtividade("A3","A2");




        assertEquals(1, controller.contaProximos("A2"));
        assertEquals(2, controller.contaProximos("A3"));
        assertEquals(0, controller.contaProximos("A1"));
    }

    @Test
    void contaProximosInvalido(){
        assertEquals("Atividade nao pode ser nulo ou vazio.",
                verificaExcecao(() -> controller.contaProximos("")));
        assertEquals("Atividade nao pode ser nulo ou vazio.",
                Verificador.verificaExcecao(() -> controller.contaProximos(null)));
    }

    @Test
    void criandoLoop(){
        boolean erro = false;

        controller.cadastrarAtividades("Realizar o transporte de alimentos até a creche.", "MEDIO", "O risco de acidentes durante o transporte.");
        controller.defineProximaAtividade("A2","A1");
        controller.defineProximaAtividade("A3","A2");


        try{
            controller.defineProximaAtividade("A1","A3");

        }
        catch (Exception e){
            erro = true;
        }
        assertEquals(true, erro);
    }

    @Test
    void pegaProximo(){
        controller.cadastrarAtividades("Realizar o transporte de alimentos até a creche.", "MEDIO", "O risco de acidentes durante o transporte.");
        controller.defineProximaAtividade("A2","A1");
        controller.defineProximaAtividade("A3","A2");

        assertEquals("A1", controller.pegaProximo("A2", 1));
        assertEquals("A2", controller.pegaProximo("A3", 1));

    }

    @Test
    void pegaProximoInvalido(){
        assertEquals("Atividade nao pode ser nulo ou vazio.",
                verificaExcecao(() -> controller.pegaProximo("",1)));
        assertEquals("Atividade nao pode ser nulo ou vazio.",
                Verificador.verificaExcecao(() -> controller.pegaProximo(null,1)));
        assertEquals("EnesimaAtividade nao pode ser negativa ou zero.",
                verificaExcecao(() -> controller.pegaProximo("A2",-1)));
        assertEquals("EnesimaAtividade nao pode ser negativa ou zero.",
                verificaExcecao(() -> controller.pegaProximo("A2",0)));
    }
}