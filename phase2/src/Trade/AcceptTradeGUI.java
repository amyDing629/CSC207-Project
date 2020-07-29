package Trade;

import User.ClientUser;
import User.UserManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AcceptTradeGUI {
    Trade currTrade;
    ClientUser currUser;
    AcceptTradeController atc;

    public AcceptTradeGUI(ClientUser currUser, TradeManager tm, UserManager um){
        this.currUser = currUser;
        atc = new AcceptTradeController(currUser, tm, um);
    }

    public void run(){
        JFrame frame = new JFrame("Accept Trade Session");
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
        tradeArea.setText(atc.printUnconfirmed());
        JScrollPane jsp= new JScrollPane(tradeArea);
        panelW.setLayout(new BoxLayout(panelW, BoxLayout.Y_AXIS));
        panelW.add(tradeList);
        panelW.add(jsp);

        JLabel currTradeL = new JLabel("Trade selected");
        JTextArea currArea = new JTextArea();
        panelC.setLayout(new BoxLayout(panelC, BoxLayout.Y_AXIS));
        panelC.add(currTradeL);
        panelC.add(currArea);
        currArea.setText("no trade selected");

        JButton agree = new JButton("agree");
        JButton refuse = new JButton("refuse");
        panelE.setLayout(new BoxLayout(panelE, BoxLayout.Y_AXIS));
        panelE.add(agree);
        panelE.add(refuse);

        JLabel input = new JLabel("input trade number");
        JTextArea inputArea = new JTextArea("trade number");
        JButton submit = new JButton("submit");
        JButton back = new JButton("return to trade menu");
        JButton update = new JButton("update");
        panelS.add(input);
        panelS.add(inputArea);
        panelS.add(submit);
        panelS.add(back);
        panelS.add(update);


        frame.setVisible(true);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tradeNum = inputArea.getText();
                inputArea.setText("trade number");
                if (!atc.checkInput(tradeNum)){
                    msg.setText("wrong input");
                }else{
                    currTrade = atc.getCurrTrade(tradeNum);
                    currArea.setText(currTrade.toString());
                    msgArea.setText("Current trade has been updated");
                }
            }
        });

        agree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currTrade == null){
                    msgArea.setText("no trade selected");
                } else{
                    atc.agreeTrade();
                    msgArea.setText("you have agreed the trade");
                    tradeArea.setText(atc.printUnconfirmed());
                    currTrade = null;
                    currArea.setText("no trade selected");
                }
            }
        });

        refuse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currTrade == null){
                    msgArea.setText("no trade selected");
                }else{
                    atc.refuseTrade();
                    msgArea.setText("you have refused the trade");
                    tradeArea.setText(atc.printUnconfirmed());
                    currTrade = null;
                    currArea.setText("no trade selected");
                }
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });

        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tradeArea.setText(atc.printUnconfirmed());
            }
        });
    }
}
