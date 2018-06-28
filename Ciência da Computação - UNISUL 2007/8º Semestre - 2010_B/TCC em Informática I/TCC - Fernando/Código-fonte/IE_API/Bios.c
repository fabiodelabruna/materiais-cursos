/*************************************************************************\
 * Nome: Bios.c                                                          *
 * Vers�o: 1.0                                                           *
 * Data: Setembro de 2003                                                *
 * Autor: Marcos Perez Mokarzel                                          *
 * Fun��o: Executa os acessos a hardware do sistema, isto desvincula o   *
 *           software do MCU.                                            *
 * Hist�rico:                                                            *	
\*************************************************************************/

 //se � PIC18

#include <p18f452.h>
#include <portb.h>
void Bios_Relogio(void);
void ligaOK(void);
void ligaOK1(void); 
void delay(void);

#include "..\IE_API\Diretivas.h"
#include "..\IE_API\Bios.h"
#include "..\IE_API\Rede.h"
#include "..\IE_API\Trsp.h"



///////////////////////////////////////////////////////////////////////////
// Bios_Init
// Construtora da classe Rede, inicializa as portas de I/O e 
//  o sistema de osciladores que ser� empregado.
// Par�metros: Void
// Retorno: void
///////////////////////////////////////////////////////////////////////////
void Bios_Init(void)
{
  //
  // Inicializa as portas de I/O do sistema.
  //


  TRISA = 0x00;           //Chaveia todos os pinos da porta A para sa�da.
  PORTA = 0;           //Coloca todos em n�vel 0.

  //
  //Inicializa as portas usadas na constru��o do barramento ISA utilizado
  // para controlar o CS8900.
  //

PORTB = 0;
BUS_ADD_VAL = 0;
TRISB = 0;
BUS_ADD_VAL = IOR | IOW;

  TRISD = 0x00;           //Seta como sa�da.
  PORTD = 0x00;              //Zera o barramento de dados.
 


  
  /*INTCON = 0x20;          //Desabilita interrup��o global e 
                          // habilita interrup��o TMR0.
  INTCON2 = 0x84;         //Seta TMR0 para alta prioridade.

  RCONbits.IPEN = 1;      //Habilita interrup��o de alta prioridade.

  TMR0H = 0;
  TMR0L = 217;            //Conta 256 - 217 = 39 antes da interrup��0
                          // 25,6uS * 39 = 1mS.

  T0CON = 0xC6; //#11000110b
  //               |||||+++--> Seta divisor para 128. 200nS * 128 = 25,6uS.
  //               ||||+-----> Utiliza o divisor(prescale).
  //               |||+------> Incrementa na transi��o de descida.
  //               ||+-------> Fonte de clock interna FOSC / 4 = 5MHz.
  //               |+--------> Timer0 opera com 8 bits.
  //               +---------> Habilita Timer0,

  INTCONbits.GIEH = 1;    //Habilita todas as int. de alta prioridade.
*/

}

///////////////////////////////////////////////////////////////////////////
// Bios_Escreve
// Escreve uma WORD, BYTE a BYTE, em um endere�o espec�fico do PHY gerando 
//  o diagrama de tempo da figura 3.2.4 do livro.
// Par�metros:  bAdd  - Endere�o onde ser� escrito os dados.
//              wDado - Dados que devem ser escritos.
// Retorno: void
///////////////////////////////////////////////////////////////////////////
void Bios_Escreve(_IN_ BYTE bAdd, _IN_ WORD wData)
{

  BUS_DATA_DIR = 0x00;    //Passa barramento de dados para sa�da.
  BUS_ADD_VAL = IOR | IOW | bAdd; //Coloca o endere�o e 
                          // confirma posi��o alta dos bits IOR e IOW.
  BUS_DATA_OUT = wData >> 8; //Escreve o byte de ordem mais alta no 
                            // barramento de dados.
  BUS_ADD_VAL &= ~IOW;    //Abaixa IOW para gerar o pulso de escrita.
  
  BUS_ADD_VAL = IOR | IOW | (bAdd + 1); //Sobe IOW finalizando o pulso de
                          // escrita e j� coloca o novo endere�o.
  
  BUS_DATA_OUT = wData;   //Escreve o byte de ordem mais baixa no 
                       // barramento de dados.
  BUS_ADD_VAL &= ~IOW;    //Abaixa IOW para gerar o pulso de escrita.
  
  BUS_ADD_VAL |= IOW;     //Sobe IOW finalizando o pulso de escrita.
  
  
}


