package Main.UI;

import User.AdministrativeUser;
import User.ClientUser;
import User.ItemApprovalManager;
import User.UserManager;

import java.util.Scanner;

public class CreateAdmin {


    ClientUser user;
    Scanner sc;
    UserManager um;
    ItemApprovalManager iam;
    public CreateAdmin(ClientUser user,UserManager um, ItemApprovalManager iam){
        this.user=user;
        sc=new Scanner(System.in);
        this.um = um;
        this.iam = iam;
    }

    public void run(){
        System.out.println("Create new admin");
        System.out.println("Enter the username of new admin type 0 to quit.");
        String input4=sc.nextLine();
        if (!input4.equals("0")){
            System.out.println("Now enter the password of new admin");
            String input5555=sc.nextLine();
            ((AdministrativeUser)user).addNewAdmin(input4,input5555);
            System.out.println("New admin created successfully! Returning to menu");
        }
    }
}
