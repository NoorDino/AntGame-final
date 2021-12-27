package com.mycompany.a4;
import com.codename1.charts.models.Point;

public abstract class Moveable extends GameObject {
	private int heading; //0 means north, 90 means east, 180 means south, 270 means west
	private int speed;
	private MapView mv; //seems wrong to me

	Moveable(){
		super();
	}
	
	Moveable(int size, Point p){
		super(size,p);
	}	
	
	public void setHeading(int heading) {
		this.heading = heading;
	}
	public int getHeading() {return heading;}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getSpeed() {return speed;}
	
	
	
	public void move() {
		boundaryCheck();
		float deltaX = (float) (Math.cos(Math.toRadians(90.0 - this.heading)) * this.speed);
		float deltaY = (float) (Math.sin(Math.toRadians(90.0 - this.heading)) * this.speed);
		//float deltaY = (float) (Math.sin(90.0 - this.heading) * this.speed);

		Point p = new Point(getLocation().getX()+deltaX,getLocation().getY()+deltaY);

		this.changeLocation(p);
	} 
	public void boundaryCheck() {
		 // need to change boundaries so things don't go outside
		
		if(this.getLocation().getX() + 10 >= 1660  || this.getLocation().getX() - 80 <= 0.0) {
			this.setHeading(heading+180);
			if(this.getHeading() > 360) {
				   this.setHeading(this.getHeading() - 360);
				}
			
		}
		
		if(this.getLocation().getY() + 10 >= 1200 || this.getLocation().getY() - 80 <= 0.0) {
			this.setHeading(heading+180);
			if(this.getHeading() > 360) {
				   this.setHeading(this.getHeading() - 360);
				}
			
		}
	}
}
