package User;

import Trade.Trade;
import Trade.TradeManager;

import java.util.*;

/**
 * [UseCase class]
 * The renew and modification of users
 */
public class UserManager {

    /**
     * the list of users
     */
    ArrayList<ClientUser> userList;

    /**
     * [constructor]
     */
    public UserManager(){
        userList = new ArrayList<>();
    }

    /**
     * return list of users
     */
    public ArrayList<ClientUser> getUserList(){
        return userList;
    }

    /**
     * @param name the name of the user that the manager wants to get
     * find the user by the user name
     */
    public ClientUser getUser(String name) {
        //ArrayList<ClientUser> userList = splitUser(readFile());
        for(ClientUser u : userList){
            if(u.getUsername().equals(name))
                return u;
        }
        return null;
    }

    public void addUser(ClientUser user){
        userList.add(user);
    }

    /**
     * @param userId the ID of the user that the manager wants to get
     * find the user by the user ID
     */
    public ClientUser getUser(UUID userId) {
        //ArrayList<ClientUser> userList = splitUser(readFile());
        for(ClientUser u : userList){
            if(u.getId().equals(userId))
                return u;
        }
        return null;
    }

    /**
     * @param userId The ID of the administrative user.
     * get the administrative user by the user ID
     */
    public ClientUser getAdmin(UUID userId){
        for(ClientUser u : userList) {
            if(u.getId().equals(userId) && u.getIsAdmin()) {
                return u;
            }
        } return null;
    }


    /**
     * @param name the name of the user that the manager check
     * @param password the password of the user that the manager check
     * Check if the name matches with the password
     */
    public boolean verifyUser(String name, String password) {
        //ArrayList<ClientUser> userList = splitUser(readFile());
        for(ClientUser u : userList){
            if(u.getUsername().equals(name) && u.getPassword().equals(password)) {
                return true;}
        }
        return false;
    }

    /**
     * @param tm the object that edits the Item list of input gateway
     * @param user the input user
     * return the list of most frequent three traders that the user trades with
     * if the user trades with less than three traders, return all the traders the user trades with
     */
    public List<String> getFrequentUser(TradeManager tm, ClientUser user) {
        List<Trade> a = tm.getAllTrade(user);
        HashMap<UUID, Integer> b = new HashMap<>();
        for (Trade c : a) {
            for (UUID d : c.getUsers()) {
                if (!(d.equals(user.getId()))) {
                    if (b.containsKey(d)) {
                        b.replace(d, b.get(d) + 1);
                    } else {
                        b.put(d, 1);
                    }
                }
            }
        }
        if(b.size() == 0){
            List<String> EmptyList = new ArrayList<>(Collections.emptyList());
            EmptyList.add("no user");
            return EmptyList;}
        int e = 0;
        ArrayList<String> g = new ArrayList<>();
        int maxValueInMap = (Collections.max(b.values()));
        for (Map.Entry<UUID, Integer> entry : b.entrySet()) {
            if (entry.getValue() == maxValueInMap && e != 3) {
                g.add(getUser(entry.getKey()).getUsername());
                e++;
                b.remove(entry.getKey());
            }
        }
        return g;
    }
    //no use
    public List<UUID> getTradeHistory(ClientUser a){
        return a.getTradeHistory();
    }

    //FINISHED
    public boolean getIsAdmin(ClientUser a){return a.getIsAdmin();}
    //FINISHED
    public List<String> getWishLend(ClientUser a) {
        return a.getWishLend();
    }
    //FINISHED
    public List<String> getWishBorrow(ClientUser a) {
        return a.getWishBorrow();
    }
    //finished
    public int getWeekTransactionLimit(ClientUser a) {
        return a.getWeekTransactionLimit();
    }
    //finished
    public int getIncompleteTransactionLimit(ClientUser a) {
        return a.getIncompleteTransactionLimit();
    }
    //no use
    public boolean getIsBorrow(ClientUser a) {
        return a.getIsBorrow();
    }
    //finished
    public int getDiff(ClientUser a) {
        return a.getDiff();
    }
    //finished
    public String getPassword(ClientUser a) {
        return a.getPassword();
    }

    //finished
    public String getUsername(ClientUser a){return a.getUsername();}

    //finished
    public UUID getId(ClientUser a) {
        return a.getId();
    }

    //finished
    public boolean getIsFrozen(ClientUser a){
        return a.getIsFrozen();
    }
    //no use
    public List<String> getNotification(ClientUser a){return a.getNotification();}
    //finished
    public void set(ClientUser a, String password){a.setPassword(password);}
    //finished
    public void createClientUser(String name, String password, boolean isAdmin){
        userList.add(new ClientUser(name, password, isAdmin));
    }



}

