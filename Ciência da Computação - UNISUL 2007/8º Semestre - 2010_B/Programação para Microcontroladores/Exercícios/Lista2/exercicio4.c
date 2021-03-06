#include <16F877A.h>
#fuses XT, PROTECT
#use delay(clock=4Mhz)
#use rs232(baud=9600, xmit=pin_c6, rcv=pin_c7)


#define DELAY 5000

void main() {

   int VL_PORTB = 0;
   int VL_PORTD = 0;

   while(true) {
  
      VL_PORTB = input_b();
      VL_PORTD = input_d();
      printf("PORT_B: %d\r\n", VL_PORTB);
      printf("PORT_D: %d\r\n", VL_PORTD);
      printf("PORT_B + PORT_D: %d\r\n", (VL_PORTB+VL_PORTD));
      delay_ms(DELAY);
   
   }

}
