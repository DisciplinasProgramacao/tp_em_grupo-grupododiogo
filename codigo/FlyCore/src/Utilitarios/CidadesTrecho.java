package Utilitarios;

import java.util.Arrays;
import java.util.List;

public class CidadesTrecho {
    private static final List<String> cidadesOrigem = Arrays.asList(
        "Belo Horizonte",
        "São Paulo",
        "Rio de Janeiro",
        "Rio Grande do sul",
        "Pernanbuco");

    private static final List<String> cidadesDestino = Arrays.asList(
        "Belo Horizonte",
        "São Paulo",
        "Rio de Janeiro",
        "Rio Grande do sul",
        "Pernanbuco");

    public static List<String> getCidadesOrigem() {
        return cidadesOrigem;
    }

    public static List<String> getCidadesDestino() {
        return cidadesDestino;
    }
}
