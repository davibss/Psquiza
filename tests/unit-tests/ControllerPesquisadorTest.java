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

    // Testes do caso de uso 2

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
        controllerPesquisador.cadastraPesquisador("killua zoldyck", "estudante", "Interessado em eletricidade, o terceiro de cinco filhos da famosa familia Zaoldyeck.",
                "hunterxhunter@1998","https://godspeed");
        controllerPesquisador.cadastraPesquisador("heisenberg", "professor", "Interessado nos efeitos da metafetamina e no estudo sobre o cancer. Pesquisador principal da pesquisa de radigrafia a fotons, peca fundamental na pesquisa que ganhou um premio nobel.",
                "breakingbad@2008","https://iamthedanger");
        controllerPesquisador.cadastraPesquisador("Prairie Johnson", "externo", "Interessada no estudo de multiplas dimensoes e no estudo dos sentidos humanos.",
                "theoa@2016","https://notblind");
        controllerPesquisador.cadastraPesquisador("Joel","externo","Interessado em fungos.","thelastofus@2013","https://Cordyceps");
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

    // Testes do caso de uso 6

    @Test
    void cadastraEspecialidadeProfessorInvalido(){
        assertEquals("Campo email nao pode ser nulo ou vazio.", verificaExcecao(() ->
                controllerPesquisador.cadastraEspecialidadeProfessor("", "Doutorado", "DSC", "01/01/2011")));
        assertEquals("Campo formacao nao pode ser nulo ou vazio.", verificaExcecao(() ->
                controllerPesquisador.cadastraEspecialidadeProfessor("email@email", null, "DSC", "01/01/2011")));
        assertEquals("Campo unidade nao pode ser nulo ou vazio.", verificaExcecao(() ->
                controllerPesquisador.cadastraEspecialidadeProfessor("email@email", "Doutorado", "", "01/01/2011")));
        assertEquals("Campo data nao pode ser nulo ou vazio.", verificaExcecao(() ->
                controllerPesquisador.cadastraEspecialidadeProfessor("email@email", "Doutorado", "DSC", null)));
        assertEquals("Formato de email invalido.", verificaExcecao(() ->
                controllerPesquisador.cadastraEspecialidadeProfessor("email@", "Doutorado", "DSC", "01/05/2015")));
        assertEquals("Pesquisadora nao encontrada.", verificaExcecao(() ->
                controllerPesquisador.cadastraEspecialidadeProfessor("email1@email", "Doutorado", "DSC", "01/05/2015")));
        cadastraPesquisador();
        assertEquals("Pesquisador nao compativel com a especialidade.",
                verificaExcecao(() -> controllerPesquisador.cadastraEspecialidadeProfessor("hunterxhunter@1998","Doutorado", "DSC", "01/05/2015")));
        assertEquals("Atributo data com formato invalido.",
                verificaExcecao(() -> controllerPesquisador.cadastraEspecialidadeProfessor("breakingbad@2008","Doutorado", "DSC", "01052015")));
        assertEquals("Atributo data com formato invalido.",
                verificaExcecao(() -> controllerPesquisador.cadastraEspecialidadeProfessor("breakingbad@2008","Doutorado", "DSC", "2015/05/15")));
        assertEquals("Atributo data com formato invalido.",
                verificaExcecao(() -> controllerPesquisador.cadastraEspecialidadeProfessor("breakingbad@2008","Doutorado", "DSC", "1/5/2015")));
    }

    @Test
    void cadastraEspecialidadeProfessor(){
        cadastraPesquisador();
        controllerPesquisador.cadastraEspecialidadeProfessor("breakingbad@2008","Doutorado", "DSC", "01/05/2015");
    }

    @Test
    void cadastraEspecialidadeAlunoInvalido(){
        assertEquals("Campo email nao pode ser nulo ou vazio.", verificaExcecao(() ->
                controllerPesquisador.cadastraEspecialidadeAluno("", 2, 3.0)));
        assertEquals("Atributo semestre com formato invalido.", verificaExcecao(() ->
                controllerPesquisador.cadastraEspecialidadeAluno("email@email", -1, 3.0)));
        assertEquals("Atributo IEA com formato invalido.", verificaExcecao(() ->
                controllerPesquisador.cadastraEspecialidadeAluno("email@email", 3, -3.0)));
        assertEquals("Atributo IEA com formato invalido.", verificaExcecao(() ->
                controllerPesquisador.cadastraEspecialidadeAluno("email@email", 11, 13.0)));
        assertEquals("Formato de email invalido.", verificaExcecao(() ->
                controllerPesquisador.cadastraEspecialidadeAluno("email@", 2, 3.0)));
        assertEquals("Pesquisadora nao encontrada.", verificaExcecao(() ->
                controllerPesquisador.cadastraEspecialidadeAluno("email@email", 2, 3.0)));
        cadastraPesquisador();
        assertEquals("Pesquisador nao compativel com a especialidade.",
                verificaExcecao(() -> controllerPesquisador.cadastraEspecialidadeAluno("breakingbad@2008",2,3.0)));
    }

    @Test
    void cadastraEspecialidadeAluno(){
        cadastraPesquisador();
        controllerPesquisador.cadastraEspecialidadeAluno("hunterxhunter@1998",2,3.0);
    }

    @Test
    void listaPesquisadoresInvalido(){
        assertEquals("Campo tipo nao pode ser nulo ou vazio.",
                verificaExcecao(() -> controllerPesquisador.listaPesquisadores("")));
        assertEquals("Tipo 123test inexistente.",
                verificaExcecao(() -> controllerPesquisador.listaPesquisadores("123test")));
    }

    @Test
    void listaPesquisadores(){
        cadastraEspecialidadeAluno();
        assertEquals("Prairie Johnson (externo) - Interessada no estudo de multiplas dimensoes e no estudo dos sentidos humanos. - theoa@2016 - https://notblind | " +
                "Joel (externo) - Interessado em fungos. - thelastofus@2013 - https://Cordyceps", controllerPesquisador.listaPesquisadores("EXTERNO"));
        assertEquals("killua zoldyck (estudante) - Interessado em eletricidade, o terceiro de cinco filhos da famosa familia Zaoldyeck. - hunterxhunter@1998 - " +
                "https://godspeed - 2o SEMESTRE - 3.0", controllerPesquisador.listaPesquisadores("ALUNA"));
        assertEquals("heisenberg (professor) - Interessado nos efeitos da metafetamina e no estudo sobre o cancer. Pesquisador principal da pesquisa de radigrafia a fotons, " +
                "peca fundamental na pesquisa que ganhou um premio nobel. - breakingbad@2008 - https://iamthedanger", controllerPesquisador.listaPesquisadores("PROFESSORA"));
    }

    @Test
    void exibePesquisadorProfessor(){
        cadastraEspecialidadeProfessor();
        assertEquals("heisenberg (professor) - Interessado nos efeitos da metafetamina e no estudo sobre o cancer. " +
                "Pesquisador principal da pesquisa de radigrafia a fotons, peca fundamental na pesquisa que ganhou um premio nobel. - " +
                "breakingbad@2008 - https://iamthedanger - Doutorado - DSC - 01/05/2015", controllerPesquisador.exibePesquisador("breakingbad@2008"));
    }

    @Test
    void exibePesquisadorAluno(){
        cadastraEspecialidadeAluno();
        assertEquals("killua zoldyck (estudante) - Interessado em eletricidade, o terceiro de cinco filhos da famosa familia Zaoldyeck. - " +
                "hunterxhunter@1998 - https://godspeed - 2o SEMESTRE - 3.0", controllerPesquisador.exibePesquisador("hunterxhunter@1998"));
    }

    @Test
    void alteraProfessorInvalido(){
        cadastraEspecialidadeProfessor();
        assertEquals("Atributo nao pertencem a esta especialidade.",
                verificaExcecao(() -> controllerPesquisador.alteraPesquisador("breakingbad@2008", "SEMESTRE", "3")));
        assertEquals("Atributo nao pertencem a esta especialidade.",
                verificaExcecao(() -> controllerPesquisador.alteraPesquisador("breakingbad@2008", "IEA", "6.0")));
        assertEquals("Atributo data com formato invalido.",
                verificaExcecao(() -> controllerPesquisador.alteraPesquisador("breakingbad@2008", "DATA", "12/23/2324")));
        controllerPesquisador.cadastraPesquisador("All Might", "professor", "Simbolo da paz.",
                "allmight@email","https://plusultra");
        assertEquals("Pesquisador nao possui especialidade cadastrada para se modificar.",
                verificaExcecao(() -> controllerPesquisador.alteraPesquisador("allmight@email", "FORMACAO", "DOUTORADO")));
    }

    @Test
    void alteraProfessor(){
        cadastraEspecialidadeProfessor();
        controllerPesquisador.alteraPesquisador("breakingbad@2008", "DATA", "29/05/2015");
        controllerPesquisador.alteraPesquisador("breakingbad@2008", "FORMACAO", "POSDOC");
        controllerPesquisador.alteraPesquisador("breakingbad@2008", "UNIDADE", "LSD");
        assertEquals("heisenberg (professor) - Interessado nos efeitos da metafetamina e no estudo sobre o cancer. " +
                "Pesquisador principal da pesquisa de radigrafia a fotons, peca fundamental na pesquisa que ganhou um premio nobel. - " +
                "breakingbad@2008 - https://iamthedanger - POSDOC - LSD - 29/05/2015", controllerPesquisador.exibePesquisador("breakingbad@2008"));
    }

    @Test
    void alteraAlunoInvalido(){
        cadastraEspecialidadeAluno();
        assertEquals("Atributo nao pertencem a esta especialidade.",
                verificaExcecao(() -> controllerPesquisador.alteraPesquisador("hunterxhunter@1998", "FORMACAO", "DOUTORADO")));
        assertEquals("Atributo nao pertencem a esta especialidade.",
                verificaExcecao(() -> controllerPesquisador.alteraPesquisador("hunterxhunter@1998", "DATA", "05/08/2011")));
        assertEquals("Atributo nao pertencem a esta especialidade.",
                verificaExcecao(() -> controllerPesquisador.alteraPesquisador("hunterxhunter@1998", "UNIDADE", "LSD")));
        assertEquals("Atributo semestre com formato invalido.",
                verificaExcecao(() -> controllerPesquisador.alteraPesquisador("hunterxhunter@1998", "SEMESTRE", "-1")));
        assertEquals("Atributo IEA com formato invalido.",
                verificaExcecao(() -> controllerPesquisador.alteraPesquisador("hunterxhunter@1998", "IEA", "-1")));
        assertEquals("Atributo IEA com formato invalido.",
                verificaExcecao(() -> controllerPesquisador.alteraPesquisador("hunterxhunter@1998", "IEA", "11")));
        controllerPesquisador.cadastraPesquisador("Roronoa Zoro","estudante","Ser o melhor espadachim do mundo.", "zoro@email", "https://zoro.png");
        assertEquals("Pesquisador nao possui especialidade cadastrada para se modificar.",
                verificaExcecao(() -> controllerPesquisador.alteraPesquisador("zoro@email", "SEMESTRE", "3")));
    }

    @Test
    void alteraAluno(){
        cadastraEspecialidadeAluno();
        controllerPesquisador.alteraPesquisador("hunterxhunter@1998", "SEMESTRE", "3");
        controllerPesquisador.alteraPesquisador("hunterxhunter@1998", "IEA", "9.5");
        assertEquals("killua zoldyck (estudante) - Interessado em eletricidade, o terceiro de cinco filhos da famosa familia Zaoldyeck. - " +
                "hunterxhunter@1998 - https://godspeed - 3o SEMESTRE - 9.5", controllerPesquisador.exibePesquisador("hunterxhunter@1998"));
    }
}