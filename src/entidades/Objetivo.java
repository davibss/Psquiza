package com.psquiza.entidades;

/**
 * Representação de um objetivo no sistema
 * @author HenriqueGalindo - 119110732
 * Cada objetivo possui tipo, descrição, aderencia e viabilidade
 */
public class Objetivo {

    /** Representação em String do tipo do objetivo, que pode ser GERAL ou ESPECIFICO*/
    private String tipo;
    /** Representação em String da descrição do objetivo*/
    private String descricao;
    /** Valor inteiro entre 1 e 5 que representa a aderencia do objetivo*/
    private int aderencia;
    /** Valor inteiro entre 1 e 5 que representa a viabilidade do objetivo*/
    private int viabilidade;


    /**
    * Constrói objetivo a partir dos parâmetros passados.
    * @param tipo representação em String do tipo do objetivo
    * @param descricao representação em String da descrição do objetivo
    * @param aderencia int que representa a aderencia do objetivo
    * @param viabilidade int que representa a viabilidade do objetivo
    */
    public Objetivo(String tipo, String descricao, int aderencia, int viabilidade) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.aderencia = aderencia;
        this.viabilidade = viabilidade;
    }


    /**
     * Retorna a representação em String do objeto Objetivo
     * @return a representação em String do objeto
     */
    public String toString() {
        int valor = this.aderencia + this.viabilidade;
        return this.tipo + " - " + this.descricao + " - " + valor;
    }

}
