package com.psquiza.Especialidades;

import com.psquiza.entidades.Especialidade;
import com.psquiza.verificadores.Verificador;

import java.io.Serializable;

public class Professor implements Especialidade, Serializable {
    private String formacao;
    private String unidade;
    private String data;

    public Professor(String formacao, String unidade, String data){
//        Pattern dataPadrao = Pattern.compile("(^(((0[1-9]|[12][0-8])[/](0[1-9]|1[012]))|((29|30|31)[/](0[13578]|1[02]))|((29|30)[/](0[4,6,9]|11)))[/]" +
//                "(19|[2-9][0-9])\\d\\d$)|(^29[/]02[/](19|[2-9][0-9])(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)$)");
//        data.matches("(^(((0[1-9]|[12][0-8])[/](0[1-9]|1[012]))|((29|30|31)[/](0[13578]|1[02]))|((29|30)[/](0[4,6,9]|11)))[/]" +
//                "(19|[2-9][0-9])\\d\\d$)|(^29[/]02[/](19|[2-9][0-9])(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)$)");
        if (!Verificador.verificaData(data)){
            throw new RuntimeException("Atributo data com formato invalido.");
        }
//        if (!dataPadrao.matcher(data).matches()){
//            throw new RuntimeException("Atributo data com formato invalido.");
//        }
//        if (!data.matches("\\d{2}[/]\\d{2}[/]\\d{4}")){
//            throw new RuntimeException("Atributo data com formato invalido.");
//        }
//        String[] analise = data.split("/");
//        if(Integer.parseInt(analise[0]) > 31 || Integer.parseInt(analise[1]) > 12 || Integer.parseInt(analise[2]) > 2019 || Integer.parseInt(analise[2]) < 1919) {
//            throw new RuntimeException("Atributo data com formato invalido.");
//        }
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
//            if (!data.matches("\\d{2}[/]\\d{2}[/]\\d{4}")){
//                throw new RuntimeException("Atributo data com formato invalido.");
//            }
            this.data = atributo;
        }
    }
}
