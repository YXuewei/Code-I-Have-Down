import java.lang.Math;
import java.util.*;

public class Decimal_To_Binary{
	public static void main(String[] args){
		Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter a decimal number: ");
        while(keyboard.hasNextInt() ){
            int input = keyboard.nextInt();
		    int count = 0;
		    if(input < 0){
			    System.out.println("Please input a positive number.");
			    return;
		    }else{
			    for( double i = 0; Math.pow(2,i) <= input; i++){
					count++;
			    }
		    }
		    int[] binary = new int[count];
		    for(int x = 0; x <= count - 1; x++ ){
			    binary[x] = input % 2;
			    input = input / 2;
		    }
		    System.out.print("Binary code is ");
		    for(int z = count - 1; z >= 0; z--){
			    System.out.print(binary[z]);
		    }
		    System.out.println();
            System.out.print("Enter a decimal number: ");        
        }
	}
}
