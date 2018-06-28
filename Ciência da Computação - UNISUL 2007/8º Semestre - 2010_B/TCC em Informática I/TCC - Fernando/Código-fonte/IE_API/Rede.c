/*************************************************************************\
 * Nome: Rede.c                                                          *
 * Vers�o: 1.0                                                           *
 * Data: Setembro de 2003                                                *
 * Autor: Marcos Perez Mokarzel                                          *
 * Fun��o: Executa as fun��es de acesso ao hardware e da camada Rede do  *
 *          protocolo TCP/IP.                                            *
 * Hist�rico:                                                            *	
\*************************************************************************/

#include "..\IE_API\Diretivas.h"
#include "..\IE_API\Bios.h"
#include "..\IE_API\Rede.h"
#include "..\IE_API\IP.h"
#include "..\IE_API\Trsp.h"
#include "delays.h"
	
//Fun��es privadas da classe Rede.
void Rede_CopiaMCUMem(_IN_ WORD *pwSource,  //Copia um conjunto de
                      _IN_ WORD wSize);     // Bytes da mem�ria do 
                                            //MCU no PHY.
void Rede_CopiaMCUMemSw(_IN_ WORD *pwSource,//Copia um conjunto de 
                        _IN_ WORD wSize);   // Bytes da mem�ria do 
                                            // MCU no PHY.
void Rede_CopiaBuffer(_OUT_ void *pvDest,   //Copia um conjunto de  
                      _IN_ WORD wSize);     // Bytes do Buffer do PHY 
                                            // para a mem�ria do MCU.
void Rede_Descarta(_IN_ WORD iSize);        //Descarrega buffer do PHY.
void Rede_RecebePacote8900(void);     //Verifica se existe pacote no 8900.
void Rede_TransmitePacote8900(void);  //Verifica necessidade de transmitir 
                                      // pacotes no 8900.
void Rede_TrataBroadcast(void);       //Trata pacotes enviados p/ broadcast
void Rede_TiraBufFila(void);          //Retira buf da fila e avisa seu dono
void Rede_BufEnviado(_IN_ BYTE bId);  //Libera buf que acaba de ser enviado
void Rede_ConsultaARP(_IN_ const WORD *pwIPAlvo); //Envia solicita��o de 
                                                  // endere�o MAC.
void Rede_TrataArp(_IN_ t_CabArp *ptCabArp);//Responde requisi��o de 
                                            // endere�o MAC.
void Arpt_SalvaMac(_IN_ WORD *pwIP, _IN_ WORD *pwMAC); //Guardo o novo MAC
                                                       // na tabela ARP.
BOOLEAN Arpt_ConsultaMac(_IN_ WORD *pwIP, _OUT_ WORD *pwMAC); //Retorna MAC
                                                              // do IP.
void Rede_SolicitaBuffer(_IN_ WORD wSize);  //Solicita espa�o no 
                                            // buffer do PHY.
WORD Rede_TxPronto(void);             //Checa se PHY est� pronto para
                                    // receber frame p/ ser transmitido.

//Vari�veis privadas da classe Rede.
t_Arp     tBufArp;                    //Cria uma instacia do buffer para
                                      // resposta ARP.
t_EthData tBufEnt;                    //Cria uma instacia do 
                                      // buffer de entrada.

t_Rede_TxControl ptTxControl[TOTAL_BUF];    //Fila de controle de buffers
                                            // para ser transmitido.
t_Rede_TxControl tEmTxControl;              //Controla o buffer que est� 
                                           // em transmiss�o.

struct
{
  WORD pwIP[2];                             //IP a ser consultado.
  WORD pwMAC[3];                            //MAC correspondente.
} ptArpTable[TAM_ARP_TABLE];                //Tabela para convers�o
                                            // de IP em MAC.

BYTE  bEntraTxControl;
BYTE  bApArpTable;


