import java.util.*;

public class Simulation{

    static HashMap< String, Book > library = new HashMap< String, Book >();

    public static void main(String[] args){

    }

    public static void initialize(){
        System.out.println("Welcome to this Library Management System");
        System.out.println("There are " + library.size() + " books in library");
    }

    public static void help(){
        System.out.println("Set < title >");
        System.out.println("Edit < title >");
        
    }
}