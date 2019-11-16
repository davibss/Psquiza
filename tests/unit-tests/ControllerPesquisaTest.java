package com.tests;

import com.psquiza.controllers.ControllerAtividade;
import com.psquiza.controllers.ControllerPesquisa;
import com.psquiza.entidades.Atividade;
import com.psquiza.entidades.Pesquisador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerPesquisaTest {

    private ControllerPesquisa controllerPesquisa;
    private ControllerAtividade controllerAtividade;

    private String verificaExcecao(Runnable runnable){
        try{
            runnable.run();
        } catch (RuntimeException e){
            return e.getMessage();
        }
        return null;
    }

    @BeforeEach
    void criarController(){
        controllerPesquisa = new ControllerPesquisa();
        controllerAtividade = new ControllerAtividade();
        controllerAtividade.cadastrarAtividades("Monitoramento de chats dos alunos de computacao do primeiro periodo.",
                                                "ALTO","Por se tratar de apenas um monitoramento, o risco nao e elevado.");
        controllerAtividade.cadastrarAtividades("Degustacao de uma nova remeca de cervejas, criadas a partir de um novo processo de fermentacao.",
                                                "MEDIO", "Degustadores podem sofrer com problemas de saude nessa atividade, tal como ser alergico a algum ingrediente da cerveja.");
    }

    // Testes para o caso de uso 1

    @Test
    void cadastrarPesquisaInvalido() {
        assertEquals("Descricao nao pode ser nula ou vazia.",
                verificaExcecao(() -> controllerPesquisa.cadastrarPesquisa("","computacao, homofobia")));
        assertEquals("Formato do campo de interesse invalido.", verificaExcecao(() -> controllerPesquisa.cadastrarPesquisa("Homofobia em mensagens online de alunos de computacao do primeiro periodo.", "")));
        assertEquals("Formato do campo de interesse invalido.", verificaExcecao(() -> controllerPesquisa.cadastrarPesquisa("Homofobia em mensagens online de alunos de computacao do primeiro periodo.",
                "Kotae wa doko e? Sagashite\n" +
                        "Where's my soul?\n" +
                        "It's like a fear warui yume no you de\n" +
                        "Mezametemo all I've got is bones\n" +
                        "I'm in a panic? Get out, hurry\n" +
                        "Oh, there are mysteries and miseries\n" +
                        "Dead or alive hazama ni yurarete\n" +
                        "Samayoi right to left to hell\n" +
                        "Yami no shoutai emo no shoutai\n" +
                        "Odoru one, two, three steps on this dark stage")));
        assertEquals("Formato do campo de interesse invalido.", verificaExcecao(() -> controllerPesquisa.cadastrarPesquisa("Homofobia em mensagens online de alunos de computacao do primeiro periodo.",
                "computacao, , ,eletrica")));
        assertEquals("Formato do campo de interesse invalido.", verificaExcecao(() -> controllerPesquisa.cadastrarPesquisa("Homofobia em mensagens online de alunos de computacao do primeiro periodo.",
                "computacao, medicina, direito, seguranca do trabalho, arquitetura")));
        assertEquals("Formato do campo de interesse invalido.", verificaExcecao(() -> controllerPesquisa.cadastrarPesquisa("Homofobia em mensagens online de alunos de computacao do primeiro periodo.",
                "co")));
    }

    @Test
    void cadastrarPesquisa() {
        assertEquals("AST1", controllerPesquisa.cadastrarPesquisa("Identificacao de buracos negros com uso de programacao", "astronomia, computacao"));
        assertEquals("PSI1", controllerPesquisa.cadastrarPesquisa("Alienacao Parental e o Sistema de Justica Brasileiro.", "psicologia, sistema juridico, alienacao parental, brasil"));
    }

    @Test
    void alterarPesquisaInvalido() {
        assertEquals("Pesquisa nao encontrada.", verificaExcecao(() -> controllerPesquisa.alterarPesquisa("ELE1",
                "DESCRICAO","123456")));
        assertEquals("Descricao nao pode ser nula ou vazia.", verificaExcecao(() -> controllerPesquisa.alterarPesquisa("ELE1",
                "DESCRICAO","")));
        assertEquals("Formato do campo de interesse invalido.", verificaExcecao(() -> controllerPesquisa.alterarPesquisa("ELE1",
                "CAMPO","")));
        cadastrarPesquisa();
        controllerPesquisa.encerrarPesquisa("AST1", "pesquisa concluída");
        assertEquals("Pesquisa desativada.", verificaExcecao(() -> controllerPesquisa.alterarPesquisa("AST1",
                "CAMPO","pesquisa sobre galaxias")));
    }

    @Test
    void alterarPesquisa() {
        cadastrarPesquisa();
        controllerPesquisa.alterarPesquisa("AST1", "DESCRICAO","pesquisa sobre galaxias");
        controllerPesquisa.alterarPesquisa("AST1", "CAMPO","astronomia");
        assertEquals("AST1 - pesquisa sobre galaxias - astronomia", controllerPesquisa.exibirPesquisa("AST1"));
    }

    @Test
    void encerrarPesquisaInvalido() {
        assertEquals("Motivo nao pode ser nulo ou vazio.", verificaExcecao(() -> controllerPesquisa.encerrarPesquisa("AST1","")));
        assertEquals("Pesquisa nao encontrada.", verificaExcecao(() -> controllerPesquisa.encerrarPesquisa("AST1","pesquisa concluída")));
        cadastrarPesquisa();
        controllerPesquisa.encerrarPesquisa("AST1", "pesquisa concluida");
        assertEquals("Pesquisa desativada.", verificaExcecao(() -> controllerPesquisa.encerrarPesquisa("AST1", "pesquisa concluida")));
    }

    @Test
    void encerrarPesquisa() {
        cadastrarPesquisa();
        controllerPesquisa.encerrarPesquisa("AST1", "pesquisa concluida");
    }

    @Test
    void ativarPesquisaInvalido() {
        assertEquals("Codigo nao pode ser nulo ou vazio.", verificaExcecao(() -> controllerPesquisa.ativarPesquisa("")));
        assertEquals("Pesquisa nao encontrada.", verificaExcecao(() -> controllerPesquisa.ativarPesquisa("COM1")));
        cadastrarPesquisa();
        assertEquals("Pesquisa ja ativada.", verificaExcecao(() -> controllerPesquisa.ativarPesquisa("AST1")));
    }

    @Test
    void ativarPesquisa() {
        cadastrarPesquisa();
        controllerPesquisa.encerrarPesquisa("AST1","pesquisa concluida");
        controllerPesquisa.ativarPesquisa("AST1");
    }

    @Test
    void exibirPesquisaInvalido() {
        assertEquals("Codigo nao pode ser nulo ou vazio.", verificaExcecao(() -> controllerPesquisa.exibirPesquisa("")));
        assertEquals("Pesquisa nao encontrada.", verificaExcecao(() -> controllerPesquisa.exibirPesquisa("COM1")));
    }

    @Test
    void exibirPesquisa() {
        cadastrarPesquisa();
        assertEquals("AST1 - Identificacao de buracos negros com uso de programacao - astronomia, computacao", controllerPesquisa.exibirPesquisa("AST1"));
    }

    @Test
    void pesquisAtivaInvalido() {
        assertEquals("Codigo nao pode ser nulo ou vazio.", verificaExcecao(() -> controllerPesquisa.pesquisAtiva("")));
        assertEquals("Pesquisa nao encontrada.", verificaExcecao(() -> controllerPesquisa.pesquisAtiva("COM1")));
    }

    @Test
    void pesquisAtiva() {
        cadastrarPesquisa();
        assertTrue(controllerPesquisa.pesquisAtiva("AST1"));
        controllerPesquisa.encerrarPesquisa("AST1","pesquisa concluida");
        assertFalse(controllerPesquisa.pesquisAtiva("AST1"));
    }

    // Testes para o caso de uso 6 (DAVI)

    @Test
    void associaPesquisadorInvalido(){
        assertEquals("Pesquisa nao encontrada.", verificaExcecao(() ->
                controllerPesquisa.associaPesquisador("PSI1","email@email",
                        new Pesquisador("Teste","externo","123","email@email","http://foto"))));
        encerrarPesquisa();
        assertEquals("Pesquisa desativada.",
                verificaExcecao(() -> controllerPesquisa.associaPesquisador("AST1","email@email",
                        new Pesquisador("Teste","externo","123","email@email","http://foto"))));
    }

    @Test
    void associaPesquisador(){
        cadastrarPesquisa();
        assertTrue(controllerPesquisa.associaPesquisador("AST1","email@email",
                new Pesquisador("Teste","externo","123","email@email","http://foto")));
        assertFalse(controllerPesquisa.associaPesquisador("AST1","email@email",
                new Pesquisador("Teste","externo","123","email@email","http://foto")));
        assertTrue(controllerPesquisa.associaPesquisador("AST1","maria@email",
                new Pesquisador("Maria","estudante","estuda","maria@email","http://foto3x4")));
    }

    @Test
    void desassociaPesquisadoresInvalido(){
        assertEquals("Pesquisa nao encontrada.", verificaExcecao(() ->
                controllerPesquisa.desassociaPesquisadores("PSI1","email@email")));
        encerrarPesquisa();
        assertEquals("Pesquisa desativada.",
                verificaExcecao(() -> controllerPesquisa.desassociaPesquisadores("AST1","email@email")));
    }

    @Test
    void desassociaPesquisadores(){
        associaPesquisador();
        assertTrue(controllerPesquisa.desassociaPesquisadores("AST1","email@email"));
        assertFalse(controllerPesquisa.desassociaPesquisadores("AST1","email@email"));
    }

    // TESTES PARA O CASO DE USO 5 (HENRIQUE)

    @Test
    void associaProblemaInvalido(){
        cadastrarPesquisa();

    }

    @Test
    void associaProblema(){
        cadastrarPesquisa();
    }

    @Test
    void desassociaProblemaInvalido(){

    }

    @Test
    void desassociaProblema(){

    }

    @Test
    void associaObjetivoInvalido(){

    }

    @Test
    void associaObjetivo(){

    }

    @Test
    void desassociaObjetivoInvalido(){

    }

    @Test
    void desassociaObjetivo(){

    }

    // TESTES PARA O CASO DE USO 7 (DAVI - AJUDANDO)
    @Test
    void associaAtividadeInvalido(){
        assertEquals("Campo codigoPesquisa nao pode ser nulo ou vazio.",
                verificaExcecao(() -> controllerPesquisa.associaAtividade("","A1",
                        controllerAtividade.getAtividade("A1"))));
        assertEquals("Campo codigoAtividade nao pode ser nulo ou vazio.",
                verificaExcecao(() -> controllerPesquisa.associaAtividade("COM1","",
                        controllerAtividade.getAtividade("A1"))));
        assertEquals("Pesquisa nao encontrada.",
                verificaExcecao(() -> controllerPesquisa.associaAtividade("COM5","A1",
                        controllerAtividade.getAtividade("A1"))));
        encerrarPesquisa();
        assertEquals("Pesquisa desativada.",
                verificaExcecao(() -> controllerPesquisa.associaAtividade("AST1","A1",
                        controllerAtividade.getAtividade("A1"))));
    }

    @Test
    void associaAtividade(){
        cadastrarPesquisa();
        assertTrue(controllerPesquisa.associaAtividade
                ("AST1", "A1", controllerAtividade.getAtividade("A1")));
        assertFalse(controllerPesquisa.associaAtividade
                ("AST1", "A1", controllerAtividade.getAtividade("A1")));
    }

    @Test
    void desassociaAtividadeInvalido(){
        assertEquals("Campo codigoPesquisa nao pode ser nulo ou vazio.",
                verificaExcecao(() -> controllerPesquisa.desassociaAtividade("","A1")));
        assertEquals("Campo codigoAtividade nao pode ser nulo ou vazio.",
                verificaExcecao(() -> controllerPesquisa.desassociaAtividade("COM1","")));
        assertEquals("Pesquisa nao encontrada.",
                verificaExcecao(() -> controllerPesquisa.desassociaAtividade("COM5","A1")));
        encerrarPesquisa();
        assertEquals("Pesquisa desativada.",
                verificaExcecao(() -> controllerPesquisa.desassociaAtividade("AST1","A1")));
    }

    @Test
    void desassociaAtividade(){
        associaAtividade();
        assertTrue(controllerPesquisa.desassociaAtividade("AST1", "A1"));
        assertFalse(controllerPesquisa.desassociaAtividade("AST1", "A1"));
    }
}