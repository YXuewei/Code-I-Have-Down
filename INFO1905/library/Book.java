public class Books{

    private final String title;
    private final String author;
    private final long ISBN;
    private int edtion;
    private String publisher;
    private final int publishYear;
    private boolean borrowed;
    private String dueDate;

    public book (String title, String author, long isbn, int edtion, String publisher, int publishyear){
        this.title = title;
        this.author = author;
        this.ISBN = isbn;
        this.edtion = edtion;
        this.publisher = publisher;
        this.publishYear = publishYear
    }

    //get method
    
    public String getTitle(){
        return this.title;
    }

    public String getAuthor(){
        return this.author;
    }

    public long getISBN(){
        return this.ISBN;
    }

    public int getEdition(){
        this.edtion;
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

    public void setEdtion(int edtion){
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