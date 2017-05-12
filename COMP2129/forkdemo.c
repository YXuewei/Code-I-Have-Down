#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/types.h>

int main(void)
{
    int n = 4;
    printf("parent process ID of parrent is %d\n",getppid() );
    puts("about to fork");
    pid_t pid = fork();
    if (pid < 0)
    {
        perror("unable to fork");
        return 1;
    }
    if (pid == 0)
    {
        puts("child");
        n++;
        //sleep(1);
    }
    else
    {
        wait(NULL);
        puts("parent");
        n *= 2;
    }
    printf("fork returned %d, n is %d\n", getpid(), n);
    return 0;
}