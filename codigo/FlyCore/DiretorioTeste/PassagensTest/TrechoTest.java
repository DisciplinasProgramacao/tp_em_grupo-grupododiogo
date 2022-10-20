package PassagensTest;

import static org.junit.jupiter.api.Assertions.*;


import Passagens.Bilhete;
import Passagens.Trecho;
import Passagens.Voo;
import Utilitarios.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TrechoTest {

    public Trecho bhParaSp;

    @BeforeEach
    public void setUp(){
        bhParaSp = new Trecho("Belo Horizonte", "São Paulo");
    }

    @Test
    public void toStringInfo() {
        assertEquals(Integer.toString(bhParaSp.getIdTrecho()) + " de Belo Horizonte para São Paulo", bhParaSp.toString());
    }

    @Test
    public void setarCidadeDestino() {
        bhParaSp.setCidadeDestino("Rio de Janeiro");
        assertEquals(Integer.toString(bhParaSp.getIdTrecho()) + " de Belo Horizonte para Rio de Janeiro", bhParaSp.toString());
    }

    @Test
    public void setarCidadeOrigem() {
        bhParaSp.setCidadeOrigem("Rio de Janeiro");
        assertEquals(Integer.toString(bhParaSp.getIdTrecho()) + " de Rio de Janeiro para São Paulo", bhParaSp.toString());
    }
}
