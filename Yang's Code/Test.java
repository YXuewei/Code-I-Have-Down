import java.util.*;
import java.lang.*;

public class Test{
    public static void main( String[] args){
        Hashtable< Date, String> a = new Hashtable< Date, String>();
        Date today = new Date( 2016, 12, 20 );
        String location = "Sydney";
        a.put( today, location );
        System.out.println( a.size() );
    }
}