///////////////////////////////////////////////////////////////////////////
// Bios_EscreveSw
// Executa um SWAPB e escreve a WORD, BYTE a BYTE, em um endere�o
// espec�fico do PHY gerando o diagrama de tempo da figura 3.2.4 do livro.
// Par�metros:  bAdd  - Endere�o onde ser� escrito os dados.
//              wDado - Dados que devem ser escritos.
// Retorno: void
///////////////////////////////////////////////////////////////////////////
void Bios_EscreveSw(_IN_ BYTE bAdd, _IN_ WORD wData)
{
  
  BUS_DATA_DIR = 0x00;    //Passa barramento de dados para sa�da.
  BUS_ADD_VAL = IOR | IOW | bAdd;   //Coloca o endere�o e 
                        // confirma posi��o alta dos bits IOR e IOW.
  BUS_DATA_OUT = wData;
  
  BUS_ADD_VAL &= ~IOW;    //Abaixa IOW para gerar o pulso de escrita.
  
  BUS_ADD_VAL = IOR | IOW | (bAdd + 1); //Sobe IOW finalizando o pulso de
                        // escrita e j� coloca o novo endere�o.
  BUS_DATA_OUT = wData >> 8; //Escreve o byte de ordem mais alta no
                      // barramento de dados.
  BUS_ADD_VAL &= ~IOW;    //Abaixa IOW para gerar o pulso de escrita.
 
  BUS_ADD_VAL |= IOW;     //Sobe IOW finalizando o pulso de escrita.
  

}


///////////////////////////////////////////////////////////////////////////
// Bios_Le
// L� uma WORD de uma porta especif�ca do PHY gerando o diagrama de tempo 
//  da figura 3.2.3 do livro.
// Par�metros:  bAdd  - Endere�o de onde o dado ser� lido.
// Retorno: Word lido.
///////////////////////////////////////////////////////////////////////////
WORD Bios_Le(_IN_ BYTE bAdd)
{
  
  WORD wData;  
  BUS_DATA_DIR = 0xFF;  
                     //Passa barramento de dados para leitura.
bAdd |= IOW;

bAdd |= IOR;

BUS_ADD_VAL = bAdd;

  //BUS_ADD_VAL = IOR | IOW | bAdd; //Coloca endere�o e confirma posi��o 
                               // alta de IOR e IOW.
  BUS_ADD_VAL &= ~IOR;            //Abaixa IOR gerando o pulso de leitura.
  wData = BUS_DATA_IN;            //L� o byte de ordem mais baixa.
//
bAdd |= IOR | IOW;
BUS_ADD_VAL = bAdd;

BUS_ADD_VAL &= ~IOR;

wData = BUS_DATA_IN;
 
bAdd=bAdd+2;
BUS_ADD_VAL = bAdd;
  //BUS_ADD_VAL = IOR | IOW | (bAdd + 1); //Levanta IOR e coloca o 
                              // novo endere�o de leitura.
  BUS_ADD_VAL &= ~IOR;            //Abaixa IOR gerando o pulso de leitura.
  
  wData |= BUS_DATA_IN << 8;      //L� o byte de ordem mais alta.
  BUS_ADD_VAL |= IOR;             //Levanta IOR.
 
  return wData;                   //Retorna dado lido.

}


///////////////////////////////////////////////////////////////////////////
// Bios_LeFrame
// L� uma WORD da porta RX_FRAME_PORT do PHY com os bits mais signigica_
//  tivos antes dos menos significativos, isto evita a necessidade de fazer
//  byte-swap na leitura de dados TCP/IP. Esta fun��o gera o diagrama de
//  tempo da figura 3.2.3 do livro.
// Par�metros:  void.
// Retorno: Word lido.
///////////////////////////////////////////////////////////////////////////
WORD Bios_LeFrame(void)
{
  WORD wData;
  BUS_DATA_DIR = 0xFF;            //Passa barramento de dados para leitura.
  BUS_ADD_VAL = IOR | IOW | RX_FRAME_PORT; //Coloca endere�o RX_FRAME_PORT
                                           // e confirma IOR e IOW.
  BUS_ADD_VAL &= ~IOR;            //Abaixa IOR gerando o pulso de leitura.
  wData = BUS_DATA_IN << 8;       //L� o byte de ordem mais alta.
  BUS_ADD_VAL = IOR | IOW | (RX_FRAME_PORT + 1); //Levanta IOR e coloca o
                                                // novo end de leitura.
  BUS_ADD_VAL &= ~IOR;            //Abaixa IOR gerando o pulso de leitura.
 
  wData |= BUS_DATA_IN;           //L� o byte de ordem mais baixa.
  BUS_ADD_VAL |= IOR;             //Levanta IOR. 

  return wData;                   //Retorna dado lido. 

}


