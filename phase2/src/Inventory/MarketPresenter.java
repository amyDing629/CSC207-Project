package Inventory;

import Trade.BorderGUIWithThreeTextArea;

public class MarketPresenter {
    BorderGUIWithThreeTextArea bta;

    public MarketPresenter(BorderGUIWithThreeTextArea bta){
        this.bta = bta;
    }

    void resetInputArea(){
        bta.setInput("input","item name");
    }

    void updateListM(String availableList){
        bta.setListText(availableList);
    }

    void closeFrame(){
        bta.getFrame().setVisible(false);
    }

    void resetCurr(){
        bta.setCurrText("not item selected");
    }

    void wrongInput(){
        bta.setMsgText("wrong input");
    }

    void updateCurr(String itemInfo){

        bta.setCurrText(itemInfo);
    }
}
