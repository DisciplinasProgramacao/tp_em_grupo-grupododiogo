package Utilitarios.AceleradorPts;

public class MultiplicadorPrata implements IMultiplicavel{

    private static final String tipo = "prata";
    private static double preco=0d;
    private boolean ativo = false;
    private  static  final double valor = 1.50;
    
    public MultiplicadorPrata(){
        this.ativo = false;
    }

    @Override
    public int multiplicar(int pts) {
        return (int) (int ) (pts*MultiplicadorPrata.valor);
    }

    @Override
    public boolean on_off() {
        this.ativo = !this.ativo;
        return this.ativo;
    }

    @Override
    public double setPreco(double preco) {
        if(preco > 0)
            MultiplicadorPrata.preco = preco;    
        return preco;
    }

    @Override
    public double getPreco() {
        return MultiplicadorPrata.preco;
    }

    @Override
    public Object trocar() {
        return new MultiplicadorPreto();
    }
}
