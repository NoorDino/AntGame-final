package com.mycompany.a4;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class ExitCommand extends Command{
	private GameWorld gw;

	public ExitCommand(GameWorld gw) {
        super("Exit");
        this.gw = gw;

    }
    
    public void actionPerformed(ActionEvent e)
    {
        String result = "";
        
        result = "Would you like to end the game?";
        boolean c = Dialog.show("Cancel", result, "Ok", "CANCEL");
        if (c) {
        	this.gw.exit();
        }
    }
}

//public void actionPerformed(ActionEvent e)
//{
//    String result = "";
//    
//    result = "Would you like to end the game?";
//    Dialog.show("Cancel", result, "Ok", "CANCEL");
//}
