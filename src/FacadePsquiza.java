package com.psquiza;

import com.psquiza.controllers.ControllerGeral;
import easyaccept.EasyAccept;
import easyaccept.EasyAcceptException;

import java.io.IOException;

public class FacadePsquiza {

    private ControllerGeral controllerGeral;

    public static void main(String[] args) {
        args = new String[]{"com.psquiza.FacadePsquiza", "tests/accept-tests/use_case_1.txt",
                                                         "tests/accept-tests/use_case_2.txt",
                                                         "tests/accept-tests/use_case_3.txt",
                                                         "tests/accept-tests/use_case_4.txt",
                                                         "tests/accept-tests/use_case_5.txt",
                                                         "tests/accept-tests/use_case_6.txt",
                                                         "tests/accept-tests/use_case_7.txt",
                                                         "tests/accept-tests/use_case_8.txt",
                                                         "tests/accept-tests/use_case_9.txt",
                                                         "tests/accept-tests/use_case_10.txt",
                                                         "tests/accept-tests/use_case_11.txt"};
        try {
            EasyAccept.executeEasyAccept(args);
        } catch (EasyAcceptException e) {
            e.printStackTrace();
        }
        try {
            EasyAccept.executeEasyAccept(new String[]{"com.psquiza.FacadePsquiza", "tests/accept-tests/use_case_12SALVAR.txt"});
        } catch (EasyAcceptException e) {
            e.printStackTrace();
        }
        try {
            EasyAccept.executeEasyAccept(new String[]{"com.psquiza.FacadePsquiza", "tests/accept-tests/use_case_12CARREGAR.txt"});
        } catch (EasyAcceptException e) {
            e.printStackTrace();
        }
    }

    public FacadePsquiza() {
        controllerGeral = new ControllerGeral();
    }

    // MÉTODOS AQUI

    //Caso de uso 1(José Nestor)
    public String cadastraPesquisa(String descricao, String campoInteresse){
        return controllerGeral.cadastrarPesquisa(descricao, campoInteresse);
    }

