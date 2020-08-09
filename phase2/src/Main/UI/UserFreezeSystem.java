package Main.UI;

import User.Entity.ClientUser;
import User.UseCase.AdminActivityManager;
import User.UseCase.ItemApprovalManager;
import User.UseCase.UserManager;

import java.util.ArrayList;

public class UserFreezeSystem {
    ClientUser user;
    UserManager um;
    ItemApprovalManager iam;
    public AdminActivityManager aam;
    UIcontoller uc;
    public UserFreezeSystem(ClientUser user, UserManager um, ItemApprovalManager iam, AdminActivityManager aam,UIcontoller uc){
        this.user=user;
        this.um=um;
        this.iam = iam;
        this.aam=aam;
        this.uc=uc;
    }

    public void run() {
        System.out.println("User Freeze System");
        System.out.println("Menu");
        if (um.getIsFrozen(user)) {
            System.out.println("1.request to remove freeze");
        }
        if (um.getIsAdmin(user)) {
            System.out.println("2.Freeze user\n3.unfreeze user");
        }
        if (!um.getIsFrozen(user) && !um.getIsAdmin(user)) {
            System.out.println("Returning to menu.....");
        } else {
            int inputF = uc.getNumber("Please enter a number!");
            if (inputF == 2) {
                String input3 = uc.getString("Type in the username of user you want to freeze, type 0 to quit.");
                if (!input3.equals("0")) {
                    ClientUser ha = um.getUser(input3);
                    if (ha == null) {
                        System.out.println("Sorry there is no such user, returning to main menu.");
                    } else {
                        aam.setFreeze(user,true);
                        System.out.println("ClientUser:" + um.getUsername(ha) + " account has been frozen");
                        System.out.println("Username: " + um.getUsername(ha));
                        System.out.println("Username: " + um.getPassword(ha));
                    }
                }
            } else if (inputF == 3) {
                ArrayList<ArrayList<String>> usa = iam.getUserApproval();
                for (int i = 0; i < usa.size(); i++) {
                    System.out.println("User" + i + ": " + usa.get(i).get(1));
                    System.out.println("Reason: " + usa.get(i).get(2));
                    System.out.println("**************************");
                    System.out.println("Currently there is no user freeze request");
                }
                String inputU = uc.getString("Enter the ClientUser number to approve,enter -1 to quit.");
                int k = Integer.parseInt(inputU);
                if (k < usa.size() && k > -1) {
                    aam.setFreeze(um.getUser(usa.get(k).get(1)),false);
                    usa.remove(k);
                }
            } else if (inputF == 1) {
                String des = uc.getString("Please enter the reason why you should unfreeze...enter -1 to quit");
                if (!des.equals("-1")) {
                    System.out.println("Request successfully");
                    System.out.println("Please wait for the administrator to approve");
                }else{
                    System.out.println("returning....");
                }
            }
        }
    }
}
