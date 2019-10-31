package com.psquiza.controllers;

import com.psquiza.entidades.Objetivo;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class ControllerObjetivo {
    private Map<String, Objetivo> objetivos;
    private int idObjetivo;

    public ControllerObjetivo() {
        this.objetivos = new HashMap<>();
        this.idObjetivo = 1;
    }

    public void cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade) {
        verificaValidadeObjetivo(tipo, descricao, aderencia, viabilidade);
        String chave = "O"+(this.idObjetivo);
        this.objetivos.put(chave, new Objetivo(tipo, descricao, aderencia, viabilidade));
        this.idObjetivo += 1;
    }

    private void verificaValidadeObjetivo(String tipo, String descricao, int aderencia, int viabilidade) {
        verificaVazioNulo(descricao, "descricao");
        verificaVazioNulo(tipo, "tipo");
        if(!(tipo.equals("GERAL") || tipo.equals("ESPECIFICO"))) {
            throw new IllegalArgumentException("Valor invalido de tipo.");
        }
        if(aderencia < 1 || aderencia > 5) {
            throw new IllegalArgumentException("Valor invalido de aderencia");
        }
        if(viabilidade < 1 || viabilidade > 5) {
            throw new IllegalArgumentException("Valor invalido de viabilidade.");
        }
    }

    public void apagarObjetivo(String codigo) {
        verificaVazioNulo(codigo, "codigo");
        if(!this.objetivos.containsKey(codigo)) {
            throw new IllegalArgumentException("Objetivo nao encontrado");
        }
        this.objetivos.remove(codigo);
    }

    public String exibeObjetivo(String codigo) {
        if(!this.objetivos.containsKey(codigo)) {
            throw new IllegalArgumentException("Objetivo nao encontrado");
        }
        return codigo + " - " + this.objetivos.get(codigo).toString();
    }

    private void verificaVazioNulo(String atributo, String nomeAtributo) {
        StringJoiner joiner = new StringJoiner(" ");

        joiner.add("Campo").add(nomeAtributo);
        joiner.add("nao pode ser nulo ou vazio.");
        if (atributo == null || atributo.equals("")){
            throw new IllegalArgumentException(joiner.toString());
        }
    }
}
