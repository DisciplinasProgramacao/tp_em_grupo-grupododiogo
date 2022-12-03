package Utilitarios.AceleradorPts;

public interface IMultiplicavel {
    /**
     * @param pts Número inteiro que será multiplicado pelo valor do multiplicador.
     * @return pts após o boost do multiplicador.
     */
    public int multiplicar(int pts);

    /**
     * Ativa e Desativa o multiplicador
     * @return boolean status do multiplicador após a execução do método
     */
    public boolean on_off();

    /**
     * Retorna o preço relativo a classe de multiplicador que está utilizando o método.
     */
    public double getPreco();

     /**
     * Retorna o tipo relativo a classe de multiplicador que está utilizando o método.
     * @return String --> ex: Prata, Preto.
     */
    public String getTipo();

    /**
     * Retorna o status atual da instancia de multiplicador que implementa o método.
     * @return boolean --> true : ativo ; false : desativado
     */
    public boolean isAtivo();

    /**
     * Seta o preço da instancia de multiplicador que implementa o método.
     */
    public double setPreco(double preco);
}
