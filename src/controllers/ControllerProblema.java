package com.psquiza.controllers;

import com.psquiza.entidades.Problema;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class ControllerProblema {
    private Map<String, Problema> problemas;
    private int idProblema;

    public ControllerProblema() {
        this.problemas = new HashMap<>();
        this.idProblema = 1;
    }

    public void cadastraProblema(String descricao, int viabilidade) {
        verificaValidadeProblema(descricao, viabilidade);
        String chave = "P"+(this.idProblema);
        this.problemas.put(chave, new Problema(chave, descricao, viabilidade));
        this.idProblema += 1;
    }

    private void verificaValidadeProblema(String descricao, int viabilidade) {
        verificaVazioNulo(descricao, "descricao");
        if(viabilidade < 1 || viabilidade > 5) {
            throw new IllegalArgumentException("Valor invalido de viabilidade.");
        }
    }

    public void apagarProblema(String codigo) {
        verificaVazioNulo(codigo, "codigo");
        verificaProblema(codigo);
//        if(!this.problemas.containsKey(codigo)) {
//            throw new IllegalArgumentException("Problema nao encontrado");
//        }
        this.problemas.remove(codigo);
    }

    public String exibeProblema(String codigo) {
        verificaVazioNulo(codigo,"codigo");
        verificaProblema(codigo);
//        if(!this.problemas.containsKey(codigo)) {
//            throw new IllegalArgumentException("Problema nao encontrado");
//        }
        //return codigo + " - " + this.problemas.get(codigo).toString();
        return this.problemas.get(codigo).toString();
    }

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
