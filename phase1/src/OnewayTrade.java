import java.util.ArrayList;

public class OnewayTrade extends Trade {
    private final int lenderId;
    private final int borrowerId;
    private final Item item;

    /**
     *
     * @param lender Trader who wants to lend item to others
     * @param borrower Trader who wants to borrow item from others
     * @param item the two item traders what to trade
     * @param duration whether the trade is temporary or permanent
     */
    public OnewayTrade(int borrower, int lender, Item item, int duration){
        super(duration);
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
        return "Trader " + borrowerId +  " makes a one way trade with trader " + lenderId + " to borrow " + item;
    }

}

