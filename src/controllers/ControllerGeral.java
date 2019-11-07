package com.psquiza.controllers;

import com.psquiza.entidades.Buscador;
import com.psquiza.entidades.Pesquisador;
import com.psquiza.verificadores.Verificador;

public class ControllerGeral {
    private ControllerAtividade controllerAtividade;
    private ControllerPesquisa controllerPesquisa;
    private ControllerProblema controllerProblema;
    private ControllerObjetivo controllerObjetivo;
    private ControllerPesquisador controllerPesquisador;
    private Buscador buscador;


    public ControllerGeral() {
        controllerAtividade = new ControllerAtividade();
        controllerProblema = new ControllerProblema();
        controllerObjetivo = new ControllerObjetivo();
        controllerPesquisador = new ControllerPesquisador();
        controllerPesquisa = new ControllerPesquisa();
        buscador =  new Buscador(controllerAtividade, controllerPesquisa, controllerPesquisador, controllerObjetivo, controllerProblema);
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
        return controllerPesquisa.listaPesquisas(ordem);
    }

    //Caso de uso 6 (Nestor)
    public boolean associaPesquisador(String idPesquisa, String emailPesquisador){
        if(idPesquisa.equals("") || idPesquisa == null){
            throw new RuntimeException("Campo idPesquisa nao pode ser nulo ou vazio.");
        }
        if(emailPesquisador.equals("") || emailPesquisador == null){
            throw new RuntimeException("Campo emailPesquisador nao pode ser nulo ou vazio.");
        }
        return controllerPesquisa.associaPesquisador(idPesquisa, emailPesquisador, controllerPesquisador.getPesquisador(emailPesquisador));
    }

    public boolean desassociaPesquisador(String idPesquisa, String emailPesquisador){
        if(idPesquisa.equals("") || idPesquisa == null) {
            throw new RuntimeException("Campo idPesquisa nao pode ser nulo ou vazio.");
        }
        if(emailPesquisador.equals("") || emailPesquisador == null){
            throw new RuntimeException("Campo emailPesquisador nao pode ser nulo ou vazio.");
        }

        return controllerPesquisa.desassociaPesquisadores(idPesquisa, emailPesquisador);
    }

    public void cadastraEspecialidadeProfessor(String email, String formacao, String unidade, String data){
        if(email.equals("") || email == null){
            throw new RuntimeException("Campo email nao pode ser nulo ou vazio.");
        }

        if(formacao.equals("") || formacao == null){
            throw new RuntimeException("Campo formacao nao pode ser nulo ou vazio.");
        }

        if(unidade.equals("") || unidade == null){
            throw new RuntimeException("Campo unidade nao pode ser nulo ou vazio.");
        }

        if(data.equals("") || data == null){
            throw new RuntimeException("Campo data nao pode ser nulo ou vazio.");
        }
        controllerPesquisador.cadastraEspecialidadeProfessor(email, formacao, unidade, data);
    }
    public void cadastraEspecialidadeAluno(String email, int semestre, Double iea){
        if(email.equals("") || email == null){
            throw new RuntimeException("Campo email nao pode ser nulo ou vazio.");
        }

        if(semestre < 1){
            throw new RuntimeException("Campo semestre nao pode ser nulo ou vazio.");
        }

        if(iea < 0){
            throw new RuntimeException("Campo IEA nao pode ser nulo ou vazio.");
        }
        controllerPesquisador.cadastraEspecialidadeAluno(email, semestre, iea);
    }

    // Caso de uso 7 - Henrique
    public boolean associaAtividade(String codigoPesquisa, String codigoAtividade) {
        Verificador.verificaVazioNulo(codigoPesquisa, "codigoPesquisa");
        Verificador.verificaVazioNulo(codigoAtividade, "codigoAtividade");

        return controllerPesquisa.associaAtividade(codigoPesquisa, codigoAtividade, controllerAtividade.getAtividade(codigoAtividade));
    }

    public boolean desassociaAtividade(String codigoPesquisa, String codigoAtividade) {
        Verificador.verificaVazioNulo(codigoPesquisa, "codigoPesquisa");
        Verificador.verificaVazioNulo(codigoAtividade, "codigoAtividade");
        controllerAtividade.getAtividade(codigoAtividade);

        return controllerPesquisa.desassociaAtividade(codigoPesquisa, codigoAtividade);
    }

    public void executaAtividade(String codigoAtividade, int item, int duracao) {
        Verificador.verificaVazioNulo(codigoAtividade, "codigoAtividade");
        if(item < 1) {
            throw new IllegalArgumentException("Item nao pode ser nulo ou negativo.");
        }
        if(duracao < 1) {
            throw new IllegalArgumentException("Duracao nao pode ser nulo ou negativo.");
        }

        if(controllerPesquisa.associacao(codigoAtividade)) {
            controllerAtividade.executaAtividade(codigoAtividade, item, duracao);
        }
    }

    // caso de uso 8 - Anderson
    public String busca(String termo){
        return buscador.buscaGeral(termo);
    }
    public String buscaPorNumero(String termo, int posicao){
        return buscador.buscaPorNumero(termo, posicao);
    }
    public int contaResultadosBusca(String termo){
        return buscador.contaResultados(termo);
    }

}
