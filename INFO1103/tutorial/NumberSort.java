public class Test{
	public static void main(String[] args){
		int[] array = new int[]{20,13,8,22,19};
		int temp;
		boolean fixed = false;
		while(fixed == false){
			fixed = true;
		for(int x = 0; x < 4; x ++){
			if(array[x] > array[x + 1]){ 
				temp = array[x + 1];
				array[x + 1] = array[x];
				array[x] = temp; 
				fixed = false;
			}
		}
		}
		for(int i = 0; i < 5; i++){
			System.out.println(array[i]);
		}
	}
}