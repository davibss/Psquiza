package com.psquiza.entidades;

import com.psquiza.comparators.CompararStringNumero;
import com.psquiza.verificadores.Verificador;

import java.io.Serializable;
import java.util.*;

/**
 * Representação de uma pesquisa no sistema.
 * A pesquisa possui um estado booleano que informa se está ativa ou desativa, descrição, campo de interesse, código de identificação, problema, objetivos, pesquisadores e atividades.
 * @author José Nestor - 119110608
 */
public class Pesquisa implements Serializable {
    /** Atributo usado pelo serializable */
    private static final long serialVersionUID = 1L;
    /** Representação em String do código que identifica pesquisas. */
    private String codigo;
    /** Representação boleana do estado da pesquisa, true para ativada e false para desativada. */
    private boolean estadoAtivacao;
    /** Representação em String da descrição da pesquisa. */
    private String descricao;
    /** Representação em String do campo de innteresse. */
    private String campoInteresse;
    /**
     * Problema que está associado com a pesquisa.
     */
    private Problema problema;
    /**
     * Mapa de objetivos que estão associados com a pesquisa.
     */
    private Map<String, Objetivo> objetivos;
    /**
     * Mapa de pesquisadores que estão associados com a pesquisa.
     */
    private Map<String, Pesquisador> pesquisadores;
    /**
     * Mapa de atividades que estão associadas com a pesquisa.
     */
    private Map<String, Atividade> atividades;


    /**
     * Constrói uma pesquisa através dos parâmetros. Inicializa o problema e suas listas de objetivos, pesquisadores e atividades.
     * @param codigo Representação em String do código que identifica pesquisas.
     * @param descricao representação em String da descrição da pesquisa.
     * @param campoInteresse representação em String do campo de innteresse.
     */
    public Pesquisa(String codigo, String descricao, String campoInteresse){
        Verificador.verificaVazioNulo(codigo, "codigo");
        Verificador.verificaVazioNulo(descricao, "descricao");
        Verificador.verificaVazioNulo(campoInteresse, "campoInteresse");
        this.codigo = codigo;
        this.estadoAtivacao = true;
        this.descricao = descricao;
        this.campoInteresse = campoInteresse;
        this.problema = null;
        this.objetivos = new LinkedHashMap<>();
        this.pesquisadores = new LinkedHashMap<>();
        this.atividades = new LinkedHashMap<>();
    }

    /**
     * Retorna um valor booleano que informa se um pesquisador foi associado a uma pesquisa, sendo verdadeiro quando associado e falso caso não seja associado.
     * O motivo de não associar acontece caso o pesquisado já esteja associado.
     * @param emailPesquisador email do pesquisador, no formato "nome@gmail.com".
     * @param pesquisador pesquisador, objeto que representa um pesquisador.
     * @return a representação booleana da associação dos pesquisador com a pesquisa.
     */
    public boolean associaPesquisador(String emailPesquisador, Pesquisador pesquisador){
        boolean retorno = true;
        if(pesquisadores.containsKey(emailPesquisador)){
            retorno = false;
        }
        this.pesquisadores.put(emailPesquisador, pesquisador);
        return retorno;
    }

    /**
     * Retorna um valor booleano que informa se um pesquisador foi desassociado de uma pesquisa, sendo verdadeiro quando desassociado e falso caso não ocorra a desassociação.
     * O motivo de não desassociar acontece caso o pesquisado não esteja associado.
     * @param emailPesquisador email do pesquisador, no formato "nome@gmail.com".
     * @return a representação booleana da desassociação dos pesquisador com a pesquisa.
     */
    public boolean desassociaPesquisador(String emailPesquisador){
        if(!pesquisadores.containsKey(emailPesquisador)){
            return false;
        }
        this.pesquisadores.remove(emailPesquisador);
        return true;
    }

    /**
     * Associa um problema a esta pesquisa a partir de um objeto do tipo Problema.
     * Verifica se já existe um problema associado a esta pesquisa, se sim, lança exceção.
     * @param problema representação do problema no tipo Problema
     * @return true se a operação for realizada com sucesso, e false se não.
     */
    public boolean associaProblema(Problema problema) {
        if (this.problema != null){
            if (this.problema.equals(problema)){
                return false;
            }
            throw new IllegalArgumentException("Pesquisa ja associada a um problema.");
        }
        this.problema = problema;
        return true;
    }

    /**
     * Desassocia um problema de uma pesquisa.
     * Se não houver nenhum problema associado, retorna false.
     * @return true se a operação foi realizada com sucesso, se não, false.
     */
    public boolean desassociaProblema(){
        if (this.problema == null){
            return false;
        }
        this.problema = null;
        return true;
    }

