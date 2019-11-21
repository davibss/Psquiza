package com.psquiza.controllers;

import com.psquiza.comparators.CompararStringNumero;
import com.psquiza.entidades.Atividade;
import com.psquiza.verificadores.Verificador;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

/**
 * Controller do objeto Atividade
 * @author davibss - 119111034
 */
public class ControllerAtividade {
    /** Mapa de atividades, em que a chave é o código e o valor é o objeto Atividade*/
    private Map<String, Atividade> atividades;
    /** Quantidade de atividades criadas no sistema*/
    private int atividadesCriadas;


    /**
     * Constrói o controller, inicializando mapa e a quantidade de atividades criadas,
     * por padrão a quantidade de atividades criadas é 1, pois a contagem começa de 1.
     */
    public ControllerAtividade() {
        this.atividades = new HashMap<>();
        this.atividadesCriadas = 1;
    }

    /**
     * Cadastra atividades no mapa de atividades.
     * Verifica se os parâmetros são vazios ou nulos,
     * Verifica se o risco é BAIXO, MEDIO OU ALTO.
     * @param descricao representação em String da descrição da atividade.
     * @param risco representação em String do risco da atividade [BAIXO,MEDIO OU ALTO]
     * @param descricaoRisco representação em String da descrição do risco.
     */
    public void cadastrarAtividades(String descricao, String risco, String descricaoRisco) {
        verificaValidadeAtividade(descricao, risco, descricaoRisco);
        String chave = "A"+(this.atividadesCriadas);
        this.atividades.put(chave, new Atividade(chave, descricao, risco, descricaoRisco));
        this.atividadesCriadas+=1;
    }

    /**
     * Verifica se os parâmetros passados são vazios ou nulos e se o risco é BAIXO, MEDIO OU ALTO.
     * @param descricao representação em String do objeto a ser testado.
     * @param risco representação em String do objeto a ser testado.
     * @param descricaoRisco representação em String do objeto a ser testado.
     */
    private void verificaValidadeAtividade(String descricao, String risco, String descricaoRisco) {
        Verificador.verificaVazioNulo(descricao, "Descricao");
        Verificador.verificaVazioNulo(risco, "nivelRisco");
        Verificador.verificaVazioNulo(descricaoRisco, "descricaoRisco");
        verificaValidadeRisco(risco);
    }

    /**
     * Verifica se o risco é BAIXO, MEDIO OU ALTO, se não for, lança exceção.
     * @param risco representação em String do risco.
     */
    private void verificaValidadeRisco(String risco) {
        List<String> riscos = Arrays.asList("BAIXO","MEDIO","ALTO");
        if (!riscos.contains(risco)){
            throw new IllegalArgumentException("Valor invalido do nivel do risco.");
        }
    }

    /**
     * Remove uma atividade do mapa de atividades a partir do código.
     * Verifica se o código é vazio ou nulo e se a atividade existe.
     * @param codigo representação em String do código da atividade.
     */
    public void apagarAtividade(String codigo) {
        Verificador.verificaVazioNulo(codigo, "codigo");
        verificaExistenciaAtividade(codigo);
        atividades.remove(codigo);
    }

    /**
     * Verifica se uma atividade existe, se não existe, lança exceção.
     * @param codigo representação em String do código da atividade.
     */
    private void verificaExistenciaAtividade(String codigo) {
        if (!atividades.containsKey(codigo)){
            throw new IllegalArgumentException("Atividade nao encontrada");
        }
    }

    /**
     * Cadastra item dentro de uma atividade no sistema.
     * @param codigo representação em String do código da atividade.
     * @param nomeItem representação em String do nome do item a ser cadastrado na atividade.
     */
    public void cadastraItem(String codigo, String nomeItem) {
        verificaValidadeItem(codigo, nomeItem);
        verificaExistenciaAtividade(codigo);
        this.atividades.get(codigo).cadastraItem(nomeItem);
    }

    /**
     * Verifica se o código da atividade e o nome do item são vazios ou nulos.
     * @param codigo reresentação em String do objeto a ser verificado.
     * @param nomeItem reresentação em String do objeto a ser verificado.
     */
    private void verificaValidadeItem(String codigo, String nomeItem) {
        Verificador.verificaVazioNulo(codigo, "codigo");
        Verificador.verificaVazioNulo(nomeItem, "Item");
    }

