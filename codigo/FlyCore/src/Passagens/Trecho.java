package Passagens;

import java.io.Serializable;

public class Trecho implements Serializable {
    private static final long serialVersionUID = 20221L;

    private final int idTrecho;
    private String cidadeOrigem;
    private String cidadeDestino;

    /**
     * Recebe a cidade do aeroporto de origem e destino
     * @param cidadeOrigem,cidadeDestino 
     * @return void
     */
    public Trecho(String cidadeOrigem, String cidadeDestino) {
        this.cidadeOrigem  = cidadeOrigem;
        this.cidadeDestino = cidadeDestino;
        this.idTrecho      = this.hashCode();
    }

    /**
     * Alterar o nome da cidade de origem do trecho
     * @param cidadeOrigem
     * @return void
     */
    public void setCidadeOrigem(String cidadeOrigem) {
        this.cidadeOrigem = cidadeOrigem;
    }

    /**
     * Alterar o nome da cidade de origem do destino
     * @param cidadeDestino
     * @return void
     */
    public void setCidadeDestino(String cidadeDestino) {
        this.cidadeDestino = cidadeDestino;
    }

    /**
     * Metodo responsavel por retornar a descricao do trecho 
     * Exemplo de retorno: PUC9783 De Belo Horizonte para Parana
     * @return String
     * @author Esdras Filipe
     * @since 25/09/2022
     */
    @Override
    public String toString() {
        return "Trecho " + this.idTrecho + " de " + this.cidadeOrigem + " para " + this.cidadeDestino + ".\n\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trecho trecho = (Trecho) o;
        return idTrecho == trecho.idTrecho && cidadeOrigem.equals(trecho.cidadeOrigem) && cidadeDestino.equals(trecho.cidadeDestino);
    }
    private int somarValoresCharString(char[] a, char[] b){
        int somaA =0, somaB =0;
        for(char e : a){
            somaA += (int) e *5;
        }
        for(char f: b){
            somaB += (int) f;
        }
        return somaA+somaB;
    }

    @Override
    public int hashCode() {
        char[] origem = this.cidadeOrigem.toCharArray();
        char [] destino = this.cidadeDestino.toCharArray();
        return somarValoresCharString(origem, destino);
    }

    public String getCidadeDestino() {
        return cidadeDestino;
    }
    public String getCidadeOrigem() {
        return cidadeOrigem;
    }
    public int getIdTrecho() {
        return this.idTrecho;
    }
}