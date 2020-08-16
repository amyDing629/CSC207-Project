package User.Actions;

import User.Adapter.ClientUserController;
import User.Entity.ClientUser;
import User.UseCase.ApprovalManager;

public class AddWishBorrowUserAction implements UserAction {

    ClientUser user;
    String borrowWish;
    public AddWishBorrowUserAction(ClientUser user, String borrowWish){
        this.user=user;
        this.borrowWish=borrowWish;
    }
    /**
     * return object indicator of the action
     */
    @Override
    public String getIndicator() {
        return "AddWishBorrowUserAction"+getUsername()+borrowWish;
    }
    /**
     * return object type of the action
     */
    @Override
    public String getType() {
        return "add to borrow";
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
        return borrowWish;
    }

    @Override
    public String toString() {
        return "Requested: "+borrowWish;
    }
}
