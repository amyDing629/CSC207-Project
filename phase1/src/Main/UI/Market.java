package Main.UI;

import Inventory.Inventory;
import Inventory.Item;
import Main.GateWay;
import User.*;
import java.util.Scanner;

public class Market {

    public Scanner sc;
    public User user;
    public Inventory iv;

    public Market(User u, GateWay gw) {
        this.iv = new Inventory(gw);
        user = u;
        sc = new Scanner(System.in);
    }

    public void run(){
        System.out.println("Hello "+ user.getUsername());
        for(Item item:iv.getAvailableList()){
            System.out.println("Item:"+item);
        }
        System.out.println("--------------------------");
    }
}