package Trade.Adaptor.RequestTrade;

import Trade.Adaptor.BorderGUI;

import java.util.List;

public class SelectPresenter {

    BorderGUI bta;

    public SelectPresenter(BorderGUI bta){
        this.bta = bta;
    }

    public void closeFrame(){
        bta.getFrame().setVisible(false);
    }

    public void updateFrame(List<String> il){
        StringBuilder result = new StringBuilder();
        for (String it: il){
            result.append(it).append("\n");
        }
        bta.setListText(result.toString());
    }

    public void resetInputArea(){
        bta.setInput("input", "Trade Number");
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
