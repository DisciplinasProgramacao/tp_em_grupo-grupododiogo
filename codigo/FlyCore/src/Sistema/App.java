// package Sistema;

// import java.util.InputMismatchException;
// import java.util.Scanner;

// import Passagens.Bilhete;
// import Passagens.BilheteFidelidade;
// import Passagens.BilhetePromocional;
// import Passagens.Trecho;
// import Passagens.Voo;
// import Utilitarios.CidadesTrecho;
// import Utilitarios.Data;
// import Utilitarios.DatasVoo;

// public class App {
//     static Scanner teclado = new Scanner(System.in);
//     // #region utilidades
//     /**
//      * "Limpa" a tela (códigos de terminal VT-100)
//      * @return void
//      */
//     public static void limparTela() {
//         System.out.print("\033[H\033[2J");
//         System.out.flush();
//     }

//     /**
//      * Pausa para leitura de mensagens em console
//      * @return void
//     */
//     public static void pausa() {
//         System.out.println("\nEnter para continuar.");
//         teclado.nextLine();
//     }
//     //#endregion

//     //#region menus
//     /**
//      * Menu principal do sistema
//      * @return Opção do usuário (int)
//     */
//     public static int menuPrincipal() {

//         System.out.println();
//         System.out.println();

//         System.out.println("FLY CORE");
//         System.out.println("==========================");
//         System.out.println("1 - Comprar bilhete");
//         System.out.println("2 - Editar bilhete");
//         System.out.println("3 - Verificar pontuação");
//         System.out.println("4 - Exibir bilhete");
//         System.out.println("5 - Exibir todos os bilhetes");
        
//         System.out.println("0 - Sair");
//         System.out.print("Digite sua opção: ");
//         try {
//             int opcao = teclado.nextInt();
//             teclado.nextLine();
//             return opcao;
//         } catch (InputMismatchException e) {
//             return -1;
//         }
//     }

//     /**
//      * Menu para escolha do tipo de bilhete desejado
//      * @return Opção do usuário (int)
//     */
//     public static int menuTipoBilhete() {

//         System.out.println();
//         System.out.println();

//         System.out.println("FLY CORE");
//         System.out.println("==========================");
//         System.out.println("1 - Bilhete comum");
//         System.out.println("2 - Bilhete promocional");
//         System.out.println("3 - Bilhete fidelidade");
        
//         System.out.println("0 - Sair");
//         System.out.print("Digite sua opção: ");
//         try {
//             int opcao = teclado.nextInt();
//             teclado.nextLine();
//             return opcao;
//         } catch (InputMismatchException e) {
//             return -1;
//         }
//     }

//     /**
//      * Menu para gerenciar bilhetes
//      * @return Opção do usuário (int)
//     */
//     public static int menuBilhete() {
//         limparTela();
//         System.out.println();
//         System.out.println();

//         System.out.println("FLY CORE");
//         System.out.println("==========================");
//         System.out.println("1 - Inserir um voo");
//         System.out.println("2 - Remover um voo");
//         System.out.println("3 - Editar um voo");
//         System.out.println("0 - Cancelar");
//         System.out.print("Digite sua opção: ");
//         try {
//             int opcao = teclado.nextInt();
//             teclado.nextLine();
//             return opcao;
//         } catch (InputMismatchException e) {
//             return -1;
//         }
//     }

//     /**
//      * Menu para gerenciar voos
//      * @return Opção do usuário (int)
//     */
//     public static int menuVoo() {
//         limparTela();
//         System.out.println();
//         System.out.println();

//         System.out.println("FLY CORE");
//         System.out.println("==========================");
//         System.out.println("1 - Alterar trecho");
//         System.out.println("2 - Alterar data");
//         System.out.println("0 - Cancelar");
//         System.out.print("Digite sua opção: ");
//         try {
//             int opcao = teclado.nextInt();
//             teclado.nextLine();
//             return opcao;
//         } catch (InputMismatchException e) {
//             return -1;
//         }
//     }

