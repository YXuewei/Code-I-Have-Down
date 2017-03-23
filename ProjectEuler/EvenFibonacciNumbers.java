import java.util.*;

public class EvenFibonacciNumbers{
    
    public static ArrayList<Integer> fibonacci;
    
    public static void main(String[] args){
        fibonacci = new ArrayList<Integer>();
        fibonacci.add( 1 );
        fibonacci.add( 2 );
        int size = fibonacci.size(); 
        int lastTerm = fibonacci.get( size - 1);

        //start to genrate fibonacci series
        while ( lastTerm < 4000000){
            addNewTerm( fibonacci );
            size = fibonacci.size();
            lastTerm = fibonacci.get( size - 1);
        }

        //start to adding sum
        int sum = 0;
        for ( int i = 0; i < fibonacci.size(); i++){
            if ( fibonacci.get( i ) % 2 == 0){
                sum += fibonacci.get( i );
                System.out.println( "term is " + fibonacci.get( i ) );
            }
        }

        System.out.println("Sum is " + sum);

    }

    public static void addNewTerm ( ArrayList<Integer> list){
        int length = list.size();
        int sum = 0; 
        
        //compute new term
        sum = list.get( length - 1) + list.get( length - 2 );

        list.add( sum );
        return;
    }

}