package DiretorioTeste.PassagensTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import Passagens.Bilhete;
import Passagens.Voo;
import Passagens.Trecho;

import Utilitarios.Data;
public class BilheteTest {
    
    @Test
    public void toStringEmptyInfo() {
        Trecho bhParaSp = new Trecho("Belo Horizonte", "São Paulo"); 
        Data dataVoo = new Data(06, 05, 2003);
        Voo bhParaSpVoo = new Voo(bhParaSp, dataVoo, 0);
        Bilhete bilhete = new Bilhete();

        assertEquals("Nenhum voo cadastrado", bilhete.toString());
    }

    @Test
    public void toStringInfoEInserirVoo() {
        Trecho bhParaSp = new Trecho("Belo Horizonte", "São Paulo"); 
        Data dataVoo = new Data(06, 05, 2003);
        Voo bhParaSpVoo = new Voo(bhParaSp, dataVoo, 0);
        Bilhete bilhete = new Bilhete();
        assertTrue(bilhete.inserirVoo(bhParaSpVoo));

        assertEquals("=========== Bilhete número: "+bilhete.getIdBilhete()+"===========\n"+
        "=========== Voo Número: "+bhParaSpVoo.getIdVoo()+"\n Data do Voo: "+dataVoo.dataFormatada()+"\n"+
        bhParaSp.getIdTrecho() + " de Belo Horizonte para São Paulo", bilhete.toString());
    }

    @Test
    public void removerVooTest() {
        Trecho bhParaSp = new Trecho("Belo Horizonte", "São Paulo"); 
        Data dataVoo = new Data(06, 05, 2003);
        Voo bhParaSpVoo = new Voo(bhParaSp, dataVoo, 0);
        Bilhete bilhete = new Bilhete();
        assertTrue(bilhete.inserirVoo(bhParaSpVoo));

        assertTrue(bilhete.removerVoo(bhParaSpVoo.getIdVoo()));
    }

    @Test
    public void removerVooInexistenteTest() {
        Trecho bhParaSp = new Trecho("Belo Horizonte", "São Paulo"); 
        Data dataVoo = new Data(06, 05, 2003);
        Voo bhParaSpVoo = new Voo(bhParaSp, dataVoo, 0);
        Bilhete bilhete = new Bilhete();
        assertTrue(bilhete.inserirVoo(bhParaSpVoo));

        assertFalse(bilhete.removerVoo(4521457));// id "aleatório"
    }
}
