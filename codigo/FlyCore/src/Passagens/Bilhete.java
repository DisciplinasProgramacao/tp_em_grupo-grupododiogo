package Passagens;
import java.util.LinkedList;

public abstract class Bilhete {
    private final int idBilhete;
    private LinkedList<Voo> voos = new LinkedList<Voo>();
    private double precoBilhete = 0d;
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
    public double calcularPreco(){
        double precoFinal=0;
        if(!this.voos.isEmpty() && this.voos.size() == 1){
            double precovoo = voos.get(0).preco;
            precoFinal = (0.010 * precovoo) + precovoo;
            this.precoBilhete = precoFinal;
        }
        else{
            if(!this.voos.isEmpty && this.voos.size()>1){
                int idVooMaisCaro = encontrarVooMaiorValor();
                double precoVooMaisCaro  = buscarVoo(buscarIndexVoo(idVooMaisCaro)).getPreco();
                precoFinal = somarPrecoVoos(idVooMaisCaro)+precoVooMaisCaro;
                this.precoBilhete = precoFinal;
            }
        }
        return precoFinal;
    }
    private int encontrarVooMaiorValor(){
        try {
            if (!this.voos.isEmpty()) {
                int idMaiorVoo = 0;
                double maiorPrecoVoo = 0d;
                for (int i = 0; i < this.voos.size() - 1; i_++) {
                    if(this.voos.get(i)!=null) {
                        maiorPrecoVoo = this.voos.get(i).getPreco();
                        idMaiorVoo = this.voos.get(i).getIdVoo();
                        if (maiorPrecoVoo < this.voos.get(i + 1).getPreco()) {
                            maiorPrecoVoo = this.voos.getPreco();
                            idMaiorVoo = this.voos.getIdVoo();
                        }
                    }
                }
            }
            return idMaiorVoo;
        }
        catch (IndexOutOfBoundsException e) {System.out.println(e); return 0d;}
    }
    private double somarPrecoVoos(int idMaiorVoo){
        double precoDescontado=0d, somaVoo=0d;
        for (Voo voosBilhete: this.voos) {
            if(voosBilhete != null && voosBilhete.getIdVoo() != idMaiorVoo){
                precoDescontado = (voosBilhete.getPreco()*0.5);
                somaVoo+=precoDescontado;
            }
        }
        return somaVoo;
    }
    /** 
     * @return String com as informações de todos os Voos do bilhete.
     */
    @Override
    public String toString(){
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

    public int getIdBilhete() {
        return this.idBilhete;
    }
}

