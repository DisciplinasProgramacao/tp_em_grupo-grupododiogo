package Utilitarios;

import java.util.Arrays;
import java.util.List;

public class DatasVoo {
    private static final List<String> datasDisponiveis = Arrays.asList(
        "21/10/2022",
        "15/08/2022",
        "19/04/2022",
        "06/05/2003",
        "07/06/2021");

    public static List<String> getDatasDisponiveis() {
        return datasDisponiveis;
    }
}
