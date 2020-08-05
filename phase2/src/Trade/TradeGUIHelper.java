package Trade;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TradeGUIHelper {

    JButton getExitButton(JFrame currFrame, JFrame lastFrame){
        JButton back = new JButton("return to trade menu");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currFrame.setVisible(false);
                lastFrame.setVisible(true);
            }
        });
        return back;
    }



}
