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
            private String message;

    /**
     * Create and display the main frame for the messenger program.
     */
    protected void showMessengerGUI(){
        // Reads Image From File
        setLayout(new BorderLayout());
	final JLabel background = new JLabel(new ImageIcon("/Users/DiegoB/Dropbox/Computerscience/ComputerScience240/messenger/BTBPoly.jpg"));
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
        JButton backButton = new JButton("Back");
        topPanel.add(backButton, BorderLayout.EAST);
        
        backButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
            //open registeration window
            showStartupFrame();  
            }
        });

        
        sendButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
            //open registeration window
            message = entryField.getText();
            JLabel jlabel = new JLabel(message);
            jlabel.setFont(new Font("Helvetica",1,20));

            topPanel.add(jlabel);            
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
    /**
     * Creates Guest interface
     */
    protected void showGuestGUI(){
        // Reads Image From File
        setLayout(new BorderLayout());
	JLabel background=new JLabel(new ImageIcon("/Users/DiegoB/Dropbox/Computerscience/ComputerScience240/messenger/BTBSmall.jpg"));
       
        final int TEXT_FIELD_WIDTH = 25;
        
        final JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(new JLabel("Messages:"), BorderLayout.NORTH);
        JTextArea messageField = new JTextArea(10, 25);
        messageField.setEditable(false);
        JScrollPane scroller = new JScrollPane(messageField);
        topPanel.add(messageField, BorderLayout.CENTER);
        
        
         //Add image to background
        topPanel.add(background);
	background.setLayout(new FlowLayout());
        
       
        
        final JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        bottomPanel.setLayout(new BorderLayout());
        final JTextField entryField = new JTextField(TEXT_FIELD_WIDTH);
        bottomPanel.add(entryField, BorderLayout.WEST);
        JButton sendButton = new JButton("Send");
        bottomPanel.add(sendButton, BorderLayout.EAST);
        
        
        
        JFrame frame = new JFrame("Messenger App");
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
        
        setLayout(new BorderLayout());
	JLabel background=new JLabel(new ImageIcon("/Users/DiegoB/Dropbox/Computerscience/ComputerScience240/messenger/BTBSmall.jpg"));
        
       
        
        //setup containers
        JPanel container = new JPanel(new GridLayout(0, 1));
        
        //container.add(background);
	//background.setLayout(new FlowLayout());
        
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
                showRegistrationFrame();
            }
        });
        guestButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                showGuestGUI();
                
                //start guest sesssion
                //TODO add code here
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
    
    /**
     * Display the screen for registering a new user.
     */
    protected void showRegistrationFrame(){
        int LABEL_WIDTH = 10;
        int TEXT_FIELD_WIDTH = 20;
                
        final JPanel formPanel = new JPanel(new GridLayout(0, 2, 6, 6));
        
        //make the registration 
        JLabel fn = new JLabel("First Name:");
        final JTextField firstName = new JTextField(TEXT_FIELD_WIDTH);
        fn.setLabelFor(firstName);
        formPanel.add(fn); formPanel.add(firstName);
        JLabel ln = new JLabel("Last Name:");
        final JTextField lastName = new JTextField(TEXT_FIELD_WIDTH);
        ln.setLabelFor(lastName);
        formPanel.add(ln); formPanel.add(lastName);
        JLabel u = new JLabel("Username:");
        final JTextField username = new JTextField(TEXT_FIELD_WIDTH);
        u.setLabelFor(username);
        formPanel.add(u); formPanel.add(username);
        JLabel pw = new JLabel("Password:");
        final JPasswordField password = new JPasswordField(TEXT_FIELD_WIDTH);
        pw.setLabelFor(password);
        formPanel.add(pw); formPanel.add(password);
        JLabel cp = new JLabel("Confirm Password:");
        final JPasswordField confirmPassword = new JPasswordField(TEXT_FIELD_WIDTH);
        cp.setLabelFor(confirmPassword);
        formPanel.add(cp); formPanel.add(confirmPassword);
        
        //make the signup button
        JButton registerButton = new JButton("Sign Up");
        registerButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                if(Arrays.equals(password.getPassword(), confirmPassword.getPassword())){
                    //register
                    User newUser = new User(firstName.getText(), lastName.getText(), username.getText(), confirmPassword.getPassword());
                    try {
                        newUser.Register();
                        JOptionPane.showMessageDialog(formPanel, "Registration Successful!");
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(formPanel, "Registration error.");
                    }
                } else {
                    JOptionPane.showMessageDialog(formPanel, "Passwords must match.");
                }
            }
        });
        formPanel.add(registerButton);
        
        JFrame registrationFrame = new JFrame("Register");
        registrationFrame.add(formPanel);
        registrationFrame.setResizable(false);
        registrationFrame.pack();
        registrationFrame.setVisible(true);
    }
}
