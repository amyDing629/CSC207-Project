package User.Actions;

import User.Adapter.ClientUserController;
import User.Entity.ClientUser;
import User.UseCase.UserManager;

import java.util.ArrayList;

public class PasswordUserAction implements UserAction {
    String prePass;
    ClientUser user;
    public PasswordUserAction(ClientUser user, String prePass){
        this.user=user;
        this.prePass=prePass;
    }
    /**
     * return object indicator of the action
     */
    @Override
    public String getIndicator() {
        return "PasswordUserAction"+getUsername()+prePass;
    }
    /**
     * return object type of the action
     */
    @Override
    public String getType() {
        return "pass";
    }
    /**
     * return action user's name
     */
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * return the string or item that needed by the action to perform
     */
    @Override
    public String getItem() {
        return prePass;
    }

    @Override
    public String toString() {
        return "Changed password from "+prePass+" to "+user.getPassword();
    }
}
