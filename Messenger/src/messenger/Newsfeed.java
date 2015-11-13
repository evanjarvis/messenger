/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messenger;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author DiegoB
 */
public class Newsfeed extends JFrame{
    protected void showNewsfeedGUI(){
       // Reads Image From File
        setLayout(new BorderLayout());
        final JLabel background = new JLabel(new ImageIcon("build/Images/BTBPoly.jpg"));
        final int TEXT_FIELD_WIDTH = 25;
        
        final JPanel topPanel = new JPanel();
        topPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        final JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        topPanel.setLayout(new BorderLayout());
        final JTextArea messageField = new JTextArea(40, 75);
        messageField.setEditable(true);
        JScrollPane scroller = new JScrollPane(messageField);
        topPanel.add(messageField, BorderLayout.CENTER);
        JButton post = new JButton("Post");
        bottomPanel.add(post, BorderLayout.WEST);
        final JTextField entryField = new JTextField(TEXT_FIELD_WIDTH);
        bottomPanel.add(entryField, BorderLayout.WEST);
        
        // Add image to background
        //bottomPanel.add(background);
	//background.setLayout(new FlowLayout());
        post.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                String message = entryField.getText();
                messageField.append(message+ "\n");
            }
        });


       
        JFrame frame = new JFrame("NewsFeed");
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }
}

