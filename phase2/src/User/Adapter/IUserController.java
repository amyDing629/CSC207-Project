package User.Adapter;

import User.Entity.ClientUser;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface IUserController {

    String getPassword(String username);

    void setPassword(String name, String password);

    UUID nameToUUID(String name);

    boolean getIsFrozen(UUID userID);

    int getTradeNumber(String username);

    String UUIDToName(UUID userID);

    int getWeekTransactionLimit(String a);

    int readDiff(String username);

    int getDiff(String a);

    int getIncompleteTransactionLimit(String a);

    int getIncompleteTransaction(UUID userId);

    boolean checkUser(String name);

    boolean getIsAdmin(String username);

    ClientUser getUser(String username);

    List<ClientUser> getUserList();

    ArrayList<ArrayList<String>> getActions(String username);
}
