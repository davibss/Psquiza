package com.psquiza.controllers;

import com.psquiza.entidades.Atividade;
import javafx.util.Pair;

import java.util.*;

public class ControllerAtividade {

    private Map<String, Atividade> atividades;
    private int atividadesCriadas;

    public ControllerAtividade() {
        this.atividades = new HashMap<>();
        this.atividadesCriadas = 1;
    }

    public void cadastrarAtividades(String descricao, String risco, String descricaoRisco) {
        verificaValidadeAtividade(descricao, risco, descricaoRisco);
        String chave = "A"+(this.atividadesCriadas);
        this.atividades.put(chave, new Atividade(chave, descricao, risco, descricaoRisco));
        this.atividadesCriadas+=1;
    }

    private void verificaValidadeAtividade(String descricao, String risco, String descricaoRisco) {
        verificaVazioNulo(descricao, "Descricao");
        verificaVazioNulo(risco, "nivelRisco");
        verificaVazioNulo(descricaoRisco, "descricaoRisco");
        verificaValidadeRisco(risco);
    }

    private void verificaValidadeRisco(String risco) {
        List<String> riscos = Arrays.asList("BAIXO","MEDIO","ALTO");
        if (!riscos.contains(risco)){
            throw new IllegalArgumentException("Valor invalido do nivel do risco.");
        }
    }

    private void verificaVazioNulo(String atributo, String nomeAtributo) {
        StringJoiner joiner = new StringJoiner(" ");

        if (!nomeAtributo.equals("item")) {
            joiner.add("Campo").add(nomeAtributo);
        }else{
            joiner.add("Item");
        }

        joiner.add("nao pode ser nulo ou vazio.");
        if (atributo == null || atributo.equals("")){
            throw new IllegalArgumentException(joiner.toString());
        }
    }

    public void apagarAtividade(String codigo) {
        verificaVazioNulo(codigo, "codigo");
        verificaExistenciaAtividade(codigo);
        atividades.remove(codigo);
    }

    private void verificaExistenciaAtividade(String codigo) {
        if (!atividades.containsKey(codigo)){
            throw new IllegalArgumentException("Atividade nao encontrada");
        }
    }

    public void cadastraItem(String codigo, String nomeItem) {
        verificaValidadeItem(codigo, nomeItem);
        verificaExistenciaAtividade(codigo);
        this.atividades.get(codigo).cadastraItem(nomeItem);
    }

    private void verificaValidadeItem(String codigo, String nomeItem) {
        verificaVazioNulo(codigo, "codigo");
        verificaVazioNulo(nomeItem, "item");
    }

    public String exibeAtividade(String codigo) {
        verificaVazioNulo(codigo,"codigo");
        verificaExistenciaAtividade(codigo);
        return this.atividades.get(codigo).toString();
    }

    public int contaItensPendentes(String codigo) {
        verificaVazioNulo(codigo,"codigo");
        verificaExistenciaAtividade(codigo);
        return (this.atividades.get(codigo).contaItensPendentes());
    }

    public int contaItensRealizados(String codigo) {
        verificaVazioNulo(codigo,"codigo");
        verificaExistenciaAtividade(codigo);
        return (this.atividades.get(codigo).contaItensRealizados());
    }
}
