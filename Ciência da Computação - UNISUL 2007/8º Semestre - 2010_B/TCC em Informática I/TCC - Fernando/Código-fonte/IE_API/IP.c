/*************************************************************************\
 * Nome: IP.c                                                            *
 * Vers�o: 1.0                                                           *
 * Data: Setembro de 2003                                                *
 * Autor: Marcos Perez Mokarzel                                          *
 * Fun��o: Executa as fun��es da camada IP do protocolo TCP/IP.          *
 * Hist�rico:                                                            *	
\*************************************************************************/

#include "..\IE_API\Diretivas.h"
#include "..\IE_API\Bios.h"
#include "..\IE_API\Rede.h"
#include "..\IE_API\IP.h"
#include "..\IE_API\Trsp.h"


//Fun��es privadas da classe IP.
void IP_EnviaEchoReply(_IN_ t_IPData *ptBuf);
BOOLEAN IP_BufIcmpLivre(void);
WORD IP_CalcChecksum(_IN_ void *pvBuf,  _IN_ WORD wTam, 
                     _IN_ void *pvCont, _IN_ WORD wTamCont);
BOOLEAN ICMP_ConfereChecksum(_IN_ t_Icmp *ptBuf, _IN_ WORD wTam);
void IP_IPRede(_IN_ WORD *pwIPIn, _OUT_ WORD *pwIPOut);
BOOLEAN IP_BufLivre(_OUT_ BYTE *bNum);

//Vari�veis privadas da classe IP.
t_Eth   ptBufTx[1]; //Cria os buffers p/ transmiss�o de pacotes IP
WORD    pwIcmpData[ICMP_DATA_SIZE>>1];   //Armazena dados para pacote ICMP.


///////////////////////////////////////////////////////////////////////////
// IP_Init
// Construtora da classe IP.
// Par�metros: Void
// Retorno: void
///////////////////////////////////////////////////////////////////////////
void IP_Init(void)
{
  BYTE bIndex;
  
  for(bIndex = 0; bIndex < IP_NUM_BUF; bIndex++)
    ptBufTx[bIndex].Rede.IP.CabIP.wVerIhlQos = BUFFER_LIVRE;
    
  pwIcmpData[0] = BUFFER_LIVRE;

}


///////////////////////////////////////////////////////////////////////////
// IP_Trata
// Trata pacote que foi recebido pela camada Rede.
// Par�metros:  ptBuf - Buffer contendo o pacote IP.
// Retorno: void
///////////////////////////////////////////////////////////////////////////

void IP_Trata(_IN_ t_IPData *ptBuf)
{

  if(((*ptBuf).CabIP.pwIPDestino[0] == pwMeuIP[0]) &&     //Se o IP est�
     ((*ptBuf).CabIP.pwIPDestino[1] == pwMeuIP[1]) &&     //correto.
     !((*ptBuf).CabIP.wFlagFrag & IP_FLAG_MORE_FRAG) &&   //Se o pacote n�o
     (((*ptBuf).CabIP.wFlagFrag & IP_FLAG_OFFSET) == 0x0000))// est�
 {   
	                                                   // fragmentado.
    switch((*ptBuf).CabIP.bProtocolo)
    {
      case IP_PROTOCOLO_ICMP:           //Se � um pacote ICMP.
        if(ICMP_ConfereChecksum(&(*ptBuf).Trsp.Icmp,   //Checksum correto?
                                 (*ptBuf).CabIP.wTamTot - IP_HEADER_SIZE))  
        {
          switch((*ptBuf).Trsp.Icmp.bTipo)
          {
            case ICMP_ECHO:             //Se � "ping".
              IP_EnviaEchoReply(ptBuf);
              break;
              
            case ICMP_ECHO_REPLY:       //Se � resposta de um "ping".
              App_TrataPing((*ptBuf).CabIP.pwIPOrigem,
                            (*ptBuf).Trsp.Icmp.wSeq, FALSE);
              break;
          }
       }
       break;
      case IP_PROTOCOLO_TCP:            //Se � um pacote TCP.
        TCP_Trata(ptBuf);
        break;
    }
  }
	
}


