#include <stdio.h>
#define MAX 30

int strrindex(char s[], char t);

int main()
{
    char line[MAX];
    char search;
    int p;

    printf("Enter a line\n");
    scanf("%s", line);
    printf("Enter a char that would like to search\n");
    getchar();
    scanf("%c", &search);

    p = strrindex(line, search);
    printf("%d", p);
}

int strrindex(char s[], char t)
{
    int position = -1;
    for (int i = 0; s[i] != '\0'; ++i)
    {
        if (s[i] == t)
        {
            position = i;
        }
    }
    return position;
}