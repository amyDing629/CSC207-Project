package User.UseCase;


import User.Adapter.ClientUserController;
import User.Adapter.IUserController;
import User.Entity.ClientUser;
import User.UseCase.ApprovalManager;
import User.UseCase.UserManager;

import java.util.ArrayList;

public class RemoveActions {
    ClientUser user;
    IUserController uc;
    ApprovalManager iam;

    public RemoveActions(ClientUser user, IUserController uc, ApprovalManager iam) {
        this.user = user;
        this.uc = uc;
        this.iam = iam;
    }

    public void deleteAction(ArrayList<String> action) {
        String check = action.get(0);
        switch (check) {
            case "password":
                deletePassWord(action.get(1));
                break;
            case "Freeze ticket":
                deleteFreezeTicket();
                break;
            case "Item ticket":
                deleteItemTicket(action.get(1));
                break;
            case "add to borrow":
                deleteWishBorrow(action.get(1));
                break;
             case "add to wish":
                deleteWishLend(action.get(1));
                break;
        }
    }

    private void deletePassWord(String passAction){
        uc.setPassword(user.getUsername(),passAction);
        uc.removeAction(user.getUsername(),"password",passAction);
    }

    private void deleteWishBorrow(String borrowWish){
        uc.deleteBItem(user.getUsername(),borrowWish);
        uc.removeAction(user.getUsername(),"add to borrow","");
    }

    private void deleteWishLend(String lendWish){
        uc.deleteLItem(user.getUsername(),lendWish);
        uc.removeAction(user.getUsername(),"add to wish","");
    }
    private void deleteFreezeTicket(){
        iam.removeUserApproval(user.getUsername());
        uc.removeAction(user.getUsername(),"Freeze ticket","");
    }

    private void deleteItemTicket(String itemName){
        iam.removeItemApproval(itemName);
        uc.removeAction(user.getUsername(),"Item ticket","");
    }
}
