public class test{
    public static void main(String[] args){
      Scanner keyboad = new Scanner( System.in );
      System.out.print( "Enter a number " );
      int number = keyboad.nextInt();
      int sum =  recusionDemo( number );
      System.out.println(sum);
    }

    //given a number, compute the sum from 1 up to this number
    public static int recursionDemo(int number){

      int sum = 0;
      if ( number == 0 ){
        return sum;
      }else {
        sum += number;
        recursionDemo( number - 1 );
      }
    }
}