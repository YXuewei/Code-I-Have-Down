import java.util.*;
import java.text.*;
import java.math.*;

public class GoldenRatio{
    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter two numbers: ");
        double number1;
        double number2;
        double number3;
        try{
            number1 = keyboard.nextDouble();
            number2 = keyboard.nextDouble();
            if(number2 > number1){
                number3 = number1;
                number1 = number2;
                number2 = number3;
            }
            double ratio = (number1 + number2) / number1;
            DecimalFormat df = new DecimalFormat("#.###");
            df.setRoundingMode(RoundingMode.HALF_UP);
            
            if(df.format(ratio).equals("1.618")){
                System.out.println();
                System.out.println("Golden ratio!");
            }else{
                System.out.println();
                System.out.println("Maybe next time.");
            }
        }catch(InputMismatchException ex){
            System.out.println();
            System.out.println("Invalid input.");
            System.exit(0);
        }
        
    }
}