package Sistema;

import Clientes.Cliente;
import Passagens.Bilhete;
import Passagens.BilheteFidelidade;
import Passagens.BilhetePromocional;
import Passagens.Trecho;
import Passagens.Voo;
import Utilitarios.*;
import Utilitarios.AceleradorPts.*;
import java.rmi.NotBoundException;
import java.text.BreakIterator;
import java.util.*;
import java.util.Formatter.BigDecimalLayoutForm;
import java.util.stream.Collectors;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.input.ElasticCharAppender;

public class Application {

  private static Scanner teclado = new Scanner(System.in); //Leitor para INTEGER
  private static Scanner sc = new Scanner(System.in); //leitor String
  private static Map<Integer, Voo> voosSistema = new HashMap<>();
  private static LinkedList<Trecho> trechosSistema = new LinkedList<>();
  private static Map<Integer, Cliente> clientesSistema = new HashMap<>();
  private static Trecho trechoVooCadastro = null;
  private static Data dataVooCadastro = new Data();
  private static double precoVooCadastro = 0d;
  private static int idVooCadastro = 0;

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
  /**
   * Método Responsavél por retornar cidade de origem escolhida por usuario.
   *
   * Utiliza-se do método de escolherCidades para receber cidade escolhida
   * por usuario.
   *
   * @return cidade origem -> String
   */
  private static String escolherCidadeOrigem() {
    String cidadeOrigem = "";
    do {
      cidadeOrigem = escolherCidadesTrecho("Origem");
    } while (cidadeOrigem == "");
    return cidadeOrigem;
  }

  /**
   * Método Responsavél por retornar cidade de destino escolhida por usuario.
   *
   * Utiliza-se do método de escolherCidades para receber cidade escolhida
   * por usuario.
   *
   * @return cidade destino -> String
   */
  private static String escolherCidadeDestino() {
    String cidadeDestino = "";
    do {
      cidadeDestino = escolherCidadesTrecho("Destino");
    } while (cidadeDestino == "");
    return cidadeDestino;
  }

  /**
   * Verificar se trecho já está cadastrado na lista e se é nulo
   * para poder add.
   * @param novoTrecho --> Novo trecho cadastrado
   * @return boolean --> true:se add a lista; false: caso não consiga adicionar
   */
  private static boolean adicionarTrechoAlista(Trecho novoTrecho) {
    if (
      !trechosSistema.contains(novoTrecho) && novoTrecho != null
    ) return trechosSistema.add(novoTrecho);
    return false;
  }

  /**
     *Método utiliza cidades previamente cadastradas no sistema para escolha do usuario,
     *Essas cidades são disponibilizadas através de uma lista de Strings que a classe cidadesTrecho
     fornece 
     * @param escolha String que identifica qual tipo de cidade será escolhida (Origem ou Destino)
     * @return String -> cidade escolhida pelo usaurio.
     */
  private static String escolherCidadesTrecho(String escolha) {
    limparTela();
    System.out.println();
    System.out.println();

    System.out.println("FLY CORE");
    System.out.println("==========================");

    int posicaoCidade = 0;
    for (String cidade : escolha == "origem"
      ? CidadesTrecho.getCidadesOrigem()
      : CidadesTrecho.getCidadesDestino()) {
      System.out.println((posicaoCidade + 1) + " - " + cidade);
      posicaoCidade++;
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
    } catch (IndexOutOfBoundsException x) {
      pausa();
      System.out.println("\nEscolha uma opção valida: ");
      return "";
    }
  }

  /**
   * Método responsavél por criar o objeto trecho no sistema,
   * ele utiliza os métodos de escolha de cidade para formar o Trecho e retornar.
   * @return Trecho ou Null -> Trecho gerado e instanciado com a cidades escolhidas;
   * Null(caso a cidade de origem seja igual a de destino)
   */
  private static Trecho formarTrecho() {
    String cidadeOrigem = "";
    cidadeOrigem = escolherCidadeOrigem();
    String cidadeDestino = "";
    cidadeDestino = escolherCidadeDestino();
    if (!cidadeDestino.equals(cidadeOrigem)) return new Trecho(
      cidadeOrigem,
      cidadeDestino
    );
    return null;
  }

