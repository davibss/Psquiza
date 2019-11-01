package com.psquiza.entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Representação de uma atividade no sistema.
 * A atividade possui codigo único, descrição, lista de itens, risco e descrição do risco.
 */
public class Atividade {
    /** Representação em String do código da atividade, no formato: A[número]*/
    private String codigo;
    /** Representação em String da descrição da atividade*/
    private String descricao;
    /** Representação em lista do objeto Item da atividade*/
    private List<Item> resultados;
    /** Representação em String do risco da atividade, só poderá ser: BAIXO, MEDIO, ALTO*/
    private String risco;
    /** Representação em String da descrição do risco da atividade*/
    private String descricaoRisco;

    /**
     * Constrói atividade a partir dos parâmetros passados.
     * Inicia lista de itens.
     * @param codigo representação em String do código da atividade
     * @param descricao representação em String da descrição da atividade
     * @param risco representação em String do risco da atividade
     * @param descricaoRisco representação em String da descrição do risco da atividade
     */
    public Atividade(String codigo, String descricao, String risco, String descricaoRisco) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.resultados = new ArrayList<>();
        this.risco = risco;
        this.descricaoRisco = descricaoRisco;
    }

    /**
     * Cadastra um novo item na lista de itens.
     * Verifica se já existe aquele item.
     * @param nomeItem representação em String do nome do item.
     */
    public void cadastraItem(String nomeItem){
        for (Item item : this.resultados) {
            if (item.getNome().equals(nomeItem)){
                throw new IllegalArgumentException("Item ja cadastrado nesta atividade.");
            }
        }
        this.resultados.add(new Item(nomeItem));
    }

    /**
     * Representação em String do objeto atividade, no formato:
     *  descricao - (risco - descricaoRisco) | statusItem - nomeItem
     * @return String com a representação da atividade.
     */
    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(" | ");
        joiner.add(String.format("%s (%s - %s)",this.descricao, this.risco, this.descricaoRisco));
        this.resultados.forEach((r) -> joiner.add(r.toString()));
        return joiner.toString();
    }

    /**
     * Compara dois objetos e retorna se são iguais.
     * @param o objeto a ser comparado.
     * @return true se for igual, se não, false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atividade atividade = (Atividade) o;
        return Objects.equals(codigo, atividade.codigo);
    }

    /**
     * Retorna representação única do objeto a partir do código.
     * @return representação em String da identificação do objeto.
     */
    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    /**
     * Retorna quantiadde de itens pendentes.
     * @return int representando a quantidade de itens pendentes.
     */
    public int contaItensPendentes() {
        return this.resultados.size() - contaItensRealizados();
    }

    /**
     * Retorna quantiadde de itens realizados.
     * @return int representando a quantidade de itens realizados.
     */
    public int contaItensRealizados() {
        return (int) this.resultados.stream().filter(Item::isRealizado).count();
    }
}
