package messenger;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;


/**
 * A simple messaging program.
 * @author Evan Jarvis
 */

//test comment here

public class Messenger {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        //create window
        GUI messengerGUI = new GUI();
        messengerGUI.showStartupFrame();
    }
    
}
