package com.psquiza.entidades;

public class Pesquisador {
    private String funcao;
    private String bio;
    private String nome;
    private String email;
    private String foto;
    private boolean ativo;

    public Pesquisador(String nome, String funcao, String bio, String email, String foto) {
        this.funcao = funcao;
        this.bio = bio;
        this.nome = nome;
        this.email = email;
        this.foto = foto;
        this.ativo = true;
    }

    public boolean ehAtivo() {
        return ativo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return (nome + " (" + funcao + ")" +  " - " + bio + " - " + email + " - " + foto );
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Pesquisador that = (Pesquisador) object;
        return java.util.Objects.equals(email, that.email);
    }

    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), email);
    }
}
