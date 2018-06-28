/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * **
*             Programa��o Em C - M�dulo B PIC16F877A          *
*                             Exemplo 7                       *
*                                                             *
*             CENTRO DE TREINAMENTO - Cerne Tecnologia        *
*                                                             *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
*   VERS�O : 1.0
*   DATA : 22/07/2005
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
*                         Descri��o geral                     *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 Este exemplo ir� demonstrar a utiliza��o da mem�ria
 EEPROM interna do PIC. Para isso, iremos implementar
 um contador no barramento de leds e assim que o sistema
 acordar, o mesmo ler� a mem�ria e carregar� o dado
 novamente no barramento*/

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *             Defini��o e inicializa��o das vari�veis         *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
Aqui iremos definir as vari�veis globais do sistema*/

char  dado;

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                       In�cio do programa                    *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
Nessa parte ficar� o loop principal do sistema*/

void main(void)
{
   portb=0;
   trisb=0b00000000;            //inicializa o barramento
   trisd=0b00000000;            //configura i/o do sistema
   adcon1=7;                    //desliga a/d
   option_reg=0b10000000;       //configura prescaler do timer 0
   dado=eeprom_read(0);         //l� a posi��o zero de mem�ria e carrega no barramento

     while (1)
     {

       portb.f5=1;                //liga a sa�da de leds
       portd=dado;               //atualiza a sa�da de leds
       delay_ms(500);             //aguarda 1000ms

       portb.f5=0;                //desliga sa�da de leds
       portd=0b11111110;          //seleciona linha 1 do teclado matricial
       delay_us(10);              //tempo de acomoda��o

       if (porta.f4==0)           //se o bot�o estiver pressioando...
       {
           dado++;                //incrementa o dado
           eeprom_write(0,dado);  //atualiza a eeprom
       }

       if (porta.f5==0)          //se o bot�o estiver pressioando...
       {
           dado--;                //decrementa o dado
           eeprom_write(0,dado);  //atualiza a eeprom
       }
     }
}

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                       Fim do programa                       *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

