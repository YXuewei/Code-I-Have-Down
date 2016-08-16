public class LinkedListDeque<E> implements Deque<E>{
 
    public class Node<E>{

        private E element;
        private Node<E> prv;
        private Node<E> next;

        public Node<E>( E element, Node<E> prv, Node<E> next ){
            this.element = element;
            this.prv = prv;
            this.next = next;
        }

        public E getElement(){
            return this.element;
        }

        public Node<E> getNext(){
            return this.next;
        }

        public Node<E> getPrv(){
            return this.prv;
        }

        public void setElement( E element ){
            this.element = element;
        }

        public void setNext( Node<E> next ){
            this.next = next;
        }

        public void setPrv( Node<E> prv ){
            this.prv = prv;
        }
    }

    private int size;
    
    public LinkedListDeque<E>(){
        Node<E> header = new Node<E>( null, null, null );
        Node<E> trailer = new Node<E>( null, header, null );
        int size = 0;
    }

    public int size(){
        return this.size;
    }

    public boolean isEmpty(){
        if ( this.size == 0 ){
            return true;
        }else{
            return false;
        }
    }

    public E peekFirst(){
        if ( this.size == 0 ){
            return null;
        }

        return this.header.getNext().getElement();
    }

    public E peekLast(){
        if ( this.size == 0 ){
            return null;
        }

        return this.trailer.getPrv().getElement();
    }

    public void addFirst( E element ){
        Node<E> insertNode = new Node<>( element, null , null );
        Node<E> succ = this.header.getNext();
        this.header.setNext( insertNode );
        insertNode.setNext( succ );
        insertNode.setPrv( this.header );
    }

    public addLast( E element ){
        Node<E> insertNode = new Node<>( element, null, null );
        Node<E> lead = this.trailer.getPrv();
        lead.setNext( insertNode );
        insertNode.setNext( this.trailer );
        insertNode.setPrv( lead );
    }

    public E pollFirst(){
        
        E returnValue;
        Node<E> remove = this.header.getNext();
        Node<E> first = remove.getNext();
        returnValue = remove.getElement();
        first.setPrv( this.header );
        this.header.setNext( first );

        return returnValue;
    }

    public E pollLast(){

        E returnValue;
        Node<E> remove = this.trailer.getPrv();
        Node<E> last = remove.getPrv();
        returnValue = remove.getElement();
        last.setNext( this.trailer );
        this.trailer.setPrv( last );

        return returnValue;
    }
}