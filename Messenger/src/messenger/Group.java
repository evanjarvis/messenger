package messenger;

import java.util.ArrayList;

/**
 *
 * @author Me
 */
public class Group {
    private ArrayList userList;
    /**
     * Add a user to the group.
     * @param user the user to be added
     */
    void addUser(User user){
        userList.add(user);
    }
    /**
     * Remove a user from the group.
     * @param user The user to be removed
     */
    void removeUser(User user){
        userList.remove(user);
    }
    /**
     * Return an ArrayList containing all Users in the group.
     * @return the list of users as an ArrayList
     */
    public ArrayList toArrayList(){
        return userList;
    }
}
