import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class OnewayTrade extends Trade {
    private final Integer lenderId;
    private final Integer borrowerId;
    private final Item item;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final UserManager um = new UserManager();
    private final Inventory iv = new Inventory();

    /**
     *
     * @param lender Trader who wants to lend item to others
     * @param borrower Trader who wants to borrow item from others
     * @param item the two item traders what to trade
     * @param duration whether the trade is temporary or permanent
     */
    public OnewayTrade(Integer borrower, Integer lender, Item item, int duration, LocalDateTime time){
        super(duration, time);
        borrowerId = borrower;
        lenderId = lender;
        this.item = item;
    }


    /**
     * getter for lender of the trade
     * @return lender
     */
    public int getLenderId() {
        return lenderId;
    }

    /**
     * getter for borrow of the trade
     * @return borrower
     */
    public int getBorrower() {
        return borrowerId;
    }

    /**
     * getter for item of the trade
     * @return item
     */
    public Item getItem() {
        return item;
    }

    public ArrayList<Integer> getUsers(){
        ArrayList<Integer> users = new ArrayList<Integer>();
        users.add(borrowerId);
        users.add(lenderId);
        return users;
    }

    public String toString(){
        return "Trade" + getId() + ": Trader " + borrowerId +  " makes a one way trade with trader " + lenderId +
                " to borrow " + item + " at " + getCreateTime().format(formatter);
    }

    public ArrayList<Item> getItemList(){
        ArrayList<Item> rst = new ArrayList<Item>();
        rst.add(item);
        return rst;
    }

    @Override
    public void makeTrade() throws IOException {
        ClientUser bor = (ClientUser)um.getUser(borrowerId);
        ClientUser lend = (ClientUser)um.getUser(lenderId);
        bor.getWishBorrow().remove(item.getName());
        lend.getWishLend().remove(item.getName());
        iv.getLendingList().remove(item);
    }


}

