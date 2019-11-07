package com.psquiza;

import com.psquiza.controllers.*;
import easyaccept.EasyAccept;

public class FacadePsquiza {

    private ControllerGeral controllerGeral;

    public static void main(String[] args) {

        args = new String[]{"com.psquiza.FacadePsquiza", "tests/accept-tests/use_case_1.txt",
                                                         "tests/accept-tests/use_case_2.txt",
                                                         "tests/accept-tests/use_case_3.txt",
                                                         "tests/accept-tests/use_case_4.txt",
                                                         "tests/accept-tests/use_case_5.txt",
                                                         "tests/accept-tests/use_case_6.txt"};
        EasyAccept.main(args);
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
    public boolean desassociaProblema(String idPesquisa, String idProblema){
        return controllerGeral.desassociaProblema(idPesquisa,idProblema);
    }
    public boolean associaObjetivo(String idPesquisa, String idObjetivo){
        return controllerGeral.associaObjetivo(idPesquisa, idObjetivo);
    }
    public boolean desassociaObjetivo(String idPesquisa, String idObjetivo){
        return controllerGeral.desassociaObjetivo(idPesquisa, idObjetivo);
    }
    public String listaPesquisas(String ordem) {
        //return controllerGeral.listaPesquisa(ordem);
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
}
