import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class tradeMain {
    public static void main (String[] args) throws IOException, AccountFrozenException {
        TradeManager tm = new TradeManager();
        UserManager um = new UserManager();
        Inventory iv = new Inventory();
        /*
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

         */
        ArrayList<User> lst = um.splitUser(um.readFile());
        User amy = lst.get(0);
        User daniel = lst.get(1);
        Trade trade = tm.getTrade(UUID.fromString("04439336-6386-40c3-baff-b7f4e88e2aef"));
        //System.out.println(tm.getTradeList().get(0).getId() == trade.getId());
        //System.out.println(trade);
        tm.updateTradeHistory(amy.getId(),daniel.getId(),trade);//not working
        //amy.getTradeHistory().add(trade.getId());
        System.out.println(amy.getTradeHistory());
        TradeUI tr = new TradeUI((ClientUser)amy,tm.getTradeList().get(0).getId());

        tr.run();
    }
}
