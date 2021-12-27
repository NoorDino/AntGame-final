package com.mycompany.a4;
import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Graphics;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Border;

public class MapView extends Container implements Observer{
	private GameWorld gwVar;
	private GameCollection gameColl;

	public MapView() {
		this.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.rgb(255, 0, 0)));
		this.setLayout(new BorderLayout());
		this.getAllStyles().setBgTransparency(255);
	}
	
	public int getContainerHeight() {
		int y = Display.getInstance().getDisplayHeight();
		return y;
	}
	
	public int getContainerWidth() {
		int x = Display.getInstance().getDisplayWidth();
		return x;
	}
	
	@Override  //paint iterates through game objects and invoke draw() in each of them
	public void paint(Graphics g) {
		Point pCmpRelPrnt = new Point(getX(),getY());
		gameColl = gwVar.returnGameCollection();
        IIterator theElements = gameColl.getIterator();
        while ( theElements.hasNext() )
        { 
            GameObject obj = (GameObject) theElements.getNext() ;
            obj.draw(g, pCmpRelPrnt);
        } 
	}
	
	@Override
	public void update(Observable observable, Object data) {  //called automatically when notify observer is called
		System.out.println("_________________________________________________________________________");
        System.out.println("________________________Showing Map______________________________________");
        gwVar = (GameWorld) data; // not sure about this line here
        ((GameWorld) data).showMap();
        this.repaint();
	}
	
}
