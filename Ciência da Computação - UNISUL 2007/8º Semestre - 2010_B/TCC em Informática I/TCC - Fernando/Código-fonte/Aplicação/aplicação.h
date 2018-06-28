/*************************************************************************\
 * Nome: Aplica��o.h                                                     *
 * Vers�o: 1.0                                                           *
 * Data: Setembro de 2003                                                *
 * Autor: Marcos Perez Mokarzel                                          *
 * Fun��o: Declara��o de vari�veis e constantes da camada aplica��o.     *
 * Hist�rico:                                                            *
\*************************************************************************/


//Estados da transmiss�o da p�gina.
#define APP_TX_INICIO 0x00
#define APP_TX_TEMP   0x01
#define APP_TX_DELAY  0x02
#define APP_TX_SYS    0x03
#define APP_FIM_PAG   0x04

//Tempos do comando PING.
#define APP_PING_INTERVALO  30000
#define APP_PING_DELAY_MAX  3000


//Defini��o das contantes.
extern const char pszInicio[];
extern const char pszFim[];

