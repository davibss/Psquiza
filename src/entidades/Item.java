package com.psquiza.entidades;

import java.io.Serializable;
import java.util.Objects;

/**
 * Classe que representa um item contido na atividade.
 * Item possui nome, como atributo único e atributo para representar o status de pendência do item.
 * @author davibss - 119111034
 */
public class Item implements Serializable {
    /** Atributo usado pelo serializable */
    private static final long serialVersionUID = 1L;
    /** Representação em String do nome do item */
    private String nome;
    /** Representação em boolean do status do item */
    private boolean realizado;
    /** Representação em inteiro da duração do item ao ser executado */
    private int duracao;

    /**
     * Constrói o item, a partir do nome passado como parâmetro.
     * @param nome representação em String do nome do item.
     */
    public Item(String nome) {
        this.nome = nome;
        this.realizado = false;
        this.duracao = 0;
    }

    /**
     * Retorna o status do item, true se estiver REALIZADO, se não, false.
     * @return boolean representando o status.
     */
    boolean isRealizado() {
        return realizado;
    }

    /**
     * Retorna o nome do produto.
     * @return representação em String do nome do item.
     */
    String getNome() {
        return nome;
    }

    /**
     * Executa o item a partir da duração passada como parâmetro.
     * Se o atributo realizado for true, lança exceção.
     * @param duracao representação em inteiro da duração do item.
     */
    public void executa(int duracao) {
        if(this.realizado){
            throw new IllegalArgumentException("Item ja executado.");
        }
        this.duracao = duracao;
        this.realizado = true;
    }

    /**
     * Representação completa do item, no formato: status - nome
     * @return representação em String do item.
     */
    @Override
    public String toString() {
        return String.format("%s - %s", this.realizado ? "REALIZADO":"PENDENTE", this.nome);
    }

    /**
     * Compara 2 objetos e retorna se são iguais ou não a partir do nome.
     * @param o objeto a ser comparado.
     * @return true se for igual, false se não for.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(nome, item.nome);
    }

    /**
     * Retorna representação única do objeto a partir do nome.
     * @return representação em String da identificação do objeto.
     */
    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    /**
     * Retorna a representação em String do estado de um item, podendo se "PENDENTE" OU "REALIZADO".
     * @return a representação em String do estado de um item.
     */
    public String estadoItem(){
        return this.realizado ? "REALIZADO" : "PENDENTE";
    }

    /**
     * Retorna a representação em númeto inteiro da duração de um item.
     * @return a representação em númeto inteiro da duração de um item.
     */
    public int getDuracao() {
        return this.duracao;
    }
}
