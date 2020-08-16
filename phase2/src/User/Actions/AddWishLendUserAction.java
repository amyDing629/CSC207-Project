package User.Actions;

import User.Adapter.ClientUserController;
import User.Entity.ClientUser;
import User.UseCase.ApprovalManager;

public class AddWishLendUserAction implements UserAction {
    ClientUser user;
    String itemName;
    public AddWishLendUserAction(ClientUser user, String itemName){
        this.user=user;
        this.itemName=itemName;
    }
    /**
     * return object indicator of the action
     */
    @Override
    public String getIndicator() {
        return "AddWishLendUserAction"+itemName;
    }
    /**
     * return object type of the action
     */
    @Override
    public String getType() {
        return "add to wish";
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
        return itemName;
    }

    @Override
    public String toString() {
        return "Requested: "+itemName;
    }
}
