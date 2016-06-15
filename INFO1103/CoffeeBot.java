// INFO1103 Assignment 1
// Student Name:Xuewei Yang
// Student ID:450045220
import java.util.Scanner;
import java.lang.*;

public class CoffeeBot{
	public static void main(String[] args){
			Scanner keyboard = new Scanner(System.in);
			// state supply value
			int cups;
			int shots;
			// start to check valid arguments and return proper value
			if(args.length == 0){
				System.out.println("No arguments. System terminating.");
					return;
			}else if(args.length ==1){
				System.out.println("Not enough arguments. System terminating.");
				return;
			}else if(args.length >2){
				System.out.println("Too many arguments. System terminating.");
				return;
			}else{
				cups = Integer.parseInt(args[0]);
				shots = Integer.parseInt(args[1]);
				if(cups < 0 && shots <0){
					System.out.println("Negative supply chain. System terminating.");
					return;
				}else if(cups < 0 && shots > 0){
					System.out.println("Negative supply of coffee cups. System terminating.");
					return;
				}else if(cups > 0 && shots < 0){
					System.out.println("Negative supply of coffee shots. System terminating.");
					return;
				}
			}
			// start to taking order
			System.out.print("Hello, what's your name? ");
			String customerName = keyboard.nextLine();
			System.out.print("Would you like to order some coffee, "+ customerName + "? (y/n) ");
			int i = 0;
			while(i <1){
				String yesOrNo = keyboard.next();
				switch(yesOrNo){
					case "y":
			      i= 2;
						System.out.println("Great! Let's get started.");
						break;

					case "n":
			      i = 2;
						System.out.println("Come back next time, " + customerName +".");
						return;

					default:
						System.out.println("Invalid response. Try again.");
						System.out.print("Would you like to order some coffee, "+ customerName + " ? (y/n) ");
				}
			}
			System.out.print("\n");
			System.out.println("Order selection");
			System.out.println("---------------" + "\n");
			if(cups == 1){
				System.out.println("There is " + cups + " coffee cup in stock and each cost $2.00.");
			}else{
				System.out.println("There are " + cups + " coffee cups in stock and each costs $2.00.");
			}
			if(shots ==1 ){
				System.out.println("There is " + shots + " coffee shot in stock and each cost $1.00.");
			}else{
				System.out.println("There are " + shots + " coffee shots in stock and each costs $1.00.");
			}
			System.out.print("\n");
			System.out.print("How many cups of coffee would you like? ");
			int cupNumber = keyboard.nextInt();
			if(cupNumber == 0){
				System.out.println("No cups, no coffee. Goodbye.");
				return;
			}else if(cupNumber < 0){
				System.out.println("Does not compute. System terminating.");
				return;
			}else if(cupNumber > cups){
				System.out.println("Not enough stock. Come back later.");
				return;
			}
			System.out.print("\n");
			int shotsLeft = shots;
			int order = 0;
			int shotsForEach;
			int[] shotsList = new int[cupNumber];
			// start to take shots for each coffeee cup
			while(order <= (cupNumber - 1)){
				System.out.print("How many coffee shots in cup " + (order + 1) + "? ");
				shotsForEach = keyboard.nextInt();
				keyboard.nextLine();
				// consume rest of line
				if(shotsForEach < 0 ){
					System.out.println("Does not compute. Try again.");
					continue;
				}
				if(shotsForEach > shotsLeft){
					if(shotsLeft == 1){
						System.out.println("There is only " + shotsLeft + " coffee shot left. Try again.");
						continue;
					}else{
						System.out.println("There are only " + shotsLeft + " coffee shots left. Try again.");
					}
				}else{
					shotsList[order] = shotsForEach;
					shotsLeft = shotsLeft - shotsForEach;
					order = order + 1;
				}
			}
			// start to print order summary -- how many shots and the total price
			System.out.print("\n");
			System.out.println("Order summary");
			System.out.println("-------------");
			System.out.print("\n");
			int SHOT_PRICE = 100;
			// since there is no need to deal with money less than 1 cent
			// take the data as interger,1 means 1 cent, and then divided by 100
			// to represent as 0.01 dollar
			int CUP_PRICE = 200;
			int remainPrice = cupNumber*CUP_PRICE + (shots - shotsLeft)*SHOT_PRICE;
			// remainPrice is the money that pending to be paid
			for(i = 1;i < cupNumber + 1;i++){
				if(shotsList[i - 1] >=2){
					System.out.print("Cup " + i + " has " + shotsList[i -1] + " shots and will cost $");
					System.out.printf("%.2f",(((double)shotsList[i-1]*(double)SHOT_PRICE + (double)CUP_PRICE)/100.00));
					System.out.println();
				}else{
					System.out.print("Cup " + i + " has " + shotsList[i -1] + " shot and will cost $");
					System.out.printf("%.2f",(((double)shotsList[i-1]*(double)SHOT_PRICE + (double)CUP_PRICE)/100.00));
					System.out.println();
				}
			}
			System.out.print("\n");
			if(cupNumber == 1){
				System.out.println(cupNumber + " coffee to purchase.");
			}else if (cupNumber >=2){
			System.out.println(cupNumber + " coffees to purchase.");
			}
			System.out.print("Purchase price is $");
			System.out.printf("%.2f", (double)remainPrice/100.00);
			System.out.println();
			System.out.print("Proceed to payment? (y/n) ");
			// start to take payment
			int goPay = 0;
			while(goPay < 1){
				String isPay = keyboard.nextLine();
				switch(isPay){
					case"y":
						System.out.print("\n");
						System.out.println("Order payment");
						System.out.println("-------------");
						goPay = 2;
						break;

					case"n":
						System.out.println("Come back next time, " + customerName + ".");
						goPay = 2;
						return;

					default:
						System.out.println("Invalid response. Tryagain.");
						System.out.println("\n");
						System.out.print("Proceed to payment? (y/n) ");
						break;
				}
			}
			// start to check amount that remain to be paid and give changes back
			int change;
			String subOfInsert;
			//string followed by '$'
			double paid = 0;
			// retrun subOfInsert as a int
			String payment;
			// the input when procceed to payment
			int[] AMOUNT_SET = {10000, 5000, 2000, 1000, 500, 200, 100, 50, 20, 10, 5};
			int bill = 0;
			// how many bills should be as change
			int p = 1;
			while(p < 2 ){
				System.out.print("\n");
				System.out.print("$");
				System.out.printf("%.2f",((double)remainPrice/100.00) );
				System.out.print(" remains to be paid. Enter coin or note: ");
				payment = keyboard.nextLine();
				if(payment.length() >= 2){
					int indexOfPoint = payment.indexOf(".");
					// start to check does input is decimal number
					String decimal = payment.substring(indexOfPoint + 1);
					char secondChar = payment.charAt(1);
					//to check does the charcter followed by '$' is a digit or not
					boolean check = Character.isDigit(secondChar);
					boolean inAMOUNT_SET = false;
					if(payment.startsWith("$") == true && payment.contains(".") && decimal.length() == 2 && check == true){
							// only input format as $1.00 is valid
							subOfInsert = payment.substring(1);
							paid = Double.parseDouble(subOfInsert) * 100.0;
							for(int m = 0; m < AMOUNT_SET.length; m++){
								if(paid == AMOUNT_SET[m]){
									inAMOUNT_SET = true;
									break;
								}
							}
							if(inAMOUNT_SET == true){
								if( paid < remainPrice){
								// determin wehter the first char of input is '$'
								// and does the amount of input is less than reamined price
									remainPrice = remainPrice - (int)paid;
									continue;
								}else if ( paid > remainPrice){
								// to see if the change were needed
									System.out.print("\n");
									System.out.print("You gave $");
									System.out.printf("%.2f", (paid / 100.0 ) );
									change = (int)paid - remainPrice;
									System.out.print("\n");
									System.out.println("Your change:");
									for(int z = 0;z < 11; z++){
										bill = change / AMOUNT_SET[z];
										if(bill != 0){
											System.out.print(bill + " x " + "$");
											System.out.printf("%.2f",((double)AMOUNT_SET[z] / 100) );
											System.out.println();
											change = change -  ( bill * AMOUNT_SET[z] );
										}else{
											continue;
										}
										p = 3;
									}// close for
								}else if( (int)paid == remainPrice ){
									System.out.println("No change needed! Perfect");
									p = 3;
								}
							}else{
								System.out.println("Invalid coin or notes, please try again.");
								continue;
							}
					}else{
						System.out.println("Invalid coin or notes, please try again.");
						continue;
					}
				}	
			
			}// ends while
			System.out.print("\n");
			System.out.println("Thank you, "  + customerName + ".");
			System.out.println("See you next time.");
	}
}
