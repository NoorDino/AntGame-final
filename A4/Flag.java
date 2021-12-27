package com.mycompany.a4;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Flag extends Fixed{
	private int sequenceNumber = 0; //changed to static
	private static int increment = 0;
	Flag(int size, Point p){
		super(size, p);
		setColor(ColorUtil.BLUE);
		increment++;
		setSeqNum();
		setName("flag");

	}
	
	public void setSeqNum() {
		sequenceNumber = increment;
	}
	public void resetIncrement() {increment = 0;}
	public int getSeqNum() {return sequenceNumber;}
	
	@Override
	public void changeLocation(Point p) {}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		float x = (float)(Math.round(getLocation().getX() * 10.0)/10.0);
		float y = (float)(Math.round(getLocation().getY() * 10.0)/10.0);
		return ("Flag: loc=" + x + "," + y + " color =[" + ColorUtil.red(getColor())+ ","+ColorUtil.green(getColor())
		+","+ColorUtil.blue(getColor())+"]" + " size="+ getSize() + " seqNum=" + sequenceNumber);
	}

	public void draw(Graphics g, Point pCmpRelPrnt) {
		Point loc = this.getLocation();
		int x = (int)loc.getX();
		int y = (int)loc.getY();

		int xLoc = (int) (pCmpRelPrnt.getX()) + x;
		int yLoc = (int) (pCmpRelPrnt.getY()) + y;
		
		
		int half = getSize()/2;
		int nPoints = 3;
		int xPoints[] = {xLoc-half, xLoc, xLoc+half};
		int yPoints[] = {yLoc-half, yLoc+half, yLoc-half};
		
		String num = String.valueOf(getSeqNum());
		
		
		g.setColor(getColor());
		g.fillPolygon(xPoints, yPoints, nPoints); //filled triangle for the flag
		
		g.setColor(ColorUtil.YELLOW);
		g.drawString(num, xLoc - 8 , yLoc - 20);
	}
}
