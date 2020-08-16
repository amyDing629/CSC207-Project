package User.UseCase;

import User.Actions.UserAction;
import User.Entity.ClientUser;

import javax.swing.*;
import java.util.ArrayList;

public class ActionManager {

    UserManager um=new UserManager();

    public void addAction(String username, UserAction action) {
        um.addAction(username,action);
    }


    /**
     * @param username name of user
     * return list of list of UserActions
     */
    public ArrayList<UserAction> getActions(String username) {
        return um.getActions(um.getUser(username));
    }
    
}
