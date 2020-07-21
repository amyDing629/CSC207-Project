package Main.UI;

import User.ClientUser;
import User.UserManager;

import java.util.Scanner;

/**
 * [ClientUser Interface]
 * shows the interface that the user uses
 */
public class Register {
    /**
     * read input
     */
    public Scanner sc;
    /**
     * the object that edits the user list of input gateway
     */
    public UserManager a;

    /**
     * [constructor]
     */
    public Register(UserManager um){
        sc = new Scanner(System.in);
        a= um;
    }

    /**
     * run the system
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        int input = 0;
        while (input != 1) {
            System.out.println("--------------------\nRegister");
            System.out.println("Please enter your username!");
            System.out.print(">");
            String username = sc.nextLine();
            System.out.println("Please enter your password!");
            System.out.print(">");
            String password = sc.nextLine();
            if (a.getUser(username) == null) {
                a.createClientUser(username,password,false);
                System.out.println("Your account has been successfully created!");
                System.out.println("Your id: " +a.getId(a.getUser(username)));
                System.out.println("Your username: " + a.getUsername(a.getUser(username)));
                System.out.println("Your password: " + a.getPassword(a.getUser(username)));
                input=1;
            }
            else {
                System.out.println("The username already exists, please try to register again, " +
                        "enter any number to continue. Enter 1 to exit.");
                input = sc.nextInt();
                sc.nextLine();
            }
        }
    }
}
