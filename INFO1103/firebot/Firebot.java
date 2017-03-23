/**
 * info1103 - assignment 3
 * <Xuewei Yang>
 * <450045220>
 */

import java.util.*;

public class Firebot {

	static Integer seed;
	static int width;
	static int height;
	static int dayChanged;

    private static Scanner scan;
    private static Simulation sim;

	public static void main(String[] cmd) {
			initialise(cmd);
			status("status");
			System.out.println();
			simulateEngin();
	}

	public static void initialise(String[] args){
		
		if(args.length != 3){
			System.out.println("Usage: java Firebot <seed> <width> <height>");
			System.exit(1);
		}
		
		//parse arguments to integer
		try{
			seed = Integer.parseInt(args[0]);
			width = Integer.parseInt(args[1]);
			height = Integer.parseInt(args[2]);
		}catch (NumberFormatException e){
			System.out.println("Usage: java Firebot <seed> <width> <height>");
			System.exit(1);
		}
		
		//ensure that input is valid
		if(seed <= 0 || width <= 0 || height <= 0){
			System.out.println("Usage: java Firebot <seed> <width> <height>");
			System.exit(1);		
		}
		
		//Initialise simulation
		sim = new Simulation(seed, height, width);
		sim.countTreeNumber();
		
	}
	
	//Display all command avialable
	public static void helpCommand(String line){
		String[] args = line.split(" ");
		if ( args.length == 1){
			System.out.println("BYE");
			System.out.println("HELP");
			System.out.println();
			System.out.println("DATA");
			System.out.println("STATUS");
			System.out.println();
			System.out.println("NEXT <days>");
			System.out.println("SHOW <attribute>");
			System.out.println();
			System.out.println("FIRE <region>");
			System.out.println("WIND <direction>");
			System.out.println("EXTINGUISH <region>");
		}else {
			System.out.println("Invalid command");
			return;
		}
	}
	
	//Terminates program	
	public static void byeCommand(String line){
		String[] args = line.split(" ");
		if ( args.length != 1){
			System.out.println("Invalid command");
			return;
		}else if ( args[0].equals("bye") ){
			System.out.println("bye");
			System.exit(0);
		}else {
			System.out.println("Invalid command");
			return;
		}
	}
	
	//Set wind based on given input
	public static void setWind(String line){
		
		String[] args = line.split(" ");
		if ( args.length != 2 ){
			System.out.println("Invalid command");
			return;
		}
		
		switch( args[1]){
			case "north":
				sim.setWind("north");
				System.out.println("Set wind to " + sim.getWind() );
				break;
			case "south":
				sim.setWind("south");
				System.out.println("Set wind to " + sim.getWind() );
				break;
			case "east":
				sim.setWind("east");
				System.out.println("Set wind to " + sim.getWind() );
				break;
			case "west":
				sim.setWind("west");
				System.out.println("Set wind to " + sim.getWind() );
				break;
			case "all":
				sim.setWind("all");
				System.out.println("Set wind to " + sim.getWind() );
				break;
			case "none":
				sim.setWind("none");
				System.out.println("Set wind to " + sim.getWind() );
				break;			
			default:
				System.out.println("Invalid command");
				break;
		}
	}
	
	//Set day
	public static void next(String line){
		
		int day = sim.getDay();
		String[] args = line.split(" ");
		
		if ( args.length == 1 ){
			sim.setDay(day + 1);
			fireSpread( 1 , sim.getWind() );
			status( "status" );
		}else if ( args.length == 2){
			try {
			int increase = Integer.parseInt( args[1]);
			sim.setDay( day + increase );
			fireSpread( increase , sim.getWind() );
			status( "status" );
			}catch (NumberFormatException ex){
				System.out.println("Invalid command");
				return;
			}
		}else{
			System.out.println("Invalid command");
			return;
		}
	}
	
	//display status
	public static void status(String line){
		String[] args = line.split(" ");
		if ( args.length != 1){
			System.out.println("Invalid command");
			return;
		}else{
		System.out.println( "Day: " + sim.getDay() );
		System.out.println( "Wind: " + sim.getWind() );
		}
	}
	
