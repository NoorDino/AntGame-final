package com.mycompany.a4;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class HelpCommand extends Command{
	public HelpCommand() {
        super("Help");
    }
    
    public void actionPerformed(ActionEvent e)
    {
        String result = "";
        
        result = "a - accelerate\n"
                + "b - brake\n"
                + "l - turn left\n"
                + "r - turn right\n"
                + "f - collide with food\n"
                + "g - collide with spider\n"
                +"t - tick\n";
        Dialog.show("Command Keys", result, "Ok", null);
    }
}
