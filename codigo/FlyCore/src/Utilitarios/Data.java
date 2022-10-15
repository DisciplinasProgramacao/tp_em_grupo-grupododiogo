package classes;

/** 
 * MIT License
 *
 * Copyright(c) 2022 João Caram <caram@pucminas.br>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

 /** Classe data simples para fundamentos de POO */
public class Data{
    

    //#region atributos
    //constante: dias de cada mês
    private static final int[] DIASDOMES = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    private static final int ANO_ATUAL = 2022;
    private static final String[] DIASDASEMANA = {"sábado", "domingo", "segunda", "terça", "quarta", "quinta", "sexta" }; //2022 começa em um sábado

    //atributos 
    private int dia;
    private int mes;
    private int ano;
    //#endregion
    
    //#region Construtores   

    /**
     * Inicializador privado. É utilizado pelos construtores. Faz a validação e, em caso de data inválida, retorna 01/01/1900.
     * @param dia Dia para a data
     * @param mes Mês para a data
     * @param ano Ano para a data
     */
    private void init(int dia, int mes, int ano){
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        if (!this.dataValida()){     //se a data não é válida... (método da própria classe)
            this.dia = this.mes = 1;
            this.ano = 1900;
        }
    }
    
    /**
     * Construtor completo: recebe dia, mês e ano e valida a data. Datas inválidas vão para 01/01/1900
     * @param dia Dia para a data
     * @param mes Mês para a data
     * @param ano Ano para a data
     */
    public Data(int dia, int mes, int ano){
        this.init(dia, mes, ano);
    }
    
    /**
     * Construtor para ano atual: recebe dia, mês e completa com ano atual (2022).
     * Datas inválidas vão para 01/01/1900
     * @param dia Dia para a data
     * @param mes Mês para a data
     * 
     */
    public Data(int dia, int mes){
        this.init(dia, mes, ANO_ATUAL);
    }

    /**
     * Construtor para data padrão: 01/01/1990
     */
    public Data(){
        this.init(1,1,1990);
    }        
    //#endregion

    /**  
    * Verifica se o ano da data armazenada é bissexto.
    * Para regras do ano bissexto:
    * http://educacao.uol.com.br/disciplinas/matematica/ano-bissexto-eles-se-repetem-a-cada-4-anos.htm
    * http://www.sogeografia.com.br/Curiosidades/?pg=4
    * @return Se o ano é bissexto (true) ou não (false)
    */
    public boolean anoBissexto(){
        boolean resposta = false;
        if(this.ano%400==0) 
              resposta = true;
        else if(this.ano%4==0 && this.ano%100!=0)
              resposta = true;
        
        return resposta;
    }

    /**
    * Verifica se a data armazenada é válida (método privado). Só valem datas de 1900 em diante.
    * @return TRUE se é válida ; FALSE se não é válida
    */
    private Boolean dataValida(){
        Boolean resposta = true;        //resposta sobre a validade
        int maximoDeDias=0;

        if(this.ano<1900)
            resposta = false;
        else{
           if (this.mes < 1 || this.mes > 12)                           //mês<1 ou mês>12 --> data inválida
               resposta = false;
           else { 
                    maximoDeDias = DIASDOMES[mes];
                    if (this.anoBissexto()&&mes==2)                    //caso de 29/02 em ano bissexto --> data válida
                           maximoDeDias++;
                   if (this.dia > maximoDeDias)                //verifica validade de acordo com o mês atual
                           resposta = false;
                }
        }                       
        return resposta;    //retorna a validação obtida
    }

   
    /**
     * Método static: calcula do dia da semana de uma data em 2022 (ignora o ano caso seja diferente).
     * Datas inválidas retornam e são calculadas como 01/01/2022
     * @return O dia da semana da data parametrizada em 2022.
     */
    public static String diaDaSemanaEm2022(String data){
        
        int deslocamento = 0;
        int totalDias = 0;
        int mesParaSomar = 1;

        String[] detalhes = data.split("/");
        int dia = Integer.parseInt(detalhes[0]);    
        int mes = Integer.parseInt(detalhes[1]);
        
        Data aux = new Data(dia, mes);
        
        while(mesParaSomar!=aux.mes){           //somamos todos os dias dos meses anteriores ao atual
            totalDias += DIASDOMES[mesParaSomar];
            mesParaSomar++;
        }

        totalDias += (aux.dia-1);       //somamos os dias para chegar na data dentro do mês
        deslocamento = totalDias%7;     //o deslocamento em dias no vetor contante de dias da semana indica o dia da data.

        return DIASDASEMANA[deslocamento];
    }

   
    
    /**
    * Retorna a data formatada
    * @return String com a data no formato dd/mm/aaaa
    */
    public String dataFormatada(){
        
        return (String.format("%02d",this.dia)+ 
          "/" + String.format("%02d",this.mes)+ 
          "/" + String.format("%4d",this.ano));
    }
    


    
}

