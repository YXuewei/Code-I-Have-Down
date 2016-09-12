import java.util.Scanner;

public class Palindrome{
	public static void main(String[] args){
		Scanner keyboard = new Scanner(System.in);
		String input = keyboard.next();
		int length = input.length();
		int start = 0;
		int end = length;
		for(;start < end; start ++){
			if(input.charAt(start) == input.charAt(end-1) ){
				end--;
				continue;
				
			
			}else{
				System.out.println("It is not a palindrome.");
				return;
			}
		}
		System.out.println("It is a palindrome.");
	}
}
