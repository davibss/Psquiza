package com.psquiza;

import com.psquiza.controllers.*;
import easyaccept.EasyAccept;

public class FacadePsquiza {

    private ControllerAtividade controllerAtividade;
    private ControllerPesquisa controllerPesquisa;
    private ControllerProblema controllerProblema;
    private ControllerObjetivo controllerObjetivo;
    private ControllerPesquisador controllerPesquisador;

    public static void main(String[] args) {

        args = new String[]{"com.psquiza.FacadePsquiza", "tests/accept-tests/use_case_1.txt",
                                                         "tests/accept-tests/use_case_2.txt",
                                                         "tests/accept-tests/use_case_3.txt",
                                                         "tests/accept-tests/use_case_4.txt"};
        EasyAccept.main(args);
    }

    public FacadePsquiza() {
        controllerAtividade = new ControllerAtividade();
        controllerProblema = new ControllerProblema();
        controllerObjetivo = new ControllerObjetivo();
        controllerPesquisa = new ControllerPesquisa();
        controllerPesquisador = new ControllerPesquisador();
    }

    // MÉTODOS AQUI

    //Caso de uso 1(José Nestor)
    public String cadastraPesquisa(String descricao, String campoInteresse){
        System.out.println(controllerPesquisa.cadastrarPesquisa(descricao, campoInteresse));
        return controllerPesquisa.cadastrarPesquisa(descricao, campoInteresse);
    }

    public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo){
        controllerPesquisa.alterarPesquisa(codigo, conteudoASerAlterado, novoConteudo);
    }

    public void encerraPesquisa(String codigo, String motivo){
        controllerPesquisa.encerrarPesquisa(codigo, motivo);
    }
    public void ativaPesquisa(String codigo){
        controllerPesquisa.ativarPesquisa(codigo);
    }
    public String exibePesquisa(String codigo){
        return controllerPesquisa.exibirPesquisa(codigo);
    }
    public boolean pesquisaEhAtiva(String codigo){
        return controllerPesquisa.pesquisAtiva(codigo);
    }



    //Caso de uso 2(Anderson)
    public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoURL){
        controllerPesquisador.cadastraPesquisador(nome, funcao, biografia, email, fotoURL);
    }
    public void alteraPesquisador(String email, String atributo, String novoValor){
        controllerPesquisador.alteraPesquisador(email, atributo, novoValor);
    }
    public void desativaPesquisador(String email){
        controllerPesquisador.desativaPesquisador(email);
    }
    public void ativaPesquisador(String email){
        controllerPesquisador.ativaPesquisador(email);
    }
    public String exibePesquisador(String email){
        return controllerPesquisador.exibePesquisador(email);
    }
    public boolean pesquisadorEhAtivo(String email){
        return controllerPesquisador.pesquisadorEhAtivo(email);
    }

    //Caso de uso 3 (Henrique)

    public void cadastraProblema(String descricao, int viabilidade) { controllerProblema.cadastraProblema(descricao, viabilidade); }

    public void apagarProblema(String codigo) { controllerProblema.apagarProblema(codigo); }

    public String exibeProblema(String codigo) { return controllerProblema.exibeProblema(codigo); }

    public void cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade)  { controllerObjetivo.cadastraObjetivo(tipo, descricao, aderencia, viabilidade); }

    public void apagarObjetivo(String codigo) { controllerObjetivo.apagarObjetivo(codigo); }

    public String exibeObjetivo(String codigo) { return  controllerObjetivo.exibeObjetivo(codigo); }

    //Caso de uso 4 (Davi)

    public void cadastraAtividade(String descricao,  String risco, String descricaoRisco){
        controllerAtividade.cadastrarAtividades(descricao, risco, descricaoRisco);
    }

    public void apagaAtividade(String codigo){
        controllerAtividade.apagarAtividade(codigo);
    }

    public void cadastraItem(String codigo, String nomeItem){
        controllerAtividade.cadastraItem(codigo, nomeItem);
    }

    public String exibeAtividade(String codigo){
        return controllerAtividade.exibeAtividade(codigo);
    }

    public int contaItensPendentes(String codigo){
        return controllerAtividade.contaItensPendentes(codigo);
    }

    public int contaItensRealizados(String codigo){
        return controllerAtividade.contaItensRealizados(codigo);
    }
}
