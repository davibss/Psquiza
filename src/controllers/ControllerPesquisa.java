package com.psquiza.controllers;


import com.psquiza.comparators.CompararStringNumero;
import com.psquiza.comparators.OrdenaPorIDProblema;
import com.psquiza.comparators.OrdenaPorObjetivo;
import com.psquiza.comparators.OrdenarPesquisa;
import com.psquiza.entidades.*;
import com.psquiza.verificadores.Verificador;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Controller de pesquisa, classe responsável por cadastrar, alterar, ativar, encerrar e exibir pesquisa
 * @author José Nestor
 */
public class ControllerPesquisa {
    /** Mapa que armazena pesquisas, pesquisas são identificadas por um código gerado no sistema*/
    private Map<String, Pesquisa> pesquisas;
    /** Estrtégia utilizada na sugestão da próxima atividade a ser executada*/
    private String estrategia;


    /**
     * Constrói o controller, inicializando um mapa.
     * A estratégia de sugestão é definida por padrão como "MAIS_ANTIGA"
     */
    public ControllerPesquisa(){
        this.pesquisas = new HashMap<>();
        this.estrategia = "MAIS_ANTIGA";
    }

    /**
     * Cadastra uma pesquisa, cria um código que identifica pesquisas no mapa, lança exceções se necessário e retorna o código em forma de String.
     * @param descricao representação em String da descrição da pesquisa.
     * @param campoInteresse representação em String do campo de interesse da pesquisa.
     * @return representação em String do código identificador de uma pesquisa.
     */
    public String cadastrarPesquisa(String descricao, String campoInteresse){
       if(descricao == null || descricao.equals("")){
            throw new NullPointerException("Descricao nao pode ser nula ou vazia.");
       }
       String[] campos = campoInteresse.split(",");
       if (campos.length > 4 || campos[0].trim().length() < 3 || campoInteresse.length() > 255){
           throw new IllegalArgumentException ("Formato do campo de interesse invalido.");
       }
       for(String topico : campos){
           if (topico == null || topico.trim().equals("")){
               throw new IllegalArgumentException ("Formato do campo de interesse invalido.");
           }
       }
       int valorInt = 1;
       if (this.pesquisas.keySet().stream().anyMatch(s -> s.substring(0, 3).equals(campos[0].substring(0, 3).toUpperCase()))){
           valorInt = this.pesquisas.keySet().stream().
                   filter(s -> s.substring(0,3).equals(campos[0].substring(0,3).toUpperCase())).
                   mapToInt(s -> Integer.parseInt(s.substring(3))).max().getAsInt() + 1;
       }

//       for(String codigo : this.pesquisas.keySet().stream().sorted(String::compareTo).collect(Collectors.toList())){
//           if(codigo.substring(0,3).equals(campos[0].trim().substring(0, 3).toUpperCase())){
////               System.out.println(codigo);
////               System.out.println(campos[0].trim().toUpperCase());
//               valorInt = Integer.parseInt(String.valueOf(codigo.charAt(3))) + 1;
//           }
//       }
       String codigo = campos[0].trim().substring(0, 3).toUpperCase() + valorInt;
       this.pesquisas.put(codigo, new Pesquisa(codigo,true, descricao, campoInteresse));
       return codigo;
    }

