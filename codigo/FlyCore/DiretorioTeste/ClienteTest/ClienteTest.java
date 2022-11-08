package ClienteTest;

import org.junit.jupiter.api.BeforeEach;

import Passagens.Bilhete;
import Passagens.Trecho;
import Passagens.Voo;
import Utilitarios.Data;

public class ClienteTest {


    @BeforeEach
    public void setUp(){
         Trecho tr1 = new Trecho("Belo Horizonte", "São Paulo");
         Data data1 = new Data(06, 05, 2003);
         Voo voo_1 = new Voo(tr1, data1, 250);
         Bilhete blh1 = new Bilhete();
         blh1.inserirVoo(voo_1);
    }
    
}
