package DiretorioTeste;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import codigo.FlyCore.src.Passagens.Trecho;

public class TrechoTest {
    
    @Test
    public void toStringInfo() {
        Trecho bhParaSp = new Trecho("Belo Horizonte", "São Paulo"); 
    
        assertEquals(Integer.toString(bhParaSp.getIdTrecho()) + " de Belo Horizonte para São Paulo", bhParaSp.toString());
    }

    @Test
    public void setarCidadeDestino() {
        Trecho bhParaSp = new Trecho("Belo Horizonte", "São Paulo"); 
    
        bhParaSp.setCidadeDestino("Rio de Janeiro");
        assertEquals(Integer.toString(bhParaSp.getIdTrecho()) + " de Belo Horizonte para Rio de Janeiro", bhParaSp.toString());
    }

    @Test
    public void setarCidadeOrigem() {
        Trecho bhParaSp = new Trecho("Belo Horizonte", "São Paulo"); 
    
        bhParaSp.setCidadeOrigem("Rio de Janeiro");
        assertEquals(Integer.toString(bhParaSp.getIdTrecho()) + " de Rio de Janeiro para São Paulo", bhParaSp.toString());
    }
}
