package com.psquiza.entidades;

import com.psquiza.comparators.AtividadeMaisAntiga;

import java.io.Serializable;
import java.util.*;

/**
 * Representação de uma pesquisa no sistema.
 * A pesquisa possui estado que informa se está ativa ou desativa, descrição, campo de interesse.
 * @author José Nestor - 119110608
 */
public class Pesquisa implements Serializable {
    private static final long serialVersionUID = 1L;

    private String codigo;
    /** Representação boleana do estado da pesquisa, true para ativada e false para desativada*/
    private boolean estadoAtivacao;
    /** Representação em String da descrição da pesquisa*/
    private String descricao;
    /** Representação em String do campo de innteresse*/
    private String campoInteresse;
    //Nova implementação - caso de uso 5
    private Problema problema;
    private Map<String, Objetivo> objetivos;

    private Map<String, Pesquisador> pesquisadores;

    private Map<String, Atividade> atividades;


    /**
     * Constrói uma pesquisa através dos parâmetros
     * @param estadoAtivacao representação boleana do estado da pesquisa
     * @param descricao representação em String da descrição da pesquisa
     * @param campoInteresse representação em String do campo de innteresse
     */
    public Pesquisa(String codigo, boolean estadoAtivacao, String descricao, String campoInteresse){
        this.codigo = codigo;
        this.estadoAtivacao = estadoAtivacao;
        this.descricao = descricao;
        this.campoInteresse = campoInteresse;
        this.problema = new Problema();
        this.objetivos = new LinkedHashMap<>();
        this.pesquisadores = new LinkedHashMap<>();
        this.atividades = new LinkedHashMap<>();
    }

    public boolean associaPesquisador(String emailPesquisador, Pesquisador pesquisador){
        if(pesquisadores.containsKey(emailPesquisador)){
            return false;
        }
        this.pesquisadores.put(emailPesquisador, pesquisador);
        return true;
    }

    public boolean desassociaPesquisador(String emailPesquisador){
        if(!pesquisadores.containsKey(emailPesquisador)){
            return false;
        }
        this.pesquisadores.remove(emailPesquisador);
        return true;
    }

    public boolean associaProblema(Problema problema) {
        if (this.problema.equals(problema)){
            return false;
        }
        if (!(this.problema.equals(new Problema()))){
            throw new IllegalArgumentException("Pesquisa ja associada a um problema.");
        }
        this.problema = problema;
        return true;
    }

    public boolean desassociaProblema(){
        if (this.problema.getIdProblema() == null){
            return false;
        }
//        else if (!this.problema.getIdProblema().equals(problema)){
//            return false;
//        }
        this.problema = new Problema();
        return true;
    }

    public boolean asssociaObjetivo(Objetivo objetivo){
        if (this.objetivos.containsValue(objetivo)){
            return false;
        }
        this.objetivos.put(objetivo.getId(), objetivo);
        return true;
    }

    public boolean desassociaObjetivo(String idObjetivo){
        if (!this.objetivos.containsKey(idObjetivo)){
            return false;
        }
        this.objetivos.remove(idObjetivo);
        return true;
    }

    public int getObjetivos(){
        return this.objetivos.size();
    }

    public String maiorObjetivo(){
        //return Collections.max(this.objetivos, (chave1, chave2) -> chave1.compareTo(chave2) * -1);
        return Collections.max(this.objetivos.keySet(), (chave1, chave2) -> chave1.compareTo(chave2) * -1);
    }

    public boolean contemObjetivo(Objetivo objetivo){
        //return this.objetivos.contains(objetivo);
        return this.objetivos.containsValue(objetivo);
    }

    public boolean associaAtividade(String codigoAtividade, Atividade atividade) {
        if(this.atividades.containsKey(codigoAtividade)) {
            return false;
        }
        this.atividades.put(codigoAtividade, atividade);
        return true;
    }

    public boolean desassociaAtividade(String codigoAtividade) {
        if(!this.atividades.containsKey(codigoAtividade)) {
            return false;
        }
        this.atividades.remove(codigoAtividade);
        return true;
    }

    public String proximaAtividade(String estrategia) {
        String proxima = "";
        switch (estrategia) {
            case "MAIS_ANTIGA":
                //sugestao
//                proxima = this.atividades.entrySet().stream().filter(entry ->  entry.getValue().contaItensPendentes() > 0)
//                        .findFirst().get().getKey();
                List<String> lista = new ArrayList<>();
                lista.addAll(this.atividades.keySet());
                Collections.sort(lista, new AtividadeMaisAntiga());
                for(String codigoAtividade : lista){
                    if(this.atividades.get(codigoAtividade).contaItensPendentes() != 0) {
                        proxima = codigoAtividade;
                    }
                }
                break;

            case "MENOS_PENDENCIAS":
                Iterator<String> itr1 = this.atividades.keySet().iterator();
                proxima = itr1.next();
                int pendencias = this.atividades.get(proxima).contaItensPendentes();
                while (itr1.hasNext()) {
                    String codigoAtividade = itr1.next();
                    if(this.atividades.get(codigoAtividade).contaItensPendentes() == pendencias) { }
                    
                    if((this.atividades.get(codigoAtividade).contaItensPendentes() < pendencias && this.atividades.get(codigoAtividade).contaItensPendentes() != 0) || pendencias == 0) {
                        proxima = codigoAtividade;
                        pendencias = this.atividades.get(codigoAtividade).contaItensPendentes();
                    }
                }
                break;

            case "MAIOR_RISCO":
                break;
            case "MAIOR_DURACAO":
                Iterator<String> itr2 = this.atividades.keySet().iterator();
                proxima = itr2.next();
                int duracao = this.atividades.get(proxima).getDuracao();
                while (itr2.hasNext()) {
                    String codigoAtividade = itr2.next();
                    if(this.atividades.get(codigoAtividade).getDuracao() > duracao && this.atividades.get(codigoAtividade).contaItensPendentes() != 0) {
                        proxima = codigoAtividade;
                        duracao = this.atividades.get(codigoAtividade).getDuracao();
                    }
                }
                break;
        }
        return proxima;
    }

    public boolean hasAtividade(String codigoAtividade){
        return this.atividades.containsKey(codigoAtividade);
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCampoInteresse() {
        return campoInteresse;
    }

    public String getProblema() {
        return this.problema.getIdProblema();
    }

    /**
     * Retorna uma String que representa uma pesquisa.
     * A representação segue o formato: " - descrição - campo de interesse".
     *
     * @return Representação em String de uma pesquisa.
     */
    @Override
    public String toString() {
        //return " - " + descricao + " - " +campoInteresse;
        return String.format("%s - %s - %s",this.codigo,this.descricao,this.campoInteresse);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pesquisa pesquisa = (Pesquisa) o;
        return Objects.equals(codigo, pesquisa.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

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

    public String listaPesquisadores() {
        return null;
    }

    public String getProblemaResumo() {
        return null;
    }

    public String getObjetivosResumo() {
        return null;
    }

    public String getAtividadesResumo(){
        StringJoiner joiner = new StringJoiner("\n");
        this.atividades.values().forEach(atividade -> joiner.add(atividade.toStringResumo()));
        return joiner.toString();
    }
}
