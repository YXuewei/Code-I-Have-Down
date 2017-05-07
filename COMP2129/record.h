#ifndef RECORD_H
#define RECORD_H

#include <math.h>
#include <stdio.h>
#include <stdint.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <stdbool.h>

void command(char* command, char* arg);
void command_quit(char* arg);
void command_display(char* arg);
void command_fastfwd(char* arg);
void command_rewind(char* arg);
void record_display();
void record_fastfwd(int n);
void record_rewind(int n);
void record_extract(int argc, char** argv);

typedef struct command_t command_t;
typedef struct move_t move_t;
typedef struct grid_t grid_t;

struct command_t {
	char* command;
	void (*execute)(char*);
};

struct move_t {
	uint8_t x;
	uint8_t y;
	uint16_t padding;
};

struct grid_t {
	uint8_t atoms;
	uint8_t player_id;
	uint16_t padding;
};

#endif