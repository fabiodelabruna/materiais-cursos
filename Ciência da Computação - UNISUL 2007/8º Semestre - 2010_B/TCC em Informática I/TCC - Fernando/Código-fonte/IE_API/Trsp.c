/*************************************************************************\
 * Nome: Trsp.c                                                          *
 * Vers�o: 1.0                                                           *
 * Data: Setembro de 2003                                                *
 * Autor: Marcos Perez Mokarzel                                          *
 * Fun��o: Executa as fun��es de transposte do protocolo.                *
 * Hist�rico:                                                            *	
\*************************************************************************/

#include "..\IE_API\Diretivas.h"
#include "..\IE_API\Bios.h"
#include "..\IE_API\Rede.h"
#include "..\IE_API\IP.h"
#include "..\IE_API\Trsp.h"

//Fun��es privadas da classe Trsp.
BOOLEAN TCP_AchaSkt(_IN_ WORD wPrtLocal,   _IN_ WORD wPrtRemota,
                    _IN_ WORD *pwIPRemoto, _OUT_ BYTE *pbSkt);
BOOLEAN TCP_AchaSktLivre(_OUT_ BYTE *pbSkt);
WORD TCP_CalcChecksum(_IN_ WORD *pwIPDest, 
                      _IN_ void *pvBuf, _IN_ WORD wTam,
                      _IN_ void *pvCont, _IN_ WORD wTamCont);
BOOLEAN TCP_ConfereChecksum(_IN_ t_IPData *ptBuf);
BOOLEAN TCP_ConferePorta(_IN_ WORD wPrtLocal);
BOOLEAN TCP_EnviaFlag(_IN_ BYTE bSkt, _IN_ WORD wFlag);
BOOLEAN TCP_EnviaPacote(_IN_ BYTE bSkt, _IN_ WORD wFlag);
void TCP_RxAck(_IN_ BYTE bSkt, _IN_ DWORD dwAck);

 //Vari�veis privadas da classe Trsp.
t_Skt   ptSkt[TOTAL_SKT];
t_CabTcp  tCabTcp;

//Vari�veis Publicas da classe Trsp.

///////////////////////////////////////////////////////////////////////////
// Trsp_Init
// Rotina de inicializa��o das vari�veis da classe Transporte.
// Par�metros: Void
// Retorno: void
///////////////////////////////////////////////////////////////////////////
void Trsp_Init(void)
{
	
  BYTE  bIndex;
	
  for(bIndex = 0; bIndex < TOTAL_SKT; bIndex++){   //Coloca todos os sockets
    ptSkt[bIndex].bEstado = EST_LISTEN;           // em estado de repouso.
	}

}


///////////////////////////////////////////////////////////////////////////
// Trsp_Loop
// Rotina executada peri�dicamente para tomar as provid�ncias da camada.
// Par�metros: Void
// Retorno: void
///////////////////////////////////////////////////////////////////////////

void Trsp_Loop(void)
{
  BYTE bSkt;
  
  for(bSkt = 0; bSkt < TOTAL_SKT; bSkt++) //Passa por todos os sockets.
  {
    if(!ptSkt[bSkt].bTempo)         //Se acabou o tempo de espera (timeout)
    {
      switch(ptSkt[bSkt].bEstado & EST_BIT)//Verifica o estado do socket.
      {
        case EST_ESTABLISHED:
          switch(ptSkt[bSkt].bEstado & SUBEST_BIT)
          {
            case SUBEST_WATING:     //Se est� aguardando solicita��o.
            case SUBEST_TX_FIM:     // ou j� tentou muitas vezes.
              TCP_Desconecta(bSkt); //Comanda desconex�o do socket.
              break;
            case SUBEST_TX_1:       //Se est� tentando transmitir.
            case SUBEST_TX_2:
            case SUBEST_TX_3:
              ptSkt[bSkt].wPtrBufTx = 0;  //Aponta para o inicio do buffer.
              TCP_EnviaPacote(bSkt, TCP_FLG_PSH | TCP_FLG_ACK); //Reenvia.
              ptSkt[bSkt].bEstado += 0x10;//Passa para o pr�ximo subestado.
              ptSkt[bSkt].bTempo = TCP_TEMPO_RTX; //Tempo para receber a
                                                  // confirma��o ACK.
              break;
          }
          break;
        case EST_CLOSE_WAIT:
          TCP_Desconecta(bSkt);     //Comanda desconex�o do socket.
          break;

        default:
          ptSkt[bSkt].bEstado = EST_LISTEN;   //Liberando o socket.
          break;
      }
    }
  }
}


///////////////////////////////////////////////////////////////////////////
// Trsp_Relogio
// Rotina temporal associada ao clock do MCU, executada a cada 1mS.
// Par�metros: Void
// Retorno: void
///////////////////////////////////////////////////////////////////////////
void Trsp_Relogio(void)
{
  BYTE bIndex;
  static BYTE bConta100mS = 100;
  
  bConta100mS--;
  if(!bConta100mS)
  {
    for(bIndex = 0; bIndex < TOTAL_SKT; bIndex++) //Passa por todos 
    {                                             // os sockets.
      if(ptSkt[bIndex].bTempo)          //Se existe tempo para ser contado.
        ptSkt[bIndex].bTempo--;         //conta tempo.
    }
    bConta100mS = 100;
  }
	
}



