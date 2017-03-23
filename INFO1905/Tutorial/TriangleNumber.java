public class TriangleNumber{
    public static int triangle( int n ){
        if ( n == 1 ){
            return 1;
        }else if ( n == 0 ){
            return 0;
        } else {
            return n + triangle( n - 1 );
        }
    }
}