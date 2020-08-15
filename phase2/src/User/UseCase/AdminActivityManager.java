package User.UseCase;

import Trade.UseCase.TradeManager;
import User.Entity.ClientUser;
import User.Gateway.DataAccess;
import User.Gateway.UserDataAccess;
import User.PointSystem.PointManager;

import java.util.UUID;

/**
 * This is the use case class of administrative user
 * Administrative user are allowed to
 */

public class AdminActivityManager {
    UserManager um = new UserManager();
    DataAccess userAccess = new UserDataAccess();


    /**
     * @param a name of user
     * @param s freeze status of user
     */
    public void setFreeze(String a,boolean s){
        ClientUser ca = (ClientUser)userAccess.getObject(a);
        if(ca != null){ca.setFrozen(s);}
        userAccess.updateSer();
    }

    /**
     * @param username name of user
     * @param password password of user
     * add new admin user
     */
    public void addNewAdmin(String username, String password) {
        if(userAccess.getObject("admin")!= null) {
            um.addUser(new ClientUser(username, password, true));
        }
        for(ClientUser i:um.getUserList()){
            System.out.println(i.getUsername());
            System.out.println(i.getPassword());
        }
    }





}
