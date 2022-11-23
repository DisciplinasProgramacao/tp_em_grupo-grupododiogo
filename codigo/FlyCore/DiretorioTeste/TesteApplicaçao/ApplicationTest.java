package TesteApplicaçao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import Clientes.Cliente;

public class ApplicationTest {

    private Map<Integer, Cliente> clientes_teste = new HashMap<>();

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
}
