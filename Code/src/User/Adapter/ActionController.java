package User.Adapter;

import User.Actions.PasswordUserAction;
import User.Actions.UserAction;
import User.UseCase.ApprovalManager;
import User.UseCase.UserManager;

import java.util.ArrayList;

public class ActionController {

    ApprovalManager iam= new ApprovalManager();
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


    /**
     * @param ua name of user
     * check the type of UserAction and reverse the action
     */
    public void check(UserAction ua){
        switch (ua.getType()) {
            case "pass":
                passWordReverse(ua.getUsername(), ua.getItem());
                break;
            case "Freeze Ticket":
                deleteFreezeTicket(ua.getUsername());
                break;
            case "add to borrow":
                deleteWishBorrow(ua.getUsername(), ua.getItem());
                break;
            case "add to wish":
                deleteItemTicket(ua.getItem());
                break;
        }
    }
    /**
     * @param username name of user
     * @param password passowrd of user
     * reverse the action of changing password
     */
    private void passWordReverse(String username,String password){
        um.setPassword(username,password);
    }
    /**
     * @param username name of user
     * reverse the action of freeze ticket
     */
    private void deleteFreezeTicket(String username){
        iam.removeUserApproval(username);
    }
    /**
     * @param username name of user
     * @param borrowWish name of the item
     * reverse the action of adding WishBorrow
     */
    private void deleteWishBorrow(String username,String borrowWish){
        um.deleteBItem(username,borrowWish);
    }
    /**
     * @param itemName name of item
     * reverse the action of adding ticket
     */
    private void deleteItemTicket(String itemName){
        iam.removeItemApproval(itemName);
    }

    /**
     * @param username name of user
     * reverse the latest action of user by its username.
     */
    public void reverse(String username){

        UserAction ua= getActions(username).get(0);
        check(ua);
        removeAction(username,ua);
    }
    /**
     * @param username name of user
     * @param ua user's action
     * remove the action from user by its username
     */
    public void removeAction(String username,UserAction ua){
        um.removeAction(username,ua);
    }

}
