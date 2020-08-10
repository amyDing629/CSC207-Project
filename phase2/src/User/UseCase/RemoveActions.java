package User.UseCase;


import User.Entity.ClientUser;
import User.UseCase.ApprovalManager;
import User.UseCase.UserManager;

import java.util.ArrayList;

public class RemoveActions {
    ClientUser user;
    UserManager um;
    ApprovalManager iam;

    public RemoveActions(ClientUser user, UserManager um, ApprovalManager iam) {
        this.user = user;
        this.um = um;
        this.iam = iam;
    }


    public void deleteAction(ArrayList<String> action) {
        String check = action.get(1);
        switch (check) {
            case "password":
                deletePassWord(action.get(0));
                break;
            case "Freeze ticket":
                deleteFreezeTicket();
                break;
            case "Item ticket":
                deleteItemTicket(action.get(0));
                break;
            case "add to borrow":
                deleteWishBorrow(action.get(0));
                break;
        }
    }

    private void deletePassWord(String passAction){
        um.set(user,passAction);
    }

    private void deleteWishBorrow(String borrowWish){
        um.removeBWishes(borrowWish,user);
    }

    private void deleteFreezeTicket(){
        iam.removeUserApproval(user.getUsername());
    }

    private void deleteItemTicket(String itemName){
        iam.removeItemApproval(itemName);
    }
}