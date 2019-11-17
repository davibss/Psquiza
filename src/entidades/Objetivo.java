package com.psquiza.entidades;

import java.io.Serializable;

public class Objetivo implements Serializable {
    private static final long serialVersionUID = 1L;

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
    /**
     ** Retorna a representação em String de um objetivo.
     *  @return a representação em String de um objetivo.
     */
    public String toString() {
        int valor = this.aderencia + this.viabilidade;
        //return this.tipo + " - " + this.descricao + " - " + valor;
        return String.format("%s - %s - %s - %d", this.codigo, this.tipo, this.descricao, valor);
    }

    public String getId() {
        return this.codigo;
    }

    public String getDescricao() {
        return descricao;
    }
}
