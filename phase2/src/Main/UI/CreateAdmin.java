package Main.UI;

import User.*;

import java.util.Scanner;

public class CreateAdmin {


    ClientUser user;
    Scanner sc;
    UserManager um;
    ItemApprovalManager iam;
    public AdminActivityManager aam;
    UIcontoller uc;
    public CreateAdmin(ClientUser user, UserManager um, ItemApprovalManager iam, AdminActivityManager aam,UIcontoller uc){
        this.user=user;
        sc=new Scanner(System.in);
        this.um = um;
        this.iam = iam;
        this.aam=aam;
        this.uc=uc;
    }

    public void run(){
        System.out.println("Create new admin");
        System.out.println("Enter the username of new admin type 0 to quit.");
        String input4=sc.nextLine();
        if (!input4.equals("0")){
            String input5555=uc.getString("Now enter the password of new admin");
            aam.addNewAdmin(user,input4,input5555);
            System.out.println("New admin created successfully! Returning to menu");
        }
    }
}
