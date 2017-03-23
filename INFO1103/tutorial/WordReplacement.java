import java.lang.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class WordReplacement{
    public static void main(String[] args)throws UnsupportedEncodingException{
      String fileName = args[0];
      String wordToBeCatched = args[1];
      String wordReplaced = args[2];
      File f = new File(fileName);
      Scanner fileReader = null;
      try{
          fileReader = new Scanner(f);
      }
      catch(FileNotFoundException ex){
          System.out.println("File not found");
      }
      ArrayList<String> stringList = new ArrayList<String>();
      while( fileReader.hasNextLine() ){
          stringList.add( fileReader.nextLine() );
      }
      fileReader.close();
      int length = stringList.size();
      for(int i = 0; i < length; i++){
          if( stringList.get(i).equals(wordToBeCatched) ){
              stringList.remove(i);
              stringList.add(i, wordReplaced);
          }
      }
      PrintWriter writer = null;
      try{
          writer = new PrintWriter(fileName,"UTF-8");
      }catch(FileNotFoundException ex){
          System.out.println("File Not Found.");    
      }
      for(int i = 0; i < length; i++){
          writer.println( stringList.get(i) );
      }
      writer.close();
      System.out.println("Finished");
    }   
}