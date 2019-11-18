package com.psquiza.entidades;

import java.io.Serializable;
import java.util.Objects;

/**
 * Classe que representa um problema no sistema.
 * O problema possui codigo, descrição e viabilidade.
 */
public class Problema implements Serializable {
    /** Atributo usado pelo serializable */
    private static final long serialVersionUID = 1L;
    /** Representação em String do código único de problema. */
    private String codigo;
    /** Representação em String da descrição do problema. */
    private String descricao;
    /** Representação em inteiro da viabilidade do problema. */
    private int viabilidade;

    /**
     * Constrói um problema a partir dos parâmetros passados.
     * @param codigo representação em String do código do problema.
     * @param descricao representação em String da descrição do problema.
     * @param viabilidade representação em inteiro da viabilidade do problema.
     */
    public Problema(String codigo, String descricao, int viabilidade) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.viabilidade = viabilidade;
    }
    /**
     * Retorna a representação em String de um problema.
     * @return a representação em String de um problema.
     */
    public String toString() {
        return String.format("%s - %s - %d",this.codigo, this.descricao, this.viabilidade);
    }

    /**
     * Retorna o ID do problema.
     * @return a representação em String do problema.
     */
    public String getIdProblema() {
        return this.codigo;
    }

    /**
     * Retorna a descrição do problema.
     * @return a representação em String da descrição do problema.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Método que verifica se dois objetos são iguais.
     * @param o objeto a ser verificado.
     * @return true se os objetos forem iguais, false se não forem.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Problema problema = (Problema) o;
        return Objects.equals(codigo, problema.codigo);
    }

    /**
     * Gera um número único para o problema a partir de seu código.
     * @return a representação em inteiro do código único de identificação.
     */
    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}
