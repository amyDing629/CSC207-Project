package Trade;

import javax.swing.*;

interface TradeGUIPlan {
    void setFrame(int Width, int Height, String name);
    void setWest(JPanel panelW);
    void setEast(JPanel panelE);
    void setNorth(JPanel panelN);
    void setSouth(JPanel panelS);
    void setCenter(JPanel panelC);
    void initializeList(JTextArea ta);
    void initializeCurr(JTextArea ta);
    void initializeMsg(JTextArea ta);
    void setListText(String str);
    void setCurrText(String str);
    void setMsgText(String str);
}
