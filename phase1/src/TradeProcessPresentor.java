import MeetingSystem.MeetingSystem;

import java.io.IOException;

public class TradeProcessPresentor {
    TradeManager tradeManager = new TradeManager();

    public TradeProcessPresentor(){
        TradeManager tradeManager = new TradeManager();
    }

    public void run(int tradeId) throws IOException {
        Trade trade = tradeManager.getTrade(tradeId);
        MeetingSystem mt = new MeetingSystem(trade.getUsers());
        mt.run();
    }

}
