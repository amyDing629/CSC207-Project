package Trade;

import Inventory.Inventory;
import Inventory.Item;
import User.ClientUser;
import User.UserManager;

import java.io.IOException;

public class RTradeGUI_test {
    public static void main(String[] args) throws IOException {
        ClientUser daniel = new ClientUser("daniel", "123", false);
        ClientUser amy = new ClientUser("amy", "123", false);
        Item apple = new Item("apple", "amy");
        Item pear = new Item("pear", "daniel");
        amy.addWishBorrow("pear");
        daniel.addWishes("pear");
        Inventory iv = new Inventory();
        iv.addItem(apple);
        iv.addItem(pear);
        UserManager um = new UserManager();
        TradeManager tm = new TradeManager();
        um.addUser(daniel);
        um.addUser(amy);
        RTradeGUI rtg = new RTradeGUI(daniel, apple, tm, um, iv );
        rtg.run();
        System.out.println(tm.getTradeList());
        System.out.println(tm.getUnconfirmed(amy));
    }
}
