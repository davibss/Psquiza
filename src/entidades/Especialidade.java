package com.psquiza.entidades;

/**
 * Representação de uma interface que comunica pesquisador com uma especialidade
 * @author José Nestor - 119110608
 */
public interface Especialidade {
    /**
     * Retorna a representação em String de uma especialidade.
     * @return a representação em String de uma especialidade.
     */
    String toString();

    /**
     * Altera um dos atributos de uma especialidade.
     * @param nomeAtributo nome do atributo que será alterado.
     * @param atributo atributo que substituirá o antigo.
     */
    void altera(String nomeAtributo, String atributo);
}
