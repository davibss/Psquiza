package com.psquiza.entidades;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Representação de uma atividade no sistema.
 * A atividade possui codigo único, descrição, lista de itens, risco e descrição do risco.
 * @author davibss - 119111034
 */
public class Atividade implements Serializable {
    /** Atributo usado pelo serializable */
    private static final long serialVersionUID = 1L;
    /** Representação em String do código da atividade, no formato: A[número]*/
    private String codigo;
    /** Representação em String da descrição da atividade*/
    private String descricao;
    /** Representação em lista do objeto Item da atividade*/
    private List<Item> itens;
    /** Representação em String do risco da atividade, só poderá ser: BAIXO, MEDIO, ALTO*/
    private String risco;
    /** Representação em String da descrição do risco da atividade*/
    private String descricaoRisco;
    /** Representação em lista de todos os resultados da atividade. */
    private List<String> resultados;
    /** Representação da atividade subsequente desta atividade. */
    private Atividade nextAtividade;

    /**
     * Constrói atividade a partir dos parâmetros passados.
     * Inicia lista de itens.
     * A duração da atividade inicia como 0.
     * @param codigo representação em String do código da atividade
     * @param descricao representação em String da descrição da atividade
     * @param risco representação em String do risco da atividade
     * @param descricaoRisco representação em String da descrição do risco da atividade
     */
    public Atividade(String codigo, String descricao, String risco, String descricaoRisco) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.itens = new ArrayList<>();
        this.risco = risco;
        this.descricaoRisco = descricaoRisco;
        this.resultados = new ArrayList<>();
        this.nextAtividade = null;
    }

    /**
     * Retorna a descrição de atividade.
     * @return uma String com a descrição da atividade.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Retorna a descrição do risco da atividade.
     * @return uma String com a descrição do risco da atividade.
     */
    public String getDescricaoRisco() {
        return descricaoRisco;
    }

    /**
     * Retorna o risco da atividade, podendo ser [ALTO, MEDIO, BAIXO]
     * @return uma String com o risco da atividade.
     */
    public String getRisco() {
        return risco;
    }

    /**
     * Cadastra um novo item na lista de itens.
     * Verifica se já existe aquele item.
     * @param nomeItem representação em String do nome do item.
     */
    public void cadastraItem(String nomeItem){
        for (Item item : this.itens) {
            if (item.getNome().equals(nomeItem)){
                throw new IllegalArgumentException("Item ja cadastrado nesta atividade.");
            }
        }
        this.itens.add(new Item(nomeItem));
    }

    /**
     * Executa um dos itens da atividade, mudando seu estado para realizado
     *
     * @param item Índice do item a ser executado
     * @param duracao Duração da execução do item
     */
    public void executaAtividade(int item, int duracao) {
        if(item > this.itens.size()){
            throw new IllegalArgumentException("Item nao encontrado.");
        }
        this.itens.get(item - 1).executa(duracao);
    }

    public int cadastraResultado(String resultado) {
        this.resultados.add(resultado);
        return this.resultados.size();
    }

    public boolean removeResultado(int numeroResultado) {
        if(numeroResultado > this.resultados.size()) {
            throw new IllegalArgumentException("Resultado nao encontrado.");
        }
        if(this.resultados.get(numeroResultado - 1).equals("")) {
            return false;
        }
        this.resultados.set(numeroResultado - 1, "");
        return true;
    }

    public String listaResultados() {
        StringJoiner joiner = new StringJoiner(" | ");
        for(String resultado : this.resultados) {
            if(!resultado.equals("")) {
                joiner.add(resultado);
            }
        }
        return joiner.toString();
    }

    /**
     * Retorna o somatório da duração de todos os itens cadastrados nesta atividade.
     * @return um inteiro com a duração total.
     */
    public int getDuracao() {
        return this.itens.stream().mapToInt(Item::getDuracao).sum();
    }

    /**
     * Representação em String do objeto atividade, no formato:
     *  descricao - (risco - descricaoRisco) | statusItem - nomeItem
     * @return String com a representação da atividade.
     */
    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(" | ");
        joiner.add(String.format("%s (%s - %s)",this.descricao, this.risco, this.descricaoRisco));
        this.itens.forEach((r) -> joiner.add(r.toString()));
        return joiner.toString();
    }

    /**
     ** Retorna a representação em String do resumo de uma atividade.
     *  @return a representação em String do resumo de uma atividade.
     */
    public String toStringResumo() {
        AtomicInteger numero = new AtomicInteger(1);
        StringJoiner joiner = new StringJoiner("\n" +
                "            - ");
        joiner.add(String.format("        - %s (%s - %s)",this.descricao, this.risco, this.descricaoRisco));
        this.itens.forEach((r) -> joiner.add(r.estadoItem() + " - " + "ITEM" + (numero.getAndIncrement())));
        return joiner.toString();
    }
    /**
     ** Retorna a representação em String do resultado de uma atividade
     *  @return a representação em String do resultado de uma atividade.
     */
    public String toStringResultado() {
        AtomicInteger numero = new AtomicInteger(1);
        StringJoiner joiner = new StringJoiner("\n            - ");
        joiner.add(String.format("        - %s",this.descricao));
        this.itens.stream().filter(item -> item.getDuracao()>0).
                forEach((r) -> joiner.add("ITEM" + numero.getAndIncrement() + " - " + r.getDuracao()));
        this.resultados.stream().filter(s -> !s.equals("")).forEach(joiner::add);
        return joiner.toString();
    }

    /**
     * Compara dois objetos e retorna se são iguais.
     * @param o objeto a ser comparado.
     * @return true se for igual, se não, false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atividade atividade = (Atividade) o;
        return Objects.equals(codigo, atividade.codigo);
    }

    /**
     * Retorna representação única do objeto a partir do código.
     * @return representação em String da identificação do objeto.
     */
    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    /**
     * Retorna quantiadde de itens pendentes.
     * @return int representando a quantidade de itens pendentes.
     */
    public int contaItensPendentes() {
        return this.itens.size() - contaItensRealizados();
    }

    /**
     * Retorna quantiadde de itens realizados.
     * @return int representando a quantidade de itens realizados.
     */
    public int contaItensRealizados() {
        return (int) this.itens.stream().filter(Item::isRealizado).count();
    }

    /**
     *  Verifica se o codigo passsado como parametro é igual ao código desta Atividade ou de subsequentes, para
     *  evitar a criacao de loops em defineProximaAtividade.
     *
     * @param codigo String representando o codigo da segunda atividade a ser comparada
     * @return Valor Boolean representando se o código da Atividade é igual ao código passado.
     */
    public boolean verificaProxima(String codigo){
        if (this.nextAtividade == null){
            return this.codigo.equals(codigo);
        }
        return this.nextAtividade.verificaProxima(codigo);
    }
    /**
     * Define a Atividade subsequente a esta. Lança exceções caso seja possível a criação de loops
     * entre as Atividades e quando a Atividade já possui subsequente.
     *
     * @param proxima Atividade a ser definida como subsequente a esta.
     */
    public void defineProximaAtividade(Atividade proxima){
        if (proxima.verificaProxima(codigo)){
            throw new IllegalArgumentException("Criacao de loops negada.");
        }
        if (nextAtividade == null){
            nextAtividade = proxima;
        }else{
            throw new IllegalArgumentException("Atividade ja possui uma subsequente.");
        }
    }
    /**
     *  Remove a associacao em relação a Atividade subsequente. A Atividade
     *  passa a não ter mais subsequente.
     */
    public void tiraProximaAtividade(){
        nextAtividade = null;
    }
    /**
     * Realiza a contagem de quantas atividades subsequentes a Atividade base existem.
     *
     * @return Inteiro representando a quantidade de Atividades subsequentes a esta Atividade.
     */
    public int contaProximos(){
        // solução em 1 linha
        //return this.nextAtividade == null ? 0 : 1 + this.nextAtividade.contaProximos();
        if (this.nextAtividade == null){
            return 0;
        }
        return 1 + this.nextAtividade.contaProximos();
    }
    /**
     * Pega o código da enésima Atividade subsequente a esta.
     *
     * @param numero Inteiro representando a posicao da Atividade sendo buscada em relação a esta.
     * @return Código da Atividade na enésima posição buscada.
     */
    public String pegaProximo(int numero){
        if (this.contaProximos() < numero){
            throw new IllegalArgumentException("Atividade inexistente.");
        }
        if (numero == 0){
            return this.codigo;
        }
        return nextAtividade.pegaProximo(numero - 1);
    }
    /**
     * Retorna a Atividade de Maior Risco a partir de uma Atividade e suas subsequentes. Lança exceção caso
     * não exista próxima Atividade.
     *
     * @return String representando o código da Atividade de maior risco em uma sequência de Atividades.
     */
    public String pegaMaiorRiscoAtividades(){
        if (nextAtividade == null){
            throw new NullPointerException("Nao existe proxima atividade.");
        }
        return this.nextAtividade.pegaMaiorRiscoAtividadesRecursivo(this.nextAtividade);
    }
    /**
     *  Retorna a Atividade de maior risco em uma sequência de atividades. Recursivamente compara o risco
     *  da Atividade com o risco das Ativdades subsequentes, para encontrar o maior risco dentre as Atividades.
     *
     * @param maior Atividade que possui o maior risco encontrado até a iteração.
     * @return Código representando a Atividade de Maior Risco na seqûencia.
     */
    private String pegaMaiorRiscoAtividadesRecursivo(Atividade maior) {
        Map<String, Integer> mapaRiscos = new HashMap<String, Integer>(){{ put("ALTO", 3); put("MEDIO", 2); put("BAIXO", 1);}};
        if (this.nextAtividade == null){
            return maior.codigo;
        }else{
            return this.nextAtividade.pegaMaiorRiscoAtividadesRecursivo(
                    mapaRiscos.get(this.risco) >= mapaRiscos.get(maior.risco) ? this : maior);
        }
    }

}