///////////////////////////////////////////////////////////////////////////
// Rede_Init
// Envia os camando iniciais para o PHY.
// Par�metros: Void
// Retorno: void
///////////////////////////////////////////////////////////////////////////
void Rede_Init(void)
{
  
  char rede_init;
 /* B=Bios_Le(DATA_PORT);
  Bios_EscreveSw(ADD_PORT, PP_SelfCTL); //Executa programa��o do CS8900.
  Bios_EscreveSw(DATA_PORT, POWER_ON_RESET);//Reseta o CS8900.
  Bios_EscreveSw(ADD_PORT, PP_SelfST); 
  if(Bios_Le(DATA_PORT)!=B){
    ligaOK();
  }  
  ligaOK();//passou
  while(!(Bios_Le(DATA_PORT) & INIT_DONE)); //Aguarda o fim do reset.
*/
  ligaOK();
  Bios_EscreveSw(ADD_PORT, PP_SelfST);
  rede_init = Bios_Le(DATA_PORT);
  Bios_EscreveSw(ADD_PORT, PP_SelfCTL); //Executa programa��o do CS8900.
  Bios_EscreveSw(DATA_PORT, POWER_ON_RESET);//Reseta o CS8900.

  Bios_EscreveSw(ADD_PORT, PP_SelfST);
  rede_init = Bios_Le(DATA_PORT);
  while(!(Bios_Le(DATA_PORT) & INIT_DONE)); //Aguarda o fim do reset.

  ligaOK1();
  Bios_EscreveSw(ADD_PORT, PP_IA);
  Bios_Escreve(DATA_PORT, pwMeuMAC[0]); //Passa bytes 1 e 2 do endere�o MAC
  Bios_EscreveSw(ADD_PORT, PP_IA + 2);  //Passa bytes 3 e 4 do endere�o MAC
  Bios_Escreve(DATA_PORT, pwMeuMAC[1]);
  Bios_EscreveSw(ADD_PORT, PP_IA + 4);  //Passa bytes 5 e 6 do endere�o MAC
  Bios_Escreve(DATA_PORT, pwMeuMAC[2]);
  ligaOK1();//passou
  Bios_EscreveSw(ADD_PORT, PP_LineCTL); //Termina de configurar o PHY.
  Bios_EscreveSw(DATA_PORT, SERIAL_RX_ON | SERIAL_TX_ON);
  Bios_EscreveSw(ADD_PORT, PP_RxCTL);
  Bios_EscreveSw(DATA_PORT, RX_OK_ACCEPT|RX_IA_ACCEPT|RX_BROADCAST_ACCEPT);
 
  //Inicializa a fila de buffers para transmiss�o.
  bEntraTxControl = 0;             //Aponta a entrada para o inicio da fila
                                   // indicando que a fila est� vasia.
    
  //Inicializa o buffer para transmiss�o de pacotes da camada rede.
  tBufArp.CabEth.wTipo = BUFFER_LIVRE; //Buffer para ARP livre.
  
  //Inicializa a ptArpTable.
  for(bApArpTable = 0; bApArpTable < TAM_ARP_TABLE; bApArpTable++)
  {
    ptArpTable[bApArpTable].pwIP[0] = BUFFER_LIVRE; //Inicializa todos
    ptArpTable[bApArpTable].pwIP[1] = BUFFER_LIVRE; // os IPs com zero.
  }
  bApArpTable = 0;                    //Aponta para o inicio da fila.
  
  //Inicializa controle de transmiss�o.
  tEmTxControl.bTempoVida = 0;        //Transmiss�o em repouso.
     	
}


///////////////////////////////////////////////////////////////////////////
// Rede_Relogio
// Interrup��o de rel�gio que ocorre a cada 1mS.
// Par�metros:  void
// Retorno: void
////////////////////////////////////////////////////////////////////////////

void Rede_Relogio(void)
{
  static BYTE sbTempo = 20;           //Conta 20mS.
  
  sbTempo --;                         //Conta tempo.
  if(!sbTempo)                        //Se decorreu 20mS.
  {
    //Conta tempo de espera do pacote no buffer.
    if(ptTxControl[0].bTempoVida)
      ptTxControl[0].bTempoVida --;
  
    //Conta tempo tentando transmitir pacote.
    if(tEmTxControl.bTempoVida > 1)   //Se ainda tem tempo para ser contado
      tEmTxControl.bTempoVida--;      //Conta tempo at� chegar a 1.

    sbTempo = 20;
  }
}


///////////////////////////////////////////////////////////////////////////
// Rede_CopiaMCUMem
// Copia um conjunto de bytes da mem�ria do MCU para a porta frame do PHY.
//  NOTA: - Um n�mero par de endere�os deve ser passado quando for at� o
//            fim da mem�ria.
// Par�metros:  pwSource - Endere�o para a mem�ria inicial do buffer.
//              wSize    - N�mero bytes a ser transferido.
// Retorno: void
///////////////////////////////////////////////////////////////////////////
void Rede_CopiaMCUMem(_IN_ WORD *pwSource, _IN_ WORD wSize)
{
  WORD *pwPtrAux = pwSource;
  
  while(wSize > 1)                //Varre todo o buffer.
  {
    Bios_Escreve(TX_FRAME_PORT, *pwPtrAux); //Envia os bytes.
    pwPtrAux++;
    wSize -= 2;
  }
  
  if(wSize)                       //Se sobrou um byte para ser transmitido.
    Bios_Escreve(TX_FRAME_PORT, *pwPtrAux); //Escreve o �ltimo byte, o PHY
                                  // ir� ignorar o byte de ordem mais alta.
}


