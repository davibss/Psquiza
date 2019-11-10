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
        if ((nomeAtributo.equals("Item") || nomeAtributo.equals("Codigo") || nomeAtributo.equals("Motivo"))) {
            joiner.add(nomeAtributo);
        }else{
            joiner.add("Campo").add(nomeAtributo);
        }
        joiner.add("nao pode ser nulo ou vazio.");
        if (atributo == null || atributo.equals("")){
            throw new IllegalArgumentException(joiner.toString());
        }
    }

    public static boolean verificaData(String data){
        return data.matches("(^(((0[1-9]|[12][0-8])[/](0[1-9]|1[012]))|((29|30|31)[/](0[13578]|1[02]))|((29|30)[/](0[4,6,9]|11)))[/]" +
                "(19|[2-9][0-9])\\d\\d$)|(^29[/]02[/](19|[2-9][0-9])(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)$)");
    }

    public static boolean verificaEmail(String email){
        return email.matches("\\S+[@]\\S+");
    }

    public static boolean verificaFoto(String foto){
        return foto.matches("http[s]?://\\S*");

    }
}
