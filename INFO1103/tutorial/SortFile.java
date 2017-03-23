import java.lang.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class SortFile{
    public static void main(String[] args)throws UnsupportedEncodingException{
        File inputFile;
            if(args.length > 0){
            inputFile = new File( args[0] );
        }else{
            System.out.println("No input file");
            return;
        }
       Scanner fileReader;
       try{
           fileReader = new Scanner(inputFile);
       }catch(FileNotFoundException ex){
           System.out.println("Can not find file");
           return;
       }
       ArrayList<String> input = new ArrayList<String>();
       while(fileReader.hasNextLine()){
           input.add(fileReader.nextLine() );
       }
       String sortFile[] = new String[input.size()];
       sortFile = input.toArray(sortFile);
       Arrays.sort(sortFile);
       String fileName = inputFile.getName();
       int indexOfDot = fileName.indexOf(".");
       fileName = fileName.substring(0,indexOfDot - 1);
       StringBuilder nameBuilder = new StringBuilder();
       nameBuilder.append(fileName);
       nameBuilder.append("-sroted.txt");
       String sortedName = nameBuilder.toString();
       PrintWriter writer;
       try{
           writer = new PrintWriter(sortedName, "UTF-8");
       }catch(FileNotFoundException ex){
           System.out.println("Erro");
           return;
       }
       
       for(int i = 0; i < sortFile.length;i++){
           writer.println(sortFile[i]);
       }
       writer.close();
       System.out.println("Finished");
    }   
}