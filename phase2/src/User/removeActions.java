package User;


import java.util.ArrayList;

public class removeActions {
    ClientUser user;
    UserManager um;
    ItemApprovalManager iam;
    public removeActions(ClientUser user,UserManager um,ItemApprovalManager iam){
        this.user=user;
        this.um=um;
        this.iam=iam;
    }


    public void deleteAction(ArrayList<String> action){
        String check=action.get(1);
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
        iam.removeUser(um.getUsername(user));
    }

    private void deleteItemTicket(String itemName){
        iam.removeItem(itemName);
    }
}
