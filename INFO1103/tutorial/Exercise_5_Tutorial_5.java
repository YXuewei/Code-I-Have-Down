public class Exercise_5_Tutorial_5{
	public static void main(String[] args){
		int length = args.length;
		String[] intArray = new String[length];
		for(int i = 0; i < length; i ++){
			intArray[i] = args[i];
		}
		String input = "";
		boolean inArray = false;
		while(input.equals("exit") != true){
			System.out.println("Enter a number:");
			Scanner keyboard = new Scanner(System.in);
			input = keyboard.next();
			for(int i = 0; i < length; i++){
				if( input.equals("exit") ){
					System.out.println("Exiting program.");
					return;
				}else if( input.equals(intArray[i] ) ){
					System.out.println("That number is in the array.");
					inArray = true;
					break;
				}
			}
			if(inArray == false){
					System.out.println("That number is not in the array.");
				}
			}
		}
	}