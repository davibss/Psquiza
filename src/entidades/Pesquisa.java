package com.psquiza.entidades;

public class Pesquisa {
    private boolean estadoAtivacao;
    private String descricao;
    private String campoInteresse;

    public Pesquisa(boolean estadoAtivacao, String descricao, String campoInteresse){
        this.estadoAtivacao = estadoAtivacao;
        this.descricao = descricao;
        this.campoInteresse = campoInteresse;
    }

    @Override
    public String toString() {
        return " - " + descricao + " - " +campoInteresse;
    }

    public void setCampoInteresse(String campoInteresse) {
        this.campoInteresse = campoInteresse;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setEstadoAtivacao(boolean estadoAtivacao) {
        this.estadoAtivacao = estadoAtivacao;
    }

    public boolean estadoAtivacao() {
        return estadoAtivacao;
    }
}
