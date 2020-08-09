package Trade;

import javax.swing.*;
import java.awt.*;

public interface TradeGUIPlan {
    void setFrame(int Width, int Height, String name);


    void run();

    JFrame getFrame();

    //String getInput();

}
