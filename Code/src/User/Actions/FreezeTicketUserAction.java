package User.Actions;

import User.Adapter.ClientUserController;
import User.Entity.ClientUser;
import User.UseCase.ApprovalManager;

public class FreezeTicketUserAction implements UserAction {
    ClientUser user;
    public FreezeTicketUserAction(ClientUser user){
        this.user=user;
    }

    /**
     * return object indicator of the action
     */
    @Override
    public String getIndicator() {
        return getType()+user.getUsername()+getItem();
    }
    /**
     * return object type of the action
     */
    @Override
    public String getType() {
        return "Freeze Ticket";
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
        return "";
    }

    @Override
    public String toString() {
        return "Requested to unfreeze";
    }
}