///////////////////////////////////////////////////////////////////////////
// Rede_CopiaMCUMemSw
// Copia um conjunto de bytes da mem�ria do MCU para a porta frame do PHY 
//  executando um SWAPB na WORD antes de executar a c�pia.
//  NOTA: - Um n�mero par de endere�os deve ser passado quando for at� o
//            fim da mem�ria.
// Par�metros:  pwSource - Endere�o para a mem�ria inicial do buffer.
//              wSize    - N�mero bytes a ser transferido.
// Retorno: void
///////////////////////////////////////////////////////////////////////////
void Rede_CopiaMCUMemSw(_IN_ WORD *pwSource, _IN_ WORD wSize)
{
  WORD *pwPtrAux = pwSource;
  
  while(wSize > 1)                //Varre todo o buffer.
  {
    Bios_EscreveSw(TX_FRAME_PORT, *pwPtrAux); //Envia os bytes.
    pwPtrAux++;
    wSize -= 2;
  }
  
  if(wSize)                       //Se sobrou um byte para ser transmitido.
    Bios_EscreveSw(TX_FRAME_PORT, *pwPtrAux);//Escreve o �ltimo byte, o PHY
                                  // ir� ignorar o byte de ordem mais alta.
}


///////////////////////////////////////////////////////////////////////////
// Rede_CopiaBuffer
// Copia um conjunto de bytes do frame do PHY para a mem�ria.
//  NOTA: - Um n�mero par de endere�os deve ser passado quando for at� o
//            fim da mem�ria.
// Par�metros:  pvDest - Ponteiro de mem�dia onde ser� gravado os bytes.
//              wSize  - N�mero de bytes a serem gravados.
// Retorno: Word lido.
///////////////////////////////////////////////////////////////////////////
void Rede_CopiaBuffer(_OUT_ void *pvDest, _IN_ WORD wSize)
{
  WORD *pwPtrAux = (WORD*)pvDest;
  
  while(wSize > 1)                //Varre todo o buffer.
  {
    *pwPtrAux = Bios_LeFrame();   //L� copia o byte para mem�ria.
    pwPtrAux++;
    wSize -= 2;
  }
  
  if(wSize)                       //Verifica se faltou um byte....
    *pwPtrAux = Bios_LeFrame();   //L� o ultimo byte, o PHY
}                                 // retorna zero para o byte mais alto.


///////////////////////////////////////////////////////////////////////////
// Rede_Descarta
// Descarta um conjunto de bytes do frame do PHY.
//  NOTA: -- Ser� sempre descartado um n�mero par de bytes.
// Par�metros:  wSize - N�mero de bytes a serem descartados.
// Retorno: void.
///////////////////////////////////////////////////////////////////////////
void Rede_Descarta(_IN_ WORD wSize)
{
  while (wSize > 1)               //Descarta os bytes do buffer.
  {
    Bios_Le(RX_FRAME_PORT);
    wSize -= 2;
  }
}


///////////////////////////////////////////////////////////////////////////
// Rede_TxPronto
// Checa se o CS8900 est� pronto para aceitar o frame para ser transmitido.
// Par�metros:  void.
// Retorno: Se est� ou n�o pronto para receber o frame.
///////////////////////////////////////////////////////////////////////////
WORD Rede_TxPronto(void)
{
  Bios_EscreveSw(ADD_PORT, PP_BusST);

  return (Bios_Le(DATA_PORT) & READY_FOR_TX_NOW);
}


///////////////////////////////////////////////////////////////////////////
// Rede_Loop
// Loop infinito para rodar as rotinas peri�dicas.
// Par�metros:  void.
// Retorno: void.
///////////////////////////////////////////////////////////////////////////
void Rede_Loop(void)
{
  Rede_RecebePacote8900();        //Verifica se existe pacote no 8900.
  Rede_TransmitePacote8900();     //Verifica necessidade de transmitir 
                                  // pacotes no 8900.
}


///////////////////////////////////////////////////////////////////////////
// Rede_RecebePacote8900
// Verifica se existe pacote para ser recebido, caso haja l� o pacote,
//  trata o cabe�alho Ethernet e repassa para:
//        - Camada IP atravez da IP_Trata().
//        - Tratador de broadcast atravez da Rede_TrataBroadcast().
//        - Tratador de Arp atravez da Rede_TrataArp().
// Par�metros: Void
// Retorno: void
///////////////////////////////////////////////////////////////////////////
void Rede_RecebePacote8900(void)
{
  WORD wActRxEvent;               //Copia do registrador RxEvent do 8900.
  WORD wTamPacote;                //Armazena o tamanho do pacote recebido.

  Bios_EscreveSw(ADD_PORT, PP_RxEvent);//Aponta para o registrador RxEvent.
  wActRxEvent = Bios_Le(DATA_PORT); //L� o ultimo evento ocorrido.
 
  if(wActRxEvent & RX_OK)         //Se o pacote est� OK, ou seja, o CRC e 
  {           
	       					         // o tamanho est�o corretos.
    if(wActRxEvent & (RX_IA | RX_BROADCAST))//Se o pacote deve ser tratado.
    {

      //Os pr�ximos dois Words DEVEM ser lidos com o Byte alto primeiro, 
      // ver p�gina 2 da refer�ncia [10] do livro.
      Bios_LeHB1St(RX_FRAME_PORT);  //Iguinora RxStatus Word
      wTamPacote = Bios_LeHB1St(RX_FRAME_PORT); //L� o tamanho do pacote.
     
      if(wTamPacote < IP_TOTAL_SIZE)//Se o pacote que chegou est� dentro do
      {                             // tamanho correto.
		
        Rede_CopiaBuffer(&tBufEnt, wTamPacote); //Copia p/ buf de entrada.
        
        if(wActRxEvent & RX_BROADCAST)  //Se � mensagem de broadcast,
        {  
	                             // prov�vel ARP.
          Rede_TrataBroadcast();
        }
        else                      //Se n�o � mensagem de broadcast.
        {
		
          switch(tBufEnt.CabEth.wTipo)  //Checa tipo de frame.
          {
            case FRAME_IP:        //Se o frame � do tipo IP.
			
	        Arpt_SalvaMac(tBufEnt.Rede.IPData.CabIP.pwIPOrigem,//Armazena
                    tBufEnt.CabEth.pwMACOrigem); // IP e MAC na tabela ARP.
              IP_Trata(&tBufEnt.Rede.IPData); //Envia pacote p/ camada IP.
              break;
            case FRAME_ARP:
				                 //Se o frame � do tipo ARP.
              Rede_TrataArp(&tBufEnt.Rede.CabArp);//Envia pacote p/ trata_
                                                  // mento de resposta de 
                                                  // requisi��o de MAC.
              break;
		
           }
        }
      }
      else                        //Se o pacote � muito grande.
      {
        Rede_Descarta(wTamPacote); //Descarta o pacote.
      }
    }
  }

}


