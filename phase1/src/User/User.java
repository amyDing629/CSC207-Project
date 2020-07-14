package User;

import Inventory.Inventory;
import Trade.Trade;
import Trade.TradeManager;
import Trade.TradeStatus;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.UUID;
/**
 * [Entity class]
 * The basic functionality of users
 */
public class User {
    /**
     * the id of user, evey user has unique id.
     */
    protected UUID id;
    /**
     * the name and password of the user account
     */
    protected String password;
    protected String username;
    /**
     * all the messages the user send
     */
    private List<String> notification;
    /**
     * boolean attribute shows that if the user is administrative user or not
     */
    private final boolean isAdmin;
    /**
     * the list of names of items that the user want to lend and borrow
     */
    private List<String> wishLend;
    private List<String> wishBorrow;
    /**
     * boolean attribute shows that if the user have the ability to borrow items or not
     */
    private boolean isBorrow;
    /**
     * boolean attribute shows that if the user account has been frozen or not
     */
    private boolean isFrozen;
    /**
     * List of IDs of all the trade that the user has
     */
    protected List<UUID> tradeHistory;
    /**
     * the maximum number of transactions the user can do within seven days
     */
    private int weekTransactionLimit;
    /**
     * the maximum number of incomplete transactions the user can have.
     */
    private int incompleteTransactionLimit;
    /**
     * the list of names of items that the user has lent and borrowed
     */
    private List<String> lend;
    private List<String> borrowed;
    /**
     * the difference amount between the amount of
     */
    private int diff = 2;


    /**
     * @param username the username of the user account
     * @param password the password of the user account
     * @param isAdmin the boolean shows if the user is administrative user or not
     * ID is random generated and is unique
     */
    public User(String username, String password, boolean isAdmin){
        this.username = username;
        this.password = password;
        //this.id = id;
        this.weekTransactionLimit=5;
        this.incompleteTransactionLimit=2;
        this.notification=new ArrayList<>();
        this.wishLend= new ArrayList<>();
        this.wishBorrow= new ArrayList<>();
        this.tradeHistory=new ArrayList<>();
        this.isAdmin = isAdmin;
        id = UUID.randomUUID();
    }


    public int getDiff() {
        return diff;
    }

    /**
     * return the password of the user
     */
    public String getPassword() {
        return password;
    }
    /**
     * return the name of the user
     */
    public String getUsername(){return username;}
    /**
     * return the id of the user
     */
    public UUID getId() {
        return id;
    }
    /**
     * return whether the user account is being frozen
     */
    public boolean getIsFrozen(){
        return isFrozen;
    }
    /**
     * return all the notifications the user has
     */
    public List<String> getNotification(){return notification;}

    /**
     * @param incompleteTransaction the maximum number of incomplete transactions user can have
     * set the input integer as the maximum incomplete transaction number of a user
     */
    public void setIncompleteTransaction(int incompleteTransaction) {
        this.incompleteTransactionLimit = incompleteTransaction;
    }

    /**
     * @param a id we want to set for the user
     * set the input id for the user
     */
    public void setId(UUID a){this.id = a;}
    /**
     * @param weekTransaction  the week transaction limit we want to set for the user
     * set the week transaction limit for the user
     */
    public void setWeekTransactionLimit(int weekTransaction){
        this.weekTransactionLimit = weekTransaction;
    }

    /**
     * @param hi the name of the item
     * add the name of item that the user wants to lend into list
     */
    public void addWishes(String hi){
        this.wishLend.add(hi);
    }

    /**
     * @param name the name of the item
     * add the name of item that the user wants to borrow into list
     */
    public void addWishBorrow(String name){
        this.wishBorrow.add(name);
    }
    /**
     * @param hi the name of the item
     * the name of item that the user does not have to borrow
     */
    public void removeBWishes(String hi){
        this.wishBorrow.remove(hi);
    }

    /**
     * @param hi the name of the item
     * the name of item that the user does not have to lend
     */
    public void removeLWishes(String hi){
        this.wishLend.remove(hi);
    }

    /**
     * @param wishLend the list of items the user wants to lend
     * set the list of items that the user wants to lend as wishlend list
     */
    public void setWishLend(List<String> wishLend){
        this.wishLend = wishLend;
    }

    /**
     * @param notification all the notifications the user has
     * set all the notifications as the list
     */
    public void setNotification(List<String> notification){
        this.notification = notification;
    }

    /**
     * @param diff the number of the difference between number of borrowed items and lend items
     * set the integer as the number of the difference between number of borrowed items and lend items
     */
    public void setDiff(int diff) {
        this.diff = diff;
    }

    /**
     * @param tradeHistory all the trades' ids that the user has
     * set the user's trade history as the list of ids
     */
    public void setTradeHistory(List<UUID> tradeHistory) {
        this.tradeHistory = tradeHistory;
    }

    /**
     * get all the trades' ids the user has
     */
    public List<UUID> getTradeHistory(){
        return tradeHistory;
    }

    /**
     * @param id the trade id the user wants to add
     * add the trade id into user's trade history
     */
    public void addTradeHistory(UUID id){tradeHistory.add(id);}

    /**
     * return whether the user is administrative user or not
     */
    public boolean getIsAdmin(){return isAdmin;}

    /**
     * return the list of items that the user wants to lend
     */
    public List<String> getWishLend() {
        return wishLend;
    }

