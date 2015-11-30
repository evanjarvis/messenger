package messenger;

import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Stores information and methods to allow a User to interact with a database.
 * @author Me
 */
public class User {
    private String firstName;
    private String lastName;
    private String username;
    public String bioInfo;
    
    private char[] password;
    private ArrayList<String> subscriptionList;
    
    //Change these to host url, database username and password
    private final String HOST = "jdbc:mysql://www.evanjarvis.net:3306/evanjarv_messenger?zeroDateTimeBehavior=convertToNull";
    private final String USER = "evanjarv_project";
    private final String PASS = "User1945";
    
   private Newsfeed newsfeed;       //the user's personal newsfeed
   
    /**
     * Constructor for a new User.
     * @param fn first name
     * @param ln last name
     * @param un username
     * @param pw password
     */
    public User(String fn, String ln, String un, char[] pw){
        firstName = fn;
        lastName = ln;
        username = un;
        password = pw;      
    }
    /**
     * Constructor for a user with only a username and password. User must be validated with the validate()
     * method in order to retrieve other information from database.
     * @param un the username
     * @param pw the password
     */
    public User(String un, char[] pw){
        username = un;
        password = pw;
        firstName = null;
        lastName = null;
    }
    /**
     * Attempts to validate the user in the database with a given password
     * @param pw the password to test
     * @return -1 if the credentials are wrong, 1 if the credentials match, or 0 if the connection fails
     * @throws SQLException if the connection cannot be established.
     */
    public boolean validate(char[] pw) throws SQLException{
        
        //establish connection
        Connection connection = DriverManager.getConnection(HOST, USER, PASS);
            
        //prepare SQL statement
        String sql = "SELECT * FROM USERS WHERE USERNAME = (?)";
            
        //set up PreparedStatements
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
                          
        //execute
        ResultSet results = statement.executeQuery();
        results.next();
        
        //compare passwords
        password = results.getString("PASSWORD").toCharArray();
        if(slowEquals(password, pw)){
            firstName = results.getString("FIRST_NAME");
            lastName = results.getString("LAST_NAME");
            //populate the subscriptionList
            return true;
        }
        return false;
    }
        /**
     * Registers a user by adding it to the database
     * @return a message indicating the result of the attempted registration.
     */
    String Register() throws SQLException{
        try {
            //establish connection
            Connection connection = DriverManager.getConnection(HOST, USER, PASS);
            System.out.println("Connection established!");
            
            //check to see if user is already in the database
            String checkDuplicate = "SELECT * FROM USERS WHERE USERNAME = (?)";
            PreparedStatement s = connection.prepareStatement(checkDuplicate);
            s.setString(1, username);
            ResultSet rs = s.executeQuery();
            if(!rs.next()){ //if there are no results
                //prepare SQL statement
                String sql = "INSERT INTO USERS (FIRST_NAME, LAST_NAME, USERNAME, PASSWORD) " +
                       "VALUES (?, ?, ?, ?)";
            
                //create PreparedStatement
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, firstName);
                statement.setString(2, lastName);
                statement.setString(3, username);
                String passwordString = new String(password);
                statement.setString(4, passwordString);    //strong password as string is not secure, change later 
                System.out.println(passwordString);
                    
                //execute statement
                statement.execute();
                
                //make a column in the subscriptions table
                sql = "ALTER TABLE SUBSCRIPTIONS ADD (?), VARCHAR(20)";
                statement = connection.prepareStatement(sql);
                statement.setString(1, username);
                statement.execute();
                
                return "Registration successful!";
                } else {
                    return "That username is already in use.";
                }
        } catch (SQLException ex) {
                System.out.println( ex.getMessage( ) );
                return( ex.getMessage());
        }
    } 
    /**
     * Post a public message.
     * @param post the message to be posted to the newsfeed
     * @param p whether the post is private or not
     */
    void post(String post, boolean p)throws SQLException {
        
        //Parser that will go through the message looking for the @ symbol
        //in the message and if it finds @ with a username, send a message.
        // if not, it will post publically automatically.
        for(int i=0; i < post.length(); i++)
        {
            char current = post.charAt(i);
           
            if(current == '@')
            {
                System.out.println("I found an @ symbol!");
                //TODO: SEND THE POST AS A PRIVATE MESSAGE AND RETURN IMMEDIATELY
            }   
            else if(current == '#')
            {
                System.out.println("I found a #!");
                //TODO: SEND A COPY OF THE POST TO THE HASHTAG TABLE, THEN POST TO NEWSFEED
            }   
        }
        //post a public message
        try{
            Connection connection = DriverManager.getConnection(HOST, USER, PASS);
            String sql = "INSERT INTO NEWSFEED (USER_NAME, MESSAGE, TIMESTAMP, PRIVATE) " + "VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, post);
            statement.setTimestamp(3, getCurrentTimeStamp());
            statement.setBoolean(4, p);
            statement.execute();
            System.out.println("Great Success");
        } catch (SQLException e) {
            System.out.println("connection failed");
        }
    }
    /**
     * Send a private message to another user.
     * @param message the message
     * @param recipient username of the recipient.
     */
    void sendMessage(String message, String recipient){
        try{
            Connection connection = DriverManager.getConnection(HOST, USER, PASS);
            String sql = "INSERT INTO PRIVATE_MESSAGES (SENDER, RECIPIENT, MESSAGE, TIMESTAMP) " + "VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, recipient);
            statement.setString(3, message);
            statement.setTimestamp(4, getCurrentTimeStamp());
            statement.execute();
        } catch (SQLException e) {
            System.out.println("connection failed");
        }
    }
    /**
     * gets the user's username
     * @return username
     */
    public void setUserName(){
        username = "Guest";
    }
    public void setName(){
        firstName = "Guest";
    }
    public void setLastName(){
        lastName = "User";
    }
    public void setBio(){
        bioInfo = "";
    }
    public String getUsername(){
        return username;
    }
    /**
     * gets the user's firstname
     * @return first name
     */
    public String getFirstName(){
        return firstName;
    }
    
    /**
     * gets the user's last name
     * @return last name
     */
    public String getLastName(){
        return lastName;
    }
    
    public String getBioInfo(){
        return bioInfo;
    }
    /**
     * Change the password.
     * @param pw the new password
     */
    public void changePassword(char[] pw){
        password = pw;
    }
    /**
     * Subscribe to a user's messages.
     * @param un the username of the user to subscribe to
     */
    void subsribe(String un) throws SQLException{
        Connection connection = DriverManager.getConnection(HOST, USER, PASS);
        String sql = "INSERT INTO SUBSCRIPTIONS " + username + " VALUE (?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, un);
        statement.execute();
    }
    /**
     * Unsubscribe from a user.
     * @param un the username of the user to unsubscribe from
     */
    void unsubscribe(String un) throws SQLException{
        Connection connection = DriverManager.getConnection(HOST, USER, PASS);
        String sql = "ALTER TABLE SUBSCRIPTIONS SET " + username + " = NULL WHERE " + " USERNAME = (?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, un);
        statement.execute();
    }
    /**
     * Get the personal newsfeed for this user, including all messages from subscriptions.
     * @return 
     */
    ResultSet getPersonalFeed() throws SQLException{
        ArrayList<String> subscriptionList = new ArrayList();
        Connection connection = DriverManager.getConnection(HOST, USER, PASS);
        
        //get subscriptions
        String sql = "SELECT (?) FROM SUBSCRIPTIONS";
        PreparedStatement s = connection.prepareStatement(sql);
        s.setString(1, username);
        ResultSet subscriptionSet = s.executeQuery();
        subscriptionSet.next();
        
        sql = "SELECT * FROM NEWSFEED WHERE PRIVATE = FALSE OR USER_NAME IN (?)";
        String names = username + ", ";
        while(subscriptionSet.isLast()){
            names = names + subscriptionSet.next() + ", ";
        }
        
        s = connection.prepareStatement(sql);
        s.setString(1, names);
        ResultSet rs = s.executeQuery();
        return rs;
    }
    /**
     * Make a timestamp for the current time.
     * @return the timestamp
     */
    private static Timestamp getCurrentTimeStamp() {
	java.util.Date today = new java.util.Date();
	return new java.sql.Timestamp(today.getTime());
    }
    /**
     * Enhanced-security method for comparing character arrays.  Takes a constant
     * amount of time to compare arrays in order to prevent timing attacks.
     * @param a the first array to be compared
     * @param b the second array to be compared
     * @return 
     */
    private static boolean slowEquals(char[] a, char[] b)
       {
         int diff = a.length ^ b.length;
         for(int i = 0; i < a.length && i < b.length; i++){
             diff |= a[i] ^ b[i];
         }
         return diff == 0;
    }
    
    /**
     * Allows for setting the user's new bio info
     * @param bioInfo is the new bio information updated by the user
     * @throws SQLException if it cannot connect to the database
     */
    public void setBioInfo(String bioInfo) throws SQLException{
        this.bioInfo = bioInfo;
        System.out.println("User is: " +username+ " and bio info is: " + bioInfo);
        try{
            // Will attempt to connect to the database
            Connection connection = DriverManager.getConnection(HOST, USER, PASS);
            System.out.println("Connected database successfully...");
      
            System.out.println("Creating statement...");
            
            // This sql statement will delete the user's old Bio info
            String SQL = "DELETE FROM USERINFO WHERE USER_NAME =?";
            PreparedStatement stmt = connection.prepareStatement(SQL);
            stmt.setString(1, username);
            stmt.executeUpdate();
            
            System.out.println("Great Success!");
            // This sql commant will insert the users new bio info
            String sql = "INSERT INTO USERINFO (USER_NAME, BIO)" + " VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, bioInfo);
            statement.execute();
            connection.close();
        }
        catch(Exception e){
            System.out.println("Connection failed");
        }
        
    }
    
    /**
     * This method will return the user's
     * bio info and display it once the user 
     * is logged in
     * @return returns the user's bio info
     */
    public String pullBioInfo(){
        
        try{
            Connection connection = DriverManager.getConnection(HOST, USER, PASS);
            String sql = "Select USER_NAME,BIO FROM USERINFO";
            
            System.out.println("Test 1");
            PreparedStatement statement = connection.prepareStatement(sql);
            System.out.println("Test 2");
            System.out.println("Test 3");
            ResultSet rs = statement.executeQuery();
            System.out.println("Test 4");
            while(rs.next()){
                String un = rs.getString("USER_NAME");
                if(un.equals(username)){
                    System.out.println("Test 5");
                    bioInfo = rs.getString("BIO");
                    System.out.println(bioInfo);
                }        
            }
            
        }
        catch(Exception e){
            System.out.println("Didnt work nigga");
        }
        return bioInfo;
    }
}