public class TradePresenter {
    User currUser;
    Trade trade;


    public TradePresenter(User currUser, Trade trade){
        this.currUser = currUser;
        this.trade = trade;
    }

    public void presentTradeUIInfo(){
        System.out.println(currUser.getUsername());
        System.out.println("tradeId: " + trade.getId());
        System.out.println("tradeUsers: " + trade.getUsers());
        System.out.println("tradeStatus: " + trade.getStatus());
        System.out.println("tradeItem: " + trade.getItemList());
        System.out.println("first meeting: " + trade.getMeeting());
        System.out.println("second meeting: " + trade.getSecondMeeting());
    }
}
