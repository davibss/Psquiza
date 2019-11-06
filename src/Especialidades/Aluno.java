package com.psquiza.Especialidades;

import com.psquiza.entidades.Especialidade;

public class Aluno implements Especialidade {
    private int semestre;
    private Double IEA;

    public Aluno(int semestre, Double IEA){
        this.semestre = semestre;
        this.IEA = IEA;
    }

    @Override
    public String toString() {
        return (semestre + "-" + IEA);
    }

  /*  public void altera(){

    }*/
}
