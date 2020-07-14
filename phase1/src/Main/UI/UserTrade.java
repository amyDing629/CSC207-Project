package Main.UI;

import Main.GateWay;
import Trade.Trade;
import User.AdministrativeUser;
import User.User;
import User.UserManager;
import Trade.*;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class UserTrade {
    public Scanner sc;
    public UserManager um;
    public TradeManager tm;
    public User user;

    public UserTrade(User u, GateWay gw) {
        user = u;
        sc = new Scanner(System.in);
        um = new UserManager(gw);
        tm = new TradeManager(gw);
    }

    public void run() throws IOException {
        Scanner sc = new Scanner(System.in);
        int escape = 0;
        while (escape == 0) {
            List<Trade> iL = tm.getIncomplete(user);
            List<Trade> iU = tm.getUnconfirmed(user);
            System.out.println("--------------------\nTrade");
            System.out.println("Hello,user" + user.getUsername());
            System.out.println("Menu:\n1.confirm trades\n2.complete trade\n3.Trade History\n0.quit");
            int input1 = sc.nextInt();
            sc.nextLine();
            switch (input1) {
                case 1:
                    for (int i = 0; i < iU.size(); i++) {
                        System.out.println(i+". " + iU.get(i).toString());
                    }
                    System.out.println("Which trade do you want to confirm? select trade number to confirm, enter anything else to exit");
                    int input2 = sc.nextInt();
                    sc.nextLine();
                    if ((input2 < iU.size()) && (input2 >= 0)) {
                        TradeUI tu=new TradeUI(user,iU.get(input2).getId(),tm,um);
                        tu.run();
                    }
                    break;
                case 2:
                    for (int i = 0; i < iL.size(); i++) {
                        System.out.println((i + 1) + ". " + iL.get(i).toString());
                    }
                    System.out.println("Which trade do you want to complete? select trade ID to confirm");
                    int input3 = sc.nextInt();
                    sc.nextLine();
                    if ((input3 < (iL.size() + 1)) && (input3 > 0)) {
                        TradeUI tu = new TradeUI(user, iL.get(input3 - 1).getId(), tm, um);
                        tu.run();
                    } else {
                        System.out.println("Wrong Number, returning to UserTrade menu....");
                    }
                case 3:
                    System.out.println("Hi user: " + user.getUsername());
                    System.out.println("Compeleted past trades:");
                    List<Trade> tHis = tm.getTradeHistoryTop(user);
                    System.out.println("****************");
                    for (int i = 0; i < tHis.size(); i++) {
                        System.out.println((i + 1) + ". " + tHis.get(i).toString());
                    }
                    System.out.println("****************");
                    System.out.println("Most frequent user:");
                    for (String a : um.getFrequentUser(tm, user)) {
                        System.out.println(a);
                    }
                case 0:
                    escape = 1;
                    break;
            }

        }
    }
}
