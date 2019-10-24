package com.psquiza;

import easyaccept.EasyAccept;

public class FacadePsquiza {

    public static void main(String[] args) {
        args = new String[]{"com.psquiza.FacadePsquiza", "tests/accept-tests/use_case_1.txt"};
//        args = new String[]{"com.psquiza.FacadePsquiza", "tests/accept-tests/use_case_1.txt",
//                                                         "tests/accept-tests/use_case_2.txt",
//                                                         "tests/accept-tests/use_case_3.txt",
//                                                         "tests/accept-tests/use_case_4.txt"};
        EasyAccept.main(args);
    }

    // MÉTODOS AQUI
}
