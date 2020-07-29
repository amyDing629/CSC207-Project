package Trade;

import Inventory.Inventory;
import Inventory.Item;
import User.ClientUser;
import User.UserManager;

public class SelectController {
    ClientUser currUser;
    UserManager um;
    Inventory iv;
    Item currItem;

    public SelectController(ClientUser currUser, UserManager um, Inventory iv){
        this.currUser = currUser;
        this.um = um;
        this.iv = iv;
    }

    public String getWishList(){
        String result = "";
        for (String it: um.getWishBorrow(currUser)){
            result = result + it + "\n";
        }
        return result;
    }

    boolean checkInput(String str){
        return um.getWishBorrow(currUser).contains(str);
    }

    Item getItem(String str){
        return iv.getItem(str);
    }


    String getItemInfo(String str){
        Item item = iv.getItem(str);
        currItem = item;
        return "Item Info:\nitem name: " + item.getName() + "\n" +
                "item description: " + item.getDescription()
                + "\n" + "item owner: " + item.getOwnerName() ;

    }
}
