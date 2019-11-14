package com.psquiza.comparators;

import java.util.Comparator;

public class AtividadeMaisAntiga implements Comparator<String> {
    @Override
    public int compare(String a, String b) {
        if(Integer.parseInt(a.substring(0)) < Integer.parseInt(b.substring(0))) {
            return -1;
        }
        if(Integer.parseInt(a.substring(0)) > Integer.parseInt(b.substring(0))) {
            return -1;
        }
        return 0;
    }
}
