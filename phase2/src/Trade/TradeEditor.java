package Trade;

import java.util.UUID;

public class TradeEditor {

    public void setStatus(Trade trade, TradeStatus status){
        trade.setStatus(status);
    }

    public TradeStatus getTradeStatus(Trade trade){
        return trade.getStatus();
    }

    public void setCreator(Trade trade, UUID creatorID){
        trade.setCreator(creatorID);
    }

    public UUID getId(Trade trade){
        return trade.getId();
    }
}
