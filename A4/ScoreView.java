package com.mycompany.a4;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

public class ScoreView extends Container implements Observer{

	private Label timeVal;
	private Label remLife;
	private Label LFlagReached;
	private Label foodLevelVal;
	private Label healthLevelVal;
	private Label soundVal;
	
	public ScoreView() {
		this.setLayout(new BoxLayout(BoxLayout.X_AXIS));
		
		timeVal = new Label("Time: 0");
		timeVal.getAllStyles().setFgColor(ColorUtil.BLUE);
		timeVal.getAllStyles().setMargin(LEFT, 250);
		timeVal.getAllStyles().setPadding(RIGHT, 2);
		this.add(timeVal);
		
		remLife = new Label("Lives Left: 3");
		remLife.getAllStyles().setFgColor(ColorUtil.BLUE);
		this.add(remLife);
		
		LFlagReached = new Label("Last Flag reached: 1");
		LFlagReached.getAllStyles().setFgColor(ColorUtil.BLUE);
		this.add(LFlagReached);
		
		foodLevelVal = new Label("Food Level: 100");
		foodLevelVal.getAllStyles().setFgColor(ColorUtil.BLUE);
		this.add(foodLevelVal);
		
		healthLevelVal = new Label("Health Level: 10");
		healthLevelVal.getAllStyles().setFgColor(ColorUtil.BLUE);
		this.add(healthLevelVal);
		
		soundVal = new Label("Sound: OFF");
		soundVal.getAllStyles().setFgColor(ColorUtil.BLUE);
		this.add(soundVal);
		
		
	}

	@Override
	public void update(Observable observable, Object data) {
		this.timeVal.setText("Time: "+((GameWorld) data).returnClockTicks()+ "   ");
		this.remLife.setText("Lives Left: "+((GameWorld) data).returnLivesLeft()+"   ");
		this.LFlagReached.setText("Last Flag reached: "+((GameWorld) data).returnLastFlag()+"   ");
		this.foodLevelVal.setText("Food Level: "+((GameWorld) data).returnFoodLevel()+"   ");
		this.healthLevelVal.setText("Health Level: "+((GameWorld) data).returnHealthLevel()+"   ");

    	if(((GameWorld) data).returnSound() == true) {
			this.soundVal.setText("Sound: ON");
		}
		else {
			this.soundVal.setText("Sound: OFF");
		}
		this.repaint(); 
	}
}
