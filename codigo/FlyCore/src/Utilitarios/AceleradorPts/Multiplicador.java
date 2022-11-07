package Utilitarios.AceleradorPts;

public abstract class Multiplicador{
    protected String tipo = "";

    protected  static double precoMensal=0d;

    public double setPrecoMensal(double preco){
            try{
                precoMensal = preco;
            }
            catch(Error error){
                System.out.println(error+"\n"+tipo);
            }
            return precoMensal;
    }   
    
    public double getPrecoMensal(){
        return precoMensal;
    }
    
    public abstract int multiplicarPts(int pts);

}