package Passagens;
import Utilitarios.Data;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.stream.Collectors;

public class Bilhete {
    protected final int idBilhete;
    protected LinkedList<Voo> voos = new LinkedList<Voo>();
    protected double precoBilhete = 0;
    protected String tipo;
    protected Data dataDeCompra;

    protected boolean statusCalculoPromocao = true;

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
        
        return this.voos.add(novoVoo);
    }

    /** Remove voo da lista de voos contidos no bilhete.
     * @param idvoo
     * @return true caso consiga remover o voo com sucesso da lista. Caso isso não ocorra, retorna false.
     */
    public boolean removerVoo(Voo idvoo) {
        try {
            
            return this.voos.remove(idvoo);
         
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
        return pontosFidelidade;
    }

    public void setStatusCalculoPromocao(boolean statusCalculoPromocao) {
        this.statusCalculoPromocao = statusCalculoPromocao;
    }

    /**
     * @param vooBuscado voo que deseja buscar
     * @return Voo encontrado.
     */
    public int buscarVoo(Voo vooBuscado) {
        return this.voos.indexOf(vooBuscado);
    }

    /**
     * Encontra o voo com o maior preço contido no bilhete
     * @return Voo com maior preço
     */
    protected Voo encontrarVooMaiorValor() {
           Voo maior_valor =  this.voos.stream().collect(Collectors.maxBy(Comparator.comparingDouble(Voo::getPreco))).orElse(null);
            return maior_valor;
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

    public void inserirDataCompra(){
        this.dataDeCompra = new Data();
    }

    public String exibirVoosBilhete(){
        StringBuilder infos = new StringBuilder();
        this.voos.stream().forEach(b -> infos.append(b.toString() + "\n"));
        return infos.toString();
    }

    /** 
     * @return String com as informações de todos os Voos do bilhete.
     */
    @Override
    public String toString() {
        StringBuilder infoBilhete = new StringBuilder();
        infoBilhete.append("Bilhete " + this.tipo + " número: "+this.idBilhete+" ===========\n");
        if (this.voos.isEmpty()) {
            return "Nenhum voo cadastrado";
        }

        for (Voo vooBi : this.voos) {
            infoBilhete.append(vooBi.toString());
        }

        infoBilhete.append("\nPreço total do bilhete: " + this.calcularPreco());
        return infoBilhete.toString();
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

    public int getIdBilhete() {
        return this.idBilhete;
    }
    public Data getDataCompra() { return this.dataDeCompra;}

    public boolean getStatus() {return this.statusCalculoPromocao;}

}