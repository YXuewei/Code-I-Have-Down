public class RegexTest{
    public static void main( String[] args ){
        String str = "ABC123.'''',,....Yes";
        System.out.println( str );
        str = str.replaceAll( "[^A-Za-z]","");
        System.out.println( str );
    }
}