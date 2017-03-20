#include <stdio.h>

main(){
    float celsius, farhm;
    int increase, limit;
    
    limit = 40;
    increase = 5;
    celsius = 0;

    printf( "Celsius\tFarhm\n"); 
    while ( celsius < limit ){
        farhm = ( celsius * 1.8 ) + 32;
        printf("%3.0f\t%3.1f\n", celsius, farhm);
        celsius += increase;
    }
}