package com.psquiza.verificadores;

import java.util.StringJoiner;

public class Verificador {
    public static void verificaVazioNulo(String atributo, String nomeAtributo) {
        StringJoiner joiner = new StringJoiner(" ");
        if (!nomeAtributo.equals("item")) {
            joiner.add("Campo").add(nomeAtributo);
        }else{
            joiner.add("Item");
        }
        joiner.add("nao pode ser nulo ou vazio.");
        if (atributo == null || atributo.equals("")){
            throw new IllegalArgumentException(joiner.toString());
        }
    }
}
