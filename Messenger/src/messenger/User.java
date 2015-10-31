package messenger;

/**
 *
 * @author Me
 */
public class User {
    private final String name;
    private final String username;
    /**
     * Constructor for a new User.
     * @param n name
     * @param un username
     * @param pw password
     */
    public User(String n, String un, String pw){
        name = n;
        username = un;
    }
    public boolean validate(){
        //TODO: insert user validation code here
        return false;
    }
}
