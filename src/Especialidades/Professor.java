package com.psquiza.Especialidades;

import com.psquiza.entidades.Especialidade;
import com.psquiza.verificadores.Verificador;

import java.io.Serializable;

/**
 * Representação de um professor, todo professor necessita de formação, unidade e data.
 * @author José Nestor - 119110608
 */
public class Professor implements Especialidade, Serializable {
    /** Atributo usado pelo serializable */
    private static final long serialVersionUID = 1L;
    /**
     * Representação em String da formação do professor.
     */
    private String formacao;
    /**
     * Representação em String da unidade do professor.
     */
    private String unidade;
    /**
     * Representação em String da data de formação do professor.
     */
    private String data;

    /**
     * Constrói um Professor a partir da formação, unidade e data de formação.
     * @param formacao formação do professor.
     * @param unidade unidade do professor.
     * @param data data de formaçã do professor.
     */
    public Professor(String formacao, String unidade, String data){
        if (!Verificador.verificaData(data)){
            throw new RuntimeException("Atributo data com formato invalido.");
        }
        this.formacao = formacao;
        this.unidade = unidade;
        this.data = data;
    }

    /**
     * Retorna a representação em String de professor.
     * @return a representação em String de professor.
     */
    @Override
    public String toString() {
        return (formacao + " - " + unidade + " - " + data);
    }


     /**
     * Altera um dos atributos de professor.
     * @param nomeAtributo nome do atributo que será alterado.
     * @param atributo atributo que substituirá o antigo.
     */
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
