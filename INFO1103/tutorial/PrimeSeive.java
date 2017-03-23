import java.util.*;
import java.util.Scanner;
import java.lang.*;

public class PrimeSeive{
        // initially assume all integers are prime
    public ArrayList<Long> primeSeive(long number){
        ArrayList<Long> primeSet = new ArrayList<Long>();
        int N = (int)Math.sqrt(number);
        boolean[] isPrime = new boolean[N + 1];
        for (int i = 2; i <= N; i++) {
            isPrime[i] = true;
        }

        // mark non-primes <= N using Sieve of Eratosthenes
        for (int i = 2; i*i <= N; i++) {

            // if i is prime, then mark multiples of i as nonprime
            // suffices to consider mutiples i, i+1, ..., N/i
            if (isPrime[i]) {
                for (int j = i; i*j <= N; j++) {
                    isPrime[i*j] = false;
                }
            }
        }
        for(int i = 2; i < isPrime.length;i++){
            if(isPrime[i] == true){
                primeSet.add(i);
            }
        }
        return primeSet;
    }
}
