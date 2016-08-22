import java.util.*;
import java.lang.*;

public class Test{
    public static void main( String[] args){
       String str = "  a   b c    d";
       str = str.replaceAll( "[ ]{2,}","");
       System.out.println( str );
    }
}