package com.mycompany.a4;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;


public class CollideSpiderCommand extends Command{
	private GameWorld gw;
    
    public CollideSpiderCommand(GameWorld gw) {
        super("Collide with Spider");
        this.gw = gw;
    }
    
    @Override
    public void actionPerformed(ActionEvent ev) {
        gw.caughtBySpider();
		System.out.println("Colliding with spider!");
    }
}
