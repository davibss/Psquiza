package com.psquiza.comparators;

import com.psquiza.entidades.Pesquisa;

import java.util.Comparator;

public class OrdenarPesquisa implements Comparator<Pesquisa> {
    @Override
    public int compare(Pesquisa p1, Pesquisa p2) {
        String soString1 = p1.getCodigo().replaceAll("[\\d.]","");
        String soString2 = p2.getCodigo().replaceAll("[\\d.]","");
        if (soString1.equals(soString2)){
            Integer soNumero1 = Integer.parseInt(p1.getCodigo().replaceAll("\\D", ""));
            Integer soNumero2 = Integer.parseInt(p2.getCodigo().replaceAll("\\D", ""));
            return soNumero1.compareTo(soNumero2) * -1;
        }
        return soString1.compareTo(soString2) * -1;
    }
}
