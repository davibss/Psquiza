package com.psquiza.controllers;

import com.psquiza.entidades.Atividade;
import com.psquiza.entidades.Objetivo;
import com.psquiza.entidades.Problema;

import java.util.*;

public class ControllerAtividade {

//    private Map<String, Problema> problemas;
//    private int idProblema;
//    private Map<String, Objetivo> objetivos;
//    private int idObjetivo;

    private Map<String, Atividade> atividades;
    private int atividadesCriadas;

    public ControllerAtividade() {
//        this.problemas = new HashMap<>();
//        this.idProblema = 1;
//        this.objetivos = new HashMap<>();
//        this.idObjetivo = 1;

        this.atividades = new HashMap<>();
        this.atividadesCriadas = 1;
    }

    //CdU 3
//    public void cadastrarProblema(String descricao, int viabilidade) {
//        verificaValidadeProblema(descricao, viabilidade);
//        String chave = "P"+(this.idProblema);
//        this.problemas.put(chave, new Problema(descricao, viabilidade));
//        this.idProblema += 1;
//    }
//
//    private void verificaValidadeProblema(String descricao, int viabilidade) {
//        verificaVazioNulo(descricao, "Descricao");
//        if(viabilidade < 1 || viabilidade > 5) {
//            throw new IllegalArgumentException("Valor invalido de viabilidade.");
//        }
//    }
//
//    public void apagarProblema(String codigo) {
//        verificaVazioNulo(codigo, "codigo");
//        if(!this.problemas.containsKey(codigo)) {
//            throw new IllegalArgumentException("Problema nao encontrado");
//        }
//        this.problemas.remove(codigo);
//    }
//
//    public String exibeProblema(String codigo) {
//        if(!this.problemas.containsKey(codigo)) {
//            throw new IllegalArgumentException("Problema nao encontrado");
//        }
//        return codigo + " - " + this.problemas.get(codigo).toString();
//    }

//    public void cadastrarObjetivo(String tipo, String descricao, int aderencia, int viabilidade) {
//        verificaValidadeObjetivo(tipo, descricao, aderencia, viabilidade);
//        String chave = "O"+(this.idObjetivo);
//        this.objetivos.put(chave, new Objetivo(tipo, descricao, aderencia, viabilidade));
//        this.idObjetivo += 1;
//    }
//
//    private void verificaValidadeObjetivo(String tipo, String descricao, int aderencia, int viabilidade) {
//        verificaVazioNulo(descricao, "Descricao");
//        verificaVazioNulo(tipo, "Tipo");
//        if(!tipo.equals("GERAL") || tipo.equals("ESPECIFICO")) {
//            throw new IllegalArgumentException("Valor invalido de tipo.");
//        }
//        if(aderencia < 1 || aderencia > 5) {
//            throw new IllegalArgumentException("Valor invalido de aderencia.");
//        }
//        if(viabilidade < 1 || viabilidade > 5) {
//            throw new IllegalArgumentException("Valor invalido de viabilidade.");
//        }
//    }
//
//    public void apagarObjetivo(String codigo) {
//        verificaVazioNulo(codigo, "codigo");
//        if(!this.objetivos.containsKey(codigo)) {
//            throw new IllegalArgumentException("Objetivo nao encontrado");
//        }
//        this.objetivos.remove(codigo);
//    }
//
//    public String exibeObjetivo(String codigo) {
//        if(!this.objetivos.containsKey(codigo)) {
//            throw new IllegalArgumentException("Objetivo nao encontrado");
//        }
//        return codigo + " - " + this.objetivos.get(codigo).toString();
//    }

    //CdU 4
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
