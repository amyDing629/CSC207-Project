package Trade;

import User.ClientUser;

import java.util.ArrayList;

/**
 * [presenter]
 * present the information of trade
 */
public class TradePresenter {
    private final ClientUser currUser;
    private Trade trade;

    /**
     * [constructor]
     * @param currUser current user
     * @param trade current trade
     */
    TradePresenter(ClientUser currUser, Trade trade){
        this.currUser = currUser;
        this.trade = trade;
    }

    TradePresenter(ClientUser currUser){
        this.currUser = currUser;
    }

    /**
     * present trade information (might delete some information)
     */
    String presentTradeUIInfo(){
        String result = "===========================" + "\n" +
        currUser.getUsername() + "\n" +
        "tradeId: " + trade.getId() + "\n" +
        "tradeUsers: " + trade.getUsers() + "\n" +
        "tradeStatus: " + trade.getStatus() + "\n" +
        "tradeItem: " + trade.getItemList() + "\n" +
        "first meeting: " + trade.getMeeting();
        if (trade.getMeeting()!= null){
            result = result + "\n" +
            "first meeting status: " + trade.getMeeting().getStatus() + "\n" +
            "id to confirm: " + trade.getMeeting().getConfirmedStatusFull() + "\n" +
            "id to agree: " + trade.getMeeting().getAgreedStatusFull() + "\n" +
            "id to edition: " + trade.getMeeting().getIdToEditor();
        }
        result = result + "second meeting: " + trade.getSecondMeeting();
        if (trade.getSecondMeeting()!=null){
            result = result + "\n"+ "second meeting status: "+trade.getSecondMeeting().getStatus();
        }
        result = result + "============================";
        return result;
    }

    void enterTrade(){
        System.out.println("type 1 to exit, type anything to continue with current trade");
    }

    void exitTrade(){
        System.out.println("you have exited the trade UI");
    }

    void confirmTrade(){
        System.out.println("your trade has been confirmed");
    }

    void cancelTrade(){
        System.out.println("your trade has been cancelled");
    }

    void wrongInput(){
        System.out.println("wrong input, please type again");
    }

    void selectConfirm(){
        System.out.println("type 1 to confirm the trade, type 2 to disagree/cancel the trade");
    }

    void completeTrade(){
        System.out.println("your trade has been completed");
    }

    void enterFirstM(){
        System.out.println("you are entering the first meeting system");
    }

    void enterSecondM(){
        System.out.println("you are entering the second meeting system");
    }

    void requestTradeMenu(){
        System.out.println("menu: \n 1.one way(temporary)\n 2.one way(permanent)" +
                "\n 3.two way(temporary)\n 4.two way(permanent)\n 0.exit");
    }

    void requestTrade(){
        System.out.println("your trade has been created, please wait for the target user to reply");
    }


    String selectSecondItem(){
        return "Your wish lend list: " + currUser.getWishLend();
    }

    void printSuggestedItemList(ArrayList<String> sItemList){
        System.out.println("Suggested items: " + sItemList);

    }


}
