package com.tests;

import com.psquiza.controllers.ControllerPesquisador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerPesquisadorTest {

    private ControllerPesquisador controllerPesquisador;

    private String verificaExcecao(Runnable runnable){
        try{
            runnable.run();
        } catch (RuntimeException e){
            return e.getMessage();
        }
        return null;
    }

    @BeforeEach
    void criaController(){
        controllerPesquisador = new ControllerPesquisador();
    }

    @Test
    void cadastraPesquisadorInvalido() {
        assertEquals("Campo fotoURL nao pode ser nulo ou vazio.", verificaExcecao(()  -> controllerPesquisador.cadastraPesquisador("killua zoldyck", "estudante",
                        "Interessado em eletricidade, o terceiro de cinco filhos da famosa familia Zaoldyeck.", "hunterxhunter@1998","")));
        assertEquals("Campo nome nao pode ser nulo ou vazio.", verificaExcecao(()  -> controllerPesquisador.cadastraPesquisador(null, "estudante",
                        "Interessado em eletricidade, o terceiro de cinco filhos da famosa familia Zaoldyeck.", "hunterxhunter@1998","https://godspeed")));
        assertEquals("Formato de foto invalido.", verificaExcecao(()  -> controllerPesquisador.cadastraPesquisador("killua zoldyck", "estudante",
                "Interessado em eletricidade, o terceiro de cinco filhos da famosa familia Zaoldyeck.", "hunterxhunter@1998","sem o necessario")));
        assertEquals("Formato de foto invalido.", verificaExcecao(()  -> controllerPesquisador.cadastraPesquisador("killua zoldyck", "estudante",
                "Interessado em eletricidade, o terceiro de cinco filhos da famosa familia Zaoldyeck.", "hunterxhunter@1998","https")));
        assertEquals("Formato de email invalido.", verificaExcecao(()  -> controllerPesquisador.cadastraPesquisador("killua zoldyck", "estudante",
                "Interessado em eletricidade, o terceiro de cinco filhos da famosa familia Zaoldyeck.", "testeteste","https://godspeed")));
        assertEquals("Formato de email invalido.", verificaExcecao(()  -> controllerPesquisador.cadastraPesquisador("killua zoldyck", "estudante",
                "Interessado em eletricidade, o terceiro de cinco filhos da famosa familia Zaoldyeck.", "teste@","https://godspeed")));
    }

    @Test
    void cadastraPesquisador() {
        controllerPesquisador.cadastraPesquisador("killua zoldyck", "estudante",
                "Interessado em eletricidade, o terceiro de cinco filhos da famosa familia Zaoldyeck.", "hunterxhunter@1998","https://godspeed");
    }

    @Test
    void alteraPesquisadorInvalido() {
        assertEquals("Atributo nao pode ser vazio ou nulo.",
                verificaExcecao(() -> controllerPesquisador.alteraPesquisador("email@email","","")));
        assertEquals("Campo email nao pode ser nulo ou vazio.",
                verificaExcecao(() -> controllerPesquisador.alteraPesquisador(null,"","")));
        assertEquals("Pesquisador nao encontrado",
                verificaExcecao(() -> controllerPesquisador.alteraPesquisador("hunterxhunter@1998","NOME","gon")));
        assertEquals("Pesquisador nao encontrado",
                verificaExcecao(() -> controllerPesquisador.alteraPesquisador("hunterxhunter@1998","NOME","gon")));

        cadastraPesquisador();
        controllerPesquisador.desativaPesquisador("hunterxhunter@1998");
        assertEquals("Pesquisador inativo.", verificaExcecao(() -> controllerPesquisador.alteraPesquisador("hunterxhunter@1998","NOME","gon")));

        controllerPesquisador.ativaPesquisador("hunterxhunter@1998");
        assertEquals("Campo nome nao pode ser nulo ou vazio.", verificaExcecao(() -> controllerPesquisador.alteraPesquisador("hunterxhunter@1998","NOME","")));
        assertEquals("Campo funcao nao pode ser nulo ou vazio.", verificaExcecao(() -> controllerPesquisador.alteraPesquisador("hunterxhunter@1998","FUNCAO",null)));
        assertEquals("Campo biografia nao pode ser nulo ou vazio.", verificaExcecao(() -> controllerPesquisador.alteraPesquisador("hunterxhunter@1998","BIOGRAFIA","")));
        assertEquals("Campo email nao pode ser nulo ou vazio.", verificaExcecao(() -> controllerPesquisador.alteraPesquisador("hunterxhunter@1998","EMAIL","")));
        assertEquals("Formato de email invalido.", verificaExcecao(() -> controllerPesquisador.alteraPesquisador("hunterxhunter@1998","EMAIL","teste")));
        assertEquals("Formato de email invalido.", verificaExcecao(() -> controllerPesquisador.alteraPesquisador("hunterxhunter@1998","EMAIL","teste@")));
        assertEquals("Campo fotoURL nao pode ser nulo ou vazio.", verificaExcecao(() -> controllerPesquisador.alteraPesquisador("hunterxhunter@1998","FOTO","")));
        assertEquals("Formato de foto invalido.", verificaExcecao(() -> controllerPesquisador.alteraPesquisador("hunterxhunter@1998","FOTO","teste")));
        assertEquals("Formato de foto invalido.", verificaExcecao(() -> controllerPesquisador.alteraPesquisador("hunterxhunter@1998","FOTO","http")));
        assertEquals("Formato de foto invalido.", verificaExcecao(() -> controllerPesquisador.alteraPesquisador("hunterxhunter@1998","FOTO","https")));
        assertEquals("Atributo invalido.", verificaExcecao(() -> controllerPesquisador.alteraPesquisador("hunterxhunter@1998","ENDERECO","https")));
    }

    @Test
    void alteraPesquisador() {
        cadastraPesquisador();
        controllerPesquisador.alteraPesquisador("hunterxhunter@1998","NOME","GON");
        // verificar exibição
    }

    @Test
    void pesquisadorEhAtivoInvalido() {
        assertEquals("Email nao pode ser vazio ou nulo.", verificaExcecao(() -> controllerPesquisador.pesquisadorEhAtivo("")));
        assertEquals("Email nao pode ser vazio ou nulo.", verificaExcecao(() -> controllerPesquisador.pesquisadorEhAtivo(null)));
        assertEquals("Pesquisador nao encontrado", verificaExcecao(() -> controllerPesquisador.pesquisadorEhAtivo("email@email")));
    }

    @Test
    void pesquisadorEhAtivo() {
        cadastraPesquisador();
        assertTrue(controllerPesquisador.pesquisadorEhAtivo("hunterxhunter@1998"));
    }

    @Test
    void desativaPesquisadorInvalido() {
        assertEquals("Campo email nao pode ser nulo ou vazio.", verificaExcecao(() -> controllerPesquisador.desativaPesquisador("")));
        assertEquals("Campo email nao pode ser nulo ou vazio.", verificaExcecao(() -> controllerPesquisador.desativaPesquisador(null)));
        assertEquals("Pesquisador nao encontrado", verificaExcecao(() -> controllerPesquisador.desativaPesquisador("email@email")));
        cadastraPesquisador();
        controllerPesquisador.desativaPesquisador("hunterxhunter@1998");
        assertEquals("Pesquisador inativo.",verificaExcecao(() -> controllerPesquisador.desativaPesquisador("hunterxhunter@1998")));
    }

    @Test
    void desativaPesquisador() {
        cadastraPesquisador();
        controllerPesquisador.desativaPesquisador("hunterxhunter@1998");
        assertFalse(controllerPesquisador.pesquisadorEhAtivo("hunterxhunter@1998"));
    }

    @Test
    void ativaPesquisadorInvalido() {
        assertEquals("Campo email nao pode ser nulo ou vazio.", verificaExcecao(() -> controllerPesquisador.ativaPesquisador("")));
        assertEquals("Campo email nao pode ser nulo ou vazio.", verificaExcecao(() -> controllerPesquisador.ativaPesquisador(null)));
        assertEquals("Pesquisador nao encontrado", verificaExcecao(() -> controllerPesquisador.ativaPesquisador("email@email")));
        cadastraPesquisador();
        assertEquals("Pesquisador ja ativado.", verificaExcecao(() -> controllerPesquisador.ativaPesquisador("hunterxhunter@1998")));
    }

    @Test
    void ativaPesquisador() {
        cadastraPesquisador();
        controllerPesquisador.desativaPesquisador("hunterxhunter@1998");
        controllerPesquisador.ativaPesquisador("hunterxhunter@1998");
        assertTrue(controllerPesquisador.pesquisadorEhAtivo("hunterxhunter@1998"));
    }

    @Test
    void exibePesquisadorInvalido() {
        assertEquals("Campo email nao pode ser nulo ou vazio.", verificaExcecao(() -> controllerPesquisador.exibePesquisador("")));
        assertEquals("Formato de email invalido.", verificaExcecao(() -> controllerPesquisador.exibePesquisador("hunter")));
        assertEquals("Formato de email invalido.", verificaExcecao(() -> controllerPesquisador.exibePesquisador("hunterxhunter@")));
        assertEquals("Formato de email invalido.", verificaExcecao(() -> controllerPesquisador.exibePesquisador("@1998")));
        assertEquals("Pesquisador nao encontrado", verificaExcecao(() -> controllerPesquisador.exibePesquisador("email@email")));
        cadastraPesquisador();
        controllerPesquisador.desativaPesquisador("hunterxhunter@1998");
        assertEquals("Pesquisador inativo.", verificaExcecao(() -> controllerPesquisador.exibePesquisador("hunterxhunter@1998")));
    }

    @Test
    void exibePesquisador() {
        cadastraPesquisador();
        String exibicao = "killua zoldyck (estudante) - Interessado em eletricidade, o terceiro de cinco filhos da famosa familia Zaoldyeck. - hunterxhunter@1998 - https://godspeed";
        assertEquals(exibicao, controllerPesquisador.exibePesquisador("hunterxhunter@1998"));
    }
}