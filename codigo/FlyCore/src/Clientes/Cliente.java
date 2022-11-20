package Clientes;

import java.util.LinkedList;
import Passagens.Bilhete;
import Utilitarios.AceleradorPts.Emulti;
import Utilitarios.AceleradorPts.IMultiplicavel;
import Utilitarios.AceleradorPts.MultiplicadorPrata;
import Utilitarios.AceleradorPts.MultiplicadorPreto;
import Utilitarios.Data;

public class Cliente {

    private String nome = "";
    private String cpf = "";
    private int pontuacaoCliente = 0;
    private LinkedList<Bilhete> bilhetesCliente = new LinkedList<Bilhete>();
    private IMultiplicavel acelardorPts;
    private int numeroBilhetesPromocionais = 0;

    /**
     * Construtor cliente, recebe o nome e o numero do documento.
     * @param nomeCliente
     * @param numCpf
     */
    public Cliente(String nomeCliente, String numCpf) {
        this.nome = nomeCliente;
        this.cpf = numCpf;
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
            bilheteCompra.inserirDataCompra();
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

    public int calcularNumeroBilhetesPromocionais(){
        Data dataAtual = new Data();
        dataAtual.tirar1Ano();

        int valorTotal = this.bilhetesCliente.stream().filter(b -> b.getDataCompra().maisRecenteQue(dataAtual) == -1).mapToInt(Bilhete::calcularPontuacao).sum();

        double valorAux = valorTotal / 10500;
        int numeroBilhetes = (int)valorAux;

        this.numeroBilhetesPromocionais = numeroBilhetes;
        return this.numeroBilhetesPromocionais;
    }

    public void setNumeroBilhetesPromocionais (int valor) {
        this.numeroBilhetesPromocionais = valor - 1;
    }

    /**
     * Ordena os bilhetes do usuário em ordem de voo mais recente
     * @return void
     */
    public void ordenarBilhetes() {
        bilhetesCliente.sort((b1, b2) -> (b1.buscarVoo(0).getData().maisRecenteQue(b2.buscarVoo(0).getData())));
    }

    public IMultiplicavel getAcelardorPts() {
        return this.acelardorPts;
    }
}