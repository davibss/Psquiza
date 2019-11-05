package com.psquiza.comparators;

import com.psquiza.entidades.Pesquisa;

import java.util.Comparator;

public class OrdenaPorObjetivo implements Comparator<Pesquisa> {
    @Override
    public int compare(Pesquisa p1, Pesquisa p2) {
        if (p1.getObjetivos() > p2.getObjetivos()){
            return -1;
        }else if (p1.getObjetivos() < p1.getObjetivos()){
            return 1;
        }else{
            return p1.maiorObjetivo().compareTo(p2.maiorObjetivo()) * -1;
        }
    }
}
