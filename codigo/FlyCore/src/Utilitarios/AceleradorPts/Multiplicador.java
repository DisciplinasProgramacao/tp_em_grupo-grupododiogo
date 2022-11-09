package Utilitarios.AceleradorPts;

public class Multiplicador implements Imulti{

    private boolean preto = false;
    private boolean prata = true;
    private static double precoPrata=0d;
    private static double precoPreto=0d;
    private boolean ativo = false;
    private double valor = 0d;
    
    
    public Multiplicador(){
        this.prata = true;
        this.valor = 1.25;
    }

    public double setPrecoPrata(double valor){
        if(valor>0)
            Multiplicador.precoPrata = valor;
        return valor;
    }   
    
    public double setPrecoPreto(double valor){
        if(valor>0)
            Multiplicador.precoPreto = valor;
        return valor;
    }

    public String getTipo(){
        if(this.prata)
            return "prata";
        else
            return "preto";    
    }

    public boolean isAtivo(){
        return this.ativo;
    }

    
@Override
public boolean ativar() {
    if(Multiplicador.precoPrata > 0d && Multiplicador.precoPreto > 0d)
        this.ativo = !this.ativo;
    return this.ativo;
}
@Override
public int multiplicar(int pontuacao) {
    
    return (int)(int)(this.valor * pontuacao);
}
@Override
public String trocar() {
    if(!this.prata){
        this.prata= true;
        this.valor = 1.25;
    }
    else{
        this.preto = true;
        this.valor = 1.5; 
    }   
    return this.getTipo();
}
}