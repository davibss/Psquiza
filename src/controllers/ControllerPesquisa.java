package com.psquiza.controllers;


import com.psquiza.entidades.Pesquisa;

import java.util.*;

/**
 * Controller de pesquisa, classe responsável por cadastrar, alterar, ativar, encerrar e exibir pesquisa
 * @author José Nestor
 */
public class ControllerPesquisa {
    /** Mapa que armazena pesquisas, pesquisas são identificadas por um código gerado no sistema*/
    private Map<String, Pesquisa> pesquisas;

    //private ControllerAssociacaoPesquisa controllerAssociacaoPesquisa;

    public ControllerPesquisa(){
        this.pesquisas = new HashMap<>();
    }

    /**
     * Constrói um controller, inicializando um mapa.
     */
//    public ControllerPesquisa(ControllerAssociacaoPesquisa controllerAssociacaoPesquisa){
//        this.pesquisas = new HashMap<>();
//        this.controllerAssociacaoPesquisa = controllerAssociacaoPesquisa;
//    }

    /**
     * Cadastra uma pesquisa, cria um código que identifica pesquisas no mapa, lança exceções se necessário e retorna o código em forma de String.
     * @param descricao representação em String da descrição da pesquisa.
     * @param campoInteresse representação em String do campo de interesse da pesquisa.
     * @return representação em String do código identificador de pesquisas.
     */
    public String cadastrarPesquisa(String descricao, String campoInteresse){
       if(descricao == null || descricao.equals("")){
            throw new NullPointerException("Descricao nao pode ser nula ou vazia.");
       }

       String[] campos = campoInteresse.split(",");
       if (campos.length > 4 || campos[0].trim().length() < 3 || campoInteresse.length() > 255){
           throw new IllegalArgumentException ("Formato do campo de interesse invalido.");
       }
       for(String topico : campos){
           //if (topico == null || topico.equals("") || topico.equals(" ")){
           if (topico == null || topico.trim().equals("")){
               throw new IllegalArgumentException ("Formato do campo de interesse invalido.");
           }
       }
       int valorInt = 1;
       for(String codigo : this.pesquisas.keySet()){
           if(codigo.substring(0,3).equals(campos[0].trim().substring(0, 3).toUpperCase())){
               valorInt = Integer.parseInt(String.valueOf(codigo.charAt(3))) + 1;
           }
       }
       String codigo = campos[0].trim().substring(0, 3).toUpperCase() + valorInt;
       this.pesquisas.put(codigo, new Pesquisa(codigo,true, descricao, campoInteresse));
       return codigo;
    }