    /**
     * Exibe a atividade a partir do seu código.
     * Verifica se o código é vazio ou nulo, e se a atividade existe.
     * @param codigo representação em String da atividade.
     * @return String com a representaão da atividade.
     */
    public String exibeAtividade(String codigo) {
        Verificador.verificaVazioNulo(codigo,"codigo");
        verificaExistenciaAtividade(codigo);
        return this.atividades.get(codigo).toString();
    }

    /**
     * Conta todos os itens pendentes de uma atividade a partir do código.
     * Verifica se código é vazio ou nulo, e se a atividade existe.
     * @param codigo representação em String do código da atividade.
     * @return um inteiro com a quantidade de itens pendentes.
     */
    public int contaItensPendentes(String codigo) {
        Verificador.verificaVazioNulo(codigo,"codigo");
        verificaExistenciaAtividade(codigo);
        return (this.atividades.get(codigo).contaItensPendentes());
    }

    /**
     Conta todos os itens realizados de uma atividade a partir do código.
     * Verifica se código é vazio ou nulo, e se a atividade existe.
     * @param codigo representação em String do código da atividade.
     * @return um inteiro com a quantidade de itens realizados.
     */
    public int contaItensRealizados(String codigo) {
        Verificador.verificaVazioNulo(codigo, "codigo");
        verificaExistenciaAtividade(codigo);
        return (this.atividades.get(codigo).contaItensRealizados());
    }

    /**
     *  Faz uma busca por um termo em forma de String nos atributos Descricao e Descricao de risco das atividades. Retornando
     *  uma lista contendo os atributos onde o termo for encontrado.
     *
     * @param termo String representando um termo a ser buscado no Sistema Psquiza.
     * @return Lista de resultados da busca pelo termo, contendo atributos de Atividades onde o temro foi encontrado.
     */
    public List<String>  buscaAtividade(String termo){
        List<String> found = new ArrayList<>();
        //atividades.entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.reverseOrder())).
        atividades.entrySet().stream().sorted(Map.Entry.comparingByKey(new CompararStringNumero(-1))).
        forEach(entry -> {
            if (entry.getValue().getDescricao().toLowerCase().contains(termo)){
                found.add(entry.getKey() + ": " + entry.getValue().getDescricao());
            }
            if (entry.getValue().getDescricaoRisco().toLowerCase().contains(termo)){
                found.add(entry.getKey() + ": " + entry.getValue().getDescricaoRisco());
            }
        });
        return found;
    }

    /**
     * Retorna a atividade que possui o código passado como parâmetro
     *
     * @param codigo Código da atividade a ser retornada
     * @return A atividade com o código informado
     */
    public Atividade getAtividade(String codigo) {
        if(!this.atividades.containsKey(codigo)) {
            throw new IllegalArgumentException("Atividade nao encontrada");
        }
        return this.atividades.get(codigo);
    }

    /**
     * Executa um item da atividade com o código passado como parâmetro
     *
     * @param codigoAtividade Código da atividade que possui o item sendo executado
     * @param item Item a ser executado
     * @param duracao Duração da execução do item
     */
    public void executaAtividade(String codigoAtividade, int item, int duracao) {
        if(item <= 0) {
            throw new IllegalArgumentException("Item nao pode ser nulo ou negativo.");
        }
        if(duracao <= 0) {
            throw new IllegalArgumentException("Duracao nao pode ser nula ou negativa.");
        }
        if(!this.atividades.containsKey(codigoAtividade)) {
            throw new IllegalArgumentException("Atividade nao encontrada");
        }
        this.atividades.get(codigoAtividade).executaAtividade(item, duracao);
    }

    public int cadastraResultado(String codigoAtividade, String resultado) {
        Verificador.verificaVazioNulo(codigoAtividade, "codigoAtividade");
        Verificador.verificaVazioNulo(resultado, "Resultado");
        if(!this.atividades.containsKey(codigoAtividade)) {
            throw new IllegalArgumentException("Atividade nao encontrada");
        }
        return this.atividades.get(codigoAtividade).cadastraResultado(resultado);
    }

    public boolean removeResultado(String codigoAtividade, int numeroResultado) {
        Verificador.verificaVazioNulo(codigoAtividade, "codigoAtividade");
        if(!this.atividades.containsKey(codigoAtividade)) {
            throw new IllegalArgumentException("Atividade nao encontrada");
        }
        if(numeroResultado <= 0) {
            throw new IllegalArgumentException("numeroResultado nao pode ser nulo ou negativo.");
        }
        return this.atividades.get(codigoAtividade).removeResultado(numeroResultado);
    }

