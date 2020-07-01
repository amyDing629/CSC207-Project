import java.io.IOException;

public class TradeProcessPresentor {
    TradeManager tradeManager;
    UserManager userManager = new UserManager();

    public TradeProcessPresentor(){
        TradeManager tradeManager = new TradeManager();
    }

    public void run(int tradeId) throws IOException {
        Trade trade = tradeManager.getTrade(tradeId);
        ClientUser user1 = (ClientUser) userManager.getUser(trade.getUsers().get(0));
        ClientUser user2 = (ClientUser) userManager.getUser(trade.getUsers().get(1));
        MeetingSystem mt = new MeetingSystem(user1,user2);
        mt.run();
    }

}
