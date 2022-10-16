public class BilhetePromocional extends Bilhete {

    public BilhetePromocional(){
        super();
    }
    @Override
    public double calcularPreco(){
       double precoBase = super();
       precoBase = ((precoBase * 0.06)+precoBase);
       return precoBase;
    }
}