///////////////////////////////////////////////////////////////////////////
// IP_EnviaEchoReply
// Constroi um pacote de Echo Reply e encaminha para a camada rede.
// Par�metros:  ptBuf - Buffer contendo o pacote recebido.
// Retorno: void
///////////////////////////////////////////////////////////////////////////
void IP_EnviaEchoReply(_IN_ t_IPData *ptBuf)
{
  BYTE  bNumBuf;
  WORD  wIndex;
  WORD  wTamBuf;
  WORD  pwIPDestRede[2];
  
  wTamBuf = (*ptBuf).CabIP.wTamTot - IP_HEADER_SIZE - ICMP_HEADER_SIZE;
  if(wTamBuf > ICMP_DATA_SIZE)
    wTamBuf = ICMP_DATA_SIZE;
  if(IP_BufLivre(&bNumBuf) &&       //Se existe buffer livre.
     IP_BufIcmpLivre())             // e o buffer de ICMP tb est� livre.
  {
    //Preenche o cabe�alho IP do buffer livre.
    ptBufTx[bNumBuf].Rede.IP.CabIP.wVerIhlQos = IP_VER_IHL_QOS;
    ptBufTx[bNumBuf].Rede.IP.CabIP.wTamTot =
                                   wTamBuf+IP_HEADER_SIZE+ICMP_HEADER_SIZE;
    ptBufTx[bNumBuf].Rede.IP.CabIP.wId = (*ptBuf).CabIP.wId;
    ptBufTx[bNumBuf].Rede.IP.CabIP.wFlagFrag = 0x0000;
    ptBufTx[bNumBuf].Rede.IP.CabIP.bTtl = IP_TTL;
    ptBufTx[bNumBuf].Rede.IP.CabIP.bProtocolo = IP_PROTOCOLO_ICMP;
    ptBufTx[bNumBuf].Rede.IP.CabIP.wChecksum = 0x0000;
    ptBufTx[bNumBuf].Rede.IP.CabIP.pwIPOrigem[0] = pwMeuIP[0];
    ptBufTx[bNumBuf].Rede.IP.CabIP.pwIPOrigem[1] = pwMeuIP[1];
    ptBufTx[bNumBuf].Rede.IP.CabIP.pwIPDestino[0] = 
                     (*ptBuf).CabIP.pwIPOrigem[0];
    ptBufTx[bNumBuf].Rede.IP.CabIP.pwIPDestino[1] = 
                     (*ptBuf).CabIP.pwIPOrigem[1];
    ptBufTx[bNumBuf].Rede.IP.CabIP.wChecksum =        //Calcula checksum do
              IP_CalcChecksum((void *)&ptBufTx[bNumBuf].Rede.IP, // cab IP.
              IP_HEADER_SIZE, NULL, 0); 
       
    //Preenche o cabe�alho ICMP do buffer livre.
    ptBufTx[bNumBuf].Rede.IP.Trsp.CabIcmp.bTipo = ICMP_ECHO_REPLY;
    ptBufTx[bNumBuf].Rede.IP.Trsp.CabIcmp.bCod = 0x00;
    ptBufTx[bNumBuf].Rede.IP.Trsp.CabIcmp.wChecksum = 0x0000;
    ptBufTx[bNumBuf].Rede.IP.Trsp.CabIcmp.wId = (*ptBuf).Trsp.Icmp.wId;
    ptBufTx[bNumBuf].Rede.IP.Trsp.CabIcmp.wSeq = (*ptBuf).Trsp.Icmp.wSeq;

    //Preenche o buffer ICMP.
    for(wIndex = 0; wIndex < (wTamBuf>>1); wIndex++) //Copia os dados ICMP.
      pwIcmpData[wIndex] = SWAPB((*ptBuf).Trsp.Icmp.pwData[wIndex]);

    if((*ptBuf).CabIP.wTamTot&0x0001)   //Se faltou o �ltimo byte. Copia.
      pwIcmpData[wIndex+1] = SWAPB((*ptBuf).Trsp.Icmp.pwData[wIndex+1]);

    ptBufTx[bNumBuf].Rede.IP.Trsp.CabIcmp.wChecksum = //Checksum do ICMP.
            IP_CalcChecksum((void *)&ptBufTx[bNumBuf].Rede.IP.Trsp.CabIcmp,
            ICMP_HEADER_SIZE, pwIcmpData, wTamBuf);

    //Verifica se IP pertence a rede ou precisa enviar atrav�s do gateway.    
    IP_IPRede((*ptBuf).CabIP.pwIPOrigem, pwIPDestRede);

    //Envia para a camada Rede.
    Rede_Envia((WORD*)&ptBufTx[bNumBuf],
               ETH_HEADER_SIZE + IP_HEADER_SIZE + ICMP_HEADER_SIZE, 
               pwIcmpData, wTamBuf, pwIPDestRede, FRAME_IP, bNumBuf);
  }
}


