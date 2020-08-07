package Trade;

import User.ClientUser;
import User.UserManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TradeHistoryGUI {
    ClientUser currUser;
    TradeHistoryController thc;
    Frame tf;

    public TradeHistoryGUI(ClientUser currUser, TradeHistoryController tc, Frame tf){
        this.currUser = currUser;
        this.thc =tc;
        this.tf = tf;
    }

    public void run(){
        JFrame frame = new JFrame("Trade History Session");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 400);

        JPanel panel = new JPanel();
        JLabel tradeList = new JLabel("Top Three Trade");
        JTextArea trade = new JTextArea();
        trade.setText(thc.getTradeHistory());
        JScrollPane jsp= new JScrollPane(trade);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(tradeList);
        panel.add(jsp);

        JPanel panelR = new JPanel();
        JLabel freUser = new JLabel("Most Frequent Trader");
        JTextArea user = new JTextArea();
        user.setText(thc.getFreUser());
        JScrollPane jspU = new JScrollPane(user);
        panelR.setLayout(new BoxLayout(panelR, BoxLayout.Y_AXIS));
        panelR.add(freUser);
        panelR.add(jspU);

        JButton update = new JButton("update");
        JPanel upPanel = new JPanel();
        JButton back = new JButton("return");
        upPanel.add(update);
        upPanel.add(back);
        upPanel.setLayout(new BoxLayout(upPanel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(400,400));
        frame.getContentPane().add(BorderLayout.WEST, panel);
        frame.getContentPane().add(BorderLayout.CENTER, panelR);
        frame.getContentPane().add(BorderLayout.EAST, upPanel);

        frame.setVisible(true);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                tf.setVisible(true);
            }
        });

        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trade.setText(thc.getTradeHistory());
            }
        });



    }
}
