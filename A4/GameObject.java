package com.mycompany.a4;
import com.codename1.charts.models.Point;
import java.util.Random;


public abstract class GameObject implements IDrawable, ICollider{
	private String name; // just for collision checking
	private int size;
	private Point location; //range 0.0 - 1000.0
	private int color; //objects of same type = same color
	


	GameObject(){
		Random random = new Random();
		Random rand = new Random();
		location = new Point();
		this.size= 150 + random.nextInt(40); //30 used to be 10, messing with sizes here
		this.location.setX(rand.nextFloat() * 1520 + 100);
		this.location.setY(rand.nextFloat() * 1100 + 100);
	}

	GameObject(int size, Point p){ //to be used for ant and flags 
		this.size = size;
		this.location = p;
	}
	
	
	public void setName(String n) {name = n;} 
	public String getName() {return name;}
	public int getSize() {return size;}
	
	public Point getLocation() {return location;}
	public void changeLocation(Point p) {this.location = p;}//TODO not sure if needed
	
	public void setColor(int objColor) {this.color = objColor;}
	public int getColor() {return this.color;}
	
	public abstract String toString(); //TODO define on concrete class
	
	@Override
	public boolean collidesWith(GameObject otherObject) {
		// check to see if collision is occurring
		boolean result = false;
	
		float thisCenterX = this.getLocation().getX() + (100); //this code was for circle bounding collision
		float thisCenterY = this.getLocation().getY() + (100);
		float otherCenterX = otherObject.getLocation().getX() + (100);
		float otherCenterY = otherObject.getLocation().getY() + (100);
		
		float dx = thisCenterX - otherCenterX;
		float dy = thisCenterY - otherCenterY;
		float distBetweenCenterSqr = (dx*dx + dy*dy);

		float thisRadius = this.size/2;
		float otherRadius = otherObject.size/2;
		float radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius + otherRadius*otherRadius);
		if (distBetweenCenterSqr <= radiiSqr) {
			result = true;
		}
		return result;
	}
	
	@Override
	public void handleCollision(GameObject otherObject, GameWorld gw) {
		//System.out.println(name + " has collided with " + otherObject.name);
		gw.checkObjectType(otherObject);
	}


}

/*
@Override
public boolean collidesWith(GameObject otherObject) {
	// check to see if collision is occurring
	boolean result = false;
//	float thisCenterX = this.getLocation().getX() + (size/2); this code was for circle bounding collision
//	float thisCenterY = this.getLocation().getY() + (size/2);
//	float otherCenterX = otherObject.getLocation().getX() + (size/2);
//	float otherCenterY = otherObject.getLocation().getY() + (size/2);
	
	float T1 = this.getLocation().getY(); //this object points
	float L1 = this.getLocation().getX();
	float R1 = this.getLocation().getX() + this.size;
	float B1 = this.getLocation().getY() + this.size;
	
	float T2 = otherObject.getLocation().getY(); //Other object points
	float L2 = otherObject.getLocation().getX();
	float R2 = otherObject.getLocation().getX() + otherObject.size;
	float B2 = otherObject.getLocation().getY() + otherObject.size;

	if((R1 < L2 || L1 > R2) && (T2 < B1 || T1 < B2)) {
		return result;
	}
	result  = true;
	return result;
}
*/