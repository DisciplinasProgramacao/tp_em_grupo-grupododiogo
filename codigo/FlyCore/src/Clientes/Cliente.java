package Clientes;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

import Passagens.Bilhete;
import Utilitarios.Data;
import Utilitarios.AceleradorPts.IMultiplicavel;

public class Cliente {

    private String nome = "";
    private String cpf = "";
    private int pontuacaoCliente = 0;
    private String senha="";
    private Deque<Bilhete> bilhetesCliente = new ArrayDeque<>();
    private IMultiplicavel acelardorPts;
    private int numeroBilhetesPromocionais = 0;

    /**
     * Construtor cliente, recebe o nome,numero do documento e senha.
     * @param nomeCliente
     * @param numCpf
     */
    public Cliente(String nomeCliente, String numCpf, String senhaCadastro) {
        this.nome = nomeCliente;
        this.cpf = validarCpf(numCpf);
        this.senha = validarSenha(senhaCadastro);
        this.pontuacaoCliente = 0;
    }

    /**
     * Validação basica do cpf (Tamnho != 11) será tratado apenas.
     * @param cpf cpf digitado no construtor
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
     * Faz a validação da senha para caso contenha apenas espaços vazios
     * ou seja menor que 3 em tamanho tera seu padrao para --> "123" 
     * @param senhaUSR senha digitada no construtor
     * @return String senha final do cliente após validação
     */
    private String validarSenha(String senhaUSR){
        senhaUSR = senhaUSR.replaceAll(" ", "");
        if(senhaUSR.length()<3 || senhaUSR.isBlank() || senhaUSR.isEmpty()){
            return "123";
        }
        return senhaUSR;
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
    public String getCpf(){return this.cpf;}
    public String getSenha(){return this.senha;}
    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}