package messenger;

import java.sql.*;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Me
 */
public class User {
    private final String firstName;
    private final String lastName;
    private final String username;
    private char[] password;
    
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
    public String getUsername(){
        return username;
    }
    
    public String getFirstName(){
        return firstName;
    }
    
    public String getLastName(){
        return lastName;
    }
    /**
     * Attempts to validate the user in the database with a given password
     * @param pw the password to test
     * @return -1 if the credentials are wrong, 1 if the credentials match, or 0 if the connection fails
     * @throws SQLException if the connection could not be established
     */
    public boolean validate(char[] pw) throws SQLException{
        //establish connection
        Connection connection = DriverManager.getConnection(HOST, USER, PASS);
            
        //prepare SQL statement
        String sql = "SELECT PASSWORD FROM USERS WHERE USERNAME = (?)";
            
        //set up PreparedStatements
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
                          
        //execute
        ResultSet results = statement.executeQuery();
        results.next();
        
        //compare passwords
        password = results.getString("PASSWORD").toCharArray();
        return slowEquals(password, pw);
    }
    /**
     * Post a public message.
     * @param message the message
     */
    void post(String message)throws SQLException {
        
        //Parser that will go through the message looking for the @ symbol
        //in the message and if it finds @ with a username, send a message.
        // if not, it will post publically automatically.
        for(int i=0; i < message.length(); i++)
        {
            char current = message.charAt(i);
           
            if(current == '@')
            {
                System.out.println("I found an @ symbol!");
            }
            
            else if(current == '#')
            {
                System.out.println("I found a #!");
            }   
        }
        //post a public message
        try{
            Connection connection = DriverManager.getConnection(HOST, USER, PASS);
            String sql = "INSERT INTO NEWSFEED (USER_NAME, MESSAGE, PRIVATE, TIMESTAMP) " + "VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, message);
            statement.setBoolean(3, false);
            statement.setTimestamp(4, getCurrentTimeStamp());
            statement.execute();
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
        //send a private message
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
     * Change the password.
     * @param pw the new password
     */
    public void changePassword(char[] pw){
        password = pw;
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
}

//User