    public String listaResultados(String codigoAtividade) {
        Verificador.verificaVazioNulo(codigoAtividade, "codigoAtividade");
        if(!this.atividades.containsKey(codigoAtividade)) {
            throw new IllegalArgumentException("Atividade nao encontrada");
        }
        return this.atividades.get(codigoAtividade).listaResultados();
    }

    public int getDuracao(String codigoAtividade) {
        Verificador.verificaVazioNulo(codigoAtividade, "codigoAtividade");
        if(!this.atividades.containsKey(codigoAtividade)) {
            throw new IllegalArgumentException("Atividade nao encontrada");
        }
        return this.atividades.get(codigoAtividade).getDuracao();
    }

    /**
     * Grava o mapa de atividades e a contagem de atividades.
     * a partir de um objeto do tipo ObjectOutputStream passado como parâmetro.
     * @param objectOutputStream variável que permite escrever objetos em um arquivo.
     * @throws IOException Exceção lançada caso a escrita de arquivo falhe.
     */
    public void grava(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeObject(this.atividades);
        objectOutputStream.writeObject(this.atividadesCriadas);
    }

    /**
     * Carrega um objeto no mapa de atividades e no atributo atividadesCriadas.
     * @param objectInputStream variável que lê um objetos de um arquivo.
     * @throws IOException Exceção lançada caso a escrita de arquivo falhe.
     * @throws ClassNotFoundException Exceção lançada caso a escrita de arquivo falhe.
     */
    public void carrega(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        this.atividades = (Map<String, Atividade>) objectInputStream.readObject();
        this.atividadesCriadas = (int) objectInputStream.readObject();
    }

    private void verificaAtividade(String id){
        if (id == null){
            throw new NullPointerException("Atividade nao pode ser nulo ou vazio.");
        } else if (id.trim().equals("")){
            throw new IllegalArgumentException("Atividade nao pode ser nulo ou vazio.");
        }
        if (!atividades.containsKey(id)){
            throw new IllegalArgumentException("Atividade nao encontrada.");
        }
    }
    /**
     *  Define uma relação de ordem para duas Atividades, a Atividade de idSubsequente será
     *  definida como seguinte a Atividade de idPrecedente.
     *
     * @param idPrecedente String representando o código da Atividade a ser definida como precedente.
     * @param idSubsequente String representando o código da Atividade a ser definida como subsequente.
     */
    public void defineProximaAtividade(String idPrecedente, String idSubsequente){
        verificaAtividade(idPrecedente);
        verificaAtividade(idSubsequente);
        atividades.get(idPrecedente).defineProximaAtividade(atividades.get(idSubsequente));
    }
    /**
     *  Remove a relação de ordem de uma Atividade, não possuindo mais
     *  Atividades subsequentes.
     *
     * @param idPrecedente String representando o código da Atividade a ter sua próxima Atividade removida.
     */
    public void tiraProximaAtividade(String idPrecedente){
        verificaAtividade(idPrecedente);
        atividades.get(idPrecedente).tiraProximaAtividade();
    }
    /**
     * Conta a quantidade de Atividades subsequentes a Atividade de idPrecedente.
     *
     * @param idPrecedente String representando o código da Atividade a ser realizada a contagem de subsequentes.
     * @return Inteiro representando a quantidade de Atividades seguintes.
     */
    public int contaProximos(String idPrecedente){
        verificaAtividade(idPrecedente);
        return atividades.get(idPrecedente).contaProximos();
    }
    /**
     * Retorna a enésima Atividade subsequente a Atividade de idPrecedente.
     *
     * @param idPrecedente String representando o código da Atividade a ser iniciada a contagem.
     * @param enesimaAtividade Inteiro representando a posição da enésima Atividade subsequente a ser procurada.
     * @return String representando o código da enésima Atividade subsequente.
     */
    public String pegaProximo(String idPrecedente, int enesimaAtividade){
        verificaAtividade(idPrecedente);
        if (enesimaAtividade <= 0){
            throw new IllegalArgumentException("EnesimaAtividade nao pode ser negativa ou zero.");
        }
        return atividades.get(idPrecedente).pegaProximo(enesimaAtividade);
    }
    /**
     * Retorna a Atividade de Maior Risco dentre a Atividade de idAtividade e suas subsequentes.
     *
     * @param idAtividade String representando a Atividade a partir da qual será feita a busca.
     * @return String representando o código da Atividade de maior risco na sequência de Atividades.
     */
    public String pegaMaiorRiscoAtividades(String idAtividade) {
        verificaAtividade(idAtividade);
        return atividades.get(idAtividade).pegaMaiorRiscoAtividades();
    }
}
