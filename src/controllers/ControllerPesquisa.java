package com.psquiza.controllers;


import com.psquiza.entidades.Pesquisa;

import java.util.*;

public class ControllerPesquisa {
    private Map<String, Pesquisa> pesquisas;
    private int valorInt;

    public ControllerPesquisa(){
        this.pesquisas = new HashMap<>();
        this.valorInt = 0;
    }
    public String cadastrarPesquisa(String descricao, String campoInteresse){
        String[] verifica = campoInteresse.split(", ");
        //verifica[0].substring(0, 2)
        if (verifica.length > 4 || verifica[0].length() < 3 || campoInteresse.length() > 255){
            throw new IllegalArgumentException ("Formato do campo de interesse invalido.");
        }
        valorInt ++;
        return "";
    }
}