///////////////////////////////////////////////////////////////////////////
// TCP_Trata
// Trata os pacotes TCP que chegaram das camadas mais baixas.
// Par�metros: ptBuf  - Buffer contendo o pacote que deve ser tratado.
// Retorno: void
///////////////////////////////////////////////////////////////////////////
void TCP_Trata(t_IPData *ptBuf)
{
  BYTE bSkt;
  WORD wNumBytes;

  (*ptBuf).Trsp.Tcp.dwSeq = SWAPW((*ptBuf).Trsp.Tcp.dwSeq);
  (*ptBuf).Trsp.Tcp.dwAck = SWAPW((*ptBuf).Trsp.Tcp.dwAck);

  //Calcula o n�mero de bytes.
  wNumBytes = (*ptBuf).CabIP.wTamTot-IP_HEADER_SIZE-TCP_HEADER_SIZE;
  if(TCP_ConfereChecksum(ptBuf) &&            //Se o checksum est� correto.
     TCP_ConferePorta((*ptBuf).Trsp.Tcp.wPortaDestino)) // e a porta est�
  {                                           //aberta, trata o pacote.
    if(!TCP_AchaSkt((*ptBuf).Trsp.Tcp.wPortaDestino,  //Se n�o existe
                   (*ptBuf).Trsp.Tcp.wPortaOrigem,    // conex�o.
                   (*ptBuf).CabIP.pwIPOrigem, &bSkt))
    {
      //Ignora pacote com comando de RST ou FIN.
      if(!((*ptBuf).Trsp.Tcp.wDOffFlag & (TCP_FLG_RST|TCP_FLG_FIN)))
      {
        if(TCP_AchaSktLivre(&bSkt))         //Tenta alocar um novo socket.
        {
          //Preenche os dados do socket.
          ptSkt[bSkt].wPrtLocal = (*ptBuf).Trsp.Tcp.wPortaDestino;
          ptSkt[bSkt].wPrtRemota = (*ptBuf).Trsp.Tcp.wPortaOrigem;
          ptSkt[bSkt].pwIPRemoto[0] = (*ptBuf).CabIP.pwIPOrigem[0];
          ptSkt[bSkt].pwIPRemoto[1] = (*ptBuf).CabIP.pwIPOrigem[1];
          ptSkt[bSkt].dwSeqRx = (*ptBuf).Trsp.Tcp.dwSeq+1;//Sincroniza o
                                          // seq de recep��o com o enviado
                                          // pelo cliente, soma 1 pois este
                                          // s� pode ser um pacote SYN.
          ptSkt[bSkt].dwSeqTx = SWAPW(ptSkt[bSkt].dwSeqTx)>>3;//Init Seq Tx
          ptSkt[bSkt].wTamBufTx = 0;      //Reseta o buffer de transmiss�o.
  
          if((*ptBuf).Trsp.Tcp.wDOffFlag & TCP_FLG_ACK) //Se chegou um Ack
          {                                             // indevido.
            TCP_EnviaFlag(bSkt, TCP_FLG_RST); //Reseta a comunica��o.
            ptSkt[bSkt].bEstado = EST_LISTEN; //Libera o socket
          }
          else if((*ptBuf).Trsp.Tcp.wDOffFlag & TCP_FLG_SYN)//Se � uma
          {                               // tentativa de criar uma conex�o
            //Cria a nova conex�o.
            if(TCP_EnviaFlag(bSkt, TCP_FLG_SYN+TCP_FLG_ACK))//Responde 
            {                                               // SYN, ACK.
              ptSkt[bSkt].dwSeqTx++; //Se consegui transmitir soma 1 no Seq
              ptSkt[bSkt].bEstado = EST_SYN_RECEIVED;
              ptSkt[bSkt].bTempo = TCP_TEMPO_RESP;
            }
          }
        }
      }
    }
    else                                          //Se j� existe conex�o.
    {
      if((*ptBuf).Trsp.Tcp.wDOffFlag&TCP_FLG_RST) //Independente do estado,
      {                                           // se chegou um reset.
        ptSkt[bSkt].bEstado = EST_LISTEN;         //Libera socket.
      }
      else if(((*ptBuf).Trsp.Tcp.wDOffFlag&TCP_FLG_SYN) && //Se n�o � um 
              ((ptSkt[bSkt].bEstado & EST_BIT) == EST_SYN_SENT))// reset,
      {                                     // mas um SYN no estado errado.
        TCP_EnviaFlag(bSkt, TCP_FLG_RST);         //Reseta a comunica��o.
        ptSkt[bSkt].bEstado = EST_TIME_WAIT;      //Tempo para finaliza��o.
        ptSkt[bSkt].bTempo = TCP_TEMPO_CLOSE;     //Aguarda 2MSL.
      }
      else
      {
        switch(ptSkt[bSkt].bEstado & EST_BIT)   //Verifica em qual estado 
        {                                       // o socket est�.
          case EST_SYN_SENT:                  //Se est� aguardando SYN,ACK.
            if((*ptBuf).Trsp.Tcp.wDOffFlag & TCP_FLG_FIN) //Se chegou um
            {                                             // comando FIN.
              TCP_EnviaFlag(bSkt, TCP_FLG_RST); //Reseta a comunica��o.
              ptSkt[bSkt].bEstado = EST_LISTEN; //Libera o socket
            }
            else if((*ptBuf).Trsp.Tcp.wDOffFlag&TCP_FLG_SYN)//Se chegou um
            {                                               // comando SYN.
              if((*ptBuf).Trsp.Tcp.wDOffFlag&TCP_FLG_ACK) //Se est� acompa_
              {                                           // nhado do ACK.
                TCP_EnviaFlag(bSkt, TCP_FLG_ACK); //Confirma conex�o c/ ACK
                ptSkt[bSkt].bEstado = EST_ESTABLISHED;  //Socket conectado.
                ptSkt[bSkt].bTempo = TCP_TEMPO_OUVIR;   //Tempo p/ resposta
                ptSkt[bSkt].wWin = (*ptBuf).Trsp.Tcp.wWin; //Guarda Window.
                //Inicia envio de dados.
              }
              else                                //Se veio sem o [ACK].
              {
                if(TCP_EnviaFlag(bSkt, TCP_FLG_ACK))//Confirma recebimento
                                                    // da requisi��o c/ ACK
                  ptSkt[bSkt].bEstado = EST_SYN_RECEIVED; //Aguarda ACK.
                ptSkt[bSkt].bTempo = TCP_TEMPO_RESP;
              }
            }
            break;
            
          case EST_SYN_RECEIVED:              //Se o socket est� aguardando
                                              // ACK para SYN.
            if((*ptBuf).Trsp.Tcp.wDOffFlag & TCP_FLG_FIN) //Ou se chegou um
            {                                             // comando FIN.
              TCP_EnviaFlag(bSkt, TCP_FLG_RST); //Reseta a comunica��o.
              ptSkt[bSkt].bEstado = EST_LISTEN; //Libera o socket.
            }
            else if((*ptBuf).Trsp.Tcp.wDOffFlag&TCP_FLG_ACK)//Se aceitou o
            {                                               // sincronismo.
              if((*ptBuf).Trsp.Tcp.dwAck == ptSkt[bSkt].dwSeqTx)//Se � o
              {                                             // ACK do SYN.
                ptSkt[bSkt].bEstado = EST_ESTABLISHED;  //Estado conectado.
                ptSkt[bSkt].bTempo = TCP_TEMPO_OUVIR; //Tempo m�ximo para
                                                  // receber a solicita��o.
                ptSkt[bSkt].wWin = (*ptBuf).Trsp.Tcp.wWin; //Armazena o
                                                  // tamanho m�ximo aceito.
              }
            }
            break;
            
          case EST_ESTABLISHED:               //Est� conectado. Pronto para
                                              // enviar e receber dados.
            ptSkt[bSkt].bTempo = TCP_TEMPO_OUVIR; //Se recebeu um pacote,
                                                  // reseta tempo.
            if((*ptBuf).Trsp.Tcp.wDOffFlag & TCP_FLG_FIN)//Deve finalizar
            {                                           // a conex�o?
              if(TCP_EnviaFlag(bSkt, TCP_FLG_ACK))    //Responde [ACK].
                ptSkt[bSkt].bEstado = EST_CLOSE_WAIT; //Novo estado.
              ptSkt[bSkt].bTempo = TCP_TEMPO_FIN;     //Conta novo tempo.
            }
            else                              //Se chegou um pacote correto
            {
              if((*ptBuf).Trsp.Tcp.wDOffFlag & TCP_FLG_ACK) //Chegou Ack
              {                                           // c/ a mensagem?
                TCP_RxAck(bSkt, (*ptBuf).Trsp.Tcp.dwAck);   //Trata o ack.
              }
              
              //
              //Verifica se o pacote est� na sequ�ncia correta, por econo_
              // mia de mem�ria n�o vamos aceitar pacotes em ordem errada
              // ou se forem quebrados ser�o repassados peda�o por peda�o.
              //
              if(ptSkt[bSkt].dwSeqRx==(*ptBuf).Trsp.Tcp.dwSeq) //Se ainda
              {                               // n�o tratou este pacote.
                ptSkt[bSkt].dwSeqRx += wNumBytes;//Calc. pr�xima entrada.
                
                //Repassa para a camada aplica��o.
                App_Trata((*ptBuf).Trsp.Tcp.wPortaDestino,//Porta local.
                          bSkt,                     //N�mero da conex�o 
                                                    // (mesmo do socket).
                          (*ptBuf).Trsp.Tcp.pbData, //Buffer onde se 
                                                    // encontra o pacote.
                          wNumBytes);               //N�mero de bytes.
              }
            }
            break;
            
          case EST_FIN_WAIT_1:                    //Se est� aguardando a
                                                  // confirma��o do [FIN].
            if((*ptBuf).Trsp.Tcp.wDOffFlag & TCP_FLG_FIN) //Se confirmou,
            {                                   // est� finalizando tamb�m.
              if(TCP_EnviaFlag(bSkt, TCP_FLG_ACK))//Envia um [ACK].
              {                                   //Se enviou.
                ptSkt[bSkt].bEstado = EST_CLOSING;//Passa a aguardar o ACK
                                                  // confirmando o FIN.
              }
              ptSkt[bSkt].bTempo = TCP_TEMPO_FIN; //Marca tempo.
            }
            else if((*ptBuf).Trsp.Tcp.wDOffFlag&TCP_FLG_ACK)//Se confirmou
            {                                               // a recep��o.
              if((*ptBuf).Trsp.Tcp.dwAck == ptSkt[bSkt].dwSeqTx)//Se � o
              {                                             // Ack do FIN.
                ptSkt[bSkt].bEstado = EST_FIN_WAIT_2;//Passa a aguardar FIN
                ptSkt[bSkt].bTempo = TCP_TEMPO_FIN;  //Conta tempo m�ximo.
              }
            }
            break;
            
          case EST_FIN_WAIT_2:                  //Se est� aguardando a 
                                                // confirma��o do [FIN].
            if((*ptBuf).Trsp.Tcp.wDOffFlag & TCP_FLG_FIN) //Se confirmou,
            {                                   // est� finalizando tamb�m.
              if(TCP_EnviaFlag(bSkt, TCP_FLG_ACK))   //Envia um [ACK].
                ptSkt[bSkt].bEstado = EST_TIME_WAIT; //Tempo p/ finaliza��o
              ptSkt[bSkt].bTempo = TCP_TEMPO_CLOSE;  //Conta tempo.
            }
            break;

          case EST_LAST_ACK:                  //Aguardando �ltimo ACK antes
                                              // de liberar o socket.
            if(((*ptBuf).Trsp.Tcp.wDOffFlag&TCP_FLG_ACK)&&  //Se confirmou
               ((*ptBuf).Trsp.Tcp.dwAck == ptSkt[bSkt].dwSeqTx)) // o FIN.
            {
              ptSkt[bSkt].bEstado = EST_LISTEN;         //Libera o socket.
            }
            break;
            
          case EST_CLOSING:                   //Se est� fechando a conex�o.
            if(((*ptBuf).Trsp.Tcp.wDOffFlag&TCP_FLG_ACK)&&  //Se confirmou
               ((*ptBuf).Trsp.Tcp.dwAck == ptSkt[bSkt].dwSeqTx)) // o FIN.
            {
              ptSkt[bSkt].bEstado = EST_TIME_WAIT;  //Tempo p/ finaliza��o.
              ptSkt[bSkt].bTempo = TCP_TEMPO_CLOSE; //Aguarda 2MSL.
            }
            break;
            
          default:                  
            //Os estados n�o tratados aqui s�o alterados via timeout,
            // comando de conecta ou desconecta.
            break;
        }
      }
    }
  }
}


