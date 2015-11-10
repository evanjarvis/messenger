package messenger;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/**
 * A simple messaging program.
 * @author Evan Jarvis
 */
public class Messenger {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //create window
        GUI messengerGUI = new GUI();
        messengerGUI.showStartupFrame();
        messengerGUI.showRegistrationFrame();
        messengerGUI.showGUI();
    }
    
}

