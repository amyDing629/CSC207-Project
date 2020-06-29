import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RequestTradePresenter {
    TradeManager tradeManager;
    UserManager userManager;
    Inventory inventory;

    public RequestTradePresenter() {
        TradeManager tradeManager = new TradeManager();
        UserManager userManager = new UserManager();
        Inventory inventory = new Inventory();

    }

    public void run(String currUserName, String itemName) throws AccountFrozenException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ClientUser currUser = (ClientUser) userManager.getUser(currUserName);
        if (currUser.getIsFrozen()) {
            throw new AccountFrozenException("your account is frozen!");
        }
        Item item = inventory.getItem(itemName);
        ClientUser tarUser = (ClientUser) userManager.getUser(item.getOwnerName());
        if (tarUser.getIsFrozen()) {
            throw new AccountFrozenException("the account of the item owner is frozen.");
        }


        while (true) {
            System.out.println("'one way(temporary)' or 'one way(permanent)' or " +
                    "'two way(temporary)' or 'two way(permanent)");
            try {
                String line = br.readLine();
                if (!line.equals("one way(temporary)") && !line.equals("one way(permanent)")
                        && !line.equals("two way(temporary)") && !line.equals("two way(permanent)")) {
                    throw new IOException("Wrong input, please type again.");
                } else {
                    Trade newTrade = createTrade(currUser, tarUser, item, line);
                    //tarUser.getNotification().add("Trade" + newTrade.getId()+" : " + newTrade + "/ accept or not");
                    //user needs to have a trade request list
                    System.out.println("your trade has been created, please wait for the target user to reply");
                    break;
                }
            } catch (IOException e) {
                System.out.println("Error! Please type again!");
            }
        }

    }

    private Trade createTrade(ClientUser currUser, ClientUser tarUser, Item item, String line){
        Trade trade;
        if (line.equals("one way(temporary)")){
            trade = tradeManager.createOnewayTrade(currUser, tarUser, item, 30);
        }
        else if (line.equals("one way(permanent)")){
            trade = tradeManager.createOnewayTrade(currUser, tarUser, item, -1);
        }
        else if (line.equals("two way(temporary)")){
            Item item2 = inventory.getItem(getSecondItem(currUser));
            trade = tradeManager.createTwowayTrade(currUser, tarUser, item, item2, 30);
        }else{
            Item item2 = inventory.getItem(getSecondItem(currUser));
            trade = tradeManager.createTwowayTrade(currUser, tarUser, item, item2, -1);
        }
        return trade;

    }

    private String getSecondItem(ClientUser currUser){
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



















