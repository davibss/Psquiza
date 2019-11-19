package com.psquiza.controllers;

import com.psquiza.entidades.Buscador;
import com.psquiza.verificadores.Verificador;

import java.io.*;
/**
 * Controller Geral, classe responsável por gerenciar todos os controllers e realizar a comunicação com a Facede.
 *
 */
public class ControllerGeral {

    private static String diretorio = "data"+System.getProperty("file.separator")+"pzquiza.txt";
    private static File file = new File(diretorio);

    private ControllerAtividade controllerAtividade;
    /**
     * Controller de pesquisa, onde são realizadas todas as operações com pesquisa.
     */
    private ControllerPesquisa controllerPesquisa;
    private ControllerProblema controllerProblema;
    private ControllerObjetivo controllerObjetivo;
    private ControllerPesquisador controllerPesquisador;
    private Buscador buscador;

    /**
     * Constrói o controller, inicializa todos os controllers junto com a classe buscador.
     */
    public ControllerGeral() {
        controllerAtividade = new ControllerAtividade();
        controllerProblema = new ControllerProblema();
        controllerObjetivo = new ControllerObjetivo();
        controllerPesquisador = new ControllerPesquisador();
        controllerPesquisa = new ControllerPesquisa();
        buscador =  new Buscador(controllerAtividade, controllerPesquisa, controllerPesquisador, controllerObjetivo, controllerProblema);
    }

    //Caso de uso 1(José Nestor)

    /**
     * Cadastra uma pesquisa, cria um código que identifica pesquisas no mapa, lança exceções se necessário e retorna o código em forma de String.
     * @param descricao representação em String da descrição da pesquisa.
     * @param campoInteresse representação em String do campo de interesse da pesquisa.
     * @return representação em String do código identificador de uma pesquisa.
     */
    public String cadastrarPesquisa(String descricao, String campoInteresse){
        return controllerPesquisa.cadastrarPesquisa(descricao, campoInteresse);
    }

    /**
     * Altera o conteudo de pesquisa, podendo ser a descrição ou campo de interesse, o método lança exceções se necessário.
     * @param codigo representação em String do código que identifica pesquisas.
     * @param conteudoASerAlterado representação em String do conteúdo que será alterado, podendo ser descrição ou campo de interesse.
     * @param novoConteudo representação em String do novo conteúdo.
     */
    public void alterarPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo){
        controllerPesquisa.alterarPesquisa(codigo, conteudoASerAlterado, novoConteudo);
    }
    /**
     * Encerra a pesquisa mudando seu estado para falso.
     * @param codigo representação em String do código que identifica pesquisas.
     * @param motivo representação em String do motivo para encerrar a pesquisa.
     */
    public void encerrarPesquisa(String codigo, String motivo){
        controllerPesquisa.encerrarPesquisa(codigo, motivo);
    }
    /**
     * Ativa a pesquisa mudando seu estado para verdadeiro.
     * @param codigo representação em String do código que identifica pesquisas.
     */
    public void ativarPesquisa(String codigo){
        controllerPesquisa.ativarPesquisa(codigo);
    }
    /**
     * Retorna uma String que representa uma pesquisa.
     * A representação segue o formato: "Código - descrição - campo de interesse".
     * @param codigo representação em String do código que identifica pesquisas.
     * @return A representação em String de uma pesquisa.
     */
    public String exibirPesquisa(String codigo){
        return controllerPesquisa.exibirPesquisa(codigo);
    }
    /**
     * Retorna um valor boleano que representa o estado de ativação da pesquisa, sendo verdadeiro para ativa e falso para desativa.
     * @param codigo representação em String do código que identifica pesquisas.
     * @return A representação boleana do estado da pesquisa.
     */
    public boolean pesquisAtiva(String codigo){
        return controllerPesquisa.pesquisAtiva(codigo);
    }



    //Caso de uso 2(Anderson)
    public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoURL){
        controllerPesquisador.cadastraPesquisador(nome, funcao, biografia, email, fotoURL);
    }
    public void alteraPesquisador(String email, String atributo, String novoValor){
        controllerPesquisador.alteraPesquisador(email, atributo, novoValor);
    }
    public void desativaPesquisador(String email){
        controllerPesquisador.desativaPesquisador(email);
    }

    public void ativaPesquisador(String email){
        controllerPesquisador.ativaPesquisador(email);
    }

    public String exibePesquisador(String email){
        return controllerPesquisador.exibePesquisador(email);
    }

    public boolean pesquisadorEhAtivo(String email){
        return controllerPesquisador.pesquisadorEhAtivo(email);
    }

