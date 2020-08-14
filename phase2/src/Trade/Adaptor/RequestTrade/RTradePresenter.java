package Trade.Adaptor.RequestTrade;

import Inventory.Entity.Item;
import Trade.Adaptor.InputAndPresent;
import User.Entity.ClientUser;

import java.util.ArrayList;
import java.util.List;

/**
 * [presenter]
 * present the information of trade
 */
public class RTradePresenter {
    private final InputAndPresent bta;


    RTradePresenter(InputAndPresent bta){
        this.bta = bta;
    }


    void wrongInput(){
        bta.setMsgText("wrong input");
    }

    void presentTradeInfo(ClientUser currUser, Item item, List<String> secondList, ArrayList<String> suggestList){
        bta.setCurrText("Current User: " + currUser.getUsername() + "\n" + "Item to request the trade: " + item.getName()
                + "\n" + "items in your lending list: "+ secondList
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
        bta.setInput("input","type item name here");
    }

    public void closeFrame(){
        bta.getFrame().setVisible(false);
    }




}
