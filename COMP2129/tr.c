#include <stdio.h>

int main(int argc, int *argv[])
{
    printf("%s", &argv[1]);
    argv++;
    printf("%s", &argv[-1]);
}