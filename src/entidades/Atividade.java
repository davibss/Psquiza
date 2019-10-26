package com.psquiza.entidades;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Atividade {
    private String codigo;
    private String descricao;
    private Period duracao;
    private List<Item> resultados;
    private String risco;
    private String descricaoRisco;

    public Atividade(String codigo, String descricao, String risco, String descricaoRisco) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.resultados = new ArrayList<>();
        this.risco = risco;
        this.descricaoRisco = descricaoRisco;
    }

    public void cadastraItem(String nomeItem){
        this.resultados.add(new Item(nomeItem));
    }

    @Override
    public String toString() {
        return String.format("%s (%s - %s)",this.descricao, this.risco, this.descricaoRisco);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atividade atividade = (Atividade) o;
        return Objects.equals(codigo, atividade.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    public int contaItensPendentes() {
        int pendentes = 0;
        for (Item item : this.resultados) {
            if (!item.isRealizado()){
                pendentes++;
            }
        }
        return pendentes;
    }


    public int contaItensRealizados() {
        return this.resultados.size() - contaItensPendentes();
    }
}
