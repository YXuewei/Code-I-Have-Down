public class Extension2{
	public static void main(String[] args){
		boolean isPrime =false;
		int check = 2; // number we want to check
		System.out.println(2);
		for(;check < 1000; check ++){
				for(int i = 2; i < check;i++){
					if(check % i == 0){
						isPrime = false;
						break;
					}//close if under else and for
					else{isPrime = true;}
				}//close for under else
				if(isPrime == true){System.out.println(check);}
		}
	}//close main
}
