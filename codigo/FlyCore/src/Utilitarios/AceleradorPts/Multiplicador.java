package Utilitarios.AceleradorPts;

public class Multiplicador implements Imulti{

    private String tipo = " ";
    private static double precoPrata=0d;
    private static double precoPreto=0d;
    private boolean ativo = false;
    private double valor = 0d;
    
    
    public Multiplicador(String tipo){
        this.tipo = tipo;
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
        return this.tipo;
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
   switch (this.tipo) {
    case "preto":
            this.tipo = "prata";
            this.valor = 1.25;
        break;
    case "prata":
            this.tipo = "preto";    
            this.valor = 1.5;
    default:
        break;
   }
    return this.getTipo();
}
}