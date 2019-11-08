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

        return (semestre + "o SEMESTRE" + " - " + IEA);
    }

    public void altera(String nomeAtributo, String atributo){
        if(semestre < 1){
            throw new RuntimeException("Atributo semestre com formato invalido.");
        }
        if(IEA > 10 || IEA < 0){
            throw new RuntimeException("Atributo IEA com formato invalido.");
        }
        if(nomeAtributo.equals("SEMESTRE")){
            this.semestre = Integer.parseInt(atributo);
        }
        if(nomeAtributo.equals("IEA")){
            this.IEA = Double.parseDouble(atributo);
        }

    }
}
