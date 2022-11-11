package Utilitarios.AceleradorPts;

public class MultiplicadorPreto implements IMultiplicavel {

    private static final String tipo = "preto";
    private static double preco=0d;
    private boolean ativo = false;
    private  static double valor = 1.50;
    
    public MultiplicadorPreto(){
        this.ativo = false;
    }

    @Override
    public int multiplicar(int pts) {
        return (int) (int ) (pts*MultiplicadorPreto.valor);
    }

    @Override
    public boolean on_off() {
        this.ativo = !this.ativo;
        return this.ativo;
    }

    @Override
    public double setPreco(double preco) {
        if(preco > 0)
            MultiplicadorPreto.preco = preco;    
        return preco;
    }

    @Override
    public double getPreco() {
        return MultiplicadorPreto.preco;
    }

    @Override
    public Object trocar() {
        return new MultiplicadorPrata();
    }
}
