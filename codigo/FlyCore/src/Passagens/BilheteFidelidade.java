package Passagens;

public class BilheteFidelidade extends Bilhete {

    /**
     * Construtor do bilhete de fidelidade, atribuindo um hashCode como id e o tipo do bilhete
     */
    public BilheteFidelidade() {
        super();
        this.tipo = "Fidelidade";
    }

    /** Calcula o preço de um bilhete de fidelidade
     * @return $0
     */
    @Override
    public double calcularPreco() {
        return 0d;
    }

    /** Calcula os pontos de fidelidade de um bilhete de fidelidade
     * @return 0 pontos de fidelidade
     */
    @Override
    public int calcularPontuacao(){
        return 0;
    }
}