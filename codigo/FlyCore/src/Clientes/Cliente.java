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
    private int pontuacaoCliente = 0;
    private LinkedList<Bilhete> bilhetesCliente = new LinkedList<Bilhete>();
    public IMultiplicavel acelardorPts;
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
        catch (NullPointerException e) {
            return false;
        }
    }

    /**
     * Calcula a pontuaçao de fidelidade total do cliente.
     * @return pontuaçao de fidelidade
     */
    public int getPontuacao() {
        int pontuacaoTotal = 0;

        try {
            for (Bilhete bilhete : this.bilhetesCliente) {
                pontuacaoTotal += this.acelardorPts.multiplicar(bilhete.calcularPontuacao());
            }
        }
        catch(NullPointerException e) {
                pontuacaoTotal = verificarPontuacaoPadrao();
            }
        return pontuacaoTotal;
    } 

    private int verificarPontuacaoPadrao() {
        int pts = 0;
            for (Bilhete bilhete : this.bilhetesCliente) {
                pts +=  bilhete.calcularPontuacao();
            }
        return pts;
    } 

    public void setAcelerador(Emulti opt) {
        if (opt == Emulti.PRATA) {
            this.acelardorPts = new MultiplicadorPrata();
        }
        else if (opt == Emulti.PRETO) {
            this.acelardorPts = new MultiplicadorPreto();
        }
    }

    /**
     * Ordena os bilhetes do usuário em ordem de voo mais recente
     * @return void
     */
    public void ordenarBilhetes() {
        bilhetesCliente.sort((b1, b2) -> (b1.buscarVoo(0).getData().maisRecenteQue(b2.buscarVoo(0).getData())));
    }
}