///////////////////////////////////////////////////////////////////////////
// Rede_TransmitePacote8900
// Verifica se existe pacote para ser transmitido, caso haja verifica se o
//  PHY est� com o buffer livre, quando livre envia o pacote.
//  NOTA: -Esta fun��o segue o diagrama da figura 30 na p�gina 101 da
//          refer�ncia [8] do livro.
//        -Observe que a variavel tEmTxControl.bTempoVida faz o papel de
//          contador de tempo e de estado.
// Par�metros: Void
// Retorno: void
///////////////////////////////////////////////////////////////////////////
void Rede_TransmitePacote8900(void)
{
  BYTE    bIndex;
  BYTE    bProx;
  BOOLEAN Achou = FALSE;
  
  if(!tEmTxControl.bTempoVida)    //Transmiss�o em repouso.
  {
    //Busca o buffer na fila p/ ser tratado (transmitido ou descartado).
    bIndex = 0;                   //Toma o inicio da fila.
    while(bIndex != bEntraTxControl)
    {
      if(!Achou)                  //Se ainda n�o achou o pr�ximo buffer
      {                           //  para ser tratado
        if(!(ptTxControl[bIndex].bFlag & TX_CTRL_FALTA_MAC) || //Se este
                                                  // buffer est� completo,
                                                  // pronto para ser tx
          (ptTxControl[bIndex].bTempoVida == 0))  // ou completou tempo
        {                                         // de vida m�ximo.
          //Passa o buffer para "em tratamento"
          tEmTxControl.pwBuf = ptTxControl[bIndex].pwBuf;
          tEmTxControl.wTam = ptTxControl[bIndex].wTam;
          tEmTxControl.pwCont = ptTxControl[bIndex].pwCont;
          tEmTxControl.wTamCont = ptTxControl[bIndex].wTamCont;
          tEmTxControl.wTipo = ptTxControl[bIndex].wTipo;
          tEmTxControl.pwIPDestino[0] = ptTxControl[bIndex].pwIPDestino[0];
          tEmTxControl.pwIPDestino[1] = ptTxControl[bIndex].pwIPDestino[1];
          tEmTxControl.bFlag = ptTxControl[bIndex].bFlag;
          tEmTxControl.bId = ptTxControl[bIndex].bId;
          
          bEntraTxControl--;      //Volta uma posi��o no final da fila.
            
          Achou = TRUE;           //Assinala que achou.
        }
        else                      //Se o buffer n�o est� pronto.
        {
          if((ptTxControl[bIndex].bFlag & TX_CTRL_ENVIAR_ARP) && //Se ainda
             (tBufArp.CabEth.wTipo == BUFFER_LIVRE)) // n�o enviou enviou
          {                                          // uma mensagem de ARP
                                                     // para este pacote
                                                     // e o buffer de
                                                     // ARP est� livre.
            //Cria o frame para requisi��o do MAC de destino.
            Rede_ConsultaARP(ptTxControl[bIndex].pwIPDestino);//Consulta 
                                                             // MAC destino
            Rede_Envia((WORD *)&tBufArp, ARP_TOTAL_SIZE, NULL,
                        0, NULL, FRAME_ARP, 0); 
			
            ptTxControl[bIndex].bFlag &= ~TX_CTRL_ENVIAR_ARP;
          }
          bIndex++;               //Pega o pr�ximo na fila de buffers.
        }
      }
      else                        //Se j� achou o pr�ximo a ser transmitido
      {
        bProx = bIndex + 1;       //Toma o pr�ximo da fila.
          
        //Desloca os outros itens da fila.
        ptTxControl[bIndex].pwBuf = ptTxControl[bProx].pwBuf;
        ptTxControl[bIndex].wTam = ptTxControl[bProx].wTam;
        ptTxControl[bIndex].pwCont = ptTxControl[bProx].pwCont;
        ptTxControl[bIndex].wTamCont = ptTxControl[bProx].wTamCont;
        ptTxControl[bIndex].wTipo = ptTxControl[bProx].wTipo;
        ptTxControl[bIndex].pwIPDestino[0] = 
                              ptTxControl[bProx].pwIPDestino[0];
        ptTxControl[bIndex].pwIPDestino[1] = 
                              ptTxControl[bProx].pwIPDestino[1];
        ptTxControl[bIndex].bFlag = ptTxControl[bProx].bFlag;
        ptTxControl[bIndex].bId = ptTxControl[bProx].bId;
        ptTxControl[bIndex].bTempoVida = ptTxControl[bProx].bTempoVida;
        
        bIndex = bProx;
      }
    };
  
    if(Achou)                     //Se achou um buffer para ser tratado.
    {
      if(!(tEmTxControl.bFlag&TX_CTRL_FALTA_MAC)) //Se o buffer achado deve
      {                                           // ser transmitido.
        //Solicita espa�o no buffer do PHY para transmitir.
        Bios_EscreveSw(TX_CMD_PORT, TX_START_ALL_BYTES);
        Bios_EscreveSw(TX_LEN_PORT, 
                       tEmTxControl.wTam + tEmTxControl.wTamCont);
  
        if(Rede_TxPronto())       //Se o espa�o est� dispon�vel.
        {
          Rede_CopiaMCUMem(tEmTxControl.pwBuf, //Copia o buffer da mem�ria
                           tEmTxControl.wTam); // do MCU para mem�ria do 
          Rede_CopiaMCUMemSw(tEmTxControl.pwCont, // PHY. Isto d� inicio a
                             tEmTxControl.wTamCont); // transmiss�o.
	
          Rede_TiraBufFila();     //Retira o buf da fila e avisa seu dono.
        }
        else                      //Se n�o est� pronto para transmitir.
          tEmTxControl.bTempoVida = REDE_TEMPO_PHY_MAX; //Inicia contagem 
                                                // de tempo e passa p/ o
                                                // estado de espra do PHY.
      }
      else                        //Se o buffer deve ser descartado.
      {
        Rede_TiraBufFila();       //Retira o buf da fila e avisa seu dono.
        //
        //Caso haja algum led de sinaliza��o de erro, este deve ser aceso.
        //
      }
    }
  }
  else                            //Aguardando libera��o do buffer.
  {
    if(Rede_TxPronto())           //Se liberou espa�o no buffer do PHY.
    {
      Rede_CopiaMCUMem(tEmTxControl.pwBuf,  //Copia o buffer da mem�ria 
                       tEmTxControl.wTam);  // do MCU para a mem�ria do 
      Rede_CopiaMCUMemSw(tEmTxControl.pwCont, // PHY. Isto d� inicio a
                         tEmTxControl.wTamCont); // transmiss�o.
      Rede_TiraBufFila();         //Retira o buf da fila e avisa seu dono.
      tEmTxControl.bTempoVida = 0;//volta para o estado de repouso.
    }
    else                          //Se n�o est� pronto para transmitir.
    {
      if(tEmTxControl.bTempoVida == 1) //Se esperou muito tempo.
      {
        tEmTxControl.bTempoVida = 0; //Volta para o estado de repouso.
        Rede_TiraBufFila();       //Retira o buf da fila e avisa seu dono.
        //
        //Caso haja algum led de sinaliza��o de erro, este deve ser aceso.
        //
      }
    }
  }
}


