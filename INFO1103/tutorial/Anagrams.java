import java.util.Scanner;
import java.lang.*;
import java.util.Arrays;

public class Anagram{
	public static void main(String[] args){
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter line: ");
		String inputLine = keyboard.nextLine().replaceAll("[^0-9A-Za-z]", "").toLowerCase();
		System.out.print("Enter anagram: ");
		String anagrams = keyboard.nextLine().replaceAll("[^0-9A-Za-z]", "").toLowerCase();
		String sortedIn = sort(inputLine);
		String sortedA = sort(anagrams);
		boolean isAnagrams = false;
		if(sortedIn.length() == sortedA.length()){
			for(int i = 0; i < sortedIn.length();i++ ){
				if(sortedIn.charAt(i) == sortedA.charAt(i)){
					isAnagrams = true;
				}else{
					isAnagrams = false;
					break;
				}
			}
		}else{
			System.out.println();
			System.out.println("Not an anagram.");
			return;
		}
		if(isAnagrams == true){
			System.out.println();
			System.out.println("Anagram!");
		}else{
			System.out.println();
			System.out.println("Not an anagram.");
		}
	}
	public static String sort(String s){
		int length = s.length();
		char[] charArray= new char[length];
		int[] intArray = new int[length];
		for(int z = 0; z < length; z++){
			charArray[z] = s.charAt(z);
		}
		for(int x = 0; x < length; x++){
			intArray[x] = (int)charArray[x];
		}
		Arrays.sort(intArray);
		for(int y = 0; y < length; y++){
			charArray[y] = (char)intArray[y];
		}
		String sorted = new String(charArray);
		return sorted;
	}
}