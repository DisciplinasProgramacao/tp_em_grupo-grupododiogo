package TesteApplicaçao;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import Clientes.Cliente;
import Passagens.Trecho;

public class ApplicationTest {

    private Map<Integer, Cliente> clientes_teste = new HashMap<>();
    private List<Trecho> trechos_teste = new LinkedList<>();
    @Test
    public void testeHashSetClientes_Busca(){
        
        Cliente cl = new Cliente("Leonardo", "55068321842", "123");
        clientes_teste.put(cl.hashCode(), cl);
        //Busca:
        Cliente clBusca = new Cliente("", "55068321842", "");
        //testando contains do HashSet
        assertEquals(cl.hashCode(), clBusca.hashCode());
        assertEquals(clientes_teste.containsKey(clBusca.hashCode()), true);
        // exemplo de busca
        assertEquals(clientes_teste.get(clBusca.hashCode()), cl);
        // Exemplo do uso de colections com o map: clientes_teste.values().stream();
    }

    @Test 

    public void testeBuscarTrechosEmLista(){

        Trecho nTrecho = new Trecho("guarulhos", "navegantes");    
        
        trechos_teste.add(nTrecho);
        Trecho nTrechoBusca = new Trecho("guarulhos", "navegantes");
        assertEquals(trechos_teste.contains(nTrechoBusca), true);
    }
}
