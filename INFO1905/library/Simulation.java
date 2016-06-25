import java.util.*;

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

    public static void Edit( String input, String title ){

        Book editBook = library.get( title );
        String updateInfo = input.split(" ");
        switch ( uodateInfo[0] ){
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
        if ( borrowedBook.getBorrowed() == true ){
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
            if ( b.getBorrowed() == true ){
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
            
            switch ( command[0].toLower() ){
                case "help":
                    help();
                    break;
                case "add":
                    System.out.print("Please Type in the information as ");
                    System.out.print("following sequence");
                    System.out.println("Title,Author,ISBN,Edition,Publisher,Publiser Year");
                    String info = keyboard.nextLine();
                    add( command[1], info);
                    break;
                case "edit":
                    System.out.println( "Please enter which one you would like to edit,Edition or Publisher" );
                    String input = keyboard.nextLIne();
                    Edit( input , command[1] );
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