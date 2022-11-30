package Utilitarios.AceleradorPts;

import java.io.Serializable;

public class MultiplicadorPreto implements IMultiplicavel, Serializable {
    private static final long serialVersionUID = 1L;

    private static final String TIPO = "preto";
    private static double preco = 0d;
    private boolean ativo = false;
    private static final double valor = 1.50;
    
    public MultiplicadorPreto(){
        this.ativo = false;
    }
    
    @Override
    public int multiplicar(int pts) {
        int total = pts; 
        if(this.ativo)
            total = (int) (int) (pts * MultiplicadorPreto.valor);
        return total;//retorna os pts recebidos caso esteja inativo
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
        return MultiplicadorPreto.preco;
    }

    @Override
    public String getTipo() {
        return MultiplicadorPreto.TIPO;
    }

    public static double setPreco(double val) {
        if (val > 0)
            MultiplicadorPreto.preco = val;
        return val;
    }
    
    @Override
    public boolean isAtivo() {
        return this.ativo;
    }
}