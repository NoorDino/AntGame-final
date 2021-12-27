package com.mycompany.a4;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class SoundCommand extends Command{
	private GameWorld gw;
    
    public SoundCommand(GameWorld gw) {
        super("Sound");
        this.gw = gw;
    }
    
    @Override
    public void actionPerformed(ActionEvent ev) {
        gw.toggleSound();
		System.out.println("Sound toggled!");
    }
}
