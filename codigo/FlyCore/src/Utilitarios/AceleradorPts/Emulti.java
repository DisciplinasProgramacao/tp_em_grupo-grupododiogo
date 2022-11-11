package Utilitarios.AceleradorPts;

public enum Emulti {
    PRATA(0), PRETO(1);

    private final int valor;

    Emulti(int valorOpcao){
        valor = valorOpcao;
    }

    public int getValor(){
        return valor;
    }

}