	// show height or show fire
	public static void show(String line){
		String[] args = line.split(" ");
		if ( args.length != 2){
			System.out.println("Invalid command");
			return;
		}
		//show height
		if ( args[1].equals("height") ){
			System.out.print("+");
			
			// print top bundry
			for (int i = 0; i < width * 2 - 1; i++){
				System.out.print("-");
			}
			System.out.print("+");
			System.out.println();
		
			
			// print actual content
			for (int i = 0; i < height; i++){
				System.out.print("|");
				for (int j = 0; j < width; j++){
					if ( sim.getTree(i,j).getBurnt() == true ){
						System.out.print("x");
					}else if ( sim.getTree(i,j).getHeight() != 0){
						System.out.print( sim.getTree(i,j).getHeight() );
					}else{
						System.out.print(" ");
					}
					if ( j != (width - 1) ){
						System.out.print(" ");
					}
				}
				System.out.println("|");
			}
			// print bottom bundry
			System.out.print("+");
			for (int i = 0; i < width * 2 - 1; i++){
				System.out.print("-");
			}
			System.out.println("+");
		// show fire	
		}else if ( args[1].equals("fire") ){
			
			// print top bundry
			System.out.print("+");
			for (int i = 0; i < width * 2 - 1; i++){
				System.out.print("-");
			}
			System.out.print("+");
			System.out.println();
			
			//print actual content
			for (int i = 0; i < height; i ++){
				System.out.print("|");
				for( int j = 0; j < width; j++){
					if ( sim.getTree(i,j).getBurnt() == true){
						System.out.print("x");
					}else if ( sim.getTree(i,j).getFire() != 0 ){
						System.out.print( sim.getTree(i,j).getFire() );
					}else if ( sim.getTree(i,j).getBurnt() == false && sim.getTree(i,j).getHeight() != 0 ){
						System.out.print(".");
					}else {
						System.out.print(" ");
					}
					
					if (j != width - 1){
						System.out.print(" ");
					}
				}
				System.out.println("|");
			}
			
			// print bottom bundry
			System.out.print("+");
			for (int i = 0; i < width * 2 - 1; i++){
				System.out.print("-");
			}
			System.out.println("+");
			
		}else {
			System.out.println("Invalid command");
			return;
		}
		
		
	}
	
	// show data
	public static void data(String line){
		String args[] = line.split(" ");
		if (args.length != 1){
			System.out.println("Invalid command");
			return;
		}
		
		double tree = (double)sim.getTreeNumber();
		double burnt = (double)sim.getBurntNumber();
		System.out.print("Damage: ");
		System.out.printf("%.2f",(burnt / tree) * 100.00);
		System.out.println("%");
		//System.out.printf(".2%f","Damage: " + (tree / burnt) + "%");
		System.out.println("Pollution: " + sim.getTotalPollution() );
	}
	
