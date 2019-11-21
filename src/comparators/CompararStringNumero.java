package com.psquiza.comparators;

import java.util.Comparator;
/**
 *  Classe responsavel por implementar uma interface Comparator, e
 *  um metodo compare para ordenar Pesquisas, Atividades, Problemas e Objetivos
 *  em ordem anti-lexicografica.
 *
 */
public class CompararStringNumero implements Comparator<String> {

    private int reverse;

    /**
     * Constroi o objeto Comparator para ser utilizado na busca.
     *
     * @param i Inteiro utilizado na ordenação da busca.
     */
    public CompararStringNumero(int i) {
        this.reverse = i;
    }

    /**
     * Metodo responsavel por comparar duas representacoes do codigo de
     * Pesquisas, Problemas, Objetivos ou Atividades em String.
     *
     * @param s1 String representando o primeiro codigo passado na comparacao.
     * @param s2 String representando o segundo codigo passado na comparacao.
     * @return Valor Inteiro utilizado no metodo sort para ordenacao.
     */
    @Override
    public int compare(String s1, String s2) {
        String soString1 = s1.replaceAll("[\\d.]","");
        String soString2 = s2.replaceAll("[\\d.]","");
        if (soString1.equals(soString2)){
            Integer soNumero1 = Integer.parseInt(s1.replaceAll("\\D", ""));
            Integer soNumero2 = Integer.parseInt(s2.replaceAll("\\D", ""));
            return soNumero1.compareTo(soNumero2) * this.reverse;
        }
        return soString1.compareTo(soString2) * this.reverse;
    }
}