///////////////////////////////////////////////////////////////////////////
// Rede_TrataBroadcast.
// Trata pacotes enviados para broadcast, provavelmente solicita��o de MAC
//  via pacote ARP.
// Par�metros: Void
// Retorno: void
///////////////////////////////////////////////////////////////////////////
void Rede_TrataBroadcast(void)
{
  if((tBufEnt.CabEth.wTipo == FRAME_ARP)&& //Checa se o frame � do tipo ARP
    (tBufArp.CabEth.wTipo == BUFFER_LIVRE)&& //Se o buffer est� livre para 
                                             // transmitir a requisi��o.
    (tBufEnt.Rede.CabArp.wProtFis == HARDW_ETH10)&& //Frame Ethernet.
    (tBufEnt.Rede.CabArp.wProtLog == FRAME_IP)&&  //Protocolo l�gico IP.
    (tBufEnt.Rede.CabArp.wTamFisLog == IP_HLEN_PLEN)&& //Checa se os 
                                // tamanhos f�sico e l�gico est�o corretos.
    (tBufEnt.Rede.CabArp.wOpcode == OP_ARP_REQUEST)&& //Se � realmente uma
                                                     // solicita��o de MAC.
    (tBufEnt.Rede.CabArp.pwIPAlvo[0] == pwMeuIP[0])&&//Se est� consultando
    (tBufEnt.Rede.CabArp.pwIPAlvo[1] == pwMeuIP[1])) // o meu IP.
  {
    //Cria o frame para responder a solicita��o com o meu MAC.
  
    // Ethernet
    tBufArp.CabEth.pwMACDestino[0] = tBufEnt.Rede.CabArp.pwMAC[0];
    tBufArp.CabEth.pwMACDestino[1] = tBufEnt.Rede.CabArp.pwMAC[1];
    tBufArp.CabEth.pwMACDestino[2] = tBufEnt.Rede.CabArp.pwMAC[2];
  
    // ARP
    tBufArp.CabArp.wProtFis = HARDW_ETH10;
    tBufArp.CabArp.wProtLog = FRAME_IP;
    tBufArp.CabArp.wTamFisLog = IP_HLEN_PLEN;
    tBufArp.CabArp.wOpcode = OP_ARP_ANSWER;
  
    tBufArp.CabArp.pwMAC[0] = pwMeuMAC[0];
    tBufArp.CabArp.pwMAC[1] = pwMeuMAC[1];
    tBufArp.CabArp.pwMAC[2] = pwMeuMAC[2];
  
    tBufArp.CabArp.pwIP[0] = pwMeuIP[0];
    tBufArp.CabArp.pwIP[1] = pwMeuIP[1];
  
    tBufArp.CabArp.pwMACAlvo[0] = tBufEnt.Rede.CabArp.pwMAC[0];
    tBufArp.CabArp.pwMACAlvo[1] = tBufEnt.Rede.CabArp.pwMAC[1];
    tBufArp.CabArp.pwMACAlvo[2] = tBufEnt.Rede.CabArp.pwMAC[2];
  
    tBufArp.CabArp.pwIPAlvo[0] = tBufEnt.Rede.CabArp.pwIP[0];
    tBufArp.CabArp.pwIPAlvo[1] = tBufEnt.Rede.CabArp.pwIP[1];

    Rede_Envia((WORD *)&tBufArp, ARP_TOTAL_SIZE, NULL, 
                0, NULL, FRAME_ARP, 0);
  }
}


