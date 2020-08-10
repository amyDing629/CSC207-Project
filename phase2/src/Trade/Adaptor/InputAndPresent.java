package Trade.Adaptor;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public interface InputAndPresent {
    HashMap<String, TextArea> inputs = new HashMap<String, TextArea>();

    String getInput(String key);
    void setInput(String key, String contents);
    void addInput(String key, JTextArea input);
    void initializeList(JTextArea ta);
    void initializeCurr(JTextArea ta);
    void initializeMsg(JTextArea ta);
    void setListText(String str);
    void setCurrText(String str);
    void setMsgText(String str);
    JFrame getFrame();
}
