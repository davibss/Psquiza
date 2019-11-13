package com.psquiza.Especialidades;

import com.psquiza.entidades.Especialidade;

import java.io.Serializable;
import java.util.Locale;

public class Aluno implements Especialidade, Serializable {
    private int semestre;
    private Double IEA;

    public Aluno(int semestre, Double IEA){
        this.semestre = semestre;
        this.IEA = IEA;
    }

    @Override
    public String toString() {
        return String.format(Locale.US,"%do SEMESTRE - %.1f", semestre, IEA);
        //return (semestre + "o SEMESTRE" + " - " + IEA);
    }

    public void altera(String nomeAtributo, String atributo){
        if(nomeAtributo.equals("SEMESTRE")){
            if(Integer.parseInt(atributo) < 1){
                throw new RuntimeException("Atributo semestre com formato invalido.");
            }
            this.semestre = Integer.parseInt(atributo);
        }
        if(nomeAtributo.equals("IEA")){
            if(Double.parseDouble(atributo) > 10 || Double.parseDouble(atributo) < 0){
                throw new RuntimeException("Atributo IEA com formato invalido.");
            }
            this.IEA = Double.parseDouble(atributo);
        }
    }
}