///////////////////////////////////////////////////////////////////////////
// TCP_BufEnviado
// Recebe a informa��o das camadas de baixo que o buffer usado para o evio  
//  pode ser liberado.
// Par�metros:  wPrtOrigem  - Porta de origem do pacote
//              wPrtDestino - Porta de destino do pacote
//              pwIPDestino - IP de destino do pacote.
// Retorno:   void
///////////////////////////////////////////////////////////////////////////
void TCP_BufEnviado(_IN_ WORD wPrtOrigem, _IN_ WORD wPrtDestino,
                    _IN_ WORD *pwIPDestino)
{
  BYTE bSkt;
  if(TCP_AchaSkt(wPrtOrigem, wPrtDestino, pwIPDestino, &bSkt))//Se o socket
  {                                                           // existe.
    if((ptSkt[bSkt].bEstado & EST_BIT) == EST_ESTABLISHED)    //Se est�
    {                                                         // conectado.
      if(ptSkt[bSkt].wTamBufTx != ptSkt[bSkt].wPtrBufTx)  //Se existe dados
      {                                              // p/ ser transmitido.
        //Inicia a transmiss�o do pr�ximo bloco.
        TCP_EnviaPacote(bSkt, TCP_FLG_PSH | TCP_FLG_ACK); 
      }
    }
  }
}


