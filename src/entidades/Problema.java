package com.psquiza.entidades;

/**
 * Representação de um problema no sistema
 * @author HenriqueGalindo - 119110732
 * Cada problema possui descrição e viabilidade
 */
public class Problema {

    /** Representação em String da descrição do problema*/
    private String descricao;
    /** Valor inteiro entre 1 e 5 que representa a viabilidade do problema*/
    private int viabilidade;

    /**
     * Constrói problema a partir dos parâmetros passados.
     * @param descricao representação em String da descrição do objetivo
     * @param viabilidade int que representa a viabilidade do objetivo
     */
    public Problema(String descricao, int viabilidade) {
        this.descricao = descricao;
        this.viabilidade = viabilidade;
    }

    /**
     * Retorna a representação em String do objeto Problema
     * @return a representação em String do objeto
     */
    public String toString() {
        return this.descricao + " - " + this.viabilidade;
    }

}
