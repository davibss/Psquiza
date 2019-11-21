package com.tests;

import com.psquiza.controllers.ControllerGeral;
import org.junit.jupiter.api.Test;
import com.psquiza.verificadores.Verificador;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class ControllerGeralTest {
    private ControllerGeral controllerGeral = new ControllerGeral();

    public String verificaExcecao(Runnable runnable){
        try{
            runnable.run();
        } catch (IllegalArgumentException e){
            return e.getMessage();
        }
        return null;
    }
    //Teste Caso de Uso 10
    @Test
    void configuraEstrategiaVazioNulo(){
        assertEquals("Estrategia nao pode ser nula ou vazia.", verificaExcecao(() -> controllerGeral.configuraEstrategia(null)));
        assertEquals("Estrategia nao pode ser nula ou vazia.", verificaExcecao(() -> controllerGeral.configuraEstrategia("")));
    }
    @Test
    void configuraEstrategiaInvalido(){
        assertEquals("Valor invalido da estrategia", verificaExcecao(() -> controllerGeral.configuraEstrategia("MENOR_DURACAO")));
    }
    @Test
    void proximaAtividadeVazioNulo(){
        controllerGeral.cadastrarPesquisa("Identificacao de buracos negros com uso de programacao", "astronomia, computacao");
        assertEquals("Pesquisa nao pode ser nula ou vazia.", verificaExcecao(() -> controllerGeral.proximaAtividade("")));
        assertEquals("Pesquisa nao pode ser nula ou vazia.", verificaExcecao(() -> controllerGeral.proximaAtividade(null)));
    }
}
