//package Main.UI;
////check
//import Inventory.UseCase.Inventory;
//import Trade.UseCase.TradeManager;
//import User.AdminActivityManager;
//import User.ClientUser;
//import User.ItemApprovalManager;
//import User.UserManager;
//
//import java.io.IOException;
//import java.util.Scanner;
//
///**
// * [ClientUser Interface]
// * shows the interface that the user uses
// */
//public class Login {
//    /**
//     * read input
//     */
//    public Scanner sc;
//    /**
//     * the object that edits the user list of input gateway
//     */
//    public UserManager a;
//    /**
//     * the object that edits the Item list of input gateway
//     */
//    public TradeManager tm;
//
//    public Inventory iv;
//    public UIcontoller uc;
//    public ItemApprovalManager iam;
//    public AdminActivityManager aam;
//    /**
//     * [constructor]
//     */
//    public Login(UserManager um, TradeManager tm, Inventory iv, ItemApprovalManager iam, AdminActivityManager aam,UIcontoller uc){
//        sc = new Scanner(System.in);
//        a = um;
//        this.tm = tm;
//        this.iv = iv;
//        this.iam = iam;
//        this.aam=aam;
//        this.uc=uc;
//    }
//
//    /**
//     * run the system
//     */
//    public void run() throws IOException {
//        int input=0;
//        while (input==0) {
//            String username = uc.getString("Please enter your account username!");
//            String password = uc.getString("Please enter your password!");
//            if (a.verifyUser(username, password)) {
//                while (true) {
//                    ClientUser user=a.getUser(username);
//                    System.out.println("------------------------------------------------------------");
//                    uc.UserDisplayStatus(user);
//                    System.out.println("Actions:\n1.Edit information\n2.Trade\n3.Inventory\n4.Market\n0.quit to menu");
//                    System.out.print(">");
//
//                    int op = uc.getNumber("Please enter a number");
//                    if (op == 1) {
//                        EditInfo ei = new EditInfo(user, a, iv, iam,aam,uc);
//                        ei.run();
//                    } else if (op == 2) {
//                        UserTrade ut = new UserTrade(user, a, tm,uc);
//                        ut.run();
//                    } else if (op == 3) {
//                        UserInventory ui=new UserInventory(user, a, tm, iv, iam,uc);
//                        ui.run();
//                    } else if (op == 4) {
//                        Market m=new Market(user, iv,a,uc);
//                        m.run();
//                    } else if (op == 0) {
//                        input=1;
//                        break;
//                    } else {
//                        System.out.println("Sorry I don't understand your command, plz enter it again");
//                    }
//                }
//            } else {
//                input = uc.getNumber("You have incorrect username or password, please try to login again, " +
//                        "enter 0 to continue,any other number to quit ot menu.");
//            }
//        }
//    }
//}
