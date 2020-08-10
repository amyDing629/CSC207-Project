package User.PointSystem;

import Trade.Adaptor.BorderGUIBuilder;
import Trade.Adaptor.BorderGUINoTextArea;
import Trade.Adaptor.BorderGUIWithThreeTextArea;
import Trade.Adaptor.TradeGUIHelper;
import javax.swing.*;
import java.awt.*;
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
        tg.setFrame(800, 400, "Redeem Point Session");
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
        JLabel p = new JLabel("Points");
        JTextArea points = new JTextArea();
        points.setEditable(false);
        points.setPreferredSize(new Dimension(50, 50));
        JButton eb = new JButton("Get Bonus");
        panelE.setLayout(new BoxLayout(panelE, BoxLayout.Y_AXIS));
        panelE.add(p);
        panelE.add(points);
        panelE.add(eb);
        eb.addActionListener(e -> aa.ebBut());
        tg.addInput("Points", points);
        aa.updatePoint();
        tg.setEast(panelE);

    }

    @Override
    public void buildPanelW() {
        JPanel panelW =  new JPanel();
        JLabel tradeList = new JLabel("Available Trades");
        JTextArea tradeArea = new JTextArea();
        tradeArea.setEditable(false);
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
        JLabel input = new JLabel("Input Trade Number");
        JTextArea inputArea = new JTextArea("Trade Number", 1, 10);
        JButton submit = new JButton("Submit");
        JButton back = new JButton("Back");
        JButton update = new JButton("Update");
        panelS.add(input);
        panelS.add(inputArea);
        panelS.add(submit);
        panelS.add(back);
        panelS.add(update);
        tg.setSouth(panelS);
        tg.addInput("input", inputArea);
        tg.setInput("input", "Trade Number");

        submit.addActionListener(e -> {
            String tradeNum = tg.getInput("input");
            System.out.println(tradeNum);
            aa.submitBut(tradeNum);
        });
        back.addActionListener(e -> aa.backBut());
        update.addActionListener(e -> aa.updateBut());
    }


    @Override
    public void buildPanelC() {
        JPanel panelC =  new JPanel();
        JLabel currTradeL = new JLabel("Trade Selected");
        JTextArea currArea = new JTextArea();
        currArea.setEditable(false);
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
