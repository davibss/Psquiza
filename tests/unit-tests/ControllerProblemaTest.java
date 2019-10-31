package com.tests;

import com.psquiza.controllers.ControllerProblema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerProblemaTest {

    private ControllerProblema controllerProblema;

    public String verificaExcecao(Runnable runnable){
        try{
            runnable.run();
        } catch (IllegalArgumentException e){
            return e.getMessage();
        }
        return null;
    }

    @BeforeEach
    void criarController(){
        controllerProblema = new ControllerProblema();
    }

    @Test
    void cadastraProblemaInvalido() {
        assertEquals("Campo descricao nao pode ser nulo ou vazio.",
                verificaExcecao(() -> controllerProblema.cadastraProblema("",5)));
        assertEquals("Valor invalido de viabilidade.",
                verificaExcecao(() -> controllerProblema.cadastraProblema("A dificuldade de gerar dados precisos em experimentos sobre dinamica em gravidade 0",
                        -10)));
        assertEquals("Valor invalido de viabilidade.",
                verificaExcecao(() -> controllerProblema.cadastraProblema("A dificuldade de gerar dados precisos em experimentos sobre dinamica em gravidade 0",
                        0)));
        assertEquals("Valor invalido de viabilidade.",
                verificaExcecao(() -> controllerProblema.cadastraProblema("A dificuldade de gerar dados precisos em experimentos sobre dinamica em gravidade 0",
                        1234321124)));
    }

    @Test
    void cadastraProblema() {
        controllerProblema.cadastraProblema("A dificuldade de gerar dados precisos em experimentos sobre dinamica em gravidade 0",4);
    }

    @Test
    void apagarProblemaInvalido() {
        assertEquals("Campo codigo nao pode ser nulo ou vazio.", verificaExcecao(() -> controllerProblema.apagarProblema("")));
        assertEquals("Problema nao encontrado", verificaExcecao(() -> controllerProblema.apagarProblema("Ṕ66666")));
    }

    @Test
    void apagarProblema() {
        cadastraProblema();
        controllerProblema.apagarProblema("P1");
    }

    @Test
    void exibeProblemaInvalido() {
        assertEquals("Campo codigo nao pode ser nulo ou vazio.", verificaExcecao(() -> controllerProblema.exibeProblema("")));
        assertEquals("Problema nao encontrado", verificaExcecao(() -> controllerProblema.exibeProblema("P101")));
    }

    @Test
    void exibeProblema() {
        cadastraProblema();
        assertEquals("P1 - A dificuldade de gerar dados precisos em experimentos sobre dinamica em gravidade 0 - 4",
                controllerProblema.exibeProblema("P1"));
    }
}