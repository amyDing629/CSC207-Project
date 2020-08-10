package Trade.Adaptor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

    public ArrayList<Object> createMessagePanel(){
        JPanel panelN = new JPanel();
        JLabel msg = new JLabel("message:", SwingConstants.LEFT);
        JTextArea msgArea = new JTextArea();
        panelN.add(msg);
        panelN.add(msgArea);
        ArrayList<Object> rst = new ArrayList<Object>();
        rst.add(panelN);
        rst.add(msgArea);

        return rst;
    }



}
