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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.StyledDocument;
/**
 *
 * @author Me
 */
public class Session extends JFrame{
    Group group;
    private final boolean guestSession;
    User localUser;
    public String message;
    public String bioIn;
    final JTextArea messageField2 = new JTextArea(200,20); 

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
    /**
     * Create and display the main frame for the messenger program.
     * @throws java.sql.SQLException
     */
    protected void showMessageGUI() throws SQLException{
        setLayout(new BorderLayout());
	JLabel background=new JLabel(new ImageIcon("build/images/BTBPoly.jpg"));
       
        
        String frameTitle;
        if(guestSession == true){
            frameTitle = "Messenger App (Guest)";
        } else {
            frameTitle = "Messenger App";
        }
        
        //localUser.pullMessages();
        final JFrame frame = new JFrame(frameTitle);
        final int TEXT_FIELD_WIDTH = 25;   
        final JPanel topPanel1 = new JPanel();
        final JPanel topPanel2 = new JPanel();
        final JPanel bottomPanel = new JPanel();
        final JTextArea messageField = new JTextArea(10, 25);
        
        
        messageField.setEditable(false);
        JScrollPane scroller = new JScrollPane(messageField,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        messageField.setLineWrap(true);        
        messageField.setWrapStyleWord(true);

       try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://www.evanjarvis.net:3306/evanjarv_messenger?zeroDateTimeBehavior=convertToNull", "evanjarv_project", "User1945");
            String sql = "Select SENDER,RECIPIENT,MESSAGE FROM PRIVATE_MESSAGES";
            
            System.out.println("Test 1");
            PreparedStatement statement = connection.prepareStatement(sql);
            System.out.println("Test 2");
            System.out.println("Test 3");
            ResultSet rs = statement.executeQuery();
            System.out.println("Test 4");
            String userName = localUser.getUsername();
            while(rs.next()){
                String un = rs.getString("RECIPIENT");
                if(un.equals(userName)){
                    System.out.println("Test 5");
                    String message = rs.getString("MESSAGE");
                    String sender = rs.getString("SENDER");
                    messageField.append("From: "+sender+" "+message+"\n\n");
                }        
            }
            
        }
        catch(Exception e){
            System.out.println("Connection Failed");
        }
        
        // Add image to background
        topPanel1.add(background);
	background.setLayout(new FlowLayout());
        
        
        topPanel1.setLayout(new BorderLayout());        
        
        topPanel2.setLayout(new BorderLayout());
        topPanel2.add(new JLabel("Messages:"), BorderLayout.NORTH);
        topPanel2.add(scroller, BorderLayout.CENTER);
        topPanel2.setBackground(new Color(0xA7B23C));        
        
        
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBackground(new Color(0x609EAA));
        
        
        
        
        frame.add(topPanel1, BorderLayout.NORTH);
        frame.add(topPanel2, BorderLayout.NORTH);
        frame.add(scroller);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        
       
    }
        protected void showNewsfeedGUI() throws SQLException {
       // Reads Image From File
        setLayout(new BorderLayout());
        final int TEXT_FIELD_WIDTH = 20;
        final JFrame frame = new JFrame("BTB NewsFeed");
        
        // Sets the panels for the window
        final JPanel topPanel = new JPanel();
        final JPanel centerPanel = new JPanel();
        final JPanel sidePanel = new JPanel();
        final JPanel bottomPanel = new JPanel();
        final JPanel innerTopBio = new JPanel();
        final JPanel innerBio = new JPanel();
        final JTextArea messageField = new JTextArea();   
        final JTextField entryField = new JTextField(TEXT_FIELD_WIDTH);
        final JTextField entryField2 = new JTextField(TEXT_FIELD_WIDTH);
                
        // Conditional statement to determine if user is a guest
        if(localUser.getUsername().equals("guest")){
            messageField2.append("Welcome, Guest");
        }
        else {
             messageField2.append("Welcome, " + localUser.getFirstName()+ " " + localUser.getLastName()+"!");
             String bio = localUser.pullBioInfo();
             messageField2.append("\n\n"+bio);
        }
        
        // Buttons
        JButton editBio = new JButton("Edit Bio");
        editBio.setSize(50,50);
        JButton postPublic = new JButton("Public Post");
        postPublic.setSize(50,50);
        JButton postPrivate = new JButton("Private Post");
        postPrivate.setSize(50,50);
        JButton search = new JButton("Search");
        search.setSize(50,50);
        JButton logout = new JButton("Logout");
        logout.setSize(50,50);
        JButton messages = new JButton("Message");
        logout.setSize(50,50);
        
        centerPanel.setLayout(new BorderLayout());
   
        // Changes colors of the panels
        sidePanel.setBackground(new Color(0xB8D1F1));
        topPanel.setBackground(new Color(0x73D3FC));
        bottomPanel.setBackground(new Color(0x73D3FC));
        innerTopBio.setBackground(new Color(0x73D3FC));
        
        // Sets dimensions of the Side Panel and Center Panel
        sidePanel.setPreferredSize(new Dimension(200, 100));
        centerPanel.setPreferredSize(new Dimension(500, 800));
        innerTopBio.setPreferredSize(new Dimension(700, 100));
        
                       
        // Establishes the messagefield for the Newsfeed... Obviously...
        messageField.setEditable(false);
        messageField2.setEditable(false);
        messageField.setVisible(true);
        messageField2.setVisible(true);
        JScrollPane scroller = new JScrollPane(messageField,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        centerPanel.add(scroller, BorderLayout.CENTER);
        JScrollPane scroller2 = new JScrollPane(messageField2,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        messageField2.setFont(new Font("Helvetica", Font.PLAIN, 18));
        innerTopBio.add(scroller2, BorderLayout.CENTER);

        // Wraps the text so it doesn't go out of bounds
        messageField.setLineWrap(true);
        messageField2.setLineWrap(true);
        messageField.setWrapStyleWord(true);
        messageField2.setWrapStyleWord(true);
            
        // Sets the Post button and entry field on the bottom Panel
        bottomPanel.add(postPublic, BorderLayout.WEST);
        bottomPanel.add(postPrivate, BorderLayout.CENTER);
        bottomPanel.add(entryField, BorderLayout.EAST);     
        
        sidePanel.add(messages);
        
        topPanel.add(search, BorderLayout.WEST);
        topPanel.add(entryField2, BorderLayout.WEST);
        topPanel.add(editBio, BorderLayout.EAST);
        topPanel.add(logout, BorderLayout.EAST);
        
        
        
        //sidePanel.add(logout);

        //get the newsfeed
        ResultSet rs = localUser.getPersonalFeed();
        while(rs.next()) {
            //Catches string from database and displays it on newsfeed
            String str1 = rs.getString("USER_NAME");
            messageField.setFont(new Font("Helvetica", Font.PLAIN, 18));
            String str2 = rs.getString("MESSAGE"); 
            Timestamp str3 = rs.getTimestamp("TIMESTAMP");           
            messageField.append(str1+"\n"+"On: "+str3 +"\n"+ str2+ "\n\n");                    
        }
        
        messages.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                try {
                    showMessageGUI();
                } catch (SQLException ex) {
                    Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
                }
           }
        });
        
