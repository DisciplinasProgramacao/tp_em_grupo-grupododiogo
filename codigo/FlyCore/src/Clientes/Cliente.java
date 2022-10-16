package Clientes;

import java.util.LinkedList;

import Passagens.Bilhete;
public class Cliente {

    private String nome = "";
    private int NUM_DOCUMENTO = 0;
    private int pontuacaoCliente;
    private LinkedList<Bilhete> bilhetesCliente = new LinkedList<Bilhete>();

    public Cliente(String nomeCliente, int numDoc) {
        this.nome = nomeCliente;
        this.NUM_DOCUMENTO = numDoc;
        this.pontuacaoCliente = 0;
    }

    public boolean comparaBilhete(Bilhete bilheteCompra) {
        try {
            this.bilhetesCliente.add(bilheteCompra);
            //Restam implementações na classe bilhete para finalizar método
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}