package com.psquiza.Especialidades;

import com.psquiza.entidades.Especialidade;
import com.psquiza.verificadores.Verificador;

import java.io.Serializable;

public class Professor implements Especialidade, Serializable {
    private static final long serialVersionUID = 1L;

    private String formacao;
    private String unidade;
    private String data;

    public Professor(String formacao, String unidade, String data){
        if (!Verificador.verificaData(data)){
            throw new RuntimeException("Atributo data com formato invalido.");
        }
        this.formacao = formacao;
        this.unidade = unidade;
        this.data = data;
    }

    @Override
    public String toString() {

        return (formacao + " - " + unidade + " - " + data);
    }

    public void altera(String nomeAtributo, String atributo){
        if(nomeAtributo.equals("FORMACAO")){
            this.formacao = atributo;
        }
        if(nomeAtributo.equals("UNIDADE")){
            this.unidade = atributo;
        }
        if(nomeAtributo.equals("DATA")) {
            if (!Verificador.verificaData(atributo)){
                throw new RuntimeException("Atributo data com formato invalido.");
            }
            this.data = atributo;
        }
    }
}
