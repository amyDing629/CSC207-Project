import java.io.IOException;
import java.time.LocalDateTime;

public class tradeMain {
    public static void main (String[] args) throws IOException, AccountFrozenException {
        TradeManager tm = new TradeManager();
        ClientUser amy = new ClientUser("amy","1234", false);
        ClientUser daniel = new ClientUser("daniel","345", false);
        UserManager um = new UserManager();
        um.addUser(amy);
        um.addUser(daniel);
        Item peach = new Item("peach", "daniel");
        Inventory iv = new Inventory();
        iv.addItem(peach);
        OnewayTrade owt = new OnewayTrade(amy.getId(), daniel.getId(), peach, 30, LocalDateTime.now());
        tm.getTradeList().add(owt);
        tm.updateFile();
        //System.out.println(tm.getTradeList());
        RequestTradeUI rtp = new RequestTradeUI(amy, peach);

        rtp.run();
    }
}
