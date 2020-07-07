package Inventory;

import User.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [user.User Interface-Inventory]
 * show available items on the market.
 * select item.
 * show selected item information.
 * add item to user's wishBorrow list
 */
public class InventoryUI {
    Inventory inventory;
    User currUser;
    InventoryPresenter ip;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    Item currItem;
    InventoryController ic;

    /**
     * [constructor]
     * @param currUser the user that is using the system.
     * call inventory presenter and controller
     */
    public InventoryUI(User currUser){
        inventory = new Inventory();
        this.currUser = currUser;
        ip = new InventoryPresenter(currUser);
        ic = new InventoryController(currUser);
    }

    /**
     * run the system
     */
    public void run() {
        int exit = 0;
        while (exit !=1){
            while (true){
                ip.printAvailable();
                System.out.println("type '1' to exit, or select an item");
                try {
                    String line = br.readLine();
                    if (line.equals("1")) {
                        exit = 1;
                        break;
                    }else{
                        if (!ic.selectItem(line)) {
                            throw new IOException("your input is not found, please type again");
                        } else{
                            currItem = inventory.getItem(line);
                            itemAction();
                            break;
                        }
                    }
                } catch (IOException e) {
                    System.out.println("your input is not correct, please try again");
                }
            }
        }
    }

    /**
     * let user to select whether to add the item in the wishBorrow list.
     */
    private void itemAction(){
        while (true){
            ip.printItemInfo(currItem);
            System.out.println("menu:\n type '1' to add to wish borrow list and return back to the inventory" +
                    "\n type '2' to return back to inventory directly");
            try{
                String line2 = br.readLine();
                if (line2.equals("1")){
                    if (ic.isOwnItem(currItem)){
                        System.out.println("you can not add your own item to wish borrow list");
                    }else{
                        ic.moveToWishList(currItem);
                        System.out.println("the item has been moved to the wish list");
                        break;//move back to see inventory
                    }
                }else if (line2.equals("2")){
                    break;
                }else{
                    System.out.println("wrong input, please type again");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}





