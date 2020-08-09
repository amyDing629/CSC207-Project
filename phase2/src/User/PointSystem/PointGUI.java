package User.PointSystem;

import Trade.Trade;
import User.Entity.ClientUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PointGUI {
    Trade currTrade;
    ClientUser currUser;
    AwardActivities aa;
    Frame tf;

    public PointGUI(ClientUser currUser, Frame tf){
        this.currUser = currUser;
        aa = new AwardActivities();
        this.tf = tf;
    }

    public void run(){
        JFrame frame = new JFrame("Point Session");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 400);

        JPanel panelW = new JPanel();
        JPanel panelC = new JPanel();
        JPanel panelN = new JPanel();
        JPanel panelE = new JPanel();
        JPanel panelS = new JPanel();
        frame.getContentPane().add(BorderLayout.EAST, panelE);
        frame.getContentPane().add(BorderLayout.WEST, panelW);
        frame.getContentPane().add(BorderLayout.CENTER, panelC);
        frame.getContentPane().add(BorderLayout.SOUTH, panelS);
        frame.getContentPane().add(BorderLayout.NORTH, panelN);

        panelW.setPreferredSize(new Dimension(380,370));

        JLabel msg = new JLabel("message:", SwingConstants.LEFT);
        JTextArea msgArea = new JTextArea();
        panelN.add(msg);
        panelN.add(msgArea);

        JLabel tradeList = new JLabel("Available Trades");
        JTextArea tradeArea = new JTextArea();
        //tradeArea.setText(aa.printAvailable());
        JScrollPane jsp= new JScrollPane(tradeArea);
        panelW.setLayout(new BoxLayout(panelW, BoxLayout.Y_AXIS));
        panelW.add(tradeList);
        panelW.add(jsp);

        JLabel currTradeL = new JLabel("Trade selected");
        JTextArea currArea = new JTextArea();
        panelC.setLayout(new BoxLayout(panelC, BoxLayout.Y_AXIS));
        panelC.add(currTradeL);
        panelC.add(currArea);
        currArea.setText("no trade selected"); // Only one trade is selected each time?

        JLabel points = new JLabel(String.valueOf(currUser.getBonusPoints()));
        JButton eb = new JButton("Get Bonus");
        panelE.setLayout(new BoxLayout(panelE, BoxLayout.Y_AXIS));
        panelE.add(points);
        panelE.add(eb);

        JLabel input = new JLabel("input trade number");
        JTextArea inputArea = new JTextArea("trade number");
        JButton submit = new JButton("submit");
        JButton back = new JButton("return to login menu");
        JButton update = new JButton("update");
        panelS.add(input);
        panelS.add(inputArea);
        panelS.add(submit);
        panelS.add(back);
        panelS.add(update);


        frame.setVisible(true);

        eb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aa.getBonus(currUser, currTrade);
                points.setText(String.valueOf(currUser.getBonusPoints()));
            }
        });

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
                points.setText(String.valueOf(currUser.getBonusPoints()));
            }
        });

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tradeNum = inputArea.getText();
                inputArea.setText("trade number");
                if (!aa.checkInput(tradeNum)){
                    msg.setText("wrong input");
                }else{
                    currTrade = aa.getCurrTrade(tradeNum);
                    currArea.setText(currTrade.toString());
                    msgArea.setText("Current trade has been updated");
                }
            }
        });
    }

}