    /**
     * Associa um objetivo a esta pesquisa através de um atributo do tipo Objetivo.
     * Verifica se já existe este objetivo dentro desta pesquisa, se houver, retorna false.
     * @param objetivo representação do objetivo a ser cadastrado, do tipo Objetivo.
     * @return true se a operação foi realizada com sucesso, se não, false.
     */
    public boolean asssociaObjetivo(Objetivo objetivo){
        if (this.objetivos.containsValue(objetivo)){
            return false;
        }
        this.objetivos.put(objetivo.getId(), objetivo);
        return true;
    }

    /**
     * Desassocia um objetivo desta pesquisa, através da identificação única do objetivo.
     * Se este objetivo não estiver dentro desta pesquisa, retorna false.
     * @param idObjetivo representação em String do id do objetivo.
     * @return true se a operação foi realizada com sucesso, se não, false.
     */
    public boolean desassociaObjetivo(String idObjetivo){
        if (!this.objetivos.containsKey(idObjetivo)){
            return false;
        }
        this.objetivos.remove(idObjetivo);
        return true;
    }

    /**
     * Retorna a quantidade total de objetivos desta pesquisa.
     * @return uma representação em inteiro do total de objetivos cadastrados.
     */
    public int getObjetivos(){
        return this.objetivos.size();
    }

    /**
     * Retorna o id do maior objetivo.
     * @return uma String do maior objetivo dentro da pesquisa.
     */
    public String maiorObjetivo(){
        return Collections.max(this.objetivos.keySet(), new CompararStringNumero(1));
    }

    /**
     * Verifica se um objetivo está contido na lista de objetivos.
     * @param objetivo representação do tipo Objetivo do objetivo a ser verificado.
     * @return true se o objetivo estiver dentro da lista, se não, retorna false.
     */
    public boolean contemObjetivo(Objetivo objetivo){
        return this.objetivos.containsValue(objetivo);
    }

    /**
     * Armazena uma atividade em um mapa de atividades, sendo o código da atividade a chave e a atividade o valor
     * Retorna um valor booleano verdadeiro caso o armazenamento seja bem sucedido
     * ou falso caso o mapa já contenha aquela atividade
     *
     * @param codigoAtividade representaçao em String do código da atividade.
     * @param atividade objeto atividade.
     * @return  Um valor booleano que indica se o armazenamento foi bem sucedido
     */
    public boolean associaAtividade(String codigoAtividade, Atividade atividade) {
        if(this.atividades.containsKey(codigoAtividade)) {
            return false;
        }
        this.atividades.put(codigoAtividade, atividade);
        return true;
    }

    /**
     * Desassocia uma atividade que esteja associada a esta pesquisa
     * Retorna um valor booleano verdadeiro caso a desassociação seja bem sucedida
     * ou falso caso a atividade não esteja associada a esta pesquisa
     *
     * @param codigoAtividade Código da atividade sendo desassociada da pesquisa
     * @return Um valor booleano que indica se a desassociação foi bem sucedida
     */
    public boolean desassociaAtividade(String codigoAtividade) {
        if(!this.atividades.containsKey(codigoAtividade)) {
            return false;
        }
        this.atividades.remove(codigoAtividade);
        return true;
    }

    /**
     * Verifica se a pesquisa contém alguma atividade com pendencias e lança uma exceção caso ela não possua
     */
    private void conferePendencias(){
        boolean a = true;
        for(Atividade atividade : this.atividades.values()){
            if(atividade.contaItensPendentes() > 0) {
                a = false;
            }
        }
        if(a) {
            throw new IllegalArgumentException("Pesquisa sem atividades com pendencias.");
        }
    }

    /**
     * Retrona o código da próxima atividade que deve ser executada de acordo com a estratégia utilizada
     *
     * @param estrategia Estratégia a ser utilizada na sugestão da próxima atividade
     * @return O código da próxima atividade que deve ser executada nesta pesquisa.
     */
    public String proximaAtividade(String estrategia) {
        conferePendencias();
        String proxima = "";
        switch (estrategia) {
            case "MAIS_ANTIGA":
                proxima = this.atividades.entrySet().stream().filter(entry ->  entry.getValue().contaItensPendentes() > 0)
                        .findFirst().get().getKey();
                break;

            case "MENOS_PENDENCIAS":
                return this.atividades.entrySet().stream().filter(entry -> entry.getValue().contaItensPendentes() != 0).
                       sorted(Comparator.comparingInt(stringAtividadeEntry -> stringAtividadeEntry.getValue().contaItensPendentes())).
                       map(Map.Entry::getKey).findFirst().get();

            case "MAIOR_RISCO":
                return this.atividades.entrySet().stream().filter(entry -> entry.getValue().contaItensPendentes()!=0).
                      sorted((stringAtividadeEntry, t1) -> {
                          Map<String, Integer> mapaRiscos = new HashMap<String, Integer>(){{ put("ALTO", 3); put("MEDIO", 2); put("BAIXO", 1);}};
                          return mapaRiscos.get((stringAtividadeEntry.getValue().getRisco())).compareTo(mapaRiscos.get(t1.getValue().getRisco())) * -1;
                      }).map(Map.Entry::getKey).findFirst().get();

            case "MAIOR_DURACAO":
               return this.atividades.entrySet().stream().filter(entry -> entry.getValue().contaItensPendentes() != 0).
                       max(Comparator.comparingInt(stringAtividadeEntry -> stringAtividadeEntry.getValue().getDuracao())).
                       map(Map.Entry::getKey).get();
        }
        return proxima;
    }

