package com.mycompany.a4;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class AboutCommand extends Command {
	
	public AboutCommand() {
        super("About");
    }
    
    public void actionPerformed(ActionEvent e)
    {
        String result = "";
        
        result = "Noordeen Abunamous\n"
                + "CSC 133\n"
                +"Version: 2.0\n";
        Dialog.show("About", result, "Ok", null);
    }
}
