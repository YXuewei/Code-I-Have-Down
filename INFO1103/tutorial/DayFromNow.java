import java.util.*;
import java.lang.*;

public class DayFromNow{
   		
	enum Week{
			   SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
	}
    
	public static void main(String[] args){
		int currentDay;
		int nextDay = 0;
		try{
			if(args.length == 2){
				currentDay = Integer.parseInt(args[0]);				
				nextDay = (currentDay + Integer.parseInt(args[1])) % 7;
			}else{
				currentDay = Integer.parseInt(args[0]);
			}
		}catch (NumberFormatException ex){
			System.out.println("Invalid output");
			return;
		}
		for(Week day : EnumSet.range(Week.SUNDAY, Week.SATURDAY)){
			if(day.ordinal() == currentDay){
				System.out.println("Day " + currentDay + " is " + day);
				if(args.length == 2){
					for(Week next : EnumSet.range(Week.SUNDAY, Week.SATURDAY)){
						if(next.ordinal() == nextDay){
						System.out.println("And in " + args[1] + " days it will be " + next);
						}
						
					}
					
				}
			}
		}
	}
}