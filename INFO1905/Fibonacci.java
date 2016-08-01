import java.util.*;

public class Fibonacci{
    
    public static void main( String[] args ){
        Scanner keyboard = new Scanner( System.in);
        int input;
        System.out.printf( "Input a number\t");
        input = keyboard.nextInt();
        System.out.print(" Result is " + fibonacci( input ) +"\n");
    }
    
    public static int fibonacci( int n ){
        if ( n == 0 ){
            return 0;
        }else if ( n == 1 ){
            return 1;
        }else {
            return  fibonacci( n - 1 ) + fibonacci( n - 2 );
        }
    }
}