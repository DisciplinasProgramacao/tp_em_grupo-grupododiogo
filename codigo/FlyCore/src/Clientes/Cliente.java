package Clientes;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

import Passagens.Bilhete;
import Utilitarios.Data;
import Utilitarios.AceleradorPts.IMultiplicavel;

public class Cliente {

    private String nome = "";
    private String cpf = "";
    private int pontuacaoCliente = 0;
    private Deque<Bilhete> bilhetesCliente = new ArrayDeque<>();
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
            bilheteCompra.inserirDataCompra();
            this.bilhetesCliente.add(bilheteCompra);
            return true;
        }
        catch (NullPointerException e) {
            return false;
        }
    }

    /**
     * Calcula a pontuaçao de fidelidade total do cliente.
     * A principio o método tenta utilizar o multiplicador do cliente para realizar o calculo,
     * caso não consiga por não ter um Multiplicador relacionado será desviado o fluxo e chamado
     * um método auxiliar para calcular a pontuação total padrão.
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
    /**
     * Método privado para soma padrão da pontuação de bilhetes relacionado a um Cliente.
     * @return int pts --> pontuação total do cliente
     */
    private int verificarPontuacaoPadrao() {
        int pts = 0;
            for (Bilhete bilhete : this.bilhetesCliente) {
                pts +=  bilhete.calcularPontuacao();
            }
        return pts;
    }

    public void setAcelerador(IMultiplicavel multi) {
       try{
        this.acelardorPts = multi;
       }
       catch(ClassCastException e){
        System.out.println("Multiplicador Invalido");
       }
    }
    /**
     * Calcula o número de bilhetes grátis que um cliente pode ganhar
     * Verifica a pontuação na lista de bilhetes em um periodo de um ano referente a data da atual 
     * @return int numeroBilhetes --> número de bilhetes promocionais para o cliente.
     */
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

    public IMultiplicavel getAcelardorPts() {
        return this.acelardorPts;
    }
}