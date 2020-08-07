package Trade;

import javax.swing.*;
import java.awt.*;

interface TradeGUIPlan {
    void setFrame(int Width, int Height, String name);


    void run();

    JFrame getFrame();

    //String getInput();

}
