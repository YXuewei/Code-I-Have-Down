import java.util.Random;

public class R32{
    public static void main( String[] args){
        int[] array = {1, 3, 9, 12, 23, 5, 6};
        
        Random random = new Random();
        int removeCount = 0;
        while ( removeCount <= 6 ){
            int index = random.nextInt( array.length );
            if ( array[ index ] == 0 ){
                continue;
            }else{
                array[ index ] = 0;
                removeCount++;
            }
        }

        System.out.println( " start to check array ");
        for ( int i = 0; i < array.length; i++ ){
            System.out.print( array[ i ] + " ");
        }
        

    }
}