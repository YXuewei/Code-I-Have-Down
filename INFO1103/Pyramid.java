public class Pyramid{
	public static void main(String[] args){
		if(args.length == 0){
			System.out.println("No input System terminating.");
			return;
		}else if(args.length > 2){
			System.out.println("Too many arguments. System terminating.");
			return;
		}
		int input = Integer.parseInt(args[0]);
		for(int i = 0; i <= input;i++){
			for(int x = input; x > i; x--){
				System.out.print(" ");
			}
			for(int z = 1; z<= i;z++){
				System.out.print("* ");
			}
			System.out.print("\n");
		
		}
	}
}
