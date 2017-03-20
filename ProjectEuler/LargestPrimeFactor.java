import java.util.*;

public class LargestPrimeFactor{
   
   public static long number = 600851475143L;

    public static void main(String[] args){
        long halfOfNumber = ( number / 2 ) + 1;
        ArrayList<Long> factors = new ArrayList<Long>();
        ArrayList<Long> primeFactors = new ArrayList<Long>();

        //start to find all the factors
        for ( long i = 2; i < halfOfNumber ; i++){
            if ( number % i == 0 ){
                factors.add( i );
            }
        }

       //start to find all prime factor;
       for ( int i = 0; i < factors.size(); i++){
           if ( isPrime( factors.get( i ) ) == true ){
               primeFactors.add( factors.get( i ));
           }
       }

        // start to find largest value 
        long max = Long.MIN_VALUE;
        for ( int i = 0; i < primeFactors.size(); i++){
            if ( primeFactors.get( i ) > max){
                max = primeFactors.get( i );
            }
        }

        System.out.println("Largest prime factor is " + max); 
    }

    public static boolean isPrime( Long number){
        long root = (long)Math.sqrt( number ) + 1;
        if ( number % 2 == 0 ){
            return false;
        }else{
            for (long i = 3; i < root; i++){
                if ( number % i == 0 ){
                    return false;
                }
            }

            return true;
        }
    }
}