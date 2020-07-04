import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
public class TwowayTrade extends Trade {
    private final Integer trader1Id;
    private final Integer trader2Id;
    private final Item item1to2;
    private final Item item2to1;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private UserManager um = new UserManager();
    private Inventory iv = new Inventory();
    /**
     *
     * @param trader1Id Trader who participates in the trade
     * @param trader2Id Trader who participates in the trade
     * @param item1 the item that trader1 give to trader2
     * @param item2 the item that trader2 give to trader1
     * @param duration whether the trade is temporary or permanent
     */
    public TwowayTrade(Integer trader1Id, Integer trader2Id, Item item1, Item item2, int duration, LocalDateTime time){
        super(duration, time);
        this.trader1Id = trader1Id;
        this.trader2Id = trader2Id;
        item1to2 = item1;
        item2to1 = item2;
    }


    public int getTrader1Id(){
        return trader1Id;
    }
    public int getTrader2Id(){
        return trader2Id;
    }
    /**
     * getter for item that trader1 give to trader2
     * @return item1to2
     */
    public Item getItem1to2() {
        return item1to2;
    }

    /**
     * getter for item that trader2 give to trader1
     * @return item2to1
     */
    public Item getItem2to1(){
        return item2to1;
    }

    public ArrayList<Integer> getUsers(){
        ArrayList<Integer> users = new ArrayList<Integer>();
        users.add(trader1Id);
        users.add(trader2Id);
        return users;
    }

    public String toString(){
        return "Trade" + getId() + ": trader1 " + trader1Id +" makes two way trade with trader" + trader2Id +
                " for item " + item1to2 + "and" + item2to1 +  " at " + getCreateTime().format(formatter);
    }

    @Override
    public ArrayList<Item> getItemList() {
        ArrayList<Item> rst = new ArrayList<Item>();
        rst.add(item1to2);
        rst.add(item2to1);
        return rst;
    }

    @Override
    public void makeTrade() throws IOException {
        ClientUser u1 = (ClientUser)um.getUser(trader1Id);
        ClientUser u2 = (ClientUser)um.getUser(trader2Id);
        u1.getWishBorrow().remove(item2to1.getName());
        u1.getWishLend().remove(item1to2.getName());
        u2.getWishBorrow().remove(item1to2.getName());
        u2.getWishLend().remove(item2to1.getName());
        iv.getLendingList().remove(item1to2);
        iv.getLendingList().remove(item2to1);
    }


}