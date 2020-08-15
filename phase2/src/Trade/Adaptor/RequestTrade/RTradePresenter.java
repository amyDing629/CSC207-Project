package Trade.Adaptor.RequestTrade;

import Inventory.Entity.Item;
import Trade.Adaptor.InputAndPresent;
import Trade.Adaptor.iPresent;
import User.Entity.ClientUser;

import java.util.ArrayList;
import java.util.List;

/**
 * [presenter]
 * present the information of trade
 */
public class RTradePresenter implements iRTradePresenter {
    private final iPresent bta;


    RTradePresenter(iPresent bta){
        this.bta = bta;
    }


    public void wrongInput(){
        bta.setMsgText("wrong input");
    }

    public void presentTradeInfo(ClientUser currUser, Item item, List<String> secondList, ArrayList<String> suggestList){
        bta.setCurrText("Current User: " + currUser.getUsername() + "\n" + "Item to request the trade: " + item.getName()
                + "\n" + "items in your lending list: "+ secondList
                + "\n" + "Suggest item to lend if make a two way trade: " + suggestList);
    }

    public void inTradeError(){
        bta.setMsgText("The item is already in the trade");
    }

    public void currAccountFrozen(){
        bta.setMsgText("your account is frozen");
    }

    public void tarAccountFrozen(){
        bta.setMsgText("target user's account is Frozen");
    }

    public void tarUserNotFound(){
        bta.setMsgText("target user is not found");
    }

    public void selfItem(){
        bta.setMsgText("you can not make trade with yourself");
    }

    public void createSuccess(String dur){
        bta.setMsgText("the trade" + dur + "has been created, please wait for another user to confirm");
    }

    public void updateInputArea(){
        bta.setInput("input","type item name here");
    }

    public void closeFrame(){
        bta.getFrame().setVisible(false);
    }




}
