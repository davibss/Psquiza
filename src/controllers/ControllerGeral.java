package com.psquiza.controllers;

import com.psquiza.verificadores.Verificador;

public class ControllerGeral {
    private ControllerAtividade controllerAtividade;
    private ControllerPesquisa controllerPesquisa;
    private ControllerProblema controllerProblema;
    private ControllerObjetivo controllerObjetivo;
    private ControllerPesquisador controllerPesquisador;
    private ControllerAssociacaoPesquisa controllerAssociacaoPesquisa;

    public ControllerGeral() {
        controllerAtividade = new ControllerAtividade();
        controllerProblema = new ControllerProblema();
        controllerObjetivo = new ControllerObjetivo();
        controllerPesquisador = new ControllerPesquisador();
        controllerPesquisa = new ControllerPesquisa();
        controllerAssociacaoPesquisa = new ControllerAssociacaoPesquisa(controllerPesquisa);
    }

    //Caso de uso 1(José Nestor)
    public String cadastrarPesquisa(String descricao, String campoInteresse){
        return controllerPesquisa.cadastrarPesquisa(descricao, campoInteresse);
    }

    public void alterarPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo){
        controllerPesquisa.alterarPesquisa(codigo, conteudoASerAlterado, novoConteudo);
    }

    public void encerrarPesquisa(String codigo, String motivo){
        controllerPesquisa.encerrarPesquisa(codigo, motivo);
    }

    public void ativarPesquisa(String codigo){
        controllerPesquisa.ativarPesquisa(codigo);
    }

    public String exibirPesquisa(String codigo){
        return controllerPesquisa.exibirPesquisa(codigo);
    }

    public boolean pesquisAtiva(String codigo){
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

    public void cadastrarAtividade(String descricao,  String risco, String descricaoRisco){
        controllerAtividade.cadastrarAtividades(descricao, risco, descricaoRisco);
    }

    public void apagarAtividade(String codigo){
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

    //Caso de uso 5 (Davi)
    public boolean associaProblema(String idPesquisa, String idProblema){
        Verificador.verificaVazioNulo(idPesquisa, "idPesquisa");
        Verificador.verificaVazioNulo(idProblema, "idProblema");
        return controllerPesquisa.associaProblema(idPesquisa, controllerProblema.getProblema(idProblema));
    }
    public boolean desassociaProblema(String idPesquisa, String idProblema){
        return controllerPesquisa.desassociaProblema(idPesquisa,idProblema);
    }
    public boolean associaObjetivo(String idPesquisa, String idObjetivo){
        Verificador.verificaVazioNulo(idPesquisa,"idPesquisa");
        Verificador.verificaVazioNulo(idObjetivo,"idObjetivo");
        return controllerPesquisa.associaObjetivo(idPesquisa, controllerObjetivo.getObjetivo(idObjetivo));
    }
    public boolean desassociaObjetivo(String idPesquisa, String idObjetivo){
        return controllerPesquisa.desassociaObjetivo(idPesquisa, idObjetivo);
    }
    public String listaPesquisas(String ordem) {
        //return controllerAssociacaoPesquisa.listaPesquisa(ordem);
        return controllerPesquisa.listaPesquisas(ordem);
    }
}
