/**
 * info1103 - assignment 3
 * <Xuewei Yang>
 * <450045220>
 */

public class Tree {

	private int fire;
	private int height;
	private boolean burntDown;
	private boolean isSpread; // check does the tree on fire is spread;
	private boolean canSpread;// check does the tree can spread fire;
	
	public Tree(int height) {
		this.height = height;
		this.fire = 0;
		this.burntDown = false;
		this.isSpread = false;
		this.canSpread = true;
	}
	
	public boolean getBurnt(){
		return this.burntDown;
	}
	
	public void setBurntDown(boolean burnt){
		this.burntDown = burnt;
	}
	
	public int getHeight(){
		return this.height;
	}
	
	public void setHeight(int height){
		this.height = height;
	}
	
	public int getFire(){
		return this.fire;
	}
	
	public void setFire(int level){
		this.fire = level;
	}
	
	public int getPollution(){
		int pollution = this.fire - this.height;
		if ( this.getBurnt() == true){
			return 0;
		}else{
			return pollution;
		}
	}
	
	public boolean getIsSpread(){
		return this.isSpread;
	}
	
	public void setIsSpread(boolean result){
		this.isSpread = result;
	}
	
	public boolean getCanSpread(){
		return this.canSpread;
	}
	
	public void setCanSpread(boolean result){
		this.canSpread = result;
	}
}