///////////////////////////////////////////////////////////////////////////
// IP_BufLivre
// Verifica se existe posi��o de buffer livre para armazenar pacote IP
//  para ser transmitido.
// Par�metros:  pbNum - Aponta para a vari�vel onde deve ser colocado o
//                      n�mero do buffer que est� livre.
// Retorno:     TRUE  - Se existe buffer livre.
//              FALSE - Se n�o existe buffer livre.
///////////////////////////////////////////////////////////////////////////
BOOLEAN IP_BufLivre(_OUT_ BYTE *pbNum)
{
  BOOLEAN Stt = FALSE;
  BYTE    bNum;
  
  for(bNum = 0 ; bNum < IP_NUM_BUF; bNum++) //Varre todos os buffers
  {                                         // procurando um livre.
    if(ptBufTx[bNum].Rede.IP.CabIP.wVerIhlQos == BUFFER_LIVRE)//Se o buffer
    {                                                        // est� livre.
      Stt = TRUE;                     //Assinala que achou um buffer livre.
      *pbNum = bNum;                  //Armazena o n�mero do buffer.
      break;                          //Encerra a busca.
    }
  }
  return Stt;
}


///////////////////////////////////////////////////////////////////////////
// IP_BufIcmpLivre
// Verifica se existe posi��o de buffer livre para armazenar pacote ICMP
//  para ser transmitido.
// Par�metros:  void
// Retorno:     TRUE  - Se existe buffer livre.
//              FALSE - Se n�o existe buffer livre.
///////////////////////////////////////////////////////////////////////////
BOOLEAN IP_BufIcmpLivre(void)
{
  BOOLEAN Stt = FALSE;
  
  if(pwIcmpData[0] == BUFFER_LIVRE)   //Se o buffer de ICMP est� livre.
    Stt = TRUE;                       //Assinala que achou o buffer livre.

  return Stt;
}


///////////////////////////////////////////////////////////////////////////
// IP_CalcChecksum
// Calcula o valor do checksum de um conjunto de bytes segundo a regra
//  descrita no cap�tulo V do livro.
// Par�metros:  pvBuf   - Ponteiro para o cojunto de WORDs.
//              wTam    - Tamanho do buffer em bytes.
//              pvCont  - Ponteiro para acontinua��o do cojunto de WORDs.
//              wTamCont- Tamanho da continua��o do buffer em bytes.
// Retorno:     Valor do checksum calculado.
///////////////////////////////////////////////////////////////////////////
WORD IP_CalcChecksum(_IN_ void *pvBuf,  _IN_ WORD wTam, 
                     _IN_ void *pvCont, _IN_ WORD wTamCont)
{
  WORD  *pwData;
  DWORD dwSum = 0;
  WORD  wIndex;

  pwData = pvBuf;
  for(wIndex = 0; wIndex < (wTam>>1); wIndex++)  //Soma Words do buffer.
  {
    dwSum += *pwData;
    pwData++;
  }
  
  pwData = pvCont;
  for(wIndex = 0; wIndex < (wTamCont>>1); wIndex++)//Soma Words do buffer.
  {
    dwSum += SWAPB(*pwData);
    pwData++;
  }
  
  if(wTamCont & 0x0001)                 //Se sobrou um byte.
    dwSum += SWAPB(*pwData);

  while(dwSum >> 16)             //Transforma a soma de 32 bits em 16 bits.
    dwSum = (dwSum & 0xFFFF) + (dwSum >> 16);
  
  return ~dwSum;
}


