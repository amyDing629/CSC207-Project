package Trade.Adaptor;

import javax.swing.*;

public interface TradeGUIPlan {

    void setFrame(int Width, int Height, String name);

    void run();

    JFrame getFrame();

    //String getInput();

}