  /**
   * Verificar se o trecho escolhido para o cadastro do Voo já está cadastrado no Sistema.
   * @return Trecho ou Null -> Trecho(Cadastrado); Null(Não Cadastrado)
   */
  private static Trecho escolherTrechoVoo() {
    limparTela();
    System.out.println();
    System.out.println();
    System.out.println("FLY CORE");
    System.out.println("==========================");
    Trecho trechoEscolhido = formarTrecho();
    if (!trechosSistema.contains(trechoEscolhido)) {
      System.out.println("\nTrecho não cadastrado");
      trechoEscolhido = null;
    } else {
      System.out.println("\nOK Trecho escolhido!");
    }
    return trechoEscolhido;
  }

  //endregion

  //#region Bilhetes

  private static Trecho escolherTrehcoBilhete() {
    Trecho novoTrecho = formarTrecho();
    return novoTrecho;
  }

  private static Trecho[] formarEscalasVoo(
    String cidadeOrigem,
    String cidadeDestino
  ) {
    Trecho destinoCoringa = formarEscalaCoringaDestino(cidadeDestino);
    Trecho origemCoringa = formarEscalaCoringaOrigem(cidadeOrigem);
    Trecho[] escalas = new Trecho[2];
    escalas[0] = origemCoringa;
    escalas[1] = destinoCoringa;
    return escalas;
  }