    /**
     * Altera o conteudo de pesquisa, podendo ser a descrição ou campo de interesse e lança exceções se necessário.
     * @param codigo representação em String do código que identifica pesquisas.
     * @param conteudoASerAlterado representação em String do conteúdo que será alterado, podendo ser descrição ou campo de interesse.
     * @param novoConteudo representação em String do novo conteúdo.
     */
    public void alterarPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo){
        if((novoConteudo == null || (novoConteudo.equals(""))) && conteudoASerAlterado.equals("DESCRICAO")){
            throw new RuntimeException("Descricao nao pode ser nula ou vazia.");
        }
        else if((novoConteudo == null || (novoConteudo.equals(""))) && conteudoASerAlterado.equals("CAMPO")) {
            throw new RuntimeException("Formato do campo de interesse invalido.");
        }
        verificaPesquisa(codigo);
        if(!pesquisas.get(codigo).estadoAtivacao()){
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

    /**
     * Encerra a pesquisa mudando seu estado para falso.
     * @param codigo representação em String do código que identifica pesquisas.
     * @param motivo representação em String do motivo para encerrar a pesquisa.
     */
    public void encerrarPesquisa(String codigo, String motivo){
        Verificador.verificaVazioNulo(codigo,"Codigo");
        Verificador.verificaVazioNulo(motivo,"Motivo");
        verificaPesquisa(codigo);
//        if (!pesquisas.containsKey(codigo)){
//            throw new NullPointerException("Pesquisa nao encontrada.");
//        }
        if(!pesquisas.get(codigo).estadoAtivacao()){
              throw new RuntimeException("Pesquisa desativada.");
        }
        pesquisas.get(codigo).setEstadoAtivacao(false);
    }

    /**
     * Ativa a pesquisa mudando seu estado para verdadeiro.
     * @param codigo representação em String do código que identifica pesquisas.
     */
    public void ativarPesquisa(String codigo){
        Verificador.verificaVazioNulo(codigo,"Codigo");
        verificaPesquisa(codigo);
//        if (!pesquisas.containsKey(codigo)){
//            throw new NullPointerException("Pesquisa nao encontrada.");
//        }
        if(pesquisas.get(codigo).estadoAtivacao()) {
            throw new RuntimeException("Pesquisa ja ativada.");
        }
        pesquisas.get(codigo).setEstadoAtivacao(true);
    }

    /**
     * Retorna uma String que representa uma pesquisa.
     * A representação segue o formato: "Código - descrição - campo de interesse".
     * @param codigo representação em String do código que identifica pesquisas.
     * @return A representação em String de uma pesquisa.
     */
    public String exibirPesquisa(String codigo){
        Verificador.verificaVazioNulo(codigo,"Codigo");
        if (!pesquisas.containsKey(codigo)){
            throw new NullPointerException("Pesquisa nao encontrada.");
        }
        //return codigo + pesquisas.get(codigo).toString();
        return pesquisas.get(codigo).toString();
    }

    /**
     * Retorna um valor boleano que representa o estado de ativação da pesquisa, sendo verdadeiro para ativa e falso para desativa.
     * @param codigo representação em String do código que identifica pesquisas.
     * @return A representação boleana do estado da pesquisa.
     */
    public boolean pesquisAtiva(String codigo){
        if(codigo == null || codigo.equals("")){
            throw new RuntimeException("Codigo nao pode ser nulo ou vazio.");
        }
        verificaPesquisa(codigo);
//        else if (!pesquisas.containsKey(codigo)){
//            throw new NullPointerException("Pesquisa nao encontrada.");
//        }
        return pesquisas.get(codigo).estadoAtivacao();
    }

    /**
     * Verifica se a pesquisa existe no mapa de presquisas, lança uma exceção caso não exista.
     * @param codigo representação em String do código que identifica pesquisas.
     */
    public void verificaPesquisa(String codigo){
        if (!pesquisas.containsKey(codigo)){
            throw new NullPointerException("Pesquisa nao encontrada.");
        }
    }

    /**
     * Retorna um valor booleano que informa se um pesquisador foi desassociado de uma pesquisa, sendo verdadeiro quando desassociado e falso caso não ocorra a desassociação.
     *  O motivo de não desassociar acontece caso o pesquisado não esteja associado.
     * @param idPesquisa Representação em String do código que identifica pesquisas.
     * @param emailPesquisador email do pesquisador, no formato "nome@gmail.com".
     * @param pesquisador pesquisador, objeto que representa um pesquisador.
     * @return a representação booleana da associação dos pesquisador com a pesquisa.
     */
    public  boolean associaPesquisador(String idPesquisa, String emailPesquisador, Pesquisador pesquisador){
        if(!pesquisas.containsKey(idPesquisa)){
            throw new RuntimeException("Pesquisa nao encontrada.");
        }
        if(!pesquisas.get(idPesquisa).estadoAtivacao()){
            throw new RuntimeException("Pesquisa desativada.");
        }

        return pesquisas.get(idPesquisa).associaPesquisador(emailPesquisador, pesquisador);
    }

    /**
     * Retorna um valor booleano que informa se um pesquisador foi desassociado de uma pesquisa, sendo verdadeiro quando desassociado e falso caso não ocorra a desassociação.
     * O motivo de não desassociar acontece caso o pesquisado não esteja associado.
     * @param idPesquisa Representação em String do código que identifica pesquisas.
     * @param emailPesquisador email do pesquisador, no formato "nome@gmail.com".
     * @return a representação booleana da desassociação dos pesquisador com a pesquisa.
     */
    public boolean desassociaPesquisadores(String idPesquisa, String emailPesquisador){
        if(!pesquisas.containsKey(idPesquisa)){
            throw new RuntimeException("Pesquisa nao encontrada.");
        }
        if(!pesquisas.get(idPesquisa).estadoAtivacao()){
            throw new RuntimeException("Pesquisa desativada.");
        }

        return pesquisas.get(idPesquisa).desassociaPesquisador(emailPesquisador);
    }

    /**
     * Associa um problema a uma pesquisa, passando o nome da pesquisa e o problema.
     * Verifica se o id da pesquisa é nulo ou vazio e se a pesquisa existe e está ativada.
     * @param pesquisa representação em String da pesquisa.
     * @param problema representação do tipo Problema do problema.
     * @return true se a operação foi realizada com sucesso, se não, false.
     */
    public boolean associaProblema(String pesquisa, Problema problema) {
        Verificador.verificaVazioNulo(pesquisa, "idPesquisa");
        verificaPesquisa(pesquisa);
        if (!pesquisAtiva(pesquisa)){
            throw new IllegalArgumentException("Pesquisa desativada.");
        }
        return pesquisas.get(pesquisa).associaProblema(problema);
    }

    /**
     * Desassocia um problema de uma pesquisa.
     * Verifica se o ID da pesquisa é nulo ou vazio, se a pesquisa existe e se ela está desativada.
     * @param pesquisa representação em String da pesquisa.
     * @return true se a operação foi realizada com sucesso, se não, false.
     */
    public boolean desassociaProblema(String pesquisa) {
        Verificador.verificaVazioNulo(pesquisa, "idPesquisa");
        verificaPesquisa(pesquisa);
        if (!pesquisAtiva(pesquisa)){
            throw new IllegalArgumentException("Pesquisa desativada.");
        }
        return pesquisas.get(pesquisa).desassociaProblema();
    }


    /**
     * Associa um objetivo a uma pesquisa, através dos parâmetros passados.
     * Verifica se o ID da pesquisa é nulo ou vazio, se a pesquisa existe e se está desativada.
     * Verifica se outras pesquisas possuem este objetivo.
     * @param pesquisa representação em String da pesquisa.
     * @param objetivo representação do tipo Objetivo do objetivo.
     * @return true se a operação foi realizada com sucesso, se não, false.
     */
    public boolean associaObjetivo(String pesquisa, Objetivo objetivo) {
        Verificador.verificaVazioNulo(pesquisa, "idPesquisa");
        verificaPesquisa(pesquisa);
        if (!pesquisAtiva(pesquisa)){
            throw new IllegalArgumentException("Pesquisa desativada.");
        }

        if (this.pesquisas.keySet().stream().filter(p -> !p.equals(pesquisa)).anyMatch(p -> this.pesquisas.get(p).contemObjetivo(objetivo))){
            throw new IllegalArgumentException("Objetivo ja associado a uma pesquisa.");
        }

        return pesquisas.get(pesquisa).asssociaObjetivo(objetivo);
    }

    /**
     * Desassocia um objetivo de uma pesquisa, através dos parâmetros passados.
     * Verifica se o ID de pesquisa e objetivo são nulos ou vazios, se a pesquisa existe e se está desativada.
     * @param pesquisa representação em String da pesquisa.
     * @param objetivo representação em String do objetivo.
     * @return true se a operação foi realizada com sucesso, se não, false.
     */
    public boolean desassociaObjetivo(String pesquisa, String objetivo) {
        Verificador.verificaVazioNulo(pesquisa,"idPesquisa");
        Verificador.verificaVazioNulo(objetivo,"idObjetivo");
        verificaPesquisa(pesquisa);
        if (!pesquisAtiva(pesquisa)){
            throw new IllegalArgumentException("Pesquisa desativada.");
        }
        return pesquisas.get(pesquisa).desassociaObjetivo(objetivo);
    }

    /**
     * Ordena todas as pesquisas pelo problema, do problema de maior nome, até o de menor,
     * caso pesquisa não tenha problemas, é feita uma ordenação de pesquisas pelo código delas, de trás pra frente.
     * @return uma lista de strings com as representações completas das pesquisas.
     */
    private List<String> ordenaPorIDProblema(){
        ArrayList<String> lista = new ArrayList<>();
        this.pesquisas.entrySet().stream().filter(stringPesquisaEntry -> stringPesquisaEntry.getValue().getProblema() != null).
                sorted(Map.Entry.comparingByValue(new OrdenaPorIDProblema())).forEach(a -> lista.add(a.getKey()));
        this.pesquisas.keySet().stream().filter(k -> !lista.contains(k)).
                //sorted((chave1, chave2) -> chave1.compareTo(chave2) * -1).forEach(lista::add);
                sorted(new CompararStringNumero(-1)).forEach(lista::add);
        return lista;
    }

    /**
     * Ordena todas as pesquisas pela quantidade de objetivos, primeiro as que tem mais objetivos, depois as que tem menos,
     * caso a pesquisa não tenha objetivos, é feita uma ordenação com os códigos das pesquisas, de trás pra frente.
     * @return uma lista de strings com as representações completas das pesquisas.
     */
    private List<String> ordenaPorObjetivos(){
        ArrayList<String> lista = new ArrayList<>();
        this.pesquisas.entrySet().stream().filter(stringPesquisaEntry -> stringPesquisaEntry.getValue().getObjetivos() > 0).
               sorted(Map.Entry.comparingByValue(new OrdenaPorObjetivo())).forEach(a -> lista.add(a.getKey()));
        this.pesquisas.keySet().stream().filter(k -> !lista.contains(k)).
                //sorted((chave1, chave2) -> chave1.compareTo(chave2) * -1).forEach(lista::add);
                sorted(new CompararStringNumero(-1)).forEach(lista::add);
        return lista;
    }

    /**
     * Lista todas as pesquisas a partir de uma ordem passada por parâmetro.
     * A ordem só poderá ser: PROBLEMA, OBJETIVOS e PESQUISA, se não for uma destas, lança exceção.
     * @param ordem representação da ordem a ser utilizada.
     * @return uma String com todas as pesquisas concatenadas.
     */
    public String listaPesquisas(String ordem) {
        if (ordem == null){
            throw new IllegalArgumentException("Valor ordem nao pode ser nulo.");
        }
        StringJoiner joiner = new StringJoiner(" | ");
        ArrayList<Pesquisa> lista = new ArrayList<>(this.pesquisas.values());
        switch (ordem){
            case "PROBLEMA":
                ordenaPorIDProblema().forEach(c -> joiner.add(exibirPesquisa(c)));
                break;
            case "OBJETIVOS":
                ordenaPorObjetivos().forEach(c -> joiner.add(exibirPesquisa(c)));
                break;
            case "PESQUISA":
                //lista.stream().sorted(Comparator.comparing(Pesquisa::getCodigo).reversed()).
                lista.stream().sorted(new OrdenarPesquisa()).
                        forEach(pesquisa -> joiner.add(pesquisa.toString()));
                break;
            default:
                throw new IllegalArgumentException("Valor invalido da ordem");
        }
        return joiner.toString();
    }
    /**
     *  Faz uma busca por um termo em forma de String nos atributos Descricao e Campo de Interesse das Pesquisas. Retornando
     *  uma lista contendo os atributos onde o termo for encontrado.
     *
     * @param termo String representando um termo a ser buscado no Sistema Psquiza.
     * @return Lista de resultados da busca pelo termo, contendo atributos de Pesquisas onde o termo foi encontrado.
     */
    public List<String> buscaPesquisa(String termo){
        List<String> found = new ArrayList<String>();
        //pesquisas.entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.reverseOrder())).
        pesquisas.entrySet().stream().sorted(Map.Entry.comparingByKey(new CompararStringNumero(-1))).
                forEach(entry -> {
                    if (entry.getValue().getDescricao().toLowerCase().contains(termo)){
                        found.add(entry.getKey() + ": " + entry.getValue().getDescricao());
                    }
                    if (entry.getValue().getCampoInteresse().toLowerCase().contains(termo)){
                        found.add(entry.getKey() + ": " + entry.getValue().getCampoInteresse());
                    }
                });
        return found;
    }

    public boolean associaAtividade(String codigoPesquisa, String codigoAtividade, Atividade atividade) {
        Verificador.verificaVazioNulo(codigoPesquisa, "codigoPesquisa");
        Verificador.verificaVazioNulo(codigoAtividade, "codigoAtividade");
        if(!this.pesquisas.containsKey(codigoPesquisa)) {
            throw new IllegalArgumentException("Pesquisa nao encontrada.");
        }
        if(!this.pesquisas.get(codigoPesquisa).estadoAtivacao()) {
            throw new IllegalArgumentException("Pesquisa desativada.");
        }
        return this.pesquisas.get(codigoPesquisa).associaAtividade(codigoAtividade, atividade);
    }

    public boolean desassociaAtividade(String codigoPesquisa, String codigoAtividade) {
        Verificador.verificaVazioNulo(codigoPesquisa, "codigoPesquisa");
        Verificador.verificaVazioNulo(codigoAtividade, "codigoAtividade");
        if(!this.pesquisas.containsKey(codigoPesquisa)) {
            throw new IllegalArgumentException("Pesquisa nao encontrada.");
        }
        if(!this.pesquisas.get(codigoPesquisa).estadoAtivacao()) {
            throw new IllegalArgumentException("Pesquisa desativada.");
        }
        return this.pesquisas.get(codigoPesquisa).desassociaAtividade(codigoAtividade);
    }

    public boolean associacao(String codigoAtividade) {
        return this.pesquisas.values().stream().anyMatch(pesquisa -> pesquisa.hasAtividade(codigoAtividade));
    }

    /**
     * Grava o mapa de pesquisas a partir de um objeto do tipo ObjectOutputStream passado como parâmetro.
     * @param objectOutputStream variável que permite escrever objetos em um arquivo.
     * @throws IOException Exceção lançada caso a escrita de arquivo falhe.
     */
    public void grava(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeObject(this.pesquisas);
    }

    /**
     * Carrega um objeto no mapa de pesquisas.
     * @param objectInputStream variável que lê um objetos de um arquivo.
     * @throws IOException Exceção lançada caso a escrita de arquivo falhe.
     * @throws ClassNotFoundException Exceção lançada caso a escrita de arquivo falhe.
     */
    public void carrega(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        this.pesquisas = (Map<String, Pesquisa>) objectInputStream.readObject();
    }

    /**
     * Grava em um arquivo .txt o resumo da pesquisa.
     * @param codigoPesquisa representação em String do código que identifica pesquisas.
     * @throws IOException
     */
    public void gravarResumo(String codigoPesquisa) throws IOException {
        if (codigoPesquisa == null ||codigoPesquisa.equals("")){
            throw new RuntimeException("Pesquisa nao pode ser nula ou vazia.");
        }
        verificaPesquisa(codigoPesquisa);
        String resumoPesquisa = "- Pesquisa: " + codigoPesquisa +" - "+ pesquisas.get(codigoPesquisa).getDescricao() + " - "+ pesquisas.get(codigoPesquisa).getCampoInteresse() + "\n" +
                "    - Pesquisadores:\n" +
                pesquisas.get(codigoPesquisa).listaPesquisadores() +
                "\n" +
                "    - Problema:\n" +
                "        - " + pesquisas.get(codigoPesquisa).getProblemaResumo() +
                "\n" +
                "    - Objetivos:\n" +
                pesquisas.get(codigoPesquisa).getObjetivosResumo() +
                "\n" +
                "    - Atividades:\n" +
                pesquisas.get(codigoPesquisa).getAtividadesResumo()+"\n";
        if (!new File("tests/accept-tests/easyaccept/").exists()){
            new File("tests/accept-tests/easyaccept").mkdir();
        }
        File file = new File("./_"+codigoPesquisa+".txt");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(resumoPesquisa.getBytes());
        } finally {
            fos.close();
        }
    }

    /**
     * Grava em um arquivo .txt o resultado da pesquisa
     * @param codigoPesquisa representação em String do código que identifica pesquisas.
     * @throws IOException
     */
    public void gravarResultados(String codigoPesquisa) throws IOException{
        if (codigoPesquisa == null ||codigoPesquisa.equals("")){
            throw new RuntimeException("Pesquisa nao pode ser nula ou vazia.");
        }
        verificaPesquisa(codigoPesquisa);
        String resultadoPesquisa = "- Pesquisa: " + codigoPesquisa +" - "+ pesquisas.get(codigoPesquisa).getDescricao() + " - "+ pesquisas.get(codigoPesquisa).getCampoInteresse() + "\n" +
                "    - Resultados:" +
                "\n" +
                pesquisas.get(codigoPesquisa).getAtividadesResultado() + "\n";
        File file = new File("./"+codigoPesquisa+"-Resultados.txt");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            String resultados = resultadoPesquisa;
            fos.write(resultados.getBytes());
        } finally {
            fos.close();
        }
    }

    public void configuraEstrategia(String estrategia) {
        this.estrategia = estrategia;
    }

    public String proximaAtividade(String codigoPesquisa) {
        if(!this.pesquisas.containsKey(codigoPesquisa)) {
            throw new IllegalArgumentException("Pesquisa nao encontrada.");
        }
        if(!pesquisas.get(codigoPesquisa).estadoAtivacao()){
            throw new RuntimeException("Pesquisa desativada.");
        }
        return this.pesquisas.get(codigoPesquisa).proximaAtividade(this.estrategia);
    }
}

