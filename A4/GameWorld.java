package com.mycompany.a4;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import java.util.Vector;

import com.codename1.charts.models.Point;

public class GameWorld extends Observable{
	
	private int clockTicks = 0;
	private int actualClockTicks = 0;
	private int decFoodTimer = 0;
	private int lives = 3;
	private int lastFlag = 4;
	private boolean sound = false;
	private boolean pause = false;
	private boolean position = false;
	Ant ant;
	private GameCollection gameCollection;
	
	//sounds objects
	private Sound antCry;
	private Sound antEat;
	private Sound antFlag;
	private BGSound antBackground;

	
	
	//create vectors to store object collision
	Vector<GameObject> listObj1 = new Vector<GameObject>();
	Vector<GameObject> listObj2 = new Vector<GameObject>();

	
	public void init() {
		
		//myGameObjects = new ArrayList<GameObject>();
		populateGame();
		updateObservers();
		//code here to create the       
		//initial game objects/setup
		
	}
	
		// additional methods here to    
	    // manipulate world objects and
	    // related game state data
	public void populateGame() {
		gameCollection = new GameCollection();
		Spider spider1 = new Spider();
		Spider spider2 = new Spider();
		
		FoodStation station1 = new FoodStation();
		FoodStation station2 = new FoodStation();
		
		Point start = new Point(200,200);
		Flag flag1 = new Flag(80, start); // flags used to be 10, gonna try 80
		Point p = new Point(200,800);
		Flag flag2 = new Flag(80, p);
		p = new Point(700,800);
		Flag flag3 = new Flag(80, p);
		p = new Point(900,400);
		Flag flag4 = new Flag(80, p);
		
		ant = Ant.getAnt(50,start); //singleton . 50 used to be 5, messing with sizes here
		
		gameCollection.add(flag1);
		gameCollection.add(flag2);
		gameCollection.add(flag3);
		gameCollection.add(flag4);
		gameCollection.add(ant);
		gameCollection.add(spider1);
		gameCollection.add(spider2);
		gameCollection.add(station1);
		gameCollection.add(station2);	
	}
	
    public void showMap() {
        IIterator theElements = gameCollection.getIterator();
        while ( theElements.hasNext() )
        { 
            GameObject obj = (GameObject) theElements.getNext() ;
            System.out.println ( obj ) ; 
        } 
    }
	public void gameState() {
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("Game State");
		System.out.println("Lives Remaining: " + lives);
		System.out.println("Time: " + clockTicks);
		System.out.println("Highest Flag Reached: " + ant.getLastFlag());
		System.out.println("Current Food Level: " + ant.getFoodLevel());
		System.out.println("Health Level: " + ant.getHealthLevel());
		System.out.println("-----------------------------------------------------------------------------");
	}
	
	//---------------------------------------------------------------
	//----Map and game state above --- rest of the commands below ---
	//---------------------------------------------------------------
	
