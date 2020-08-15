package User.Adapter;

import User.Entity.ClientUser;

import java.time.LocalDateTime;
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

    void setEnd(String username, LocalDateTime end);

    boolean getIsLeft(UUID userID);

    void addAction(String userName, String password, String password1);

    void removeAction(String username, String item_ticket, String s);

    void deleteLItem(String username, String lendWish);

    void deleteBItem(String username, String borrowWish);

    boolean checkActionExist(String text, ArrayList<String> strings);
}
