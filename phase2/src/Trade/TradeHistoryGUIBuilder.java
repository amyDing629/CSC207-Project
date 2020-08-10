package Trade;

import javax.swing.*;
import java.awt.*;

public class TradeHistoryGUIBuilder implements BorderGUIBuilder {
    TradeHistoryController thc;
    BorderGUIWithThreeTextArea bgUI;

    public TradeHistoryGUIBuilder(String currUser, JFrame tf){
        bgUI = new BorderGUIWithThreeTextArea();
        this.thc = new TradeHistoryController(currUser, bgUI, tf);
    }


    @Override
    public void buildFrame() {
        bgUI.setFrame(800, 400, "Trade History Session");
    }

    @Override
    public void buildPanelN() {


    }

    @Override
    public void buildPanelE() {
        JButton update = new JButton("Update");
        JPanel upPanel = new JPanel();
        JButton back = new JButton("Back");
        upPanel.add(update);
        upPanel.add(back);
        upPanel.setLayout(new BoxLayout(upPanel, BoxLayout.Y_AXIS));
        bgUI.setEast(upPanel);

        back.addActionListener(e -> thc.backBut());

        update.addActionListener(e -> {
            thc.updateTrade();
            thc.updateUser();
        });

    }

    @Override
    public void buildPanelW() {

        JPanel panel = new JPanel();
        JLabel tradeList = new JLabel("Complete Trade");
        JTextArea trade = new JTextArea();
        trade.setText(thc.getTradeHistory());
        JScrollPane jsp= new JScrollPane(trade);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(tradeList);
        panel.add(jsp);
        bgUI.initializeList(trade);
        bgUI.setWest(panel);

    }

    @Override
    public void buildPanelS() {

    }

    @Override
    public void buildPanelC() {
        JPanel panelR = new JPanel();
        JLabel freUser = new JLabel("Most Frequent Trader");
        JTextArea user = new JTextArea();
        JScrollPane jspU = new JScrollPane(user);
        panelR.setLayout(new BoxLayout(panelR, BoxLayout.Y_AXIS));
        panelR.add(freUser);
        panelR.add(jspU);
        panelR.setPreferredSize(new Dimension(300,400));
        bgUI.initializeCurr(user);
        bgUI.setCenter(panelR);
        thc.updateUser();


    }

    @Override
    public BorderGUINoTextArea getTradeGUI() {
        return bgUI;
    }
}
