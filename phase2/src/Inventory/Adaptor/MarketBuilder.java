package Inventory.Adaptor;

import Trade.Adaptor.BorderGUIBuilder;
import Trade.Adaptor.BorderGUINoTextArea;
import Trade.Adaptor.BorderGUIWithThreeTextArea;

import javax.swing.*;


public class MarketBuilder implements BorderGUIBuilder {

    iItemController ic;
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
        itemList.setText(ic.printList());
        JScrollPane sp = new JScrollPane(itemList);
        panelW.setLayout(new BoxLayout(panelW, BoxLayout.Y_AXIS));
        panelW.add(il);
        panelW.add(sp);
        tg.setWest(panelW);
        tg.initializeList(itemList);
        ic.updateList();



    }

    @Override
    public void buildPanelS() {
        JPanel panelS = new JPanel();
        JTextArea ta = new JTextArea("Type item name here", 1, 10);
        JButton submit = new JButton("Submit");
        JButton back  = new JButton("Return");
        JButton update = new JButton("update");
        panelS.add(ta);
        panelS.add(submit);
        panelS.add(back);
        tg.setSouth(panelS);
        tg.addInput("input", ta);
        submit.addActionListener(e -> ic.submitBut());
        back.addActionListener(e -> ic.backBut());
        update.addActionListener(e -> ic.updateBut());


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
