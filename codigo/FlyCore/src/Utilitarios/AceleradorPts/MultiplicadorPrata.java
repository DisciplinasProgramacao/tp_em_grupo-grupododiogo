package Utilitarios.AceleradorPts;

public class MultiplicadorPrata extends Multiplicador{

    public MultiplicadorPrata(double precoMensal) {
        super(precoMensal);
        this.tipo = "Prata";
    } 
    @Override
    public int multiplicarPts(int pts) {
        return (int) ((int) pts*1.25);
    }
}
