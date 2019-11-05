package com.psquiza.controllers;

import com.psquiza.associacoes.CompPesquisaObjetivo;
import com.psquiza.comparators.OrdenaPorIDProblema;
import com.psquiza.entidades.Pesquisa;
import com.psquiza.verificadores.Verificador;

import java.util.*;

public class ControllerAssociacaoPesquisa {
    private ControllerPesquisa controllerPesquisa;
    private Map<String, CompPesquisaObjetivo> associacaoPesquisa;

    public ControllerAssociacaoPesquisa(ControllerPesquisa controllerPesquisa) {
        this.controllerPesquisa = controllerPesquisa;
        associacaoPesquisa = new HashMap<>();
    }

    public boolean associaProblema(String pesquisa, String problema) {
        Verificador.verificaVazioNulo(pesquisa, "idPesquisa");
        Verificador.verificaVazioNulo(problema, "idProblema");
        controllerPesquisa.verificaPesquisa(pesquisa);
        if (!controllerPesquisa.pesquisAtiva(pesquisa)){
            throw new IllegalArgumentException("Pesquisa desativada.");
        }
        CompPesquisaObjetivo compPesquisaObjetivo = new CompPesquisaObjetivo();
        if (!associacaoPesquisa.containsKey(pesquisa)){
            associacaoPesquisa.put(pesquisa, compPesquisaObjetivo);
        }
        return associacaoPesquisa.get(pesquisa).associaProblema(problema);
    }

    public boolean desassociaProblema(String pesquisa, String problema) {
        Verificador.verificaVazioNulo(pesquisa, "idPesquisa");
        Verificador.verificaVazioNulo(problema, "idProblema");
        controllerPesquisa.verificaPesquisa(pesquisa);
        if (!controllerPesquisa.pesquisAtiva(pesquisa)){
            throw new IllegalArgumentException("Pesquisa desativada.");
        }
        return associacaoPesquisa.get(pesquisa).desassociaProblema(problema);
    }


    public boolean associaObjetivo(String pesquisa, String objetivo) {
        Verificador.verificaVazioNulo(pesquisa,"idPesquisa");
        Verificador.verificaVazioNulo(objetivo,"idObjetivo");
        controllerPesquisa.verificaPesquisa(pesquisa);
        if (!controllerPesquisa.pesquisAtiva(pesquisa)){
            throw new IllegalArgumentException("Pesquisa desativada.");
        }
        for (String p : this.associacaoPesquisa.keySet()){
            if (!p.equals(pesquisa)){
                if (this.associacaoPesquisa.get(p).contemObjetivo(objetivo)){
                    throw new IllegalArgumentException("Objetivo ja associado a uma pesquisa.");
                }
            }
        }
        //CompPesquisaObjetivo compPesquisaObjetivo = new CompPesquisaObjetivo();
        if (!associacaoPesquisa.containsKey(pesquisa)){
            associacaoPesquisa.put(pesquisa, new CompPesquisaObjetivo());
        }
        return associacaoPesquisa.get(pesquisa).asssociaObjetivo(objetivo);
    }

    public boolean desassociaObjetivo(String pesquisa, String objetivo) {
        Verificador.verificaVazioNulo(pesquisa,"idPesquisa");
        Verificador.verificaVazioNulo(objetivo,"idObjetivo");
        controllerPesquisa.verificaPesquisa(pesquisa);
        if (!controllerPesquisa.pesquisAtiva(pesquisa)){
            throw new IllegalArgumentException("Pesquisa desativada.");
        }
        return associacaoPesquisa.get(pesquisa).desassociaObjetivo(objetivo);
    }

    List<String> ordenaPorIDProblema(){
        ArrayList<String> lista = new ArrayList<>();
//        associacaoPesquisa.entrySet().stream().sorted(Map.Entry.comparingByValue(
//                (cpo1, cpo2) -> cpo1.getProblema().compareTo(cpo2.getProblema()) * -1
//        )).forEach(a -> lista.add(a.getKey()));
//        associacaoPesquisa.entrySet().stream().sorted(Map.Entry.comparingByValue(new OrdenaPorIDProblema())).
//                forEach(a -> lista.add(a.getKey()));
        return lista;
    }

    List<String> ordenaPorObjetivos(){
        ArrayList<String> lista = new ArrayList<>();
        associacaoPesquisa.entrySet().stream().sorted(Map.Entry.comparingByValue(
                (cpo1, cpo2) -> {
                    if (cpo1.getObjetivos() > cpo2.getObjetivos()){
                        return -1;
                    }else if (cpo1.getObjetivos() < cpo1.getObjetivos()){
                        return 1;
                    }else{
                        return cpo1.maiorObjetivo().compareTo(cpo2.maiorObjetivo()) * -1;
                    }
                }
        )).forEach(a -> lista.add(a.getKey()));
        return lista;
    }
}
