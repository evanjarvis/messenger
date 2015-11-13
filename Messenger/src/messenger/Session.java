package messenger;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
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
   protected void showMessengerGUI(){
       // Reads Image From File
        setLayout(new BorderLayout());
        final JLabel background = new JLabel(new ImageIcon("build/Images/BTBPoly.jpg"));
        final int TEXT_FIELD_WIDTH = 25;
        
        final JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        //topPanel.add(new JLabel("Messages:"), BorderLayout.NORTH);
        final JTextArea messageField = new JTextArea(10, 25);
        messageField.setEditable(false);
        JScrollPane scroller = new JScrollPane(messageField);
        topPanel.setBackground(new Color(0xA7B23C));
        topPanel.add(messageField, BorderLayout.CENTER);
        
        // Add image to background
        messageField.add(background);
	background.setLayout(new FlowLayout());

        
        
        final JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        bottomPanel.setLayout(new BorderLayout());
        final JTextField entryField = new JTextField(TEXT_FIELD_WIDTH);
        bottomPanel.add(entryField, BorderLayout.WEST);
        JButton sendButton = new JButton("Send");
        bottomPanel.add(sendButton, BorderLayout.EAST);
        bottomPanel.setBackground(new Color(0x609EAA));
        JButton backButton = new JButton("Back");
        topPanel.add(backButton, BorderLayout.NORTH);
        backButton.setPreferredSize(new Dimension(40, 20));
        
        backButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
            //open registeration window
            GUI gui = new GUI();
            gui.showStartupFrame();  
            }
        });

        
        sendButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                messageField.add(background);
                String message = entryField.getText();
                messageField.append(message+ "\n");
                
            }
        });
        JFrame frame = new JFrame("Messenger App");
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
