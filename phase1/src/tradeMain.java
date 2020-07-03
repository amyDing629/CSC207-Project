import java.io.IOException;
import java.time.LocalDateTime;

public class tradeMain {
    public static void main (String[] args) throws IOException {
        TradeManager tm = new TradeManager();
        Trade trade2 = new OnewayTrade(4,5, new Item("pear", "amy"),
                30, LocalDateTime.now());
        tm.addTradeToFile(trade2);
        System.out.println(tm.getTradeList());
    }
}
