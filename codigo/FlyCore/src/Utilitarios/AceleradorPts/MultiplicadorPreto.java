package Utilitarios.AceleradorPts;

public class MultiplicadorPreto  extends Multiplicador{

    public MultiplicadorPreto(double precoMensal) {
        super(precoMensal);
        this.tipo = "Preto";
    } 

    @Override
    public int multiplicarPts(int pts) {
        return (int) ((int) pts*1.5);
    }
}
