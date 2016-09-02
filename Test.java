import java.util.*;
import java.lang.*;

public class Test{
    public static void main( String[] args){
       int[] a = { 0, 1, 2, 3, 4, 5};
       int low = 0;
       while( low < a.length ){
           int p = a[ low ++ ];
           System.out.println( p );
       }
    }
}