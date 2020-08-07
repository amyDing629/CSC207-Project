package User.PointSystem;

import Trade.TradeManager;
import User.ClientUser;
import User.UserManager;

import java.util.Map;
import java.util.HashMap;
import java.util.UUID;

// TODO: May delete
public class PointSysMain {

    public static void main(String[] args) {
        ClientUser a1 = new ClientUser("admin", "123", true);
        ClientUser u1 = new ClientUser("daniel", "1234", false);
        ClientUser u2 = new ClientUser("amy", "12345", false);

        UserManager um = new UserManager();
        um.addUser(a1);
        um.addUser(u1);
        um.addUser(u2);
        TradeManager tm = new TradeManager();

        PointManager pm = new PointManager(um, tm);
        pm.setPointList();

        pm.addPoints("admin", 7);
        pm.addPoints("daniel", 3);
        pm.addPoints("amy", 2);
        Map<UUID, Integer> unsorted = pm.getPointList();
        System.out.println(unsorted);

        Map<UUID, Integer>  most = pm.getMostFreq();
        System.out.println(most);
    }
}
