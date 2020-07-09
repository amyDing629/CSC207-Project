package Trade;

import Inventory.Inventory;
import Inventory.Item;
import User.User;
import User.UserManager;

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
    User currUser;
    /**
     * the item selected that is used to request trade
     */
    Item item;
    /**
     * owner of the selected item
     */
    User tarUser;
    TradeController trc;
    Inventory iv = new Inventory();
    UserManager um = new UserManager();

    /**
     * [constructor]
     * @param currUser current user
     * @param item item selected by the current user
     * @throws IOException tarUser is not found
     */
    public RequestTradeUI(User currUser, Item item) throws IOException {
        trc = new TradeController(currUser);
        this.currUser = currUser;
        tarUser = trc.getTarUser(item);
        this.item = item;
    }

    /**
     * run request trade system
     * create trade, add the trade to user's trade history
     * @throws IOException input is not correct
     * (trade history is not working currently)
     */
    public void run() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        trc.checkInput();
        while (true) {
            System.out.println("menu: \n 1.one way(temporary)\n 2.one way(permanent)" +
                    "\n 3.two way(temporary)\n 4.two way(permanent)");
            try {
                String line = br.readLine();
                if (!line.equals("1") && !line.equals("2")
                        && !line.equals("3") && !line.equals("4")) {
                    throw new IOException("Wrong input, please type again.");
                } else {
                    if (!trc.createTrade(line, item)) {
                        String item2 = getSecondItem();
                        trc.createTrade(line, item, iv.getItem(item2));
                    }

                    System.out.println("your trade has been created, please wait for the target user to reply");
                    break;
                }
            } catch (IOException e) {
                System.out.println("Error! Please type again!");
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
            System.out.println(currUser.getWishLend());
            System.out.println("choose the item you want to lend");
            try {
                String line2 = br.readLine();
                if (currUser.getWishLend().contains(line2)){
                    return line2;
                }else{
                    throw new IOException("wrong input, you should type again!");
                }
            } catch (IOException e) {
                System.out.println("your input is invalid");
            }
        }
    }


}





















