import java.util.ArrayList;

public class OnewayTrade extends Trade {
    private final ClientUser lender;
    private final ClientUser borrower;
    private final Item item;

    /**
     *
     * @param lender Trader who wants to lend item to others
     * @param borrower Trader who wants to borrow item from others
     * @param item the two item traders what to trade
     * @param duration whether the trade is temporary or permanent
     */
    public OnewayTrade(ClientUser borrower, ClientUser lender, Item item, int duration){
        super(duration);
        this.lender = lender;
        this.borrower = borrower;
        this.item = item;
    }


    /**
     * getter for lender of the trade
     * @return lender
     */
    public ClientUser getLender() {
        return lender;
    }

    /**
     * getter for borrow of the trade
     * @return borrower
     */
    public ClientUser getBorrower() {
        return borrower;
    }

    /**
     * getter for item of the trade
     * @return item
     */
    public Item getItem() {
        return item;
    }

    public ArrayList<ClientUser> getUsers(){
        ArrayList<ClientUser> users = new ArrayList<ClientUser>();
        users.add(borrower);
        users.add(lender);
        return users;
    }

}

