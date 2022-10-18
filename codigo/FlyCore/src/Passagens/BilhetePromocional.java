package Passagens;

public class BilhetePromocional extends Bilhete {
    public BilhetePromocional() {
        super();
        this.tipo = "Promocional";
    }

    @Override
    public double calcularPreco() {
        double precoBase = super.calcularPreco();
        double novoPreco = 0d;
        novoPreco = precoBase * 0.06;
        this.precoBilhete = novoPreco;
        return novoPreco;
    }
}