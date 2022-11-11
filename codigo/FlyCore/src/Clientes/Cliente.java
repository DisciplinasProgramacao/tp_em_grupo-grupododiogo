package Clientes;

import java.util.LinkedList;

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
    public IMultiplicavel acelardor_pts;

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
    public int verificarPontuacao(){
        int pontuacaoTotal = 0;
        //Vericiar pontuação caso multipliucador ativo
        if(this.acelardor_pts != null && this.acelardor_pts.isAtivo()){
            for (Bilhete bilhete : this.bilhetesCliente) {
                int pontuacao = bilhete.calcularPontuacao();
                pontuacaoTotal += this.acelardor_pts.multiplicar(pontuacao);
            }
        }
        //Verificar pontuação caso Multiplicador desativado ou Nulo
        else{
            for (Bilhete bl : this.bilhetesCliente){
                    int pts = bl.calcularPontuacao();
                    pontuacaoTotal += pts;
            }
            
        }
        return pontuacaoTotal;
    } 

    public void setAcelerador(Emulti opt){
        if(opt == Emulti.PRATA)
            this.acelardor_pts = new MultiplicadorPrata();
        else if(opt == Emulti.PRETO){
            this.acelardor_pts = new MultiplicadorPreto();
        }
    }
}