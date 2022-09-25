package classes;
import java.util.Date;
public class Voo {
//#region Atributos Do Voo
    private final int idVoo;
    private Trecho trechoVoo;
    private Date dataVoo;
//#endregion

//#region Construtor do Voo

    /**
     * O id do voo será único e será gerado através de um hash code pelo proprio sistema
     * @param trecho Recebe Trecho que está ligado ao vooo
     * @param data Recebe a Data referente ao voo
     */
    public Voo (Trecho trecho, Date data){
        this.trechoVoo = trecho;
        this.dataVoo = data;
        this.idVoo = this.hashCode();
    }
//#endregion

//#region Metodos uteis do Voo
    /**
     * Método que recebe um novo Trecho e substitui o Trecho atual do Voo
     * @param novoTrecho Novo Trecho do Voo
     * @return boolean Retorno true --> Sucesso na alteração; false --> Erro na alteração do Trecho
     */
    public boolean alterarTrecho(Trecho novoTrecho){
        try {
            this.trechoVoo = novoTrecho;
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Método que recebe uma nova Data e substitui a data atual do Voo
     * @param novaData Nova data do Vooo
     * @return boolean Retorno true --> Sucesso na alteração; false --> Erro na alteração da Data
     */

    public boolean alterarData(Date novaData){
        try {
            this.dataVoo = novaData;
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //#endregion

//#region Override's da Classe
    @Override
    public String toString(){
        StringBuilder infoVoo = new StringBuilder();
        infoVoo.append("=========== Voo Número: "+this.idVoo+"\n Data do Voo: "+this.dataVoo+"\n");
        infoVoo.append(this.trechoVoo.toString());
        return infoVoo.toString();
    }
    @Override
    public boolean equals(Object obj){
        Voo nv;
        nv = (Voo) obj;
        if(this.idVoo == nv.idVoo)
            return true;
        else
            return false;
    }
    //endregion
}