    /**
     * return the list of items that the user wants to borrow
     */
    public List<String> getWishBorrow() {
        return wishBorrow;
    }

    /**
     * @param aTrue the status of the account the user want to set
     * set the status of the account of user to be aTrue
     */
    public void setFrozen(boolean aTrue){
        isFrozen = aTrue;
    }

    /**
     * @param borrow the status of the account the user want to set
     * set the status of the account of user to be borrow
     */
    public void setBorrow(boolean borrow){

        isBorrow = borrow;
    }


    /**
     * return the list of all trades that the user has
     */
    /*
    public List<Trade> getAllTrade() throws IOException {
        TradeManager a = new TradeManager();
        ArrayList<Trade> b = new ArrayList<>();
        for(UUID i: tradeHistory){
            b.add(a.getTrade(i));
        }
        return b;
    }

     */

    /**
     * return the list of all unconfirmed trades that the user has
     */
    /*
    public List<Trade> getUnconfirmed() throws IOException {
        List<Trade> trade=new ArrayList<>();
        for(Trade t: getAllTrade()){
            if(t.getStatus().equals(TradeStatus.unconfirmed)){
                trade.add(t);
            }
        }
        return trade;
    }

     */

    /**
     * return the list of all incomplete trades that the user has
     */
    /*
    public List<Trade> getIncomplete() throws IOException {
        List<Trade> trade=new ArrayList<>();
        for(Trade t: getAllTrade()){
            if(t.getStatus().equals(TradeStatus.incomplete)){
                trade.add(t);
            }
        }
        return trade;
    }

     */

    /**
     * return the list of most recent three trades that the user has
     * if the user has less than three trades, return all the trades the user has
     */
    /*
    public List<Trade> getTradeHistoryTop() throws IOException {
        List<Trade> trade=new ArrayList<>();
        TradeManager a = new TradeManager();
        int y = 0;
        for (int i = getAllTrade().size(); i>0;i-- ) {
            if (((!(getAllTrade().get(i).getStatus().equals(TradeStatus.unconfirmed))) &&
                    (!(getAllTrade().get(i).getStatus().equals(TradeStatus.cancelled))))&&y!=3) {
                trade.add(getAllTrade().get(i));
                y++;
            }
        }
        return trade;
    }

     */


    /**
     * @param no the new message that the user send
     * renew the notification list
     */
    public void addNotification(String no){
        notification.add(no);
    }

    public int getWeekTransactionLimit() {
        return weekTransactionLimit;
    }

    public int getIncompleteTransactionLimit() {
        return incompleteTransactionLimit;
    }

    public void setWishBorrow(ArrayList<String> wishBorrow) {
        this.wishBorrow =wishBorrow;
    }

    public void setUsername(String name) {
        username=name;
    }

    public void setPassword(String password) {
        this.password=password;
    }

    /**
     * return the number of incomplete transactions that the user has
     */
    /*
    public int getIncompleteTransaction() throws IOException {
        TradeManager a = new TradeManager();
        int number=0;
        for (UUID i : tradeHistory) {
            if (a.getTrade(i).getStatus().equals(TradeStatus.incomplete)) {
                number++;
            }
        }
        return number;
    }

     */

    /**
     * return the number of transactions of the user has in seven days from the most recent trade
     */
    /*
    public int getTradeNumber() throws IOException {
        TradeManager a = new TradeManager();
        if(tradeHistory.size() == 0){return 0;}
        Trade s = a.getTrade(tradeHistory.get(tradeHistory.size() - 1));
        LocalDateTime x  = s.getCreateTime();
        LocalDateTime y = x.minusDays(7);
        int number = 0;
        for (UUID i : tradeHistory){
            if(a.getTrade(i).getCreateTime().isAfter(y) && a.getTrade(i).getCreateTime().isBefore(x)){
                number ++;
            }
        }
        return number;
    }

     */

    /**
     * return the status that whether the user can borrow or not
     */
    public boolean getIsBorrow() {
        return isBorrow;
    }

    /**
     * return the lst of names of items that the user lends
     */
    public List<String> getLend() {
        return lend;
    }

    /**
     * return the lst of names of items that the user borrows
     */
    public List<String> getBorrowed() {
        return borrowed;
    }

    /**
     * @param description the description string the user wants to give to the item
     * @param name the name of the item
     * the user set the item with the new description
     */
    /*
    public void setDescription(String description, String name){
        Inventory b = new Inventory();
        b.getItem(name).setDescription(description);
    }

     */

    /**
     * return the list of most frequent three traders that the user trades with
     * if the user trades with less than three traders, return all the traders the user trades with
     */
    /*
    public List<User> getFrequentUser() throws IOException {
        try {
            UserManager u = new UserManager();
            List<Trade> a = getAllTrade();
            HashMap<UUID, Integer> b = new HashMap<>();
            for (Trade c : a) {
                for (UUID d : c.getUsers()) {
                    if (!(d.equals(id))) {
                        if (b.containsKey(d)) {
                            b.replace(d, b.get(d) + 1);
                        } else {
                            b.put(d, 1);
                        }
                    }
                }
            }
            if(b.size() == 0){return null;}
            int e = 0;
            ArrayList<User> g = new ArrayList<>();
            int maxValueInMap = (Collections.max(b.values()));
            for (Map.Entry<UUID, Integer> entry : b.entrySet()) {
                if (entry.getValue() == maxValueInMap && e != 3) {
                    g.add(u.getUser(entry.getKey()));
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

     */
}