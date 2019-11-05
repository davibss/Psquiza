package com.psquiza.Especialidades;

public class Professor {
    private String formacao;
    private String unidade;
    private String data;

    public Professor(String formacao, String unidade, String data){
        this.formacao = formacao;
        this.unidade = unidade;
        this.data = data;
    }

    @Override
    public String toString() {

        return (nome + " (" + funcao + ")" +  " - " + bio + " - " + email + " - " + foto + "-" + formacao + "-" + unidade + "-" data);
    }

   /* public void altera(){

    }*/
}
