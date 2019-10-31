package com.psquiza.entidades;

import java.util.Objects;

public class Item {
    private String nome;
    private boolean realizado;

    public Item(String nome) {
        this.nome = nome;
        this.realizado = false;
    }

    boolean isRealizado() {
        return realizado;
    }

    String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", this.realizado ? "REALIZADO":"PENDENTE", this.nome);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(nome, item.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
