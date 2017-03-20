import java.util.*;
import java.lang.*;

public class Test{
    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter a number: ");
        double root;
        while(keyboard.hasNextDouble()){
            double input = keyboard.nextDouble();
            root = input;
            for(int i = 0; i < 100; i++){
                root = (root + (input / root) ) / 2.0;
            }
                System.out.print("Root is ");
                System.out.printf("%.5f",root);
                System.out.println();
                System.out.print("Enter a number: ");
        }
    }
}