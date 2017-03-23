import java.util.*;
import java.util.Scanner;
import java.lang.*;
import java.io.*;

public class PetManagement{
  public static void main(String[] args)throws IOException{
    try{
      File f = new File(args[0]);
      Scanner console = new Scanner(f);
      String name = args[0].substring(0,args[0].indexOf("."));
      while(console.hasNextLine()){
        System.out.println(console.nextLine());
      }
      Scanner keyboard = new Scanner(System.in);
      System.out.println("Would you like to update information for " + name + "(y/n)");
      String input = keyboard.nextLine();
      switch (input){
        case "y":
          break;
        case "no":
          System.out.println("System terminating");
          return;
      }
      
      Pet updated = new Pet(name);
      System.out.println("How old is " + name + "?");
      int age = keyboard.nextInt();
      keyboard.nextLine();
      updated.setAge(age);
      System.out.println("What are " + name + "'s nick name?(csv)");
      input = keyboard.nextLine();
      String[] nickS = input.split(",");
      //System.out.println("length of nick names" + nickNames.length);
      updated.setNickNames(nickS);
      System.out.println("What species is " + name + "?");
      input = keyboard.nextLine();
      updated.setSpecies(input);
      System.out.println("Does " + name + "house trained?");
      input = keyboard.nextLine();
      keyboard.close();
      updated.setHouseTrained(input);
      
      PrintWriter writer = new PrintWriter(args[0],"UTF-8");
      writer.println("Pet Name: " + updated.getName());
      writer.println("Age: " + updated.getAge());
      writer.println("Nick Names: " + updated.getNickNames() );
      writer.println("Species: " + updated.getSpecies());
      writer.println("House Trained: " + updated.getHouseTrained());
      writer.close();
      System.out.println("Update finished.");
    
    }catch(FileNotFoundException ex){
      Scanner keyboard = new Scanner(System.in);
      String name = args[0].substring(0,args[0].indexOf("."));
      System.out.println("Unknow pet: " + name);
      PrintWriter writer = new PrintWriter(args[0],"UTF-8");
      
      Pet updated = new Pet(name);
      updated.setName(name);
      System.out.println("How old is " + name + "?");
      int age = keyboard.nextInt();
      keyboard.nextLine();
      updated.setAge(age);
      System.out.println("What are " + name + "'s nick name?(csv)");
      String input = keyboard.nextLine();
      String[] nickNames = input.split(",");
      updated.setNickNames(nickNames);
      System.out.println("What species is " + name + "?");
      input = keyboard.nextLine();
      updated.setSpecies(input);
      System.out.println("Does " + name + "house trained?");
      input = keyboard.nextLine();
      keyboard.close();
      updated.setHouseTrained(input);
      
      writer.println("Pet Name: " + updated.getName());
      writer.println("Age: " + updated.getAge());
      writer.println("Nick Names: " + updated.getNickNames() );
      writer.println("Species: " + updated.getSpecies());
      writer.println("House Trained: " + updated.getHouseTrained());
      writer.close();
      System.out.println("Entry Finished.");
    }
    
    }
}
