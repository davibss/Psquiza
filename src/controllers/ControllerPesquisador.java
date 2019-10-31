package com.psquiza.controllers;

import com.psquiza.entidades.Pesquisa;
import com.psquiza.entidades.Pesquisador;
import java.util.HashMap;
import java.util.Map;

public class ControllerPesquisador {
    private Map<String, Pesquisador> pesquisadores;

    public ControllerPesquisador() {
        pesquisadores =  new HashMap<>();
    }

    public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoURL){
        if (nome == null){
            throw new NullPointerException("Campo nome nao pode ser nulo ou vazio.");
        } else if (nome.equals("")){
            throw new IllegalArgumentException("Campo nome nao pode ser nulo ou vazio.");
        }
        if (funcao == null){
            throw new NullPointerException("Campo funcao nao pode ser nulo ou vazio.");
        } else if (funcao.equals("")){
            throw new IllegalArgumentException("Campo funcao nao pode ser nulo ou vazio.");
        }
        if (biografia == null){
            throw new NullPointerException("Campo biografia nao pode ser nulo ou vazio.");
        } else if (biografia.equals("")){
            throw new IllegalArgumentException("Campo biografia nao pode ser nulo ou vazio.");
        }
        if (!verificaEmail(email)){
            throw new IllegalArgumentException("Formato de email invalido.");
        }
        if (!verificaFoto(fotoURL)){
            throw new IllegalArgumentException("Formato de foto invalido.");
        }
        pesquisadores.put(email , new Pesquisador(nome, funcao, biografia, email, fotoURL));
    }

    private boolean verificaEmail(String email){
        if (email == null){
            throw new NullPointerException("Campo email nao pode ser nulo ou vazio.");
        } else if (email.equals("")){
            throw new IllegalArgumentException("Campo email nao pode ser nulo ou vazio.");
        }

        if (email.contains("@")) {
            int pos = 0;
            for(int i = 0; i < email.length(); i++){
                char c = email.charAt(i);
                if (c == '@'){
                    pos = i;
                }
            }
            if (pos + 1 < email.length()){
                return true;
            }
        }
        return false;
    }
    private boolean verificaFoto(String foto){
        if (foto == null){
            throw new NullPointerException("Campo fotoURL nao pode ser nulo ou vazio.");
        } else if (foto.equals("")){
            throw new IllegalArgumentException("Campo fotoURL nao pode ser nulo ou vazio.");
        }

        if (foto.length() >= 8){
            return foto.substring(0, 8).equals("https://");
        } else if (foto.length() >= 7){
            return foto.substring(0, 7).equals("http://");
        }
        return false;
    }

    public void alteraPesquisador(String email, String atributo, String novoValor){
        verificaEmail(email);
        if (atributo == null){
            throw new NullPointerException("Atributo nao pode ser vazio ou nulo.");
        } else if (atributo.equals("")){
            throw new IllegalArgumentException("Atributo nao pode ser vazio ou nulo.");
        }
        if (novoValor == null){
            throw  new NullPointerException();
        } else if(novoValor.equals("")){
            throw new IllegalArgumentException();
        }
        if (!pesquisadores.containsKey(email)){
            throw new IllegalArgumentException("Pesquisador nao encontrado.");
        }
        if (!pesquisadores.get(email).ehAtivo()){
            throw new IllegalArgumentException("Pesquisador inativo.");
        }

        if (atributo.equals("NOME")){
            pesquisadores.get(email).setNome(novoValor);
        } else if(atributo.equals("FUNCAO")){
            pesquisadores.get(email).setFuncao(novoValor);
        } else if (atributo.equals("BIOGRAFIA")){
            pesquisadores.get(email).setBio(novoValor);
        } else if (atributo.equals("EMAIL")){
            if (verificaEmail(novoValor)) {
                pesquisadores.put(novoValor, pesquisadores.get(email));
                pesquisadores.remove(email);
            }
        } else if (atributo.equals("FOTO")){
            if (verificaFoto(novoValor)) {
                pesquisadores.get(email).setFoto(novoValor);
            }
        }
    }
    public boolean pesquisadorEhAtivo(String email){
        return pesquisadores.get(email).ehAtivo();
    }
    public void desativaPesquisador(String email){
        verificaEmail(email);
        if (!pesquisadores.containsKey(email)){
            throw new IllegalArgumentException("Pesquisador nao encontrado");
        }
        if (!pesquisadores.get(email).ehAtivo()){
            throw new IllegalArgumentException("Pesquisador inativo.");
        }
        pesquisadores.get(email).setAtivo(false);
    }
    public  void ativaPesquisador(String email){
        verificaEmail(email);
        if (!pesquisadores.containsKey(email)){
            throw new IllegalArgumentException("Pesquisador nao encontrado");
        }
        if (pesquisadores.get(email).ehAtivo()){
            throw new IllegalArgumentException("Pesquisador ja ativado.");
        }
        pesquisadores.get(email).setAtivo(true);
    }
    public String exibePesquisador(String email){
        verificaEmail(email);
        if (!pesquisadores.get(email).ehAtivo()){
            throw new IllegalArgumentException("Pesquisador inativo.");
        }
        return pesquisadores.get(email).toString();
    }

}
