package Trade.Adaptor;

import javax.swing.*;
import java.awt.*;

public class BorderGUINoTextArea implements TradeGUIPlan, BorderLayoutGUI {
    JFrame fr;

    public void setEast(JPanel e)
    {
        fr.getContentPane().add(BorderLayout.EAST, e);
    }

    public void setNorth(JPanel n)
    {
        fr.getContentPane().add(BorderLayout.NORTH, n);
    }

    public void setSouth(JPanel s)
    {
        fr.getContentPane().add(BorderLayout.SOUTH, s);
    }

    public void setWest(JPanel w) { fr.getContentPane().add(BorderLayout.WEST, w); }

    public void setCenter(JPanel c)
    {
        fr.getContentPane().add(BorderLayout.CENTER, c);
    }

    @Override
    public void setFrame(int width, int height, String name) {
        JFrame frame = new JFrame(name);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(width, height);
        fr = frame;
    }

    @Override
    public void run () {
        fr.setVisible(true);

    }

    @Override
    public JFrame getFrame() {
        return fr;
    }
}
