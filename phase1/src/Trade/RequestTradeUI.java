package Trade;
import User.UserManager;
import Inventory.Inventory;
import Inventory.Item;
import User.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * user request a trade through an item with the owner of the item.
 * user needs to input an item from his wishLend list to request a two way trade.
 */
public class RequestTradeUI{
    /**
     * current user that use this system
     */
    private final User currUser;
    /**
     * the item selected that is used to request trade
     */
    private final Item item;
    /**
     * owner of the selected item
     */
    private final TradeController trc;
    private final TradePresenter tp;
    private final Inventory iv;

    /**
     * [constructor]
     * @param currUser current user
     * @param item item selected by the current user
     * @throws IOException tarUser is not found
     */
    public RequestTradeUI(User currUser, Item item, TradeManager tm, UserManager um, Inventory iv) throws IOException {
        trc = new TradeController(currUser, tm, um, iv);
        this.currUser = currUser;
        this.item = item;
        tp = new TradePresenter(currUser);
        trc.getTarUser(item);
        this.iv = iv;
    }

    /**
     * run request trade system
     * create trade, add the trade to user's trade history
     * @throws IOException input is not correct
     * (trade history is not working currently)
     */
    public void run() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        trc.checkInput(item);
        while (true) {
            tp.requestTradeMenu();
            try {
                String line = br.readLine();
                if (!line.equals("1") && !line.equals("2")
                        && !line.equals("3") && !line.equals("4") && !line.equals("0")) {
                    tp.wrongInput();
                } else {
                    if (line.equals("0")){
                        break;
                    }
                    if (!trc.createTrade(line, item)) {
                        String result = getSecondItem();
                        if (result.equals("exit")){
                            break;
                        }
                        Item it = iv.getItem(result);
                        trc.createTrade(line, item, it);
                    }
                    tp.requestTrade();
                    break;
                }
            } catch (IOException e) {
                tp.wrongInput();
            }
        }

    }

    /**
     * ask user for second item of two way trade
     * @return the name of the second item
     */
    private String getSecondItem(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            tp.selectSecondItem();
            try {
                String line2 = br.readLine();
                if (line2.equals("0")){
                    return "exit";
                }
                if (currUser.getWishLend().contains(line2)){
                    Item it = iv.getItem(line2);
                    if (it.getIsInTrade()){
                        System.out.println("the trade is already in the trade, please choose again");
                    }else{
                        return it.getName();
                    }
                }else{
                    tp.wrongInput();
                    throw new IOException();
                }
            } catch (IOException e) {
                tp.wrongInput();
            }
        }
    }


}





















