import java.util.*;
import java.lang.*;

public class Test{
    public static void main( String[] args){
        Hashtable< Integer, ArrayList<String> > ht = new Hashtable< Integer, ArrayList<String> >();
        Hashtable< Integer, ArrayList<String> > ht2 = new Hashtable< Integer, ArrayList<String> >();
        
       ArrayList<String> list = new ArrayList<String>();
        list.add( "Suit"); 
        ht.put( 1, list);
        ht2.put(1,list);
        System.out.println( ht2.get(1).size());

        ArrayList<String> list2 = new ArrayList<String>();
        list2 = ht.get( 1 );

        list2.add( " Suits");

        System.out.println( ht2.get(1).size() );
        
    }
}