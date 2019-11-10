package com.psquiza.entidades;

import com.psquiza.controllers.*;

import java.util.ArrayList;
import java.util.List;

public class Buscador {
    private ControllerAtividade controleAtividade;
    private ControllerPesquisa controllerPesquisa;
    private ControllerPesquisador controllerPesquisador;
    private ControllerObjetivo controllerObjetivo;
    private ControllerProblema controllerProblema;

    public Buscador(ControllerAtividade controleAtividade, ControllerPesquisa controllerPesquisa, ControllerPesquisador controllerPesquisador, ControllerObjetivo controllerObjetivo, ControllerProblema controllerProblema) {
        this.controleAtividade = controleAtividade;
        this.controllerPesquisa = controllerPesquisa;
        this.controllerPesquisador = controllerPesquisador;
        this.controllerObjetivo = controllerObjetivo;
        this.controllerProblema = controllerProblema;
    }
    private void verificaTermo(String termo){
        if (termo == null){
            throw new NullPointerException("Campo termo nao pode ser nulo ou vazio.");
        } else if (termo.trim().equals("")){
            throw new IllegalArgumentException("Campo termo nao pode ser nulo ou vazio.");
        }
    }
    private List<String> buscaGenerica(String termo){
        verificaTermo(termo);
        termo = termo.toLowerCase();
        List<String> buscados = new ArrayList<>();
        buscados.addAll(controllerPesquisa.buscaPesquisa(termo));
        buscados.addAll(controllerPesquisador.buscaPesquisador(termo));
        buscados.addAll(controllerProblema.buscaProblema(termo));
        buscados.addAll(controllerObjetivo.buscaObjetivo(termo));
        buscados.addAll(controleAtividade.buscaAtividade(termo));
        return buscados;
    }
    public String buscaGeral(String termo){
        List<String> buscados = buscaGenerica(termo);
        return String.join(" | ", buscados);
    }
    public String buscaPorNumero(String termo, int posicao){
        verificaTermo(termo);
        if( posicao < 0){
            throw new IllegalArgumentException("Numero do resultado nao pode ser negativo");
        }
        List<String> buscados = buscaGenerica(termo);
        if (posicao > buscados.size()){
            throw new IllegalArgumentException("Entidade nao encontrada.");
        }
        //buscados.forEach(System.out::println);
        return buscados.get(posicao-1);
    }
    public int contaResultados(String termo){
        List<String> buscados = buscaGenerica(termo);
        if (buscados.size() == 0){
            throw new IllegalArgumentException("Nenhum resultado encontrado");
        }
        return buscados.size();
    }
}
