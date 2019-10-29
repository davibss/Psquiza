package com.psquiza.entidades;

public class Objetivo {

    private String tipo;
    private String descricao;
    public int aderencia;
    private int viabilidade;


    public Objetivo(String tipo, String descricao, int aderencia, int viabilidade) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.aderencia = aderencia;
        this.viabilidade = viabilidade;
    }


    public String toString() {
        int valor = this.aderencia + this.viabilidade;
        return this.tipo + " - " + this.descricao + " - " + valor;
    }

}
