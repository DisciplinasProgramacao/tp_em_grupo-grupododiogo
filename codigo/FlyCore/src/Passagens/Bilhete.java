package Passagens;
import java.util.LinkedList;

public class Bilhete {
    private final int idBilhete;
    private LinkedList<Voo> voos = new LinkedList<Voo>();
    private boolean promocional = false;
    private boolean fidelidade = false;
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