	// spread fire based on day change
	public static void fireSpread(int dayPassed, String wind){	
		
		switch (wind){
			case "none":
				if (dayPassed == 0){
					return;	
				}else{
				for (int i = 0; i < height; i++){
					for (int j = 0; j < width; j++){
						int currentFire = sim.getTree(i,j).getFire();
						
						if ( currentFire == 9){
							if ( sim.getTree(i,j).getHeight() - 1 <= 0){
								sim.getTree(i,j).setHeight(0);
								sim.getTree(i,j).setFire(0);
								sim.getTree(i,j).setBurntDown(true);
							}else{
								int decreased = sim.getTree(i,j).getHeight() - 1;
								sim.getTree(i,j).setHeight( decreased );
							}
						}
						if ( currentFire == 0){
							continue;
						}else if ( currentFire + 1 > 9){
							sim.getTree(i,j).setFire(9);	
						}else{
							sim.getTree(i,j).setFire( currentFire + 1);
						}
					}	
				}
				int pollutionChanged = sim.computeTotalPollution();
				//System.out.println("Pollution changed is " + pollutionChanged);
				int originalPollution = sim.getTotalPollution();
				//System.out.println("OriginalPollution is " + originalPollution);
				sim.setTotalPollution( originalPollution + pollutionChanged);
				fireSpread( dayPassed - 1, wind);	
				}
				break;
			case "east":
				if (dayPassed == 0){
					return;
				}else {
					for (int i = 0; i < height; i++){
						for (int j = 0; j < width; j++){
							int currentFire = sim.getTree(i,j).getFire();
							// reduce height if fire is 9
							if ( currentFire == 9 ){
								int currentHeight = sim.getTree(i,j).getHeight();
								if ( currentHeight - 1  <= 0){
									sim.getTree(i,j).setFire(0);
									sim.getTree(i,j).setHeight(0);
									sim.getTree(i,j).setBurntDown(true);
								}else{
									sim.getTree(i,j).setHeight( currentHeight - 1 );
								}
							}
							
							if ( currentFire == 0 ){
								continue;
							}else if ( currentFire + 1 > 9 && sim.getTree(i,j).getIsSpread() == false ){
								sim.getTree(i,j).setFire(9);
							}else if ( sim.getTree(i,j).getIsSpread() == false ){
								sim.getTree(i,j).setFire( currentFire + 1 );
							}
							
							
							//spread fire
							if( j + 1 <= width - 1){
								int neibourFire = sim.getTree(i,j + 1).getFire();
								if (neibourFire == 0 && sim.getTree(i, j + 1).getHeight() != 0 && sim.getTree(i, j).getCanSpread() == true){
									sim.getTree(i,j + 1).setFire(1);
									sim.getTree(i, j + 1).setIsSpread(true);
									sim.getTree(i,j + 1).setCanSpread(false);
								}
							}
							
						}
					}
					for (int i = 0; i < height; i++){
						for (int j = 0; j < width; j++){
							sim.getTree(i,j).setIsSpread(false);
							sim.getTree(i,j).setCanSpread(true);
						}
					}
					int pollutionChanged = sim.computeTotalPollution();
					int originalPollution = sim.getTotalPollution();
					sim.setTotalPollution( originalPollution + pollutionChanged);
					fireSpread( dayPassed - 1, wind);
				}
				break;
			case "west":
				if ( dayPassed == 0 ){
					return;
				}else {
					for ( int i = 0; i < height; i++){
						for ( int j = 0; j < width; j++){
							int currentFire = sim.getTree(i,j).getFire();
							// reduce the height if fire is 9
							if ( currentFire == 9 ){
								int currentHeight = sim.getTree( i, j).getHeight();
								if ( currentHeight - 1 <= 0){
									sim.getTree( i,j ).setFire( 0 );
									sim.getTree( i, j ).setHeight( 0 );
									sim.getTree( i, j).setBurntDown(true);
								}else {
									sim.getTree( i, j).setHeight( currentHeight - 1 );
								}				
							}
							
							if ( currentFire == 0 ){
								continue;
							}else if ( currentFire + 1 > 9 && sim.getTree(i,j).getIsSpread() == false ) {
								sim.getTree( i, j).setFire(9);
							}else if ( sim.getTree(i,j).getIsSpread() == false){
								sim.getTree(i,j).setFire( currentFire + 1 );
							}
							
							
							//spread fire
							if ( j - 1 >= 0){
								int neibourFire = sim.getTree(i,j-1).getFire();
								if ( neibourFire == 0 && sim.getTree( i, j - 1 ).getHeight() != 0 && sim.getTree(i, j).getCanSpread() == true ){
									sim.getTree(i, j - 1).setFire(1);
									sim.getTree(i, j - 1).setIsSpread(true);
									sim.getTree(i, j - 1).setCanSpread(false);
								}
							}
							
						}
					}
					for (int i = 0; i < height; i++){
						for (int j = 0; j < width; j++){
							sim.getTree(i,j).setIsSpread(false);
							sim.getTree(i,j).setCanSpread(true);
						}
					}
					int pollutionChanged = sim.computeTotalPollution();
					int originalPollution = sim.getTotalPollution();
					sim.setTotalPollution( originalPollution + pollutionChanged);
					fireSpread( dayPassed - 1, wind);
				}
				break;
			case "south":
				if ( dayPassed == 0){
					return;
				}else {
					for ( int i = 0; i < height; i++){
						for (int j = 0; j < width; j++){
							int currentFire = sim.getTree(i,j).getFire();
							//reduce height if fire is 9
							if ( currentFire == 9){
								int currentHeight = sim.getTree(i,j).getHeight();
								if ( currentHeight - 1 <= 0){
									sim.getTree(i,j).setFire(0);
									sim.getTree(i,j).setHeight(0);
									sim.getTree(i,j).setBurntDown(true);
								}else {
									sim.getTree(i,j).setHeight( currentHeight - 1 );
								}
							}
							
							if ( currentFire == 0){
								continue;
							}else if ( currentFire + 1 > 9 && sim.getTree(i,j).getIsSpread() == false ){
								sim.getTree(i, j).setFire(9);
							}else if ( sim.getTree(i,j).getIsSpread() == false ){
								sim.getTree(i,j).setFire( currentFire + 1);
							}
							
							
							// spread fire
							if ( i + 1 <= height - 1){
								int neibourFire = sim.getTree(i + 1, j).getFire();
								if ( neibourFire == 0 && sim.getTree(i + 1, j).getHeight() != 0 && sim.getTree(i,j).getCanSpread() == true){
									sim.getTree(i + 1, j).setFire(1);
									sim.getTree(i + 1, j).setIsSpread(true);
									sim.getTree(i + 1,j).setCanSpread(false);
								}
							}
							
						}
					}
					for (int i = 0; i < height; i++){
						for (int j = 0; j < width; j++){
							sim.getTree(i,j).setIsSpread(false);
							sim.getTree(i,j).setCanSpread(true);
						}
					}
					int pollutionChanged = sim.computeTotalPollution();
					int originalPollution = sim.getTotalPollution();
					sim.setTotalPollution( originalPollution + pollutionChanged);
					fireSpread( dayPassed - 1, wind);
				}
				break;
			case "north":
				if ( dayPassed == 0){
					return;
				}else {
					for ( int i = 0; i < height; i++){
						for ( int j = 0; j < width; j++){
							int currentFire = sim.getTree(i,j).getFire();
							//reduce height if fire is 9
							if ( currentFire == 9){
								int currentHeight = sim.getTree(i,j).getHeight();
								if ( currentHeight - 1 <= 0){
									sim.getTree(i,j).setFire(0);
									sim.getTree(i,j).setHeight(0);
									sim.getTree(i,j).setBurntDown(true);
								}else{
									sim.getTree(i,j).setHeight( currentHeight - 1);
								}
							}
						
							if ( currentFire == 0){
								continue;
							}else if ( currentFire + 1 > 9 && sim.getTree(i,j).getIsSpread() == false ){
								sim.getTree(i,j).setFire(9);
							}else if ( sim.getTree(i,j).getIsSpread() == false){
								sim.getTree(i,j).setFire( currentFire + 1 );
							}
							
							
							//spread fire
							if ( i - 1 >= 0 ){
								int neibourFire = sim.getTree(i - 1,j).getFire();
								if ( neibourFire == 0 && sim.getTree(i - 1,j).getHeight() != 0 && sim.getTree(i,j).getCanSpread() == true){
									sim.getTree(i - 1, j).setFire(1);
									sim.getTree(i - 1, j).setIsSpread(true);
									sim.getTree(i - 1,j).setCanSpread(false);
								}
							}
						}
						
					}
					for (int i = 0; i < height; i++){
						for (int j = 0; j < width; j++){
							sim.getTree(i,j).setIsSpread(false);
							sim.getTree(i,j).setCanSpread(true);
						}
					}
					int pollutionChanged = sim.computeTotalPollution();
					int originalPollution = sim.getTotalPollution();
					sim.setTotalPollution( originalPollution + pollutionChanged);
					fireSpread ( dayPassed - 1, wind);
				}
				break;
			case "all":
				if ( dayPassed == 0){
					return;
				}else {
					for (int i = 0; i < height; i++){
						for (int j = 0; j < width; j ++){
							int currentFire = sim.getTree(i,j).getFire();
							//reduce height if fire is 9
							if ( currentFire == 9){
								int currentHeight = sim.getTree( i ,j).getHeight();
								if ( currentHeight - 1 <= 0){
									sim.getTree(i,j).setFire(0);
									sim.getTree(i,j).setHeight(0);
									sim.getTree(i,j).setBurntDown(true);
								}else {
									sim.getTree(i,j).setHeight( currentHeight - 1);
								}
							}
							
							if ( currentFire == 0){
								continue;
							}else if ( currentFire + 1 > 9 && sim.getTree(i, j).getIsSpread() == false){
								sim.getTree(i,j).setFire(9);
							}else if ( sim.getTree(i,j).getIsSpread() == false){
								sim.getTree(i,j).setFire( currentFire + 1);
							}
							
							
							//spread fire
							if ( j + 1 <= width - 1){
								int neibourFire = sim.getTree(i,j + 1).getFire();
								if ( neibourFire == 0 && sim.getTree(i ,j + 1).getHeight() != 0 && sim.getTree(i,j).getCanSpread() == true){
									sim.getTree(i, j + 1).setFire(1);
									sim.getTree(i, j + 1).setIsSpread(true);
									sim.getTree(i, j + 1).setCanSpread(false);
								}
							}
							
							if ( j - 1 >= 0){
								int neibourFire = sim.getTree(i, j - 1).getFire();
								if ( neibourFire == 0 && sim.getTree(i , j - 1).getHeight() != 0 && sim.getTree(i, j).getCanSpread() == true){
									sim.getTree(i, j - 1).setFire(1);
									sim.getTree(i, j - 1).setIsSpread(true);
									sim.getTree(i, j - 1).setCanSpread(false);
								}
							}
							
							if ( i + 1 <= height - 1){
								int neibourFire = sim.getTree( i + 1, j).getFire();
								if ( neibourFire == 0 && sim.getTree(i + 1, j).getHeight() != 0 && sim.getTree(i,j).getCanSpread() == true ){
									sim.getTree(i + 1,j).setFire(1);
									sim.getTree(i + 1,j).setIsSpread(true);
									sim.getTree(i + 1,j).setCanSpread(false);
								}
							}
							
							if ( i - 1 >= 0){
								int neibourFire = sim.getTree(i - 1,j).getFire();
								if ( neibourFire == 0  && sim.getTree(i - 1,j).getHeight() != 0 && sim.getTree(i,j).getCanSpread() == true){
									sim.getTree(i - 1,j).setFire(1);
									sim.getTree(i - 1,j).setIsSpread(true);
									sim.getTree(i - 1,j).setCanSpread(false);
								}
							}
							
						}
					}
					for ( int i = 0; i < height; i++){
						for ( int j = 0; j < width; j++){
							sim.getTree(i,j).setIsSpread(false);
							sim.getTree(i,j).setCanSpread(true);
						}
					}
					int pollutionChanged = sim.computeTotalPollution();
					int originalPollution = sim.getTotalPollution();
					sim.setTotalPollution( originalPollution + pollutionChanged);
					fireSpread( dayPassed - 1,wind);
				}
				break;
		}// end of the switch	
	}// end of fireSpread
	
