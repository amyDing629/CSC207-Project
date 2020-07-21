package Main.UI;

import Inventory.Inventory;
import Inventory.Item;
import User.*;
import java.util.Scanner;

/**
 * [ClientUser Interface]
 * shows the interface that the user uses
 */
public class Market {

    /**
     * read input
     */
    public Scanner sc;
    /**
     * user in user system
     */
    public ClientUser user;
    /**
     * the object in the Item list of input gateway
     */
    public Inventory iv;

    public UserManager um;
    /**
     * [constructor]
     * @param u the input user
     */
    public Market(ClientUser u, Inventory iv,UserManager um) {
        this.iv = iv;
        user = u;
        this.um=um;
        sc = new Scanner(System.in);
    }

    /**
     * run the system
     */
    public void run(){
        System.out.println("Hello "+ um.getUsername(user));
        for(Item item:iv.getAvailableList()){
            System.out.println("Item:"+item);
        }
        System.out.println("--------------------------");
    }
}