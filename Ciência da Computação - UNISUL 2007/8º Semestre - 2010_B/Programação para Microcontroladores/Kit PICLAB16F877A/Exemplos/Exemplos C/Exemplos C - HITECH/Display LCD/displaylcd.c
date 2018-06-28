/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
*     			   Programa��o em C - M�dulo B PIC16F877A              *
*                                Exemplo 7                             *
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
 *                          Defini��o de Sa�das	            	       *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
//Nessa parte ser�o definidos os pinos de sa�da do microcontrolador

#define BUS  PORTD					//Sa�da do barramento
									//1 -> PRESSIONADO
									//0 -> LIBERADO

#define	RS	 RE2					//Linha de RS
#define	EN	 RE1					//Linha de EN

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                       Prototipagem de Fun��es	           	       *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
//Nessa parte ficar� a parte referente a prototipagem das fun��es de usu�rio

void delay(void);	
void lcd_inst(unsigned char dado);
void lcd_dado(unsigned char dado);
void inicializa_lcd(void);
		  		                                   
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
	
	TRISE=0b00000000;			//Configura o PORTB como sa�da
	
	PORTB=0b00100000;			//Liga o transistor dos leds

	ADCON1=0b00000111;			//Desabilita os ADs
	
	inicializa_lcd();			//Rotina para inicializar o LCD
		
	while(1)
	{
		if (RCIF)				//Chegou algum byte?
		{
			lcd_inst(0x80);		//Posiciona cursor do display
			lcd_dado(RCREG);	//Envia o caracter para o display
		}
		
	}
}

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                 Rotina de Inicializa��o de LCD          			   *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

void inicializa_lcd(void)
{
	lcd_inst(0x30);			      // Rotina de Inicializa��o do Display
	delay();
    lcd_inst(0x30);		
    lcd_inst(0x30);		
    lcd_inst(0x38);
    lcd_inst(0x01);
    delay();
    lcd_inst(0x0C);
    lcd_inst(0x06);
}    
    
/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *   			              Rotina de Delay            			   *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

void delay(void)
{
	unsigned int tempo;			
	for(tempo=1;tempo<=1000;tempo++);	//Faz um loop para gerar um atraso no programa
}

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                  Rotina de Envio de Comando para o LCD              *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

void lcd_inst(unsigned char dado)
{
	RS=0;							// P�e pino de RS em baixo, modo comando
	BUS=dado;						// P�e o dado no portd
	EN=1;							// Gera pulso de clock
	NOP();
	NOP();
	EN=0;
	delay();						//Delay para processamento do display
}

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                  Rotina de Envio de Dados para o LCD                *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

void lcd_dado(unsigned char dado)
{
	RS=1;							// Seta o pino RS, modo dado
	BUS=dado;						// P�e o dado no portd
	EN=1;							// Gera pulso de clock
	NOP();
	NOP();
	EN=0;
	delay();						//Delay para processamento do display
}

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    			               Fim do Programa                 		   *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
