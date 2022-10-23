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
        novoPreco = precoBase * 0.6;
        this.precoBilhete = novoPreco;
        return novoPreco;
    }

    @Override
    public int calcularPontuacao(){
        double valorAux = this.calcularPreco() / 500;
        int valorBase = (int)valorAux;

        int pontosFidelidades = (valorBase * 500) / 2;

        return pontosFidelidades;
    }
}