    /**
     * Retorna um valor booleano verdadeiro caso a pesquisa esteja associada à atividade com o código passado como parâmetro
     *
     * @param codigoAtividade Código da atividade sendo verificada
     * @return Booleano que indica se a pesquisa está associada a esta atividade.
     */
    public boolean hasAtividade(String codigoAtividade){
        return this.atividades.containsKey(codigoAtividade);
    }

    /**
     * Retorna a representação da descrição de uma pesquisa em String.
     * @return a representação da descrição de uma pesquisa em String.
     */
    public String getDescricao() {
        return descricao;
    }
    /**
     * Retorna a representação do campo de interesse de uma pesquisa em String.
     * @return a representação do campo de interesse de uma pesquisa em String.
     */
    public String getCampoInteresse() {
        return campoInteresse;
    }
    /**
     * Retorna um problema.
     * @return a representação do problema de uma pesquisa no tipo Problema.
     */
    public Problema getProblema() {
        return this.problema;
    }

    /**
     * Retorna uma String que representa uma pesquisa.
     * A representação segue o formato: " - descrição - campo de interesse".
     *
     * @return Representação em String de uma pesquisa.
     */
    @Override
    public String toString() {
        return String.format("%s - %s - %s",this.codigo,this.descricao,this.campoInteresse);
    }

    /**
     * Compara um objeto do tipo desta classe com outro objeto.
     * Se forem iguais, códigos iguais, retorna true.
     * @param o objeto a ser comparado.
     * @return true se for igual, se não, false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pesquisa pesquisa = (Pesquisa) o;
        return Objects.equals(codigo, pesquisa.codigo);
    }

    /**
     * Gera a representação única deste objeto a partir do código.
     * @return representação única em inteiro.
     */
    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    /**
     * Retorna o código desta pesquisa.
     * @return a identificação única da classe Pesquisa.
     */
    public String getCodigo() {
        return this.codigo;
    }

    /**
     * Define um valor String para o atributo campoInteresse
     * @param campoInteresse String representando novo valor do atributo campoInteresse
     */
    public void setCampoInteresse(String campoInteresse) {
        this.campoInteresse = campoInteresse;
    }

    /**
     * Define um valor String para o atributo descricao
     * @param descricao String representando novo valor do atributo descricao
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Define um valor String para o atributo estadoAtivacao
     * @param estadoAtivacao String representando novo valor do atributo estadoAtivacao
     */
    public void setEstadoAtivacao(boolean estadoAtivacao) {
        this.estadoAtivacao = estadoAtivacao;
    }

    /**
     * Retorna um valor boleano que representa o estado de ativação da pesquisa
     * @return A representação boleana do estado da pesquisa
     */
    public boolean estadoAtivacao() {
        return estadoAtivacao;
    }

    /**
     * Lista os pesquisadores associados a uma pesquisa.
     * @return a representação em String da lista dos pesquisadores associados a uma pesquisa.
     */
    public String listaPesquisadores() {
        StringJoiner joiner = new StringJoiner("\n");
        this.pesquisadores.values().forEach(pesquisador -> joiner.add("        - " + pesquisador.toString()));
        return joiner.toString();
    }

    /**
     * Retorna a representação em String de um problema associado a pesquisa.
     * @return a representação em String de um problema associado a pesquisa.
     */
    public String getProblemaResumo() {
        return problema.toString();
    }

    /**
     ** Retorna a representação em String dos objetivos associados a pesquisa.
     *  @return a representação em String dos obejetivos associados a pesquisa.
     */
    public String getObjetivosResumo() {
        StringJoiner joiner = new StringJoiner("\n");
        this.objetivos.values().forEach(objetivo -> joiner.add("        - " + objetivo.toString()));
        return joiner.toString();
    }
    /**
     ** Retorna a representação em String do resumo das atividades associados a pesquisa.
     *  @return a representação em String do resumo das atividades associados a pesquisa.
     */
    public String getAtividadesResumo(){
        StringJoiner joiner = new StringJoiner("\n");
        this.atividades.values().forEach(atividade -> joiner.add(atividade.toStringResumo()));
        return joiner.toString();
    }
    /**
     ** Retorna a representação em String do resultado das atividades associados a pesquisa.
     *  @return a representação em String do resultado das atividades associados a pesquisa.
     */
    public String getAtividadesResultado(){
        StringJoiner joiner = new StringJoiner("\n");
        this.atividades.values().forEach(atividade -> joiner.add(atividade.toStringResultado()));
        return joiner.toString();
    }
}