///////////////////////////////////////////////////////////////////////////
// TCP_Envia
// Envia um conjunto de bytes atrav�s de um socket usando o protocolo TCP.
// Par�metros:  bSkt    - N�mero do soque que est� tratando esta conex�o.
//              pwBuf   - Ponteiro para o buffer que deve ser enviado.
//              wTam    - N�mero de bytes que deve ser enviado.
// Retorno:   TRUE se foi executado com sucesso.
//            FALSE se houve problema no estabelecimento da conex�o.
///////////////////////////////////////////////////////////////////////////
BOOLEAN TCP_Envia(_IN_ BYTE bSkt, _IN_ WORD *pwBuf, _IN_ WORD wTam)
{
  BOOLEAN Sucesso = FALSE;
  BYTE bAux;
  
  if(ptSkt[bSkt].bEstado == EST_ESTABLISHED)//Se est� conectado.
  {                               // e n�o est� no meio de uma transmiss�o.
    ptSkt[bSkt].pwBufTx = pwBuf;  //Guarda buffer que deve ser transmitido.
    ptSkt[bSkt].wTamBufTx = wTam; //Guarda o tamanho do buffer.
    ptSkt[bSkt].wPtrBufTx = 0;    //Aponta para o inicio do buffer.
    ptSkt[bSkt].bEstado = EST_ESTABLISHED|SUBEST_TX_1;  //Muda o subestado.
    ptSkt[bSkt].bTempo = TCP_TEMPO_RTX; //Tempo m�ximo para receber a
                                        // confirma��o ACK.
    Sucesso = TRUE;               //Assinala que os dados ser�o enviados.
        
    TCP_EnviaPacote(bSkt, TCP_FLG_PSH|TCP_FLG_ACK); //Inicia a transmiss�o.
  }
  return Sucesso;
}


