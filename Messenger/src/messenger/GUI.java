package messenger;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.*;

/**
 * GUI manager for messenger application.
 * @author Me
 */
public class GUI {
    /**
     * Create and display the main frame for the messenger program.
     */
    protected void showGUI(){
        final int TEXT_FIELD_WIDTH = 25;
        
        final JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(new JLabel("Messages:"), BorderLayout.NORTH);
        JTextArea messageField = new JTextArea(10, 25);
        messageField.setEditable(false);
        JScrollPane scroller = new JScrollPane(messageField);
        topPanel.add(messageField, BorderLayout.CENTER);
        
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
        JTextField usernameField = new JTextField(LABEL_WIDTH);
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
                
        final JPanel formPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        
        //make the registration 
        JLabel n = new JLabel("Name:");
        JTextField name = new JTextField(TEXT_FIELD_WIDTH);
        n.setLabelFor(name);
        formPanel.add(n); formPanel.add(name);
        JLabel u = new JLabel("Username:");
        JTextField username = new JTextField(TEXT_FIELD_WIDTH);
        u.setLabelFor(username);
        formPanel.add(u); formPanel.add(username);
        JLabel pw = new JLabel("Password:");
        final JPasswordField password = new JPasswordField(TEXT_FIELD_WIDTH);
        formPanel.add(pw); formPanel.add(password);
        pw.setLabelFor(password);
        JLabel cp = new JLabel("Confirm Password:");
        final JPasswordField confirmPassword = new JPasswordField(TEXT_FIELD_WIDTH);
        cp.setLabelFor(confirmPassword);
        formPanel.add(cp); formPanel.add(confirmPassword);
        
        //make the signup button
        JButton registerButton = new JButton("Sign Up");
        registerButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                //TODO code registration logic here
                if(Arrays.equals(password.getPassword(), confirmPassword.getPassword())){
                    //register
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
