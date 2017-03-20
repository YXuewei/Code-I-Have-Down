import java.util.*;

public class PalindromePermutation{
    
    public static void main( String[] args ){
        canPermutePalindrome( "aab" );
    }

    public static boolean canPermutePalindrome( String s ){
        if ( s.length() == 2 ){
            if ( s.charAt( 0 ) != s.charAt( 1 ) ){
                return false;
            }else{
                return true;
            }
        }
        
        HashMap<Character, Integer> data = new HashMap<>();
        int len = s.length();

        for ( int i = 0; i < len; i++ ){
            if ( data.containsKey( s.charAt( i ) ) ){
                int value = data.get( s.charAt( i ) );
                data.put( s.charAt( i ), value + 1 );
            }else{
                data.put( s.charAt( i ), 1 );
            }
        }

        int oddCount = 0;
        for ( Map.Entry<Character, Integer> i : data.entrySet() ){
            if ( i.getValue() % 2 != 0 ){
                oddCount++;
            }
            System.out.println( oddCount );
        }

        if ( oddCount > 1 ){
            return false;
        }else{
            return true;
        }
    }
}