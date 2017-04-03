#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include "ccrlklist.h"

//You may add/modify/remove structs

typedef struct pixel_t pixel_t;

struct pixel_t {
	uint8_t red;
	uint8_t green;
	uint8_t blue;
	uint8_t empty;
    int index;
    pixel_t *next;
    pixel_t *prev;
};

void list_init( pixel_t *head );
void append( pixel_t *head, pixel_t n);
void get_element( pixel_t *n, char value );// R,G,B represents color, I represent Index

int main(int argc, char* argv[]) {
	
	if ( argc == 1)
    {
        printf("No Filename Specified\n");
        return 1;
    }

    FILE *fp;
    fp = fopen( argv[1], "r");
    if ( fp == NULL )
    {
        printf("File Does Not Exist\n");
        return 1;
    }

    uint32_t width;
    uint32_t height;
    uint16_t magic;
    fread( &width, 4, 1, fp);
    fread( &height, 4, 1, fp);
    fread( &magic, 2, 1, fp);

    if ( magic != 60535)
    {
        printf( "Invalid Image Header\n");
        return 1;
    }

    int array[ height ][ width ];
    

	return 0;
}

void list_init( pixel_t *head )
{
    head->next = NULL;
    head->prev = NULL;
}

void appened( pixel_t *head, pixel_t n )
{
     if ( head->next == NULL )
    {
        head->next = n;
        n->prev = head;
    }
    else
    {
        pixel_t *temp;
        temp = head->next;
        head->next = n;
        n->prev = head;
        n->next = temp;
    }
}

int get_element( pixel_t n, char value )
{
    switch ( value )
    {
        case 'R':
            return n.Red;
        case 'B':
            return n.blue;
        case 'G':
            return n.green;
        case 'I':
            return n.index;
        default:
            return -1;
    }
}