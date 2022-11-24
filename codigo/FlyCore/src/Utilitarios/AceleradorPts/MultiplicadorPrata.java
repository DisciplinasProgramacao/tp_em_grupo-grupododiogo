package Utilitarios.AceleradorPts;

public class MultiplicadorPrata implements IMultiplicavel {

    private static final String TIPO = "prata";
    private static double preco = 0d;
    private boolean ativo = false;
    private static final double VALOR = 1.25;
    
    public MultiplicadorPrata() {
        this.ativo = false;
    }

    @Override
    public int multiplicar(int pts) {
        int total = pts; 
        if(this.ativo)
            total = (int) (int ) (pts*MultiplicadorPrata.VALOR);
        return total;
    }

    @Override
    public boolean on_off() {
       if(this.ativo)
        this.ativo = false;
       else
       this.ativo = true;
       
       return this.ativo;
    }

    @Override
    public double getPreco() {
        return MultiplicadorPrata.preco;
    }

    @Override
    public String getTipo() {
        return MultiplicadorPrata.TIPO;
    }

    public static double setPreco(double val) {
        if(val > 0)
            MultiplicadorPrata.preco = val;
        return val;
    }
    
    @Override
    public boolean isAtivo() {
        return this.ativo;
    }
}
