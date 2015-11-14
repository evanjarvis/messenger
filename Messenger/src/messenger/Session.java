package messenger;

import java.sql.SQLException;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Me
 */
public class Session extends JFrame{
    Group group;
    private final boolean guestSession;
    User localUser;

    /**
     * Default constructor starts a session with guest privileges.
     */
    public Session(){
        guestSession = true;
    }
    /**
     * Constructor to start a new Session.
     * @param user the local user
     * @throws SQLException 
     */
    public Session(User user) throws SQLException {
        localUser = user;
        guestSession = false;
    }
        /**
     * Create and display the main frame for the messenger program.
     */
    protected void showGUI(){
        // Reads Image From File
        setLayout(new BorderLayout());
	JLabel background=new JLabel(new ImageIcon("images/BTBPoly.jpg"));
       
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
        
        String frameTitle;
        if(guestSession == true){
            frameTitle = "Messenger App (Guest)";
        } else {
            frameTitle = "Messenger App";
        }
        JFrame frame = new JFrame(frameTitle);
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
	JLabel background=new JLabel(new ImageIcon("images/BTBSmall.jpg"));
       
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
}