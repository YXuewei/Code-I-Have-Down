import java.util.*;

public class MatchingReadings{

    public   static int countMatchingReadings(List<Report> reportList, int startTime, int endTime) {
        //TODO: return the number of readings which were logged between startTime and endTime (inclusive)
        int diff = endTime - startTime;
        int count = 0;
        for ( Report rep : reportList){
            if ( rep.getTime() <= diff ){
                count++;
            }
        }

        return count;
    }

    public static int maxMatchingReadings(List<Report> reportList, int startTime, int endTime) {
        //TODO: return the maximum value of any reading which was logged between startTime and endTime (inclusive)  
        int diff = endTime - startTime;
        int max = Integer.MIN_VALUE;
        for ( Report rep : reportList ){
            if ( rep.getTime() <= diff ){
                if ( rep.getValue() > max ){
                    max = rep.getValue();
                }
            } 
        }

        return max;

    }

    public static int modeMatchingReadings(List<Report> reportList, int startTime, int endTime) {
        //TODO: return the mode (most common value) of any reading which was logged between startTime and endTime (inclusive)  
        int diff = endTime - startTime;
        HashMap< Integer, Integer > mode = new HashMap<Integer, Integer>();
        for ( Report rep : reportList ){
            if ( rep.getTime() <= diff ){
                if ( mode.containsKey( rep.getValue() ) ){
                    int value = mode.get( rep.getValue() ) + 1;
                    mode.put( rep.getValue(), value );
                }else{
                    mode.put( rep.getValue(), 1);
                }
            }
        }

        int max = Integer.MIN_VALUE;
        Collection<Integer> values = mode.values();
        for ( Integer i : values ){
            if ( i > max ){
                max = i;
            }
        }

        return max;
    }

}