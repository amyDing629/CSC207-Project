package Trade;

import Inventory.Inventory;
import Inventory.Item;
import User.Entity.ClientUser;
import User.UseCase.UserManager;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

//public class DataAccessTest {
//    public static void main(String[] args) throws IOException {
//        ClientUser daniel = new ClientUser("daniel", "123", false);
//        ClientUser amy = new ClientUser("amy", "123", false);
//        Item apple = new Item("apple", "amy");
//        Item pear = new Item("pear", "daniel");
//        amy.addWishBorrow("pear");
//        daniel.addWishes("pear");
//        daniel.addWishBorrow("apple");
//        Inventory iv = new Inventory();
//        UserManager um = new UserManager();
//        TradeManager tm = new TradeManager();
//        iv.addItem(apple);
//        iv.addItem(pear);
//        um.addUser(daniel);
//        um.addUser(amy);
//        Trade tr = new OnewayTrade(amy.getId(), daniel.getId(), pear, 30, LocalDateTime.now());
//        Trade tr2 = new OnewayTrade(daniel.getId(), amy.getId(), apple, 30, LocalDateTime.now());
//
//        tm.getTradeList().add(tr);
//        tm.getTradeList().add(tr2);
//        TradeDataAccess tda = new TradeDataAccess(tm);
//        tda.serialize();
//        ArrayList tlist = tda.deSerialize();
//        for (Object o: tlist){
//            System.out.println(o);
//        }
//        tda.updateFile();
//    }
//}
