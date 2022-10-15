package DiretorioTeste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.jupiter.api.Test;

import codigo.FlyCore.src.Passagens.Voo;

public class VooTest {
    
    @Test
    public void toStringInfo() {
        Trecho bhParaSp = new Trecho("Belo Horizonte", "São Paulo"); 
        Data dataVoo = new Data(06, 05, 2003);
        Voo bhParaSpVoo = new Voo(bhParaSp, dataVoo);
    
        assertEquals("=========== Voo Número: " + bhParaSpVoo.getIdVoo() + "\n Data do Voo: 06/05/2003" + "\n" + Integer.toString(bhParaSp.getIdTrecho()) + " de Belo Horizonte para São Paulo", bhParaSpVoo.toString());
    }

    @Test
    public void equalsTest() {
        Trecho bhParaSp = new Trecho("Belo Horizonte", "São Paulo"); 
        Data dataVoo = new Data(06, 05, 2003);
        Voo bhParaSpVoo = new Voo(bhParaSp, dataVoo);

        Trecho bhParaRj = new Trecho("Belo Horizonte", "Rio de Janeiro"); 
        Data dataVoo2 = new Data(06, 05, 2003);
        Voo bhParaRjVoo = new Voo(bhParaRj, dataVoo2);
    
        assertFalse(bhParaSpVoo.equals(bhParaRjVoo));
    }

    @Test
    public void alterarTrechoTest() {
        Trecho bhParaSp = new Trecho("Belo Horizonte", "São Paulo"); 
        Data dataVoo = new Data(06, 05, 2003);
        Voo bhParaSpVoo = new Voo(bhParaSp, dataVoo);
    
        assertEquals("=========== Voo Número: " + bhParaSpVoo.getIdVoo() + "\n Data do Voo: 06/05/2003" + "\n" + Integer.toString(bhParaSp.getIdTrecho()) + " de Belo Horizonte para São Paulo", bhParaSpVoo.toString());
    
        Trecho bhParaRj = new Trecho("Belo Horizonte", "Rio de Janeiro"); 
        bhParaSpVoo.alterarTrecho(bhParaRj);

        assertEquals("=========== Voo Número: " + bhParaSpVoo.getIdVoo() + "\n Data do Voo: 06/05/2003" + "\n" + Integer.toString(bhParaRj.getIdTrecho()) + " de Belo Horizonte para Rio de Janeiro", bhParaSpVoo.toString());
    }

    @Test
    public void alterarDataTest() {
        Trecho bhParaSp = new Trecho("Belo Horizonte", "São Paulo"); 
        Data dataVoo = new Data(06, 05, 2003);
        Voo bhParaSpVoo = new Voo(bhParaSp, dataVoo);
    
        assertEquals("=========== Voo Número: " + bhParaSpVoo.getIdVoo() + "\n Data do Voo: 06/05/2003" + "\n" + Integer.toString(bhParaSp.getIdTrecho()) + " de Belo Horizonte para São Paulo", bhParaSpVoo.toString());
    
        Data dataVoo2 = new Data(12, 9, 2004);
        bhParaSpVoo.alterarData(dataVoo2);

        assertEquals("=========== Voo Número: " + bhParaSpVoo.getIdVoo() + "\n Data do Voo: 12/09/2004" + "\n" + Integer.toString(bhParaSp.getIdTrecho()) + " de Belo Horizonte para São Paulo", bhParaSpVoo.toString());
    }
}