    /**
     * Altera o conteudo de pesquisa, podendo ser a descrição ou campo de interesse e lança exceções se necessário.
     * @param codigo representação em String do código que identifica pesquisas
     * @param conteudoASerAlterado representação em String do conteúdo que será alterado, podendo ser descrição ou campo de interesse.
     * @param novoConteudo representação em String do novo conteúdo.
     */
    public void alterarPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo){
        if((novoConteudo == null || (novoConteudo.equals(""))) && conteudoASerAlterado.equals("DESCRICAO")){
            throw new RuntimeException("Descricao nao pode ser nula ou vazia.");
        }
        else if((novoConteudo == null || (novoConteudo.equals(""))) && conteudoASerAlterado.equals("CAMPO")) {
            throw new RuntimeException("Formato do campo de interesse invalido.");
        }
        verificaPesquisa(codigo);
//        else if(!pesquisas.containsKey(codigo)){
//            throw new RuntimeException("Pesquisa nao encontrada.");
//        }
        if(!pesquisas.get(codigo).estadoAtivacao()){
            throw new RuntimeException("Pesquisa desativada.");
        }
        else if (conteudoASerAlterado.equals("DESCRICAO")) {
            pesquisas.get(codigo).setDescricao(novoConteudo);
        }
        else if(conteudoASerAlterado.equals("CAMPO")) {
            pesquisas.get(codigo).setCampoInteresse(novoConteudo);
        }
        else{
            throw new RuntimeException("Nao e possivel alterar esse valor de pesquisa.");
        }
    }

    /**
     * Encerra a pesquisa mudando seu estado para falso.
     * @param codigo representação em String do código que identifica pesquisas
     * @param motivo representação em String do motivo de encerrar a pesquisa
     */
    public void encerrarPesquisa(String codigo, String motivo){
        verificaVazioNulo(codigo,"Codigo");
        verificaVazioNulo(motivo,"Motivo");
        verificaPesquisa(codigo);
//        if (!pesquisas.containsKey(codigo)){
//            throw new NullPointerException("Pesquisa nao encontrada.");
//        }
        if(!pesquisas.get(codigo).estadoAtivacao()){
              throw new RuntimeException("Pesquisa desativada.");
        }
        pesquisas.get(codigo).setEstadoAtivacao(false);
    }

    /**
     * Ativa a pesquisa mudando seu estado para verdadeiro.
     * @param codigo representação em String do código que identifica pesquisas
     */
    public void ativarPesquisa(String codigo){
        verificaVazioNulo(codigo,"Codigo");
        verificaPesquisa(codigo);
//        if (!pesquisas.containsKey(codigo)){
//            throw new NullPointerException("Pesquisa nao encontrada.");
//        }
        if(pesquisas.get(codigo).estadoAtivacao()) {
            throw new RuntimeException("Pesquisa ja ativada.");
        }
        pesquisas.get(codigo).setEstadoAtivacao(true);
    }

    /**
     * * Retorna uma String que representa uma pesquisa.
     *      * A representação segue o formato: "Código - descrição - campo de interesse".
     *
     * @param codigo representação em String do código que identifica pesquisas
     * @return A representação em String de uma pesquisa
     */
    public String exibirPesquisa(String codigo){
        verificaVazioNulo(codigo,"Codigo");
        if (!pesquisas.containsKey(codigo)){
            throw new NullPointerException("Pesquisa nao encontrada.");
        }
        //return codigo + pesquisas.get(codigo).toString();
        return pesquisas.get(codigo).toString();
    }

    /**
     * Retorna um valor boleano que representa o estado de ativação da pesquisa, sendo verdadeiro para ativa e falso para desativa.
     * @param codigo representação em String do código que identifica pesquisas
     * @return A representação boleana do estado da pesquisa.
     */
    public boolean pesquisAtiva(String codigo){
        if(codigo == null || codigo.equals("")){
            throw new RuntimeException("Codigo nao pode ser nulo ou vazio.");
        }
        verificaPesquisa(codigo);
//        else if (!pesquisas.containsKey(codigo)){
//            throw new NullPointerException("Pesquisa nao encontrada.");
//        }
        return pesquisas.get(codigo).estadoAtivacao();
    }

    /**
     * Verifica se o parâmetro passado é vazio ou nulo, se for, monta String
     * pra lançar uma exceção.
     * @param atributo representação em String do atributo a ser verificado.
     * @param nomeAtributo representação em String do nome do atributo.
     */
    private void verificaVazioNulo(String atributo, String nomeAtributo) {
        StringJoiner joiner = new StringJoiner(" ");
        joiner.add(nomeAtributo);
        joiner.add("nao pode ser nulo ou vazio.");
        if (atributo == null || atributo.equals("")){
            throw new IllegalArgumentException(joiner.toString());
        }
    }

    public void verificaPesquisa(String codigo){
        if (!pesquisas.containsKey(codigo)){
            throw new NullPointerException("Pesquisa nao encontrada.");
        }
    }
    
    public String listaPesquisas(String ordem, ControllerAssociacaoPesquisa controllerAssociacaoPesquisa) {
        StringJoiner joiner = new StringJoiner(" | ");
        ArrayList<Pesquisa> lista = new ArrayList<>(this.pesquisas.values());
        switch (ordem){
            case "PROBLEMA":
                controllerAssociacaoPesquisa.ordenaPorIDProblema().forEach(c -> joiner.add(exibirPesquisa(c)));
                this.pesquisas.keySet().stream().
                        filter(k -> !controllerAssociacaoPesquisa.ordenaPorIDProblema().contains(k)).
                        sorted((chave1, chave2) -> chave1.compareTo(chave2) * -1).
                        forEach(c -> joiner.add(exibirPesquisa(c)));
                break;
            case "OBJETIVOS":
                controllerAssociacaoPesquisa.ordenaPorObjetivos().forEach(c -> joiner.add(exibirPesquisa(c)));
                this.pesquisas.keySet().stream().
                        filter(k -> !controllerAssociacaoPesquisa.ordenaPorObjetivos().contains(k)).
                        sorted((chave1, chave2) -> chave1.compareTo(chave2) * -1).
                        forEach(c -> joiner.add(exibirPesquisa(c)));
                break;
            case "PESQUISA":
                lista.sort((pesquisa1, pesquisa2) -> pesquisa1.getCodigo().compareTo(pesquisa2.getCodigo()) * -1);
                lista.forEach(pesquisa -> joiner.add(pesquisa.toString()));
                break;
            default:
                throw new IllegalArgumentException("Valor invalido da ordem");
        }
        return joiner.toString();
    }
}

