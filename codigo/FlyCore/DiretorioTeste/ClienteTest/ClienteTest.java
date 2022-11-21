package ClienteTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;
import Clientes.Cliente;
import Passagens.Bilhete;
import Passagens.Trecho;
import Passagens.Voo;
import Utilitarios.Data;
import Utilitarios.AceleradorPts.MultiplicadorPreto;

public class ClienteTest {

    public Bilhete blh1 = new Bilhete();;
    public Cliente cl = new Cliente("Le", "35657145658", "teste");

    @Test
    public void test_multiplicador_nulo(){
        Trecho tr1 = new Trecho("Belo Horizonte", "São Paulo");
        Data data1 = new Data(06, 05, 2003);
        Voo voo_1 = new Voo(tr1, data1, 500);
        blh1.inserirVoo(voo_1);
        cl.comprarBilhete(blh1);
       // assertEquals(cl.getPontuacao(), 500);
    }
    @Test 
    public void test_multiplicador_inicializado(){
        Trecho tr1 = new Trecho("Belo Horizonte", "São Paulo");
        Data data1 = new Data(06, 05, 2003);
        Voo voo_1 = new Voo(tr1, data1, 500);
        blh1.inserirVoo(voo_1);
        cl.comprarBilhete(blh1);
        MultiplicadorPreto mp = new MultiplicadorPreto(); 
        cl.setAcelerador(mp);
        //Teste com acelerador innicializado porém desativado!
        assertEquals(cl.getPontuacao(), 500);
        //Teste com acelerador ativado
        cl.getAcelardorPts().on_off();
        assertEquals(cl.getPontuacao(), 750);
    }
    @Test 
    public void getPontuacaoSemBilhete(){
        assertEquals(0, cl.getPontuacao());
        cl.comprarBilhete(blh1);
    }

    @Test
    public void bilhetePontuacao(){
        Trecho tr1 = new Trecho("Belo Horizonte", "São Paulo");
        Data data1 = new Data(06, 05, 2022);
        Voo voo_1 = new Voo(tr1, data1, 3000000);
        blh1.inserirVoo(voo_1);
        cl.comprarBilhete(blh1);

        assertEquals(288, cl.calcularNumeroBilhetesPromocionais());
    }

    @Test
    public void bilhetePontuacaoZerada(){
        assertEquals(0, cl.calcularNumeroBilhetesPromocionais());
    }
}