	// fire trees up
	public static void fire(String line) {
		String[] args = line.split(" ");
		boolean fired = false;
		
		//fire all
		if ( args.length == 2 && args[1].equals("all") ){
			for ( int i = 0; i < height; i++){
				for (int j = 0; j < width; j++){
					if ( sim.getTree(i,j).getFire() == 0 && sim.getTree(i,j).getHeight() != 0 ){
						sim.getTree(i,j).setFire(1);
						fired = true;
					}
				}
			}
			if ( fired == true ){
				System.out.println("Started a fire");
			}else {
				System.out.println("No fires were started");
			}
			return;
		}
		
		//start fire with specific coordinates
		else if ( args.length == 3){
			int[] coordinates = new int[2];
			for ( int i = 0; i < 2; i++){
				try {
					coordinates[i] = Integer.parseInt( args[i + 1] );
					if ( coordinates[i] < 0){
						System.out.println("Invalid command");
						return;
					}
				}catch (NumberFormatException ex){
					System.out.println("Invalid command");
					return;	
				}
			}
			
			if ( coordinates[0] > width - 1 || coordinates[1] > height - 1){
				System.out.println("Invalid command");
				return;
			}
			if ( sim.getTree( coordinates[1], coordinates[0]).getHeight() != 0 && sim.getTree(coordinates[1],coordinates[0]).getFire() == 0){
				sim.getTree( coordinates[1], coordinates[0] ).setFire(1);
				fired = true;
			}	
		}
		
		//start fire with specific region
		 else if ( args.length == 5){
			int [] coordinates = new int[4];
			for ( int i = 0; i < 4; i++){
				try {
					coordinates[i] = Integer.parseInt( args[ i + 1] );
					if ( coordinates[i] < 0){
						System.out.println("Invalid command");
						return;
					}
				}catch (NumberFormatException ex){
					System.out.println("Invalid command");
					return;
				}
				
				if ( ( (i == 2) || (i == 3) ) && coordinates[i] <= 0){
					System.out.println("Invalid command");
					return;
				}else if ( ( (i == 0) && coordinates[i] > width - 1 ) || ( (i == 2) && coordinates[i] > width ) ){
					System.out.println("Invalid command");
					return;
				}else if ( ( (i == 1) && coordinates[i] > height - 1 ) || ( (i == 3) && coordinates[i] > height) ){
					System.out.println("Invalid command");
					return;	
				}
			}
			for ( int i = 0; i < coordinates[3]; i++){
				for ( int j = 0; j < coordinates[2]; j++){
					Tree now = sim.getTree( coordinates[1] + i, coordinates[0] + j);	
					if ( now.getFire() == 0 && now.getHeight() != 0 ){
						now.setFire(1);
						fired = true;
					}
				}	
			}
		 }else {
			 System.out.println("Invalid command");
			 return;
		 }
			
		if ( fired == false){
			System.out.println("No fires were started");
			return;
		}else{
			System.out.println("Started a fire");
			return;
		}
			
	}
	
