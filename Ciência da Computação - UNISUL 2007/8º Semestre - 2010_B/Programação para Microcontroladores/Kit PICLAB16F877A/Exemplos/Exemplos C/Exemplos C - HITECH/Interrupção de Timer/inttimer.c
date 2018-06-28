/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
*     			   Programa��o em C - M�dulo B PIC16F877A              *
*                                Exemplo 5                             *
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
 *              	 	    Defini��o de Sa�das				           *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
 
#define		LED		RD0			//Define o led do projeto
			  		                                   
/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                           Rotina Principal                	       *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
//Nessa parte ficar� o loop principal do sistema

void main(void)
{
	T1CON=0b00110001;			//Configura o timer 1
								//Prescaler 1:8
								//Timer 1 Ligado
	
	INTCON=0b11000000;			//Habilita a chave geral
								//Habilita a interrup��o de perif�ricos
								
	PIE1=0b00000001;			//Habilita a interrup��o de timer1
	
	TRISB=0;
	
	TRISD=0;					//Configura PORTB e PORTD como sa�das
	
	RB5=1;						//Liga o transistor do led
	
	PORTD=0;					//Deixa todo o PORTD desligado
	
	while(1);					//Entra em loop infinito

}

void interrupt isr(void)
{
	TMR1IF=0;					//Limpa o flag de interrup��o
	
	LED=!LED;					//Led recebe ele N�O ele, ou seja inverte o estado do led	

}

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    		                Fim do programa			  	      		   *	
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
