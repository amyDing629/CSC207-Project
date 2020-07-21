package Main.UI;
import User.ClientUser;
import User.UserManager;

import java.util.Scanner;

public class ChangePass {
    Scanner sc;
    ClientUser user;
    UserManager um;

    public ChangePass(ClientUser user, UserManager um){
        this.um=um;
        sc=new Scanner(System.in);
        this.user=user;
    }
    public void run(){
        System.out.println("Change password");
        System.out.println("Type in the password you wanted to change, type 0 to quit.");
        String input2=sc.nextLine();
        if (!input2.equals("0")){
            um.set(user,input2);
            System.out.println("Changed password successfully!");
        }
    }
}
