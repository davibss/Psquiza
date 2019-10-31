package com.tests;

import com.psquiza.controllers.ControllerObjetivo;
import com.psquiza.controllers.ControllerProblema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerObjetivoTest {

    private ControllerObjetivo controllerObjetivo;

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
        controllerObjetivo = new ControllerObjetivo();
    }

    @Test
    void cadastraObjetivoInvalido() {
        assertEquals("Campo tipo nao pode ser nulo ou vazio.",
                verificaExcecao(() -> controllerObjetivo.cadastraObjetivo("","Aumentar a porcentagem de pessoas que entregam os laboratorios na disciplina de LP2.", 1,5)));
        assertEquals("Valor invalido de tipo.",
                verificaExcecao(() -> controllerObjetivo.cadastraObjetivo("123","Aumentar a porcentagem de pessoas que entregam os laboratorios na disciplina de LP2.", 1,5)));
        assertEquals("Valor invalido de aderencia",
                verificaExcecao(() -> controllerObjetivo.cadastraObjetivo("GERAL","Aumentar a porcentagem de pessoas que entregam os laboratorios na disciplina de LP2.", -1,5)));
        assertEquals("Valor invalido de aderencia",
                verificaExcecao(() -> controllerObjetivo.cadastraObjetivo("GERAL","Aumentar a porcentagem de pessoas que entregam os laboratorios na disciplina de LP2.", 0,5)));
        assertEquals("Valor invalido de aderencia",
                verificaExcecao(() -> controllerObjetivo.cadastraObjetivo("GERAL","Aumentar a porcentagem de pessoas que entregam os laboratorios na disciplina de LP2.", 6,5)));
        assertEquals("Valor invalido de viabilidade.",
                verificaExcecao(() -> controllerObjetivo.cadastraObjetivo("GERAL","Aumentar a porcentagem de pessoas que entregam os laboratorios na disciplina de LP2.", 4,-5)));
        assertEquals("Valor invalido de viabilidade.",
                verificaExcecao(() -> controllerObjetivo.cadastraObjetivo("GERAL","Aumentar a porcentagem de pessoas que entregam os laboratorios na disciplina de LP2.", 4,0)));
        assertEquals("Valor invalido de viabilidade.",
                verificaExcecao(() -> controllerObjetivo.cadastraObjetivo("GERAL","Aumentar a porcentagem de pessoas que entregam os laboratorios na disciplina de LP2.", 4,6)));
    }

    @Test
    void cadastraObjetivo() {
        controllerObjetivo.cadastraObjetivo("GERAL","Objetivo criado para ser deletado", 4,4);
    }

    @Test
    void apagarObjetivoInvalido() {
        assertEquals("Campo codigo nao pode ser nulo ou vazio.",
                verificaExcecao(() -> controllerObjetivo.apagarObjetivo("")));
        assertEquals("Objetivo nao encontrado",
                verificaExcecao(() -> controllerObjetivo.apagarObjetivo("O1")));
    }

    @Test
    void apagarObjetivo() {
    }

    @Test
    void exibeObjetivo() {
    }
}