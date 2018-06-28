/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *             Programa��o Em C - M�dulo B PIC16F877A          *
 *                             Exemplo 4                       *
 *                                                             *
 *             CENTRO DE TREINAMENTO - Cerne Tecnologia        *
 *                                                             *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *   VERS�O : 1.0
 *   DATA : 22/07/2005
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                          Descri��o geral                    *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 Implementa��o da leitura do canal de ad*/


/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *             Defini��o e inicializa��o das vari�veis         *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
Aqui iremos definir as vari�veis globais do sistema*/

int leitura_ad;
char string_ad[5];

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                       In�cio do programa                    *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 Nessa parte ficar� o loop principal do sistema*/

void main(void)
{
   adcon1=0b10000100;
   adcon0=0b01000001;            // Configura o ad
   trisa=0b11111111;             // p�e todos os pinos para entrada
   trisd=0b00000000;
   trise=0b00000001;             //configura i/os
   option_reg=0b10000000;        //configura prescaler do timer 0
   Lcd8_Config(&PORTE, &PORTD, 2, 1, 3, 7, 6, 5, 4, 3, 2, 1, 0);
   Lcd8_Cmd(LCD_CLEAR);          //limpa o display
   Lcd8_Cmd(LCD_CURSOR_OFF);     //Desliga o cursor
   Lcd8_out(1, 3, "Voltimetro"); //Mostra mensagem no display


   while(1)
   {
       leitura_ad= ADC_read(0);
       wordtostr(leitura_ad,string_ad); //Converte para string
       strcat(string_ad,"uad");
       Lcd8_out(2,3,string_ad);         //Apresenta dado no lcd
   }
}

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                       Fim do programa                       *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
