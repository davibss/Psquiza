package com.psquiza.verificadores;

import java.util.StringJoiner;

public class Verificador {

    /**
     * Verifica se o parâmetro passado é vazio ou nulo, se for, monta String
     * pra lançar uma exceção.
     * @param atributo representação em String do atributo a ser verificado.
     * @param nomeAtributo representação em String do nome do atributo.
     */
    public static void verificaVazioNulo(String atributo, String nomeAtributo) {
        StringJoiner joiner = new StringJoiner(" ");
        if (Character.isUpperCase(nomeAtributo.charAt(0)) && !nomeAtributo.equals("Descricao")){
            joiner.add(nomeAtributo);
        }else{
            joiner.add("Campo").add(nomeAtributo);
        }
        joiner.add("nao pode ser nulo ou vazio.");
        if (atributo == null || atributo.equals("")){
            throw new IllegalArgumentException(joiner.toString());
        }
    }

    /**
     * Método que verifica se uma data está no formato dd/MM/yyyy, considerando o meses diferentes, fevereiro e ano bissexto.
     * @param data representação em String da data a ser verificada.
     * @return true se a data estiver no formato, se não, retorna false.
     */
    public static boolean verificaData(String data){
        return data.matches("(^(((0[1-9]|[12][0-8])[/](0[1-9]|1[012]))|((29|30|31)[/](0[13578]|1[02]))|((29|30)[/](0[4,6,9]|11)))[/]" +
                "(19|[2-9][0-9])\\d\\d$)|(^29[/]02[/](19|[2-9][0-9])(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)$)");
    }

    /**
     * Método que verifica se um email é válido.
     * O formato certo exige que exista texto antes e depois do "@" obrigatoriamente.
     * @param email representação em String do email a ser verificado.
     * @return true se o email for válido, false se não for.
     */
    public static boolean verificaEmail(String email){
        return email.matches("\\S+[@]\\S+");
    }

    /**
     * Método que verifica se uma foto está no formato válido.
     * O formato exige que a foto comece com http:// ou https://
     * @param foto representação em String da foto a ser verificada.
     * @return true se a foto for válida, false se não for.
     */
    public static boolean verificaFoto(String foto){
        return foto.matches("http[s]?://\\S*");
    }

    /**
     * Método que recebe uma função do tipo lambda e retorna a String da exceção que ela lança.
     * Este método é usado no assertEquals nos testes de unidade.
     * @param runnable variável que recebe a função lambda.
     * @return a mensagem da exceçao.
     */
    public static String verificaExcecao(Runnable runnable){
        try{
            runnable.run();
        } catch (RuntimeException e){
            return e.getMessage();
        }
        return null;
    }
}
