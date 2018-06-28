/*************************************************************************\
 * Nome: Rede.h                                                          *
 * Vers�o: 1.0                                                           *
 * Data: Setembro de 2003                                                *
 * Autor: Marcos Perez Mokarzel                                          *
 * Fun��o: Declara��es de fun��es e vari�veis da camada Rede.            *
 * Hist�rico:                                                            *
\*************************************************************************/

#include "CS8900A.h"

// Declara��o das fun��es Publicas da classe Rede.
void Rede_Init(void);     //Construtor da classe Rede, inicializa camada
                          // Rede do protocolo TCP/IP.
void Rede_Relogio(void);  //Interrup��o de rel�gio que ocorre a cada 20ms.
void Rede_Loop(void);     //Loop infinito para rodar rotinas peri�dicas.
void Rede_Envia(_IN_ WORD *pwBuf,   _IN_ WORD wTam,   //Envia buffer via
                _IN_ WORD *pwCont,  _IN_ WORD wTamCont, // Ethernet.
                _IN_ WORD *pwIPDest, _IN_ WORD wTipo, _IN_ BYTE bId);

//Defini��o de tipos da classe rede.
typedef struct
{
  WORD  *pwBuf;         //Aponta para o buffer que deve ser transmitido.
  WORD  wTam;           //N�mero de bytes que devem ser transmitido.
  WORD  *pwCont;        //Continua��o do Buffer.
  WORD  wTamCont;       //N�mero de bytes da continua��o.
  WORD  wTipo;          //Tipo de frame que ser� transmitido.
  WORD  pwIPDestino[2]; //IP de destino do pacote.
  BYTE  bFlag;          //Flags de transmiss�o.
  BYTE  bId;            //Identificador do buffer dentro da camada.
  BYTE  bTempoVida;     //Conta tempo para que este buffer seja liberado.
} t_Rede_TxControl;


//Declara��o das vari�veis publicas da camada Rede.
extern const WORD pwMeuMAC[];


