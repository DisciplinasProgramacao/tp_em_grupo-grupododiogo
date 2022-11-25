package Sistema;


import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;


import Utilitarios.*;
import Utilitarios.AceleradorPts.*;
import Clientes.Cliente;
import Passagens.Trecho;
import Passagens.Voo;
import Utilitarios.CidadesTrecho;

public class Application {
    private static Scanner teclado = new Scanner(System.in);//Leitor para INTEGER
    private static Scanner sc = new Scanner(System.in);//leitor String
    private static Map<Integer, Voo> voosSistema = new HashMap<>();
    private static LinkedList<Trecho> trechosSistema = new LinkedList<>();
    private static Map<Integer, Cliente> clientesSistema = new HashMap<>();
    private static Trecho trechoVooCadastro = null ;
    private static Data dataVooCadastro = new Data();
    private static double precoVooCadastro = 0d;
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

    //#region Trecho
    private static boolean adicionarTrechoAlista(Trecho novoTrecho){
        if(!trechosSistema.contains(novoTrecho))
              return  trechosSistema.add(novoTrecho);
        return false;    
    }
    private static String escolherCidadesTrecho(String escolha) { 
        limparTela();
        System.out.println();
        System.out.println();

        System.out.println("FLY CORE");
        System.out.println("==========================");

        int posicaoCidade = 0;
        for (String cidade : escolha == "origem" ? CidadesTrecho.getCidadesOrigem() : CidadesTrecho.getCidadesDestino()) {
            System.out.println((posicaoCidade + 1) + " - " + cidade);
            posicaoCidade ++;
        }
        System.out.print("Escolha a cidade de " + escolha + ": ");
        try {
            int opcao = teclado.nextInt();
            teclado.nextLine();
            return CidadesTrecho.getCidadesOrigem().get(opcao - 1);
        } catch (InputMismatchException e) {
            pausa();
            System.out.println("\nEscolha uma opção valida: ");
            return "";
        }
        catch(IndexOutOfBoundsException x){
            pausa();
           System.out.println("\nEscolha uma opção valida: "); 
           return "";
        }
        
    }
    private static Trecho formarTrecho() {
        String cidadeOrigem = "";
        do {
            cidadeOrigem = escolherCidadesTrecho("Origem");
        } while (cidadeOrigem == "");

        String cidadeDestino = "";
        do {
            cidadeDestino = escolherCidadesTrecho("Destino");
        } while (cidadeDestino == "");

        return new Trecho(cidadeOrigem, cidadeDestino);
    }
    private static Trecho escolherTrechoVoo(){
        limparTela();
        System.out.println();
        System.out.println();
        System.out.println("FLY CORE");
        System.out.println("==========================");
        Trecho trechoEscolhido = formarTrecho();
        if(!trechosSistema.contains(trechoEscolhido)){
            System.out.println("\nTrecho não cadastrado");
            trechoEscolhido = null;
        }
        else{
            System.out.println("\nOK Trecho escolhido!");
        }
        return trechoEscolhido;
    }
    //endregion

    //#region Voo
    private static Voo buscarVoo(Voo vooBusca){
        if(!voosSistema.containsKey(vooBusca.hashCode())){
                return null;
        }
        return voosSistema.get(vooBusca.hashCode());
    }
    private static boolean adicionarVooAlista(Voo novoVoo){
        if(!voosSistema.containsKey(novoVoo.hashCode()) && novoVoo.getTrecho().getIdTrecho()!=0){
        voosSistema.put(novoVoo.hashCode(), novoVoo);
        return true;
    }
        return false;
    }
    private static double cadastrarPreco(){
        Scanner sc = new Scanner (System.in);

        double preco=0d;
        limparTela();
        System.out.println("Entre com o Preço do Voo: ");
        try{
            preco = sc.nextDouble();
        }
        catch(InputMismatchException e){
            System.out.println("insira opção valida!");
            preco = -1d;
        }
        return preco;
    }
    private static Data cadastrarData(){
        //Implementar método 
        return new Data();
    }
    private static Voo formarVoo(Trecho novoTrecho, Data dataVoo, double preco){
        return new Voo(novoTrecho, dataVoo, preco);
    }
    //endregion