    public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo){
        controllerGeral.alterarPesquisa(codigo, conteudoASerAlterado, novoConteudo);
    }

    public void encerraPesquisa(String codigo, String motivo){
        controllerGeral.encerrarPesquisa(codigo, motivo);
    }

    public void ativaPesquisa(String codigo){
        controllerGeral.ativarPesquisa(codigo);
    }

    public String exibePesquisa(String codigo){
        return controllerGeral.exibirPesquisa(codigo);
    }

    public boolean pesquisaEhAtiva(String codigo){
        return controllerGeral.pesquisAtiva(codigo);
    }



    //Caso de uso 2(Anderson)
    public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoURL){
        controllerGeral.cadastraPesquisador(nome, funcao, biografia, email, fotoURL);
    }
    public void alteraPesquisador(String email, String atributo, String novoValor){
        controllerGeral.alteraPesquisador(email, atributo, novoValor);
    }
    public void desativaPesquisador(String email){
        controllerGeral.desativaPesquisador(email);
    }
    public void ativaPesquisador(String email){
        controllerGeral.ativaPesquisador(email);
    }
    public String exibePesquisador(String email){
        return controllerGeral.exibePesquisador(email);
    }
    public boolean pesquisadorEhAtivo(String email){
        return controllerGeral.pesquisadorEhAtivo(email);
    }

    //Caso de uso 3 (Henrique)

    public void cadastraProblema(String descricao, int viabilidade) { controllerGeral.cadastraProblema(descricao, viabilidade); }

    public void apagarProblema(String codigo) { controllerGeral.apagarProblema(codigo); }

    public String exibeProblema(String codigo) { return controllerGeral.exibeProblema(codigo); }

    public void cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade)  { controllerGeral.cadastraObjetivo(tipo, descricao, aderencia, viabilidade); }

    public void apagarObjetivo(String codigo) { controllerGeral.apagarObjetivo(codigo); }

    public String exibeObjetivo(String codigo) { return  controllerGeral.exibeObjetivo(codigo); }

    //Caso de uso 4 (Davi)

    public void cadastraAtividade(String descricao,  String risco, String descricaoRisco){
        controllerGeral.cadastrarAtividade(descricao, risco, descricaoRisco);
    }

    public void apagaAtividade(String codigo){
        controllerGeral.apagarAtividade(codigo);
    }

    public void cadastraItem(String codigo, String nomeItem){
        controllerGeral.cadastraItem(codigo, nomeItem);
    }

    public String exibeAtividade(String codigo){
        return controllerGeral.exibeAtividade(codigo);
    }

    public int contaItensPendentes(String codigo){
        return controllerGeral.contaItensPendentes(codigo);
    }

    public int contaItensRealizados(String codigo){
        return controllerGeral.contaItensRealizados(codigo);
    }

    //Caso de uso 5 (Davi)
    public boolean associaProblema(String idPesquisa, String idProblema){
        return controllerGeral.associaProblema(idPesquisa, idProblema);
    }

    public boolean desassociaProblema(String idPesquisa){
        return controllerGeral.desassociaProblema(idPesquisa);
    }

    public boolean associaObjetivo(String idPesquisa, String idObjetivo){
        return controllerGeral.associaObjetivo(idPesquisa, idObjetivo);
    }

    public boolean desassociaObjetivo(String idPesquisa, String idObjetivo){
        return controllerGeral.desassociaObjetivo(idPesquisa, idObjetivo);
    }

    public String listaPesquisas(String ordem) {
        return controllerGeral.listaPesquisas(ordem);
    }

    //Caso de uso 6 (Nestor)
    public boolean associaPesquisador(String idPesquisa, String emailPesquisador){
        return controllerGeral.associaPesquisador(idPesquisa, emailPesquisador);
    }

    public boolean desassociaPesquisador(String idPesquisa, String emailPesquisador){
        return controllerGeral.desassociaPesquisador(idPesquisa, emailPesquisador);
    }
    public void cadastraEspecialidadeProfessor(String email, String formacao, String unidade, String data){
        controllerGeral.cadastraEspecialidadeProfessor(email, formacao, unidade, data);
    }
    public void cadastraEspecialidadeAluno(String email, int semestre, Double iea){
        controllerGeral.cadastraEspecialidadeAluno(email, semestre, iea);
    }
    public String listaPesquisadores(String tipo){
        return controllerGeral.listaPesquisadores(tipo);
    }

    //Caso de uso 7 (Henrique)
    public boolean associaAtividade(String codigoPesquisa, String codigoAtividade) {
        return controllerGeral.associaAtividade(codigoPesquisa, codigoAtividade);
    }

    public boolean desassociaAtividade(String codigoPesquisa, String codigoAtividade) {
        return controllerGeral.desassociaAtividade(codigoPesquisa, codigoAtividade);
    }

    public void executaAtividade(String codigoAtividade, int item, int duracao) {
        controllerGeral.executaAtividade(codigoAtividade, item, duracao);
    }

    public int cadastraResultado(String codigoAtividade, String resultado) {
        return controllerGeral.cadastraResultado(codigoAtividade, resultado);
    }

    public boolean removeResultado(String codigoAtividade, int numeroResultado) {
        return controllerGeral.removeResultado(codigoAtividade, numeroResultado);
    }

    public String listaResultados(String codigoAtividade) {
        return controllerGeral.listaResultados(codigoAtividade);
    }

    public int getDuracao(String codigoAtividade) {
        return controllerGeral.getDuracao(codigoAtividade);
    }

    // Caso de Uso 8 (Anderson)
    public String busca(String termo){
        return controllerGeral.busca(termo);
    }
    public String busca(String termo, int numeroDoResultado){
        return controllerGeral.buscaPorNumero(termo, numeroDoResultado);
    }
    public int contaResultadosBusca(String termo){
        return controllerGeral.contaResultadosBusca(termo);
    }

    // Caso de Uso 9 (Anderson)
    public void defineProximaAtividade(String idPrecedente, String idSubsquente){
        controllerGeral.defineProximaAtividade(idPrecedente, idSubsquente);
    }
    public void tiraProximaAtividade(String idPrecedente){
        controllerGeral.tiraProximaAtividade(idPrecedente);
    }
    public int contaProximos(String idPrecedente){
        return controllerGeral.contaProximos(idPrecedente);
    }
    public String pegaProximo(String idAtividade, int enesimaAtividade){
        return controllerGeral.pegaProximo(idAtividade, enesimaAtividade);
    }

    public String pegaMaiorRiscoAtividades(String idAtividade){
        return controllerGeral.pegaMaiorRiscoAtividades(idAtividade);
    }
    // Caso de Uso 10 (Henrique)
    public void configuraEstrategia(String estrategia) {
        controllerGeral.configuraEstrategia(estrategia);
    }

    public String proximaAtividade(String codigoPesquisa) {
        return controllerGeral.proximaAtividade(codigoPesquisa);
    }

    // Caso de Uso 11 (Nestor)
    public void gravarResumo(String codigoPesquisa) throws IOException {
        controllerGeral.gravarResumo(codigoPesquisa);
    }
    public void gravarResultados(String codigoPesquisa) throws IOException{
        controllerGeral.gravarResultados(codigoPesquisa);
    }

    // Caso de Uso 12 (Davi)
    public void salvar(){
        controllerGeral.salva();
    }

    public void carregar(){
        controllerGeral.carrega();
    }
}


