import java.util.*;

public class Palindromes{
    
    public static boolean isPalindrome( String word ){
        Stack<Character> reversed = new Stack<>();
        int len = word.length();
       // word = word.toLowerCase();

        for ( int i = 0; i < len; i++ ){
            reversed.push( word.charAt( i ) );
        }
        for ( int i = 0; i < len; i++ ){
            char compare = reversed.pop().charValue();
            if ( word.charAt( i ) != compare ){
                return false;
            }
        }

        return true;
    }

    public static boolean isPalindromeSentence( String sentence ){
        
        sentence = sentence.toLowerCase();
        sentence = sentence.replaceAll( "[^A-Za-z]","" );
        return isPalindrome( sentence );
    }
}