    //# region Multiplicador
    private static int escolherMultiplicador (){
        System.out.println();
        System.out.println("FLY CORE");
        System.out.println("==========================");
        System.out.println("1 - Multi Prata");
        System.out.println("2 - Multi Preto");
        System.out.println("0 - Cancelar");
        System.out.print("Digite sua opção: ");
        try {
            int opcao = teclado.nextInt();
            teclado.nextLine();
            return opcao;
        } catch (InputMismatchException e) {
            return -1;
        }
    }
    private static IMultiplicavel gerarMultiplicador(){
        //implementar 
        int optMulti = escolherMultiplicador();
        switch(optMulti){
            case 1:
                return new MultiplicadorPrata();
            case 2:
                return new MultiplicadorPreto();
            default:
            System.out.println("Insira um opção valida");
            return null;
        }
    }
    //#endregion

    private static boolean validarNome(String nome){
        if(nome.matches("[0-9]+") || nome.isEmpty())
            return false;
        return true;
    }
    private static boolean validarCpf(String cpf){
        cpf = cpf.toLowerCase();
        cpf = cpf.strip();
        if(cpf.matches("[a-zA-Z]+") || cpf.length()!=11)
            return false;
        return true;    
    }
    //#region Clientes
    private static String receberCPFbusca(){
        String cpf ="";
        System.out.println("\nInsira o CPF do Cliente que deseja C: ");
        cpf = sc.nextLine();
        if(!validarCpf(cpf)){
                return "";
        }
        return cpf;
    }
    private static Cliente buscarCliente(String cpfCliente){
        Cliente clienteBusca = new Cliente("", cpfCliente);
        try{
        Cliente clienteEncontrado = clientesSistema.get(clienteBusca.hashCode());
            return clienteEncontrado;
        }
        catch(NullPointerException nulo){
            System.out.println("Erro cliente nulo: " + nulo);
        }
        return clienteBusca; // retorna Cliente apenas com cpf caso não seja encontrado
    }
    private static boolean addClienteAoMapa(Cliente novoCliente){
       try{
        if(!clientesSistema.containsKey(novoCliente.hashCode())){
            clientesSistema.put(novoCliente.hashCode(), novoCliente);
             return true;
         }
        return false;
    }
    catch(NullPointerException e){
            throw e;
    }
    catch(NumberFormatException e){
        throw e;
    }
    }
    private static boolean atualizarClienteMapa(Cliente novoCliente){
        try{
            if(clientesSistema.containsKey(novoCliente.hashCode())){
                clientesSistema.replace(novoCliente.hashCode(), novoCliente);
                return true;
            }
           else{return false;}
        }catch(NullPointerException e){
            System.out.println("Erro:"+e);
            return false;
        }
    }
    private static String receberDadosClienteCadastro(){
      
        String nome= "", cpf = "";
        System.out.println("\nEntre com o Nome do Cliente: ");
        String dadosCliente = "";
        try{
            nome = sc.nextLine();
            if(!validarNome(nome)){
                System.out.println("Nome Invalido! ");
                pausa();
                limparTela();
                return dadosCliente;
            }
        
        System.out.println("\nInsira o CPF do cliente? ");
        cpf = sc.nextLine();
            
            if(!validarCpf(cpf)){
                System.out.println("\n CPF INVALIDO!");
                pausa();
                limparTela();
            }
            dadosCliente = nome +";"+cpf;
            return dadosCliente;
        }
        catch(InputMismatchException e){System.out.println(e); throw e;}
        
    }
    private static Cliente cadastrarCliente(){
        String dadosCliente ="";
        dadosCliente = receberDadosClienteCadastro();
        String[] separandoDados = dadosCliente.split(";");
        try{
            Cliente novoCliente = new Cliente(separandoDados[0], separandoDados[1]);
            return novoCliente;
        }
        catch(NullPointerException e){
            System.out.println("Erro ao encontrar classe Clientes");
           throw e;
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("Erro ao colher dados do Cliente");
            return new Cliente("", "");
        }
    }
   private static boolean alocarMultiplicadorCliente(Cliente cl, IMultiplicavel ac){
    if(ac == null){return false;};
    try{
        cl.setAcelerador(ac);
        return true;
    }catch(NullPointerException e){System.out.println("Erro ao alocar acelerador de PTS"+e); return false;}

   }
   //#endregion
   private static void exibirVoos(){
    voosSistema.values().stream().map(e ->e .toString()).forEach(System.out::print);}
   //#region Menus
    private static int menuPrincipal() {
        limparTela();
        System.out.println();
        System.out.println();
        System.out.println("FLY CORE");
        System.out.println("==========================");
        System.out.println("1 - Passagens");
        System.out.println("2 - Cliente");
        System.out.println("3 - Administrativo");
        System.out.println("0 - Sair");
        System.out.print("Digite sua opção: ");
        try {
            int opcao = teclado.nextInt();
            teclado.nextLine();
            return opcao;
        } catch (InputMismatchException e) {
            pausa();
            return -1;
        }
    }

