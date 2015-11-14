package messenger;

import java.sql.SQLException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
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
    
    protected void showNewsfeedGUI(){
       // Reads Image From File
        setLayout(new BorderLayout());
        final JLabel background = new JLabel(new ImageIcon("build/Images/BTBPoly.jpg"));
        final int TEXT_FIELD_WIDTH = 20;
        
        // Sets the panels for the window
        final JPanel topPanel = new JPanel();
        final JPanel centerPanel = new JPanel();
        final JPanel sidePanel = new JPanel();
        final JPanel bottomPanel = new JPanel();
        final JTextArea messageField = new JTextArea(100, 50);     
        final JTextField entryField = new JTextField(TEXT_FIELD_WIDTH);
        final JTextField entryField2 = new JTextField(TEXT_FIELD_WIDTH);
        
        

        // Changes colors of the panels
        sidePanel.setBackground(new Color(0xB8D1F1));
        topPanel.setBackground(new Color(0x73D3FC));
        bottomPanel.setBackground(new Color(0x73D3FC));

        // Sets dimensions of the Side Panel and Center Panel
        sidePanel.setPreferredSize(new Dimension(200, 100));
        centerPanel.setPreferredSize(new Dimension(500, 700));
      


        //bottomPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        centerPanel.setLayout(new BorderLayout());
        
        // Establishes the messagefield for the Newsfeed... Obviously...
        messageField.setEditable(true);
        JScrollPane scroller = new JScrollPane(messageField);
        centerPanel.add(messageField, BorderLayout.CENTER);
        
        // Sets the Post button and entry field on the bottom Panel
        JButton post = new JButton("Post");
        bottomPanel.add(post, BorderLayout.WEST);
        bottomPanel.add(entryField, BorderLayout.WEST);
        
        
        // Sets the Search button and entryfield on the top Panel
        JButton search = new JButton("Search");
        topPanel.add(search, BorderLayout.WEST);
        topPanel.add(entryField2, BorderLayout.WEST);     
        
        // Adds a post to the message field
        post.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                String message = entryField.getText();
                messageField.append(message+ "\n");
            }
        });
        
        
        // Will search for friends or Trends 
        search.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                try{
                    String search = entryField2.getText();
                    Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/messenger");
                    //ps = con.prepareStatement("select * from where users id ?");
                    //rs = ps.executeQuery();
                    
                }
                catch(Exception e){
                }    
            }
        });      
       // Draws the Panels
        JFrame frame = new JFrame("NewsFeed");
        frame.add(sidePanel, BorderLayout.WEST);
        frame.add(centerPanel, BorderLayout.CENTER);
        centerPanel.add(topPanel, BorderLayout.NORTH);
        centerPanel.add(bottomPanel,BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(true);
        frame.setVisible(true);
    }
    
    
        /**
     * Create and display the main frame for the messenger program.
     */
    protected void showMessageGUI(){
        // Reads Image From File
        setLayout(new BorderLayout());
	JLabel background=new JLabel(new ImageIcon("images/BTBPoly.jpg"));
       
        final int TEXT_FIELD_WIDTH = 25;   
        final JPanel topPanel1 = new JPanel();
        final JPanel topPanel2 = new JPanel();
        final JPanel bottomPanel = new JPanel();
        final JTextField entryField = new JTextField(TEXT_FIELD_WIDTH);
        final JTextArea messageField = new JTextArea(10, 25);

        
        messageField.setEditable(false);
        JScrollPane scroller = new JScrollPane(messageField);
 
        
        JButton sendButton = new JButton("Send");
        JButton backButton = new JButton("Back");
        
        
        sendButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                String message = entryField.getText();
                messageField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                messageField.append(message+ "\n");
            }
        });
        backButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                showNewsfeedGUI();
            }
        });

        
        // Add image to background
        //topPanel.add(background);
	//background.setLayout(new FlowLayout());
        
        topPanel1.add(backButton, BorderLayout.NORTH);
        topPanel1.setLayout(new BorderLayout());

        
        
        topPanel2.setLayout(new BorderLayout());
        topPanel2.add(new JLabel("Messages:"), BorderLayout.NORTH);
        topPanel2.add(messageField, BorderLayout.CENTER);
        topPanel2.setBackground(new Color(0xA7B23C));
        

        
        bottomPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(entryField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);
        bottomPanel.setBackground(new Color(0x609EAA));
        
        String frameTitle;
        if(guestSession == true){
            frameTitle = "Messenger App (Guest)";
        } else {
            frameTitle = "Messenger App";
        }
        JFrame frame = new JFrame(frameTitle);
        frame.add(topPanel1, BorderLayout.NORTH);
        frame.add(topPanel2, BorderLayout.NORTH);
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