        // Adds a post to the message field
        postPublic.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                String formattedDate = sdf.format(date);
                message = entryField.getText();                
                try {
                    localUser.post(message, false);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(centerPanel, "The connection failed.");
                }
                messageField.append(localUser.getUsername()+"\n"+formattedDate+"\n"+message+ "\n\n"); 
                entryField.setText("");
           }
        });
        postPrivate.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                String formattedDate = sdf.format(date);
                
                message = entryField.getText();   
                String recipant = message;
                
                if(recipant.contains("@")){
                    recipant = recipant.substring(1, recipant.indexOf(" "));  
                }
                
                System.out.println("Still testing... "+recipant);
                
                try {
                    localUser.post(message, true);
                    localUser.sendMessage(message,recipant);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(centerPanel, "The connection failed.");
                }
                messageField.append(localUser.getUsername()+"\n"+formattedDate+"\n"+message+ "\n\n"); 
                entryField.setText("");
           }
        });
        logout.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){                 
                GUI gui = new GUI();
                frame.dispose();
                gui.showStartupFrame();              
            }
        });   
        // Will search for friends or Trends 
        search.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                try{
                    String search = entryField2.getText();
                        System.out.println("Entered try block");
                    Connection con = DriverManager.getConnection("jdbc:mysql://www.evanjarvis.net:3306/evanjarv_messenger?zeroDateTimeBehavior=convertToNull", "evanjarv_project", "User1945");
                        System.out.println("Got connection");
                    PreparedStatement ps = con.prepareStatement("SELECT * FROM NEWSFEED WHERE USER_NAME = (?)");
                        System.out.println("Prepared statement");
                    ps.setString(1, search);
                    System.out.println(ps);
                    ResultSet rs = ps.executeQuery();
                    System.out.println("Search executed");
                    while(rs.next()){
                        messageField.append("***** SEARCH RESULT ***** \n" +
                                rs.getString("USER_NAME")+ "\n" +
                                rs.getString("TIMESTAMP") + "\n" +
                                rs.getString("MESSAGE") + "\n \n");
                    }
                }
                catch(Exception e){
                    System.out.println("Exception thrown");
                }    
            }
        });    
        
        editBio.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                try{
                    bioInfo();
                }
                catch(Exception e){
                }    
            }
        }); 
        
       // Draws the Panels
        centerPanel.add(sidePanel, BorderLayout.WEST);
        frame.add(centerPanel, BorderLayout.CENTER);
        centerPanel.add(topPanel, BorderLayout.NORTH);
        centerPanel.add(bottomPanel,BorderLayout.SOUTH);
        frame.add(innerTopBio, BorderLayout.NORTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(true);
        frame.setVisible(true);
    } 
        protected void showGuestNewsfeedGUI() throws SQLException {
       // Reads Image From File
        setLayout(new BorderLayout());
        final int TEXT_FIELD_WIDTH = 20;
        final JFrame frame = new JFrame("BTB NewsFeed");
        
        // Sets the panels for the window
        final JPanel topPanel = new JPanel();
        final JPanel centerPanel = new JPanel();
        final JPanel sidePanel = new JPanel();
        final JPanel bottomPanel = new JPanel();
        final JPanel innerTopBio = new JPanel();
        final JPanel innerBio = new JPanel();
        final JTextArea messageField = new JTextArea();   
        final JTextField entryField = new JTextField(TEXT_FIELD_WIDTH);
        final JTextField entryField2 = new JTextField(TEXT_FIELD_WIDTH);
                      
        // appends message for welcoming user
        messageField2.append("Welcome, Guest \n Sign in to send edit bio");
            
        // Buttons
        JButton search = new JButton("Search");
        search.setSize(50,50);
        JButton logout = new JButton("Logout");
        logout.setSize(50,50);
        
        centerPanel.setLayout(new BorderLayout());
   
        // Changes colors of the panels
        sidePanel.setBackground(new Color(0xB8D1F1));
        topPanel.setBackground(new Color(0x73D3FC));
        bottomPanel.setBackground(new Color(0x73D3FC));
        innerTopBio.setBackground(new Color(0x73D3FC));
        
        // Sets dimensions of the Side Panel and Center Panel
        sidePanel.setPreferredSize(new Dimension(200, 100));
        centerPanel.setPreferredSize(new Dimension(500, 800));
        innerTopBio.setPreferredSize(new Dimension(700, 100));
        
                       
        // Establishes the messagefield for the Newsfeed
        messageField.setEditable(false);
        messageField2.setEditable(false);
        messageField.setVisible(true);
        messageField2.setVisible(true);
        JScrollPane scroller = new JScrollPane(messageField,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        centerPanel.add(scroller, BorderLayout.CENTER);
        JScrollPane scroller2 = new JScrollPane(messageField2,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        messageField2.setFont(new Font("Helvetica", Font.PLAIN, 18));
        innerTopBio.add(scroller2, BorderLayout.CENTER);

        // Wraps the text so it doesn't go out of bounds
        messageField.setLineWrap(true);
        messageField2.setLineWrap(true);
        messageField.setWrapStyleWord(true);
        messageField2.setWrapStyleWord(true);
            
        topPanel.add(search, BorderLayout.WEST);        
        topPanel.add(entryField2, BorderLayout.WEST);
        topPanel.add(logout, BorderLayout.EAST);
        
        
       

        //get the newsfeed
        ResultSet rs = localUser.getPersonalFeed();
        while(rs.next()) {
            //Catches string from database and displays it on newsfeed
            String str1 = rs.getString("USER_NAME");
            messageField.setFont(new Font("Helvetica", Font.PLAIN, 18));
            String str2 = rs.getString("MESSAGE"); 
            Timestamp str3 = rs.getTimestamp("TIMESTAMP");           
            messageField.append(str1+"\n"+"On: "+str3 +"\n"+ str2+ "\n\n");                    
        }
        
        
        logout.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){                 
                GUI gui = new GUI();
                frame.dispose();
                gui.showStartupFrame();              
            }
        });   
        
        
        // Will search for friends or Trends 
        search.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                try{
                    String search = entryField2.getText();
                        System.out.println("Entered try block");
                    Connection con = DriverManager.getConnection("jdbc:mysql://www.evanjarvis.net:3306/evanjarv_messenger?zeroDateTimeBehavior=convertToNull", "evanjarv_project", "User1945");
                        System.out.println("Got connection");
                    PreparedStatement ps = con.prepareStatement("SELECT * FROM NEWSFEED WHERE USER_NAME = (?)");
                        System.out.println("Prepared statement");
                    ps.setString(1, search);
                    System.out.println(ps);
                    ResultSet rs = ps.executeQuery();
                    System.out.println("Search executed");
                    while(rs.next()){
                        messageField.append("***** SEARCH RESULT ***** \n" +
                                rs.getString("USER_NAME")+ "\n" +
                                rs.getString("TIMESTAMP") + "\n" +
                                rs.getString("MESSAGE") + "\n \n");
                    }
                }
                catch(Exception e){
                    System.out.println("Exception thrown");
                }    
            }
        });    
        // Draws the Panels
        centerPanel.add(sidePanel, BorderLayout.WEST);
        frame.add(centerPanel, BorderLayout.CENTER);
        centerPanel.add(topPanel, BorderLayout.NORTH);
        centerPanel.add(bottomPanel,BorderLayout.SOUTH);
        frame.add(innerTopBio, BorderLayout.NORTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(true);
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
     * Check to see whether the session has Guest privileges
     * @return true if the session is a guest session, otherwise false
     */
    boolean getGuest(){ 
        return guestSession;
    }  
    public void bioInfo(){
        int LABEL_WIDTH = 10;
        int TEXT_FIELD_WIDTH = 20;
                
        messageField2.setText("");
        final JFrame registrationFrame = new JFrame("Bio Info");
        final JPanel formPanel = new JPanel();
        formPanel.setSize(100,100);
        
        //make the registration form
        JLabel fn = new JLabel("Bio Info:");
        final JTextField bioInfo = new JTextField(TEXT_FIELD_WIDTH);
        fn.setLabelFor(bioInfo);
        formPanel.add(fn); formPanel.add(bioInfo);
        
        
        //make the signup button
        JButton doneButton = new JButton("Done");
        JButton cancelButton = new JButton("Cancel");
        doneButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                messageField2.setText("");
                String bioinfo = bioInfo.getText();
                messageField2.append("Welcome, " + localUser.getFirstName()+ " " + localUser.getLastName()+"!"+"\n\n"+bioinfo);
                try {
                    localUser.setBioInfo(bioinfo);
                } catch (SQLException ex) {
                    Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
                }
                registrationFrame.dispose();
            }
        }); 
        
        cancelButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                messageField2.append("Welcome, " + localUser.getFirstName()+ " " + localUser.getLastName()+"!"+"\n\n"+localUser.getBioInfo());
                registrationFrame.dispose();
            }
        }); 
        
        formPanel.add(doneButton);
        formPanel.add(cancelButton);
        registrationFrame.add(formPanel);
        registrationFrame.setResizable(false);
        registrationFrame.pack();
        registrationFrame.setVisible(true);
        

    }
}
