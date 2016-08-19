import java.util.*;

public class RansomNote{
    public boolean canConstruct( String ransomNote, String magazine ){
        Stack< Character > ran = new Stack<>();
        Stack< Character > mag = new Stack<>();
        

        char[] rn = ransomNote.toCharArray();
        char[] mg = magazine.toCharArray();
        Arrays.sort( rn );
        Arrays.sort( mg );

        int ran_len = ransomNote.length();
        int mag_len =magazine.length(); 
        for ( int i = 0; i < ran_len; i++ ){
            ran.push( rn[ i ] );
        }

        for ( int i = 0; i < mag_len; i++ ){
            mag.push( mg[ i ] );
        }

        while ( mag.isEmpty() != true && ran.isEmpty() != true ){
            if ( mag.peek() == ran.peek() ){
                mag.pop();
                ran.pop();
            }else{
                mag.pop();
            }
        }

        if ( ran.isEmpty() ){
            return true;
        }else{
            return false;
        }
    }
}