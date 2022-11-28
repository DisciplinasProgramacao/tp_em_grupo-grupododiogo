package Clientes;

import java.util.Deque;
import java.util.LinkedList;

import Passagens.Bilhete;
import Utilitarios.Data;
import Utilitarios.AceleradorPts.IMultiplicavel;

public class Cliente {

    private String nome = "";
    private String cpf = "";
    private int pontuacaoCliente = 0;
    private Deque<Bilhete> bilhetesCliente = new LinkedList<>();
    private IMultiplicavel acelardorPts;
    private int numeroBilhetesPromocionais = 0;

    /**
     * Construtor cliente, recebe o nome e numero do documento.
     * @param nomeCliente
     * @param numCpf
     */
    public Cliente(String nomeCliente, String numCpf) {
        this.nome = nomeCliente;
        this.cpf = validarCpf(numCpf);
        this.pontuacaoCliente = 0;
    }

    /**
     * Validação basica do cpf (Tamnho != 11) será tratado apenas.
     * @param cpfd (cpf)
     * @return String --> "00000000000" caso não siga as especificações || cpf de parametro caso validado
     */
    private String validarCpf(String cpfd){
        cpfd = cpfd.replaceAll(" ", "");
        if(cpfd.length() != 11){
            return "00000000000";
        }
        return cpfd;
    }
    /**
     * Comprar um bilhete adicionando na pilha de Bilhetes.
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
        this.pontuacaoCliente = pontuacaoTotal;
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
        this.acelardorPts = multi;
    }
    /**
     * Calcula o número de bilhetes grátis que um cliente pode ganhar,
     * Verifica a pontuação na Pilha de bilhetes em um periodo referente a um ano apartir a data da atual 
     * @return int numeroBilhetes --> número de bilhetes promocionais para o cliente.
     */
    public int calcularNumeroBilhetesPromocionais(){
        try{
            return calcularNumeroBilhetesPromocionaisMulti();
        }catch(NullPointerException multiNulo){
            return calcularNumeroBilhetesPromocionaisPadrao();
        }
    }
    private int calcularNumeroBilhetesPromocionaisMulti(){
        Data data = new Data();
        data.tirar1Ano();

        int valorTotal = this.bilhetesCliente.stream().filter(b -> b.getDataCompra().maisRecenteQue(data) == -1 && b.getStatus() == true).mapToInt(e -> this.acelardorPts.multiplicar(e.calcularPontuacao())).sum();
        this.setBilhetesInvalidos();

        double valorAux = valorTotal / 10500;
        int numeroBilhetes = (int)valorAux;

        this.numeroBilhetesPromocionais = numeroBilhetes;
        return this.numeroBilhetesPromocionais;
    }
    private int calcularNumeroBilhetesPromocionaisPadrao(){
        Data data = new Data();
        data.tirar1Ano();

        int valorTotal = this.bilhetesCliente.stream().filter(b -> b.getDataCompra().maisRecenteQue(data) == -1 && b.getStatus() == true).mapToInt(e -> e.calcularPontuacao()).sum();
        this.setBilhetesInvalidos();

        double valorAux = valorTotal / 10500;
        int numeroBilhetes = (int)valorAux;

        this.numeroBilhetesPromocionais = numeroBilhetes;
        return this.numeroBilhetesPromocionais;
    }
    public int calcularPontuacaoAnual(){
        try{
            return calcularPontuacaAnualMulti();
        }catch(NullPointerException multiNulo){
            return calcularPontuacaoAnualPadrao();
        }
    }

    public int calcularPontuacaoAnualPadrao(){
        Data data = new Data();
        data.tirar1Ano();

        int valorTotal = this.bilhetesCliente.stream().filter(b -> b.getDataCompra().maisRecenteQue(data) == -1 && b.getStatus() == true).mapToInt(Bilhete::calcularPontuacao).sum();
        return valorTotal;
    }
    
    private int calcularPontuacaAnualMulti(){
        Data data = new Data();
        data.tirar1Ano();

        int valorTotal = this.bilhetesCliente.stream().filter(b -> b.getDataCompra().maisRecenteQue(data) == -1 && b.getStatus() == true).mapToInt(e -> this.acelardorPts.multiplicar(e.calcularPontuacao())).sum();
        return valorTotal;
    }
    
    private void setBilhetesInvalidos(){
        Data data = new Data();
        data.tirar1Ano();
        this.bilhetesCliente.stream().filter(b -> b.getDataCompra().maisRecenteQue(data) == -1 && b.getStatus() == true).forEach(b -> b.setStatusCalculoPromocao(false));
    }
    public boolean ativarMulti(){
        try{
            return this.acelardorPts.on_off();
        }
        catch(NullPointerException e){throw e;}
    }
    public void setNumeroBilhetesPromocionais (int valor) {
        this.numeroBilhetesPromocionais = valor - 1;
    }

    public String gerarRelatorio() {
        int qtdBilhetes = (int) this.bilhetesCliente.stream().count();
        int pontuacao = this.getPontuacao();
        int qtdBilhetesGratis = this.calcularNumeroBilhetesPromocionais();

        String statusAcelerador;
        try {
            statusAcelerador = (this.acelardorPts.isAtivo()) ? "Acelerador " + this.acelardorPts.getTipo() + " Ativado" : "Desativado";
        } catch(NullPointerException e ){
            statusAcelerador = "Desativado";
        }

        return  "Nome do cliente: " + this.nome + "\n" +
                "Cpf: " + this.cpf + "\n" +
                "Pontuaçao total: " + pontuacao + "\n" +
                "Quantidade de bilhetes: " + qtdBilhetes + "\n" +
                "Status do Acelerador: " + statusAcelerador + "\n" +
                "Numero de bilhetes gratis: " + qtdBilhetesGratis + "\n";
    }

    public IMultiplicavel getAcelardorPts() {
        return this.acelardorPts;
    }
    public String getCpf(){return this.cpf;};
    public String getNome(){return this.nome;};

    public Deque<Bilhete> getBilhetesCliente(){return this.bilhetesCliente;}
    @Override
    public int hashCode() {
        long cpf = Long.parseLong(this.cpf);
        int hash = (int) cpf;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        Cliente nb = (Cliente) obj;
        boolean equals = false;
        equals = nb.hashCode() == this.hashCode();
        return equals;
    }
}