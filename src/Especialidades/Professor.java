package com.psquiza.Especialidades;

import com.psquiza.entidades.Especialidade;

public class Professor implements Especialidade {
    private String formacao;
    private String unidade;
    private String data;

    public Professor(String formacao, String unidade, String data){
        if (!data.matches("\\d{2}[/]\\d{2}[/]\\d{4}")){
            throw new RuntimeException("Atributo data com formato invalido.");
        }
        String[] analise = data.split("/");
        if(Integer.parseInt(analise[0]) > 31 || Integer.parseInt(analise[1]) > 12 || Integer.parseInt(analise[2]) > 2019 || Integer.parseInt(analise[2]) < 1919) {
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
            if (!data.matches("\\d{2}[/]\\d{2}[/]\\d{4}")){
                throw new RuntimeException("Atributo data com formato invalido.");
            }
            this.data = atributo;
        }
    }
}
