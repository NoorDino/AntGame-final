package com.mycompany.a4;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class RightTurnCommand extends Command{
	private GameWorld gw;
    
    public RightTurnCommand(GameWorld gw) {
        super("Turn Right");
        this.gw = gw;
    }
    
    @Override
    public void actionPerformed(ActionEvent ev) {
        gw.headRight();
		System.out.println("Turning right!");
    }
}
