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
        User amy = new User("amy","1234", false);
        User daniel = new User("daniel","345", false);
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
        Trade trade = tm.getTrade(UUID.fromString("9251abe4f-1ea2-4ef9-a5a5-6f3931e7b375"));
        //System.out.println(tm.getTradeList().get(0).getId() == trade.getId());
        //System.out.println(trade);
        tm.updateTradeHistory(amy.getId(),daniel.getId(),trade);//not working
        //System.out.println(amy.getTradeHistory());
        amy.getTradeHistory().add(trade.getId());
        System.out.println(amy.getTradeHistory());
        TradeUI tr = new TradeUI(amy, trade.getId());
        tr.run();
        System.out.println(trade.getMeeting());
        TradeUI tr2 = new TradeUI(daniel,trade.getId());
        tr2.run();
        tr.run();
        /*
        ArrayList<Trade> lst = tm.getTradeList();
        System.out.println(lst);
        lst.get(0).setStatus("incomplete");
        tm.updateFile();

         */




    }
}
