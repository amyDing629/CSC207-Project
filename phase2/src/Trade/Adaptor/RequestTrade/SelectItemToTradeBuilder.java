package Trade.Adaptor.RequestTrade;

import Trade.Adaptor.BorderGUIBuilder;
import Trade.Adaptor.BorderGUINoTextArea;
import Trade.Adaptor.BorderGUI;

import javax.swing.*;
import java.awt.*;

public class SelectItemToTradeBuilder implements BorderGUIBuilder {

    SelectController sc;
    BorderGUI bta;

    public SelectItemToTradeBuilder(String currUser, JFrame tFrame){
        bta = new BorderGUI();
        sc = new SelectController(currUser, bta, tFrame);

    }
    @Override
    public void buildFrame() {
        bta.setFrame(600, 200, "Select Item to Trade Session");

    }

    @Override
    public void buildPanelN() {
        JPanel panelN = new JPanel();
        JLabel msg = new JLabel("Message:", SwingConstants.LEFT);
        JTextArea msgArea = new JTextArea();
        panelN.add(msg);
        panelN.add(msgArea);
        bta.initializeMsg(msgArea);
        bta.setNorth(panelN);

    }

    @Override
    public void buildPanelE() {
        JPanel panelE = new JPanel();
        JButton request = new JButton("Request Trade");
        panelE.add(request);
        bta.setEast(panelE);
        request.addActionListener(e -> sc.enterRTradeGUI());


    }

    @Override
    public void buildPanelW() {
        JPanel panelW =  new JPanel();
        JLabel wishList = new JLabel("Wish List to Borrow");
        JTextArea wishArea = new JTextArea();
        JScrollPane jsp= new JScrollPane(wishArea);
        panelW.setLayout(new BoxLayout(panelW, BoxLayout.Y_AXIS));
        panelW.add(wishList);
        panelW.add(jsp);
        panelW.setPreferredSize(new Dimension(200,370));
        bta.setWest(panelW);
        bta.initializeList(wishArea);
        sc.updateList();


    }

    @Override
    public void buildPanelS() {
        JPanel panelS = new JPanel();
        JLabel input = new JLabel("Input Item Name");
        JTextArea inputArea = new JTextArea("Item Name", 1, 10);
        JButton submit = new JButton("Submit");
        JButton back = new JButton("Back");
        panelS.add(input);
        panelS.add(inputArea);
        panelS.add(submit);
        panelS.add(back);
        bta.setSouth(panelS);
        bta.addInput("input", inputArea);

        submit.addActionListener(e -> sc.submitBut());
        back.addActionListener(e -> sc.backBut());
    }

    @Override
    public void buildPanelC() {
        JPanel panelC = new JPanel();
        JLabel currTradeL = new JLabel("Item Selected");
        JTextArea currArea = new JTextArea();
        panelC.setLayout(new BoxLayout(panelC, BoxLayout.Y_AXIS));
        panelC.add(currTradeL);
        panelC.add(currArea);
        currArea.setText("No item selected");
        bta.initializeCurr(currArea);
        bta.setCenter(panelC);
    }

    @Override
    public BorderGUI getTradeGUI() {
        return bta;
    }
}