///////////////////////////////////////////////////////////////////////////
// Rede_Envia
// Recebe os pacotes que devem ser enviados para a rede.
//    NOTA: - Deve-se tomar muito cuidado ao alterar esta fun��o,
//             pois ela � recursiva.
// Par�metros:  pwBuf   - Aponta para o buffer que deve ser transmitido.
//              wTam    - N�mero de bytes que devem ser transmitido.
//              pwCont  - Aponta para a continua��o do buffer.
//              wTamCont- N�mero de bytes da continua��o.
//              pwIPDest- IP de destino do pacote.
//              wTipo   - Tipo de frame que ser� transmitido.
//              bId     - Identificador do buffer dentro da camada.
// Retorno: void
///////////////////////////////////////////////////////////////////////////
void Rede_Envia(_IN_ WORD *pwBuf,  _IN_ WORD wTam, 
                _IN_ WORD *pwCont, _IN_ WORD wTamCont, 
                _IN_ WORD *pwIPDest, _IN_ WORD wTipo, _IN_ BYTE bId)
{
  BYTE bFlagAux = 0x00;
  
  //N�o vamos verificar se existe espa�o livre na fila uma vez que est� foi
  //  dimencionada para caber todos os buffers dispon�veis no sistema.
  //  Apenas tome cuidade de redimencionar a fila sempre que for adicionado
  //  novos buffers.
	
  if(pwIPDest)                    //Se especificou o IP, busca o MAC.
  {
    //Preenche MAC de destino consultando na tabela ARP.
    if(!Arpt_ConsultaMac(pwIPDest,                              
                         (*(t_Eth*)pwBuf).CabEth.pwMACDestino))
    {                             //Se n�o achou o MAC destino.
      //Seta flag indicando que est� aguardando resposta da consulta.
      bFlagAux |= TX_CTRL_FALTA_MAC; 

      //Gera mensagem de cosulta de ARP Table na rede.
      if(tBufArp.CabEth.wTipo == BUFFER_LIVRE)//Se o buffer est� livre para
      {                                       // transmitir a requisi��o.
        //Cria o frame para requisi��o do MAC de destino.
        Rede_ConsultaARP(pwIPDest);  
        
        //Ponto onde a fun��o chama ela mesma (RERCUSIVA!!!).
        Rede_Envia((WORD *)&tBufArp, ARP_TOTAL_SIZE, NULL, //Envia frame
                    0, NULL, FRAME_ARP, 0);                // criado.
      }
      else
        bFlagAux |= TX_CTRL_ENVIAR_ARP;
    }
  }
    
  //Preenche MAC de origem.
  (*(t_Eth*)pwBuf).CabEth.pwMACOrigem[0] = pwMeuMAC[0];
  (*(t_Eth*)pwBuf).CabEth.pwMACOrigem[1] = pwMeuMAC[1];
  (*(t_Eth*)pwBuf).CabEth.pwMACOrigem[2] = pwMeuMAC[2];

  //Peenche tipo de pacote.
  (*(t_Eth*)pwBuf).CabEth.wTipo = wTipo;

  //Guarda os dados na fila.
  ptTxControl[bEntraTxControl].pwBuf = pwBuf;
  ptTxControl[bEntraTxControl].wTam = wTam;
  ptTxControl[bEntraTxControl].pwCont = pwCont;
  ptTxControl[bEntraTxControl].wTamCont = wTamCont;
  ptTxControl[bEntraTxControl].wTipo = wTipo;
  ptTxControl[bEntraTxControl].pwIPDestino[0] = pwIPDest[0];
  ptTxControl[bEntraTxControl].pwIPDestino[1] = pwIPDest[1];
  ptTxControl[bEntraTxControl].bFlag = bFlagAux;
  ptTxControl[bEntraTxControl].bId = bId;
  ptTxControl[bEntraTxControl].bTempoVida = REDE_TEMPO_VIDA_MAX;
  
  //Acha a pr�xima posi��o livre na fila.
  bEntraTxControl++;
}


