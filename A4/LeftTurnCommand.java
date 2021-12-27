package com.mycompany.a4;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class LeftTurnCommand extends Command{
	private GameWorld gw;
    
    public LeftTurnCommand(GameWorld gw) {
        super("Turn Left");
        this.gw = gw;
    }
    
    @Override
    public void actionPerformed(ActionEvent ev) {
        gw.headLeft();
		System.out.println("Turning Left!");
    }
}
