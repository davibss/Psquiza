package com.tests;

import com.psquiza.controllers.ControllerAtividade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerAtividadeTest {

    private ControllerAtividade controller = new ControllerAtividade();

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

        assertTrue(excecoesOK);
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
}