import java.util.*;
import java.lang.*;

public class Caser{
	public static void main(String[] args){
		System.out.print("Enter key: ");
		Scanner keyboard = new Scanner(System.in);
		int key = keyboard.nextInt();
		keyboard.nextLine();
		if( (key >= 0 && key <= 26) == false ){
			System.out.println();
			System.out.println("Invalid Key!");
			return;
		}
		System.out.print("Enter line: ");
		String line = keyboard.nextLine();
		System.out.println();
		char[] chars = line.toCharArray();
		for(int i = 0; i < chars.length; i ++){
			if(chars[i] == ' ' || chars[i] == ',' || chars[i] == '.' || chars[i] == '?'){
				System.out.print(chars[i]);
			}else if(chars[i] >= 65 && chars[i] <= 90){
				if(chars[i] + key > 90){
					chars[i] = (char)((chars[i] + key) - 90 + 64);	
					System.out.print(chars[i]);
				}else{
					chars[i] += key;
					System.out.print(chars[i]);
				}
			}else{
				if(chars[i] + key > 122){
					chars[i] = (char)((chars[i] + key) - 122 + 96);
					System.out.print(chars[i]);
				}else{
					chars[i] += key;
					System.out.print(chars[i]);
				}
			}
		}
		System.out.println();	
	}
}