///////////////////////////////////////////////////////////////////////////
// TCP_Conecta
// Inicia o estabelecimento de uma conex�o.
// Par�metros:  wPrtLocal   - Porta local.
//              wPrtRemota  - Porta remota.
//              pwIPRemoto  - IP do host remoto.
//              pbSkt       - N�mero do socket que gerencia esta conex�o.
// Retorno:   TRUE se foi executado com sucesso.
//            FALSE se houve problema no estabelecimento da conex�o.
///////////////////////////////////////////////////////////////////////////
BOOLEAN TCP_Conecta(_IN_ WORD wPrtLocal,   _IN_ WORD wPrtRemota, 
                    _IN_ WORD *pwIPRemoto, _OUT_ BYTE *pbSkt)
{
  BOOLEAN Conectado = FALSE;
  
  if(TCP_AchaSkt(wPrtLocal, wPrtRemota, pwIPRemoto, pbSkt)) //Se j� existe
  {                                                // conex�o estabelecida.
    Conectado = TRUE;             //Retorna que obteve sucesso na conex�o.
  }
  else                            //Se n�o est� conectado.
  {
    if(TCP_AchaSktLivre(pbSkt))   //Busca um socket livre para gerenciar
    {                             // esta conex�o.
      ptSkt[*pbSkt].wPrtLocal = wPrtLocal;    //Monta o socket.
      ptSkt[*pbSkt].wPrtRemota = wPrtRemota;
      ptSkt[*pbSkt].pwIPRemoto[0] = pwIPRemoto[0];
      ptSkt[*pbSkt].pwIPRemoto[1] = pwIPRemoto[1];
      ptSkt[*pbSkt].dwSeqTx = SWAPW(ptSkt[*pbSkt].dwSeqTx) >> 3;
      if(TCP_EnviaFlag(*pbSkt, TCP_FLG_SYN))  //Envia [SYN].
      {
        ptSkt[*pbSkt].dwSeqTx++;  //Se consegui transmitir soma 1 no Seq.
        ptSkt[*pbSkt].bEstado = EST_SYN_SENT;  //Passa a aguardar SYN,ACK.
        ptSkt[*pbSkt].bTempo = TCP_TEMPO_RESP; //Tempo p/ resposta.
        Conectado = TRUE;         //Retorna que obteve sucesso na conex�o.
      }
    }
  }
  return Conectado;
}


///////////////////////////////////////////////////////////////////////////
// TCP_Estado
// Retorna o estado/subestado do socket.
// Par�metros:  bSkt       - N�mero do socket que deve ser consultado.
// Retorno:   Estado/subestado do socket.
///////////////////////////////////////////////////////////////////////////
BYTE TCP_Estado(_IN_ BYTE bSkt)
{
  return ptSkt[bSkt].bEstado;
}


///////////////////////////////////////////////////////////////////////////
// TCP_Desconecta
// Inicia a finaliza��o de uma conex�o.
// Par�metros:  bSkt  - n�mero do socket que controla a conex�o.
// Retorno:   void
///////////////////////////////////////////////////////////////////////////
void TCP_Desconecta(_IN_ BYTE bSkt)
{
  switch(ptSkt[bSkt].bEstado & EST_BIT) //Verifica estado atual da conex�o.
  {
    case EST_SYN_SENT:
      ptSkt[bSkt].bEstado = EST_LISTEN;
      break;
    case EST_SYN_RECEIVED:
    case EST_ESTABLISHED:
      TCP_EnviaFlag(bSkt, TCP_FLG_FIN | TCP_FLG_ACK);
      ptSkt[bSkt].dwSeqRx ++;
      ptSkt[bSkt].dwSeqTx ++;
      ptSkt[bSkt].bEstado = EST_FIN_WAIT_1;
      ptSkt[bSkt].bTempo = TCP_TEMPO_RESP;      //Tempo m�ximo p/ resposta.
      break;
    case EST_CLOSE_WAIT:
      TCP_EnviaFlag(bSkt, TCP_FLG_FIN | TCP_FLG_ACK);
      ptSkt[bSkt].bEstado = EST_LAST_ACK;
      ptSkt[bSkt].bTempo = TCP_TEMPO_RESP;      //Tempo m�ximo p/ resposta.
      break;
  }
}



