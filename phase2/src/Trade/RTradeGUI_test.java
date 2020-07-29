package Trade;

import Inventory.Inventory;
import Inventory.Item;
import User.ClientUser;
import User.UserManager;

import java.io.IOException;
import java.util.regex.Pattern;

public class RTradeGUI_test {
    public static void main(String[] args) throws IOException {
        ClientUser daniel = new ClientUser("daniel", "123", false);
        ClientUser amy = new ClientUser("amy", "123", false);
        Item apple = new Item("apple", "amy");
        Item pear = new Item("pear", "daniel");
        amy.addWishBorrow("pear");
        daniel.addWishes("pear");
        daniel.addWishBorrow("apple");
        Inventory iv = new Inventory();
        iv.addItem(apple);
        iv.addItem(pear);
        UserManager um = new UserManager();
        TradeManager tm = new TradeManager();
        um.addUser(daniel);
        um.addUser(amy);
        SelectItemToTradeGUI rtg = new SelectItemToTradeGUI(daniel, tm, um, iv );
        rtg.run();
        AcceptTradeGUI atg = new AcceptTradeGUI(amy, tm, um);
        atg.run();


    }
}
