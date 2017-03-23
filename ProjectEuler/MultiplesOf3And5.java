public class MultiplesOf3And5{
    public static void main(String[] args){
        int i = 3;
        int sum = 0; 
        while (i < 1000){
            if ( i % 3 == 0 || i % 5 == 0){
                sum += i; 
            }
            i++; 
        }
        System.out.println("sum is " + sum);
    }
}