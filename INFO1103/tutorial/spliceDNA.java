import java.util.*;
import java.lang.*;

public class spliceDNA{
	public static void main(String[] args){
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Input strand: ");
		String input = keyboard.nextLine();
		String output = input.replaceAll("(GUGU.*?AGAG)","");
		System.out.println();
		System.out.println("Output is " + output);
	}
}