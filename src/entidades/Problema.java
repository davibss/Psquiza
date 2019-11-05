package com.psquiza.entidades;

import java.util.Objects;

public class Problema {
    private String codigo;
    private String descricao;
    private int viabilidade;

    public Problema() {
        this.codigo = null;
        this.descricao = null;
        this.viabilidade = 0;
    }

    public Problema(String codigo, String descricao, int viabilidade) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.viabilidade = viabilidade;
    }

    public String toString() {
        return String.format("%s - %s - %d",this.codigo, this.descricao, this.viabilidade);
        //return this.descricao + " - " + this.viabilidade;
    }

    public String getIdProblema() {
        return this.codigo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Problema problema = (Problema) o;
        return Objects.equals(codigo, problema.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}
