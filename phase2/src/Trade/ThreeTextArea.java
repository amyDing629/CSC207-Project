package Trade;

import javax.swing.*;

public interface ThreeTextArea {
    void initializeList(JTextArea ta);
    void initializeCurr(JTextArea ta);
    void initializeMsg(JTextArea ta);
    void setListText(String str);
    void setCurrText(String str);
    void setMsgText(String str);
    void initializeInput(JTextArea input);
    void resetInput();
    void setInputStr(String str);
}
