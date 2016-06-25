public class Book{

    private final String title;
    private final String author;
    private final String ISBN;
    private String edtion;
    private String publisher;
    private final String publishYear;
    private boolean borrowed;
    private String dueDate;

    public Book (String title, String author, String isbn, String edtion, String publisher, String publishyear){
        this.title = title;
        this.author = author;
        this.ISBN = isbn;
        this.edtion = edtion;
        this.publisher = publisher;
        this.publishYear = publishyear;
    }

    //get method
    
    public String getTitle(){
        return this.title;
    }

    public String getAuthor(){
        return this.author;
    }

    public String getISBN(){
        return this.ISBN;
    }

    public String getEdition(){
        return this.edtion;
    }

    public String getPublihser(){
        return this.publisher;
    }
    
    public String getPublishYear(){
        return this.publishYear;
    }

    public boolean getStatus(){
        return this.borrowed;
    }

    public String getDueDate(){
        return this.dueDate;
    }

    //set method

    public void setEdtion(String edtion){
        this.edtion = edtion;
    }

    public void setPublisher(String publisher){
        this.publisher = publisher;
    }

    public void setBorrowed( boolean status){
       this.borrowed = status; 
    }

    public void setDueDate( String dueDate ){
       this.dueDate = dueDate; 
    }

}