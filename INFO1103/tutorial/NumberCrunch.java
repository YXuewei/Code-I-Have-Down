import java.util.Scanner;
import java.lang.*;

public class NumberCrunch{
	public static void main(String[] args){
		System.out.println("Please enter up to 3 positive numbers.");
		Scanner keyboard = new Scanner(System.in);
		String reader = keyboard.nextLine();	
		int count = 0;
		String [] str_input; 
		int[] int_input = new int[3];		
		str_input = reader.split(" ");
		int length = str_input.length;
		for(int i = 0; i <= length - 1; i++){
			int_input[i] = Integer.parseInt(str_input[i]);
			if(int_input[i] > 0){
				count++;
			}
		}
		System.out.println("You have entered " + count + " positive numbers.");
		for(int o = 0; o <= length -1; o++){
			System.out.print("The factor of " + str_input[o] + " is");
			for(int p = 1; p <= Integer.parseInt( str_input[o] ); p++){
				if(p == Integer.parseInt( str_input[o]) ){
					System.out.print(" " + p);
				}else if( (Integer.parseInt(str_input[o]) % p) == 0){
					System.out.print(" " +  p + ",");
				}
			}
			System.out.print(".");
			System.out.println();
		}
	}
}