    private static int menuPassagens(){
        limparTela();
        System.out.println();
        System.out.println("FLY CORE");
        System.out.println("==========================");
        System.out.println("1 - Cadastrar Trechos");
        System.out.println("2 -  Cadastrar Voos");
        System.out.println("3 - Cadastrar Datas");
        System.out.println("4 - Ver Voos Cadastrados");
        System.out.println("0 - Cancelar");
        System.out.print("Digite sua opção: ");
        try {
            int opcao = teclado.nextInt();
            teclado.nextLine();
            return opcao;
        } catch (InputMismatchException e) {
            return -1;
        }
    }

    private static int menuClientes(){
        limparTela();
        System.out.println();
        System.out.println("FLY CORE");
        System.out.println("==========================");
        System.out.println("1 - Cadastrar Cliente");
        System.out.println("2 - Multiplicador PTS");
        System.out.println("0 - Cancelar");
        System.out.print("Digite sua opção: ");
        try {
            int opcao = teclado.nextInt();
            teclado.nextLine();
            return opcao;
        } catch (InputMismatchException e) {
            return -1;
        }     
    }

    private static int menuMultiplicador(){
        limparTela();
        System.out.println();
        System.out.println("FLY CORE");
        System.out.println("==========================");
        System.out.println("1 - Add Multiplicador");
        System.out.println("2 - Ativar/ Desativar Multiplicador" );
        System.out.println("3 - Status");
        System.out.println("0 - sair");
        System.out.print("Digite sua opção: ");
        try {
            int opcao = teclado.nextInt();
            teclado.nextLine();
            return opcao;
        } catch (InputMismatchException e) {
            return -1;
        }
    }

    private static int menuADM(){
        System.out.println();
        System.out.println("FLY CORE");
        System.out.println("==========================");
        System.out.println("1 - Gerar Relatorio (Cliente)");//incluir consulta de bilhete grátis gerado
        System.out.println("2 - Cliente Maior Pts (Ultimo Ano)");
        System.out.println("3 - Voos");
        System.out.println("Vendas");
        System.out.println("0 - Cancelar");
        System.out.print("Digite sua opção: ");
        try {
            int opcao = teclado.nextInt();
            teclado.nextLine();
            return opcao;
        } catch (InputMismatchException e) {
            return -1;
        }
    }
    
    private static int menuCadastroVoos(){
        pausa();
        limparTela();
        limparTela();
        System.out.println();
        System.out.println("FLY CORE");
        System.out.println("==========================");
        System.out.println("1 - Escolher Trecho");
        System.out.println("2 - Escolher Data");
        System.out.println("3 - Cadastrar preço");
        System.out.println("4 - Cadastrar Voo ");
        System.out.println("0 - Cancelar");
        System.out.print("Digite sua opção: ");
        try {
            int opcao = teclado.nextInt();
            teclado.nextLine();
            return opcao;
        } catch (InputMismatchException e) {
            return -1;
        }
    }
    //#endregion

    //#region Execução Menus
    private static void executarMenuMultiplicador(Cliente clienteBusca){    
        int optMenuMulti = 0;
        do{
        optMenuMulti = menuMultiplicador();
        switch(optMenuMulti){
            case 1:
            boolean multiSetado= false, clAtualizado = false;
            try{
                if(!clienteBusca.getNome().isEmpty()){
                     IMultiplicavel multiplicadorEscolhido =  gerarMultiplicador();
                            multiSetado =  alocarMultiplicadorCliente(clienteBusca, multiplicadorEscolhido);
                            if(multiSetado)
                            {
                                System.out.println("\nMultiplicador Setado!");
                                clAtualizado =  atualizarClienteMapa(clienteBusca);
                            }
                            if(clAtualizado){System.out.println("\nCliente Atualizado!");}
                            }
            }catch(NullPointerException e){ System.out.println("\nCliente não encontrado");}
            pausa();
            break;
            case 2:
                    try{
                       System.out.print("Multiplicador "+((clienteBusca.ativarMulti())? "Ligado": "Desligado"));  
                    }
                    catch(NullPointerException e){System.out.print("\nNão possui Multiplicador Cadastrado!");}
             pausa();  
            case 3:

            case 0: break;
            default:
            System.out.println("Insira uma opção valida");
            pausa();
        }
    }while(optMenuMulti!=0);
    }

