#include <string.h>
#include <stdlib.h>
#include <stdbool.h>
#include <stdio.h>

bool check(int c)
{
    if ((c >= 48 && c <= 57) || (c >= 65 && c <= 90) || (c >= 97 && c <= 122))
    {
        return true;
    }
    return false;
}

int main()
{
    printf("Enter line: ");
    char input[100];
    int count, c;
    count = 0;
    while ((c = getchar()) != '\n')
    {
        if (check(c))
        {
            if (c != '\n')
            {
                input[count++] = c;
            }
            else
            {
                input[count++] = '\0';
                break;
            }
        }
    }
    printf("Enter anagram: ");
    char input2[100];
    int count2 = 0;
    while ((c = getchar()) != '\n')
    {
        if (check(c))
        {
            if (c != '\n')
            {
                input2[count2++] = c;
            }
            else
            {
                input[count2++] = '\0';
                break;
            }
        }
    }

    printf("\n");
    int count3 = 0;
    if (count != count2)
    {
        printf("Not an anagram.\n");
        exit(0);
    }
    for (int i = 0; i < count2; i++)
    {
        for (int j = 0; j < count; j++)
        {
            if (input[j] == 0)
            {
                continue;
            }
            else if ( ( input2[i] == input[j] ) || ( (int)input2[i] - (int)input[j] == 32 ) || ( (int) input2[i] - (int)input[j] == -32) )
            {
               count3++;
               input[j] = 0;
               continue; 
            }
        }
    }
    if ( count3 != count)
    {
        printf("Anagram!\n");
    }
    printf("Anagram!\n");
}