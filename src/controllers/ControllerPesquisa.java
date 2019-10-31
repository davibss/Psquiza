package com.psquiza.controllers;


import com.psquiza.entidades.Pesquisa;

import java.util.*;

public class ControllerPesquisa {

    private Map<String, Pesquisa> pesquisas;

    public ControllerPesquisa(){
        this.pesquisas = new HashMap<>();
    }

    public String cadastrarPesquisa(String descricao, String campoInteresse){

       if(descricao == null || descricao.equals("")){
            throw new NullPointerException("Descricao nao pode ser nula ou vazia.");
       }

        String[] verifica = campoInteresse.split(",");
        if (verifica.length > 4 || verifica[0].trim().length() < 3 || campoInteresse.length() > 255){
            throw new IllegalArgumentException ("Formato do campo de interesse invalido.");
        }
        for(String topico : verifica){
            if (topico == null || topico.equals("") || topico.equals(" ")){
               throw new IllegalArgumentException ("Formato do campo de interesse invalido.");
            }
        }
        int valorInt = 1;

        for(String codigo : this.pesquisas.keySet()){
            if(codigo.substring(0,3).equals(verifica[0].trim().substring(0, 3).toUpperCase())){
                valorInt = Integer.parseInt(String.valueOf(codigo.charAt(3)));
                valorInt += 1;
            }
        }
        String codigo = verifica[0].trim().substring(0, 3).toUpperCase() + valorInt;
        this.pesquisas.put(codigo, new Pesquisa(true, descricao, campoInteresse));
        return codigo;
    }

    public void alterarPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo){
            if(!pesquisas.containsKey(codigo)){
                throw new RuntimeException("Pesquisa nao encontrada.");
            }
            else if(novoConteudo == null || (novoConteudo.equals("")) && conteudoASerAlterado.equals("DESCRICAO")){
                throw new RuntimeException("Descricao nao pode ser nula ou vazia.");
            }

            else if(novoConteudo == null || (novoConteudo.equals("")) && conteudoASerAlterado.equals("CAMPO")) {
                throw new RuntimeException("Formato do campo de interesse invalido.");
            } 
            else if(!pesquisas.get(codigo).estadoAtivacao()){
                throw new RuntimeException("Pesquisa desativada.");
            }
            else if (conteudoASerAlterado.equals("DESCRICAO")) {
                pesquisas.get(codigo).setDescricao(novoConteudo);
            }
            else if(conteudoASerAlterado.equals("CAMPO")) {
                pesquisas.get(codigo).setCampoInteresse(novoConteudo);
            }
            else{
                throw new RuntimeException("Nao e possivel alterar esse valor de pesquisa.");
            }
    }

    public void encerrarPesquisa(String codigo, String motivo){
        verificaVazioNulo(motivo,"Motivo");
        if (!pesquisas.containsKey(codigo)){
            throw new NullPointerException("Pesquisa nao encontrada.");
        }
        if(!pesquisas.get(codigo).estadoAtivacao()){
              throw new RuntimeException("Pesquisa desativada.");
        }
           pesquisas.get(codigo).setEstadoAtivacao(false);
    }

    public void ativarPesquisa(String codigo){
        if (!pesquisas.containsKey(codigo)){
            throw new NullPointerException("Pesquisa nao encontrada.");
        }
        if(pesquisas.get(codigo).estadoAtivacao()) {
            throw new RuntimeException("Pesquisa ja ativada.");
        }
        pesquisas.get(codigo).setEstadoAtivacao(true);
    }

    public String exibirPesquisa(String codigo){
        if (!pesquisas.containsKey(codigo)){
            throw new NullPointerException("Pesquisa nao encontrada.");
        }
        return codigo + pesquisas.get(codigo).toString();
    }

    public boolean pesquisAtiva(String codigo){

        if(codigo == null || codigo.equals("")){
            throw new RuntimeException("Codigo nao pode ser nulo ou vazio.");
        }
        else if (!pesquisas.containsKey(codigo)){
            throw new NullPointerException("Pesquisa nao encontrada.");
        }

        return pesquisas.get(codigo).estadoAtivacao();
    }

    private void verificaVazioNulo(String atributo, String nomeAtributo) {
        StringJoiner joiner = new StringJoiner(" ");
        joiner.add(nomeAtributo);
        joiner.add("nao pode ser nulo ou vazio.");
        if (atributo == null || atributo.equals("")){
            throw new IllegalArgumentException(joiner.toString());
        }
    }
}

