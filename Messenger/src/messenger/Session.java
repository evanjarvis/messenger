package messenger;

/**
 *
 * @author Me
 */
public class Session {
    Group group;
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
