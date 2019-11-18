package com.psquiza.Especialidades;

import com.psquiza.entidades.Especialidade;

import java.io.Serializable;
import java.util.Locale;
/**
 * Representação de um aluno, todo aluno necessita de semestre e IEA.
 * @author José Nestor - 119110608
 */
public class Aluno implements Especialidade, Serializable {
    /** Atributo usado pelo serializable */
    private static final long serialVersionUID = 1L;
    /**
     * Represnetação em número inteiro do semestre do aluno.
     */
    private int semestre;
    /**
     * Representação em double do índice de eficiência acadêmica do aluno.
     */
    private Double IEA;
    /**
     * Constrói um Professor a partir da formação, unidade e data de formação.
     * @param semestre semestre do aluno.
     * @param IEA índice de eficiência acadêmica do aluno.
     */
    public Aluno(int semestre, Double IEA){
        this.semestre = semestre;
        this.IEA = IEA;
    }
    /**
     * Retorna a representação em String de aluno.
     * @return a representação em String de aluno.
     */
    @Override
    public String toString() {
        return String.format(Locale.US,"%do SEMESTRE - %.1f", semestre, IEA);
    }
    /**
     * Altera um dos atributos de um aluno.
     * @param nomeAtributo nome do atributo que será alterado.
     * @param atributo atributo que substituirá o antigo.
     */
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
