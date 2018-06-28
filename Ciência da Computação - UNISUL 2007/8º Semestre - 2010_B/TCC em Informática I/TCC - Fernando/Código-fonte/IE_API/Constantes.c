/*************************************************************************\
 * Nome: Constates.c                                                     *
 * Vers�o: 1.0                                                           *
 * Data: Setembro de 2003                                                *
 * Autor: Marcos Perez Mokarzel                                          *
 * Fun��o: Armazenas as constantes necess�rias para o funcionamento      *
 *           do protocolo.                                               *
 * Hist�rico:                                                            *
\*************************************************************************/

#include "..\IE_API\Diretivas.h"



/****************************\
 * CAMADA REDE              *
\****************************/

const WORD pwMeuMAC[] = {(WORD)MAC_2 + ((WORD)MAC_1 << 8),
                         (WORD)MAC_4 + ((WORD)MAC_3 << 8),
                         (WORD)MAC_6 + ((WORD)MAC_5 << 8)};


/****************************\
 * CAMADA IP                *
\****************************/

//Constantes IP, Submask e Gateway.
const WORD pwMeuIP[] = {(WORD)IP_2 + ((WORD)IP_1 << 8), 
                        (WORD)IP_4 + ((WORD)IP_3 << 8)};
const WORD pwMasc[] = {MASC_12 , MASC_34};
const WORD pwGateway[] = {(WORD)GWIP_2 + ((WORD)GWIP_1 << 8), 
                          (WORD)GWIP_4 + ((WORD)GWIP_3 << 8)};



/****************************\
 * CAMADA TRANSPORTE        *
\****************************/

//Portas abertas para comunica��o TCP/IP.
const WORD TCP_PrtAberta[] = {80,   //Comunica��o HTTP. 
                              0};     //Fim das portas.
