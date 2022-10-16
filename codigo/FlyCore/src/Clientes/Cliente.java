package Clientes;

import codigo.FlyCore.src.*;
import java.util.LinkedList;
public class Cliente {

    private final String nome = " ";
    private final int NUM_DOCUMENTO=0;
    private int pontuacaoCliente;
    private LinkedList<Bilhete> bilhetesCliente = new LinkedList<Bilhete>();
    public Cliente(String nome_cliente, int num_doc){
        this.nome = nome;
        this.NUM_DOCUMENTO = num_doc;
        this.pontuacaoCliente=0;
    }

    public boolean comparaBilhete(Bilhete bilheteCompra){
        try {
            this.bilhetesCliente.add(bilheteCompra);
            //Restam implementações na classe bilhete para finalizar método
            return true;
        }
        catch (e; return false;);
    }
}