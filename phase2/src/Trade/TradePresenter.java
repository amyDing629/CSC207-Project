package Trade;

import Inventory.Item;
import User.ClientUser;

import java.util.ArrayList;
import java.util.List;

/**
 * [presenter]
 * present the information of trade
 */
public class TradePresenter {
    private BorderGUIWithThreeTextArea bta;


    TradePresenter(BorderGUIWithThreeTextArea bta){
        this.bta = bta;
    }


    /**
     * present trade information (might delete some information)
     */
//    String presentTradeUIInfo(){
//        String result = "===========================" + "\n" +
//        currUser.getUsername() + "\n" +
//        "tradeId: " + trade.getId() + "\n" +
//        "tradeUsers: " + trade.getUsers() + "\n" +
//        "tradeStatus: " + trade.getStatus() + "\n" +
//        "tradeItem: " + trade.getItemList() + "\n" +
//        "first meeting: " + trade.getMeeting();
//        if (trade.getMeeting()!= null){
//            result = result + "\n" +
//            "first meeting status: " + trade.getMeeting().getStatus() + "\n" +
//            "id to confirm: " + trade.getMeeting().getConfirmedStatusFull() + "\n" +
//            "id to agree: " + trade.getMeeting().getAgreedStatusFull() + "\n" +
//            "id to edition: " + trade.getMeeting().getIdToEditor();
//        }
//        result = result + "second meeting: " + trade.getSecondMeeting();
//        if (trade.getSecondMeeting()!=null){
//            result = result + "\n"+ "second meeting status: "+trade.getSecondMeeting().getStatus();
//        }
//        result = result + "============================";
//        return result;
//    }

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
        bta.setMsgText("wrong input");
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



    void printSuggestedItemList(ArrayList<String> sItemList){
        System.out.println("Suggested items: " + sItemList);

    }

    void presentTradeInfo(ClientUser currUser, Item item, List<String> secondList, ArrayList<String> suggestList){
        bta.setCurrText("Current User: " + currUser.getUsername() + "\n" + "Item to request the trade: " + item.getName()
                + "\n" + secondList
                + "\n" + "Suggest item to lend if make a two way trade: " + suggestList);
    }

    void inTradeError(){
        bta.setMsgText("The item is already in the trade");
    }

    void currAccountFrozen(){
        bta.setMsgText("your account is frozen");
    }

    void tarAccountFrozen(){
        bta.setMsgText("target user's account is Frozen");
    }

    void tarUserNotFound(){
        bta.setMsgText("target user is not found");
    }

    void selfItem(){
        bta.setMsgText("you can not make trade with yourself");
    }

    void createSuccess(String dur){
        bta.setMsgText("the trade" + dur + "has been created, please wait for another user to confirm");
    }

    void updateInputArea(){
        bta.setInputStr("type item name here");
    }

    public void closeFrame(){
        bta.getFrame().setVisible(false);
    }




}
