package Passagens;

import java.util.Objects;

public class Trecho {

    private final int idTrecho;
    private String cidadeOrigem;
    private String cidadeDestino;

    /**
     * Recebe a cidade do aeroporto de origem e destino
     * @param cidadeOrigem,cidadeDestino 
     * @return void
     */
    public Trecho(String cidadeOrigem, String cidadeDestino) {
        this.idTrecho      = this.hashCode();
        this.cidadeOrigem  = cidadeOrigem;
        this.cidadeDestino = cidadeDestino;
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

    @Override
    public int hashCode() {
        return Objects.hash(cidadeOrigem, cidadeDestino);
    }

    public int getIdTrecho() {
        return this.idTrecho;
    }
}