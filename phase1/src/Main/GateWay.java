package Main;

import Inventory.Item;
import Trade.Trade;
import User.User;
import java.util.ArrayList;

public class GateWay {
    private final ArrayList<User> users;
    private final ArrayList<Trade> trades;
    private final ArrayList<Item> inventory;
    private final ArrayList<ArrayList<String>> ApprovalItem;
    private final ArrayList<ArrayList<String>> ApprovalUser;

    /**
     * [constructor]
     * the place we store information
     */
    public GateWay(){
        users = new ArrayList<User>();
        trades = new ArrayList<Trade>();
        inventory = new ArrayList<>();
        ApprovalItem=new ArrayList<>();
        ApprovalUser=new ArrayList<>();
    }

    /**
     * return the user list
     */
    public ArrayList<User> getUsers(){
        return users;
    }

    /**
     * return the trade list
     */
    public ArrayList<Trade> getTrades(){
        return trades;
    }

    /**
     * return the item list
     */
    public ArrayList<Item> getInv(){
        return inventory;
    }

    /**
     * return list of list approve items
     */
    public ArrayList<ArrayList<String>> getApprovalItem() {
        return ApprovalItem;
    }

    /**
     * return list of list approve users
     */
    public ArrayList<ArrayList<String>> getApprovalUser() {
        return ApprovalUser;
    }
}
