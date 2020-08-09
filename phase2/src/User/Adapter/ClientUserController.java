package User.Adapter;

import Inventory.Inventory;
import Inventory.Item;
import Trade.TradeManager;
import User.Entity.ClientUser;
import User.UseCase.ItemApprovalManager;
import User.UseCase.UserManager;

import java.util.UUID;

public class ClientUserController {
    protected final UserManager um;
    protected final ItemApprovalManager iam;
    protected final TradeManager tm;

    public ClientUserController(UserManager um, ItemApprovalManager iam, TradeManager tm){
        this.um = um;
        this.iam = iam;
        this.tm = tm;
    }


    public boolean getIsAdmin(ClientUser user){return user.getIsAdmin();}

    public boolean checkItemExist(String item, Inventory iv){
        for (Item n : iv.getLendingList()) {
            if (iv.getName(n).equals(item)) {
                return true;
            }
        }
        return false;
    }

    public ItemApprovalManager getIam() {
        return iam;
    }

    public UserManager getUm() {
        return um;
    }

    public String getUsername(ClientUser user){
        return user.getUsername();
    }

    /**
     * @param name the name of the user that the manager check
     * @param password the password of the user that the manager check
     * Check if the name matches with the password
     */
    public boolean verifyUser(String name, String password) {
        return um.verifyUser(name, password);
    }

    /**
     * @param userId the ID of the user that the manager wants to get
     * find the user by the user ID
     */
    public boolean checkUser(UUID userId) {
        return um.getUser(userId) == null;
    }

    /**
     * @param name the name of the user that the manager wants to get
     * find the user by the user name
     */
    public boolean checkUser(String name) {
        return um.getUser(name)== null;
    }

    public void createClientUser(String name, String password){um.createClientUser(name, password, false);}


}