//     /**
//      * Menu para escolher cidades disponíveis no sistema
//      * @return Opção do usuário (int)
//     */
//     public static String menuCidade(String escolha) {
//         limparTela();
//         System.out.println();
//         System.out.println();

//         System.out.println("FLY CORE");
//         System.out.println("==========================");

//         int posicaoCidade = 0;
//         for (String cidade : escolha == "origem" ? CidadesTrecho.getCidadesOrigem() : CidadesTrecho.getCidadesDestino()) {
//             System.out.println((posicaoCidade + 1) + " - " + cidade);
//             posicaoCidade ++;
//         }

//         System.out.println("0 - Cancelar");
//         System.out.print("Escolha a cidade de " + escolha + ": ");
//         try {
//             int opcao = teclado.nextInt();
//             teclado.nextLine();
//             return CidadesTrecho.getCidadesOrigem().get(opcao - 1);
//         } catch (InputMismatchException e) {
//             return "";
//         }
//     }

//      /**
//      * Menu para escolher datas de voos disponíveis no sistema
//      * @return Opção do usuário (int)
//     */
//     public static String menuData() {
//         limparTela();
//         System.out.println();
//         System.out.println();

//         System.out.println("FLY CORE");
//         System.out.println("==========================");

//         int posicaoData = 0;
//         for (String datas : DatasVoo.getDatasDisponiveis()) {
//             System.out.println((posicaoData + 1) + " - " + datas);
//             posicaoData ++;
//         }

//         System.out.println("0 - Cancelar");
//         System.out.print("Escolha uma data disponível: ");
//         try {
//             int opcao = teclado.nextInt();
//             teclado.nextLine();
//             return DatasVoo.getDatasDisponiveis().get(opcao - 1);
//         } catch (InputMismatchException e) {
//             return "";
//         }
//     }
//     //#endregion

//     //#region utilitários
//     /**
//      * verifica se um bilhete já foi instanciado
//      * @param novoBilhete objeto da classe Bilhete
//      * @return boolean. True para bilhete diferente de null. Do contrário, false
//      */
//     public static boolean bilheteCriado(Bilhete novoBilhete) {
//         if (novoBilhete != null) {
//             return true;
//         } else {
//             return false;
//         }
//     }

//     /**
//      * pede uma data ao usuário dentre as opções disponíveis no sistema
//      * @return instancia criada da classe Data
//      */
//     public static Data formatarData() {
//         String dataVoo = "";
//         do {
//             dataVoo = menuData();
//         } while (dataVoo == "");

//         String[] arrDataVoo = dataVoo.split("/");

//         return new Data(
//         Integer.parseInt(arrDataVoo[0]),
//         Integer.parseInt(arrDataVoo[1]),
//         Integer.parseInt(arrDataVoo[2]));
//     }

//     /**
//      * pede as cidades origem e destino para criar uma instancia da classe Trecho 
//      * @return instancia criada da classe Trecho
//      */
//     public static Trecho formatarTrecho() {
//         String cidadeOrigem = "";
//         do {
//             cidadeOrigem = menuCidade("Origem");
//         } while (cidadeOrigem == "");

//         String cidadeDestino = "";
//         do {
//             cidadeDestino = menuCidade("Destino");
//         } while (cidadeDestino == "");

//         return new Trecho(cidadeOrigem, cidadeDestino);
//     }

//     /**
//      * cria uma instancia da classe Voo 
//      * @return instancia criada da classe Voo
//      */
//     public static Voo formatarVoo() {
//         Trecho novoTrecho = formatarTrecho();

//         Data dataVoo = formatarData();
        
//         return new Voo(novoTrecho, dataVoo, 500);
//     }
//     //#endregion

//     public static void teste() throws Exception {

//         /*PROTÓTIPO DE SISTEMA (não existe ainda gerenciamento de cliente)*/

//         Bilhete novoBilhete = null;
//         int opcao;

//         do {
//             limparTela();
//             opcao = menuPrincipal();

