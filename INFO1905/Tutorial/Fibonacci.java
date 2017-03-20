import java.util.*;

public class Fibonacci{
    
    /*public static void main( String[] args ){
        Scanner keyboard = new Scanner( System.in);
        int input;
        System.out.printf( "Input a number\t");
        input = keyboard.nextInt();
        System.out.print(" Result is " + fibonacci( input ) +"\n");
    }*/

    public static Stack<Integer> stacks = new Stack<>();
    
    public static int fibonacci( int n ){
        if ( n == 0 ){
            return 0;
        }else if ( n == 1 ){
            return 1;
        }else {
            return  fibonacci( n - 1 ) + fibonacci( n - 2 );
        }
    }

    public static Stack<Integer> getNumbers( int n ){
       if ( n < 0 ){
           return stacks;
       }

       for ( int i = 0; i <= n; i++ ){
           if ( i < 2 ){
               stacks.push( i );
           }else{
               int a = stacks.pop().intValue();
               int b = stacks.pop().intValue();
               int c = a + b;
               stacks.push( b );
               stacks.push( a );
               stacks.push( c );
           }
       }

       return stacks;
    }
}