package com.psquiza.controllers;

import com.psquiza.entidades.Pesquisador;
import java.util.HashMap;
import java.util.Map;

/**
 *  Classe Controladora da Classe Pesquisador. Responsavel por criar, guardar e manipular os pesquisadores no sistema.
 */
public class ControllerPesquisador {
    /**
     *  Mapa armazenando pesquisadores no controlador. Pesquisadores sao identificados unicamente pelo seu email.
     */
    private Map<String, Pesquisador> pesquisadores;

    /**
     *  Constroi um objeto ControllerPesquisador, inicializando o Mapa na forma de um HashMap
     */
    public ControllerPesquisador() {
        pesquisadores =  new HashMap<>();
    }

    /**
     *  Cadastra um pesquisador no controle, criando um novo Pesquisador a partir de seu nome, funcao, biografia, email e endereco da foto, em seguida
     *  armazena no Mapa pesquisadores a partir de seu email.
     *
     * @param nome String representando o nome de um Pesquisador.
     * @param funcao String representando a funcao de um Pesquisador.
     * @param biografia String representando a biografia de um Pesquisador.
     * @param email String representando o email de um Pesquisador.
     * @param fotoURL String representando o endereco URL da foto de um Pesquisador.
     */
    public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoURL){
        verificaNome(nome);
        verificaFuncao(funcao);
        verificaBio(biografia);
        verificaEmail(email);
        verificaFoto(fotoURL);
        pesquisadores.put(email , new Pesquisador(nome, funcao, biografia, email, fotoURL));
    }

    /**
     *  Verifica uma String representando um possivel email de um Pesquisador. Verifica se o email nao eh nulo ou vazioa, em seguida
     *  se segue o formato adequado.
     *  Formato Adequado: texto alfanumerico + @ + texto alfanumerico.
     *  Caso o email nao esteja adequada, sao lancadas excecoes com a mensagem adequada.
     *
     * @param email
     */
    private void verificaEmail(String email){
        boolean valido = false;
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
            if (pos + 1 < email.length() && pos != 0){
                valido = true;
            }
        }

        if(!valido){
            throw new IllegalArgumentException("Formato de email invalido.");
        }
    }

    /**
     *  Verifica uma String representando o possivel endereco de URL da foto de um Pesquisador. Avaliando se a String nao eh
     *  nula ou vazia e em seguida se segue o formato adequado.
     *  Formato Adequado: https:// + restante do texto
     *  Formato Adequado: http:// + restante do texto
     *
     * @param foto
     */
    private void verificaFoto(String foto){
        boolean valido = false;
        if (foto == null){
            throw new NullPointerException("Campo fotoURL nao pode ser nulo ou vazio.");
        } else if (foto.equals("")){
            throw new IllegalArgumentException("Campo fotoURL nao pode ser nulo ou vazio.");
        }

        if (foto.length() >= 8){
            if(foto.substring(0, 8).equals("https://")){
                valido = true;
            }
        } else if (foto.length() >= 7){
            if( foto.substring(0, 7).equals("http://")){
                valido = true;
            }
        }
        if (!valido){
            throw new IllegalArgumentException("Formato de foto invalido.");
        }
    }

    /**
     *  Verifica uma String representando a possivel funcao de um Pesquisador. Avalia se a String eh nula ou vazia.
     *
     * @param funcao
     */
    private void verificaFuncao (String funcao){
        if (funcao == null){
            throw new NullPointerException("Campo funcao nao pode ser nulo ou vazio.");
        } else if (funcao.equals("")){
            throw new IllegalArgumentException("Campo funcao nao pode ser nulo ou vazio.");
        }
    }
    /**
     *  Verifica uma String representando o possivel nome de um Pesquisador. Avalia se a String eh nula ou vazia.
     *
     * @param nome
     */
    private void verificaNome(String nome){
        if (nome == null){
            throw new NullPointerException("Campo nome nao pode ser nulo ou vazio.");
        } else if (nome.equals("")){
            throw new IllegalArgumentException("Campo nome nao pode ser nulo ou vazio.");
        }
    }
    /**
     *  Verifica uma String representando a possivel biografia de um Pesquisador. Avalia se a String eh nula ou vazia.
     *
     * @param biografia
     */
    private void verificaBio (String biografia){
        if (biografia == null){
            throw new NullPointerException("Campo biografia nao pode ser nulo ou vazio.");
        } else if (biografia.equals("")){
            throw new IllegalArgumentException("Campo biografia nao pode ser nulo ou vazio.");
        }
    }

    /**
     *  Altera um atributo de um pesquisador, identificado por seu email.
     *
     * @param email
     * @param atributo
     * @param novoValor
     */
    public void alteraPesquisador(String email, String atributo, String novoValor){
        verificaEmail(email);
        if (atributo == null){
            throw new NullPointerException("Atributo nao pode ser vazio ou nulo.");
        } else if (atributo.equals("")){
            throw new IllegalArgumentException("Atributo nao pode ser vazio ou nulo.");
        }
        if (!pesquisadores.containsKey(email)){
            throw new IllegalArgumentException("Pesquisador nao encontrado");
        }
        if (!pesquisadores.get(email).ehAtivo()){
            throw new IllegalArgumentException("Pesquisador inativo.");
        }

        if (atributo.equals("NOME")){
            verificaNome(novoValor);
            pesquisadores.get(email).setNome(novoValor);
        } else if(atributo.equals("FUNCAO")){
            verificaFuncao(novoValor);
            pesquisadores.get(email).setFuncao(novoValor);
        } else if (atributo.equals("BIOGRAFIA")){
            verificaBio(novoValor);
            pesquisadores.get(email).setBio(novoValor);
        } else if (atributo.equals("EMAIL")){
            verificaEmail(novoValor);
            pesquisadores.put(novoValor, pesquisadores.get(email));
            pesquisadores.remove(email);
            pesquisadores.get(novoValor).setEmail(novoValor);
        } else if (atributo.equals("FOTO")){
            verificaFoto(novoValor);
            pesquisadores.get(email).setFoto(novoValor);

        } else {
            throw new IllegalArgumentException("Atributo invalido.");
        }
    }
    public boolean pesquisadorEhAtivo(String email){
        if(email == null){
            throw new NullPointerException("Email nao pode ser vazio ou nulo.");
        } else if (email.trim().equals("")){
            throw new IllegalArgumentException("Email nao pode ser vazio ou nulo.");
        }
        if (!pesquisadores.containsKey(email)){
            throw new IllegalArgumentException("Pesquisador nao encontrado");
        }
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
        if (!pesquisadores.containsKey(email)){
            throw new IllegalArgumentException("Pesquisador nao encontrado");
        }
        if (!pesquisadores.get(email).ehAtivo()){
            throw new IllegalArgumentException("Pesquisador inativo.");
        }
        return pesquisadores.get(email).toString();
    }

}
