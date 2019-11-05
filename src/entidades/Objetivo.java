package com.psquiza.entidades;

public class Objetivo {
    private String codigo;
    private String tipo;
    private String descricao;
    private int aderencia;
    private int viabilidade;


    public Objetivo(String codigo, String tipo, String descricao, int aderencia, int viabilidade) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.descricao = descricao;
        this.aderencia = aderencia;
        this.viabilidade = viabilidade;
    }

    public String toString() {
        int valor = this.aderencia + this.viabilidade;
        //return this.tipo + " - " + this.descricao + " - " + valor;
        return String.format("%s - %s - %s - %d", this.codigo, this.tipo, this.descricao, valor);
    }

    public String getId() {
        return this.codigo;
    }
}
