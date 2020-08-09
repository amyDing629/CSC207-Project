package Main.UI;

import User.UseCase.UserManager;

/**
 * [ClientUser Interface]
 * shows the interface that the user uses
 */
public class Register {
    /**
     * the object that edits the user list of input gateway
     */
    public UserManager a;
    UIcontoller uc;
    /**
     * [constructor]
     */
    public Register(UserManager um,UIcontoller uc){
        a= um;
        this.uc=uc;
    }

    /**
     * run the system
     */
    public void run() {
        int input = 0;
        while (input != 1) {
            System.out.println("--------------------\nRegister");
            String username = uc.getString("Please enter your username!");
            String password =uc.getString("Please enter your password!");
            if (a.getUser(username) == null) {
                a.createClientUser(username,password,false);
                System.out.println("Your account has been successfully created!");
                System.out.println("Your id: " +a.getId(a.getUser(username)));
                System.out.println("Your username: " + a.getUsername(a.getUser(username)));
                System.out.println("Your password: " + a.getPassword(a.getUser(username)));
                input=1;
            }
            else {
                input=uc.getNumber("The username already exists, please try to register again, " +
                        "enter any number to continue. Enter 1 to exit.");
            }
        }
    }
}
