package com.mycompany.a4;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;

public class CollideFlagCommand extends Command {
	private GameWorld gw;
    
    public CollideFlagCommand(GameWorld gw) {
        super("Collide with flag");
        this.gw = gw;
    }
    
    @Override
    public void actionPerformed(ActionEvent ev) {
        Command cOk = new Command("Ok");
        TextField myTF = new TextField();
        Dialog.show("Flag Number: ", myTF, cOk);
        String text = myTF.getText().toString();
        System.out.println("Collide with flag command invoked");
        
        try {
            int num = Integer.parseInt(text);
            gw.collideFlag(num);
        }
        catch(NumberFormatException exception){
            Dialog.show("Error", "invalid", "OK", null);
            
        }
        
    }
}
