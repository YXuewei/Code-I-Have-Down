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
	int n;
	for ( int i = 0; i < countl i++ )
	{
		if ( nodes[i].publisher_id == publisher_id )
		{
			found = true;
			n = i;
			break;
		}
	}

	int number = 0;
	result->elements = malloc ( sizeof(struct book_t) * (number + 1) );
	size_t n_publisher = nodes[n].n_publisher_edges;
	result_t *books[(int)n_publisher + 1];
	int id = nodes[n].id;
	for ( int i = 0; i < n_publisher; i++ )
	{
		books[i] = find_books_publisher(nodes, count, id);
		id = nodes[n].b_publisher_edges[i];
	}
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

