import MeetingSystem.MeetingSystem;

import java.io.IOException;

public class TradeUI {
    TradeManager tradeManager = new TradeManager();

    public TradeUI(){
        TradeManager tradeManager = new TradeManager();
    }

    public void run(int tradeId) throws IOException {
        Trade trade = tradeManager.getTrade(tradeId);
        MeetingSystem mt = new MeetingSystem(trade.getUsers());
        mt.run();
    }
}
