package Passagens;

import Utilitarios.Data;

import java.io.Serializable;
import java.util.Objects;

public class Voo implements Serializable {
    private static final long serialVersionUID = 20221L;

//#region Atributos Do Voo
    private final int idVoo;
    private Trecho trechoVoo;
    private Data dataVoo;
    private double preco;
//#endregion

//#region Construtor do Voo
    /**
     * O id do voo será único e será gerado através de um hash code pelo proprio sistema
     * @param trecho Recebe Trecho que está ligado ao voo
     * @param data Recebe a Data referente ao voo
     */
    public Voo(Trecho trecho, Data data, double precoVoo, int id) {
        this.trechoVoo = trecho;
        this.dataVoo = data;
        this.idVoo = id;
        this.preco = precoVoo;
    }
//#endregion Construtor do Voo

//#region Metodos uteis do Voo
    /**
     * Método que recebe um novo Trecho e substitui o Trecho atual do Voo
     * @param novoTrecho Novo Trecho do Voo
     * @return boolean Retorno true --> Sucesso na alteração; false --> Erro na alteração do Trecho
     */
    public boolean alterarTrecho(Trecho novoTrecho) {
        try {
            this.trechoVoo = novoTrecho;
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Método que recebe uma nova Data e substitui a data atual do Voo
     * @param novaData Nova data do Vooo
     * @return boolean Retorno true --> Sucesso na alteração; false --> Erro na alteração da Data
     */
    public boolean alterarData(Data novaData) {
        try {
            this.dataVoo = novaData;
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public int getIdVoo() {
        return this.idVoo;
    }

    public double getPreco() {
        return this.preco;
    }
    public Trecho getTrecho(){
        return this.trechoVoo;
    }
    public Data getData() {
        return this.dataVoo;
    }

    public String getCidadeDestino() {return this.trechoVoo.getCidadeDestino();}
    //#endregion Metodos uteis do Voo


//#region Override's da Classe
    @Override
    public String toString() {
        StringBuilder infoVoo = new StringBuilder();
        infoVoo.append("=========== Voo Número: " + this.idVoo + " ===========" +
        "\nData do Voo: " + this.dataVoo.dataFormatada() +
        "\nPreço do voo: " + this.preco + "\n");
        infoVoo.append(this.trechoVoo.toString());
        return infoVoo.toString();
    }

    @Override
    public boolean equals(Object o) {
        Voo voo = (Voo) o;
        return voo.hashCode()== hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVoo);
    }
//#endregion Override's da Classe
}
