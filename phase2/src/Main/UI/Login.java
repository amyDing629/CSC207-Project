package Main.UI;

import Inventory.Inventory;
import Trade.TradeManager;
import User.ItemApprovalManager;
import User.UserManager;

import java.io.IOException;
import java.util.Scanner;

/**
 * [ClientUser Interface]
 * shows the interface that the user uses
 */
public class Login {
    /**
     * read input
     */
    public Scanner sc;
    /**
     * the object that edits the user list of input gateway
     */
    public UserManager a;
    /**
     * the object that edits the Item list of input gateway
     */
    public TradeManager tm;

    public Inventory iv;

    public ItemApprovalManager iam;

    /**
     * [constructor]
     */
    public Login(UserManager um, TradeManager tm, Inventory iv, ItemApprovalManager iam){
        sc = new Scanner(System.in);
        a = um;
        this.tm = tm;
        this.iv = iv;
        this.iam = iam;
    }

    /**
     * run the system
     */
    public void run() throws IOException {
        int input=0;
        while (input==0) {
            String username;
            String password;
            System.out.println("Please enter your account username!");
            System.out.print(">");
            username = sc.nextLine();
            System.out.println("Please enter your password!");System.out.print(">");
            password = sc.nextLine();

            if (a.verifyUser(username, password)) {
                while (true) {
                    System.out.println("------------------------------------------------------------");
                    System.out.println("Hello," + username);
                    System.out.println(username);
                    //(a.getAdmin(a.getUser("admin"))).incompleteTransaction(a.getUser(username));
                    //(a.getAdmin(a.getUser("admin"))).tradeLimit(a.getUser(username));
                    if(a.getUser(username).readDiff()>=a.getDiff(a.getUser(username))){
                        //(a.getAdmin(a.getUser("admin"))).freeze(a.getUser(username));
                    }
                    System.out.println("Freeze Status: " + a.getIsFrozen(a.getUser(username)));
                    System.out.println("Trade limit: " + tm.getTradeNumber(a.getUser(username)) + "/"
                            + a.getWeekTransactionLimit(a.getUser(username)));
                    System.out.println("Incomplete trade limit: " + (tm.getIncomplete(a.getUser(username)).size()
                            + "/" + a.getIncompleteTransactionLimit(a.getUser(username))));
                    System.out.println("Difference between borrow and lend:"+a.getUser(username).readDiff()+"/"+a.getDiff(a.getUser(username)));
                    System.out.println("**************************************************************");
                    System.out.println("Actions:\n1.Edit information\n2.Trade\n3.Inventory\n4.Market\n0.quit to menu");
                    System.out.print(">");
                    int op = sc.nextInt();
                    sc.nextLine();
                    if (op == 1) {
                        EditInfo ei = new EditInfo(a.getUser(username), a, iv, iam);
                        ei.run();
                    } else if (op == 2) {
                        UserTrade ut = new UserTrade(a.getUser(username), a, tm);
                        ut.run();
                    } else if (op == 3) {
                        UserInventory ui=new UserInventory(a.getUser(username), a, tm, iv, iam);
                        ui.run();
                    } else if (op == 4) {
                        Market m=new Market(a.getUser(username), iv,a);
                        m.run();
                    } else if (op == 0) {
                        input=1;
                        break;
                    } else {
                        System.out.println("Sorry I don't understand your command, plz enter it again");
                    }
                }
            } else {
                System.out.println("You have incorrect username or password, please try to login again, " +
                        "enter 0 to continue,any other number to quit ot menu.");
                input = sc.nextInt();
                sc.nextLine();
            }
        }
    }
}
