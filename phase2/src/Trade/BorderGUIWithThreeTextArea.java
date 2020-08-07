package Trade;

import javax.swing.*;
import java.awt.*;

public class BorderGUIWithThreeTextArea extends BorderGUINoTextArea implements TradeGUIPlan, BorderLayoutGUI, ThreeTextArea {
    private JTextArea curr;
    private JTextArea list;
    private JTextArea msg;
    private JTextArea input;
    private String inputStr;

    public BorderGUIWithThreeTextArea(){
        super();
    }




    @Override
    public void setListText(String str) {
        list.setText(str);

    }

    @Override
    public void setCurrText(String str) {
        curr.setText(str);

    }

    @Override
    public void setMsgText(String str) {
        msg.setText(str);

    }

    @Override
    public void initializeInput(JTextArea input) {
        this.input = input;

    }


    @Override
    public void setInputStr(String str) {
        inputStr = str;

    }

    @Override
    public void resetInput() {
        input.setText(inputStr);

    }
    @Override
    public void initializeList(JTextArea ta) {
        list = ta;
    }

    @Override
    public void initializeCurr(JTextArea ta) {
        curr = ta;
    }

    @Override
    public void initializeMsg(JTextArea ta) {
        msg = ta;
    }


    public String getInput(){
        return input.getText();

    }


}
