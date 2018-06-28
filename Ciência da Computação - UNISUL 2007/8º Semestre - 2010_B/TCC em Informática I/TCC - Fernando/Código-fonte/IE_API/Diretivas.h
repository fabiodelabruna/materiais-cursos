/*************************************************************************\
 * Nome: Diretivas                                                       *
 * Vers�o: 1.0                                                           *
 * Data: Setembro de 2003                                                *
 * Autor: Marcos Perez Mokarzel                                          *
 * Fun��o: Cont�m as diretivas de compila��o do sistema, incluindo quais *
 *  os blocos que devem ser compilados e os par�metros do protocolo.     *
\*************************************************************************/

#if   !(_DIRETIVAS_H)
#define _DIRETIVAS_H

#define DIRETIVAS


///////////////////////////////////////////////////////////////////////////
//
// Declara��es das constantes gerais do sistema.
//
///////////////////////////////////////////////////////////////////////////

//Tipos de vari�veis.
#define   BYTE          unsigned char
#define   WORD          unsigned int
#define   DWORD         unsigned long
#define   BOOLEAN       unsigned char
#define   _IN_
#define   _OUT_

//Constantes de uso gen�rico.
#define   NULL          0x0000

#define   TRUE          0xFF
#define   FALSE         0x00

#define  BUFFER_LIVRE   0x0000


///////////////////////////////////////////////////////////////////////////
//
// Declara��es das constantes de cada uma das camadas.
//
///////////////////////////////////////////////////////////////////////////

/****************************\
 * CAMADA REDE              *
\****************************/

//Par�metros da camada Rede.
#define   MAC_1   0x00                  //Nosso endere�o ethernet (MAC)
#define   MAC_2   0x00                  // (deve ser �nico na rede LAN!)
#define   MAC_3   0x00
#define   MAC_4   0x83
#define   MAC_5   0x07
#define   MAC_6   0xA4

#define   MAC_BROADCAST       0xFFFF

#define   ETH_HEADER_SIZE     14
#define   ARP_FRAME_SIZE      28
#define   ARP_TOTAL_SIZE      ETH_HEADER_SIZE + ARP_FRAME_SIZE

#define   TAM_ARP_TABLE       0x03

#define   REDE_NUM_BUF        0x01 //Numero total de buffers dispon�veis na
                                   // camada Rede para transmitir dados.

//Defini��es da camada de Rede Ethernet
#define  FRAME_ARP            0x0806    //Tipo de frama (Armazenado no 
                                        // campo Tipo/Tamanho)
#define  FRAME_IP             0x0800

//Defini��es para pacote ARP.
#define  HARDW_ETH10          1         // hardware-type 10Mbps Ethernet
#define  IP_HLEN_PLEN         0x0604    // MAC = 6 byte, IP = 4 byte
#define  OP_ARP_REQUEST       1         // operations for ARP-frames
#define  OP_ARP_ANSWER        2

//Defini��o das constantes de controle de transmiss�o (wTxControl)
#define   TX_CTRL_FALTA_MAC   0x01      //Falta MAC de detino.
#define   TX_CTRL_ENVIAR_ARP  0x02      //Deve ser enviado a mensagem ARP.
                                        
#define   REDE_TEMPO_VIDA_MAX 100 //Tempo m�ximo de espera para transmiss�o
                                  // de um pacote na camada rede.
#define   REDE_TEMPO_PHY_MAX  10  //Tempo m�ximo de espera para libera��o
                                  // do buffer de tx do PHY.
                                        
#define   BUF_MAX_PHY         1024 //Tam m�ximo do buf suportado pelo PHY.


/****************************\
 * CAMADA INTERNET          *
\****************************/

//Par�metros da camada Internet.
#define   IP_1    10                    //Meu endere�o IP.
#define   IP_2    0
#define   IP_3    2
#define   IP_4    3

#define   MASC_12  0xFF00               //Mascara da rede.
#define   MASC_34  0x0000

#define   GWIP_1  10                   //IP do gateway.
#define   GWIP_2  0
#define   GWIP_3  2
#define   GWIP_4  1

//Poss�veis protocolos da camada IP (campo Protocolo).
#define   IP_VER_IHL_QOS      0x4500
#define   IP_TTL              127

//Protocolos transportados pela camada IP.
#define   IP_PROTOCOLO_ICMP   0x01
#define   IP_PROTOCOLO_TCP    0x06
#define   IP_PROTOCOLO_UDP    0x11

