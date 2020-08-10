package User.PointSystem;

import Trade.BorderGUIBuilder;
import Trade.BorderGUINoTextArea;
import User.Entity.ClientUser;
import Trade.BorderGUIWithThreeTextArea;
import Trade.TradeGUIHelper;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PointGUIBuilder implements BorderGUIBuilder {
    String currUser;
    AwardActivities aa;
    BorderGUIWithThreeTextArea tg;

    public PointGUIBuilder(String currUser, JFrame tf){
        this.currUser = currUser;
        tg = new BorderGUIWithThreeTextArea();
        aa = new AwardActivities(currUser, tf, tg);
    }

    @Override
    public void buildFrame() {
        tg.setFrame(800, 400, "point session");
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
        JLabel p = new JLabel("points");
        JTextArea points = new JTextArea();
        points.setPreferredSize(new Dimension(50, 50));
        JButton eb = new JButton("Get Bonus");
        panelE.setLayout(new BoxLayout(panelE, BoxLayout.Y_AXIS));
        panelE.add(p);
        panelE.add(points);
        panelE.add(eb);
        eb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aa.ebBut();
            }
        });
        tg.addInput("points", points);
        aa.updatePoint();
        tg.setEast(panelE);

    }

    @Override
    public void buildPanelW() {
        JPanel panelW =  new JPanel();
        JLabel tradeList = new JLabel("Available Trades");
        JTextArea tradeArea = new JTextArea();
        JScrollPane jsp= new JScrollPane(tradeArea);
        panelW.setLayout(new BoxLayout(panelW, BoxLayout.Y_AXIS));
        panelW.add(tradeList);
        panelW.add(jsp);
        panelW.setPreferredSize(new Dimension(380,370));
        tg.setWest(panelW);
        tg.initializeList(tradeArea);
        aa.updateList();

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
        tg.setSouth(panelS);
        tg.addInput("input", inputArea);
        tg.setInput("input", "trade number");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tradeNum = tg.getInput("input");
                System.out.println(tradeNum);
                aa.submitBut(tradeNum);
            }
        });
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aa.backBut();
            }
        });
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aa.updateBut();
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
        aa.noTradeSelected();

    }

    @Override
    public BorderGUINoTextArea getTradeGUI() {
        return tg;
    }
}