///////////////////////////////////////////////////////////////////////////
// ICMP_ConfereChecksum
// Verifica se o valor do Checksum do protocolo ICMP recebido est� correto.
// Par�metros:  ptBuf - Ponteiro para o buffer ICMP que deve ser conferido.
//              wTam  - N�mero de bytes do buffer ICMP.
// Retorno: TRUE se o valor est� correto.
//          FALSE se o valor est� incorreto.
///////////////////////////////////////////////////////////////////////////
BOOLEAN ICMP_ConfereChecksum(_IN_ t_Icmp *ptBuf, _IN_ WORD wTam)
{
  BOOLEAN Correto = FALSE;
  WORD wChecksumAux = (*ptBuf).wChecksum;  //Guarda checksum que chegou.
  
  (*ptBuf).wChecksum = 0x0000;       //Zera ele para calcular o novo valor.

  if(wChecksumAux == IP_CalcChecksum((void*)ptBuf, wTam, NULL, 0))  //Se o
  {                                               // checksum est� correto.
    Correto = TRUE;
  }
    
  (*ptBuf).wChecksum = wChecksumAux;    //Retorna o valor antigo.
  
  return Correto;
}


///////////////////////////////////////////////////////////////////////////
// IP_BufEnviado
// Libera o buffer que acaba de ser enviado.
// Par�metros:  bId     - Identificador do buffer dentro da camada.
// Retorno: void
///////////////////////////////////////////////////////////////////////////
void IP_BufEnviado(_IN_ BYTE bId)
{
  switch(ptBufTx[bId].Rede.IP.CabIP.bProtocolo)
  {
    case IP_PROTOCOLO_ICMP:             //Se estava transmitindo ICMP.
      if(ptBufTx[bId].Rede.IP.Trsp.CabIcmp.bTipo == ICMP_ECHO)//Se foi 
      {                                                 // enviado um Ping.
        App_TrataPing(ptBufTx[bId].Rede.IP.CabIP.pwIPOrigem, 
                      ptBufTx[bId].Rede.IP.Trsp.CabIcmp.wSeq, TRUE);
      }
      pwIcmpData[0] = BUFFER_LIVRE;     //Libera o buffer ICMP.
      break;
    case IP_PROTOCOLO_TCP:              //Se estava transmitindo TCP.
      TCP_BufEnviado(ptBufTx[bId].Rede.IP.Trsp.CabTcp.wPortaOrigem, 
                     ptBufTx[bId].Rede.IP.Trsp.CabTcp.wPortaDestino,  
                     ptBufTx[bId].Rede.IP.CabIP.pwIPDestino);
      break;
  }

  ptBufTx[bId].Rede.IP.CabIP.wVerIhlQos = BUFFER_LIVRE;
}


