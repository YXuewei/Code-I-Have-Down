import java.util.*;
import java.lang.*;

public class Triforce{
    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);
        try{
            System.out.print("Enter height: ");
            int height = keyboard.nextInt();
            System.out.println();
			if(height >= 2 && height <= 20){
                firstLevel(height);
                baseLevel(height);
            }else{
				System.out.println("Invalid height.");
            }
        }catch(InputMismatchException ex){
            System.out.println();
			System.out.println("Invalid height.");
        }
    }
    public static void firstLevel(int input){
        int baseLine = input - 1;
        for(int i = 0; i < input; i++){
            for(int x = input * 2 - 1; x > i; x--){
                System.out.print(" ");
            }
            System.out.print("/");
            for(int j = 0; j < i * 2; j++){
                if(i == baseLine ){
                    System.out.print("_");
                }else{
                System.out.print(" ");
                }
            }
            System.out.println("\\");
        }
        return;
    }
    
    public static void baseLevel(int input){
        int baseLine = input - 1;
        for(int i = 0 ; i < input; i++){
            for(int j = 0; j < input - i - 1; j++){
                System.out.print(" ");
            }
            System.out.print("/");
            for(int x = 0; x < i * 2; x++){
                if(i == baseLine){
                    System.out.print("_");
                }else{
                    System.out.print(" ");
                }
            }
            System.out.print("\\");
            for(int z = 0; z < ( (input - 1) * 2) - (i * 2) ;z++ ){
                System.out.print(" ");
            }
            System.out.print("/");
            for(int x = 0; x < i * 2; x++){
                if(i == baseLine){
                    System.out.print("_");
                }else{
                    System.out.print(" ");
                }
            }
            System.out.print("\\");
            System.out.println();
        }
    }
}