///////////////////////////////////////////////////////////////////////////
// Rede_TiraBufFila
// Retira o buffer da fila e avisa seu 'dono'.
// Par�metros:  void
// Retorno: void
///////////////////////////////////////////////////////////////////////////
void Rede_TiraBufFila(void)
{
  switch(tEmTxControl.wTipo)            //Verifica a qual camada o 
  {                                     // buffer pertence.
    case FRAME_ARP:
      Rede_BufEnviado(tEmTxControl.bId);
      break;
    case FRAME_IP:
      IP_BufEnviado(tEmTxControl.bId);
      break;
  }
}                 


///////////////////////////////////////////////////////////////////////////
// Rede_BufEnviado
// Libera o buffer que acaba de ser enviado.
// Par�metros:  bId     - Identificador do buffer dentro da camada.
// Retorno: void
///////////////////////////////////////////////////////////////////////////
void Rede_BufEnviado(_IN_ BYTE bId)
{
  tBufArp.CabEth.wTipo = BUFFER_LIVRE;
}


///////////////////////////////////////////////////////////////////////////
// Rede_ConsultaARP
// Envia mensagem de consulta de endere�o MAC para um determinado IP.
// Par�metros:  pwIPAlvo - Endere�o IP a ser consultado.
// Retorno: void
///////////////////////////////////////////////////////////////////////////
void Rede_ConsultaARP(_IN_ const WORD *pwIPAlvo)
{
  // Ethernet
  tBufArp.CabEth.pwMACDestino[0] = MAC_BROADCAST;
  tBufArp.CabEth.pwMACDestino[1] = MAC_BROADCAST;
  tBufArp.CabEth.pwMACDestino[2] = MAC_BROADCAST;
  
  // ARP
  tBufArp.CabArp.wProtFis = HARDW_ETH10;
  tBufArp.CabArp.wProtLog = FRAME_IP;
  tBufArp.CabArp.wTamFisLog = IP_HLEN_PLEN;
  tBufArp.CabArp.wOpcode = OP_ARP_REQUEST;

  tBufArp.CabArp.pwMAC[0] = pwMeuMAC[0];
  tBufArp.CabArp.pwMAC[1] = pwMeuMAC[1];
  tBufArp.CabArp.pwMAC[2] = pwMeuMAC[2];

  tBufArp.CabArp.pwIP[0] = pwMeuIP[0];
  tBufArp.CabArp.pwIP[1] = pwMeuIP[1];

  tBufArp.CabArp.pwMACAlvo[0] = 0x0000;
  tBufArp.CabArp.pwMACAlvo[1] = 0x0000;
  tBufArp.CabArp.pwMACAlvo[2] = 0x0000;

  tBufArp.CabArp.pwIPAlvo[0] = pwIPAlvo[0];
  tBufArp.CabArp.pwIPAlvo[1] = pwIPAlvo[1];
}