    private static void executarMenuPassagens(){
        int optMenuPassagens = 0;
        do{
        optMenuPassagens = menuPassagens();
        switch(optMenuPassagens){
            case 1:
                    Trecho novoTrecho= formarTrecho();
                    boolean trechoAdd = false;
                    trechoAdd = adicionarTrechoAlista(novoTrecho);
                    if(trechoAdd)
                        System.out.println("\n Trecho Add com Sucesso");
                    else{
                        System.out.println("\n Trecho já cadastrado !");
                    }
                    pausa();    
            break;
            case 2:
                executarMenuCadastroVoos();
            break;
            
            case 4:
                    limparTela();
                    exibirVoos();
                    pausa();
            break;
            case 0:
                break;
           default:
           System.out.println("Insira uma Opção valida");    
                    pausa();
        }
    }while(optMenuPassagens!=0);
    }
    
    private static void executarMenuCliente(){
        int optMenuClientes = 0;
        do{
        optMenuClientes= menuClientes();
        switch(optMenuClientes){
            case 1:
                Cliente nvCl = cadastrarCliente();
                boolean clienteSalvo = false;
                if(!nvCl.getCpf().equals("00000000000") && !nvCl.getNome().isEmpty() && !nvCl.getNome().isBlank()){
                    try{
                        clienteSalvo = addClienteAoMapa(nvCl);
                        if(!clienteSalvo){System.out.println("\nCliente Já Cadastrado");}
                        else{System.out.println("\nCliente Cadastrado com Sucesso!");}
                       ;
                    }

                    catch (NullPointerException e){System.out.println("\nErro ao inerir Cliente (null)");}
                    catch(NumberFormatException e){System.out.println("\nErro ao formatar HashCode cliente: "+e);}
                    pausa();
                }
                else{System.out.println("Dados invalidos/Cliente não cadastrado");}   

            break;
            case 2:
            String cpfBusca = "";
            cpfBusca = receberCPFbusca();
            if(!validarCpf(cpfBusca)){
                System.out.println("Insira um CPF válido");
            }
            else{
               Cliente clienteBusca = buscarCliente(cpfBusca);
               if(clienteBusca != null){
                     executarMenuMultiplicador(clienteBusca);
               }
               else{System.out.println("Cliente Invalido!");}     
             }
             pausa();
        break;
            case 0:
                return;
           default:
           System.out.println("Insira uma Opção valida");
           pausa();  
        }
    }while(optMenuClientes!=0);
    }
   
    private static void executarMenuCadastroVoos(){
        int optMenuCadastroVoo = 0;
        
        do{
            optMenuCadastroVoo = menuCadastroVoos();
            switch(optMenuCadastroVoo){
                case 0:      
                break;
                case 1:
                    Trecho nv = escolherTrechoVoo();
                        trechoVooCadastro = nv;
                break;
                case 2:
                      
                break;
                case 3:
                        double preco = cadastrarPreco();
                        if(preco == -1){
                            System.out.println("Entrada invalida para o preço!");
                        }
                        else{precoVooCadastro = preco;}
                break;

                case 4:                 
                        try{
                            Voo novoVoo = formarVoo(trechoVooCadastro, dataVooCadastro, precoVooCadastro);
                            System.out.println("\n INFO VOO: \n");
                            if(adicionarVooAlista(novoVoo)){
                                System.out.println("\nVoo Cadastrado!");
                                System.out.println(buscarVoo(novoVoo).toString());
                            }else{System.out.println("\nVoo Número: "+novoVoo.getIdVoo()+" Já cadastrado!");}
                        }catch(NullPointerException nulo){
                            System.out.println("\n Voo não cadastrado \n Trecho Invalido!");
                        }        
                break;
                default:
                    System.out.println("Opção Invalida!");
                    pausa();
            }
        }while(optMenuCadastroVoo!=0);
    }
   
    private static void executarMenuPrincipal(){
        int optMenuPrincipal = 0;
        do{
            optMenuPrincipal = menuPrincipal();//primeira entrada do usuario
            switch(optMenuPrincipal){
                case 0:
                    optMenuPrincipal= 0;
                break;
                case 1:
                limparTela();
                    executarMenuPassagens();
                continue;
                case 2:
                    limparTela();
                    executarMenuCliente();
                continue;
                case 3:
                break;
                case -1:
                    System.out.println("\n Entre com uma Opção Válida!");
                break;
            }
        }
        while(optMenuPrincipal!=0);
    }
    //#endregion
    public static void main(String[] args) {
        executarMenuPrincipal();
        teclado.close();
        sc.close();
    }
}