//Flags da camada IP.
#define   IP_FLAG_RESERVED    0x8000
#define   IP_FLAG_DONT_FRAG   0x4000
#define   IP_FLAG_MORE_FRAG   0x2000
#define   IP_FLAG_OFFSET      0x1FFF

//Mensagens ICMP.
#define   ICMP_ECHO_REPLY     0x00
#define   ICMP_DEST_UNREACH   0x03
#define   ICMP_SOURCE_QUENCH  0x04
#define   ICMP_REDIRECT       0x05
#define   ICMP_ECHO           0x08
#define   ICMP_TIME_EXCEEDED  0x0B
#define   ICMP_PAR_PROBLEM    0x0C
#define   ICMP_TIMESTAMP      0x0D
#define   ICMP_TIMESTAMP_REP  0x0E
#define   ICMP_INF_REQUEST    0x0F
#define   ICMP_INF_REPLY      0x10

//Defini��o dos tamanhos.
#define   IP_HEADER_SIZE    20
#define   IP_TOTAL_SIZE     ETH_HEADER_SIZE + IP_HEADER_SIZE + \
                            TCP_HEADER_SIZE + TCP_DATA_SIZE + 1
#define   ICMP_HEADER_SIZE  8
#define   ICMP_DATA_SIZE    32       //Pacote m�ximo ICMP de 32 bytes.
#define   ICMP_TOTAL_SIZE   IP_HEADER_SIZE+ICMP_HEADER_SIZE+ICMP_DATA_SIZE

#define   IP_NUM_BUF        0x02     //Numero total de buffers dispon�veis
                                     // na camada IP para transmitir dados.

#define   TOTAL_BUF         IP_NUM_BUF+REDE_NUM_BUF //Total de buffers
                                                    // no sistema.


/****************************\
 * CAMADA TRANSPORTE        *
\****************************/

//Par�metros da camada Transporte.
#define TOTAL_SKT         3   //N�mero total de sockets dispon�veis.
#define TCP_HEADER_SIZE   20
#define TCP_DATA_SIZE     60 //Tamanho m�ximo de um pacote pequeno.

//Divis�o da variavel que armazena o estados do socket.
#define EST_BIT           0x0F  //Bits que armazenam o estado do socket.
#define SUBEST_BIT        0xF0  //Bits que armazenam o subestado do socket.

//Estados do socket.
#define EST_LISTEN        0   
#define EST_SYN_SENT      1
#define EST_SYN_RECEIVED  2
#define EST_ESTABLISHED   3
#define EST_FIN_WAIT_1    4
#define EST_FIN_WAIT_2    5
#define EST_CLOSE_WAIT    6
#define EST_CLOSING       7 
#define EST_LAST_ACK      8
#define EST_TIME_WAIT     9 
                             

//Subestado de EST_ESTABLISHED.
#define SUBEST_WATING     0x00  //Aguardando pacote do TCP remoto.
#define SUBEST_TX_1       0x10  //1� tentativa de transmitir um pacote.
#define SUBEST_TX_2       0x20  //2� tentativa de transmitir um pacote.
#define SUBEST_TX_3       0x30  //3� tentativa de transmitir um pacote.
#define SUBEST_TX_FIM     0x40  //Fim das tentativas de envio.

//Flags do pacote TCP.
#define TCP_FLG_FIN       0x0001
#define TCP_FLG_SYN       0x0002
#define TCP_FLG_RST       0x0004
#define TCP_FLG_PSH       0x0008
#define TCP_FLG_ACK       0x0010
#define TCP_FLG_URG       0x0020

//Tempos da camada transporte em m�ltiplos de 20mS.
#define TCP_TEMPO_CLOSE   20    //Tempo = 20 * 100mS = 2Seg.
#define TCP_TEMPO_FIN     40    //Tempo = 40 * 100mS = 4Seg.
#define TCP_TEMPO_OUVIR   100   //Tempo = 100 * 100mS = 10Seg.
#define TCP_TEMPO_RESP    40    //Tempo = 50 * 100mS = 5Seg.
#define TCP_TEMPO_RTX     30    //Tempo = 30 * 100mS = 3Seg.


///////////////////////////////////////////////////////////////////////////
//
// Declara��es das estruturas gerais do protocolo.
//
///////////////////////////////////////////////////////////////////////////

//Estruturas de cabe�alho.
typedef struct                  //Cabe�alho Ethernet.
{
  WORD  pwMACDestino[3];
  WORD  pwMACOrigem[3];
  WORD  wTipo;
} t_CabEth;

