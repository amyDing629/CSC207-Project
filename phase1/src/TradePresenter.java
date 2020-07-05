public class TradePresenter {
    User currUser;
    Trade trade;


    public TradePresenter(User currUser, Trade trade){
        this.currUser = currUser;
        this.trade = trade;
    }

    public void presentTradeInfo(){
        System.out.println(trade);
        System.out.println("first meeting: " + trade.getMeeting());
        System.out.println("second meeting: " + trade.getSecondMeeting());
    }
}