///////////////////////////////////////////////////////////////////////////
// IP_Ping
// Envia comando ECHO atravez do protocolo ICMP/IP para um IP espec�fico.
// Par�metros:  pwIPAlvo  - IP que deve receber o comando ping.
//              wNumSeq   - Numero sequencial que deve ser enviado.
// Retorno: void
///////////////////////////////////////////////////////////////////////////
void IP_Ping(_IN_ WORD *pwIPAlvo, _IN_ WORD wNumSeq)
{
  BYTE bNumBuf;
  WORD wIndex;
  BYTE bAux;
  WORD pwIPDestRede[2];

  if(IP_BufLivre(&bNumBuf) &&       //Se existe buffer livre.
     IP_BufIcmpLivre())             // e o buffre de ICMP tb est� livre.
  {
    //Preenche o cabe�alho IP do buffer livre.
    ptBufTx[bNumBuf].Rede.IP.CabIP.wVerIhlQos = IP_VER_IHL_QOS;
    ptBufTx[bNumBuf].Rede.IP.CabIP.wTamTot = IP_HEADER_SIZE + 
                                       ICMP_HEADER_SIZE + ICMP_DATA_SIZE;
    ptBufTx[bNumBuf].Rede.IP.CabIP.wId = 0x0000;
    ptBufTx[bNumBuf].Rede.IP.CabIP.wFlagFrag = 0x0000;
    ptBufTx[bNumBuf].Rede.IP.CabIP.bTtl = IP_TTL;
    ptBufTx[bNumBuf].Rede.IP.CabIP.bProtocolo = IP_PROTOCOLO_ICMP;
    ptBufTx[bNumBuf].Rede.IP.CabIP.wChecksum = 0x0000;
    ptBufTx[bNumBuf].Rede.IP.CabIP.pwIPOrigem[0] = pwMeuIP[0];
    ptBufTx[bNumBuf].Rede.IP.CabIP.pwIPOrigem[1] = pwMeuIP[1];
    ptBufTx[bNumBuf].Rede.IP.CabIP.pwIPDestino[0] = pwIPAlvo[0];
    ptBufTx[bNumBuf].Rede.IP.CabIP.pwIPDestino[1] = pwIPAlvo[1];
    ptBufTx[bNumBuf].Rede.IP.CabIP.wChecksum =      //Calcula checksum do
          IP_CalcChecksum((void *)&ptBufTx[bNumBuf].Rede.IP,  // cab IP.
          IP_HEADER_SIZE, NULL, 0);
          
    //Preenche o cabe�alho ICMP do buffer livre.
    ptBufTx[bNumBuf].Rede.IP.Trsp.CabIcmp.bTipo = ICMP_ECHO;
    ptBufTx[bNumBuf].Rede.IP.Trsp.CabIcmp.bCod = 0x00;
    ptBufTx[bNumBuf].Rede.IP.Trsp.CabIcmp.wChecksum = 0x0000;
    ptBufTx[bNumBuf].Rede.IP.Trsp.CabIcmp.wId = 0x0200;
    ptBufTx[bNumBuf].Rede.IP.Trsp.CabIcmp.wSeq = wNumSeq;

    //Preenche o buffer ICMP.
    bAux = 0x61;
    for(wIndex = 0; wIndex < (ICMP_DATA_SIZE>>1); wIndex++)
    {
      pwIcmpData[wIndex] = bAux;        //Preenche o buffer com 'Lixo'.
      bAux++;
      pwIcmpData[wIndex] += (bAux << 8); 
      bAux++;
      if(bAux == 0x7b) bAux = 0x61;
    }

    ptBufTx[bNumBuf].Rede.IP.Trsp.CabIcmp.wChecksum =   //Checksum do ICMP.
          IP_CalcChecksum((void *)&ptBufTx[bNumBuf].Rede.IP.Trsp.CabIcmp,
          ICMP_HEADER_SIZE, pwIcmpData, ICMP_DATA_SIZE);
    
    //Verifica se o IP pertence a rede ou precisa enviar atrav�s do gateway    
    IP_IPRede(pwIPAlvo, pwIPDestRede);

    //Envia para a camada Rede.
    Rede_Envia((WORD*)&ptBufTx[bNumBuf], 
             ETH_HEADER_SIZE + IP_HEADER_SIZE + ICMP_HEADER_SIZE, 
             pwIcmpData, ICMP_DATA_SIZE, pwIPDestRede, FRAME_IP, bNumBuf);
  }
}