///////////////////////////////////////////////////////////////////////////
// TCP_AchaSkt
// Baseado nos dados passados acho o socket que corresponde a uma conex�o
//  j� estabelecida retornando o n�mero do socket.
// Par�metros:  wPrtLocal   -Porta de Origem.
//              wPrtRemota  - Porta de Destino.
//              pwIPRemoto  - IP de origem.
//              pbSkt       - Ponteiro para o n�mero do socket.
// Retorno:   TRUE se o socket j� existe.
//            FALSE se o socket n�o existe.
///////////////////////////////////////////////////////////////////////////
BOOLEAN TCP_AchaSkt(_IN_ WORD wPrtLocal, _IN_ WORD wPrtRemota, 
                    _IN_ WORD *pwIPRemoto, _OUT_ BYTE *pbSkt)
{
  BYTE Achou = FALSE;
  BYTE bSkt;
  
  for(bSkt = 0; bSkt < TOTAL_SKT; bSkt++)
  {
    if((ptSkt[bSkt].bEstado & EST_BIT) != EST_LISTEN)  //Se o socket est�
    {                                                  // ocupado, mesmas
      if((ptSkt[bSkt].wPrtLocal == wPrtLocal) &&       // porta de origem,
         (ptSkt[bSkt].wPrtRemota == wPrtRemota) &&     // porta de destino
         (ptSkt[bSkt].pwIPRemoto[0] == pwIPRemoto[0])&&// e mesmo IP de
         (ptSkt[bSkt].pwIPRemoto[1] == pwIPRemoto[1])) // origem, ent�o � a
      {                                                // mesma conex�o.
        Achou = TRUE;                     //Assinala que achou a conex�o.
        *pbSkt = bSkt;                    //Armazena o n�mero do socket.
        break;                            //Encerra a busca.
      }
    }
  }
  return Achou;
}


///////////////////////////////////////////////////////////////////////////
// TCP_AchaSktLivre
// Busca um socket livre para tratar uma nova conex�o.
// Par�metros:  pbSkt   - Ponteiro para o n�mero do socket.
// Retorno:   TRUE se existe um socket livre.
//            FALSE se todos os sockets est�o ocupados.
///////////////////////////////////////////////////////////////////////////
BOOLEAN TCP_AchaSktLivre(_OUT_ BYTE *pbSkt)
{
  BYTE Achou = FALSE;
  BYTE bSkt;
  
  for(bSkt = 0; bSkt < TOTAL_SKT; bSkt++)
  {
    if((ptSkt[bSkt].bEstado & EST_BIT) == EST_LISTEN) //Se o socket est�
    {                                                 // ocupado.
      Achou = TRUE;               //Assinala que achou a conex�o.
      *pbSkt = bSkt;              //Armazena o socket livre.
      break;                      //Encerra a busca.
    }
  }
  return Achou;
}


///////////////////////////////////////////////////////////////////////////
// TCP_CalcChecksum
// Calcula o valor do checksum de um conjunto de bytes segundo a regra
//  descrita na p�gina xxxxxxx do livro.
// Par�metros:  pwIPDest  - IP destino do pacote.
//              pvBuf     - Ponteiro para o cabe�alho do pacote.
//              wTam      - Tamanho do cabe�alho do pacote.
//              pvCont    - Ponteiro para o conjunto de dados.
//              wTamCont  - Tamanho do buffer em bytes.
// Retorno:     Valor do checksum calculado.
///////////////////////////////////////////////////////////////////////////
WORD TCP_CalcChecksum(_IN_ WORD *pwIPDest, 
                      _IN_ void *pvBuf, _IN_ WORD wTam, 
                      _IN_ void *pvCont, _IN_ WORD wTamCont)
{
  WORD  *pwData = (WORD *)pvBuf;
  DWORD dwSum = 0;
  WORD  wIndex;
  
  dwSum += pwMeuIP[0];      //Inclui TCP pseudo-header.
  dwSum += pwMeuIP[1];
  dwSum += pwIPDest[0];
  dwSum += pwIPDest[1];
  dwSum += wTam + wTamCont; //Tamanho do cabe�alho TCP + tamanho dos dados.
  dwSum += IP_PROTOCOLO_TCP;
  
  for(wIndex = 0; wIndex < (wTam>>1); wIndex++)
  {
    dwSum += *pwData;
    pwData++;
  }
  if(wTam & 0x0001)         //Se sobrou um byte.
    dwSum += (*pwData & 0xFF00);

  pwData = (WORD *)pvCont;
  for(wIndex = 0; wIndex < (wTamCont>>1); wIndex++)//Soma Words do buffer.
  {
    dwSum += SWAPB(*pwData);
    pwData++;
  }
  if(wTamCont & 0x0001)     //Se sobrou um byte.
    dwSum += (SWAPB(*pwData) & 0xFF00);

  while(dwSum >> 16)        //Transforma a soma de 32 bits em 16 bits.
    dwSum = (dwSum & 0xFFFF) + (dwSum >> 16);
  
  return ~dwSum;
}


