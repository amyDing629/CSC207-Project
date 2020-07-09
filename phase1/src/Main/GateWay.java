package Main;

import Inventory.Item;
import Trade.Trade;
import User.User;
import com.sun.deploy.net.MessageHeader;

import java.util.ArrayList;

public class GateWay {
    public static ArrayList<User> users = new ArrayList<User>();
    public static ArrayList<Trade> trades = new ArrayList<Trade>();
    public static ArrayList<Item> inventory = new ArrayList<>();
}
