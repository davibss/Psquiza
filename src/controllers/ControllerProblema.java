package com.psquiza.controllers;

import com.psquiza.entidades.Problema;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Controller do objeto Problema
 * @author HenriqueGalindo - 119110732
 */
public class ControllerProblema {
    /** Mapa contendo os objetos Problema onde a chave é o código e o valor é o problema*/
    private Map<String, Problema> problemas;
    /** Contador que indica o id do próximo problema a ser cadastrado*/
    private int idProblema;

    /**
     * Constrói o controller e inicializa o mapa e o contador
     * idProblema inicia como 1, pois este é o id do primeiro problema cadastrado
     */
    public ControllerProblema() {
        this.problemas = new HashMap<>();
        this.idProblema = 1;
    }

    /**
     * Cadastra problemas no mapa de problemas
     * Verifica se os parâmetros são vazios ou nulos
     * Verifica se viabilidade está entre 1 e 5
     * Gera o código do problema cadastrado
     * @param descricao representação em String da decricao do problema
     * @param viabilidade valor inteiro entre 1 e 5 que representa a viabilidade do problema
     */
    public void cadastraProblema(String descricao, int viabilidade) {
        verificaValidadeProblema(descricao, viabilidade);
        String chave = "P"+(this.idProblema);
        this.problemas.put(chave, new Problema(chave, descricao, viabilidade));
        this.idProblema += 1;
    }

    /**
     * Verifica se os parametros são vazios ou nulos e se viabilidade está entre 1 e 5
     * @param descricao objeto String sob teste
     * @param viabilidade int sob teste
     */
    private void verificaValidadeProblema(String descricao, int viabilidade) {
        verificaVazioNulo(descricao, "descricao");
        if(viabilidade < 1 || viabilidade > 5) {
            throw new IllegalArgumentException("Valor invalido de viabilidade.");
        }
    }

    /**
     * Remove problemas no mapa de problemas
     * Verifica se o parâmetro passado é vazio ou nulo
     * Verifica se o mapa contém o problema que deve ser removido
     * @param codigo representação em String do código do problema a ser removido
     */
    public void apagarProblema(String codigo) {
        verificaVazioNulo(codigo, "codigo");
        verificaProblema(codigo);
//        if(!this.problemas.containsKey(codigo)) {
//            throw new IllegalArgumentException("Problema nao encontrado");
//        }
        this.problemas.remove(codigo);
    }

    /**
     * Retorna a representação em String de um objeto Problema
     * Verifica se o parâmetro passado é vazio ou nulo
     * Verifica se o mapa contém o problema que deve ser exibido
     * @param codigo representação em String do código do problema a ser exibido
     * @return representação em String de um objeto Problema
     */
    public String exibeProblema(String codigo) {
        verificaVazioNulo(codigo,"codigo");
        verificaProblema(codigo);
//        if(!this.problemas.containsKey(codigo)) {
//            throw new IllegalArgumentException("Problema nao encontrado");
//        }
        //return codigo + " - " + this.problemas.get(codigo).toString();
        return this.problemas.get(codigo).toString();
    }

    /**
     * Verifica se o parâmetro passado é vazio ou nulo, se for, monta String
     * pra lançar uma exceção.
     * @param atributo representação em String do atributo a ser verificado.
     * @param nomeAtributo representação em String do nome do atributo.
     */
    private void verificaVazioNulo(String atributo, String nomeAtributo) {
        StringJoiner joiner = new StringJoiner(" ");

        if (!nomeAtributo.equals("item")) {
            joiner.add("Campo").add(nomeAtributo);
        }else{
            joiner.add("Item");
        }

        joiner.add("nao pode ser nulo ou vazio.");
        if (atributo == null || atributo.equals("")) {
            throw new IllegalArgumentException(joiner.toString());
        }
    }

    public void verificaProblema(String codigo){
        if(!this.problemas.containsKey(codigo)) {
            throw new IllegalArgumentException("Problema nao encontrado");
        }
    }

    public Problema getProblema(String problema) {
        return this.problemas.get(problema);
    }
}
