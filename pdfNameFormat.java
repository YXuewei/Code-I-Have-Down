import java.util.*;
import java.lang.*;

public class pdfNameFormat{
    public static void main( String[] args){
        System.out.println( args[0].replaceAll("_", " ") );
    }
}