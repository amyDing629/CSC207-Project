package Main;

import Inventory.Item;
import Trade.Trade;
import User.User;
import com.sun.deploy.net.MessageHeader;
import java.util.ArrayList;

public class GateWay {
    private final ArrayList<User> users;
    private final ArrayList<Trade> trades;
    private final ArrayList<Item> inventory;


    public GateWay(){
        users = new ArrayList<User>();
        trades = new ArrayList<Trade>();
        inventory = new ArrayList<>();
    }

    public ArrayList<User> getUsers(){
        return users;
    }

    public ArrayList<Trade> getTrades(){
        return trades;
    }

    public ArrayList<Item> getInv(){
        return inventory;
    }
}
