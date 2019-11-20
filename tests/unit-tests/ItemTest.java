package com.tests;

import com.psquiza.entidades.Item;
import com.psquiza.verificadores.Verificador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    private Item item;

    @BeforeEach
    void criaItem(){
        item = new Item("Monitoramento facebook/messenger");
    }

    @Test
    void criarInvalido(){
        assertEquals("Campo nomeItem nao pode ser nulo ou vazio.",
                Verificador.verificaExcecao(() -> new Item("")));
        assertEquals("Campo nomeItem nao pode ser nulo ou vazio.",
                Verificador.verificaExcecao(() -> new Item(null)));
    }

    @Test
    void executa() {
        assertEquals("Duracao nao pode ser nula ou negativa.",
                Verificador.verificaExcecao(() -> item.executa(-2)));
         item.executa(3);
         assertTrue(item.isRealizado());
         assertEquals(3, item.getDuracao());
    }

    @Test
    void testToString() {
        executa();
        assertEquals("REALIZADO - Monitoramento facebook/messenger", item.toString());
    }

    @Test
    void testEquals() {
        assertNotEquals(new Item("Teste"), item);
        assertEquals(new Item("Monitoramento facebook/messenger"), item);
    }

    @Test
    void testHashCode() {
        assertTrue(new Item("Teste").hashCode() != item.hashCode());
        assertEquals(new Item("Monitoramento facebook/messenger").hashCode(), item.hashCode());
    }

    @Test
    void estadoItem() {
        executa();
        assertEquals("PENDENTE", new Item("Monitoramento whatsapp.").estadoItem());
        assertEquals("REALIZADO", item.estadoItem());
    }
}