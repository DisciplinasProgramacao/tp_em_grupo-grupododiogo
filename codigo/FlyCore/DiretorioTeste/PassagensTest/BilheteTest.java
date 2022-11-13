package PassagensTest;


import Passagens.Bilhete;
import Passagens.Trecho;
import Passagens.Voo;
import Utilitarios.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BilheteTest {

    public Trecho bhParaSpTrecho;
    public Data dataVoo;
    public Voo bhParaSpVoo;
    public Bilhete bilhete;

    @BeforeEach
    public void setUp() {
        bhParaSpTrecho = new Trecho("Belo Horizonte", "São Paulo");
        dataVoo = new Data(06, 05, 2003);
        bhParaSpVoo = new Voo(bhParaSpTrecho, dataVoo, 100);
        bilhete = new Bilhete();
    }
    
    @Test
    public void toStringEmptyInfo() {
        assertEquals("Nenhum voo cadastrado", bilhete.toString());
    }

    @Test
    public void toStringInfoEInserirVoo() {
        assertTrue(bilhete.inserirVoo(bhParaSpVoo));

        assertEquals("=========== Bilhete Comum número: " + bilhete.getIdBilhete() + " ===========\n"+
        "=========== Voo Número: " + bhParaSpVoo.getIdVoo() + " ===========" + "\nData do Voo: " + dataVoo.dataFormatada() + "\nPreço do voo: " + bhParaSpVoo.getPreco() +
        "\nTrecho " + bhParaSpTrecho.getIdTrecho() + " de Belo Horizonte para São Paulo.\n\n" +
        "\nPreço total do bilhete: " + bilhete.calcularPreco() + "\n\nLembre-se de anotar o número do bilhete e dos voos, eles serão necessários depois ;)", bilhete.toString());
    }

    @Test
    public void removerVooTest() {
        assertTrue(bilhete.inserirVoo(bhParaSpVoo));

        assertTrue(bilhete.removerVoo(bhParaSpVoo.getIdVoo()));
    }

    @Test
    public void removerVooInexistenteTest() {
        assertTrue(bilhete.inserirVoo(bhParaSpVoo));

        assertFalse(bilhete.removerVoo(4521457));// id "aleatório"
    }

    @Test 
    public void calcular_Preco_encontrarMaiorValorVoo(){
        bilhete.inserirVoo(bhParaSpVoo);
        Trecho tr2 = new Trecho("Belo Horizonte", "São Paulo");
        Data data2 = new Data(06, 05, 2003);
        Voo voo2 = new Voo(tr2, data2, 250.0);
        bilhete.inserirVoo(voo2);

      assertEquals(300d, bilhete.calcularPreco());
    }
}
