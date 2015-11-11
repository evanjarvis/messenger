package messenger;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Me
 */
public class Session extends JFrame{
    Group group;
    /**
     * Add a user to the session so they can read messages.
     * @param u the user to be added
     */
    void addUser(User u){
        group.addUser(u);
    }
    /**
     * Save information about a session including members and recent messages.
     */
    void saveSession(){
        
    }
    void showGUI(){
        // Reads Image From File
        setLayout(new BorderLayout());
	JLabel background=new JLabel(new ImageIcon("build/BTBPoly.jpg"));
       
        final int TEXT_FIELD_WIDTH = 25;
        
        final JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(new JLabel("Messages:"), BorderLayout.NORTH);
        JTextArea messageField = new JTextArea(10, 25);
        messageField.setEditable(false);
        JScrollPane scroller = new JScrollPane(messageField);
        topPanel.add(messageField, BorderLayout.CENTER);
        topPanel.setBackground(new Color(0xA7B23C));
        
        // Add image to background
        topPanel.add(background);
	background.setLayout(new FlowLayout());
        
        final JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        bottomPanel.setLayout(new BorderLayout());
        final JTextField entryField = new JTextField(TEXT_FIELD_WIDTH);
        bottomPanel.add(entryField, BorderLayout.WEST);
        JButton sendButton = new JButton("Send");
        bottomPanel.add(sendButton, BorderLayout.EAST);
        bottomPanel.setBackground(new Color(0x609EAA));
        
        JFrame frame = new JFrame("Messenger App");
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
