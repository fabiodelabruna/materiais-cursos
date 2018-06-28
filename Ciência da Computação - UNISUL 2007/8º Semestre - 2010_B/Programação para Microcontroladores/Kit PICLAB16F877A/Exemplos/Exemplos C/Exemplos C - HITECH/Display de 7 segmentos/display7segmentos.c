/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    			   Programa��o em C - M�dulo B PIC16F877A                  *
 *                               Exemplo 2                                 *
 *                                                                         *
 *                CENTRO DE TREINAMENTO - Cerne Tecnologia	               *
 *                www.cerne-tec.com.br     (21) 4063-9798                  *
 *                                                                         *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *   VERS�O : 1.0                                                          *
 *   DATA : 16/05/2005                                                     *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                		 	Defini��o de Cabe�alho						 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

#include	<pic.h>				//microcontrolador utilizado
				  		                                   
/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                           Rotina Principal                		   *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
//Nessa parte ficar� o loop principal do sistema

void main(void)
{
	TRISB=0b00000000;			//Configura todo o PORTB como sa�da
	
	TRISD=0b00000000;			//Configura todo o PORTD como sa�da
	
	PORTB=0b00010000;			//Habilita o transistor dos leds
	
	PORTD=0b01001111;			//Carrega o caracter 3 no display
}

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    		                  Fim do programa						   *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */



