package com.mycompany.a4;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;
import com.codename1.ui.Label; 
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent; 
import java.lang.String;

public class Game extends Form implements Runnable{ //implements runnable for auto ticking
	private GameWorld gw;
	private MapView mv; //this will display the graphics
	private ScoreView sv; //this will display game state information at the top
    UITimer timer = new UITimer(this);

	public Game() {
		this.setLayout(new BorderLayout());
		gw = new GameWorld();
		mv = new MapView();
		sv = new ScoreView();
		
		gw.addObserver(mv); //implement addObserver
		gw.addObserver(sv);
		
		
		add(BorderLayout.NORTH, sv);
		add(BorderLayout.CENTER, mv);
		
		
		menu();
		leftContainer();
		bottomContainer();
		rightContainer();
		
		this.show(); //swapped order of show and gw.init
		gw.init();
		
		gw.createSound();
		revalidate();
		

	}
	
	private void menu() {
        Toolbar tb = new Toolbar();
        this.setToolbar(tb);
        
        tb.setTitle("The Ant Game");
        
        //accelerate to side menu
        AccelerateCommand myAccCommand = new AccelerateCommand(gw);
        tb.addCommandToLeftSideMenu(myAccCommand);
        
        //Sound command
        CheckBox soundCheckBox = new CheckBox();
        soundCheckBox.getAllStyles().setBgTransparency(255);
        soundCheckBox.getAllStyles().setBgColor(ColorUtil.LTGRAY);
        
        
        SoundCommand mySoundCommand = new SoundCommand(gw);
        soundCheckBox.setCommand(mySoundCommand);
        tb.addComponentToSideMenu(soundCheckBox);
        
        
        // about
        AboutCommand abtCommand = new AboutCommand();
        tb.addCommandToLeftSideMenu(abtCommand);
        // exit
        ExitCommand exitCommand = new ExitCommand(gw);
        tb.addCommandToLeftSideMenu(exitCommand);
        
        // help
        HelpCommand helpCommand = new HelpCommand();
        tb.addCommandToRightBar(helpCommand);        
    }
	
    private void leftContainer() {
    	Container leftContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        leftContainer.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));

        // MAKING ACCELERATE BUTTON
        AccelerateCommand myAccCommand = new AccelerateCommand(gw);
        Button buttonAcc = new Button(myAccCommand);
        buttonAcc.getAllStyles().setBgTransparency(255);
        buttonAcc.getAllStyles().setPadding(TOP, 5);
        buttonAcc.getAllStyles().setPadding(BOTTOM, 5);
        buttonAcc.getAllStyles().setBgColor(ColorUtil.BLUE);
        buttonAcc.getAllStyles().setFgColor(ColorUtil.WHITE);
        buttonAcc.getAllStyles().setMarginTop(100);
        leftContainer.add(buttonAcc);
        addKeyListener('a', myAccCommand);
        
        
        // MAKING LEFT TURN BUTTON
        
        LeftTurnCommand leftTurn = new LeftTurnCommand(gw);
        Button buttonLeft = new Button(leftTurn);
        buttonLeft.getAllStyles().setBgTransparency(255);
        buttonLeft.getAllStyles().setPadding(TOP, 5);
        buttonLeft.getAllStyles().setPadding(BOTTOM, 5);
        buttonLeft.getAllStyles().setBgColor(ColorUtil.BLUE);
        buttonLeft.getAllStyles().setFgColor(ColorUtil.WHITE);
        leftContainer.add(buttonLeft);
        addKeyListener('l', leftTurn);
        
        add(BorderLayout.WEST, leftContainer);
    }
    
    private void rightContainer() {
    	Container rightContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    	rightContainer.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));

        // MAKING Brake BUTTON
        BrakeCommand myBrakeCommand = new BrakeCommand(gw);
        Button buttonBrake = new Button(myBrakeCommand);
        buttonBrake.getAllStyles().setBgTransparency(255);
        buttonBrake.getAllStyles().setPadding(TOP, 5);
        buttonBrake.getAllStyles().setPadding(BOTTOM, 5);
        buttonBrake.getAllStyles().setBgColor(ColorUtil.BLUE);
        buttonBrake.getAllStyles().setFgColor(ColorUtil.WHITE);
        buttonBrake.getAllStyles().setMarginTop(100);
        rightContainer.add(buttonBrake);
        addKeyListener('b', myBrakeCommand);
        
        
        // MAKING Right TURN BUTTON
        
        RightTurnCommand rightTurn = new RightTurnCommand(gw);
        Button buttonLeft = new Button(rightTurn);
        buttonLeft.getAllStyles().setBgTransparency(255);
        buttonLeft.getAllStyles().setPadding(TOP, 5);
        buttonLeft.getAllStyles().setPadding(BOTTOM, 5);
        buttonLeft.getAllStyles().setBgColor(ColorUtil.BLUE);
        buttonLeft.getAllStyles().setFgColor(ColorUtil.WHITE);
        rightContainer.add(buttonLeft);
        addKeyListener('r', rightTurn);
        
        add(BorderLayout.EAST, rightContainer);
    }
    
    private void bottomContainer() {
    	Container bottomContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
    	bottomContainer.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
    	
    	Button btnPause = new Button("Pause");
		btnPause = bothSide(btnPause);
		btnPause.getAllStyles().setMarginLeft(850);
		PauseCommand pause  = new PauseCommand(gw,this,btnPause);
		btnPause.setCommand(pause);
		bottomContainer.add(btnPause);
		
		//Position Button
		PositionCommand position = new PositionCommand(gw);
		Button btnPosition = new Button(position);
		btnPosition = bothSide(btnPosition);
		bottomContainer.add(btnPosition);

        
        add(BorderLayout.SOUTH, bottomContainer);
        //UITimer timer = new UITimer(this);
        timer.schedule(20, true, this);

    }
    private Button bothSide(Button obj) {
		obj.getAllStyles().setBgTransparency(255);
		obj.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		obj.getAllStyles().setBorder(Border.createLineBorder(3,ColorUtil.rgb(0, 0, 0)));
		obj.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		obj.getAllStyles().setPadding(TOP, 5);
		obj.getAllStyles().setPadding(BOTTOM, 5);
		return obj;
	}

	@Override
	public void run() {
		// should invoke the tick() method from gameWorld
		gw.tick();
	}
	public void pressedPause() {
		if (!gw.getPause()) {
			timer.cancel();
			gw.setPause(!gw.getPause());
		}
		else {
			timer.schedule(20, true, this);
			gw.setPause(!gw.getPause());
		}
	}
}

//bottom container stuff, no longer needed

//CollideFlagCommand collideFlag = new CollideFlagCommand(gw);
//Button flagButton = new Button(collideFlag);
//flagButton.getAllStyles().setMarginLeft(400);
//flagButton.getAllStyles().setPadding(TOP, 5);
//flagButton.getAllStyles().setPadding(BOTTOM, 5);
//flagButton.getAllStyles().setBgTransparency(255);
//flagButton.getAllStyles().setBgColor(ColorUtil.BLUE);
//flagButton.getAllStyles().setFgColor(ColorUtil.WHITE);
//bottomContainer.add(flagButton);
//
//CollideSpiderCommand collideSpider = new CollideSpiderCommand(gw);
//Button spiderButton = new Button(collideSpider);
//spiderButton.getAllStyles().setBgTransparency(255);
//spiderButton.getAllStyles().setPadding(TOP, 5);
//spiderButton.getAllStyles().setPadding(BOTTOM, 5);
//spiderButton.getAllStyles().setBgColor(ColorUtil.BLUE);
//spiderButton.getAllStyles().setFgColor(ColorUtil.WHITE);
//bottomContainer.add(spiderButton);
//addKeyListener('g', collideSpider);
//
//	CollideFoodCommand collideFood = new CollideFoodCommand(gw);
//Button foodButton = new Button(collideFood);
//foodButton.getAllStyles().setBgTransparency(255);
//foodButton.getAllStyles().setPadding(TOP, 5);
//foodButton.getAllStyles().setPadding(BOTTOM, 5);
//foodButton.getAllStyles().setBgColor(ColorUtil.BLUE);
//foodButton.getAllStyles().setFgColor(ColorUtil.WHITE);
//bottomContainer.add(foodButton);
//addKeyListener('f', collideFood);
//
//	ClockTickCommand clockTick = new ClockTickCommand(gw);
//Button tickButton = new Button(clockTick);
//tickButton.getAllStyles().setBgTransparency(255);
//tickButton.getAllStyles().setPadding(TOP, 5);
//tickButton.getAllStyles().setPadding(BOTTOM, 5);
//tickButton.getAllStyles().setBgColor(ColorUtil.BLUE);
//tickButton.getAllStyles().setFgColor(ColorUtil.WHITE);
//bottomContainer.add(tickButton);
//addKeyListener('t', clockTick);
