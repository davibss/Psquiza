package com.psquiza.entidades;

public class Pesquisa {
    private String descricao;
    private String campoInteresse;

    public Pesquisa(String descricao, String campoInteresse){
        this.descricao = descricao;
        this.campoInteresse = campoInteresse;
    }

    @Override
    public String toString() {
        return descricao + campoInteresse;
    }
}
