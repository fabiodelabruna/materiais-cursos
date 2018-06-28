/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
*     			   Programa��o em C - M�dulo B PIC16F877A              *
*                                Exemplo 4                             *
*                                                                      *
*                  CENTRO DE TREINAMENTO - Cerne Tecnologia	           *
*                  www.cerne-tec.com.br     (21) 4063-9798             *
*                                                                      *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * **
*   VERS�O : 1.0                                                       *
*   DATA : 16/05/2005                                                  *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * ** */

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *              	 	    Defini��o de Cabe�alho			           *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

#include	<pic.h>				//microcontrolador utilizado
			  		                                   
/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                           Rotina Principal                	       *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
//Nessa parte ficar� o loop principal do sistema

void main(void)
{
	TRISB=0b00000000;			//Configura todo o PORTB como sa�da
	
	TRISD=0b00000000;			//Configura todo o PORTD como sa�da
	
	PORTB=0b00100000;			//Habilita o transistor dos leds
	
	ADCON0=0b11000001;			//Liga o AD
								//Seleciona a frequencia interna
								//Escolhe o canal 0 para convers�o

	ADCON1=0b00001110;			//Justifica��o a esquerda
								//Coloca o RA0 como entrada anal�gica	
		
	while(1)
	{
		ADCON0=ADCON0 | 0b00000100;
								//Inicia uma convers�o AD
		while(ADCON0 & 0b00000100);
								//Aguarda finalizar a convers�o
											
		PORTD=ADRESH;			//Carrega nos leds o resultado da convers�o
		
	}
}

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    		                Fim do programa			  	      		   *	
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
