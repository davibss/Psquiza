package com.psquiza.Especialidades;

public class Aluno {
    private int semestre;
    private Double IEA;

    public Aluno(int semestre, Double IEA){
        this.semestre = semestre;
        this.IEA = IEA;
    }

    @Override
    public String toString() {
        return (nome + " (" + funcao + ")" +  " - " + bio + " - " + email + " - " + foto + "-" + semestre + "-" + IEA);
    }

  /*  public void altera(){

    }*/
}
