public class BilhetePromocional extends Bilhete {

    public BilhetePromocional(){
        super();
    }
    @Override
    public double calcularPreco(){
       double precoBase = super(), novoPreco=0d;
        novoPreco = ((precoBase * 0.06)+precoBase);
       this.preco = novoPreco;
       return novoPreco;
    }
}