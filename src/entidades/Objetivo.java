package com.psquiza.entidades;

import java.io.Serializable;

/**
 * Representação de um objetivo no sistema
 * @author HenriqueGalindo - 119110732
 * Cada objetivo possui tipo, descrição, aderencia e viabilidade
 */
public class Objetivo implements Serializable {
    /** Atributo usado pelo serializable */
    private static final long serialVersionUID = 1L;
    /** Representação em String do código que identifica unicamente o objetivo. */
    private String codigo;
    /** Representação em String do tipo do objetivo, que pode ser GERAL ou ESPECIFICO. */
    private String tipo;
    /** Representação em String da descrição do objetivo. */
    private String descricao;
    /** Valor inteiro entre 1 e 5 que representa a aderencia do objetivo. */
    private int aderencia;
    /** Valor inteiro entre 1 e 5 que representa a viabilidade do objetivo. */
    private int viabilidade;

    /**
     * Constrói objetivo a partir dos parâmetros passados.
     * @param codigo representação em String do código de objetivo.
     * @param tipo representação em String do tipo do objetivo.
     * @param descricao representação em String da descrição do objetivo.
     * @param aderencia int que representa a aderencia do objetivo.
     * @param viabilidade int que representa a viabilidade do objetivo.
     */
    public Objetivo(String codigo, String tipo, String descricao, int aderencia, int viabilidade) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.descricao = descricao;
        this.aderencia = aderencia;
        this.viabilidade = viabilidade;
    }
    /**
     ** Retorna a representação em String de um objetivo.
     *  @return a representação em String de um objetivo.
     */
    public String toString() {
        int valor = this.aderencia + this.viabilidade;
        //return this.tipo + " - " + this.descricao + " - " + valor;
        return String.format("%s - %s - %s - %d", this.codigo, this.tipo, this.descricao, valor);
    }

    /**
     * Retorna o ID de objetivo.
     * @return a representação em String do ID de objetivo.
     */
    public String getId() {
        return this.codigo;
    }

    /**
     * Retorna a descrição do objetivo.
     * @return a representação em String da descrição do objetivo.
     */
    public String getDescricao() {
        return descricao;
    }
}
