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
        
        final JFrame frame = new JFrame("Login or Register");
        
        //setup containers
        JPanel container = new JPanel(new BorderLayout());

        JPanel loginWrapper = new JPanel();
        JPanel innerLoginPane = new JPanel(new GridLayout(0, 2, 5, 0));
        final JPanel loginPane = new JPanel(new BorderLayout());
        loginWrapper.setMaximumSize(new Dimension(100, 5));
        //setLayout(new BorderLayout());
	
        ImageIcon image = new ImageIcon("images/BTBMain.jpg");
        final JPanel topPanel = new JPanel(new BorderLayout());
        final JPanel bottomPanel = new JPanel(new BorderLayout());
        topPanel.add(new JLabel(image));
	//background.setLayout(new FlowLayout());
        
        
       
        //loginPane.setBorder(new BevelBorder(BevelBorder.RAISED));
        
        //loginWrapper.setMaximumSize(new Dimension(10, 10));
        //bottomPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        // Set color Background
        loginWrapper.setBackground(new Color(0x03066C));
        loginPane.setBackground(new Color(0x777BF4));
        
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
        JButton guestButton = new JButton("Guest");
        loginButton.setPreferredSize(new Dimension(1,1));
        
        
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
                        frame.dispose();
                        session.showNewsfeedGUI();
                    } 
                    else {
                        JOptionPane.showMessageDialog(loginPane, "Invalid username or password.");
                        //Zero out the possible password, for security.
                        Arrays.fill(inputPassword, '0');
                        usernameField.setText(""); passwordField.setText("");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(loginPane, "Connection not found.  Please check your network connection.");
                    showStartupFrame();
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
                    User temp = new User("guest", null); //this is necessary so there is a user in the session
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
        //loginPane.add(innerLoginPane, BorderLayout.NORTH);
        innerLoginPane.add(loginButton,BorderLayout.CENTER);
        innerLoginPane.add(registerButton, BorderLayout.WEST);
        innerLoginPane.add(guestButton, BorderLayout.EAST);
        loginWrapper.add(innerLoginPane, BorderLayout.CENTER);
        bottomPanel.add(loginWrapper);
        
        container.add(topPanel, BorderLayout.NORTH);
        container.add(bottomPanel, BorderLayout.SOUTH);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(container);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
//GUI