import java.util.*;

public class ReverseSentence{
    public String reveseWords( String s ){
        s = s.trim();
        s = s.replaceAll( "[ ]{2,}"," ");
        String[] array = s.split( " " );

        String str = "";
        for ( int i = array.length - 1; i >=0; i-- ){
            str = str.concat( array[ i ] );
            str = str.concat( " " );
        }

        str = str.trim();
        return str;
    }
}