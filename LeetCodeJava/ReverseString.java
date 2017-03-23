import java.util.*;

public class ReverseString{
    public static void main( String[] args ){
        Scanner keyboard = new Scanner( System.in );
        String input = keyboard.nextLine();
        System.out.println( reverseString( input ) );
    }
    
    public static String reverseString( String s ){
        int index = s.length() - 1;
        char[] array = new char[ s.length() ];
        
        for ( int i = 0; i < s.length(); i ++ ){
            array[ i ] = s.charAt( index );
            index--;
        }

        String str;
        StringBuilder sb = new StringBuilder();
        for ( int i = 0; i < array.length; i++ ){
            sb.append( array[ i ] );
        }

        str = sb.toString();

        return str;
    }
}