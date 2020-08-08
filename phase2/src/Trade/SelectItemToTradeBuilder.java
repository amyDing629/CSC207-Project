package Trade;

import Inventory.Inventory;
import Inventory.Item;
import User.ClientUser;
import User.UserManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectItemToTradeBuilder implements BorderGUIBuilder {

    SelectController sc;
    BorderGUIWithThreeTextArea bta;

    public SelectItemToTradeBuilder(ClientUser currUser, TradeManager tm, UserManager um, Inventory iv, JFrame tFrame){
        bta = new BorderGUIWithThreeTextArea();
        sc = new SelectController(currUser, tm, um, iv, bta, tFrame);

    }
    @Override
    public void buildFrame() {
        bta.setFrame(600, 200, "Select Item to Trade Session");

    }

    @Override
    public void buildPanelN() {
        JPanel panelN = new JPanel();
        JLabel msg = new JLabel("message:", SwingConstants.LEFT);
        JTextArea msgArea = new JTextArea();
        panelN.add(msg);
        panelN.add(msgArea);
        bta.initializeMsg(msgArea);
        bta.setNorth(panelN);

    }

    @Override
    public void buildPanelE() {
        JPanel panelE = new JPanel();
        JButton request = new JButton("request trade");
        panelE.add(request);
        bta.setEast(panelE);
        request.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sc.enterRTradeGUI();
            }
        });


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
        JLabel input = new JLabel("input item name");
        JTextArea inputArea = new JTextArea("item name");
        JButton submit = new JButton("submit");
        JButton back = new JButton("return to trade menu");
        panelS.add(input);
        panelS.add(inputArea);
        panelS.add(submit);
        panelS.add(back);
        bta.setSouth(panelS);
        bta.initializeInput(inputArea);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sc.submitBut();
            }
        });
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sc.backBut();
            }
        });


    }

    @Override
    public void buildPanelC() {
        JPanel panelC = new JPanel();
        JLabel currTradeL = new JLabel("item selected");
        JTextArea currArea = new JTextArea();
        panelC.setLayout(new BoxLayout(panelC, BoxLayout.Y_AXIS));
        panelC.add(currTradeL);
        panelC.add(currArea);
        currArea.setText("no item selected");
        bta.initializeCurr(currArea);
        bta.setCenter(panelC);
    }

    @Override
    public BorderGUINoTextArea getTradeGUI() {
        return bta;
    }
}
