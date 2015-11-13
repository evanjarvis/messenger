package messenger;

import java.awt.*;
import static java.awt.PageAttributes.ColorType.COLOR;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.Font;
import javax.swing.border.*;

/**
 * GUI manager for messenger application.
 * @author Me
 */
public class GUI extends JFrame {
    /**
     * Create and display the main frame for the messenger program.
     */
    protected void showGUI(){
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
    /**
     * Creates Guest interface
     */
    protected void showGuestGUI(){
        // Reads Image From File
        setLayout(new BorderLayout());
	JLabel background=new JLabel(new ImageIcon("build/BTBSmall.jpg"));
       
        final int TEXT_FIELD_WIDTH = 25;
        
        final JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(new JLabel("Messages:"), BorderLayout.NORTH);
        JTextArea messageField = new JTextArea(10, 25);
        messageField.setEditable(false);
        JScrollPane scroller = new JScrollPane(messageField);
        topPanel.add(messageField, BorderLayout.CENTER);
        
        
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
        
        JFrame frame = new JFrame("BTB Messenger App (Guest)");
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    /**
     * Show the startup screen.  Users may choose to login, register a new account, or continue as a guest.
     */
    protected void showStartupFrame(){
        int LABEL_WIDTH = 10;
        int TEXT_FIELD_WIDTH = 20;
        
        //setup containers
        JPanel container = new JPanel(new GridLayout(0, 1));
        JPanel loginWrapper = new JPanel(new GridLayout(0, 1));
        JPanel innerLoginPane = new JPanel(new GridLayout(0, 2, 5, 5));
        JPanel loginPane = new JPanel(new BorderLayout());
        JPanel bottomPane = new JPanel(new FlowLayout());
        loginPane.setBorder(new BevelBorder(BevelBorder.RAISED));
        loginWrapper.setBorder(new EmptyBorder(10, 10, 10, 10));
        loginWrapper.setMaximumSize(new Dimension(200, 200));
        bottomPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        //setup username and password fields
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        final JTextField usernameField = new JTextField(LABEL_WIDTH);
        final JPasswordField passwordField = new JPasswordField(TEXT_FIELD_WIDTH);
        usernameLabel.setLabelFor(usernameField);
        passwordLabel.setLabelFor(passwordField);
        
        //create the buttons
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");
        JButton guestButton = new JButton("Contine as Guest");
        
        //set button actions
        loginButton.addActionListener(new ActionListener(){
            @Override
            //TO DO: Code login logic here
            public void actionPerformed(ActionEvent e) {
                char[] input = passwordField.getPassword();
                //System.out.println("Test Password Username Login " + input);
                
                String inputUserName = usernameField.getText();
                //System.out.println("Test UserName Login " + inputUserName);
               
                //validate password
                //Zero out the possible password, for security.
                Arrays.fill(input, '0');
                //passwordField.selectAll();
            }
        });
        registerButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                //open registeration window
                RegistrationFrame registration = new RegistrationFrame();
                registration.showGUI();
            }
        });
        guestButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                showGuestGUI();
            }
        });     
        
        //layout
        innerLoginPane.add(usernameLabel);
        innerLoginPane.add(usernameField);
        innerLoginPane.add(passwordLabel);
        innerLoginPane.add(passwordField);
        loginPane.add(innerLoginPane, BorderLayout.NORTH);
        loginPane.add(loginButton);
        loginWrapper.add(loginPane);
        bottomPane.add(registerButton);
        bottomPane.add(guestButton);
        container.add(loginWrapper);
        container.add(bottomPane);
        
        JFrame frame = new JFrame("Login or Register");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(container);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
