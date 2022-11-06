package Utilitarios.AceleradorPts;

public class Multiplicador implements IMultiplicar {
    
    protected String tipo = "";

    protected double precoMensal=0d;

    /**
     * @param precoMensal
     */

    public Multiplicador(double precoMensal) {
        this.precoMensal = precoMensal;
    }

    protected double setPrecoMensal(double preco){
            try{
                this.precoMensal = preco;
            }
            catch(Error error){
                System.out.println(error+"\n"+tipo);
            }
            return preco;
    }   
    
    protected double getPrecoMensal(){
        return this.precoMensal;
    }
    @Override
    public int multiplicarPts(int pts) {
        return 0;
    }

}