/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *              Programa��o Em C - M�dulo B PIC16F877A         *
 *                             Exemplo 6                       *
 *                                                             *
 *             CENTRO DE TREINAMENTO - Cerne Tecnologia        *
 *                                                             *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *   VERS�O : 1.0
 *   DATA : 22/07/2005
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                         Descri��o geral                     *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 Este exemplo ir� demonstrar a recep��o serial
  do PIC. Todos os dados recebidos da porta serial
 ser�o apresentados no display*/

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *             Defini��o e inicializa��o das vari�veis         *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
Aqui iremos definir as vari�veis globais do sistema*/

char linha,coluna;
char recepcao;

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                       In�cio do programa                    *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
Nessa parte ficar� o loop principal do sistema*/

void main(void)
{
   trisa=0b11111111;            // p�e todos os pinos para entrada
   trisd=0b00000000;
   trise=0b00000001;            //configura i/os
   adcon1=7;                    //desliga todos os a/ds
   option_reg=0b10000000;       //configura prescaler do timer 0
   Lcd8_Config(&PORTE, &PORTD, 2, 1, 3, 7, 6, 5, 4, 3, 2, 1, 0);
   Lcd8_Cmd(LCD_CLEAR);          //limpa o display
   Lcd8_Cmd(LCD_CURSOR_OFF);     //Desliga o cursor
   Usart_Init(9600);             //Inicializa o m�dulo Usart para trabalhar a 9600bps
   linha=1;
   coluna=1;

   while(1)
   {
       if (usart_data_ready())
       {
           recepcao=Usart_Read();
           Usart_Write(recepcao);
           Lcd8_Chr(linha,coluna,recepcao);
           coluna=coluna+1;
           if (coluna==17)
            {
             coluna=1;
             if (linha==1) linha=2;
             else          linha=1;
            }
       }
   }
}
/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                       Fim do programa                       *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

