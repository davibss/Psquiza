package com.psquiza.entidades;

import java.util.Objects;

/**
 * Classe que representa um item contido na atividade.
 * Item possui nome, como atributo único e atributo para representar o status de pendência do item.
 * @author davibss - 119111034
 */
public class Item {
    /** Representação em String do nome do item */
    private String nome;
    /** Representação em boolean do status do item */
    private boolean realizado;

    /**
     * Constrói o item, a partir do nome passado como parâmetro.
     * @param nome representação em String do nome do item.
     */
    public Item(String nome) {
        this.nome = nome;
        this.realizado = false;
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

    public void executa() {
        if(this.realizado){
            throw new IllegalArgumentException("Item ja executado.");
        }
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
}
