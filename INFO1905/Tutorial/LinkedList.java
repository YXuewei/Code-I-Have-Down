public class LinkedList<E>{
    
    public static class Node<E>{
        
        private E element;
        private Node<E> prv;
        private Node<E> next;
        
        public Node<E>( E element, Node<E> prv, Node<E> next ){
            this.element = element;
            this.prv = prv;
            this.next = next;
        }

        public void setElement( E element ){
            this.element = element;
        }

        public void setPrv( Node<E> prv ){
            this.prv = prv;
        }

        public void setNext( Node<E> next ){
            this.next = next;
        }

        public Node<E> getNext(){
            return this.next;
        }

        public Node<E> getPrv(){
            return this.prv;
        }

        public E getElement(){
            return this.element;
        }

    }

    public LinkedList<E> (){
        Node<E> header = new Node<>( null,null,null );
        Node<E> trailer = new Node<>( null, header, null );
        int size = 0;
    }

    public void insert( E element ){
        Node<E> insertNode = new Node( element, null, null );
        Node<E> tail = this.trailer.getPrv();
        tail.setNext( insertNode );
        insertNode.setPrv( tail );
        insertNode.setNext( this.tail );
        this.size += 1;
    }

    public void insert ( int position, E element ){
        Node<E> insetNode = new Node( element , null, null );
        Node<E> current = this.header;
        position = position - 1;
        if ( position > this.size ){
            position = this.size;
        }

        for ( int i = 0 ; i < position; i ++ ){
            current = current.getNext();
        }

        insertNode.setNext( current.getNext() );
        insertNode.setPrv( current );
        current.setNext( insertNode );

        this.size += 1;
    }

    public void remove( int position ){
        position -= 1;
        Node<E> current = this.header;
        if ( position > this.size ){
            position = this.size;
        }
        for ( i = 0; i < position; i++ ){
            current = current.getNext();
        }

        Node<E> prv = current.getPrv();
        Node<E> next = current.getNext();

        prv.setNext( next );
        next.setPrv( prv );
        this.size -= 1;
    }

    public Node<E> getNode( int position ){
        position -= 1;
        if ( position > this.size ){
            return null;
        }

        Node<E> current = this.header;
        for ( int i = 0; i < position; i++ ){
            current.getNext();
        }

        return current;
    }
}