//             switch (opcao) {
//                 case 1: 
//                     if (!bilheteCriado(novoBilhete)) {
//                         int opcaoTipoBilhete = menuTipoBilhete();

//                         switch (opcaoTipoBilhete) {
//                             case 1:
//                                 novoBilhete = new Bilhete();
//                                 System.out.println("Bilhete gerado com sucesso");
//                             break;

//                             case 2:
//                                 novoBilhete = new BilhetePromocional();
//                                 System.out.println("Bilhete promocional gerado com sucesso");
//                             break;

//                             case 3:
//                                 novoBilhete = new BilheteFidelidade();
//                                 System.out.println("Bilhete fidelidade gerado com sucesso");
//                             break;
//                         }
//                     } else {
//                         System.out.println("Já existe um bilhete cadastrado");
//                     } 
//                 break;

//                 case 2: 
//                     if (bilheteCriado(novoBilhete)) {
//                         int opcaoBilhete = menuBilhete();

//                         switch (opcaoBilhete) {
//                             case 1: 
//                                 novoBilhete.inserirVoo(formatarVoo());
//                                 System.out.println("Voo inserido no bilhete cadastrado");
//                             break; 

//                             case 2: 
//                                 System.out.print("Digite o id do voo que deseja remover: ");
//                                 int idVooEscolhidoParaRemocao = teclado.nextInt();
//                                 teclado.nextLine();
                                
//                                 if (novoBilhete.removerVoo()) {
//                                     System.out.println("Voo removido com sucesso");
//                                 } else {
//                                     System.out.println("Não foi possível remover o voo. Por favor, verifique o id inserido.");
//                                 }
//                             break;

//                             case 3: 
//                                 System.out.print("Digite o id do voo que deseja editar: ");
//                                 int idVooEscolhidoParaEdicao = teclado.nextInt();
//                                 teclado.nextLine();

//                                 int indexVooEscolhido = novoBilhete.buscarIndexVoo(idVooEscolhidoParaEdicao);
//                                 if (indexVooEscolhido == -1) {
//                                     System.out.println("Voo não cadastrado no bilhete");
            
//                                     break;
//                                 } else {
//                                     Voo vooEscolhido = novoBilhete.buscarVoo(indexVooEscolhido);

//                                     int opcaoVoo = menuVoo();
                                
//                                     switch (opcaoVoo) {
//                                         case 1:
//                                             System.out.println("\nDigite as informações do novo trecho do voo: \n");

//                                             vooEscolhido.alterarTrecho(formatarTrecho());
//                                             System.out.println("Trecho do voo alterado com sucesso");
//                                         break;

//                                         case 2:
//                                             System.out.println("\nDigite as informações da nova data do voo: \n");

//                                             vooEscolhido.alterarData(formatarData());
//                                             System.out.println("Trecho do voo alterado com sucesso");
//                                         break;

//                                         case 0:
//                                         break;
//                                     }
//                                 }
//                             break;

//                             case 0:
//                             break;
//                         }
//                     } else {
//                         System.out.println("Nenhum bilhete cadastrado");
//                     }
//                 break;

//                 case 3: 
//                     if (bilheteCriado(novoBilhete)) {
//                         limparTela();

//                         System.out.println("\nA pontuação do bilhete é: " + novoBilhete.calcularPontuacao() + "\n");
//                     } else {
//                         System.out.println("Nenhum bilhete cadastrado");
//                     }
//                 break;

//                 case 4: 
//                     if (bilheteCriado(novoBilhete)) {
//                         limparTela();

//                         System.out.println(novoBilhete);
//                     } else {
//                         System.out.println("Nenhum bilhete cadastrado");
//                     }
//                 break;

//                 case 5: 
//                     System.out.println("Funcionalidade ainda não implementada :)");
//                 break;
//             }
//             if (opcao != 0) {
                
//                 pausa();
//             } else {
//                 System.out.println("Obrigado por usar o Fly Core. Volte sempre!");
//             }
//         } while (opcao != 0);

//         teclado.close();
//     }
// }
