package Inventory;

import Trade.BorderGUIBuilder;
import Trade.BorderGUINoTextArea;
import Trade.BorderGUIWithThreeTextArea;

import javax.swing.*;

public class AgreeReqGUIBuilder implements BorderGUIBuilder {
    InventoryController ic;
    BorderGUIWithThreeTextArea tg;

    public AgreeReqGUIBuilder(String currUser, JFrame fr){
        tg = new BorderGUIWithThreeTextArea();
        ic = new InventoryController(currUser, tg, fr);
    }


    public void buildFrame() {
        tg.setFrame(600, 200, "Agree Requests Session");

    }

    @Override
    public void buildPanelN() {
        JPanel panelN = new JPanel();
        JLabel msg = new JLabel("Message:", SwingConstants.LEFT);
        JTextArea msgArea = new JTextArea();
        msgArea.setEditable(false);
        panelN.add(msg);
        panelN.add(msgArea);
        tg.initializeMsg(msgArea);
        tg.setNorth(panelN);

    }

    public void buildPanelW(){
        JPanel panelW = new JPanel();
        JLabel requestList = new JLabel("Request List");
        JTextArea requestArea = new JTextArea();
        JScrollPane jsp= new JScrollPane(requestArea);
        panelW.setLayout(new BoxLayout(panelW, BoxLayout.Y_AXIS));
        panelW.add(requestList);
        panelW.add(jsp);
        tg.setWest(panelW);
        tg.initializeList(requestArea);
        tg.setListText(ic.printRequest());
    }

    @Override
    public void buildPanelS() {
        JPanel panelS = new JPanel();
        JLabel input = new JLabel("Input Item Name");
        JTextArea inputArea = new JTextArea("Item Name");
        JButton submit = new JButton("Submit");
        JButton back = new JButton("Back");
        JButton update = new JButton("Update");
        panelS.add(input);
        panelS.add(inputArea);
        panelS.add(submit);
        panelS.add(back);
        panelS.add(update);
        tg.setSouth(panelS);
        tg.addInput("Input", inputArea);

        submit.addActionListener(e -> ic.submitButR());
        back.addActionListener(e -> ic.backBut());
        update.addActionListener(e -> ic.updateButR());


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
        currArea.setText("No Item Selected");
        tg.setCenter(panelC);
        tg.initializeCurr(currArea);
        ic.updateCurr();
    }

    @Override
    public BorderGUINoTextArea getTradeGUI() {
        return tg;
    }

    public void buildPanelE(){
        JPanel panelE = new JPanel();
        JButton agree = new JButton("Agree");
        JButton disagree = new JButton("Disagree");
        panelE.setLayout(new BoxLayout(panelE, BoxLayout.Y_AXIS));
        panelE.add(agree);
        panelE.add(disagree);
        tg.setEast(panelE);

        agree.addActionListener(e -> ic.agreeBut());
        disagree.addActionListener(e -> ic.disagreeBut());


    }


}
