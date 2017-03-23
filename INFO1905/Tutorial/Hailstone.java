import java.util.*;

public class Hailstone{
    
    public static List<Integer> hailstone( int n ){
       ArrayList<Integer> list = new ArrayList<Integer>();
       boolean stop = false;
       if ( n == 1 ){
           list.add( n );
           return list;
       }
       
       while ( stop == false){
            list.add( n );
            if ( n % 2 == 0 ){
                n = n / 2;
                if ( n == 1){
                    stop = true;
                    list.add( n );
                }
                continue;
            }else{
                n = n * 3 + 1;
                continue;
            }
        }

        return list;
    }
    
}