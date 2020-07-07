package Trade;

import Inventory.Inventory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class tradeMain {
    public static void main (String[] args) throws IOException, AccountFrozenException {

        TradeManager tm = new TradeManager();
        UserManager um = new UserManager();
        Inventory iv = new Inventory();
        /*
        user.User amy = new user.User("amy","1234", false);
        user.User daniel = new user.User("daniel","345", false);
        um.addUser(amy);
        um.addUser(daniel);
        Inventory.Item peach = new Inventory.Item("peach", "daniel");
        Inventory.Item apple = new Inventory.Item("apple", "amy");
        iv.addItem(peach);
        iv.addItem(apple);
        amy.getWishLend().add("apple");
        amy.getWishBorrow().add("peach");
        trade.RequestTradeUI rtp = new trade.RequestTradeUI(amy, peach);
        rtp.run();
        */
        ArrayList<User> lst = um.splitUser(um.readFile());
        User amy = lst.get(0);
        User daniel = lst.get(1);
        Trade trade = tm.getTrade(UUID.fromString("251abe4f-1ea2-4ef9-a5a5-6f3931e7b375"));
        tm.updateTradeHistory(amy.getId(),daniel.getId(),trade);//not working
        ArrayList<UUID> userList = trade.getUsers();
        //System.out.println(tm.getTradeList().get(0).getId() == trade.getId());
        //System.out.println(trade);

        //System.out.println(amy.getTradeHistory());
        int num = 0;
        while (!trade.status.equals("complete")){
            if (num == 1){
                TradeUI tr2 = new TradeUI(daniel, trade.getId());
                tr2.run();
                num = 0;
            }else{
                TradeUI tr = new TradeUI(amy,trade.getId());
                tr.run();
                num = 1;
            }

        }
        //amy.getTradeHistory().add(trade.getId());
        //System.out.println(amy.getTradeHistory());

        /*
        ArrayList<trade.Trade> lst = tm.getTradeList();
        System.out.println(lst);
        lst.get(0).setStatus("incomplete");
        tm.updateFile();

         */




    }
}
