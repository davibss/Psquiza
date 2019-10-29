package com.psquiza.entidades;

public class Item {
    private String nome;
    private boolean realizado;

    public Item(String nome) {
        this.nome = nome;
        this.realizado = false;
    }

    public boolean isRealizado() {
        return realizado;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", this.realizado ? "REALIZADO":"PENDENTE", this.nome);
    }
}
