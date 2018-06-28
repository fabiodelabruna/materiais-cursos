/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                 Programa��o em C - M�dulo B PIC16F877A                  *
 *                                Exemplo 1                                *
 *                                                                         *
 *                            CERNE TECNOLOGIA                             *
 * 		                   www.cerne-tec.com.br                           *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *   VERS�O : 1.0                                                          *
 *   DATA : 16/05/2005                                                     *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                             Descri��o geral                           *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	           // Observar o estado de um bot�o atrav�s de um led

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                     	    Defini��o de Cabe�alho     	                *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

#include	<16F877A.h>					//microcontrolador utilizado

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                        Configura��es para grava��o                    *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

#fuses xt,nolvp,wdt,nolvp,nobrownout,put,nodebug,noprotect,nowrt,nocpd
                            								// Configura��es da M�quina
                                  						// Oscilador: XT
                                  						// LVP: Off
                                  						// WDT: On, Brown-out: Off
                                   						// Power-up: Off, Debug: Off
                                    					// CPD: Off

#use delay(clock=4000000)       	   	// Define o clock do sistema

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                Entradas                               *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
            //Aqui iremos definir as entradas do sistema

#define BOTAO		PIN_A4		   	 // Define entrada para o bot�o


/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                 Sa�das                              *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
          //Aqui iremos definir as sa�das do sistema

#define LED			PIN_D7		       //Define a sa�da para o led
#define COLUNA1	PIN_D0	          //Define a sa�da da coluna
#define CS_LED		PIN_B5	          //Define a sa�da da coluna

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                       Prototipagem das fun��es                  *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
//Aqui iremos declarar todas as fun��es definidas pelo usu�rio

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                           Rotina Principal                	    *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
//Nessa parte ficar� o loop principal do sistema

void main(void)
{
   
	output_high(CS_LED);		   		//Seleciona o barramento para os leds
	output_low(COLUNA1);		     		//Deixa a coluna1 ativa
   output_d(0);                     //Coloca todo o PORTD como sa�da

	while(1)				         		//Entra em loop infinito
		{
			if (input(BOTAO))    		//Bot�o est� pressionado?
			 output_low(LED);	   		//N�o, ent�o apaga o led
			else			   	       	//Sim...
			 output_high(LED);	   	//Ent�o acende o led
		}
}

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                         Fim do programa	       	                *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */


