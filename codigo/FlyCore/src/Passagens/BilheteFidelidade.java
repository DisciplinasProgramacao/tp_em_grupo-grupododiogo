package Passagens;

public class BilheteFidelidade extends Bilhete {
    public BilheteFidelidade() {
        super();
        this.tipo = "Fidelidade";
    }

    @Override
    public double calcularPreco() {
        return 0d;
    }

    @Override
    public int calcularPontuacao(){
        return 0;
    }
}