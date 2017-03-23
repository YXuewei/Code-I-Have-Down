import java.util.*;
import java.lang.*;

public class Binary_To_Decimal{
	public static void main(String[] args){
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter binary: ");
        while( keyboard.hasNext() ){
            String input = keyboard.next();
            System.out.println();
		    if(isBinary(input) == true){
                System.out.println(toDecimal(input) + " in decimal");
            }else{
                System.out.println("Not binary!");
            }
            System.out.println();
            System.out.print("Enter binary: ");
	    }
    }
    public static boolean isBinary(String s){
        int length = s.length();
        int input;
        int[] array = new int[length];
        double binary;
        try{
            binary = Double.parseDouble(s);
            for(int i = 0; i < length; i++){
                if(s.charAt(i) != '1' && s.charAt(i) != '0'){
                    return false;
                }
            }
            return true;
        }catch(NumberFormatException nfe){
            return false;
        }
    }
    public static int toDecimal(String s){
        int length = s.length();
        int input;
        int decimal = 0;
        int position;
        int roundCount = 0;
        for(int i = 0 ; i < length; i++){
            roundCount++;
            position = length - roundCount;
            input = Integer.parseInt(Character.toString( s.charAt(i) ) ) ;
            decimal = input * (int)Math.pow(2,position) + decimal;
        }
        return (int)decimal;
    }
}