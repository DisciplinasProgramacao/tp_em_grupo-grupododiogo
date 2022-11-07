package Passagens;
import java.util.LinkedList;
import java.util.Objects;

import Utilitarios.AceleradorPts.*;

public class Bilhete {
    protected final int idBilhete;
    protected LinkedList<Voo> voos = new LinkedList<Voo>();
    protected double precoBilhete =0;
    protected String tipo;
    protected Multiplicador acelerador_pts;
    protected boolean multiplicador_ativo = false;
    protected double preco_mult =0d;
    /**
     * Construtor do bilhete, atribuindo um hashCode como id e o tipo do bilhete
     */
    public Bilhete() {
        this.idBilhete = this.hashCode();
        this.precoBilhete = calcularPreco();
        this.tipo = "Comum";
    }

    /**
     * adciona o voo na lista de voos.
     * @param novoVoo
     * @return true em caso de sucesso.
     */
    public boolean inserirVoo(Voo novoVoo) {
        this.voos.add(novoVoo);
        return true;
    }

    /** Remove voo da lista de voos contidos no bilhete.
     * @param idVoo
     * @return true caso consiga remover o voo com sucesso da lista. Caso isso não ocorra, retorna false.
     */
    public boolean removerVoo(int idVoo) {
        try {
            this.voos.remove(buscarIndexVoo(idVoo));
            return true;
        }
        catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    /** Calcula o preço de um bilhete
     * @return double precoFinal do bilhete
     */
    public double calcularPreco() {
        double precoFinal = 0d;
        if (this.voos.isEmpty()) {
            return precoFinal;
        }

        if (this.voos.size() == 1) {
            double precovoo = voos.get(0).getPreco();
            precoFinal = (0.010 * precovoo) + precovoo;
            this.precoBilhete = precoFinal;
        } else {
            Voo vooMaisCaro = encontrarVooMaiorValor();
            precoFinal = somarPrecoVoosRestantes(vooMaisCaro) + vooMaisCaro.getPreco();
            this.precoBilhete = precoFinal;
        }
        return precoFinal;
    }
    
    /** Calcula os pontos de fidelidade de um bilhete
    * @return pontos de fidelidade
    */
    public int calcularPontuacao() {
        int pontosFidelidade=0;
      
        double valorAux = this.calcularPreco() / 500;
        int valorBase = (int)valorAux;
        pontosFidelidade = (valorBase * 500);
        int multiplicavel = this.multiplicar_pt_multiplicador(pontosFidelidade);
        if(multiplicavel == 0)
            return pontosFidelidade;
        else{   
        return multiplicavel;} 
    }

    protected int multiplicar_pt_multiplicador(int pnt_bilhete){
        if(this.multiplicador_ativo && this.acelerador_pts != null){
            return (Math.round((int) (this.acelerador_pts.multiplicarPts(pnt_bilhete)/500) * 500));}
       else{ 
       return 0;}    
    }

    /**
     * Método responsavél por Ativar e Desativar o Multiplicador
     * @return status do multiplicador
     */
    public boolean ativar_multiplicador(){
        if(this.acelerador_pts != null){
            if(this.multiplicador_ativo == false)
                this.multiplicador_ativo = true;
            else
                this.multiplicador_ativo = false;       
        }
        return this.multiplicador_ativo;    
    }

    /**
     * Encontra o voo com o maior preço contido no bilhete
     * @return Voo com maior preço
     */
    protected Voo encontrarVooMaiorValor() {
        try {
            Voo vooComMaiorPreco = this.voos.get(0);
            double precoAtual = 0d;

            for (int i = 0; i < this.voos.size() - 1; i++) {
                precoAtual = this.voos.get(i).getPreco();
                if (precoAtual < this.voos.get(i + 1).getPreco()) {
                    vooComMaiorPreco = this.voos.get(i + 1);
                }
            }

            return vooComMaiorPreco;
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * Soma o preço de voos restantes
     * @param maiorVoo
     * @return precoDescontado
     */
    protected double somarPrecoVoosRestantes(Voo maiorVoo) {
        double precoDescontado = 0d, somaVoo = 0d;
        for (Voo voosBilhete: this.voos) {
            if (!voosBilhete.equals(maiorVoo)) {
                somaVoo += voosBilhete.getPreco();
            }
        }

        precoDescontado = somaVoo * 0.5;
        return precoDescontado;
    }

    /** 
     * @return String com as informações de todos os Voos do bilhete.
     */
    @Override
    public String toString() {
        StringBuilder infoBilhete = new StringBuilder();
        infoBilhete.append("=========== Bilhete " + this.tipo + " número: "+this.idBilhete+" ===========\n");
        if (this.voos.isEmpty()) {
            return "Nenhum voo cadastrado";
        }

        for (int i = 0; i < this.voos.size(); i++) {
            infoBilhete.append(buscarVoo(i).toString());
        }

        infoBilhete.append("\nPreço total do bilhete: " + this.calcularPreco());
        infoBilhete.append("\n\nLembre-se de anotar o número do bilhete e dos voos, eles serão necessários depois ;)");
        return infoBilhete.toString();
    }


    /**
     * @param indexVoo
     * @return Voo encontrado.
     */
    public Voo buscarVoo(int indexVoo) {
        return this.voos.get(indexVoo);
    }

    /**
     * @param idVoo
     * @return Index do voo na lista de voos. Caso o voo não exista na lista, retorna
     * -1.
     */
    public int buscarIndexVoo(int idVoo) {
        for (int i = 0; i < this.voos.size(); i++) {
            if (this.voos.get(i).getIdVoo() == idVoo) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Retorna id do bilhete
     * @return int idBilhete
     */
    public int getIdBilhete() {
        return this.idBilhete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bilhete bilhete = (Bilhete) o;
        return idBilhete == bilhete.idBilhete && Double.compare(bilhete.precoBilhete, precoBilhete) == 0 && voos.equals(bilhete.voos) && tipo.equals(bilhete.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(voos, precoBilhete, tipo);
    }
}