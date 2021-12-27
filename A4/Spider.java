package com.mycompany.a4;
import java.util.Random;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Spider extends Moveable{
	private static int count = 0; //the spiders were too jittery so this stabalized them a bit
	Spider(){
		super();
		Random random = new Random();
		int x = random.nextInt(5) + 8; // random integer between 5 & 10, 20 was 5
		setSpeed(x);
		int y = random.nextInt(359); //random integer between 0 & 359
		setHeading(y);
		setColor(ColorUtil.BLACK);
		setName("spider");
	}

	public void crawl() {
		move();
		count++;
		Random random = new Random();
		int number = random.nextInt(100) - 5; //random integer between -5 & 5
		if(count % 25 == 0) {
			setHeading(getHeading() + number);
			
		}
	}
	
	

	@Override
	public String toString() {
		float x = (float)(Math.round(getLocation().getX() * 10.0)/10.0);
		float y = (float)(Math.round(getLocation().getY() * 10.0)/10.0);
		return ("Spider: loc=" + x + "," + y + " color =[" + ColorUtil.red(getColor())+ ","+ColorUtil.green(getColor())
		+","+ColorUtil.blue(getColor())+"]" + " heading=" + getHeading() + " speed=" + getSpeed() +" size="+ getSize());
	}
	
	public void draw(Graphics g, Point pCmpRelPrnt) {
		Point loc = getLocation();
		int x = (int)loc.getX();
		int y = (int)loc.getY();

		int xLoc = (int) (pCmpRelPrnt.getX()) - (getSize()/2) + x;
		int yLoc = (int) (pCmpRelPrnt.getY()) - (getSize()/2) + y;
		g.setColor(getColor());
		
		int half = getSize()/2;
		int nPoints = 3;
		int xPoints[] = {xLoc-half, xLoc, xLoc+half};
		int yPoints[] = {yLoc-half, yLoc+half, yLoc-half};
		g.fillPolygon(xPoints, yPoints, nPoints); //unfilled triangle for the spider
	}
}
