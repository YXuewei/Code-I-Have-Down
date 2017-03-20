public class StatisticsCalculator{

    private ArrayList<Integer> score;
    
    public StatisticsCalculator(){
        this.score = new ArrayList<Integer>();
    }

    public void addScore( int s ){
        this.score.add( s );
    }

    public double getAverage(){
        int size = this.score.size();
        double avg = 0;
        int sum = 0;
        for ( int i = 0; i < size; i++ ){
            sum = sum + this.score.get( i );
        }

        avg = (double) sum / ( double ) size;
        return avg;
    }

    public String getGrade(){
       int  avg = this.getAverage();
       if ( avg < 50 ){
           return "Fail";
       }else if ( avg >= 50 && avg <65 ){
           return "Pass";
       }else if ( avg >= 65 && avg < 75 ){
           return "Credit";
       }else if ( avg >= 75 && avg < 85 ){
           return "Distinction";
       }else{
           return "High Distinction";
       }
    }
}