  /**
   * Menu para escolha do tipo de bilhete desejado
   * @return Opção do usuário (int)
   */
  public static int menuTipoBilhete() {
    System.out.println();
    System.out.println();
    System.out.println("FLY CORE");
    System.out.println("==========================");
    System.out.println("1 - Bilhete comum");
    System.out.println("2 - Bilhete promocional");
    System.out.println("3 - Bilhete fidelidade");
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

  public static int menuCompraBilhete() {
    System.out.println();
    System.out.println();
    System.out.println("FLY CORE");
    System.out.println("==========================");
    System.out.println("1 - Escolher Tipo Bilhete");
    System.out.println("2 - Inserir Voo");
    System.out.println("3 - Remover Voo");
    System.out.println("4 - Finalizar Compra");
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

  public static Bilhete gerarBilhete() {
    int tipoBilhete = 0;
    tipoBilhete = menuTipoBilhete();
    switch (tipoBilhete) {
      case 1:
        return new Bilhete();
      case 2:
        return new BilhetePromocional();
      case 3:
        return new BilheteFidelidade();
      default:
        System.out.println("\nTipo invalido!");
        return null;
    }
  }

  public static void exibirVoosDisponiveisParaUmTrecho(Trecho trechoProcurado) {
    List<Voo> voosTrecho = new LinkedList<>();
    voosTrecho = buscarVoosPorTrecho(trechoProcurado);
    if (!voosTrecho.isEmpty()) {
      voosTrecho.stream().map(e -> e.toString()).forEach(System.out::println);
    }
  }

  public static void exibirVoosEscala(Trecho tr1, Trecho tr2) {
    List<Voo> voosTrechoUm = new LinkedList<>();
    List<Voo> voosTrechoDois = new LinkedList<>();
    voosTrechoUm = buscarVoosPorTrecho(tr1);
    voosTrechoDois = buscarVoosPorTrecho(tr2);
    if (!voosTrechoUm.isEmpty() && !voosTrechoDois.isEmpty()) {
      voosTrechoUm.stream().map(e -> e.toString()).forEach(System.out::println);
      voosTrechoDois
        .stream()
        .map(e -> e.toString())
        .forEach(System.out::println);
    }
  }

  //endregion

  //#region Voo

  private static List<Voo> buscarVoosPorTrecho(Trecho trechoProcurado) {
    return voosSistema
      .values()
      .stream()
      .filter(e -> e.getTrecho().equals(trechoProcurado))
      .toList();
  }

  private static Trecho formarEscalaCoringaDestino(String cidadeDestino) {
    Trecho escalaCoringaDestino = new Trecho("São Paulo", cidadeDestino);
    if (trechosSistema.contains(escalaCoringaDestino)) {
      return escalaCoringaDestino;
    }
    return null;
  }

  private static Trecho formarEscalaCoringaOrigem(String cidadeOrigem) {
    Trecho escalaCoringaDestino = new Trecho(cidadeOrigem, "São Paulo");
    if (trechosSistema.contains(escalaCoringaDestino)) {
      return escalaCoringaDestino;
    }
    return null;
  }

  /**
   * Recebe id de cadastro do voo pelo usuario
   * @return int -> id digitado ; -1 (expetion gerada)
   */
  private static int receberIDVoocadastro() {
    int id = 0;
    limparTela();
    System.out.println(
      "Entre com o ID do Voo: (Número inteiro de até :)" + Integer.MAX_VALUE
    );
    try {
      id = sc.nextInt();
    } catch (InputMismatchException e) {
      sc.nextLine();
      System.out.println("\nInsira uma opção valida !");
      id = -1;
    }
    return id;
  }

  /**
   * Método responsavél por realizar a busca de um Voo.
   * @param vooBusca Objeto Voo criado com os dados do objeto de busca.
   * @return Voo -> Voo encontrado ; null (não encontrado)
   */
  private static Voo buscarVoo(Voo vooBusca) {
    return voosSistema.get(vooBusca.hashCode());
  }

  /**
   * Adicionar voo ao HashMap do Sistema;
   * Verifica se o voo já está no mapa e se seu id é maior que 0.
   * @param novoVoo novo Voo cadastrado
   * @return boolean --> true (add) ; false(não add)
   */
  private static boolean adicionarVooAlista(Voo novoVoo) {
    if (
      !voosSistema.containsValue(novoVoo) &&
      novoVoo.getTrecho().getIdTrecho() != 0
    ) {
      voosSistema.put(novoVoo.hashCode(), novoVoo);
      return true;
    }
    return false;
  }

  /**
   * Método responsavél por receber preco de cadastro de um Voo.
   * @return double -> preco digitado; -1.00 (Catch Expetion)
   */
  private static double cadastrarPreco() {
    Scanner sc = new Scanner(System.in);

    double preco = 0d;
    limparTela();
    System.out.println("Entre com o Preço do Voo: ");
    try {
      preco = sc.nextDouble();
    } catch (InputMismatchException e) {
      System.out.println("insira opção valida!");
      preco = -1d;
    }
    return preco;
  }

  private static Data formatarData() {
    System.out.println("=======FlyCORE======");
    System.out.println("Insira uma Data para cadastro : \n(XX/XX/XXXX)");
    String dataDigitada = sc.nextLine();
    String[] dataFormatada = dataDigitada.split("/");
    int dia = 0, mes = 0, ano = 0;
    try {
      dia = Integer.parseInt(dataFormatada[0]);
      mes = Integer.parseInt(dataFormatada[1]);
      ano = Integer.parseInt(dataFormatada[2]);
      return new Data(dia, mes, ano);
    } catch (NumberFormatException erroFormatacao) {
      System.out.println("Erro de Formatação --> Data = data atual");
      return new Data();
    } catch (IndexOutOfBoundsException e) {
      System.out.println("ERRO FORMATAÇÃO");
      return new Data();
    }
  }

  /**
   * Método responsavél por receber atributos para criação de um Voo
   * e retornar o objeto formado.
   * @param novoTrecho Trecho Cadastrado
   * @param dataVoo  Data Cadastrada
   * @param preco    preco Cadastrado
   * @param id       id Cadastrado
   * @return Voo --> Voo formado
   */
  private static Voo formarVoo(
    Trecho novoTrecho,
    Data dataVoo,
    double preco,
    int id
  ) {
    return new Voo(novoTrecho, dataVoo, preco, id);
  }

  //endregion

  //# region Multiplicador
  private static int escolherMultiplicador() {
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

  private static IMultiplicavel gerarMultiplicador() {
    //implementar
    int optMulti = escolherMultiplicador();
    switch (optMulti) {
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

  private static boolean validarNome(String nome) {
    if (nome.matches("[0-9]+") || nome.isEmpty()) return false;
    return true;
  }

  private static boolean validarCpf(String cpf) {
    cpf = cpf.toLowerCase();
    cpf = cpf.strip();
    if (cpf.matches("[a-zA-Z]+") || cpf.length() != 11) return false;
    return true;
  }

  //#region Clientes
  private static String receberCPFbusca() {
    String cpf = "";
    System.out.println("\nInsira o CPF do Cliente:  ");
    cpf = sc.nextLine();
    if (!validarCpf(cpf)) {
      return "";
    }
    return cpf;
  }

  private static Cliente buscarCliente(String cpfCliente) {
    Cliente clienteBusca = new Cliente("", cpfCliente);
    try {
      Cliente clienteEncontrado = clientesSistema.get(clienteBusca.hashCode());
      return clienteEncontrado;
    } catch (NullPointerException nulo) {
      System.out.println("Erro cliente nulo: " + nulo);
      return null; // retorna null caso não seja encontrado
    } catch (NumberFormatException e) {
      return null;
    }
  }

  private static boolean addClienteAoMapa(Cliente novoCliente) {
    try {
      if (!clientesSistema.containsKey(novoCliente.hashCode())) {
        clientesSistema.put(novoCliente.hashCode(), novoCliente);
        return true;
      }
      return false;
    } catch (NullPointerException e) {
      throw e;
    } catch (NumberFormatException e) {
      throw e;
    }
  }

  private static boolean atualizarClienteMapa(Cliente novoCliente) {
    try {
      if (clientesSistema.containsKey(novoCliente.hashCode())) {
        clientesSistema.replace(novoCliente.hashCode(), novoCliente);
        return true;
      } else {
        return false;
      }
    } catch (NullPointerException e) {
      System.out.println("Erro:" + e);
      return false;
    }
  }

  private static String receberDadosClienteCadastro() {
    String nome = "", cpf = "";
    System.out.println("\nEntre com o Nome do Cliente: ");
    String dadosCliente = "";
    try {
      nome = sc.nextLine();
      if (!validarNome(nome)) {
        System.out.println("Nome Invalido! ");
        pausa();
        limparTela();
        return dadosCliente;
      }

      System.out.println("\nInsira o CPF do cliente? ");
      cpf = sc.nextLine();

      if (!validarCpf(cpf)) {
        System.out.println("\n CPF INVALIDO!");
        pausa();
        limparTela();
      }
      dadosCliente = nome + ";" + cpf;
      return dadosCliente;
    } catch (InputMismatchException e) {
      System.out.println(e);
      throw e;
    }
  }

  private static Cliente formarCliente() {
    String dadosCliente = "";
    dadosCliente = receberDadosClienteCadastro();
    String[] separandoDados = dadosCliente.split(";");
    try {
      Cliente novoCliente = new Cliente(separandoDados[0], separandoDados[1]);
      return novoCliente;
    } catch (NullPointerException e) {
      System.out.println("Erro ao encontrar classe Clientes");
      throw e;
    } catch (IndexOutOfBoundsException e) {
      System.out.println("Erro ao colher dados do Cliente");
      return new Cliente("", "");
    }
  }

  private static boolean alocarMultiplicadorCliente(
    Cliente cl,
    IMultiplicavel ac
  ) {
    if (ac == null) {
      return false;
    }
    try {
      cl.setAcelerador(ac);
      return true;
    } catch (NullPointerException e) {
      System.out.println("Erro ao alocar acelerador de PTS" + e);
      return false;
    }
  }

  //#endregion

  private static void exibirVoos() {
    voosSistema
      .values()
      .stream()
      .map(e -> e.toString())
      .forEach(System.out::print);
  }

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

  private static int menuPassagens() {
    limparTela();
    System.out.println();
    System.out.println("FLY CORE");
    System.out.println("==========================");
    System.out.println("1 - Cadastrar Trechos");
    System.out.println("2 -  Cadastrar Voos");
    System.out.println("3 - Ver Voos Cadastrados");
    System.out.println("4 - Comprar Bilhete");
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

  private static int menuClientes() {
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

  private static int menuMultiplicador() {
    limparTela();
    System.out.println();
    System.out.println("FLY CORE");
    System.out.println("==========================");
    System.out.println("1 - Add Multiplicador");
    System.out.println("2 - Ativar/ Desativar Multiplicador");
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

  private static int menuADM() {
    System.out.println();
    System.out.println("FLY CORE");
    System.out.println("==========================");
    System.out.println("1 - Gerar Relatorio (Cliente)");
    System.out.println(
      "2 - Gerar Relatorio de bilhetes (comprados ate 1 ano atras)"
    );
    System.out.println("3 - Consultar Cliente Maior Pts (Ultimo Ano)");
    System.out.println(
      "4 - Consultar Voo em uma data e cidade especifica com mais de 100 reservas"
    );
    System.out.println("5 - Consultar Total de Vendas");
    System.out.println("0 - Cancelar");
    System.out.println("Digite sua opção: ");
    try {
      int opcao = teclado.nextInt();
      teclado.nextLine();
      return opcao;
    } catch (InputMismatchException e) {
      return -1;
    }
  }

  private static int menuCadastroVoos() {
    pausa();
    limparTela();
    limparTela();
    System.out.println();
    System.out.println("FLY CORE");
    System.out.println("==========================");
    System.out.println("1 - Escolher Trecho");
    System.out.println("2 - Escolher Data");
    System.out.println("3 - Cadastrar preço");
    System.out.println("4 - Cadastrar ID Voo ");
    System.out.println("5 - Cadastrar Voo");
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

  private static void executarMenuCompra(Cliente cl) {
    int optMenuCompra = 0;
    Bilhete bilheteCompra = null;
    Trecho[] trechosVooBilhete = new Trecho[3];
    Voo[] voosBilhete = new Voo[3];
    int nVoos = 0;
    do {
      optMenuCompra = menuCompraBilhete();
      switch (optMenuCompra) {
        case 1:
          bilheteCompra = gerarBilhete();
          break;
        case 2:
          int idVooEscolhido = 0;
          trechosVooBilhete[0] = formarTrecho();
          if (
            trechosSistema.contains(trechosVooBilhete[0]) &&
            !buscarVoosPorTrecho(trechosVooBilhete[0]).isEmpty()
          ) {
            exibirVoosDisponiveisParaUmTrecho(trechosVooBilhete[0]);
            pausa();
          } else {
            System.out.println(
              "\n Nenhum Voo encontrado... Procurando Escalas"
            );
            try {
              Trecho[] escalas = formarEscalasVoo(
                trechosVooBilhete[0].getCidadeOrigem(),
                trechosVooBilhete[0].getCidadeDestino()
              );
              exibirVoosEscala(escalas[0], escalas[1]);
              pausa();
              if (
                buscarVoosPorTrecho(escalas[0]).isEmpty() ||
                buscarVoosPorTrecho(escalas[1]).isEmpty()
              ) {
                System.out.println("Nenhuma Esacala encontrada!");
                break;
              }
            } catch (NullPointerException e) {
              System.out.println("Trecho invalido");
              break;
            }
          }
          System.out.println("Ente com o id do Voo que deseja add: ");
          idVooEscolhido = receberIDVoocadastro();
          if (idVooEscolhido != -1) {
            Voo vooEscolhido = voosSistema.get(Objects.hash(idVooEscolhido));
            if (vooEscolhido != null) {
              try {
                voosBilhete[nVoos] = vooEscolhido;
                nVoos++;
                System.out.println("\nVoo add a lista");
              } catch (IndexOutOfBoundsException e) {
                System.out.println("\nMÁXIMO DE 3 VOOS POR BILHETE!");
              }
            } else {
              System.out.println("Voo invaldio!");
            }
          }
          break;
        case 3:
          limparTela();
          System.out.println("\nVoos selecionados : ");
          if (voosBilhete[0] == null) {
            System.out.println("Nenhum Voo cadastrado!");
            break;
          }
          for (Voo vooSelecionado : voosBilhete) {
            if (vooSelecionado != null) System.out.println(
              vooSelecionado.toString()
            );
          }
          pausa();
          System.out.println("Entre com o ID do voo que deseja excluir : ");
          int idVooExcluir = receberIDVoocadastro();
          int cont = 0;
          for (Voo vooExcluir : voosBilhete) {
            try {
              if (vooExcluir.getIdVoo() == idVooExcluir) {
                voosBilhete[cont] = null;
                System.out.println("\nVoo " + idVooExcluir + " Excluido!");
              }
              cont++;
            } catch (NullPointerException e) {}
          }
          pausa();
          break;
        case 4:
          try {
            for (Voo vooBilhete : voosBilhete) {
              if (
                vooBilhete != null && bilheteCompra.buscarVoo(vooBilhete) == -1
              ) {
                bilheteCompra.inserirVoo(vooBilhete);
              }
            }
            System.out.println("\n" + bilheteCompra.toString());
          } catch (NullPointerException bilheteNull) {
            System.out.println("\nTipo de Bilhete Invalido!");
            break;
          }
          try {
            System.out.println("1 - Confirmar\n2 - Cancelar");
            int opt = sc.nextInt();
            switch (opt) {
              case 1:
                if (cl.comprarBilhete(bilheteCompra)) {
                  System.out.println("Bilhete comprado Com sucesso");
                  cl.getPontuacao();
                  if (atualizarClienteMapa(cl)) {
                    System.out.println("Cliente Atualizado! ");
                  } else {
                    System.out.println("Problemas ao atualizar Cliente");
                  }
                } else {
                  System.out.println("\n Falha ao comprar Bilhete");
                }
                break;
              case 2:
                System.out.println("\nOk cancelando...");
                break;
              default:
                System.out.println("\nOpção invalida!");
            }
          } catch (InputMismatchException e) {
            System.out.println("\nOpcção Invalida");
          }

          break;
      }
    } while (optMenuCompra != 0);
  }

  private static void executarMenuMultiplicador(Cliente clienteBusca) {
    int optMenuMulti = 0;
    do {
      optMenuMulti = menuMultiplicador();
      switch (optMenuMulti) {
        case 1:
          boolean multiSetado = false, clAtualizado = false;
          try {
            IMultiplicavel multiplicadorEscolhido = gerarMultiplicador();
            multiSetado =
              alocarMultiplicadorCliente(clienteBusca, multiplicadorEscolhido);

            if (multiSetado) {
              System.out.println("\nMultiplicador Setado!");
              clAtualizado = atualizarClienteMapa(clienteBusca);
            }
            if (clAtualizado) {
              System.out.println("\nCliente Atualizado!");
            }
          } catch (NullPointerException e) {
            System.out.println("\nCliente não encontrado");
          }
          pausa();
          break;
        case 2:
          try {
            System.out.print(
              "Multiplicador " +
              ((clienteBusca.ativarMulti()) ? "Ligado" : "Desligado")
            );
          } catch (NullPointerException e) {
            System.out.print("\nNão possui Multiplicador Cadastrado!");
          }
          pausa();
          break;
        case 0:
          break;
        default:
          System.out.println("Insira uma opção valida");
          pausa();
      }
    } while (optMenuMulti != 0);
  }

  private static void executarMenuPassagens() {
    int optMenuPassagens = 0;
    do {
      optMenuPassagens = menuPassagens();
      switch (optMenuPassagens) {
        case 1:
          Trecho novoTrecho = formarTrecho();
          boolean trechoAdd = false;
          trechoAdd = adicionarTrechoAlista(novoTrecho);
          if (trechoAdd) System.out.println("\n Trecho Add com Sucesso"); else {
            System.out.println("\n Trecho invalido ou já cadastrado!");
          }
          pausa();
          break;
        case 2:
          executarMenuCadastroVoos();
          break;
        case 3:
          limparTela();
          exibirVoos();
          pausa();
          break;
        case 4:
          limparTela();
          String cpf = receberCPFbusca();
          Cliente cl = buscarCliente(cpf);
          if (cl != null) {
            executarMenuCompra(cl);
          } else {
            System.out.println("! Cliente invalido !");
          }
          pausa();
          break;
        case 0:
          break;
        default:
          System.out.println("Insira uma Opção valida");
          pausa();
      }
    } while (optMenuPassagens != 0);
  }

  private static void executarMenuCliente() {
    int optMenuClientes = 0;
    do {
      optMenuClientes = menuClientes();
      switch (optMenuClientes) {
        case 1:
          Cliente nvCl = formarCliente();
          boolean clienteSalvo = false;
          if (
            !nvCl.getCpf().equals("00000000000") &&
            !nvCl.getNome().isEmpty() &&
            !nvCl.getNome().isBlank()
          ) {
            try {
              clienteSalvo = addClienteAoMapa(nvCl);
              if (!clienteSalvo) {
                System.out.println("\nCliente Já Cadastrado");
              } else {
                System.out.println("\nCliente Cadastrado com Sucesso!");
              }
            } catch (NullPointerException e) {
              System.out.println("\nErro ao inerir Cliente (null)");
            } catch (NumberFormatException e) {
              System.out.println("\nErro ao formatar HashCode cliente: " + e);
            }
            pausa();
          } else {
            System.out.println("Dados invalidos/Cliente não cadastrado");
          }
          break;
        case 2:
          String cpfBusca = "";
          cpfBusca = receberCPFbusca();
          if (!validarCpf(cpfBusca)) {
            System.out.println("Insira um CPF válido");
          } else {
            Cliente clienteBusca = buscarCliente(cpfBusca);
            if (clienteBusca != null) executarMenuMultiplicador(
              clienteBusca
            ); else {
              System.out.println("\n Cliente não Cadastrado.");
            }
          }
          pausa();
          break;
        case 0:
          return;
        default:
          System.out.println("Insira uma Opção valida");
          pausa();
      }
    } while (optMenuClientes != 0);
  }

  private static void executarMenuCadastroVoos() {
    int optMenuCadastroVoo = 0;
    int idVoo = 0;
    do {
      optMenuCadastroVoo = menuCadastroVoos();
      switch (optMenuCadastroVoo) {
        case 1:
          Trecho nv = escolherTrechoVoo();
          trechoVooCadastro = nv;
          break;
        case 2:
          dataVooCadastro = formatarData();
          break;
        case 3:
          double preco = cadastrarPreco();
          if (preco == -1) {
            System.out.println("Entrada invalida para o preço!");
          } else {
            precoVooCadastro = preco;
          }
          break;
        case 4:
          idVoo = receberIDVoocadastro();
          if (idVoo > 0) {
            idVooCadastro = idVoo;
            System.out.println("ID ESCOLHIDO: " + idVoo);
          } else {
            System.out.println(
              "\nO id deve ser um número inteiro maior que zero! "
            );
            idVoo = 0;
          }
          break;
        case 5:
          try {
            Voo novoVoo = formarVoo(
              trechoVooCadastro,
              dataVooCadastro,
              precoVooCadastro,
              idVooCadastro
            );
            System.out.println("\n INFO VOO: \n");
            if (idVooCadastro != 0) {
              if (precoVooCadastro > 0d) {
                if (adicionarVooAlista(novoVoo)) {
                  System.out.println("\nVoo Cadastrado!");
                  System.out.println(buscarVoo(novoVoo).toString());
                } else {
                  System.out.println(
                    "\nVoo Número: " + novoVoo.getIdVoo() + " Já cadastrado!"
                  );
                }
              } else {
                System.out.println(
                  "\n Não é possivel cadastrar um Voo com o preço igual a zero"
                );
              }
            } else {
              System.out.println("Digite Um ID valido");
            }
          } catch (NullPointerException nulo) {
            System.out.println("\n Voo não cadastrado \n Trecho Invalido!");
          }
          break;
        case 0:
          break;
        default:
          System.out.println("Opção Invalida!");
          pausa();
      }
    } while (optMenuCadastroVoo != 0);

    trechoVooCadastro = null;
    precoVooCadastro = 0d;
    idVooCadastro = 0;
  }

  private static void executarMenuPrincipal() {
    int optMenuPrincipal = 0;
    do {
      optMenuPrincipal = menuPrincipal(); //primeira entrada do usuario
      switch (optMenuPrincipal) {
        case 0:
          optMenuPrincipal = 0;
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
          executarMenuAdm();
          continue;
        case -1:
          System.out.println("\nEntre com uma opção válida!");
          break;
      }
    } while (optMenuPrincipal != 0);
  }

  private static void executarMenuAdm() {
    int optMenuAdm = 0;
    do {
      optMenuAdm = menuADM(); //primeira entrada do usuario
      switch (optMenuAdm) {
        case 0:
          optMenuAdm = 0;
          break;
        case 1:
          limparTela();
          System.out.println("Insira o cpf do cliente para gerar o relatorio");
          String cpf = sc.nextLine();
          gerarRelatorioCliente(cpf);
          continue;
        case 2:
          limparTela();
          System.out.println("Insira o cpf do cliente para gerar o relatorio");
          String cpf1 = sc.nextLine();
          gerarRelatorioBilhetesAnual(cpf1);
          continue;
        case 3:
          limparTela();
          clienteMaisPontos();

          continue;
        case 4:
          limparTela();
          String cidade = escolherCidadeDestino();
          System.out.println("Escolha uma data");
          Data data = new Data(); //criar metodo para pegar data

          voosMaisDe100reservas(data, cidade);

          continue;
        case 5:
          limparTela();
          continue;
        case -1:
          System.out.println("\nEntre com uma opção válida!");
          break;
      }
    } while (optMenuAdm != 0);
  }

  //#endregion

  //#region Relatorios ADM
  private static void voosMaisDe100reservas(Data data, String cidade) {}

  private static void clienteMaisPontos() {
    try {
      Cliente clienteMaior = clientesSistema
        .values()
        .stream()
        .collect(
          Collectors.maxBy(
            Comparator.comparingInt(Cliente::calcularPontuacaoAnual)
          )
        )
        .orElse(null);
      clienteMaior.gerarRelatorio();
    } catch (NullPointerException n) {
      System.out.println("Nenhum cliente registrado");
    }
  }

  private static void gerarRelatorioBilhetesAnual(String cpf) {
    Cliente clienteProcurado = buscarCliente(cpf);

    Data data = new Data();
    data.tirar1Ano();

    int total = 0;

    try {
      total = clienteProcurado.calcularNumeroBilhetesPromocionais();
      clienteProcurado
        .getBilhetesCliente()
        .stream()
        .filter(b -> b.getDataCompra().maisRecenteQue(data) == -1)
        .map(b -> b.toString())
        .forEach(System.out::println);
      System.out.println(
        "\n O cliente ganhou " + total + " bilhetes promocionais no ultimo ano."
      );
    } catch (NullPointerException n) {
      System.out.println(
        "Cpf nao cadastrado ou invalido, cadastre o cliente antes."
      );
    }
  }

  private static void gerarRelatorioCliente(String cpf) {
    try {
      System.out.println(buscarCliente(cpf).gerarRelatorio());
    } catch (NullPointerException e) {
      System.out.println(
        "Cpf nao cadastrado ou invalido, cadastre o cliente antes."
      );
    }
  }

  //#endregion

  public static void main(String[] args) {
    executarMenuPrincipal();
    teclado.close();
    sc.close();
  }
}