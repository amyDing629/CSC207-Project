package Main.UI;

import Main.Main;
import Trade.TradeManager;
import User.AdministrativeUser;
import User.FileEditor;
import User.UserManager;
import User.User;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import Main.GateWay;

public class Login {
    public Scanner sc;
    public UserManager a;
    public TradeManager tm;
    public GateWay gw;

    public Login(GateWay gw){
        sc = new Scanner(System.in);
        a = new UserManager(gw);
        tm = new TradeManager(gw);
        this.gw = gw;
    }

    public void run() throws IOException {
        int input=0;
        while (input==0) {
            System.out.println("Please enter your account username!");
            System.out.print(">");
            String username = sc.nextLine();
            System.out.println("Please enter your password!");
            System.out.print(">");
            String password = sc.nextLine();
            if (a.verifyUser(username, password)) {
                while (true) {
                    System.out.println("------------------------------------------------------------");
                    System.out.println("Hello,"+username);
                    ((AdministrativeUser)a.getUser("admin")).incompleteTransaction(a.getUser(username));
                    ((AdministrativeUser)a.getUser("admin")).tradeLimit(a.getUser(username));
                    System.out.println("Freeze Status: "+a.getUser(username).getIsFrozen());
                    System.out.println("Trade limit: " + tm.getTradeNumber(a.getUser(username)) + "/" + a.getUser(username).getWeekTransactionLimit());
                    System.out.println("Incomplete trade limit: " + (tm.getIncomplete(a.getUser(username)).size() + "/" + a.getUser(username).getIncompleteTransactionLimit()));
                    System.out.println("**************************************************************");
                    System.out.println("Actions:\n1.Edit information\n2.Trade\n3.Inventory\n4.Market\n0.quit to menu");
                    System.out.print(">");
                    int op = sc.nextInt();
                    sc.nextLine();
                    if (op == 1) {
                        EditInfo ei=new EditInfo(a.getUser(username), gw);
                        ei.run();
                    } else if (op == 2) {
                        UserTrade ut=new UserTrade(a.getUser(username), gw);
                        ut.run();
                    } else if (op == 3) {
                        UserInventory ui=new UserInventory(a.getUser(username), gw);
                        ui.run();
                    } else if (op == 4) {
                        Market m=new Market(a.getUser(username));
                        m.run();
                    } else if (op == 0) {
                        input=1;
                        break;
                    } else {
                        System.out.println("Sorry I don't understand your command, plz enter it again");
                    }
                }
            } else {
                System.out.println("You have incorrect username or password, please try to login again, enter any number to continue. enter 1 to exit.");
                input=sc.nextInt();
                sc.nextLine();
            }
        }
    }
}
