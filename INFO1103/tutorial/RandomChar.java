import java.util.Random;
import java.lang.*;
import java.io.*;

public class Test{
    public static void main(String[] args)throws UnsupportedEncodingException{
        try{
            PrintWriter writer = new PrintWriter("randomChar.txt");
            Random r = new Random();
            int randomNumber;
            char randomChar;
            String str;
            for(int i = 0;i < 15; i++){
                randomNumber = r.nextInt(25);
                randomChar = (char)(randomNumber + 97);
                //System.out.println(randomChar);
                str = Character.toString(randomChar);
                writer.println(str);
            }
            writer.close();
            System.out.println("Finished");
        }catch(FileNotFoundException ex){
            System.out.println("No File");
        }
    }   
}