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

    public void makeTrade(int UserId, String itemName) throws accountFrozenException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ClientUser currUser = (ClientUser)userManager.getUser(UserId);
        if (currUser.getIsFrozen()){
            throw new accountFrozenException("your account is frozen!");
        }
        Item item = inventory.getItem(itemName);
        ClientUser tarUser = (ClientUser)userManager.getUser(item.getOwner());
        if (tarUser.getIsFrozen()){
            throw new accountFrozenException("the owner of the item's owner's account is frozen");
        }

        System.out.println("'one way' or 'two way'");
        try{
            String line = br.readLine();
        } catch (IOException e) {
            System.out.println("");
        }

    }
}