//Caso de uso 3 (Henrique)

    public void cadastraProblema(String descricao, int viabilidade) { controllerProblema.cadastraProblema(descricao, viabilidade); }

    public void apagarProblema(String codigo) { controllerProblema.apagarProblema(codigo); }

    public String exibeProblema(String codigo) { return controllerProblema.exibeProblema(codigo); }

    public void cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade)  { controllerObjetivo.cadastraObjetivo(tipo, descricao, aderencia, viabilidade); }

    public void apagarObjetivo(String codigo) { controllerObjetivo.apagarObjetivo(codigo); }

    public String exibeObjetivo(String codigo) { return  controllerObjetivo.exibeObjetivo(codigo); }

//Caso de uso 4 (Davi)

    /**
     * Cadastra atividades a partir dos parâmetros passados.
     * @param descricao representação em String da descrição da atividade.
     * @param risco representação em String do risco da atividade [ALTO, MEDIO, BAIXO]
     * @param descricaoRisco representação em String da descrição do risco da atividade.
     */
    public void cadastrarAtividade(String descricao,  String risco, String descricaoRisco){
        controllerAtividade.cadastrarAtividades(descricao, risco, descricaoRisco);
    }

    /**
     * Apaga a atividade a partir do parâmetro passado.
     * @param codigo representação do código da atividade a ser apagada.
     */
    public void apagarAtividade(String codigo){
        controllerAtividade.apagarAtividade(codigo);
    }

    /**
     * Cadastra um item em uma atividade a partir dos parâmetros passados.
     * @param codigo representação em String do código da atividade.
     * @param nomeItem representação em String do nome do item.
     */
    public void cadastraItem(String codigo, String nomeItem){
        controllerAtividade.cadastraItem(codigo, nomeItem);
    }

    /**
     * Exibe as informações da atividade, no formato: "descriçãoAtividade (risco - descriçãoRisco)"
     * @param codigo representação em String do código da atividade a ser exibida.
     * @return uma String com a representação textual da atividade.
     */
    public String exibeAtividade(String codigo){
        return controllerAtividade.exibeAtividade(codigo);
    }

    /**
     * Conta todos os itens que não foram realizados em uma atividade.
     * @param codigo representação em String do código da atividade.
     * @return um inteiro representando a quantidade de itens pendentes.
     */
    public int contaItensPendentes(String codigo){
        return controllerAtividade.contaItensPendentes(codigo);
    }

    /**
     * Conta todos os itens que foram realizados em uma atividade.
     * @param codigo representação em String do código da atividade.
     * @return um inteiro representando a quantidade de itens realizados.
     */
    public int contaItensRealizados(String codigo){
        return controllerAtividade.contaItensRealizados(codigo);
    }

    //Caso de uso 5 (Davi)

    /**
     * Associa um problema a uma pesquisa a partir dos IDs de cada uma.
     * @param idPesquisa representação em String do ID da pesquisa.
     * @param idProblema representação em String do ID do problema.
     * Verifica se os IDs são nulos ou vazios.
     * Verifica se o problema existe.
     * @return true se a operação foi realizada com sucesso, se não, retorna false.
     */
    public boolean associaProblema(String idPesquisa, String idProblema){
        Verificador.verificaVazioNulo(idPesquisa, "idPesquisa");
        Verificador.verificaVazioNulo(idProblema, "idProblema");
        controllerProblema.verificaProblema(idProblema);
        return controllerPesquisa.associaProblema(idPesquisa, controllerProblema.getProblema(idProblema));
    }

    /**
     * Desassocia o problema de uma pesquisa.
     * @param idPesquisa representação em String do ID da pesquisa.
     * @return true se a operação foi realizada com sucesso, se não, retorna false.
     */
    public boolean desassociaProblema(String idPesquisa){
        return controllerPesquisa.desassociaProblema(idPesquisa);
    }

    /**
     * Associa um objetivo a uma pesquisa a partir dos IDs de cada.
     * Verifica se os IDs são nulos ou vazios.
     * Verifica se o objetivo existe.
     * @param idPesquisa representação em String do ID de pesquisa.
     * @param idObjetivo representação em String do ID de objetivo.
     * @return true se a operação foi realizada com sucesso, se não, retorna false.
     */
    public boolean associaObjetivo(String idPesquisa, String idObjetivo){
        Verificador.verificaVazioNulo(idPesquisa,"idPesquisa");
        Verificador.verificaVazioNulo(idObjetivo,"idObjetivo");
        controllerObjetivo.verificaObjetivo(idObjetivo);
        return controllerPesquisa.associaObjetivo(idPesquisa, controllerObjetivo.getObjetivo(idObjetivo));
    }

    /**
     * Desassocia um objetivo de uma pesquisa a partir dos parâmetros passados.
     * @param idPesquisa representação em String do ID de pesquisa.
     * @param idObjetivo representação em String do ID de objetivo.
     * @return true se a operação foi realizada com sucesso, se não, retorna false.
     */
    public boolean desassociaObjetivo(String idPesquisa, String idObjetivo){
        return controllerPesquisa.desassociaObjetivo(idPesquisa, idObjetivo);
    }

    /**
     * Lista todas as pesquisas a partir de uma ordem dada.
     * @param ordem representação em String da ordem que a lista terá.
     * @return uma String contendo todas as pesquisas ordenadas.
     */
    public String listaPesquisas(String ordem) {
        return controllerPesquisa.listaPesquisas(ordem);
    }

    //Caso de uso 6 (Nestor)

    /**
     * Retorna um valor booleano que informa se um pesquisador foi desassociado de uma pesquisa, sendo verdadeiro quando desassociado e falso caso não ocorra a desassociação.
     *  O motivo de não desassociar acontece caso o pesquisado não esteja associado.
     * @param idPesquisa Representação em String do código que identifica pesquisas.
     * @param emailPesquisador email do pesquisador, no formato "nome@gmail.com".
     * @return a representação booleana da associação dos pesquisador com a pesquisa.
     */
    public boolean associaPesquisador(String idPesquisa, String emailPesquisador){
        if(idPesquisa == null || idPesquisa.equals("")){
            throw new RuntimeException("Campo idPesquisa nao pode ser nulo ou vazio.");
        }
        if(emailPesquisador == null || emailPesquisador.equals("")){
            throw new RuntimeException("Campo emailPesquisador nao pode ser nulo ou vazio.");
        }

        return controllerPesquisa.associaPesquisador(idPesquisa, emailPesquisador, controllerPesquisador.getPesquisador(emailPesquisador));
    }

    /**
     * Retorna um valor booleano que informa se um pesquisador foi desassociado de uma pesquisa, sendo verdadeiro quando desassociado e falso caso não ocorra a desassociação.
     * O motivo de não desassociar acontece caso o pesquisado não esteja associado.
     * @param idPesquisa Representação em String do código que identifica pesquisas.
     * @param emailPesquisador email do pesquisador, no formato "nome@gmail.com".
     * @return a representação booleana da desassociação dos pesquisador com a pesquisa.
     */
    public boolean desassociaPesquisador(String idPesquisa, String emailPesquisador){
        if(idPesquisa == null || idPesquisa.equals("")) {
            throw new RuntimeException("Campo idPesquisa nao pode ser nulo ou vazio.");
        }
        if(emailPesquisador == null || emailPesquisador.equals("")){
            throw new RuntimeException("Campo emailPesquisador nao pode ser nulo ou vazio.");
        }

        return controllerPesquisa.desassociaPesquisadores(idPesquisa, emailPesquisador);
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
        controllerPesquisador.cadastraEspecialidadeProfessor(email, formacao, unidade, data);
    }

    /**
     * Cadastra a especialidade aluno em um pesquisador, adicionando o seu semeste e IEA.
     * @param email email do pesquisador.
     * @param semestre semestre do aluno.
     * @param iea índice de eficiência acadêmica do aluno.
     */
    public void cadastraEspecialidadeAluno(String email, int semestre, Double iea){
        if(email == null || email.equals("")){
            throw new RuntimeException("Campo email nao pode ser nulo ou vazio.");
        }
        if(semestre < 1){
            throw new RuntimeException("Atributo semestre com formato invalido.");
        }
        if(iea < 0 || iea > 10){
            throw new RuntimeException("Atributo IEA com formato invalido.");
        }
        controllerPesquisador.cadastraEspecialidadeAluno(email, semestre, iea);
    }

    /**
     * Lista os pesquisadores pelo tipo, podendo ser externo, aluna e professora.
     * @param tipo tipo da especialidade do pesquisador.
     * @return a representação em String da lista dos pesquisadores por tipo.
     */
    public String listaPesquisadores(String tipo) {
        return controllerPesquisador.listaPesquisadores(tipo);
    }

    // Caso de uso 7 - Henrique
    public boolean associaAtividade(String codigoPesquisa, String codigoAtividade) {
        Verificador.verificaVazioNulo(codigoPesquisa, "codigoPesquisa");
        Verificador.verificaVazioNulo(codigoAtividade, "codigoAtividade");

        return controllerPesquisa.associaAtividade(codigoPesquisa, codigoAtividade, controllerAtividade.getAtividade(codigoAtividade));
    }

    public boolean desassociaAtividade(String codigoPesquisa, String codigoAtividade) {
        Verificador.verificaVazioNulo(codigoPesquisa, "codigoPesquisa");
        Verificador.verificaVazioNulo(codigoAtividade, "codigoAtividade");
        controllerAtividade.getAtividade(codigoAtividade);

        return controllerPesquisa.desassociaAtividade(codigoPesquisa, codigoAtividade);
    }

    public void executaAtividade(String codigoAtividade, int item, int duracao) {
        Verificador.verificaVazioNulo(codigoAtividade, "codigoAtividade");
        if(item <= 0) {
            throw new IllegalArgumentException("Item nao pode ser nulo ou negativo.");
        }
        if(duracao <= 0) {
            throw new IllegalArgumentException("Duracao nao pode ser nula ou negativa.");
        }
        if(controllerPesquisa.associacao(codigoAtividade)) {
            controllerAtividade.executaAtividade(codigoAtividade, item, duracao);
        }else{
            throw new IllegalArgumentException("Atividade sem associacoes com pesquisas.");
        }
    }

    public int cadastraResultado(String codigoAtividade, String resultado) {
        Verificador.verificaVazioNulo(codigoAtividade, "codigoAtividade");
        Verificador.verificaVazioNulo(codigoAtividade, "codigoAtividade");
        if (resultado == null || resultado.equals("")){
            throw new IllegalArgumentException("Resultado nao pode ser nulo ou vazio.");
        }

        return controllerAtividade.cadastraResultado(codigoAtividade, resultado);
    }

    public boolean removeResultado(String codigoAtividade, int numeroResultado) {
        Verificador.verificaVazioNulo(codigoAtividade, "codigoAtividade");
        if(numeroResultado <= 0) {
            throw new IllegalArgumentException("numeroResultado nao pode ser nulo ou negativo.");
        }

        return controllerAtividade.removeResultado(codigoAtividade, numeroResultado);
    }

    public String listaResultados(String codigoAtividade) {
        Verificador.verificaVazioNulo(codigoAtividade, "codigoAtividade");
        return controllerAtividade.listaResultados(codigoAtividade);
    }

    public int getDuracao(String codigoAtividade) {
        Verificador.verificaVazioNulo(codigoAtividade, "codigoAtividade");
        return controllerAtividade.getDuracao(codigoAtividade);
    }

    // caso de uso 8 - Anderson

    /**
     *  Realiza uma busca por um termo no Sistema Psquiza. A busca eh feita nos controllers de Pesquisa, Pesquisador, Problema, Objetivo
     *  e Atividade, seguindo esta ordem.
     *
     * @param termo String representando um termo a ser buscado no Sistema Psquiza.
     * @return String representando todos os resultados da busca do termo.
     */
    public String busca(String termo){
        return buscador.buscaGeral(termo);
    }

    /**
     *  Realiza uma busca por um termo no Sistema Psquiza para obter um resultado em uma posicao especifica.
     *  A busca eh feita nos controllers de Pesquisa, Pesquisador, Problema, Objetivo e Atividade, em seguida eh
     *  buscado um resultado em uma determinada posicao dentre os resultados gerais obtidos.
     *
     * @param termo String representando um termo a ser buscado no Sistema Psquiza.
     * @param posicao Inteiro representando uma posicao a ser acessado um resultado na lista de resultados.
     * @return String representando o resultado de determinada posicao na busca.
     */
    public String buscaPorNumero(String termo, int posicao){
        return buscador.buscaPorNumero(termo, posicao);
    }

    /**
     * Realiza uma busca por um termo no Sistema Psquiza para obter o numero de resultados encontrados.
     * A busca eh feita nos controllers de Pesquisa, Pesquisador, Problema, Objetivo e Atividade, em seguida eh
     * feita uma contagem do numero de resultados encontrados na busca daquele termo.
     *
     * @param termo String representando um termo a ser buscado no Sistema Psquiza.
     * @return Inteiro representando o numero de resultados encontrados pela busca do termo.
     */
    public int contaResultadosBusca(String termo){
        return buscador.contaResultados(termo);
    }

    //Caso de Uso 10 (Henrique)
    public void configuraEstrategia(String estrategia) {
        if (estrategia == null || estrategia.equals("")){
            throw new IllegalArgumentException("Estrategia nao pode ser nula ou vazia.");
        }
        if (!(estrategia.equals("MAIS_ANTIGA") || estrategia.equals("MENOS_PENDENCIAS") || estrategia.equals("MAIOR_RISCO") || estrategia.equals("MAIOR_DURACAO"))){
            throw new IllegalArgumentException("Valor invalido da estrategia");
        }
        controllerPesquisa.configuraEstrategia(estrategia);
    }

    public String proximaAtividade(String codigoPesquisa) {
        if (codigoPesquisa == null || codigoPesquisa.equals("")){
            throw new IllegalArgumentException("Pesquisa nao pode ser nula ou vazia.");
        }
        return controllerPesquisa.proximaAtividade(codigoPesquisa);
    }


    // Caso de Uso 11 (Nestor)

    /**
     * Grava em um arquivo .txt o resumo da pesquisa.
     * @param codigoPesquisa representação em String do código que identifica pesquisas.
     * @throws IOException Exceção lançada caso a escrita de arquivo falhe.
     */
    public void gravarResumo(String codigoPesquisa) throws IOException {
        if (codigoPesquisa == null ||codigoPesquisa.equals("")){
            throw new RuntimeException("Pesquisa nao pode ser nula ou vazia.");
        }
        controllerPesquisa.verificaPesquisa(codigoPesquisa);
        controllerPesquisa.gravarResumo(codigoPesquisa);
    }

    /**
     * Grava em um arquivo .txt o resultado da pesquisa
     * @param codigoPesquisa representação em String do código que identifica pesquisas.
     * @throws IOException Exceção lançada caso a escrita de arquivo falhe.
     */
    public void gravarResultados(String codigoPesquisa) throws IOException{
        if (codigoPesquisa == null || codigoPesquisa.equals("")){
            throw new RuntimeException("Pesquisa nao pode ser nula ou vazia.");
        }
        controllerPesquisa.verificaPesquisa(codigoPesquisa);
        controllerPesquisa.gravarResultados(codigoPesquisa);
    }

    // Caso de Uso 12 (Davi)

    /**
     * Salva todos os mapas e seus contadores em um arquivo txt.
     */
    public void salva() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            controllerPesquisa.grava(objectOutputStream);
            controllerPesquisador.grava(objectOutputStream);
            controllerObjetivo.grava(objectOutputStream);
            controllerProblema.grava(objectOutputStream);
            controllerAtividade.grava(objectOutputStream);
            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carrega todos os mapas e seus contadores a partir de um arquivo txt.
     */
    public void carrega() {
        if (file.exists() && file.length() > 0){
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                controllerPesquisa.carrega(objectInputStream);
                controllerPesquisador.carrega(objectInputStream);
                controllerObjetivo.carrega(objectInputStream);
                controllerProblema.carrega(objectInputStream);
                controllerAtividade.carrega(objectInputStream);
                objectInputStream.close();
                fileInputStream.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    //Caso de uso 9 (Anderson)

    public void defineProximaAtividade(String idPrecedente, String idSubsequente){
        controllerAtividade.defineProximaAtividade(idPrecedente, idSubsequente);
    }

    public void tiraProximaAtividade(String idPrecedente){
        controllerAtividade.tiraProximaAtividade(idPrecedente);
    }

    public int contaProximos(String idPrecedente){
        return controllerAtividade.contaProximos(idPrecedente);
    }

    public String pegaProximo(String idAtividade, int enesimaAtividade){
        return controllerAtividade.pegaProximo(idAtividade, enesimaAtividade);
    }

    public String pegaMaiorRiscoAtividades(String idAtividade) {
        return controllerAtividade.pegaMaiorRiscoAtividades(idAtividade);
    }
}
