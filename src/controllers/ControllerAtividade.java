package com.psquiza.controllers;

import com.psquiza.entidades.Atividade;
import com.psquiza.verificadores.Verificador;

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
        verificaVazioNulo(descricao, "Descricao");
        verificaVazioNulo(risco, "nivelRisco");
        verificaVazioNulo(descricaoRisco, "descricaoRisco");
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
     * Verifica se o parâmetro passado é vazio ou nulo, se for, monta String
     * pra lançar uma exceção.
     * @param atributo representação em String do atributo a ser verificado.
     * @param nomeAtributo representação em String do nome do atributo.
     */
    private void verificaVazioNulo(String atributo, String nomeAtributo) {
        StringJoiner joiner = new StringJoiner(" ");
        if (!nomeAtributo.equals("item")) {
            joiner.add("Campo").add(nomeAtributo);
        }else{
            joiner.add("Item");
        }
        joiner.add("nao pode ser nulo ou vazio.");
        if (atributo == null || atributo.equals("")){
            throw new IllegalArgumentException(joiner.toString());
        }
    }

    /**
     * Remove uma atividade do mapa de atividades a partir do código.
     * Verifica se o código é vazio ou nulo e se a atividade existe.
     * @param codigo representação em String do código da atividade.
     */
    public void apagarAtividade(String codigo) {
        verificaVazioNulo(codigo, "codigo");
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
        verificaVazioNulo(codigo, "codigo");
        verificaVazioNulo(nomeItem, "item");
    }

    /**
     * Exibe a atividade a partir do seu código.
     * Verifica se o código é vazio ou nulo, e se a atividade existe.
     * @param codigo representação em String da atividade.
     * @return String com a representaão da atividade.
     */
    public String exibeAtividade(String codigo) {
        verificaVazioNulo(codigo,"codigo");
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
        verificaVazioNulo(codigo,"codigo");
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
        verificaVazioNulo(codigo,"codigo");
        verificaExistenciaAtividade(codigo);
        return (this.atividades.get(codigo).contaItensRealizados());
    }
    public List<String>  buscaAtividade(String termo){
        List<String> found = new ArrayList<>();

        atividades.entrySet().stream().sorted((chave1, chave2) -> chave1.getKey().compareTo(chave2.getKey()) * -1).
        forEach(entry -> {
            if (entry.getValue().getDescricao().toLowerCase().contains(termo)){
                found.add(entry.getKey() + ": " + entry.getValue().getDescricao());
            }
            if (entry.getValue().getDescricaoRisco().toLowerCase().contains(termo)){
                found.add(entry.getKey() + ": " + entry.getValue().getDescricaoRisco());
            }
        });
//        for (Map.Entry<String, Atividade> entry : atividades.entrySet()){
//            String descricao = entry.getValue().getDescricao();
//            String risco = entry.getValue().getDescricaoRisco();
//            if (descricao.toLowerCase().contains(termo)){
//                found.add(entry.getKey() + ": " + descricao);
//            }
//            if (risco.toLowerCase().contains(termo)){
//                found.add(entry.getKey() + ": " + risco);
//            }
//        }
        return found;
    }
}
