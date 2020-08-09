package Trade;

import User.ClientUser;
import User.UserManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CompleteTradeGUIBuilder implements BorderGUIBuilder {
    CTradeController ctc;
    JFrame tf;
    BorderGUIWithThreeTextArea tg;

    public CompleteTradeGUIBuilder(ClientUser currUser, TradeManager tm, UserManager um, JFrame tf) {
        tg = new BorderGUIWithThreeTextArea();
        ctc = new CTradeController(currUser, tm, um, tg);
        this.tf = tf;
    }

    @Override
    public void buildFrame(){
        tg.setFrame(800, 400, "Complete Trade Session");
    }

    @Override
    public void buildPanelN() {
        ArrayList<Object> rst = new TradeGUIHelper().createMessagePanel();
        tg.initializeMsg((JTextArea)rst.get(1));
        tg.setNorth((JPanel)rst.get(0));

    }

    @Override
    public void buildPanelE() {
        JPanel panelE = new JPanel();
        JButton action = new JButton("action");
        panelE.setLayout(new BoxLayout(panelE, BoxLayout.Y_AXIS));
        panelE.add(action);
        action.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctc.action();
                ctc.closeFrame();
            }
        });
        tg.setEast(panelE);

    }

    @Override
    public void buildPanelW() {
        JPanel panelW =  new JPanel();
        JLabel tradeList = new JLabel("Available Trades");
        JTextArea tradeArea = new JTextArea();
        tg.initializeList(tradeArea);
        ctc.updateBut();
        JScrollPane jsp= new JScrollPane(tradeArea);
        panelW.setLayout(new BoxLayout(panelW, BoxLayout.Y_AXIS));
        panelW.add(tradeList);
        panelW.add(jsp);
        panelW.setPreferredSize(new Dimension(380,370));
        tg.setWest(panelW);
    }

    @Override
    public void buildPanelS() {
        JPanel panelS =  new JPanel();
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
        tg.setInputStr("trade number");
        tg.initializeInput(inputArea);
        tg.setSouth(panelS);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tradeNum = tg.getInput();
                ctc.submitBut(tradeNum);
            }
        });
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctc.backBut(tf);
            }
        });
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctc.updateBut();
            }
        });
    }

    @Override
    public void buildPanelC() {
        JPanel panelC =  new JPanel();
        JLabel currTradeL = new JLabel("Trade selected");
        JTextArea currArea = new JTextArea();
        panelC.setLayout(new BoxLayout(panelC, BoxLayout.Y_AXIS));
        panelC.add(currTradeL);
        panelC.add(currArea);
        tg.setCenter(panelC);
        tg.initializeCurr(currArea);
        ctc.noTradeSelected();

    }

    public BorderGUIWithThreeTextArea getTradeGUI(){
        return tg;
    }
}