///////////////////////////////////////////////////////////////////////////
// TCP_ConfereChecksum
// Verifica se o valor do CHecksum recebido est� correto.
// Par�metros:  ptBuf - Ponteiro para o buffer IP que deve ser conferido.
// Retorno: TRUE se o valor est� correto.
//          FALSE se o valor est� incorreto.
///////////////////////////////////////////////////////////////////////////
BOOLEAN TCP_ConfereChecksum(_IN_ t_IPData *ptBuf)
{
  BOOLEAN Correto;
  DWORD dwSum;
  WORD wChecksumAux = (*ptBuf).Trsp.Tcp.wChecksum;  //Guarda o checksum que
                                                    // chegou no pacote.
  WORD wTamIP = ((*ptBuf).CabIP.wVerIhlQos&0x0F00)>>6; //Calcula o tamanho
                                                       // do cabe�alho IP.
  (*ptBuf).Trsp.Tcp.wChecksum = 0x0000;//Zera ele p/ calcular o novo valor.

  dwSum = TCP_CalcChecksum((*ptBuf).CabIP.pwIPOrigem, 
                    (BYTE*)ptBuf + wTamIP, (*ptBuf).CabIP.wTamTot - wTamIP,
                    NULL, 0);

  if(wChecksumAux == dwSum)                 //Se o checksum est� correto.
    Correto = TRUE;
  else
    Correto = FALSE;
    
  (*ptBuf).Trsp.Tcp.wChecksum = wChecksumAux;    //Retorna o valor antigo.
  
  return Correto;
}


///////////////////////////////////////////////////////////////////////////
// TCP_ConferePorta
// Verifica se a porta local est� aberta para comunica��o.
// Par�metros:  wPorta  - Porta que o cliente est� tentando acessar.
// Retorno: TRUE se a porta est� aberta.
//          FALSE se a porta est� fechada.
///////////////////////////////////////////////////////////////////////////
BOOLEAN TCP_ConferePorta(WORD wPorta)
{
  BOOLEAN Correto = FALSE;
  WORD wIndex = 0;
  
  while(TCP_PrtAberta[wIndex])         //Passa por todas as portas abertas.
  {
    if(TCP_PrtAberta[wIndex] == wPorta)//Se achou a porta procurada.
    {
      Correto = TRUE;
      break;
    }
    wIndex++;                          //Pega a pr�xima porta.
  }
  
  return Correto;
}


///////////////////////////////////////////////////////////////////////////
// TCP_EnviaFlag 
// Envia um pacote TCP/IP contendo apenas os flags de controle.
// Par�metros:  bSkt    - N�mero do socket que gerencia esta connec��o.
//              wFlag   - Flags que devem ser enviados.
// Retorno: TRUE se os flags foram enviados.
//          FALSE se houve algum problema.
///////////////////////////////////////////////////////////////////////////
BOOLEAN TCP_EnviaFlag(_IN_ BYTE bSkt, _IN_ WORD wFlag)
{
  tCabTcp.wPortaOrigem = ptSkt[bSkt].wPrtLocal;
  tCabTcp.wPortaDestino = ptSkt[bSkt].wPrtRemota;
  tCabTcp.dwSeq = ptSkt[bSkt].dwSeqTx;
  tCabTcp.dwAck = ptSkt[bSkt].dwSeqRx;
  tCabTcp.wDOffFlag = wFlag + 0x5000;
  tCabTcp.wWin = TCP_DATA_SIZE;
  tCabTcp.wChecksum = 0x0000;
  tCabTcp.wUrgent = 0x0000;
  tCabTcp.wChecksum = TCP_CalcChecksum(ptSkt[bSkt].pwIPRemoto, 
                                       &tCabTcp, TCP_HEADER_SIZE, 
                                       NULL, 0);
  
  tCabTcp.dwSeq = SWAPW(tCabTcp.dwSeq);
  tCabTcp.dwAck = SWAPW(tCabTcp.dwAck);
  
  return (IP_Envia(ptSkt[bSkt].pwIPRemoto, IP_PROTOCOLO_TCP, //Envia pacote
              (void *)&tCabTcp, TCP_HEADER_SIZE, NULL, 0));  // usando IP.
}


