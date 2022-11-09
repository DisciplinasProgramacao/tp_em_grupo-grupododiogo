package ClienteTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import Clientes.Cliente;
import Passagens.Bilhete;
import Passagens.Trecho;
import Passagens.Voo;
import Utilitarios.Data;

public class ClienteTest {

    public Bilhete blh1;
    public Cliente cl = new Cliente("", 6550);

    @BeforeEach
    public void setUp(){
         Trecho tr1 = new Trecho("Belo Horizonte", "São Paulo");
         Data data1 = new Data(06, 05, 2003);
         Voo voo_1 = new Voo(tr1, data1, 250);
        blh1 = new Bilhete();
        blh1.inserirVoo(voo_1);
    }

    @Test
    public void criar_multiplicador(){
        
        cl.comprarBilhete(blh1);
        cl.acelardor_pts.ativar();
        assertEquals(cl.acelardor_pts.getTipo(), "prata");
    }
    
}
