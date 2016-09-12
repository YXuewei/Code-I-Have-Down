import java.util.*;
import java.lang.*;

public class Stem{
	private static final String[] suffixes = {
		"able", "al", "ance", "ant", "ate", "ed",
		"ence", "ent", "er", "es", "ful", "ible",
		"ic", "ing", "ism", "ist", "less", "ment",
		"ness", "s", "tion", "tions", "tional"
	};

	
	public static void main(String[] args){
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter word: ");
		String input = keyboard.next();
		if(input.length() <= 2){
			System.out.println();
			System.out.println(input + " cannot be stemmed.");
			return;
		}
		System.out.println();
		for(int i = 0; i < suffixes.length; i++){
			if( input.endsWith(suffixes[i]) ){
				if(input.length() == suffixes[i].length()){
					System.out.println(input);
				}else{
					for(int j = 0; j < input.length() - suffixes[i].length(); j++){
						System.out.print(input.charAt(j));
					}
					System.out.println();
				}
				return;
			}
		}
		System.out.println(input);
	}
}