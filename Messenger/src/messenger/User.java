package messenger;

import static java.lang.System.err;
import java.sql.*;

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
    private final String HOST = "jdbc:derby://localhost:1527/messenger_test";
    private final String USER = "messenger";
    private final String PASS = "test";
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
    public boolean validate() throws SQLException{
        //TODO: insert user validation code here
        try{
            Connection connection = DriverManager.getConnection(HOST, USER, PASS);
        } catch(SQLException err){
            System.out.println("Database connection failed");
        }
        return false;
    }
    /**
     * Registers a user by adding it to the database.
     */
    void Register() throws SQLException{
        
        try {
            //establish connection
            Connection connection = DriverManager.getConnection(HOST, USER, PASS);
            System.out.println("Connection established!");
            
            //prepare SQL statement
            String sql = "INSERT INTO MESSENGER.USERS (FIRST_NAME, LAST_NAME, USERNAME, PASSWORD) " +
                   "VALUES (?, ?, ?, ?)";
            
            //create PreparedStatement
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, username);
//            Character[] passwordString = new Character[password.length];   //converting Char[] password to Character[] for insertion into database
 //           for(int i = 0; i < password.length; i++){
 //               passwordString[i] = password[i];
 //           }
//           Array sqlPasswordArray = connection.createArrayOf("CHAR", passwordString); //throws SQLException: feature not implemented: createArrayOf(String,Object[]).
            String passwordString = new String(password);   //storing password as string is not secure, change later
            statement.setString(4, passwordString);      
                    
            //execute statement
            statement.execute();
            System.out.println("Added user!");
        } catch (SQLException ex) {
            System.out.println( ex.getMessage( ) );
        }
        
    }
    void saveSession(){
        
    }
    public void changePassword(char[] pw){
        password = pw;
    }
    /**
     * 
     * @return 
     */
  /*  private Character[] passwordToObject(){
        Character[] passwordArray = null;
        for(int i = 0; i < password.length - 1; i++){
            passwordArray[i] = password[i];
        }
        return passwordArray;
    }*/
}
