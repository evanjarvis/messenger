package messenger;

import static java.lang.System.err;
import java.sql.*;
import javax.activation.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;

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
     * Constructor for a user with username and password. User must be validated with the validate()
     * method in order to add other information.
     * @param un the username
     * @param pw the password
     * @throws SQLException
     */
    public User(String un, char[] pw) throws SQLException{
        username = un;
        password = pw;
        firstName = null;
        lastName = null;
    }
    public boolean validate() throws SQLException{
        //TODO: insert user validation code here
        try{
            //establish connection
            Connection connection = DriverManager.getConnection(HOST, USER, PASS);
            
            //prepare SQL statement
            String usernameSql = "SELECT FROM MESSENGER.USERS.USERNAME(?)";
            String passwordSql = "SELECT FROM MESSENGER.PASSWORD(?)";
            
            //set up PreparedStatements
            PreparedStatement queryUsername = connection.prepareStatement(usernameSql);
            PreparedStatement queryPassword = connection.prepareStatement(passwordSql);
            queryUsername.setString(1, username);
            System.out.println(username);
            String passwordString = new String(password);
            queryPassword.setString(1, passwordString);

            
            //execute
            

        } catch(SQLException err){
            System.out.println("Database connection failed");
        }
        return false;
    }
    
    
    void loopPrint() throws SQLException{
    Connection con = DriverManager.getConnection(HOST, USER, PASS);
    Statement sta = con.createStatement(); 
      ResultSet res = sta.executeQuery("SELECT FROM USER.FIRST_NAME");
      System.out.println("Customers");
      while (res.next()) {
        String firstName = res.getString("FIRST_NAME");
        //String lastName = res.getString("LastName");
        System.out.println("   "+firstName);
      }}
    /**
     * Registers a user by adding it to the database.
     */
    void Register() throws SQLException{
        
        try {
            //establish connection
            Connection connection = DriverManager.getConnection(HOST, USER, PASS);
            System.out.println("Connection established!");
            
            //prepare SQL statement
            String sql = "INSERT INTO USERS (FIRST_NAME, LAST_NAME, USERNAME, PASSWORD) " +
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
    void addNewsfeed(String newsfeed) throws SQLException{
        //establish connection
       
            Connection connection = DriverManager.getConnection(HOST, USER, PASS);
            System.out.println("Connection established!");
            
            String sql = "INSERT INTO NEWSFEEDMAIN (FIRST_NAME, LAST_NAME, NEWSFEED) " +
                   "VALUES (?, ?, ?)";
            
            //create PreparedStatement
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            System.out.println(newsfeed);
            statement.setString(3, newsfeed);
        
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
