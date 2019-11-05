package com.psquiza.entidades;

import java.util.Objects;

/**
 * Representação de uma pesquisa no sistema.
 * A pesquisa possui estado que informa se está ativa ou desativa, descrição, campo de interesse.
 * @author José Nestor - 119110608
 */
public class Pesquisa  {
    private String codigo;
    /** Representação boleana do estado da pesquisa, true para ativada e false para desativada*/
    private boolean estadoAtivacao;
    /** Representação em String da descrição da pesquisa*/
    private String descricao;
    /** Representação em String do campo de innteresse*/
    private String campoInteresse;

    /**
     * Constrói uma pesquisa através dos parâmetros
     * @param estadoAtivacao representação boleana do estado da pesquisa
     * @param descricao representação em String da descrição da pesquisa
     * @param campoInteresse representação em String do campo de innteresse
     */
    public Pesquisa(String codigo, boolean estadoAtivacao, String descricao, String campoInteresse){
        this.codigo = codigo;
        this.estadoAtivacao = estadoAtivacao;
        this.descricao = descricao;
        this.campoInteresse = campoInteresse;
    }

    /**
     * Retorna uma String que representa uma pesquisa.
     * A representação segue o formato: " - descrição - campo de interesse".
     *
     * @return Representação em String de uma pesquisa.
     */
    @Override
    public String toString() {
        //return " - " + descricao + " - " +campoInteresse;
        return String.format("%s - %s - %s",this.codigo,this.descricao,this.campoInteresse);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pesquisa pesquisa = (Pesquisa) o;
        return Objects.equals(codigo, pesquisa.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    public String getCodigo() {
        return this.codigo;
    }

    /**
     * Define um valor String para o atributo campoInteresse
     * @param campoInteresse String representando novo valor do atributo campoInteresse
     */
    public void setCampoInteresse(String campoInteresse) {
        this.campoInteresse = campoInteresse;
    }

    /**
     * Define um valor String para o atributo descricao
     * @param descricao String representando novo valor do atributo descricao
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Define um valor String para o atributo estadoAtivacao
     * @param estadoAtivacao String representando novo valor do atributo estadoAtivacao
     */
    public void setEstadoAtivacao(boolean estadoAtivacao) {
        this.estadoAtivacao = estadoAtivacao;
    }

    /**
     * Retorna um valor boleano que representa o estado de ativação da pesquisa
     * @return A representação boleana do estado da pesquisa
     */
    public boolean estadoAtivacao() {
        return estadoAtivacao;
    }


}
