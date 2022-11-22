package Sistema;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Application {
    static Scanner teclado = new Scanner(System.in);
    // #region utilidades
    /**
     * "Limpa" a tela (códigos de terminal VT-100)
     * @return void
     */
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Pausa para leitura de mensagens em console
     * @return void
    */
    public static void pausa() {
        System.out.println("\nEnter para continuar.");
        teclado.nextLine();
    }
    //#endregion


    public static int menuPrincipal() {
        System.out.println();
        System.out.println();
        System.out.println("FLY CORE");
        System.out.println("==========================");
        System.out.println("1 - Comprar bilhete");
        System.out.println("2 - Editar bilhete");
        System.out.println("3 - Verificar pontuação");
        System.out.println("4 - Exibir bilhete");
        System.out.println("5 - Exibir todos os bilhetes");
        
        System.out.println("0 - Sair");
        System.out.print("Digite sua opção: ");
        try {
            int opcao = teclado.nextInt();
            teclado.nextLine();
            return opcao;
        } catch (InputMismatchException e) {
            return -1;
        }
    }
    public static void main(String[] args) {
        
        
    }
    
}
