package com.psquiza.comparators;

import com.psquiza.entidades.Pesquisa;

import java.util.Comparator;

/**
 * Classe responsável por implementar uma interface Comparator Pesquisa e
 * usar o método compare para ordenar as pesquisas por maior problema.
 */
public class OrdenaPorIDProblema implements Comparator<Pesquisa> {
    /**
     * Método responsável por comparar 2 objetos do tipo pesquisa.
     * A ordem vai de pesquisas com maior problema até as de menor problema.
     * @param p1 representação da primeira pesquisa passada para comparação.
     * @param p2 representação da segunda pesquisa passada para comparação.
     * @return um inteiro usado no método sort para ordenação.
     */
    @Override
    public int compare(Pesquisa p1, Pesquisa p2) {
        String soString1 = p1.getProblema().getIdProblema().replaceAll("[\\d.]","");
        String soString2 = p2.getProblema().getIdProblema().replaceAll("[\\d.]","");
        if (soString1.equals(soString2)){
            Integer soNumero1 = Integer.parseInt(p1.getProblema().getIdProblema().replaceAll("\\D", ""));
            Integer soNumero2 = Integer.parseInt(p2.getProblema().getIdProblema().replaceAll("\\D", ""));
            return soNumero1.compareTo(soNumero2) * -1;
        }
        return soString1.compareTo(soString2) * -1;
    }
}
