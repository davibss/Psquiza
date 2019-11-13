package com.psquiza.entidades;

import com.psquiza.Especialidades.Aluno;
import com.psquiza.Especialidades.Professor;

import java.io.Serializable;

/**
 *  Representa um Pesquisador no Sistema.
 */
public class Pesquisador implements Serializable {
    /**
     *  Representacao em String da funcao de um pesquisador.
     */
    private String funcao;
    /**
     *  Representacao em String da Biografia de um pesquisador.
     */
    private String bio;
    /**
     *  Representacao em String do Nome de um pesquisador.
     */
    private String nome;
    /**
     *  Representacao em String do email de um pesquisador.
     */
    private String email;
    /**
     *  Representacao em String do endereco URL da foto de um pesquisador."
     */
    private String foto;
    /**
     *  Representacao em valor booleano da situacao de atividade de um pesquisador."
     */
    private boolean ativo;

    private Especialidade especialidade;

    /**
     *  Constroi um objeto Pesquisador a partir de informacoes de seu nome, funcao, biografia, email e foto. O pesquisador eh criado em estado ativo.
     *
     * @param nome String representando o nome de um(a) pesquisador(a).
     * @param funcao String representando a funcao de um(a) pesquisador(a).
     * @param bio String representando a biografia de um(a) pesquisador(a).
     * @param email String representando o email de um(a) pesquisador(a).
     * @param foto String representando o endereco URL da foto de um(a) pesquisador(a).
     */
    public Pesquisador(String nome, String funcao, String bio, String email, String foto) {
        this.funcao = funcao;
        this.bio = bio;
        this.nome = nome;
        this.email = email;
        this.foto = foto;
        this.ativo = true;
        this.especialidade = null;
    }

    public void adicionaEspecialidadeAluno(int semestre, Double iea){
        if(!funcao.equals("estudante")){
            throw new RuntimeException("Pesquisador nao compativel com a especialidade.");
        }
        this.especialidade = new Aluno(semestre, iea);
    }

    public void adicionaEspecialidadeProfessor(String formacao, String unidade, String data){
        if(!funcao.equals("professor")){
            throw new RuntimeException("Pesquisador nao compativel com a especialidade.");
        }
        this.especialidade = new Professor(formacao, unidade, data);
    }
    /**
     *  Metodo responsavel por verificar se um Pesquisador eh ativo, retornando true em caso afirmativo e false no contrario.
     *
     * @return Valor Boolean representando a situacao de atividade do Pesquisador.
     */
    public boolean ehAtivo() {
        return ativo;
    }

    public String getBio() {
        return bio;
    }

    public String getFuncao() {
        return funcao;
    }

    public void altera(String nomeAtributo, String atributo){
        if (this.funcao.equals("professor") && !(nomeAtributo.equals("FORMACAO") || nomeAtributo.equals("UNIDADE") || nomeAtributo.equals("DATA"))){
            throw new IllegalArgumentException("Atributo nao pertencem a esta especialidade.");
        }
        if (this.funcao.equals("estudante") && !(nomeAtributo.equals("SEMESTRE") || nomeAtributo.equals("IEA"))){
            throw new IllegalArgumentException("Atributo nao pertencem a esta especialidade.");
        }
        if (this.especialidade == null){
            throw new IllegalArgumentException("Pesquisador nao possui especialidade cadastrada para se modificar.");
        }
        especialidade.altera(nomeAtributo, atributo);
    }

    /**
     *  Define um valor String para o atributo email do Pesquisador.
     *
     * @param email String representando o novo email a ser definido.
     */

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *  Define um valor Boolean para o atributo ativo do Pesquisador.
     *
     * @param ativo Boolean representando o novo valor do atributo ativo a ser definido.
     */
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    /**
     *  Define um valor String para o atributo funcao do Pesquisador.
     *
     * @param funcao String representando o novo valor do atributo funcao a ser definido.
     */
    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    /**
     *  Define um valor String para o atributo Biografia do Pesquisador.
     *
     * @param bio String representando o novo valor do atributo bio a ser definido.
     */
    public void setBio(String bio) {
        this.bio = bio;
    }
    /**
     *  Define um valor String para o atributo nome do Pesquisador.
     *
     * @param nome String representando o novo valor do atributo nome a ser definido.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    /**
     *  Define um valor String para o atributo endereco URL da foto do Pesquisador.
     *
     * @param foto String representando o novo valor do atributo foto a ser definido.
     */
    public void setFoto(String foto) {
        this.foto = foto;
    }

    /**
     *  Faz a representacao do Pesquisador em forma de String, contendo seu nome, funcao, biografia, email e endereco de foto.
     *  No formato NOME (FUNCAO) - BIOGRAFIA - EMAIL - URLFOTO
     *
     * @return String representando um Pesquisador.
     */
    @Override
    public java.lang.String toString() {
        if (this.especialidade == null){
            return String.format("%s (%s) - %s - %s - %s", this.nome, this.funcao, this.bio, this.email, this.foto);
        }else{
            return String.format("%s (%s) - %s - %s - %s - %s", this.nome, this.funcao, this.bio, this.email, this.foto, this.especialidade.toString());
            //return (nome + " (" + funcao + ")" +  " - " + bio + " - " + email + " - " + foto + " - " + especialidade.toString());//+ especialidade.toString()
        }
    }


    /**
     *  Avalia se dois objetos sao do tipo Pesquisador e sao iguais. Retornando verdadeiro caso possuam o mesmo email.
     *
     * @param object Objeto a ser comparado se eh igual.
     * @return Valor Boolean indicando se os objetos sendo comparados sao iguais.
     */
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Pesquisador that = (Pesquisador) object;
        return java.util.Objects.equals(email, that.email);
    }

    /**
     *  Gera um codigo HashCode para identificar o Pesquisador a partir de seu email.
     *
     * @return Inteiro representando o codigo gerado de um objeto Pesquisador.
     */
    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), email);
    }
}
