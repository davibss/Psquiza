package com.psquiza;

import com.psquiza.controllers.ControllerAtividade;
import easyaccept.EasyAccept;

public class FacadePsquiza {

    private ControllerAtividade controllerAtividade;

    public static void main(String[] args) {
        args = new String[]{"com.psquiza.FacadePsquiza", "tests/accept-tests/use_case_4.txt"};
//        args = new String[]{"com.psquiza.FacadePsquiza", "tests/accept-tests/use_case_1.txt",
//                                                         "tests/accept-tests/use_case_2.txt",
//                                                         "tests/accept-tests/use_case_3.txt",
//                                                         "tests/accept-tests/use_case_4.txt"};
        EasyAccept.main(args);
    }

    public FacadePsquiza(){
        controllerAtividade = new ControllerAtividade();
    }

    // MÉTODOS AQUI

    //Caso de uso 1(-)


    //Caso de uso 2(-)


    //Caso de uso 3 (Henrique)


    //Caso de uso 4 (Davi)

    public void cadastraAtividade(String descricao,  String risco, String descricaoRisco){
        controllerAtividade.cadastrarAtividades(descricao, risco, descricaoRisco);
    }

    public void apagaAtividade(String codigo){
        controllerAtividade.apagarAtividade(codigo);
    }

    public void cadastraItem(String codigo, String nomeItem){
        controllerAtividade.cadastraItem(codigo, nomeItem);
    }

    public String exibeAtividade(String codigo){
        return controllerAtividade.exibeAtividade(codigo);
    }

    public int contaItensPendentes(String codigo){
        return controllerAtividade.contaItensPendentes(codigo);
    }

    public int contaItensRealizados(String codigo){
        return controllerAtividade.contaItensRealizados(codigo);
    }
}
