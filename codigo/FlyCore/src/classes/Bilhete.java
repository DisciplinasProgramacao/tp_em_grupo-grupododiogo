package classes;

import java.util.LinkedList;

public class Bilhete {
    private final int idBilhete;
    private LinkedList<Voo> voos;

    public Bilhete() {
        this.idBilhete = this.hashCode();
    }

    /**
     * adciona o voo na lista de voos.
     * @param novoVoo
     * @return true em caso de sucesso.
     */
    public boolean inserirVoo(Voo novoVoo) {
        voos.add(novoVoo);
        return true;
    }

    /**
     * @param idVoo
     * @return true caso consiga remover o voo com sucesso da lista. Caso isso não ocorra, 
     * retorna false. 
     */
    public boolean removerVoo(int idVoo) {
        try {
            voos.remove(buscarIndexVoo(idVoo));
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
        for (int i = 0; i < voos.size(); i++) {
            infoBilhete.append(buscarVoo(i).toString());
        }
        return infoBilhete.toString();
    }

    /**
     * @param indexVoo
     * @return Voo encontrado.
     */
    private Voo buscarVoo(int indexVoo) {
        return voos.get(indexVoo);
    }

    /**
     * @param idVoo
     * @return retorna o index do voo na lista de voos. Caso o voo não exista na lista, retorna
     * -1.
     */
    private int buscarIndexVoo(int idVoo) {
        for (int i = 0; i < voos.size(); i++) {
            if (voos.get(i).getIdVoo() == idVoo) {
                return i;
            }
        }
        return -1;
    }
}

