#include <time.h>
#include <stdlib.h>
#include <pthread.h>
#include <stdbool.h>
#include "worm.h"

size_t g_nthreads = 4;

// Returns result set containing book with given id.
result_t* find_book(book_t* nodes, size_t count, size_t book_id) 
{	
	bool found = false;
	result_t *result = malloc( sizeof ( struct result_t ) );
	result->elements = malloc( sizeof ( struct book_t ) );
	result->n_elements = 0;
	int i = 0;
	for (; i < count; i ++ )
	{
		if ( nodes[i].id == book_id )
		{
			found = true;
			break;
		}
	}

	if ( found == true )
	{
		*(result->elements) = &nodes[i];
		result->n_elements = 1;
	}
	return result;
}

// Returns result set containing books by given author.
result_t* find_books_by_author(book_t* nodes, size_t count, size_t author_id) 
{
	result_t *result = malloc( sizeof( struct result_t ));
	bool found = false;
	int n;
	int i = 0;
	for (; i < count; i++ )
	{
		if ( nodes[i].author_id == author_id )
		{
			found = true;
			n = i;
			break;
		}
	}
	if ( found == true )
	{
		result->elements = malloc ( sizeof ( struct book_t) * nodes[n + 1].n_author_edges );
		result->elements[0] = &nodes[n];
		for ( int i = 0; i < nodes[n].n_author_edges; i++ )
		{
			size_t index = nodes[n].b_author_edges[i];
			result->elements[i + 1] = &nodes[index];
		}
		result->n_elements = nodes[i].n_author_edges + 1;
	}

	return result;
}

// Returns result set containing books that have been reprinted by a different publisher.
result_t* find_books_reprinted(book_t* nodes, size_t count, size_t publisher_id) {

	result_t *result = malloc( sizeof( struct result_t) );
	bool found =false;
	int n = 0;
	for ( int i = 0; i < count; i++ )
	{
		if ( nodes[i].publisher_id == publisher_id )
		{
			found = true;
			n = i;
			break;
		}
	}
	int number = 0;
	int total = nodes[n].n_publisher_edges + 1;
	size_t published[total];
	int size = 10;
	result->elements = malloc( sizeof( struct book_t) * total);

	published[0] = nodes[n].publisher_id;
	for ( int i = 0; i < nodes[n].n_publisher_edges; i++ )
	{
		published[i+1] = nodes[n].b_publisher_edges[i];
	}
	for ( int i = 0; i < total; i++ )
	{
		result_t* ids =find_books_id(nodes, count, nodes[ published[i] ].id );
		for ( int j = 0; j < ids->n_elements; j++ )
		{
			//printf("n elements %zu", ids->n_elements);
			if ( ids->elements[j]->publisher_id != publisher_id )
			{
				result->elements[number] = ids->elements[j];
				number++;
				if ( number >= size )
				{
					size = size * 2;
					result->elements = realloc( result->elements, total );
				}
			}
		}	
		free(ids->elements);
		free(ids);
	}
	result->n_elements = number;
	return result;
}

// Returns result set containing books that are k distance from given book.
result_t* find_books_k_distance(book_t* nodes, size_t count, size_t book_id, uint16_t k) {

	// TODO
	return NULL;
}

// Returns result set containing books in shortest path between book 1 and 2.
result_t* find_shortest_distance(book_t* nodes, size_t count, size_t b1_id, size_t b2_id) {

	// TODO
	return NULL;
}

// Returns result set containing books in shortest path of two edges types between author 1 and 2.
result_t* find_shortest_edge_type(book_t* nodes, size_t count, size_t a1_id, size_t a2_id) {

	// TODO
	return NULL;
}

result_t* find_books_id( book_t* nodes, size_t count, size_t id )
{
	result_t* result = malloc( sizeof(struct result_t ) );
	int n = 0;	
	int size = 10;
	result->elements = malloc( sizeof (struct book_t) * 10 );
	for ( int i = 0; i < count; i++ )
	{
		if( nodes[i].id == id )
		{
			result->elements[n] = &nodes[i];
			n++;
			if ( n >= size )
			{
				size = size * 2;
				result->elements = realloc( result->elements, size );
			}
		}
	}
	result->n_elements = n;
	return result;
}