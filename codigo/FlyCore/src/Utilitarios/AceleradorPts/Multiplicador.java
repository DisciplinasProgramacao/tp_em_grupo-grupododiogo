package Utilitarios.AceleradorPts;

public abstract class Multiplicador{
    
    protected String tipo = "";

    protected  static double precoMensal=0d;

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
    
    public abstract int multiplicarPts(int pts);

}