package User;
import Inventory.Inventory;
import Main.GateWay;
import Trade.Trade;
import Trade.TradeManager;

import java.io.*;
import java.util.*;

/**
 * [UseCase class]
 * The renew and modification of users
 */
public class UserManager {
    GateWay gw;
    ArrayList<User> userList;

    public UserManager(GateWay gw){
        this.gw = gw;
        userList = gw.getUsers();
    }


    public ArrayList<User> getUserList(){
        return userList;
    }
    /**
     * @param name the name of the user that the manager wants to get
     * find the user by the user name
     */
    public User getUser(String name) throws IOException {
        FileEditor f = new FileEditor(gw);
        //ArrayList<User> userList = splitUser(readFile());
        for(User u : gw.getUsers()){
            if(u.getUsername().equals(name))
                return u;
        }
        return null;
    }

    /**
     * @param userId the ID of the user that the manager wants to get
     * find the user by the user ID
     */
    public User getUser(UUID userId) throws IOException {
        try{
            FileEditor f = new FileEditor(gw);
            if(f.readFile().size() == 0){return null;}
            //ArrayList<User> userList = splitUser(readFile());
            for(User u : gw.getUsers()){
                if(u.getId().equals(userId))
                    return u;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public AdministrativeUser getAdmin(User user){
        return new AdministrativeUser(user.getUsername(), user.getPassword(),
                true, new TradeManager(gw), new UserManager(gw));
    }

    /**
     * @param id the id of the user that the manager wants to get
     * find the trade list of the user by the user id
     */
    /*
    public List<Trade> findTrade(UUID id){
        try{
            FileEditor f = new FileEditor();
            if(f.readFile().size() == 0){return null;}
            for(User u : gm.users){
                if(u.getId().equals(id))
                    return u.getAllTrade();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

     */

    /**
     * @param name the name of the user that the manager check
     * @param password the password of the user that the manager check
     * Check if the name matches with the password
     */
    public boolean verifyUser(String name, String password) throws IOException {
        //ArrayList<User> userList = splitUser(readFile());
        for(User u : gw.getUsers()){
            if(u.getUsername().equals(name) && u.getPassword().equals(password)) {
                return true;}
        }
        return false;
    }

    /**
     * return the list of most frequent three traders that the user trades with
     * if the user trades with less than three traders, return all the traders the user trades with
     */
    public List<String> getFrequentUser(TradeManager tm, User user) throws IOException {
        try {
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
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean checkFrozen(User user) throws IOException {
        TradeManager a = new TradeManager(gw);
        if(a.getTradeNumber(user) > user.getWeekTransactionLimit()){
            return true;
        }
        else if(a.getIncomplete(user).size() > user.getIncompleteTransactionLimit()){
            return true;
        }
        else return user.getLend().size() - user.getBorrowed().size() < user.getDiff();
    }
}