typedef struct                  //Cabe�alho ARP.
{
  WORD  wProtFis;
  WORD  wProtLog;
  WORD  wTamFisLog;
  WORD  wOpcode;
  WORD  pwMAC[3];
  WORD  pwIP[2];
  WORD  pwMACAlvo[3];
  WORD  pwIPAlvo[2];
} t_CabArp;

typedef struct                  //Cabe�alho IP.
{
  WORD  wVerIhlQos;
  WORD  wTamTot;
  WORD  wId;
  WORD  wFlagFrag;
  BYTE  bProtocolo;
  BYTE  bTtl;
  WORD  wChecksum;
  WORD  pwIPOrigem[2];
  WORD  pwIPDestino[2];
} t_CabIP;

typedef struct                  //Cabe�alho ICMP.
{
  BYTE  bCod;
  BYTE  bTipo;
  WORD  wChecksum;
  WORD  wId;
  WORD  wSeq;
} t_CabIcmp;

typedef struct                  //Cabe�alho TCP.
{
  WORD  wPortaOrigem;
  WORD  wPortaDestino;
  DWORD dwSeq;
  DWORD dwAck;
  WORD  wDOffFlag;
  WORD  wWin;
  WORD  wChecksum;
  WORD  wUrgent;
} t_CabTcp;


//Estrutura para constru��o de pacote IP.
typedef struct                  //Pacote ICMP.
{
  BYTE  bCod;
  BYTE  bTipo;
  WORD  wChecksum;
  WORD  wId;
  WORD  wSeq;
  WORD  pwData[ICMP_DATA_SIZE/2];
} t_Icmp;

typedef struct                  //Pacote TCP.
{
  WORD  wPortaOrigem;
  WORD  wPortaDestino;
  DWORD dwSeq;
  DWORD dwAck;
  WORD  wDOffFlag;
  WORD  wWin;
  WORD  wChecksum;
  WORD  wUrgent;
  BYTE  pbData[TCP_DATA_SIZE];
} t_Tcp;

typedef struct                  //Pacote ARP.
{
  t_CabEth CabEth;
  t_CabArp CabArp;
} t_Arp;

typedef struct                  //Pacote IP com dados.
{
  t_CabIP  CabIP;               //Cabe�alho da camada IP
  union
  {
    t_Icmp    Icmp;             //Cabe�alho + dados do protocolo ICMP.
    t_Tcp     Tcp;              //Cabe�alho + dados do protocolo TCP.
  } Trsp;                       //Cabe�alho + dados da camada transporte.
} t_IPData;

typedef struct                  //Pacote Ethernet com dados.
{
  t_CabEth  CabEth;             //Cabe�alho da camada Ethernet.
  union
  {
    t_CabArp  CabArp;           //Cabe�alho + dados do protocolo ARP.
    t_IPData  IPData;           //Cabe�alho + dados do protocolo IP.
  } Rede;                       //Cabe�alho + dados da camada rede.
} t_EthData;

typedef struct                  //Pacote IP (somente cabe�alhos).
{
  t_CabIP  CabIP;               //Cabe�alho da camada IP
  union
  {
    t_CabIcmp CabIcmp;          //Cabe�alho do protocolo ICMP.
    t_CabTcp  CabTcp;           //Cabe�alho do protocolo TCP.
  } Trsp;                       //Cabe�alho da camada transporte.
} t_IP;

typedef struct                  //Pacote Ethernet com dados.
{
  t_CabEth  CabEth;             //Cabe�alho da camada Ethernet.
  union
  {
    t_CabArp  CabArp;           //Cabe�alho do protocolo ARP.
    t_IP      IP;               //Cabe�alho do protocolo IP.
  } Rede;                       //Cabe�alho da camada rede.
} t_Eth;


///////////////////////////////////////////////////////////////////////////
//
// Fun��es obrigat�rias da camada Aplica��o.
//
///////////////////////////////////////////////////////////////////////////

void App_Trata(_IN_ WORD wPrtLocal, _IN_ BYTE bSkt, 
               _IN_ BYTE *pbBuf, _IN_ WORD wTam);
void App_TrataPing(_IN_ WORD *pwIPAlvo, _IN_ WORD wSeqAlvo, 
                   _IN_ BOOLEAN Enviado);
void App_BufEnviado(_IN_ BYTE bSkt);
void App_Relogio(void);


#endif
