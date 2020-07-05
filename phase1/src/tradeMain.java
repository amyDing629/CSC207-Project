import java.io.IOException;
import java.time.LocalDateTime;

public class tradeMain {
    public static void main (String[] args) throws IOException, AccountFrozenException {
        TradeManager tm = new TradeManager();
        UserManager um = new UserManager();
        Inventory iv = new Inventory();
        ClientUser amy = new ClientUser("amy","1234", false);
        ClientUser daniel = new ClientUser("daniel","345", false);
        um.addUser(amy);
        um.addUser(daniel);
        Item peach = new Item("peach", "daniel");
        Item apple = new Item("apple", "amy");
        iv.addItem(peach);
        iv.addItem(apple);
        amy.getWishLend().add("apple");
        amy.getWishBorrow().add("peach");
        RequestTradeUI rtp = new RequestTradeUI(amy, peach);
        rtp.run();
        System.out.println(tm.getTradeList());
        System.out.println(tm.getTradeList().get(0));
        TradeUI tr = new TradeUI(daniel,tm.getTradeList().get(0).getId());
        tr.run();
    }
}
