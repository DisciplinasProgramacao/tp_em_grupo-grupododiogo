package Utilitarios.AceleradorPts;

public class MultiplicadorPreto  extends Multiplicador{
    public MultiplicadorPreto() {    
        this.tipo = "Prata";
    }
    public MultiplicadorPreto(double precoMensal) {
        MultiplicadorPreto.precoMensal = precoMensal;
        this.tipo = "Preto";
    } 
    @Override
    public int multiplicarPts(int pts) {
        return (int) ((int) pts*1.5);
    }
}
