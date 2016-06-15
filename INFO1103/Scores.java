import java.util.Scanner;

public class Scores{
	public static void main(String[] args){
		int score = Integer.parseInt(args[0]);
		int total = Integer.parseInt(args[1]);
		System.out.print("The percentage is ");
		System.out.printf("%.2f", calculatePercentage(score, total) * 100.00);
		System.out.println("%.");
	}
	public static double calculatePercentage(int score, int total){
		double result;
		result = ((double)score / (double)total);
		return result;
	}
}