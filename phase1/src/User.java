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
    private int diff;

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


    public String getPassword() {
        return password;
    }
    public String getUsername(){return username;}

    public UUID getId() {
        return id;
    }

    public boolean getIsFrozen(){
        return isFrozen;
    }

    public List<String> getNotification(){return notification;}

    public void setIncompleteTransaction(int incompleteTransaction) {
        this.incompleteTransactionLimit = incompleteTransaction;
    }

    public void setId(UUID a){this.id = a;}

    public void setWeekTransactionLimit(int weekTransaction){
        this.weekTransactionLimit = weekTransaction;
    }

    /**
     * the list of names of items that the user want to lend and borrow
     */
    public void addWishes(String hi){
        this.wishLend.add(hi);
    }

    public void removeBWishes(String hi){
        this.wishBorrow.remove(hi);
    }

    public void removeLWishes(String hi){
        this.wishLend.remove(hi);
    }

    public void setWishLend(List<String> wishLend){
        this.wishLend = wishLend;
    }

    public void setNotification(List<String> notification){
        this.notification = notification;
    }

    public int getDiff() {
        return diff;
    }

    public void setDiff(int diff) {
        this.diff = diff;
    }

    public void setTradeHistory(List<UUID> tradeHistory) {
        this.tradeHistory = tradeHistory;
    }

    public List<UUID> getTradeHistory(){
        return tradeHistory;
    }

    public boolean getIsAdmin(){return isAdmin;}

    public List<String> getWishLend() {
        return wishLend;
    }
    public List<String> getWishBorrow() {
        return wishBorrow;
    }
    public void setFrozen(boolean aTrue){
        isFrozen = aTrue;
    }

    public void setBorrow(boolean borrow){

        isBorrow = borrow;
    }

    public List<Trade> getAllTrade(){
        TradeManager a = new TradeManager();
        ArrayList<Trade> b = new ArrayList<>();
        for(UUID i: tradeHistory){
            b.add(a.getTrade(i));
        }
        return b;
    }

    public List<Trade> getUnconfirmed(){
        List<Trade> trade=new ArrayList<>();
        for(Trade t: getAllTrade()){
            if(t.status.equals("unconfirmed")){
                trade.add(t);
            }
        }
        return trade;
    }

    public List<Trade> getIncomplete(){
        List<Trade> trade=new ArrayList<>();
        for(Trade t: getAllTrade()){
            if(t.status.equals("incomplete")){
                trade.add(t);
            }
        }
        return trade;
    }

    public List<Trade> getTradeHistoryTop() {
        List<Trade> trade=new ArrayList<>();
        TradeManager a = new TradeManager();
        int y = 0;
        for (int i = getAllTrade().size(); i>0;i-- ) {
            if (((!(getAllTrade().get(i).status.equals("unconfirmed"))) && (!(getAllTrade().get(i).status.equals("cancelled"))))&&y!=3) {
                trade.add(getAllTrade().get(i));
                y++;
            }
        }
        return trade;
    }

    public void decideTrade(boolean a, Trade b){
        if(a){b.setStatus("incomplete");}
        else {b.setStatus("cancelled");}
    }

    public void addNotification(String no){
        notification.add(no);
    }

    public int getWeekTransactionLimit() {
        return weekTransactionLimit;
    }

    public int getIncompleteTransactionLimit() {
        return incompleteTransactionLimit;
    }

    public void setWishBorrow(ArrayList<String> lineList3) {
        wishBorrow = lineList3;
    }

    public void setUsername(String input1) {
        username=input1;
    }

    public void setPassword(String input2) {
        password=input2;
    }

    public int getIncompleteTransaction(){
        TradeManager a = new TradeManager();
        int number=0;
        for (UUID i : tradeHistory) {
            if (a.getTrade(i).status.equals("incomplete")) {
                number++;
            }
        }
        return number;
    }

    public int getTradeNumber(){
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

    public boolean getIsBorrow() {
        return isBorrow;
    }

    public List<String> getLend() {
        return lend;
    }

    public List<String> getBorrowed() {
        return borrowed;
    }

    public void setDescription(String a, String name){
        Inventory b = new Inventory();
        b.getItem(name).setDescription(a);
    }


}