package Trade;

import javax.swing.*;
import java.util.HashMap;

public class BorderGUIWithThreeTextArea extends BorderGUINoTextArea
        implements TradeGUIPlan, BorderLayoutGUI, InputAndPresent {
    private JTextArea curr;
    private JTextArea list;
    private JTextArea msg;
    private HashMap<String, JTextArea> inputs = new HashMap<String, JTextArea>();

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


    @Override
    public String getInput(String key) {
        return inputs.get(key).getText();
    }

    @Override
    public void setInput(String key, String content) {
        inputs.get(key).setText(content);
    }

    @Override
    public void addInput(String key, JTextArea input) {
        inputs.put(key, input);
        ;
    }
}