///////////////////////////////////////////////////////////////////////////
// IP_Envia
// Envia um pacote vindo da camada Transporte usando o protocolo IP.
// Par�metros:  pwIPDestino - IP do cliente que deve receber este pacote.
//              bPort       - Porta que ser� usada na conex�o.
//              pvCab       - Cabe�alho do protocolo de transporte.
//              wTamCab     - Numero do Bytes do cabe�alho.
//              pwData      - Buffer com os dados para serem transmitidos.
//              wTamData    - N�mero de Bytes do buffer de dados.
// Retorno: TRUE se a transmiss�o ocorreu com sucesso.
//          FALSE se houve problema na transmiss�o 
//              (basicamente falta de mem�ria).
///////////////////////////////////////////////////////////////////////////
BOOLEAN IP_Envia(_IN_ WORD *pwIPDestino, _IN_ BYTE bProt,
                 _IN_ void *pvCab,       _IN_ WORD wTamCab,
                 _IN_ WORD *pwData,      _IN_ WORD wTamData)
{
  BOOLEAN Enviou = FALSE;
  BYTE    bNumBuf;
  BYTE    bIndex;
  WORD    *pwTrspOrg, *pwTrspDest;
  WORD    pwIPDestRede[2];

  if(IP_BufLivre(&bNumBuf))  //Se existe buffer livre para efetuar o envio.
  {
    //Preenche o cabe�alho IP do buffer livre.
    ptBufTx[bNumBuf].Rede.IP.CabIP.wVerIhlQos = IP_VER_IHL_QOS;
    ptBufTx[bNumBuf].Rede.IP.CabIP.wTamTot = IP_HEADER_SIZE + 
                                             wTamCab + wTamData;
    ptBufTx[bNumBuf].Rede.IP.CabIP.wId ++;
    ptBufTx[bNumBuf].Rede.IP.CabIP.wFlagFrag = 0x4000;
    ptBufTx[bNumBuf].Rede.IP.CabIP.bTtl = IP_TTL;
    ptBufTx[bNumBuf].Rede.IP.CabIP.bProtocolo = bProt;
    ptBufTx[bNumBuf].Rede.IP.CabIP.wChecksum = 0x0000;
    ptBufTx[bNumBuf].Rede.IP.CabIP.pwIPOrigem[0] = pwMeuIP[0];
    ptBufTx[bNumBuf].Rede.IP.CabIP.pwIPOrigem[1] = pwMeuIP[1];
    ptBufTx[bNumBuf].Rede.IP.CabIP.pwIPDestino[0] = pwIPDestino[0];
    ptBufTx[bNumBuf].Rede.IP.CabIP.pwIPDestino[1] = pwIPDestino[1];
    ptBufTx[bNumBuf].Rede.IP.CabIP.wChecksum =      //Calcula checksum do 
          IP_CalcChecksum((void *)&ptBufTx[bNumBuf].Rede.IP,  // cab IP.
          IP_HEADER_SIZE, NULL, 0);
          
    //Preenche o cabe�alho da camada transporte.
    
    //Toma o inicio do buffer da camada trsp.
    pwTrspDest=(WORD*)(&ptBufTx[bNumBuf].Rede.IP.Trsp.CabTcp.wPortaOrigem);
    pwTrspOrg = (WORD*)pvCab;  //Inicio do buffer que veio da camada Trsp.

    for(bIndex = 0; bIndex < (wTamCab>>1); bIndex++) //Percorre todos os 
    {                                                // words do cabe�alho.
      *pwTrspDest = *pwTrspOrg;
      pwTrspDest++;
      pwTrspOrg++;
    }

    //Verifica se o IP pertence a rede ou precisa enviar atrav�s do gateway
    IP_IPRede(pwIPDestino, pwIPDestRede);

    //Envia para a camada Rede.
    Rede_Envia((WORD*)&ptBufTx[bNumBuf],
                ETH_HEADER_SIZE + IP_HEADER_SIZE + wTamCab,
                pwData, wTamData, pwIPDestRede, FRAME_IP, bNumBuf);
    Enviou = TRUE;
  }
  return Enviou;
}


///////////////////////////////////////////////////////////////////////////
// IP_IPRede
// Retorna o IP da rede que corresponde ao IP dado. Se o IP de entrada
//    pertence a rede, esta fun��o retorna ele pr�prio, caso contr�rio
//    retorna o IP do gateway.
// Par�metros:  pwIPIn    - IP que deve ser consultado.
//              pwIPOut   - IP pertencente a rede.
// Retorno: void
///////////////////////////////////////////////////////////////////////////
void IP_IPRede(_IN_ WORD *pwIPIn, _OUT_ WORD *pwIPOut)
{
  if((pwIPIn[0]&pwMasc[0]) == (pwMeuIP[0]&pwMasc[0]) &&   //Se pertence 
     (pwIPIn[1]&pwMasc[1]) == (pwMeuIP[1]&pwMasc[1]))     // a mesma rede.
  {
    pwIPOut[0] = pwIPIn[0];
    pwIPOut[1] = pwIPIn[1];
  }
  else                                          //Se pertence a outra rede.
  {
    pwIPOut[0] = pwGateway[0];
    pwIPOut[1] = pwGateway[1];
  }
}


