package com.psquiza.comparators;

import com.psquiza.entidades.Pesquisa;

import java.util.Comparator;

/**
 * Classe responsável por implementar uma interface Comparator<Pesquisa> e
 * usar o método compare para ordenar as pesquisas por quantidade de objetivos e
 * nome do objetivo.
 */
public class OrdenaPorObjetivo implements Comparator<Pesquisa> {
    /**
     * Método responsável por comparar 2 objetos do tipo pesquisa.
     * A ordem vai de pesquisas com mais objetivos até as que tem menos objetivos.
     * Em caso de empate, o objetivo de maior nome virá primeiro.
     * @param p1 representação da primeira pesquisa passada para comparação.
     * @param p2 representação da segunda pesquisa passada para comparação.
     * @return um inteiro usado no método sort para ordenação.
     */
    @Override
    public int compare(Pesquisa p1, Pesquisa p2) {
        if (p1.getObjetivos() > p2.getObjetivos()){
            return -1;
        }else if (p1.getObjetivos() < p1.getObjetivos()){
            return 1;
        }else{
            String soString1 = p1.maiorObjetivo().replaceAll("[\\d.]", "");
            String soString2 = p2.maiorObjetivo().replaceAll("[\\d.]", "");
            if  (soString1.equals(soString2)){
                Integer soNumero1 = Integer.parseInt(p1.maiorObjetivo().replaceAll("\\D", ""));
                Integer soNumero2 = Integer.parseInt(p2.maiorObjetivo().replaceAll("\\D", ""));
                return soNumero1.compareTo(soNumero2) * -1;
            }
            return p1.maiorObjetivo().compareTo(p2.maiorObjetivo()) * -1;
        }
    }
}