///////////////////////////////////////////////////////////////////////////
// TCP_EnviaPacote 
// Envia um pacote TCP/IP.
// Par�metros:  bSkt    - N�mero do socket que gerencia esta connec��o.
//              wFlag   - Flags que devem ser enviados.
// Retorno: TRUE se os flags foram enviados.
//          FALSE se houve algum problema.
///////////////////////////////////////////////////////////////////////////
BOOLEAN TCP_EnviaPacote(_IN_ BYTE bSkt, _IN_ WORD wFlag)
{
  BOOLEAN Sucesso = FALSE;
  WORD wNumByteTx;
  
  tCabTcp.wPortaOrigem = ptSkt[bSkt].wPrtLocal;
  tCabTcp.wPortaDestino = ptSkt[bSkt].wPrtRemota;
  tCabTcp.dwSeq = ptSkt[bSkt].dwSeqTx + ptSkt[bSkt].wPtrBufTx;
  tCabTcp.dwAck = ptSkt[bSkt].dwSeqRx;
  tCabTcp.wDOffFlag = wFlag + 0x5000;
  tCabTcp.wWin = ptSkt[bSkt].wWin;
  tCabTcp.wChecksum = 0x0000;
  tCabTcp.wUrgent = 0x0000;

  wNumByteTx = ptSkt[bSkt].wTamBufTx-ptSkt[bSkt].wPtrBufTx; //Calcula o que
                                                 // falta para ser enviado.
  if(wNumByteTx > ptSkt[bSkt].wWin)     //Se o que falta para ser enviado
  {                                     // � maior que a janela de envio.
    wNumByteTx = ptSkt[bSkt].wWin;      //Transmite o tamanho da janela.
    wNumByteTx &= 0xFFFE;               //Se n�o for par subtrai 1.
  }
  if(wNumByteTx > BUF_MAX_PHY)          //Se o que falta para ser enviado 
  {                                     // � maior que a mem�ria do PHY.
    wNumByteTx = BUF_MAX_PHY;           //Transmite o tamanho da mem�ria.
  }
  
  tCabTcp.wChecksum = TCP_CalcChecksum(ptSkt[bSkt].pwIPRemoto,  //Calcula o
                          &tCabTcp, TCP_HEADER_SIZE,           // checksun.
                          ptSkt[bSkt].pwBufTx+(ptSkt[bSkt].wPtrBufTx >> 1),
                          wNumByteTx);

  tCabTcp.dwSeq = SWAPW(tCabTcp.dwSeq);
  tCabTcp.dwAck = SWAPW(tCabTcp.dwAck);
  
  if(IP_Envia(ptSkt[bSkt].pwIPRemoto, IP_PROTOCOLO_TCP, //Envia pacote
              (void *)&tCabTcp, TCP_HEADER_SIZE,        // usando IP.
              ptSkt[bSkt].pwBufTx + (ptSkt[bSkt].wPtrBufTx >> 1), 
              wNumByteTx))
  {                                     //Se conseguiu enviar.
    ptSkt[bSkt].wPtrBufTx += wNumByteTx;//Calcula a nova posi��o do buffer.
    Sucesso = TRUE;
  }
  
  return Sucesso;
}



///////////////////////////////////////////////////////////////////////////
// TCP_RxAck
// Trata Ack recebido em um pacote.
// Par�metros:  bSkt    - N�mero do socket que gerencia esta connec��o.
//              dwAck   - N�mero sequencial recebido pelo ack.
// Retorno: void
///////////////////////////////////////////////////////////////////////////
void TCP_RxAck(_IN_ BYTE bSkt, _IN_ DWORD dwAck)
{
  WORD wNumBytesAck;

  switch(ptSkt[bSkt].bEstado & SUBEST_BIT)
  {
    case SUBEST_WATING:                   //Se est� aguardando solicita��o. 
      //Ignora o ACK.
      break;
    case SUBEST_TX_1:                     //Se est� tentando transmitir.
    case SUBEST_TX_2:
    case SUBEST_TX_3:
    case SUBEST_TX_FIM:
      if(dwAck <= (ptSkt[bSkt].dwSeqTx+ptSkt[bSkt].wPtrBufTx))//Se o valor
      {                                                    // est� correto.
        wNumBytesAck = dwAck - ptSkt[bSkt].dwSeqTx; //Calcula n� de bytes.
        ptSkt[bSkt].dwSeqTx = dwAck;          //Muda o ponteiro Ack.
        ptSkt[bSkt].wPtrBufTx -= wNumBytesAck;//Desloca ponteiro do buffer.
        ptSkt[bSkt].wTamBufTx -= wNumBytesAck;//Diminui tamanho do buffer.
        ptSkt[bSkt].pwBufTx += (wNumBytesAck >> 1); //Muda ponteiro do buf.
        ptSkt[bSkt].bEstado = EST_ESTABLISHED | SUBEST_TX_1;//Volta para o
                                                          // subestado Tx1.
        if(ptSkt[bSkt].wPtrBufTx == 0)     //Se confirmou tudo o que
        {                                  // foi transmitido.
          if(ptSkt[bSkt].wTamBufTx == 0)   //Se j� transmitiu todo o buffer
          {                                // solicitado pela aplica��o.
            ptSkt[bSkt].bEstado = EST_ESTABLISHED; //Volta a aguardar.
            ptSkt[bSkt].bTempo = TCP_TEMPO_OUVIR; //Tempo m�ximo para
                                           // receber uma nova solicita��o.
            App_BufEnviado(bSkt); //Libera buffer que acaba de ser enviado.
          }
        }
      }
      else                                      //Se o valor est� errado.
      {
        TCP_EnviaFlag(bSkt, TCP_FLG_RST);       //Reseta a comunica��o.
        ptSkt[bSkt].bEstado = EST_LISTEN;       //Libera o socket
      }
      break;
  }
}


