/*************************************************************************\
 * Nome: Trsp.h                                                          *
 * Vers�o: 1.0                                                           *
 * Data: Setembro de 2003                                                *
 * Autor: Marcos Perez Mokarzel                                          *
 * Fun��o: Declara��es de fun��es e vari�veis da camada Transporte.      *
 * Hist�rico:                                                            *
\*************************************************************************/


//Fun��es publicas da classe transporte.
void Trsp_Init(void);
void Trsp_Loop(void);
void Trsp_Relogio(void);
void TCP_Trata(t_IPData *ptBuf);
BOOLEAN TCP_Conecta(_IN_ WORD wPrtLocal,   _IN_ WORD wPrtRemota, 
                    _IN_ WORD *pwIPRemoto, _OUT_ BYTE *pbSkt);
BYTE TCP_Estado(_IN_ BYTE bSkt);
BOOLEAN TCP_Envia(BYTE bSkt, WORD *pwBuf, WORD wTam);
void TCP_BufEnviado(_IN_ WORD wPrtOrigem, _IN_ WORD wPrtDestino, 
                    _IN_ WORD *pwIPDestino);
void TCP_Desconecta(BYTE bSkt);

//Estruturas de controle da classe Transporte.
typedef struct
{
  WORD  wPrtLocal;    //Porta de comunica��o da origem.
  WORD  wPrtRemota;   //Porta de comunica��o do destino.
  WORD  pwIPRemoto[2];//IP dono do socket.
  BYTE  bEstado;      //Estado do socket.
  BYTE  bTempo;       //Tempo de vida do socket.
  
  DWORD dwSeqTx;      //Contagem de bytes transmitidos.
  WORD  *pwBufTx;     //Aponta o buffer que deve ser transmitido.
  WORD  wTamBufTx;    //N�mero de bytes que deve ser transmitido.
  WORD  wPtrBufTx;    //Aponta para o inicio da pr�xima por��o a ser tx.
  WORD  wWin;         //Tamanho m�ximo da janela de comunica��o.
  
  DWORD dwSeqRx;      //Contagem de bytes recebidos.
} t_Skt;              //Controle de Socket.


//Portas abertas para comunica��o TCP/IP.
extern const WORD TCP_PrtAberta[];
extern t_Skt ptSkt[TOTAL_SKT];



