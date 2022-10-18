package Passagens;
import java.util.LinkedList;

public class Bilhete {
    protected final int idBilhete;
    protected LinkedList<Voo> voos = new LinkedList<Voo>();
    protected double precoBilhete = 0d;

    public Bilhete() {
        this.idBilhete = this.hashCode();
    }

    /**
     * adciona o voo na lista de voos.
     * @param novoVoo
     * @return true em caso de sucesso.
     */
    public boolean inserirVoo(Voo novoVoo) {
        this.voos.add(novoVoo);
        return true;
    }

    /**
     * @param idVoo
     * @return true caso consiga remover o voo com sucesso da lista. Caso isso não ocorra, 
     * retorna false. 
     */
    public boolean removerVoo(int idVoo) {
        try {
            this.voos.remove(buscarIndexVoo(idVoo));
            return true;
        }
        catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    /**
     *
     * @return double precoFinal do bilhete
     */
    public double calcularPreco() {
        double precoFinal = 0d;
        if (this.voos.isEmpty()) {
            return precoFinal;
        }

        if (this.voos.size() == 1) {
            double precovoo = voos.get(0).getPreco();
            precoFinal = (0.010 * precovoo) + precovoo;
            this.precoBilhete = precoFinal;
        } else {
            Voo vooMaisCaro = encontrarVooMaiorValor();
            precoFinal = somarPrecoVoosRestantes(vooMaisCaro) + vooMaisCaro.getPreco();
            this.precoBilhete = precoFinal;
        }
        return precoFinal;
    }

    protected Voo encontrarVooMaiorValor() {
        try {
            Voo vooComMaiorPreco = this.voos.get(0);
            double precoAtual = 0d;

            for (int i = 0; i < this.voos.size() - 1; i++) {
                precoAtual = this.voos.get(i).getPreco();
                if (precoAtual < this.voos.get(i + 1).getPreco()) {
                    vooComMaiorPreco = this.voos.get(i + 1);
                }
            }

            return vooComMaiorPreco;
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    private double somarPrecoVoosRestantes(Voo maiorVoo) {
        double precoDescontado = 0d, somaVoo = 0d;
        for (Voo voosBilhete: this.voos) {
            if (!voosBilhete.equals(maiorVoo)) {
                somaVoo += voosBilhete.getPreco();
            }
        }

        precoDescontado = somaVoo * 0.5;
        return precoDescontado;
    }

    /** 
     * @return String com as informações de todos os Voos do bilhete.
     */
    @Override
    public String toString() {
        StringBuilder infoBilhete = new StringBuilder();
        infoBilhete.append("=========== Bilhete número: "+this.idBilhete+"===========\n");
        if (this.voos.isEmpty()) {
            return "Nenhum voo cadastrado";
        }

        for (int i = 0; i < this.voos.size(); i++) {
            infoBilhete.append(buscarVoo(i).toString());
        }
        return infoBilhete.toString();
    }

    /**
     * @param indexVoo
     * @return Voo encontrado.
     */
    private Voo buscarVoo(int indexVoo) {
        return this.voos.get(indexVoo);
    }

    /**
     * @param idVoo
     * @return retorna o index do voo na lista de voos. Caso o voo não exista na lista, retorna
     * -1.
     */
    private int buscarIndexVoo(int idVoo) {
        for (int i = 0; i < this.voos.size(); i++) {
            if (this.voos.get(i).getIdVoo() == idVoo) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Retorna id do bilhete
     * @return int idBilhete
     */
    public int getIdBilhete() {
        return this.idBilhete;
    }
}

