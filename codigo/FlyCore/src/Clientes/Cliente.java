package Clientes;

import java.util.LinkedList;

import org.junit.jupiter.params.shadow.com.univocity.parsers.conversions.IntegerConversion;

import Passagens.Bilhete;
import Utilitarios.AceleradorPts.Emulti;
import Utilitarios.AceleradorPts.IMultiplicavel;
import Utilitarios.AceleradorPts.MultiplicadorPrata;
import Utilitarios.AceleradorPts.MultiplicadorPreto;
public class Cliente {

    private String nome = "";
    private long NUM_DOCUMENTO = 0;
    private int pontuacaoCliente =0;
    private LinkedList<Bilhete> bilhetesCliente = new LinkedList<Bilhete>();
    public IMultiplicavel acelardor_pts = new MultiplicadorPrata();
    ;

    /**
     * Construtor cliente, recebe o nome e o numero do documento.
     * @param nomeCliente
     * @param numDoc
     */
    public Cliente(String nomeCliente, long numDoc) {
        this.nome = nomeCliente;
        this.NUM_DOCUMENTO = numDoc;
        this.pontuacaoCliente = 0;
    }

    /**
     * Comprar um bilhete adicionando na lista de bilhetes.
     * @param bilheteCompra
     * @return false/true
     */
    public boolean comprarBilhete(Bilhete bilheteCompra) {
        try {
            this.bilhetesCliente.add(bilheteCompra);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    /**
     * Calcula a pontuaçao de fidelidade total do cliente.
     * @return pontuaçao de fidelidade
     */
    public int getPontuacao(){
        int pontuacaoTotal = 0;

        try{
            for (Bilhete bilhete : this.bilhetesCliente) {
                pontuacaoTotal += this.acelardor_pts.multiplicar(bilhete.calcularPontuacao());
            }
            return  pontuacaoTotal;  
           
        }
        catch(NullPointerException e){
                pontuacaoTotal = verificarPontuacaoPadrao();
                return pontuacaoTotal;
            }
    } 

    private int verificarPontuacaoPadrao(){
        int pts = 0;
            for (Bilhete bilhete : this.bilhetesCliente) {
                pts +=  bilhete.calcularPontuacao();
            }
        return pts;
    } 

    public void setAcelerador(Emulti opt){
        if(opt == Emulti.PRATA)
            this.acelardor_pts = new MultiplicadorPrata();
        else if(opt == Emulti.PRETO){
            this.acelardor_pts = new MultiplicadorPreto();
        }
    }
}