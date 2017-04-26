#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <regex.h>
//AGCUGUGUAGAUAUGUGAGAGGUACAGUGUAAAAGAGACUAGUGUGGGGAGAGGUGA
//AGCUGUACAACUAGUGA



int main()
{
    char* pattern = ".*[^GUGU.*AGAG?].*";
    regex_t regex;
    regmatch_t pmatch[100];
    const size_t nmatch = 200;
    if ( regcomp( &regex, pattern, 0 ) != 0)
    {
        printf("Error compile regular expression\n");
        return 1;
    }
    printf("Input strand: ");
    char input[200];
    char output[200];
    char c;
    int count = 0;
    while ( 1 )
    {
        c = getchar();
        if ( c == EOF || c == '\n')
        {
            input[count] ='\0';
            break;
        }
        input[count] = c;
        count++;
    }
    int result = regexec(&regex, input, nmatch, pmatch, 0);
    //printf("%d\n eo is %d so is %d", result,pmatch[0].rm_eo, pmatch[0].rm_so);
    printf("%d\n", result );
    int mark = 0;
    printf("reached here%lld %lld \n", pmatch[1].rm_so, pmatch[1].rm_eo);
    for ( int i = pmatch[0].rm_so; i <= pmatch[0].rm_eo; i++ )
    {
        output[mark] = input[i];
        mark++;
    }
    printf("%s\n", output);
    regfree( &regex );
}
/*void substring( char **token, char* input, int index);
int main()
{
    printf("Input strand: ");
    char input[100];
    char output[100];
    int c, count = 0;
    while ( 1 )
    {
        c = getchar();
        if ( c == EOF || c == '\n')
        {
            input[count] = '\0';
            break;
        }
        input[count] = c;
        count++;
    }
    char* token = malloc( 5 * sizeof( char ) );
    token[4] = '\0';
    int start = 0, end = 0; 
    for ( int i = 0; i < count; i++ )
    {
        if ( i + 3 < count )
        {
            substring( &token, input, i );     
            if ( strcmp ( token, "GUGU") == 0 )
            {
                start = i;
            }
            if( strcmp( token, "AGAG") == 0 )
            {
                end = i + 4;
            }
        }
        else if ( i + 3 >= count )
        {
            break;
        }
    }
    int mark = 0;
    for ( int i = 0; i < start; i++ )
    {
        output[i] = input[i];
        mark++;
    }
    for ( int i = end; i < count; i++ )
    {
        output[mark] = input[i];
        mark++;;
    }
    output[mark] = '\0';

    fflush(stdout);
    fflush(stdin);
    printf("\nOutput is %s\n", output);
    free( token );

}

void substring(char** token, char* input, int index )
{
    for ( int i = 0; i < 4; i++ )
    {
        //printf("%c\n",input[index + i]);
       ( *token)[i] = input[index + i];
    }
}*/