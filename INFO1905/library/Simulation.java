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
        System.out.println("Add < title >");
        System.out.println("Edit < title >");
        System.out.println("Borrow < title >");
        System.out.println("Return < title >");
    }

    public static void add(String input){
        
        String[] information = input.split(" ");
        Arraylist<String> toAdd = new ArrayList<String>();
        for ( int i = 0; i < 6; i++){
            if ( i > information.length ){
                toAdd.add( "Unkown" );
            }else{
                toAdd.add( information[i] );
            }
        }
        String informaton = toAdd.toArray();
        
        Book newBook = new Book( information[0], information[1], information[2], information[3], information[4]
                                 information[5] );
        library.put( information[0], newBook);

        System.out.println("Book added!");
    }

    public static void Edit( String input ){
        
    }
}