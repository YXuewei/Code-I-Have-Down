#include <stdio.h>
#include <string.h>

int main(){

    char line[100];

    printf("Enter a line\n");
    scanf("%s",line);

    int i , len;
    len = strlen( line );
    char rever[len];
    
    for ( i = 0; i < len;++i){
        rever[i] = line[ len - i - 1];
    }

    printf("%s", rever);
}