package messenger;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.*;

/**
 * GUI manager for messenger application.
 * @author Me
 */
public class GUI extends JFrame {
    /**
     * Show the startup screen.  Users may choose to login, register a new account, or continue as a guest.
     */
    protected void showStartupFrame(){
        int LABEL_WIDTH = 10;
        int TEXT_FIELD_WIDTH = 20;
        
        setLayout(new BorderLayout());
	JLabel background=new JLabel(new ImageIcon("build/images/BTBSmall.jpg"));

        //setup containers
        JPanel container = new JPanel(new GridLayout(0, 1));
        
        //container.add(background);
	//background.setLayout(new FlowLayout());
        
        JPanel loginWrapper = new JPanel(new GridLayout(0, 1));
        JPanel innerLoginPane = new JPanel(new GridLayout(0, 2, 5, 5));
        final JPanel loginPane = new JPanel(new BorderLayout());
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
            public void actionPerformed(ActionEvent e) {
                String inputUserName = usernameField.getText();
                char[] inputPassword = passwordField.getPassword();
                try {
                    User user = new User(inputUserName, inputPassword);
                    if(user.validate(inputPassword) == true){
                        Session session = new Session(user);
                        session.showNewsfeedGUI();
                    } 
                    else {
                        JOptionPane.showMessageDialog(loginPane, "Invalid username or password.");
                        //Zero out the possible password, for security.
                        Arrays.fill(inputPassword, '0');
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(loginPane, "Connection not found.  Please check your network connection.");
                }
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
            public void actionPerformed(ActionEvent event) {
                    User temp = new User("Guest", null); //this is necessary so there is a user in the session
                    Session session = new Session(temp);
                try {
                    session.showNewsfeedGUI();
                } catch (SQLException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
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
