package MultiplicadorTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import Clientes.Cliente;
import Passagens.Bilhete;
import Passagens.Trecho;
import Passagens.Voo;
import Utilitarios.Data;
import Utilitarios.AceleradorPts.Emulti;
import Utilitarios.AceleradorPts.MultiplicadorPrata;
import Utilitarios.AceleradorPts.MultiplicadorPreto;

public class MultiplicadorTest {

    private Cliente usr = new Cliente("", 0);
    private Trecho trch = new Trecho("", "");
    private Voo voo;
    private Bilhete blhte = new Bilhete();
    @BeforeAll
    public void criari_classes_util(){
        this.usr = new Cliente("Leonardo", 1555);
        this.trch = new Trecho("São Paulo", "Belo Horizonte");
        this.voo  = new Voo(trch, null, 600);
        this.blhte.inserirVoo(voo);
        usr.comprarBilhete(blhte);
        MultiplicadorPrata.setPreco(250d);
        MultiplicadorPreto.setPreco(350d);
    }

    @Test
    public void multiplicarPTSdesativado(){
        usr.setAcelerador(Emulti.PRATA);
        assertEquals(usr.acelardor_pts.multiplicar(usr.verificarPontuacao()) , usr.verificarPontuacao());
    }
    @Test
    public void multiplicarPTSativado(){
        usr.setAcelerador(Emulti.PRATA);
        usr.acelardor_pts.on_off();
        assertEquals(usr.acelardor_pts.multiplicar(usr.verificarPontuacao()), usr.verificarPontuacao());
    }
    
}
