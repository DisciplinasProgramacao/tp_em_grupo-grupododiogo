package PassagensTest;

import Passagens.Bilhete;
import Passagens.Trecho;
import Passagens.Voo;
import Utilitarios.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VooTest {

    public Trecho bhParaSp;
    public Data dataVoo;
    public Voo bhParaSpVoo;
    public Bilhete bilhete;

    public Trecho bhParaRj;
    public Data dataVoo2;
    public Voo bhParaRjVoo;

    @BeforeEach
    public void setUp(){
        bhParaSp = new Trecho("Belo Horizonte", "São Paulo");
        dataVoo = new Data(06, 05, 2003);
        bhParaSpVoo = new Voo(bhParaSp, dataVoo, 0);
        bilhete = new Bilhete();

        bhParaRj = new Trecho("Belo Horizonte", "Rio de Janeiro");
        dataVoo2 = new Data(06, 05, 2003);
        bhParaRjVoo = new Voo(bhParaRj, dataVoo2, 0);
    }

    @Test
    public void toStringInfo() {
        assertEquals("=========== Voo Número: " +  bhParaSpVoo.getIdVoo() + " ===========" + "\nData do Voo: 06/05/2003" + "\nPreço do voo: " + bhParaSpVoo.getPreco() + "\nTrecho " + Integer.toString(bhParaSp.getIdTrecho()) + " de Belo Horizonte para São Paulo.\n\n", bhParaSpVoo.toString());
    }

    @Test
    public void equalsTest() {
        assertFalse(bhParaSpVoo.equals(bhParaRjVoo));
    }

    @Test
    public void alterarTrechoTest() {
    
        assertEquals("=========== Voo Número: " + bhParaSpVoo.getIdVoo() + " ===========" + "\nData do Voo: 06/05/2003" + "\nPreço do voo: " + bhParaSpVoo.getPreco() + "\nTrecho " + Integer.toString(bhParaSp.getIdTrecho()) + " de Belo Horizonte para São Paulo.\n\n", bhParaSpVoo.toString());

        bhParaSpVoo.alterarTrecho(bhParaRj);

        assertEquals("=========== Voo Número: " + bhParaSpVoo.getIdVoo() + " ===========" +"\nData do Voo: 06/05/2003" + "\nPreço do voo: " + bhParaSpVoo.getPreco() + "\nTrecho " + Integer.toString(bhParaRj.getIdTrecho()) + " de Belo Horizonte para Rio de Janeiro.\n\n", bhParaSpVoo.toString());
    }

    @Test
    public void alterarDataTest() {
    
        assertEquals("=========== Voo Número: " + bhParaSpVoo.getIdVoo() + " ===========" + "\nData do Voo: 06/05/2003"  + "\nPreço do voo: " + bhParaSpVoo.getPreco() + "\nTrecho " + Integer.toString(bhParaSp.getIdTrecho()) + " de Belo Horizonte para São Paulo.\n\n", bhParaSpVoo.toString());
    
        Data novaDataVoo = new Data(12, 9, 2004);
        bhParaSpVoo.alterarData(novaDataVoo);

        assertEquals("=========== Voo Número: " + bhParaSpVoo.getIdVoo() + " ===========" + "\nData do Voo: 12/09/2004" + "\nPreço do voo: " + bhParaSpVoo.getPreco() + "\nTrecho " + Integer.toString(bhParaSp.getIdTrecho()) + " de Belo Horizonte para São Paulo.\n\n", bhParaSpVoo.toString());
    }
}