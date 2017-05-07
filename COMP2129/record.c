#include "record.h"

#define MAX_ARG 128
#define MAX_LINE 256
#define FILE_MAGIC 128
#define NUM_COMMANDS 3

bool quit = false;
char* colours[] = { "RED", "GREEN", "PURPLE", "BLUE", "YELLOW", "WHITE" };

command_t commands[NUM_COMMANDS] = {
	{ .command = "FASTFWD", .execute = command_fastfwd },
	{ .command = "REWIND", .execute = command_rewind },
	{ .command = "QUIT", .execute = command_quit },
};

///////////////////////
// IGNORE ABOVE HERE //
///////////////////////
int n_player;
int magic;
int width;
int height;
int move_count;
int current_move = 0;
move_t move[255];
grid_t board[50][50];
char *array[] = {"RED","GREEN","PURPLE","BLUE","YELLOW","WHITE"};
void record_fastfwd(int n) {
	if ( n < 0 )
	{
		printf("Move Does Not Exist\n");
		return;
	}
	current_move += n;
	n = current_move;
	if(n > move_count - 1 || n < 0)
	{
		printf("Move Does Not Exist\n");
		return;
	}
	else
	{
	int w = move[n].x;
	int h = move[n].y;
	int colour = board[h][w].player_id;
	printf("%s -> %d %d\n", array[colour],w,h);
	}
}

void record_rewind(int n) {
	if ( n < 0 )
	{
		printf("Move Does Not Exist\n");
		return;
	}
	current_move -= n;
	n = current_move;
	if ( n < 0 )
	{
		printf("Move Does Not Exist\n");
		return ;
	}
	int w = move[n].x;
	int h = move[n].y;
	int colour = board[h][w].player_id;
	printf("%s-> %d %d\n", array[colour],w,h);
}

void record_extract(int argc, char** argv) {
	if (argc != 2)
	{
		printf("448\n");
		exit(1);
	}else
	{
		FILE *fp;
		fp = fopen(argv[1],"r");
		if (fp == NULL)
		{
			printf("448\n");
			exit(1);
		}
		fread(&width,1,1,fp);
		fread(&height, 1,1,fp);
		fread(&n_player,1,1,fp);
		fread(&magic,1,1,fp);
		if(magic != 128)
		{
			printf("448\n");
			exit(1);
		}
		else if(n_player > 6 || n_player < 2)
		{
			printf("448\n");
			exit(1);
		}
		else
		{
			printf("File Loaded\n");
			for ( int i = 0; i < height; i++ )
			{
				for (int j = 0; j < width; j++)
				{
					fread(&(board[i][j].atoms),1,1,fp);
					fread(&(board[i][j].player_id),1,1,fp);
					fread(&(board[i][j].padding),2,1,fp);
				}
			}
			move_count = 0;
			while(feof(fp) != 1)
			{
				fread(&(move[move_count].x),1,1,fp);
				fread(&(move[move_count].y),1,1,fp);
				fread(&(move[move_count].padding),2,1,fp);
				move_count++;
			}
			fclose(fp);
		}
	}
}

void cleanup() {
	for (int i = 0;i < height; i++)
	{
		free(board[i]);
	}
	free(board);
}

///////////////////////
// IGNORE BELOW HERE //
///////////////////////

void command_fastfwd(char* arg) {
	int n = strtol(arg, NULL, 10);
	record_fastfwd(n);
}

void command_rewind(char* arg) {
	int n = strtol(arg, NULL, 10);
	record_rewind(n);
}

void command_quit(char* arg) {
	quit = true;
}

void parse_command(char* command, char* arg) {
	for (int i = 0; i < NUM_COMMANDS; i++) {
		if (strcmp(commands[i].command, command) == 0) {
			commands[i].execute(arg);
		}
	}
}

int main(int argc, char** argv) {

	char buffer[MAX_LINE];
	char command[MAX_ARG];
	char arg[MAX_ARG];
	record_extract(argc, argv);
	while (!quit) {
		fgets(buffer, MAX_LINE, stdin);
		sscanf(buffer, "%s %s", command, arg);
		parse_command(command, arg);
	}
	return 0;
}