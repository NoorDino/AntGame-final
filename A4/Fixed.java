package com.mycompany.a4;
import com.codename1.charts.models.Point;

public abstract class Fixed extends GameObject{
	private boolean selected;
	Fixed(){
		super();
	}
	
	Fixed(int size, Point p){
		super(size,p);
	}		
	@Override
	public void changeLocation(Point p) {
		if(selected) 
			super.changeLocation(p);
		}
	public void setSelected(boolean y) {
		selected = y;
	}
	public boolean isSelected() {
		return selected;
	}
	
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		int radius = super.getSize() / 2;
		int left = (int)Math.round(super.getLocation().getX() - radius + pCmpRelPrnt.getX());
		int right = left + super.getSize();
		int top = (int)Math.round(super.getLocation().getY() - radius + pCmpRelPrnt.getY());
		int bottom = top + super.getSize();
		boolean checkedLR = pPtrRelPrnt.getX() > left && pPtrRelPrnt.getX() < right;
		boolean checkedTB = pPtrRelPrnt.getY() > top && pPtrRelPrnt.getY() < bottom;
		return checkedLR && checkedTB;
	}

}
