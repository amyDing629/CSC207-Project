package Main.UI;

import User.Entity.ClientUser;
import User.UseCase.AdminActivityManager;
import User.UseCase.ItemApprovalManager;
import User.UseCase.UserManager;

public class UserLimit {

    ClientUser user;
    UserManager um;
    ItemApprovalManager iam;
    UIcontoller uc;
    AdminActivityManager aam;
    public UserLimit(ClientUser user, UserManager um, ItemApprovalManager iam, UIcontoller uc, AdminActivityManager aam){
        this.user=user;
        this.um=um;
        this.iam = iam;
        this.uc=uc;
        this.aam=aam;
    }
    public void run(){
        System.out.println("Change user's limit");
        System.out.println("Menu\n1.Change trade limit\n2.Change Incomplete Transaction limit\n" +
                "3.Change the difference between borrowed and lend");
        int input30 = uc.getNumber("Enter a number!");
        if (input30 == 1) {
            String input31 = uc.getString("Which user do you want to change?");
            if (input31.equals("-1")||um.getUser(input31) == null) {
                if(um.getUser(input31) == null){
                    System.out.println("You entered wrong username");
                }
            }else {
                ClientUser b = um.getUser(input31);
                int input33 = uc.getNumber("Enter a number to change");
                aam.setWeekTransactionLimit(b,input33);
            }
        }
        else if(input30==2){
            String input31=uc.getString("Which user do you want to change?enter -1 to break");
            if (input31.equals("-1")||um.getUser(input31) == null) {
                if (um.getUser(input31) == null) {
                    System.out.println("You entered wrong username");
                }
            }else {
                ClientUser b = um.getUser(input31);
                int input33 = uc.getNumber("Enter a number to change");
               aam.setIncompleteTransaction(b,input33);
            }
        }
        else if(input30==3){
            String input31=uc.getString("Which user do you want to change? enter -1 to break");
            if (input31.equals("-1")||um.getUser(input31) == null) {
                if (um.getUser(input31) == null) {
                    System.out.println("You entered wrong username");
                }
            }else {
                ClientUser b = um.getUser(input31);
                System.out.println("Enter a number to change");
                int input33 = uc.getNumber("Enter a number to change");
                aam.setDiff(b,input33);
            }
        }
    }
}
