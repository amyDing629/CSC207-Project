//package Main.UI;
//
//import Trade.Entity.Trade;
//import Trade.UseCase.TradeManager;
//import Trade.TradeUI;
//import User.ClientUser;
//import User.UserManager;
//
//import java.util.List;
//import java.util.Scanner;
//
///**
// * [ClientUser Interface]
// * shows the interface that the user uses
// */
//public class UserTrade {
//    /**
//     * read input
//     */
//    public Scanner sc;
//    /**
//     * the object that edits the user list of input gateway
//     */
//    public UserManager um;
//    /**
//     * the object that edits the Item list of input gateway
//     */
//    public TradeManager tm;
//    /**
//     * user in user system
//     */
//    public ClientUser user;
//    UIcontoller uc;
//
//    /**
//     * [constructor]
//     * @param u  the input user
//     * @param uc
//
//     */
//    public UserTrade(ClientUser u, UserManager um, TradeManager tm, UIcontoller uc) {
//        user = u;
//        sc = new Scanner(System.in);
//        this.um = um;
//        this.tm = tm;
//        this.uc=uc;
//    }
//
//    /**
//     * run the system
//     */
//    public void run(){
//        uc.UserDisplayStatus(user);
//        int escape = 0;
//        while (escape == 0) {
//            List<Trade> iL = tm.getIncomplete(user);
//            List<Trade> iU = tm.getUnconfirmed(user);
//            System.out.println("--------------------\nTrade");
//            System.out.println("Hello,user "+ um.getUsername(user));
//            System.out.println("Menu:\n1.confirm trades\n2.complete trade\n3.Trade History\n0.quit");
//            int input1 =uc.getNumber("Please enter a number");
//            switch (input1) {
//                case 1:
//                    for (int i = 0; i < iU.size(); i++) {
//                        System.out.println(i + ". " + iU.get(i).toString());
//                    }
//                    int input2 = uc.getNumber("Which trade do you want to confirm? select the number before trade id or " +
//                            "enter any integers else to exit");;
//                    if ((input2 < iU.size()) && (input2 >= 0)) {
//                        TradeUI tu = new TradeUI(user, iU.get(input2).getId(), tm, um);
//                        tu.run();
//                    }
//                    break;
//                case 2:
//                    for (int i = 0; i < iL.size(); i++) {
//                        System.out.println((i + 1) + ". " + iL.get(i).toString());
//                    }
//                    int input3 =  uc.getNumber("Which trade do you want to complete? select the number before trade id " +
//                            "or enter any integers else to exit ");
//                    if ((input3 < (iL.size() + 1)) && (input3 > 0)) {
//                        TradeUI tu = new TradeUI(user, iL.get(input3 - 1).getId(), tm, um);
//                        tu.run();
//                    } else {
//                        System.out.println("Wrong Number, returning to UserTrade menu....");
//                    }
//                case 3:
//                    System.out.println("Hi user: " + um.getUsername(user));
//                    System.out.println("Your trades:");
//                    List<Trade> tHis = tm.getTradeHistoryTop(user);
//                    System.out.println("****************");
//                    for (int i = 0; i < tHis.size(); i++) {
//                        System.out.println((i + 1) + ". " + tHis.get(i).toString());
//                    }
//                    System.out.println("****************");
//                    System.out.println("Most frequent user:");
//                    for (String a : um.getFrequentUser(tm, user)) {
//                        System.out.println(a);
//                    }
//                case 0:
//                    escape = 1;
//                    break;
//            }
//
//        }
//    }
//}
