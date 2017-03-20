public class LargestPalindromeProduct{
    public static void main(String[] args){
        int x , y , z;
        x = y = z = 1;
        int number = 0;
        
        for( ;x < 100; x++){
            for(; y < 100; y++){
                for (; z < 100; z++){
                    String check = String.valueOf( x * y * z );
                    if ( isPalindrome( check) ){
                        number =x * y * z;
                    }
                }
            }
        }

        System.out.println( number );

    }

    public static boolean isPalindrome( String check ){
       // System.out.println("Check is " + check);
        int len = check.length();
        int index = len / 2;
        for ( int i = 0; i < index; i++){
           // System.out.printf("%d\t%d\n",i,len-i);
            if ( check.charAt( i ) != check.charAt( len - i - 1) ){
                return false;
            }
        }
        return true;
    }
}