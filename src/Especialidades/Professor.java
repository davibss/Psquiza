package com.psquiza.Especialidades;

import com.psquiza.entidades.Especialidade;

public class Professor implements Especialidade {
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

        return (formacao + "-" + unidade + "-" + data);
    }

    public void altera(String nomeAtributo, String atributo){
        if(nomeAtributo.equals("FORMACAO")){
            this.formacao = atributo;
        }
        if(nomeAtributo.equals("UNIDADE")){
            this.unidade = atributo;
        }
        if(nomeAtributo.equals("DATA")){
            this.data = atributo;
        }
    }
}
