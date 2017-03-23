import java.util.*;
import java.lang.*;

public class RomanNumerals{
    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);
        try{
            System.out.print("Enter a number: ");
            int number = keyboard.nextInt();
            StringBuilder romanNumber = new StringBuilder();
            int[] arabicNumber = {1000,900,500,400,100,90,50,40,10,9,8,7,6,5,4,3,2,1};
            String[] roamn = {"M","CM","D","CD","C","XC","L","XL","X","IX","VIII","VII","VI","V","IV","III","II","I"};
            for(int i = 0; i < roamn.length; i++){
                int qutient = number / arabicNumber[i];
                for(int j = 0; j < qutient; j++){
                    romanNumber.append(roamn[i]);
                }
                number = number - (qutient * arabicNumber[i]);
            }
            System.out.println();
            System.out.println("Roman equivalent is " + romanNumber);
        }catch(InputMismatchException ex){
            System.out.println();
            System.out.println("Not a number.");
            System.exit(0);
        }
    }
}