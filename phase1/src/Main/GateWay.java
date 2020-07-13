package Main;

import Inventory.Item;
import Trade.Trade;
import User.User;
import com.sun.deploy.net.MessageHeader;
import java.util.ArrayList;

public class GateWay {
    public ArrayList<User> users;
    public ArrayList<Trade> trades;
    public ArrayList<Item> inventory;


    public GateWay(){
        users = new ArrayList<User>();
        trades = new ArrayList<Trade>();
        inventory = new ArrayList<>();
    }

    public ArrayList<User> getUsers(){
        return users;
    }
}
