package com.mycompany.a4;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class ClockTickCommand extends Command{
	private GameWorld gw;
    
    public ClockTickCommand(GameWorld gw) {
        super("Tick");
        this.gw = gw;
    }
    
    @Override
    public void actionPerformed(ActionEvent ev) {
        gw.tick();
		System.out.println("Clock Ticked!");
    }
}
