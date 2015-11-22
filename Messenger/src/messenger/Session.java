package messenger;

import java.sql.SQLException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.EmptyBorder;
/**
 *
 * @author Me
 */
public class Session extends JFrame{
    Group group;
    private final boolean guestSession;
    User localUser;
    public String message;

    /**
     * Default constructor starts a session with guest privileges.
     */
    public Session(){
        guestSession = true;
    }
    /**
     * Constructor to start a new Session.
     * @param user the local user
     */
    public Session(User user) {
        localUser = user;
        guestSession = false;
    }
    
    void loopFeed() throws SQLException{
    String HOST = "jdbc:mysql://www.evanjarvis.net:3306/evanjarv_messenger?zeroDateTimeBehavior=convertToNull";
    String USER = "evanjarv_project";
    String PASS = "User1945";
    
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
        }
    Connection con = DriverManager.getConnection(HOST, USER, PASS);
    
    //prepare SQL statement
    String sql = "SELECT MESSAGE FROM NEWSFEED";
    PreparedStatement s = con.prepareStatement(sql);
    

    //set up PreparedStatements
    ResultSet rs = s.executeQuery();
    while(rs.next()) {  
        
        String str1 = rs.getString("MESSAGE");
        //System.out.println(str1);    
        }
    }
    

        /**
     * Create and display the main frame for the messenger program.
     */
    protected void showMessageGUI() throws SQLException{
        // Reads Image From File
        loopFeed();
        
        setLayout(new BorderLayout());
	JLabel background=new JLabel(new ImageIcon("build/images/BTBPoly.jpg"));
       
        final int TEXT_FIELD_WIDTH = 25;   
        final JPanel topPanel1 = new JPanel();
        final JPanel topPanel2 = new JPanel();
        final JPanel bottomPanel = new JPanel();
        final JTextField entryField = new JTextField(TEXT_FIELD_WIDTH);
        final JTextArea messageField = new JTextArea(10, 25);
        
        
        messageField.setEditable(false);
        JScrollPane scroller = new JScrollPane(messageField,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
 
        
        JButton sendButton = new JButton("Send");
        JButton backButton = new JButton("Back");
 
                
        sendButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                String message = entryField.getText();
                messageField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                messageField.append(message+ "\n");
                Calendar calendar = Calendar.getInstance();
                Timestamp currentTimestamp = new java.sql.Timestamp(calendar.getTime().getTime());
                try { 
                    //send the message to database
                    localUser.post(message);
                } catch (SQLException ex) {
                    //Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Didn't work");
                }
            }
        });
        backButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                try {
                    showNewsfeedGUI();
                    //showMessageGUI();
                } catch (SQLException ex) {
                    Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        
        // Add image to background
        topPanel1.add(background);
	background.setLayout(new FlowLayout());
        
        topPanel1.add(backButton, BorderLayout.NORTH);
        topPanel1.setLayout(new BorderLayout());

        
        
        topPanel2.setLayout(new BorderLayout());
        topPanel2.add(new JLabel("Messages:"), BorderLayout.NORTH);
        topPanel2.add(messageField, BorderLayout.CENTER);
        topPanel2.setBackground(new Color(0xA7B23C));
        

        
        //bottomPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
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
        frame.add(scroller);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        
       
    }
        protected void showNewsfeedGUI() throws SQLException {
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

        sidePanel.add(new JLabel("Welcome " + localUser.getFirstName()+ " " + localUser.getLastName()+"!"));
        
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
        messageField.setEditable(false);
        JScrollPane scroller = new JScrollPane(messageField,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        centerPanel.add(scroller, BorderLayout.CENTER);
        
        // Wraps the text so it doesn't go out of bounds
        messageField.setLineWrap(true);
        messageField.setWrapStyleWord(true);
        
                        
        // Sets the Post button and entry field on the bottom Panel
        JButton post = new JButton("Post");
        bottomPanel.add(post, BorderLayout.WEST);
        bottomPanel.add(entryField, BorderLayout.WEST);     
        
        // Sets the Search button and entryfield on the top Panel
        JButton search = new JButton("Search");
        topPanel.add(search, BorderLayout.WEST);
        topPanel.add(entryField2, BorderLayout.WEST);
        
        // Sets the button to open message window
        JButton logout = new JButton("Logout");
        sidePanel.add(logout, BorderLayout.NORTH);
        
        
        // Connects to the database to display Newsfeed       
        Connection con = DriverManager.getConnection("jdbc:mysql://www.evanjarvis.net:3306/evanjarv_messenger?zeroDateTimeBehavior=convertToNull", "evanjarv_project", "User1945");
    
        //prepare SQL statement
        String sql = "SELECT USER_NAME,MESSAGE,TIMESTAMP FROM NEWSFEED";
        PreparedStatement s = con.prepareStatement(sql);
    

        //set up PreparedStatements
        ResultSet rs = s.executeQuery();
        while(rs.next()) {
            //Catches string from database and displays it on newsfeed
            String str1 = rs.getString("USER_NAME");
            messageField.setFont(new Font("Helvetica", Font.PLAIN, 18));
            String str2 = rs.getString("MESSAGE"); 
            Timestamp str3 = rs.getTimestamp("TIMESTAMP");
            messageField.append(str1+"\n"+str3 +"\n"+ str2+ "\n\n");                    
        }
        
    
     
       // Adds a post to the message field
        post.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                String formattedDate = sdf.format(date);
                //System.out.println(formattedDate); // 12/01/2011 4:48:16 PM
                message = entryField.getText();                
                messageField.append(localUser.getUsername()+"\n"+formattedDate+"\n"+message+ "\n\n"); 
                try {
                    localUser.post(message);
                } catch (SQLException ex) {
                    Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
                }
                entryField.setText("");
           }
        });
        
        
        logout.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){                 
                GUI gui = new GUI();
                gui.showStartupFrame();              
            }
        });   
        
        
        // Will search for friends or Trends 
        search.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                try{
                    String search = entryField2.getText();
                    Connection con = DriverManager.getConnection("jdbc:mysql://www.evanjarvis.net:3306/evanjarv_messenger?zeroDateTimeBehavior=convertToNull");
                    //ps = con.prepareStatement("select * from where users id ?");
                    //rs = ps.executeQuery();
                    
                }
                catch(Exception e){
                }    
            }
        });      
       // Draws the Panels
        JFrame frame = new JFrame("BTB NewsFeed");
        frame.add(sidePanel, BorderLayout.WEST);
        frame.add(centerPanel, BorderLayout.CENTER);
        centerPanel.add(topPanel, BorderLayout.NORTH);
        centerPanel.add(bottomPanel,BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
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

//Session