import java.util.*;

public class Palindromes{
    public static boolean isPalindrome( String word ){
        Stack reversed = new Stack();
        Queue origin = new Queue();
        int len = word.length();
        word = word.toLowerCase();

        for ( int i = 0; i < len; i++ ){
            origin.add( word.charAt( i ) );
        }

        for ( int i = 0; i < len; i++ ){
            if ( origin.remove().equals( reversed.pop() ) != false ){
                return false;
            }
        }

        return true;
    }
}