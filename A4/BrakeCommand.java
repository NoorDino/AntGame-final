package com.mycompany.a4;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class BrakeCommand extends Command{
	private GameWorld gw;
    
    public BrakeCommand(GameWorld gw) {
        super("Braking");
        this.gw = gw;
    }
    
    @Override
    public void actionPerformed(ActionEvent ev) {
        gw.brake();
		System.out.println("Ant is braking!");
    }
}
