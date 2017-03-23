/**
 * info1103 - assignment 3
 * <Xuewei Yang>
 * <450045220>
 */

public class Simulation {

	private int width;
	private int height;
	private Tree[][] trees;
	private int day;
	private String wind;
	private int xLimit;
	private int yLimit;
	private int treeNumber;
	private int burntNumber;
	private int pollution;
	
	public void setWind(String direction){
		this.wind = direction;
	}
	
	public String getWind(){
		return this.wind;
	}
	
	public void setDay(int day){
		this.day = day;
	}
	
	public int getDay(){
		return this.day;
	}
	
	public Tree getTree(int height,int width){
		return this.trees[height][width];
	}
	
	public void countTreeNumber(){
		int count = 0;
		for ( int i = 0; i < height; i++ ){
			for (int j = 0; j < width; j ++){
				if ( this.getTree(i,j).getHeight() != 0){
					count++;
				}
			}
		}
		this.treeNumber = count;
	}
	
	public int getTotalPollution(){
		if ( this.pollution < 0){
			this.pollution = 0;
		}
		return this.pollution;
	}
	
	public int computeTotalPollution(){
		int count = 0;
		for (int i = 0; i < height; i++){
			for (int j = 0; j < width; j++){
				count += this.getTree(i,j).getPollution();
				//System.out.println("I is " + i + " J is " + j);
				//System.out.println( this.getTree(i,j).getFire() + " " + this.getTree(i,j).getHeight() );
			}
		}
		return count;
	}
	
	
	public void setTotalPollution(int number){
		this.pollution = number;
	}
	
	public int getTreeNumber(){
		return this.treeNumber;
	}
	
	public int getBurntNumber(){
		int count = 0;
		for (int i = 0; i < this.height; i++){
			for  ( int j = 0; j < this.width; j++){
				if ( this.getTree(i,j).getBurnt() == true){
					count++;
				}
			}
		}
		return count;
	}
	
	public void setBurntNumber(int number){
		this.burntNumber = number;
	}
	
	public Simulation(int seed, int height, int width){
		this.trees = new Tree[height][width];
		this.width = width;
		this.height = height;
		this.day = 1;
		this.wind = "none";
		this.xLimit = width - 1;
		this.yLimit = height - 1;
		generateTerrain(seed);
		this.pollution = 0;
	}

	private void generateTerrain(int seed) {

		// ###################################
		// ### DO NOT MODIFY THIS FUNCTION ###
		// ###################################

		Perlin perlin = new Perlin(seed);
		double scale = 10.0 / Math.min(width, height);

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				double height = perlin.noise(x * scale, y * scale, 0.5);
				height = Math.max(0, height * 1.7 - 0.7) * 10;
				trees[y][x] = new Tree((int) height);
			}
		}
	}
}
