import java.util.*;
import java.lang.*;

public class Simulation{

    static HashMap< String, Book > library = new HashMap< String, Book >();

    public static void main(String[] args){
        initialize();
        simuEngin();

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
        System.out.println("Stats");
        System.out.println("Bye");

    }

    public static void add(String input){
        
        String[] data = input.split(" ");
        ArrayList<String> toAdd = new ArrayList<String>();
        for ( int i = 0; i < 6; i++){
            if ( i > data.length ){
                toAdd.add( "Unkown" );
            }else{
                toAdd.add( data[i] );
            }
        }
        
        Book newBook = new Book( toAdd.get(0), toAdd.get(1), toAdd.get(2), toAdd.get(3), toAdd.get(4), toAdd.get(5) );
        library.put( data[0], newBook);

        System.out.println("Book added!");
    }

    public static void Edit( String input, String title ){

        Book editBook = library.get( title );
        String[] updateInfo = input.split(" ");
        switch ( updateInfo[0] ){
            case "edtion":
                editBook.setEdtion( updateInfo[1] );
                break;
            case "publisher":
                editBook.setPublisher( updateInfo[1] );
                break;
        }
    }

    public static void borrow(String title){

        Book borrowedBook = library.get( title );
        if ( borrowedBook.getStatus() == true ){
            System.out.println("Sorry, the book were borrowed.");
        }else{
            borrowedBook.setBorrowed( true );
            System.out.println("Here is your book! Remember to return them in 40 days!");
        }
    }

    public static void returnBook( String title ){

        Book returnedBook = library.get( title );
        returnedBook.setBorrowed( false );
    }

    public static void bye(){
        System.out.println("You have a wonderful day. Bye!");
        System.exit(1);
    }

    public static void stats(){
        System.out.println("Library have " + library.size() + " books");
        int borrowed = 0;
        
        //better solution for this problem is that set a static variable
        //and change the variable everytime lent a book out.
        //The purpose of wrtitting a more complex solution
        //is to practice with itterating map
        for ( Map.Entry< String, Book> b : library.entrySet() ){
            if ( b.getValue().getStatus() == true ){
                borrowed++;
            }
        }

        System.out.println("We have " + borrowed + "books lent out.");
    }

    public static void simuEngin(){
        System.out.print("> ");
        Scanner keyboard = new Scanner(System.in);
        while ( keyboard.hasNextLine() ){
            String input = keyboard.nextLine();
            String[] command = input.split(" ");
            
            switch ( command[0].toLowerCase() ){
                case "help":
                    help();
                    break;
                case "add":
                    System.out.print("Please Type in the information as ");
                    System.out.print("following sequence");
                    System.out.println("Title,Author,ISBN,Edition,Publisher,Publiser Year");
                    String info = keyboard.nextLine();
                    add( info );
                    break;
                case "edit":
                    System.out.println( "Please enter which one you would like to edit,Edition or Publisher" );
                    String in = keyboard.nextLine();
                    Edit( in , command[1] );
                    System.out.println("Book information has been updated");
                    break;
                case "borrow":
                    borrow( command[1] );
                    break;
                case "return":
                    returnBook( command[1] );
                    System.out.println("Your book has been returned.");
                    break;
                case "bye":
                    bye();
                    break;
                case "stats":
                    stats();
                    break;
                default:
                    System.out.println("Invalid command, please try again");
                    break;
            }
            
            System.out.print("> ");
        }
    }
}