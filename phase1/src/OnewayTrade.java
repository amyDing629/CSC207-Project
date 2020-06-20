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
     * @param status whether the trade is unconfirmed, confirmed or completed
     * @param duration whether the trade is temporary or permanent
     */
    public OnewayTrade(ClientUser lender, ClientUser borrower, ClientUser item, String status, String duration){
        super(status, duration);
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

    /**
     * remove item from borrower's wish to borrow list
     * remove item from lender's wish to lend list
     * if the trade is temporary trade, add the item to willReturn list, add the item to will getBack list.
     */
    @Override
    public void makeTrade() {

    }

    /**
     * remove item from borrower's willReturn list
     * remove item from lender's will getBack list
     */
    @Override
    public void returnObject() {

    }
}

