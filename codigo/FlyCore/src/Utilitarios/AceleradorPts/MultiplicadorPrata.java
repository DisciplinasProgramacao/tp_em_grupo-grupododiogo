package Utilitarios.AceleradorPts;

public class MultiplicadorPrata extends Multiplicador{
    
    public MultiplicadorPrata() {    
        this.tipo = "Prata";
    }
    public MultiplicadorPrata(double precoMensal) {
        MultiplicadorPrata.precoMensal = precoMensal;
        this.tipo = "Prata";
    } 
    @Override
    public int multiplicarPts(int pts) {
        return (int) ((int) pts*1.25);
    }
}
