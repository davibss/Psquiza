package com.psquiza.comparators;

import java.util.Comparator;

public class CompararStringNumero implements Comparator<String> {

    private int reverse;

    public CompararStringNumero(int i) {
        this.reverse = i;
    }

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