	// enxtinguish all fired trees
	public static void extinguish(String line){
		String[] args = line.split(" ");
		boolean extinguished = false;
		if ( args[1].equals("all") ){
			for (int i = 0; i < height; i++){
				for ( int j = 0; j < width; j++){
					if (sim.getTree(i,j).getFire() != 0){
						sim.getTree(i,j).setFire(0);
						extinguished = true;
					}
				}
			}
		}
		
		//extinguish fire in specific coordinates
		else if ( args.length == 3){
			int[] coordinates = new int[2];
			for ( int i = 0; i < 2; i++){
				try {
					coordinates[i] = Integer.parseInt( args[ i + 1]);
					if ( coordinates[i] < 0 ){
						System.out.println("Invalid command");
						return;	
					}
				}catch (NumberFormatException ex){
					System.out.println("Invalid command");
					return;
				}
			}
			if ( coordinates[0] > width - 1){
				System.out.println("Invalid command");
				return;	
			}else if ( coordinates[1] > height - 1){
				System.out.println("Invalid command");
				return;
			}
			Tree now = sim.getTree(coordinates[1], coordinates[0]);
			if ( now.getFire() != 0 && now.getHeight() != 0){
				now.setFire(0);
				extinguished = true;
			}	
		}
		
		//extinguish fire in specific region
		else if ( args.length == 5){
			int[] coordinates = new int[4];
			for (int i = 0; i < 4;i++){
				try {
					coordinates[i] = Integer.parseInt( args[i + 1]);
					if ( coordinates[i] < 0){
						System.out.println("Invalid command");
						return;
					}
				}catch (NumberFormatException ex){
					System.out.println("Invalid command");
					return;
				}
				
				if ( ( (i == 2) || (i == 3) ) && coordinates[i] <= 0){
					System.out.println("Invalid command");
					return;
				}else if ( ( (i == 0) && coordinates[i] > width - 1 ) || ( (i == 2) && coordinates[i] > width ) ){
					System.out.println("Invalid command");
					return;
				}else if ( ( (i == 1) && coordinates[i] > height - 1 ) || ( (i == 3) && coordinates[i] > height) ){
					System.out.println("Invalid command");
					return;	
				}
			}
			for (int i = 0; i < coordinates[3];i++){
				for (int j = 0; j < coordinates[2]; j++){
					Tree now = sim.getTree( coordinates[0] + i, coordinates[1] + j);
					if ( now.getFire() != 0 ){
						now.setFire(0);
						extinguished = true;
					}
				}
			}
		}else {
			System.out.println("Invalid command");
			return;
		}
			
		if ( extinguished == false){
			System.out.println("No fires to extinguish");
		}else {
			System.out.println("Extinguished fires");
		}
	}
	
	// engin
	public static void simulateEngin(){
		scan = new Scanner (System.in);
		String command;
		while ( true ){
		
			System.out.print("> ");
			command = scan.nextLine().toLowerCase();
			String[] args = command.split(" ");
			switch ( args[0] ){
				case "help":
					helpCommand( command );
					break;
				case "bye":
					byeCommand(command);
					break;
				case "wind":
					setWind( command );
					break;
				case "next":
					next( command );
					break;
				case "status":
					status( command );
					break;
				case "show":
					show( command );
					break;
				case "data":
					data( command );
					break;
				case "fire":
					fire( command );
					break;
				case "extinguish":
					extinguish( command );
					break;
				default:
					System.out.println("Invalid command");
			}
			System.out.println();	
		}
	}
}
