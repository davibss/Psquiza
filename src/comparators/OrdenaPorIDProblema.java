package com.psquiza.comparators;

import com.psquiza.entidades.Pesquisa;

import java.util.Comparator;

public class OrdenaPorIDProblema implements Comparator<Pesquisa> {
    @Override
    public int compare(Pesquisa p1, Pesquisa p2) {
        return p1.getProblema().compareTo(p2.getProblema()) * -1;
    }
}
