import com.sun.java.util.jar.pack.Instruction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class tradeUI {
    TradeManager tradeManager;
    UserManager userManager;
    Inventory inventory;

    public tradeUI(){
        TradeManager tradeManager = new TradeManager();
        UserManager userManager = new UserManager();
        Inventory inventory = new Inventory();
    }

    public void createTrade(int UserId, String itemName) throws accountFrozenException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ClientUser currUser = (ClientUser) userManager.getUser(UserId);
        if (currUser.getIsFrozen()) {
            throw new accountFrozenException("your account is frozen!");
        }
        Item item = inventory.getItem(itemName);
        ClientUser tarUser = (ClientUser) userManager.getUser(item.getOwner());
        if (tarUser.getIsFrozen()) {
            throw new accountFrozenException("the owner of the item's owner's account is frozen.");
        }


        while (true) {
            System.out.println("'one way(temporary)' or 'one way(permanent)' or 'two way(temporary)' or 'two way(permanent)");
            try {
                String line = br.readLine();
                if (!line.equals("one way(temporary)") && !line.equals("one way(permanent)")
                        && !line.equals("two way(temporary)") && !line.equals("two way(permanent)")) {
                    throw new IOException("Wrong input, please type again.");
                } else {
                    Trade trade;
                    switch (line) {
                        case "one way(temporary)":
                            trade = new OnewayTrade(currUser, tarUser, item, 30);
                    }
                }
                } catch (IOException e) {
                System.out.println("Error! Please type again.");
                    }

        }
    }













        System.out.println("'temporary' or 'permanent'");
        while (true){
            try {
                String line2 = br.readLine();
                if (line2 != "temporary" && line2 != "permanent") {
                    throw new IOException("Please type 'temporary' or 'permancent'");
                }else {
                    if(line2 == "temporary"){
                        dur = -1;
                    }else{
                        dur = 30;
                    }
                }
            } catch (IOException e) {
                System.out.println("Error! Please type again");
            }

        }

        if (type.equals("one way")){
            tradeManager.createOnewayTrade(currUser, tarUser, item, dur);
        }else{

        }
        }
}



}