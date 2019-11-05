package com.psquiza.entidades;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Representação de uma pesquisa no sistema.
 * A pesquisa possui estado que informa se está ativa ou desativa, descrição, campo de interesse.
 * @author José Nestor - 119110608
 */
public class Pesquisa {
    private String codigo;
    /** Representação boleana do estado da pesquisa, true para ativada e false para desativada*/
    private boolean estadoAtivacao;
    /** Representação em String da descrição da pesquisa*/
    private String descricao;
    /** Representação em String do campo de innteresse*/
    private String campoInteresse;
    //Nova implementação - caso de uso 5
    private Problema problema;
    private Map<String, Objetivo> objetivos;

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
        this.problema = new Problema();
        this.objetivos = new HashMap<>();
    }

    public boolean associaProblema(Problema problema) {
        if (this.problema.equals(problema)){
            return false;
        }
        if (!(this.problema.equals(new Problema()))){
            throw new IllegalArgumentException("Pesquisa ja associada a um problema.");
        }
        this.problema = problema;
        return true;
    }

    public boolean desassociaProblema(String problema){
        if (this.problema.getIdProblema() == null){
            return false;
        }else if (!this.problema.getIdProblema().equals(problema)){
            return false;
        }
        this.problema = new Problema();
        return true;
    }

    public boolean asssociaObjetivo(Objetivo objetivo){
        if (this.objetivos.containsValue(objetivo)){
            return false;
        }
        this.objetivos.put(objetivo.getId(), objetivo);
        return true;
    }

    public boolean desassociaObjetivo(String idObjetivo){
        if (!this.objetivos.containsKey(idObjetivo)){
            return false;
        }
        this.objetivos.remove(idObjetivo);
        return true;
    }

    public int getObjetivos(){
        return this.objetivos.size();
    }

    public String maiorObjetivo(){
        //return Collections.max(this.objetivos, (chave1, chave2) -> chave1.compareTo(chave2) * -1);
        return Collections.max(this.objetivos.keySet(), (chave1, chave2) -> chave1.compareTo(chave2) * -1);
    }

    public boolean contemObjetivo(Objetivo objetivo){
        //return this.objetivos.contains(objetivo);
        return this.objetivos.containsValue(objetivo);
    }

    public String getProblema() {
        return this.problema.getIdProblema();
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
