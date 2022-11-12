package ClienteTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;
import Clientes.Cliente;
import Passagens.Bilhete;
import Passagens.Trecho;
import Passagens.Voo;
import Utilitarios.Data;
import Utilitarios.AceleradorPts.Emulti;
public class ClienteTest {

    public Bilhete blh1 = new Bilhete();;
    public Cliente cl = new Cliente("", 6550);

    @Test
    public void test_multiplicador_nulo(){

        Trecho tr1 = new Trecho("Belo Horizonte", "São Paulo");
        Data data1 = new Data(06, 05, 2003);
        Voo voo_1 = new Voo(tr1, data1, 500);
        blh1.inserirVoo(voo_1);
        cl.comprarBilhete(blh1);
        assertEquals(cl.getPontuacao(), 500);
    }
    @Test 
    public void test_multiplicador_inicializado(){
        Trecho tr1 = new Trecho("Belo Horizonte", "São Paulo");
        Data data1 = new Data(06, 05, 2003);
        Voo voo_1 = new Voo(tr1, data1, 500);
        blh1.inserirVoo(voo_1);
        cl.comprarBilhete(blh1);    
        cl.setAcelerador(Emulti.PRETO);
        //Teste com acelerador innicializado porém desativado!
        assertEquals(cl.getPontuacao(), 500);
        //Teste com acelerador ativado
        cl.acelardor_pts.on_off();
        assertEquals(cl.getPontuacao(), 750);
    }
    
}
