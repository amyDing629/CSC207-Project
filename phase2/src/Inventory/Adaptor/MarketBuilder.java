package Inventory.Adaptor;

import Trade.Adaptor.BorderGUIBuilder;
import Trade.Adaptor.BorderGUINoTextArea;
import Trade.Adaptor.BorderGUIWithThreeTextArea;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MarketBuilder implements BorderGUIBuilder {

    MarketController ic;
    BorderGUIWithThreeTextArea tg;

    public MarketBuilder(JFrame fr) {
        tg = new BorderGUIWithThreeTextArea();
        ic = new MarketController(tg, fr);
    }

    @Override
    public void buildFrame() {
        tg.setFrame(600, 200, "Market Session");
    }

    @Override
    public void buildPanelN() {
        JPanel panelN = new JPanel();
        JLabel msg = new JLabel("message:", SwingConstants.LEFT);
        JTextArea msgArea = new JTextArea();
        panelN.add(msg);
        panelN.add(msgArea);
        tg.setNorth(panelN);
        tg.initializeMsg(msgArea);

    }

    @Override
    public void buildPanelE() {


    }

    @Override
    public void buildPanelW() {
        JPanel panelW = new JPanel();
        JLabel il = new JLabel("available item list");
        JTextArea itemList = new JTextArea();
        itemList.setText(ic.printAvailable());
        JScrollPane sp = new JScrollPane(itemList);
        panelW.setLayout(new BoxLayout(panelW, BoxLayout.Y_AXIS));
        panelW.add(il);
        panelW.add(sp);
        tg.setWest(panelW);
        tg.initializeList(itemList);
        ic.updateListM();



    }

    @Override
    public void buildPanelS() {
        JPanel panelS = new JPanel();
        JTextArea ta = new JTextArea("Type item name here", 1, 10);
        JButton submit = new JButton("Submit");
        JButton back  = new JButton("Return");
        panelS.add(ta);
        panelS.add(submit);
        panelS.add(back);
        tg.setSouth(panelS);
        tg.addInput("input", ta);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ic.submitButM();
            }
        });
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ic.backBut();
            }
        });


    }

    @Override
    public void buildPanelC() {
        JPanel panelC = new JPanel();
        JLabel currTradeL = new JLabel("Item Selected");
        JTextArea currArea = new JTextArea();
        currArea.setEditable(false);

        panelC.setLayout(new BoxLayout(panelC, BoxLayout.Y_AXIS));
        panelC.add(currTradeL);
        panelC.add(currArea);
        currArea.setText("no item selected");
        tg.setCenter(panelC);
        tg.initializeCurr(currArea);
        ic.updateCurr();


    }

    @Override
    public BorderGUINoTextArea getTradeGUI() {
        return tg;
    }
}
