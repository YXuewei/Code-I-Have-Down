import java.util.*;
import java.lang.*;

public class DNA{
		enum DNA_SET{G,A,T,C,g,a,t,c};
	public static void main(String[] args){
		System.out.print("Enter strand: ");
		Scanner keyboard = new Scanner(System.in);
		String input = keyboard.nextLine();
		System.out.println();
		if(input.length() == 0){
			System.out.print("No strand provided.");
			return;
		}
		System.out.print("Complementary strand is ");
		for(int i = 0; i < input.length(); i++){
			switch( input.charAt(i) ){
				case 'G':
					System.out.print('C');
					continue;
				case 'A':
					System.out.print('T');
					continue;
				case 'T':
					System.out.print('A');
					continue;
				case'C':
					System.out.print('G');
					continue;
				case'g':
					System.out.print('c');
					continue;
				case'a':
					System.out.print('t');
					continue;
				case't':
					System.out.print('a');
					continue;
				case'c':
					System.out.print('g');
					continue;
				default:
					System.out.print('x');
					continue;
			}
		}
		System.out.println();
	}
}