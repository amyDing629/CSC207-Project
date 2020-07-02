import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

public class RequestTradePresenter {
    TradeManager tradeManager;
    UserManager userManager;
    Inventory inventory;
    ClientUser currUser;
    Item item;

    public RequestTradePresenter(String currUserName, String itemName) throws IOException {
        TradeManager tradeManager = new TradeManager();
        UserManager userManager = new UserManager();
        Inventory inventory = new Inventory();
        ClientUser currUser = (ClientUser) userManager.getUser(currUserName);
        Item item= inventory.getItem(itemName);

    }

    public void run() throws AccountFrozenException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        if (currUser.getIsFrozen()) {
            throw new AccountFrozenException("your account is frozen!");
        }
        ClientUser tarUser = (ClientUser) userManager.getUser(item.getOwnerName());
        if (tarUser.getIsFrozen()) {
            throw new AccountFrozenException("the account of the item owner is frozen.");
        }


        while (true) {
            System.out.println("menu: \n 1.one way(temporary)\n 2.one way(permanent)" +
                    "\n 3.two way(temporary)'\n 4.'two way(permanent)");
            try {
                String line = br.readLine();
                if (!line.equals("one way(temporary)") && !line.equals("one way(permanent)")
                        && !line.equals("two way(temporary)") && !line.equals("two way(permanent)")) {
                    throw new IOException("Wrong input, please type again.");
                } else {
                    Trade newTrade = createTrade(tarUser, line);
                    System.out.println("your trade has been created, please wait for the target user to reply");
                    break;
                }
            } catch (IOException e) {
                System.out.println("Error! Please type again!");
            }
        }

    }

    private Trade createTrade(ClientUser tarUser,String line) throws IOException {
        Trade trade;
        LocalDateTime time = LocalDateTime.now();
        if (line.equals("one way(temporary)")){
            trade = tradeManager.createOnewayTrade(currUser.getId(), tarUser.getId(), item, 30, time);
        }
        else if (line.equals("one way(permanent)")){
            trade = tradeManager.createOnewayTrade(currUser.getId(), tarUser.getId(), item, -1, time);
        }
        else if (line.equals("two way(temporary)")){
            Item item2 = inventory.getItem(getSecondItem());
            trade = tradeManager.createTwowayTrade(currUser.getId(), tarUser.getId(), item, item2, 30, time);
        }else{
            Item item2 = inventory.getItem(getSecondItem());
            trade = tradeManager.createTwowayTrade(currUser.getId(), tarUser.getId(), item, item2, -1, time);
        }
        return trade;

    }

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



















