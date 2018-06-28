/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
*     			   Programa��o em C - M�dulo B PIC16F877A              *
*                                Exemplo 6                             *
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
	RCSTA=0b10010000;			//Habilita a recep��o serial
								//Habilita a recep��o cont�nua de dados
	
	TXSTA=0b00100100;			//Habilita a transmiss�o serial
	
	SPBRG=25;					//Configura o baud-rate para 9600 bps
	
	TRISD=0b00000000;			//Configura o PORTD como sa�da
	
	TRISB=0b00000000;			//Configura o PORTB como sa�da
	
	PORTB=0b00100000;			//Liga o transistor dos leds
	
	while(1)
	{
		if (RCIF)				//Chegou algum byte?
		{
			PORTD=RCREG;		//Sim, ent�o carrega nos leds o dado recebido;
			TXREG=RCREG;		//Envia para o PC o dado recebido	
		}
		
	}
}


/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    		                Fim do programa			  	      		   *	
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
