#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <regex.h>
//AGCUGUGUAGAUAUGUGAGAGGUACAGUGUAAAAGAGACUAGUGUGGGGAGAGGUGA
//AGCUGUACAACUAGUGA



int main()
{
    char* pattern = "GUGU.*AGAG";
    regex_t regex;
    regmatch_t pmatch[500];
    const size_t nmatch = 200;
    if ( regcomp( &regex, pattern, REG_EXTENDED ) != 0)
    {
        printf("Error compile regular expression\n");
        return 1;
    }
    printf("Input strand: ");
    char input[300];
    char output[300];
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
    int result = regexec(&regex, input, nmatch, pmatch, 0 );
    //printf("%d\n eo is %d so is %d", result,pmatch[0].rm_eo, pmatch[0].rm_so);
    //printf("%d\n", result );
    if ( result == REG_NOMATCH)
    {
        printf("result is %d\n", result);
        regfree( &regex );
        return 0;
    }
    int mark = 0;
    //printf("reached here %d %d \n", pmatch[0].rm_so, pmatch[0].rm_eo);
    for ( int i = 0; i <= pmatch[0].rm_so - 1; i++ )
    {
        output[mark] = input[i];
        //printf("mark is %d i is %d\n", mark, i );
        mark++;
    }
    printf("count is %d, end is %d\n", count, pmatch[0].rm_eo);
    for ( int i = pmatch[0].rm_eo; i < count; i++ )
    {
        output[mark] = input[i];
        mark++;
    }
    //printf("mark is %d\n", mark );
    output[mark] = '\0';
    printf("%s\n", output);
    //printf("reached here\n");
    regfree( &regex );
    return 0;
}