///////////////////////////////////////////////////////////////////////////
// Bios_LeHB1St
// L� uma WORD de uma porta espec�fica do PHY com os bits mais signigica_
//  tivos antes dos menos significativos, isto evita a necessidade de fazer
//  byte-swap na leitura de alguns registradores especiais. Esta fun��o
//  gera o diagrama de tempo da figura 3.2.3 do livro.
// Par�metros:  bAdd  - Endere�o de onde o dado ser� lido.
// Retorno: Word lido.
///////////////////////////////////////////////////////////////////////////
WORD Bios_LeHB1St(_IN_ BYTE bAdd)
{
  WORD wData;
  BUS_DATA_DIR = 0xFF;            //Passa barramento de dados para leitura.
  BUS_ADD_VAL = IOR | IOW | (bAdd + 1); //Coloca end e confirma IOR e IOW.
 
  BUS_ADD_VAL &= ~IOR;            //Abaixa IOR gerando o pulso de leitura.
   
  wData = BUS_DATA_IN << 8;       //L� o byte de ordem mais alta.
  BUS_ADD_VAL = IOR | IOW | bAdd; //Levanta IOR e poe novo end de leitura.
  
  BUS_ADD_VAL &= ~IOR;            //Abaixa IOR gerando o pulso de leitura.
  
  wData |= BUS_DATA_IN;           //L� o byte de ordem mais baixa.
  BUS_ADD_VAL |= IOR;             //Levanta IOR.

  return wData;                   //Retorna dado lido.

}


///////////////////////////////////////////////////////////////////////////
// Bios_Relogio
// Clock geral do sistema, acionado a cada 1mS atrav�s da int do timer.
// Par�metros: void
// Retorno: void
///////////////////////////////////////////////////////////////////////////

/*#pragma code HIGH_INTERRUPT_VECTOR = 0x08
void high_ISR(void)
{
  _asm
  goto Bios_Relogio
  _endasm
}
#pragma code
#pragma interrupt Bios_Relogio
void Bios_Relogio(void)
{
  static WORD wTmpSingSisOk = 500;  //Contador de tempo para piscar LED.

  if(INTCONbits.TMR0IF)
  {
    TMR0L = 217;          //Conta 256 - 217 = 39 antes da interrup��o.
    INTCONbits.TMR0IF = 0;
    App_Relogio();        //Roda a rotina de rel�gio da camada aplica��o.
    Trsp_Relogio();       //Roda a rotina de rel�gio da camada transporte.
    Rede_Relogio();       //Roda a rotina de rel�gio da camada rede.

    wTmpSingSisOk--;      //Soma um ao contador de tempo de sistema ok.
    if(!wTmpSingSisOk)    //Se decorreu 1Seg.
    {
      LATAbits.LATA0 = !LATAbits.LATA0; //Altera o estado do LED.
      wTmpSingSisOk = 500; //Reinicia a contagem de tempo.
    }
  }
}

*/

///////////////////////////////////////////////////////////////////////////
// Bios_GetTemp
// Amostra e retorna o valor do canal 10 do conversor AD que no MSP430
//  est� ligado a um internamente a um diodo sensor de temperatura.
//  Nota: para melhorar a precis�o, vamos tirar a m�dia de 8 amostras.
// Par�metros: void
// Retorno: valor da temperatura em �C.
///////////////////////////////////////////////////////////////////////////
WORD Bios_GetTemp(void)
{
  DWORD dwVal = 0xFF;
  return dwVal;
}
void ligaOK(void){
	LATAbits.LATA1=TRUE;
    
}
void ligaOK1(void){
	
    LATAbits.LATA0=!LATAbits.LATA0;
}
void delay(){
	int i=0;
   int h=0;
   for(i=0;i<10;i++){
     h+=1;

	}
}
