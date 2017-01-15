import java.util.*;
import java.lang.*;

public class pdfNameFormat{
    public static void main( String[] args){
        String str = "Introduction_to_the_Theory_of_Computation_by_Michael_Sipser_Third_Edition_Course_Technology";
        System.out.println( str.replaceAll("_", " ") );
    }
}