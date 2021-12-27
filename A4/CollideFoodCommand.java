package com.mycompany.a4;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CollideFoodCommand extends Command{
	private GameWorld gw;
    
    public CollideFoodCommand(GameWorld gw) {
        super("Collide with Food station");
        this.gw = gw;
    }
    
    @Override
    public void actionPerformed(ActionEvent ev) {
        gw.collideFoodStation();
		System.out.println("Colliding with food station!");
    }
}
