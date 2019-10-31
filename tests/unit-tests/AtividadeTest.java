package com.tests;

import com.psquiza.entidades.Atividade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AtividadeTest {

    private Atividade atividade1 = new Atividade("A1", "Realizar entrevistas com estudantes do primeiro periodo.", "BAIXO", "O risco de apenas realizar entrevistas não é elevado.");
    private Atividade atividade2 =  new Atividade("A2", "Realizar um mapeamento da UFCG", "MEDIO", "Essa atividade requer a relaizacao de medicoes em terrenos ingrimes.");
    private Atividade atividade3 =  new Atividade("A3", "Realizar o monitoramento de postes eletricos.", "ALTO", "Lidar com equipamentos eletricos de alta potencia pode levar a diversos acidentes.");

    @BeforeEach
    void criaItens() {
        atividade1.cadastraItem("Entrevistas com alunos de Ciencia da Computacao");
        atividade1.cadastraItem("Entrevistas com alunos de Engenharia Eletrica");
        atividade2.cadastraItem("Mapeamento da entrada principal");
    }

    @Test
    void testToString() {
        assertEquals("Realizar entrevistas com estudantes do primeiro periodo. (BAIXO - O risco de apenas realizar entrevistas não é elevado.) | PENDENTE - Entrevistas com alunos de Ciencia da Computacao" +
                " | PENDENTE - Entrevistas com alunos de Engenharia Eletrica", atividade1.toString());
        assertEquals("Realizar um mapeamento da UFCG (MEDIO - Essa atividade requer a relaizacao de medicoes em terrenos ingrimes.) | PENDENTE - Mapeamento da entrada principal",
                atividade2.toString());
        assertEquals("Realizar o monitoramento de postes eletricos. (ALTO - Lidar com equipamentos eletricos de alta potencia pode levar a diversos acidentes.)", atividade3.toString());

        atividade3.cadastraItem("Monitoramento de postes do bairro Universitario");
        assertEquals("Realizar o monitoramento de postes eletricos. (ALTO - Lidar com equipamentos eletricos de alta potencia pode levar a diversos acidentes.) | PENDENTE - Monitoramento de postes do bairro Universitario",
                atividade3.toString());
    }

    @Test
    void testEquals() {
        Atividade atividade4 = new Atividade("A1", "Realizar entrevistas com estudantes do primeiro periodo.", "BAIXO", "O risco de apenas realizar entrevistas não é elevado.");
        Atividade atividade5 =  new Atividade("A2", "Realizar um mapeamento da UFCG", "MEDIO", "Essa atividade requer a relaizacao de medicoes em terrenos ingrimes.");
        Atividade atividade6 =  new Atividade("A3", "Realizar o monitoramento de postes eletricos.", "ALTO", "Lidar com equipamentos eletricos de alta potencia pode levar a diversos acidentes.");

        assertTrue(atividade1.equals(atividade4));
        assertTrue(atividade2.equals(atividade5));
        assertTrue(atividade3.equals(atividade6));
    }

    @Test
    void contaItensPendentes() {
        assertEquals(2, atividade1.contaItensPendentes());
        assertEquals(1, atividade2.contaItensPendentes());
        assertEquals(0, atividade3.contaItensPendentes());

        atividade1.cadastraItem("a");
        atividade2.cadastraItem("b");
        atividade3.cadastraItem("c");

        assertEquals(3, atividade1.contaItensPendentes());
        assertEquals(2, atividade2.contaItensPendentes());
        assertEquals(1, atividade3.contaItensPendentes());
    }

    @Test
    void contaItensRealizados() {
        assertEquals(0, atividade1.contaItensRealizados());
        assertEquals(0, atividade2.contaItensRealizados());
        assertEquals(0, atividade3.contaItensRealizados());

        atividade1.cadastraItem("a");
        atividade2.cadastraItem("b");
        atividade3.cadastraItem("c");

        assertEquals(0, atividade1.contaItensRealizados());
        assertEquals(0, atividade2.contaItensRealizados());
        assertEquals(0, atividade3.contaItensRealizados());
    }
}