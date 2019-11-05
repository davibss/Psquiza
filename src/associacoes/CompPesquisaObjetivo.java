package com.psquiza.associacoes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CompPesquisaObjetivo {
    private String idProblema;
    private List<String> objetivos;

    public CompPesquisaObjetivo() {
        this.idProblema = "";
        objetivos = new ArrayList<>();
    }

    public boolean associaProblema(String problema) {
        if (this.idProblema.equals(problema)){
            return false;
        }
        if (!this.idProblema.equals("")){
            throw new IllegalArgumentException("Pesquisa ja associada a um problema.");
        }
        this.idProblema = problema;
        return true;
    }

    public boolean desassociaProblema(String problema){
        if (!this.idProblema.equals(problema) || this.idProblema.equals("")){
            return false;
        }
        this.idProblema = "";
        return true;
    }

    public boolean asssociaObjetivo(String objetivo){
        if (this.objetivos.contains(objetivo)){
            return false;
        }
        this.objetivos.add(objetivo);
        return true;
    }

    public boolean desassociaObjetivo(String idObjetivo){
        if (!this.objetivos.contains(idObjetivo)){
            return false;
        }
        this.objetivos.removeIf((o) -> o.equals(idObjetivo));
        return true;
    }

    public int getObjetivos(){
        return this.objetivos.size();
    }

    public String maiorObjetivo(){
        return Collections.max(this.objetivos, (chave1, chave2) -> chave1.compareTo(chave2) * -1);
    }

    public boolean contemObjetivo(String objetivo){
        return this.objetivos.contains(objetivo);
    }

    public String getProblema() {
        return this.idProblema;
    }
}
