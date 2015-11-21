package messenger;

import java.util.ArrayList;
import javax.swing.*;
/**
 *
 * @author DiegoB
 */
public class Newsfeed extends JFrame {
    User user;
    private ArrayList<String> subscriptionArray;    //the usernames of subscribed-to users
    

    /**
     * Subscribe to another user.  Takes the provided username and adds it to the list of
     * usernames subscribed to.
     * @param username the username of the user to subscribe to
     */
    public void subscribe(String username){
        subscriptionArray.add(username);
    }
    /**
     * Returns an ArrayList of all the posts the user has subscribed to.
     * @return the list of posts
     */
    public ArrayList returnFeed(){
        ArrayList feedArray = new ArrayList();  //the list of posts from subscribed-to users
        for(int i = 0; i < subscriptionArray.size(); i++){
            //search for posts from subscribed-to users
        }
        return feedArray;
    }
}