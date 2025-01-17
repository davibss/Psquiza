package com.psquiza.controllers;

import com.psquiza.comparators.CompararStringNumero;
import com.psquiza.entidades.Pesquisador;
import com.psquiza.verificadores.Verificador;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

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
     * Cadastra a especialidade aluno em um pesquisador, adicionando o seu semeste e IEA.
     * @param email email do pesquisador.
     * @param semestre semestre do aluno.
     * @param iea índice de eficiência acadêmica do aluno.
     */
    public void cadastraEspecialidadeAluno(String email, int semestre, Double iea){
        Verificador.verificaVazioNulo(email, "email");
        if(semestre < 1) throw new RuntimeException("Atributo semestre com formato invalido.");
        if(iea < 0 || iea > 10) throw new RuntimeException("Atributo IEA com formato invalido.");
        verificaEmail(email);
        if(!pesquisadores.containsKey(email)){
            throw new RuntimeException("Pesquisadora nao encontrada.");
        }
        pesquisadores.get(email).adicionaEspecialidadeAluno(semestre, iea);
    }

    /**
     * Cadastra a especialidade professor em um pesquisador, adicionando a sua formação, unidade e data de formação.
     * @param email email do pesquisador.
     * @param formacao formação do pesquisador.
     * @param unidade unidade do pesquisador.
     * @param data data de formaçã do pesquisador.
     */
    public void cadastraEspecialidadeProfessor(String email, String formacao, String unidade, String data){
        Verificador.verificaVazioNulo(email,"email");
        Verificador.verificaVazioNulo(formacao,"formacao");
        Verificador.verificaVazioNulo(unidade, "unidade");
        Verificador.verificaVazioNulo(data,"data");
        verificaEmail(email);
        if(!pesquisadores.containsKey(email)){
            throw new RuntimeException("Pesquisadora nao encontrada.");
        }
        pesquisadores.get(email).adicionaEspecialidadeProfessor(formacao, unidade, data);

    }

    /**
     * Lista os pesquisadores pelo tipo, podendo ser externo, aluna e professora.
     * @param tipo tipo da especialidade do pesquisador.
     * @return a representação em String da lista dos pesquisadores por tipo.
     */
    public String listaPesquisadores(String tipo) {
        if (tipo == null || tipo.equals("")) {
            throw new RuntimeException("Campo tipo nao pode ser nulo ou vazio.");
        }
        if (!(tipo.equals("EXTERNO") || tipo.equals("ALUNA") || tipo.equals("PROFESSORA"))) {
            throw new RuntimeException("Tipo " + tipo + " inexistente.");
        }
        Map<String, String> tipos = new HashMap<String, String>(){{ put("EXTERNO","externo"); put("ALUNA","estudante"); put("PROFESSORA","professor"); }};
        StringJoiner joiner = new StringJoiner(" | ");
        this.pesquisadores.values().stream().filter(pesquisador -> pesquisador.getFuncao().equals(tipos.get(tipo))).
                                            forEach(pesquisador -> joiner.add(pesquisador.toString()));
        return joiner.toString();
    }

    /**
     *  Verifica uma String representando um possivel email de um Pesquisador. Verifica se o email nao eh nulo ou vazioa, em seguida
     *  se segue o formato adequado.
     *  Formato Adequado: texto alfanumerico + @ + texto alfanumerico.
     *  Caso o email nao esteja adequada, sao lancadas excecoes com a mensagem adequada.
     *
     * @param email String representando o email de um Pesquisador.
     */
    private void verificaEmail(String email){
        Verificador.verificaVazioNulo(email, "email");
        if (!Verificador.verificaEmail(email)){
            throw new IllegalArgumentException("Formato de email invalido.");
        }
    }

    /**
     *  Verifica uma String representando o possivel endereco de URL da foto de um Pesquisador. Avaliando se a String nao eh
     *  nula ou vazia e em seguida se segue o formato adequado.
     *  Formato Adequado: https:// + restante do texto
     *  Formato Adequado: http:// + restante do texto
     *
     * @param foto String representando o endereco URL da foto de um Pesquisador.
     */
    private void verificaFoto(String foto){
        Verificador.verificaVazioNulo(foto, "fotoURL");
        if (!Verificador.verificaFoto(foto)){
            throw new IllegalArgumentException("Formato de foto invalido.");
        }
    }

    /**
     *  Verifica uma String representando a possivel funcao de um Pesquisador. Avalia se a String eh nula ou vazia
     *  e lanca excecoes de acordo.
     *
     * @param funcao String representando a funcao de um Pesquisador.
     */
    private void verificaFuncao (String funcao){
        Verificador.verificaVazioNulo(funcao, "funcao");
    }
    /**
     *  Verifica uma String representando o possivel nome de um Pesquisador. Avalia se a String eh nula ou vazia
     *  e lanca excecoes de acordo.
     *
     * @param nome String representando o nome de um Pesquisador.
     */
    private void verificaNome(String nome){
        Verificador.verificaVazioNulo(nome, "nome");
    }
    /**
     *  Verifica uma String representando a possivel biografia de um Pesquisador. Avalia se a String eh nula ou vazia
     *  e lanca excecoes de acordo.
     *
     * @param biografia String representando a biografia de um Pesquisador.
     */
    private void verificaBio (String biografia){
        Verificador.verificaVazioNulo(biografia, "biografia");
    }

    /**
     *  Altera um atributo de um pesquisador, identificado por seu email. O atributo alterado pode ser
     *  NOME, FUNCAO, BIOGRAFIA, EMAIL ou FOTO, e esse atributo sera substituido pelo novoValor.
     *
     * @param email String representando o email de um Pesquisador.
     * @param atributo String representando o atributo a ser alterado.
     * @param novoValor String representando o novo valor do atributo sendo alterado.
     */
    public void alteraPesquisador(String email, String atributo, String novoValor){
        verificaEmail(email);
        if (atributo == null || atributo.equals("")){
            throw new NullPointerException("Atributo nao pode ser vazio ou nulo.");
        }
        if (!pesquisadores.containsKey(email)){
            throw new IllegalArgumentException("Pesquisador nao encontrado");
        }
        if (!pesquisadores.get(email).ehAtivo()){
            throw new IllegalArgumentException("Pesquisador inativo.");
        }

        switch (atributo) {
            case "NOME":
                verificaNome(novoValor);
                pesquisadores.get(email).setNome(novoValor);
                break;
            case "FUNCAO":
                verificaFuncao(novoValor);
                pesquisadores.get(email).setFuncao(novoValor);
                break;
            case "BIOGRAFIA":
                verificaBio(novoValor);
                pesquisadores.get(email).setBio(novoValor);
                break;
            case "EMAIL":
                verificaEmail(novoValor);
                pesquisadores.put(novoValor, pesquisadores.get(email));
                pesquisadores.remove(email);
                pesquisadores.get(novoValor).setEmail(novoValor);
                break;
            case "FOTO":
                verificaFoto(novoValor);
                pesquisadores.get(email).setFoto(novoValor);
                break;
            case "SEMESTRE":
            case "IEA":
            case "FORMACAO":
            case "UNIDADE":
            case "DATA":
                pesquisadores.get(email).altera(atributo, novoValor);
                break;
            default:
//                if(atributo.equals("SEMESTRE") || atributo.equals("IEA") || atributo.equals("FORMACAO") || atributo.equals("UNIDADE") || atributo.equals("DATA")) {
//
//                    pesquisadores.get(email).altera(atributo, novoValor);
//                }
//                else {
                    throw new IllegalArgumentException("Atributo invalido.");
                //}
        }
    }

    /*public String listaPesquisadores(String tipo){
        return "a";
    }*/

    /**
     *  Verifica se um Pesquisador, identificado pelo email, esta ativo ou nao. Retornando verdadeiro caso esteja ativo
     *  e falso caso esteja inativo.
     *
     * @param email String representando o email de um Pesquisador.
     * @return Valor Boolean representando a situacao de atividade de um Pesquisador.
     */
    public boolean pesquisadorEhAtivo(String email){
        if(email == null || email.trim().equals("")) {
            throw new NullPointerException("Email nao pode ser vazio ou nulo.");
        }
        if (!pesquisadores.containsKey(email)){
            throw new IllegalArgumentException("Pesquisador nao encontrado");
        }
        return pesquisadores.get(email).ehAtivo();
    }

    /**
     *  Desativa um pesquisador, alterando seu estado de ativo para inativo. Caso o pesquisador ja esteja inativo
     *  uma excecao eh lancada.
     *
     * @param email String representando o email de um Pesquisador.
     */
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

    /**
     *  Ativa um Pesquisador, identificado pelo email, alterando seu estado de inativo para ativo. Caso o pesquisador ja
     *  esteja ativo uma excecao sera lancada.
     *
     * @param email String representando o email de um Pesquisador.
     */
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

    /**
     *  Gera a representacao em String de um Pesquisador, identificado pelo email, para exibicao.
     *  A representacao segue o padrao "NOME (FUNCAO) - BIOGRAFIA - EMAIL - FOTO_URL".
     *
     * @param email String representando o email de um Pesquisador.
     * @return String representando um Pesquisador, com suas informacoes de nome, funcao, biografia, email e foto.
     */
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

    /**
     * Retorna um objeto do tipo Pesquisador passando o email do pesquisador como parâmetro.
     * @param email representação em String do email do pesquisador a ser buscado.
     * @return um objeto do tipo Pesquisador.
     */
    public Pesquisador getPesquisador(String email){
        return this.pesquisadores.get(email);
    }

    /**
     *  Faz uma busca por um termo em forma de String nos atributos Biografia de Pesquisadores. Retornando
     *  uma lista contendo os atributos onde o termo for encontrado.
     *
     * @param termo String representando um termo a ser buscado no Sistema Psquiza.
     * @return Lista de resultados da busca pelo termo, contendo atributos de Pesquisadores onde o temro foi encontrado.
     */
    public List<String> buscaPesquisador(String termo){
        List<String> found = new ArrayList<>();
        pesquisadores.entrySet().stream().filter(entry -> entry.getValue().getBio().toLowerCase().contains(termo)).
                sorted(Map.Entry.comparingByKey(new CompararStringNumero(-1))).
                forEach(entry -> found.add(entry.getKey() + ": " + entry.getValue().getBio()));
        return found;
    }

    /**
     * Grava o mapa de pesquisadores a partir de um objeto do tipo ObjectOutputStream passado como parâmetro.
     * @param objectOutputStream variável que permite escrever objetos em um arquivo.
     * @throws IOException Exceção lançada caso a escrita de arquivo falhe.
     */
    public void grava(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeObject(this.pesquisadores);
    }

    /**
     * Carrega um objeto no mapa de pesquisadores.
     * @param objectInputStream variável que lê um objetos de um arquivo.
     * @throws IOException Exceção lançada caso a escrita de arquivo falhe.
     * @throws ClassNotFoundException Exceção lançada caso a escrita de arquivo falhe.
     */
    public void carrega(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        this.pesquisadores = (Map<String, Pesquisador>) objectInputStream.readObject();
    }
}
