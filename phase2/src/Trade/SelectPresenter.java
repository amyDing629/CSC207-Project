package Trade;

import Inventory.Item;

import java.util.List;

public class SelectPresenter {

    BorderGUIWithThreeTextArea bta;

    public SelectPresenter(BorderGUIWithThreeTextArea bta){
        this.bta = bta;
    }

    public void closeFrame(){
        bta.getFrame().setVisible(false);
    }

    public void updateFrame(List<String> il){
        String result = "";
        for (String it: il){
            result = result + it + "\n";
        }
        bta.setListText(result);
    }

    public void resetInputArea(){
        bta.setInput("input", "item name");
    }

    public void wrongInput(){
        bta.setMsgText("wrong input");
    }

    public void selectItemInfo(String itemInfo){
        bta.setCurrText(itemInfo);
    }

    public void updateSuccess(){
        bta.setMsgText("The item info has been updated");
    }

    public void notItemSelected(){
        bta.setMsgText("no item is selected");
    }
}
