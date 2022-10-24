package Passagens;

public class BilhetePromocional extends Bilhete {

    /**
     * Construtor do bilhete promocional, atribuindo um hashCode como id e o tipo do bilhete
     */
    public BilhetePromocional() {
        super();
        this.tipo = "Promocional";
    }

    /** Calcula o preço de um bilhete promocional
     * @return double precoFinal do bilhete
     */
    @Override
    public double calcularPreco() {
        double precoBase = super.calcularPreco();
        double novoPreco = 0d;
        novoPreco = precoBase * 0.6;
        this.precoBilhete = novoPreco;
        return novoPreco;
    }

    /** Calcula os pontos de fidelidade de um bilhete promocinal
     * @return pontos de fidelidade
     */
    @Override
    public int calcularPontuacao(){
        double valorAux = this.calcularPreco() / 500;
        int valorBase = (int)valorAux;

        int pontosFidelidades = (valorBase * 500) / 2;

        return pontosFidelidades;
    }
}