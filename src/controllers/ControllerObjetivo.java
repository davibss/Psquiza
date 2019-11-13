package com.psquiza.controllers;

import com.psquiza.entidades.Objetivo;
import com.psquiza.verificadores.Verificador;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

/**
 * Controller do objeto Objetivo
 * @author HenriqueGalindo - 119110732
 */
public class ControllerObjetivo {
    /** Mapa contendo os objetos Objetivo onde a chave é o código e o valor é o objetivo*/
    private Map<String, Objetivo> objetivos;
    /** Contador que indica o id do próximo objetivo a ser cadastrado*/
    private int idObjetivo;

    /**
     * Constrói o controller e inicializa o mapa e o contador
     * idObjetivo inicia como 1, pois este é o id do primeiro objetivo cadastrado
     */
    public ControllerObjetivo() {
        this.objetivos = new HashMap<>();
        this.idObjetivo = 1;
    }

    /**
     * Cadastra objetivos no mapa de objetivos
     * Verifica se os parâmetros são vazios ou nulos
     * Verifica se tipo é GERAL ou ESPECIFICO
     * Verifica se aderencia e viabilidade estão entre 1 e 5
     * Gera o código do objetivo cadastrado
     * @param tipo representação em String do tipo do objetivo [GERAL ou ESPECIFICO]
     * @param descricao representação em String da decricao do objetivo
     * @param aderencia valor inteiro entre 1 e 5 que representa a aderencia do objetivo
     * @param viabilidade valor inteiro entre 1 e 5 que representa a viabilidade do objetivo
     */
    public void cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade) {
        verificaValidadeObjetivo(tipo, descricao, aderencia, viabilidade);
        String chave = "O"+(this.idObjetivo);
        this.objetivos.put(chave, new Objetivo(chave, tipo, descricao, aderencia, viabilidade));
        this.idObjetivo += 1;
    }

    /**
     * Verifica se os parametros são vazios ou nulos
     * Verifica se tipo é GERAL ou ESPECIFICO e se aderencia e viabilidade estão entre 1 e 5
     * @param tipo objeto String sob teste
     * @param descricao objeto String sob teste
     * @param aderencia int sob teste
     * @param viabilidade int sob teste
     */
    private void verificaValidadeObjetivo(String tipo, String descricao, int aderencia, int viabilidade) {
        Verificador.verificaVazioNulo(descricao, "descricao");
        Verificador.verificaVazioNulo(tipo, "tipo");
        if(!(tipo.equals("GERAL") || tipo.equals("ESPECIFICO"))) {
            throw new IllegalArgumentException("Valor invalido de tipo.");
        }
        if(aderencia < 1 || aderencia > 5) {
            throw new IllegalArgumentException("Valor invalido de aderencia");
        }
        if(viabilidade < 1 || viabilidade > 5) {
            throw new IllegalArgumentException("Valor invalido de viabilidade.");
        }
    }

    /**
     * Remove objetivos do mapa de objetivos
     * Verifica se o parâmetro passado é vazio ou nulo
     * Verifica se o mapa contém o objetivo que deve ser removido
     * @param codigo representação em String do código do objetivo a ser removido
     */
    public void apagarObjetivo(String codigo) {
        Verificador.verificaVazioNulo(codigo, "codigo");
        if(!this.objetivos.containsKey(codigo)) {
            throw new IllegalArgumentException("Objetivo nao encontrado");
        }
        this.objetivos.remove(codigo);
    }

    /**
     * Retorna a representação em String de um objeto Objetivo
     * Verifica se o parâmetro passado é vazio ou nulo
     * Verifica se o mapa contém o objetivo que deve ser exibido
     * @param codigo representação em String do código do objetivo a ser exibido
     * @return representação em String de um objeto Objetivo
     */
    public String exibeObjetivo(String codigo) {
        Verificador.verificaVazioNulo(codigo, "codigo");
        if(!this.objetivos.containsKey(codigo)) {
            throw new IllegalArgumentException("Objetivo nao encontrado");
        }
        //return codigo + " - " + this.objetivos.get(codigo).toString();
        return this.objetivos.get(codigo).toString();
    }

    public Objetivo getObjetivo(String idObjetivo) {
        return this.objetivos.get(idObjetivo);
    }

    public List<String> buscaObjetivo(String termo){
        List<String> found = new ArrayList<>();
        objetivos.entrySet().stream().filter(entry -> entry.getValue().getDescricao().contains(termo)).
                sorted(Map.Entry.comparingByKey(Comparator.reverseOrder())).
                forEach(entry -> found.add(entry.getKey() + ": " + entry.getValue().getDescricao()));
        return found;
    }

    public void grava(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeObject(this.objetivos);
        objectOutputStream.writeObject(this.idObjetivo);
    }

    public void carrega(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        this.objetivos = (Map<String, Objetivo>) objectInputStream.readObject();
        this.idObjetivo = (int) objectInputStream.readObject();
    }
}
