/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messenger;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Me
 */
public class RegistrationFrame {
     /**
     * Display the screen for registering a new user.
     */
    protected void showGUI(){
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