	public void accelerate() {
		ant.accelerate();
		System.out.println("Ant is accelerating!");
		updateObservers();
	}
	public void brake() {
		ant.brake();
		System.out.println("Ant is braking!");
		updateObservers();

	}
	public void headLeft() {
		ant.turnLeft();
		System.out.println("Turning left!");
		updateObservers();
	}
	public void headRight() {
		ant.turnRight();
		System.out.println("Turning right!");
		updateObservers();
	}
	public void collideFlag(int num) {
		if((ant.getLastFlag()+1) == num) {
			ant.updateLastFlag();
			System.out.println("Flag " + num + " has been collided with!");
		}
		if(ant.getLastFlag() == lastFlag) {  //check if the last flag was reached, you win if it is
			System.out.println("Game Over, you won!! Total Time: " + actualClockTicks);
			exit();
		}
		updateObservers();
	}
	public void collideFoodStation() {
        IIterator theElements = gameCollection.getIterator();

		while(theElements.hasNext()) {
			GameObject obj = (GameObject)theElements.getNext();
			if(obj instanceof FoodStation) {
				if(((FoodStation) obj).getCapacity() != 0) {
					ant.updateFoodLevel(((FoodStation) obj).getCapacity());
					System.out.println("Ant collided with food station. Food level up by: " + ((FoodStation) obj).getCapacity());
					((FoodStation) obj).deplete();
					gameCollection.add(new FoodStation());
					updateObservers();
					return;
				}
			}
		}
		updateObservers();
	}
	public void caughtBySpider() {
        IIterator theElements = gameCollection.getIterator();

		while(theElements.hasNext()) {
			GameObject obj = (GameObject)theElements.getNext();
			if(obj instanceof Spider) {
				ant.collideWithSpider();
				System.out.println("Ouch! Bit by a spider!");
				updateObservers();
				return;
			}
		}
		lifeLost(); //check if the any has lost a life, call init if yes
		updateObservers();
	}
	public void tick() {
		clockTicks++;//clock tick incremented
		actualClockTicks = clockTicks/50;
        IIterator theElements = gameCollection.getIterator();
		while(theElements.hasNext()) {
			GameObject obj = (GameObject)theElements.getNext();
			if(obj instanceof Spider) {
			    ((Spider) obj).crawl();
			}
			if(obj instanceof Ant) {
			    ((Ant) obj).move();
			    if(actualClockTicks == decFoodTimer) {
			    	((Ant) obj).consume();
			    	decFoodTimer++;
			    }
			    
			}
		}
		
		//going to try to have collision loops here, hope it works
		IIterator iter1 = gameCollection.getIterator();

		while(iter1.hasNext()) {
			GameObject obj1 = iter1.getNext(); //remove (GameObject) cast
			IIterator iter2 = gameCollection.getIterator();
			while(iter2.hasNext()) {
				GameObject obj2 = iter2.getNext();
				if(obj1 != obj2) {
					if(obj1.collidesWith(obj2)) { // seems the vectors that add in collisions should be in this if statement
						if(!listObj1.contains(obj2) && !listObj2.contains(obj1)) {
							listObj1.add(obj2);
							listObj2.add(obj1);
							if(obj1 instanceof Ant) {
								obj1.handleCollision(obj2, this);	
								updateObservers();

							}
						}
					}
					//if false then check the vector as well to remove obj from each others collsionVectors
//					listObj1.remove(obj2);
//					listObj2.remove(obj1);
				}
				listObj1.remove(obj2);
				listObj2.remove(obj1);
			}
		}
		
		lifeLost(); //check if the any has lost a life, call init() if yes
		if(returnSound()) {
			antBackground.play();
		}
		System.out.println("Clock Ticked, Locations updated...");
		updateObservers();
	}
	public void checkObjectType(GameObject object) {
		if(object instanceof FoodStation && ((FoodStation) object).getCapacity() != 0) {
			//collideFoodStation();
			ant.updateFoodLevel(((FoodStation) object).getCapacity());
			((FoodStation) object).deplete();
			if(returnSound()) {
				antEat.play();
			}
			gameCollection.add(new FoodStation());
			updateObservers();
		}else if(object instanceof Flag) {
			int num = ((Flag) object).getSeqNum();
			collideFlag(num);
			if(returnSound()) {
				antFlag.play();
			}
		}else if(object instanceof Spider && actualClockTicks % 2 == 0) {
			//caughtBySpider();
			ant.collideWithSpider();
			if(returnSound()) {
				antCry.play();
			}
			updateObservers();

		}
		updateObservers();
	}
	public void exitPrompt() {
		System.out.println("Do you want to exit? Press Y/N then press enter");
	}
	public void exit() {
		System.exit(0);
	}
	public void exitCancelled() {
		System.out.println("Cancel not confirmed...continue");
	}
	public void lifeLost() {
		if(lives <= 0) {
			System.out.println("Game over, you failed!!");
			exit();
		}
        IIterator theElements = gameCollection.getIterator();
		while(theElements.hasNext()) {
			GameObject obj = (GameObject)theElements.getNext();
			if(obj instanceof Flag) { //resets flag numbers so they aren't incremented based on first iteration
				((Flag) obj).resetIncrement();
			}
			if(obj instanceof Ant) {
				if(((Ant) obj).getHealthLevel() <= 0 || ((Ant) obj).getMaxSpeed() == 0 || ((Ant) obj).getFoodLevel() <= 0) {
					System.out.println("You lost a life. Game world is reinitailizing...");
					lives--;
					((Ant) obj).resetAnt(); // set and to null so new one can be created
					init();
				}
			}
		}
		updateObservers();
	}
	public int returnClockTicks() {
		return actualClockTicks; //changed from one time to actual time
	}
	public int returnLivesLeft() {
		return lives;
	}
	public int returnLastFlag() {
		return ant.getLastFlag();
	}
	public int returnFoodLevel() {
		return ant.getFoodLevel();
	}
	public int returnHealthLevel() {
		return ant.getHealthLevel();
	}
	public boolean returnSound() {
		return sound;
	}
	public void toggleSound() { //needs to control audio
		if(sound) {
			sound = false;
		}else {
			sound = true;
		}
		updateObservers();
	}
	public void turnOffSound() {
		sound = false;
		updateObservers();
	}
	public void turnOnSound() {
		sound = true;
		updateObservers();
	}
	public void createSound() {
		antCry = new Sound("antCry.wav");
		antEat = new Sound("antEat.wav");
		antFlag = new Sound("antFlag.wav");
		antBackground = new BGSound("antBackground.wav");
	}
	public GameCollection returnGameCollection() { //to send the gamecollection to mapview
		return gameCollection;
	}
	
    private void updateObservers() {
        setChanged();
        notifyObservers(this);
    }
	public void setPause(boolean newPause) {
		pause = newPause;
	}
	public boolean getPause() {
		return pause;
	}
	public void turnOnPosition() {
		if (position == true)
			position = false;
		else
			position = true;
	}
	public void click(Point clickPoint, Point originalPoint) {
		IIterator tempObject = gameCollection.getIterator();
		while(tempObject.hasNext()) {
			if(tempObject.getNext() instanceof Fixed) {
				Fixed temp = (Fixed)tempObject.getNext();
				if(position && temp.isSelected()) {
					float newX = clickPoint.getX() - originalPoint.getX();
					float newY = clickPoint.getY() - originalPoint.getY();
					Point newPoint = new Point(newX, newY);
					temp.changeLocation(newPoint);
					position = false;
					temp.setSelected(false);
				} else {	
					if(temp.contains(clickPoint, originalPoint)) 
						temp.setSelected(true);
					 else 
						temp.setSelected(false);
				}
			}
		}
		
		updateObservers();
	}
}
