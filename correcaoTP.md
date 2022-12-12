# POO - Agência de turismo

## Nota: 19,3

## Comentário geral:
**MUITO IMPORTANTE**

* Primeiro, o elogio: dá para perceber que vocês se dedicaram bastante e quiseram cumprir 100% dos requisitos e usar 100% dos conceitos aprendidos. Porém...
	
* Sério, pessoal, o sistema de vocês é completo e, em tese, bem modularizado. No entanto, tem uma estrutura de execução tão inchada, pouco documentada e modularizada, que praticamente anula qualquer esforço de modularização anterior. TUDO que eu tentei modificar ou testar no mais eu não consegui entender, ou tive que percorrer/procurar dezenas de linhas, literalmente, uma por uma, para tentar chegar onde queria. 

* Fiquem atentos a isso. Em POO/modularidade, sempre tem o __menos é mais__. Um uso mais racional dos recursos é importante, faz parte da evolução e inclusive economiza tempo e manutenção. Vejam muitos detalhes abaixo.

## Correção
descobrindo porque tantas linhas: vejam entre a linha 1438 e a 1478: tudo isso são operações em sequência. Eram para estar em um único try, evitando este monte de catches iguais e repetidos (olhar ao final do comentário)
	
descobrindo porque tantas linhas2: muitas vezes a modularização de métodos auxiliares economiza linhas, erros, espaço e manutenção. Veja no stream de total arrecadado que há código quase iguais para com data e sem data (cases 1 e 2, linha 1591 em diante). Se houvesse um método para encapsular a stream, bastariam ali 2 linhas de código (ou uma terceira para verificar a existência de filtro)
	
não faço a menor ideia de quais CPFs existem para eu poder consultar dados de clientes, para comprar bilhetes etc. Eu **espero muito** que vocês não tenham cadastrado tudo manualmente. Não encontrei arquivo para descobrir a fonte dos dados e o cliente do ApplicationTest dá 'cliente inválido' no main 
	
eu que tenho que criar manualmente o código do voo, número inteiro arbitrário, sem saber quais existem?
	
sério, difícil demais comprar um bilhete. 
		menu 1; menu 4; menu 2; não consigo saber quais trechos existem para tentar um voo; 
		se acerto no chute aparece um monte de voos; dá clear na hora de eu escolher o id logo após mostrar todos
	
cases enormes: da linha 1051 até a 1099 para criar um voo. Esta parte toda sem documentação. Imaginem eu tentando testar a compra e os pontos. 
	
o que um trecho como este abaixo faz na compra de bilhete? (sem documentação e sem modularidade) Imaginem para eu conseguir entender isso.
	
	for (Voo voos: voosBilhete) {
                    try {
                      int id = voos.getIdVoo();
                      voosSistema.get(Objects.hash(id)).incrementarPassageiro();
                    } catch (NullPointerException n) {

                    }
                  }
	}
	
dá um getPontuacao (linha 1155) a cada bilhete, percorrendo todos, a cada compra. Para quê?
	
linha 1645: usando equals de Objects? não seria de voo? (sim, seria). e para que o getData? não teria que ter um método no voo para isso (sim, teria)
	
### Modelagem: 1,8/2
	- ligação de cliente com a interface (já comentada...)
	- herdar de classe concreta nunca é a melhor opção... 

### Documentação e aderência ao modelo: 2/2
	- Arquivo de testes
	
### Requisitos Cliente: 8/10 
	- Bilhete com vários voos	3/3
	- Preço/pontuação/aceleração de bilhetes	4/4
	- Verificação de fidelidade	1/3 (em tese, ok. Gerei um cliente com 14375 pontos e não me deixou comprar fidelidade)
	
### Requisitos Empresa: 7,5/8
	- Dados de cliente	2/2
	- Cliente com mais pontos	2/2
	- Voo cidade/data/100 reservas	1,75/2 (sem conseguir testar pois não há documentação ou teste indicando quais parâmetros dariam verdadeiro)
	- Total arrecadado / filtro mês	1,75/2 (vejam a duplicação de código em 1594 e 1614. Modularizar: se o stream é o mesmo, pode estar encapsulado em um método que recebe a parte variável como parâmetro).
	
### Apresentação e aderência ao SOLID (peso entre 0 e 1)
	- peso 1
	
### Try/catch único já que todas as exceções são as mesmas
    try {
      gravarMultiPrata();
      gravarMultiPreto();
      gravarTrechos();
      gravarVoos();
      //(etc)
    } catch (FileNotFoundException e) {
      System.out.println("Arquivo não encontrado.");
      System.out.println("Não foi possível gravar os dados do multiplicador prata.");
      System.out.print("Nome do arquivo: ");
      arqMultiPrata = teclado.nextLine();
      pausa();
    } catch(IOException ex) {
      System.out.println("Problema no uso do arquivo.");
      System.out.println("Favor reiniciar o sistema.");
      pausa();
    }