///////////////////////////////////////////////////////////////////////////
// Rede_TrataArp
// Envia pacote para tratamento de resposta de requisi��o de endere�o MAC.
// Par�metros:  pCabArp - Endere�o IP a ser consultado.
// Retorno: void
///////////////////////////////////////////////////////////////////////////
void Rede_TrataArp(_IN_ t_CabArp *pCabArp)
{
  BYTE bIndex;
  
  if((*pCabArp).wOpcode == OP_ARP_ANSWER)   //Se � resposta da requisi��o.
  {
    if(((*pCabArp).wProtFis == HARDW_ETH10) && //Se � protocolo Ethernet
       ((*pCabArp).wProtLog == FRAME_IP) && // e se o protocolo l�gico � IP
       ((*pCabArp).wTamFisLog == IP_HLEN_PLEN)) // e se os tamanhos est�o
    {                                           // corretos.
      //Armazena o IP e o MAC na ARP Table.
      Arpt_SalvaMac((*pCabArp).pwIP, (*pCabArp).pwMAC);
      
      //Tenta achar na fila de buffers quem solicitou este MAC.
      bIndex = 0;                             //Toma o inicio da fila.
      while(bIndex != bEntraTxControl)
      {
        if(ptTxControl[bIndex].bFlag & TX_CTRL_FALTA_MAC) //Se este buffer
        {                                             // n�o est� completo.
          //Se estava procurando por este IP.
          if((ptTxControl[bIndex].pwIPDestino[0] == (*pCabArp).pwIP[0]) &&
             (ptTxControl[bIndex].pwIPDestino[1] == (*pCabArp).pwIP[1]))
          {
            //Coloca o MAC no cabe�alho ethernet do pacote.
            (*(t_Eth*)ptTxControl[bIndex].pwBuf).CabEth.pwMACDestino[0] = 
                                                    (*pCabArp).pwMAC[0];
            (*(t_Eth*)ptTxControl[bIndex].pwBuf).CabEth.pwMACDestino[1] = 
                                                    (*pCabArp).pwMAC[1];
            (*(t_Eth*)ptTxControl[bIndex].pwBuf).CabEth.pwMACDestino[2] = 
                                                    (*pCabArp).pwMAC[2];
            
            //Assinala que o pacote est� pronto para ser transmitido.
            ptTxControl[bIndex].bFlag &= 
                                 ~(TX_CTRL_FALTA_MAC | TX_CTRL_ENVIAR_ARP);
          }
        }
        bIndex++;                     //Pega o pr�ximo na fila de buffers.
      };
    }
  }
  else                                //Se est� solicitando MAC.
  {
    Rede_TrataBroadcast();
  }
}


///////////////////////////////////////////////////////////////////////////
// Arpt_SalvaMac
// Guarda o novo MAC na tabela ARP.
// Par�metros:  pwIP  - Endere�o IP a ser armazenado.
//              pwMAC - Endere�o MAC a ser armazenado.
// Retorno: void
///////////////////////////////////////////////////////////////////////////
void Arpt_SalvaMac(_IN_ WORD *pwIP, _IN_ WORD *pwMAC)
{
  BYTE bApArpTableAux;
  BOOLEAN Achou = FALSE;

  for(bApArpTableAux = 0; bApArpTableAux<TAM_ARP_TABLE; bApArpTableAux++)
  {
    if((ptArpTable[bApArpTableAux].pwIP[0] == pwIP[0])&& //Se existe este
       (ptArpTable[bApArpTableAux].pwIP[1] == pwIP[1]))  // IP na tabela.
    {
      Achou = TRUE;           //Assinala que j� existe.
      break;                  //Encerra a busca.
    }
  }
  if(!Achou)                  //Se n�o existe na tabela.
  {
    ptArpTable[bApArpTable].pwIP[0] = pwIP[0];   //Armazena o IP na posi��o
    ptArpTable[bApArpTable].pwIP[1] = pwIP[1];   // mais antiga da tabela.
    ptArpTable[bApArpTable].pwMAC[0] = pwMAC[0]; //Armazena o MAC.
    ptArpTable[bApArpTable].pwMAC[1] = pwMAC[1];
    ptArpTable[bApArpTable].pwMAC[2] = pwMAC[2];

    bApArpTable++;            //Aponta pr�xima posi��o a ser sobrescrevida.
    if(bApArpTable == TAM_ARP_TABLE) //Se chegou ao fim da tabela.
      bApArpTable = 0;        //Volta para o inicio.
  }
}


///////////////////////////////////////////////////////////////////////////
// Arpt_ConsultaMac
// Dado um IP retorna o MAC correspondente.
// Par�metros:  pwIP  - Endere�o IP a ser armazenado.
//              pwMAC - Endere�o MAC correspondente.
// Retorno: TRUE se achou o IP na tabela.
//          FALSE se n�o acou o IP.
///////////////////////////////////////////////////////////////////////////
BOOLEAN Arpt_ConsultaMac(_IN_ WORD *pwIP, _OUT_ WORD *pwMAC)
{
  BYTE bApArpTableAux;
  BOOLEAN Achou = FALSE;

  for(bApArpTableAux = 0; bApArpTableAux<TAM_ARP_TABLE; bApArpTableAux++)
  {
    if((ptArpTable[bApArpTableAux].pwIP[0] == pwIP[0]) && //Se existe este
       (ptArpTable[bApArpTableAux].pwIP[1] == pwIP[1]))   //IP na tabela.
    {
      pwMAC[0] = ptArpTable[bApArpTableAux].pwMAC[0];  //Armazena o MAC.
      pwMAC[1] = ptArpTable[bApArpTableAux].pwMAC[1];
      pwMAC[2] = ptArpTable[bApArpTableAux].pwMAC[2];
      Achou = TRUE;                   //Assinala que achou o MAC.
      break;                          //Encerra a busca.
    }
  }

  return Achou;
}




