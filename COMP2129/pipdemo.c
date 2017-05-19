#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include <sys/wait.h>

int main(void) {

	int pipefd[2];
	if (pipe(pipefd) < 0) {
		perror("unable to create pipe");
	}

	pid_t pid = fork();
	if (pid < 0) {
		perror("unable to fork");
	}

	if (pid == 0) {
		char buffer[20];
		read(pipefd[0], buffer, 20);
		if (strcmp(buffer, "vote for trump") == 0) {
			puts("high energy");
		} else {
			puts("wat");
		}
	} else {
		write(pipefd[1], "vote for trump", 20);
		wait(NULL);
	}

	return 0;
}
