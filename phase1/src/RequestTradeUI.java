import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RequestTradeUI{
    ClientUser currUser;
    Item item;
    TradeController trc;
    Inventory iv = new Inventory();
    UserManager um = new UserManager();

    public RequestTradeUI(ClientUser currUser, Item item) throws IOException {
        trc = new TradeController(currUser, item);

    }

    public void run() throws AccountFrozenException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        trc.checkFrozen();
        while (true) {
            System.out.println("menu: \n 1.one way(temporary)\n 2.one way(permanent)" +
                    "\n 3.two way(temporary)\n 4.two way(permanent)");
            try {
                String line = br.readLine();
                if (!line.equals("1") && !line.equals("2")
                        && !line.equals("3") && !line.equals("4")) {
                    throw new IOException("Wrong input, please type again.");
                } else {
                    if (trc.createTrade(line)){
                        System.out.println("your trade has been created, please wait for the target user to reply");
                    }else{
                        String item2 = getSecondItem();
                        trc.createTrade(line, iv.getItem(item2));
                        System.out.println("your trade has been created, please wait for the target user to reply");
                    }
                    break;
                }
            } catch (IOException e) {
                System.out.println("Error! Please type again!");
            }
        }

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



















