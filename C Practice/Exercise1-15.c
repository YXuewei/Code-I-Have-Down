#include <stdio.h>
#define LOW 0
#define UPPER 300
#define STEP 20

void conversion( int celsius, int fahr ){
    fahr = LOW;
    while ( fahr <= UPPER ){
        celsius = 5 * ( fahr - 32 ) / 9;
        printf( "%d\t%d\n", fahr, celsius );
        fahr = fahr + STEP;
    }
}

main(){
    int cel = 0;
    int fahr = 0;
    conversion( cel, fahr );
}