import java.io.IOException;
import java.time.LocalDateTime;

public class TradeController {
    private final ClientUser currUser;
    private ClientUser tarUser;
    private final TradeManager tm = new TradeManager();
    private UserManager um = new UserManager();


    public TradeController(ClientUser currUser) throws IOException {
        this.currUser = currUser;

    }


    public ClientUser getTarUser(Item item) throws IOException {
        tarUser = (ClientUser) um.getUser(item.getOwnerName());
        return tarUser;
    }


    public void checkInput() throws IOException {
        if (currUser.getIsFrozen()) {
            throw new IOException("your account is frozen!");
        }
        if (tarUser == null){
            System.out.println("tarUser not found");
        };
        if (tarUser.getIsFrozen()) {
            throw new IOException("the account of the item owner is frozen!");
        }
        if (tarUser == currUser){
            throw new IOException("you cannot make trade with yourself");
        }
    }

    public boolean createTrade(String line, Item item) throws IOException {
        LocalDateTime time = LocalDateTime.now();
        Trade trade;
        switch (line) {
            case "1":
                tm.createOnewayTrade(currUser.getId(), tarUser.getId(), item, 30, time);
                return true;
            case "2":
                tm.createOnewayTrade(currUser.getId(), tarUser.getId(), item, -1, time);
                return true;
            default: {
                return false;
            }
        }
    }
    public void createTrade(String line, Item item1, Item item2) throws IOException {
        LocalDateTime time = LocalDateTime.now();
        Trade trade;
        if (line.equals("3")){
            tm.createTwowayTrade(currUser.getId(), tarUser.getId(), item1, item2, 30, time);
        }else{
            tm.createTwowayTrade(currUser.getId(), tarUser.getId(), item1, item2, -1, time);
        }
    }

    public String checkTradeMeeting(Trade currTrade){
        if (currTrade.getStatus().equals("unconfirmed")) {
            return "confirm trade";
        }else if (currTrade.getStatus().equals("complete")) {
            return "complete";
        } else if (currTrade.getMeeting() == null || currTrade.getMeeting().getStatus().equals("incomplete")){
            return "first meeting";
        }else if (currTrade.getDuration()==Trade.temp){
            if (currTrade.getSecondMeeting().getStatus().equals("incomplete")){
                return "second meeting";
            }else{
                return "complete";
            }
        }else{
            return "complete";
        }
    }

    public void confirmTrade(Trade currTrade){
        currTrade.setStatus("incomplete");
    }

    public void completeTrade(Trade currTrade) throws IOException {
        currTrade.setStatus("complete");
        currTrade.makeTrade();
    }


}

