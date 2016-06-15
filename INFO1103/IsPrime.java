import java.util.Scanner;

public class IsPrime{
	public static void main(String[] args){
		System.out.println("Hi,enter a number, and I will tell you is that a prime or not!");
		Scanner keyboard = new Scanner(System.in);
		int check = keyboard.nextInt();
		// the number that we want to check is or not a prime
		if(check <= 1){
			System.out.print(check + " is not prime");
		}
		else{
			for(int i = 2; i < check; i++){
				if(check % i  == 0){
				System.out.println(check +  " is not prime:" + i + " is a factor");
				return;
				}// close if
			}// close for
					System.out.println(check + " is a prime");
		}//close else
	}//close main
}// close class
