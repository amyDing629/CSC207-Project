import java.io.IOException;
import java.time.LocalDateTime;

public class TradeController {
    private final ClientUser currUser;
    private final Item item;
    private final ClientUser tarUser;
    private final TradeManager tm = new TradeManager();


    public TradeController(ClientUser currUser, Item item) throws IOException {
        this.currUser = currUser;
        this.item = item;
        tarUser = (ClientUser) UserManager.getUser(item.getOwnerName());
    }


    public ClientUser getTarUser(){
        return tarUser;
    }


    public void checkFrozen() throws AccountFrozenException, IOException {
        if (currUser.getIsFrozen()) {
            throw new AccountFrozenException("your account is frozen!");
        }
        ClientUser tarUser = getTarUser();
        assert tarUser != null;
        if (tarUser.getIsFrozen()) {
            throw new AccountFrozenException("the account of the item owner is frozen!");
        }
    }

    public boolean createTrade(String line) throws IOException {
        LocalDateTime time = LocalDateTime.now();
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
    public void createTrade(String line, Item item2) throws IOException {
        LocalDateTime time = LocalDateTime.now();
        if (line.equals("3")){
            tm.createTwowayTrade(currUser.getId(), tarUser.getId(), item, item2, 30, time);
        }else{
            tm.createTwowayTrade(currUser.getId(), tarUser.getId(), item, item2, -1, time);
        }
    }

}

