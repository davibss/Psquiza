package com.psquiza.entidades;

import com.psquiza.controllers.*;

import java.util.ArrayList;
import java.util.List;

/**
 *  Classe responsavel por realizar a busca e contagem de busca de um termo no sistema. Essa busca eh
 *  feita nos controllers especificos de Atividade, Pesquisa, Pesquisador, Objetivo e Problema.
 *
 */
public class Buscador {
    /**
     *  Controller de Atividades, onde sera feita a busca do termo entre as descricoes e descricoes de risco de atividades.
     */
    private ControllerAtividade controleAtividade;
    /**
     *  Controller de Pesquisas, onde sera feita a busca do termo entre as descricoes e campos de interesse de pesquisas.
     */
    private ControllerPesquisa controllerPesquisa;
    /**
     * Controller de Pesquisadores, onde sera feita a busca do termo entre as biografias de pesquisadores.
     */
    private ControllerPesquisador controllerPesquisador;
    /**
     * Controller de Objetivos, onde sera feita a busca do termo entre as descricoes de objetivos.
     */
    private ControllerObjetivo controllerObjetivo;
    /**
     *  Controller de Problemas, onde sera feita a busca do termo entre as descricoes de problemas.
     */
    private ControllerProblema controllerProblema;

    /**
     *  Constroi o Buscador, a partir dos Controllers onde a busca sera realizada.
     *
     * @param controleAtividade Controller de Atividades, onde sera feita a busca do termo entre as descricoes e descricoes de risco de atividades.
     * @param controllerPesquisa Controller de Pesquisas, onde sera feita a busca do termo entre as descricoes e campos de interesse de pesquisas.
     * @param controllerPesquisador Controller de Pesquisadores, onde sera feita a busca do termo entre as biografias de pesquisadores.
     * @param controllerObjetivo Controller de Objetivos, onde sera feita a busca do termo entre as descricoes de objetivos.
     * @param controllerProblema Controller de Problemas, onde sera feita a busca do termo entre as descricoes de problemas.
     */
    public Buscador(ControllerAtividade controleAtividade, ControllerPesquisa controllerPesquisa, ControllerPesquisador controllerPesquisador, ControllerObjetivo controllerObjetivo, ControllerProblema controllerProblema) {
        this.controleAtividade = controleAtividade;
        this.controllerPesquisa = controllerPesquisa;
        this.controllerPesquisador = controllerPesquisador;
        this.controllerObjetivo = controllerObjetivo;
        this.controllerProblema = controllerProblema;
    }

    /**
     *  Verifica se o termo a ser buscado eh nulo ou vazio, lancando uma excecao caso seja.
     *
     * @param termo String termo a ser utilizado em uma busca.
     */
    private void verificaTermo(String termo){
        if (termo == null){
            throw new NullPointerException("Campo termo nao pode ser nulo ou vazio.");
        } else if (termo.trim().equals("")){
            throw new IllegalArgumentException("Campo termo nao pode ser nulo ou vazio.");
        }
    }

    /**
     * Faz uma busca em Pesquisas, Pesquisadores, Problemas, Objetivos e Atividades por um termo. Retornando a lista
     * contendo os resultados da busca.
     *
     * @param termo String representando um termo a ser buscado no Sistema.
     * @return Lista de Strings contendo todos resultados da busca pelo termo no Sistema Psquiza.
     */
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

    /**
     *  Realiza uma busca geral pelo termo no sistema.
     *  Faz uma busca generica por um termo no Sistema, em seguida concatena os resultados em uma unica String.
     *
     * @param termo String representando um termo a ser buscado no Sistema Psquiza.
     * @return String representando todos os resultados da busca do termo.
     */
    public String buscaGeral(String termo){
        List<String> buscados = buscaGenerica(termo);
        return String.join(" | ", buscados);
    }

    /**
     *  Faz uma busca especifica por um resultado em determinada posicao. Faz uma busca generica pelo termo, em seguida verifica
     *  se a posicao a ser acessada eh valida, caso seja retorna o resultado daquela detrminada posicao.
     *
     * @param termo String representando um termo a ser buscado no Sistema Psquiza.
     * @param posicao Inteiro representando uma posicao a ser acessado um resultado na lista de resultados.
     * @return String representando o resultado de determinada posicao na busca.
     */
    public String buscaPorNumero(String termo, int posicao){
        verificaTermo(termo);
        if( posicao < 0){
            throw new IllegalArgumentException("Numero do resultado nao pode ser negativo");
        }
        List<String> buscados = buscaGenerica(termo);
        if (posicao > buscados.size()){
            throw new IllegalArgumentException("Entidade nao encontrada.");
        }
        return buscados.get(posicao-1);
    }

    /**
     *  Conta o numero de resultados para a busca de determinado termo no sistema. Faz uma busca generica pelo termo,
     *  em seguida retorna o tamanho da lista de resultados encontrados pela busca.
     *
     * @param termo String representando um termo a ser buscado no Sistema Psquiza.
     * @return Inteiro representando o numero de resultados encontrados pela busca do termo.
     */
    public int contaResultados(String termo){
        verificaTermo(termo);
        List<String> buscados = buscaGenerica(termo);
        if (buscados.size() == 0){
            throw new IllegalArgumentException("Nenhum resultado encontrado");
        }
        return buscados.size();
    }
}
