package Main.UI;

import Main.GateWay;
import User.*;
import java.util.Scanner;

public class Market {

    public Scanner sc;
    public UserManager um;
    public User user;
    public GateWay gw;

    public Market(User u) {
        user = u;
        sc = new Scanner(System.in);
        um = new UserManager();
        gw =new GateWay();
    }

    public void run(){
        System.out.println("Hello "+ user.getUsername());
        for (User b: gw.getUsers()){
            System.out.println(b.getUsername());
            for(String c:b.getWishLend()){
                System.out.println("Item:"+c);
            }
            System.out.println("--------------------------");
        }
    }
}