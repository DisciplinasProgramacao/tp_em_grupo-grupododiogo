package Clientes;
import java.util.LinkedList;
import codigo.FlyCore.src.*;
public class Cliente {

    private final String nome = " ";
    private final int NUM_DOCUMENTO=0;
    private int pontuacaoCliente;
    private LinkedList<Bilhete> bilhetesCliente;

    public Cliente(String nome_cliente, int num_doc){
        this.nome = nome;
        this.NUM_DOCUMENTO = num_doc;
        this.pontuacaoCliente=0;
    }

}