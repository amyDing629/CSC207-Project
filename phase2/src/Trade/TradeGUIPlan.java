package Trade;

import javax.swing.*;

interface TradeGUIPlan {
    void setSize(int Width, int Height);
    void setWest(JPanel panelW);
    void setEast(JPanel panelE);
    void setNorth(JPanel panelN);
    void setSouth(JPanel panelS);
    void setCenter(JPanel panelC);
    void setListText(String str);
    void setCurrText(String str);
    void setMsgText(String str);
}
