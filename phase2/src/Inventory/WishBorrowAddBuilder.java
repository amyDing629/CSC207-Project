package Inventory;

import Trade.BorderGUIBuilder;
import Trade.BorderGUINoTextArea;
import Trade.BorderGUIWithThreeTextArea;
import User.Entity.ClientUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

public class WishBorrowAddBuilder implements BorderGUIBuilder {
    InventoryController ic;
    BorderGUIWithThreeTextArea tg;

    public WishBorrowAddBuilder(String currUser, JFrame fr) {
        tg = new BorderGUIWithThreeTextArea();
        ic = new InventoryController(currUser, tg, fr);
    }

    @Override
    public void buildFrame() {
        tg.setFrame(600, 200, "Add to Borrow Session");
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
        JTextArea ta = new JTextArea("type item name here");
        JButton submit = new JButton("submit");
        JButton awl = new JButton("add to wish list");
        JButton back  = new JButton("return");
        panelS.add(ta);
        panelS.add(submit);
        panelS.add(awl);
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
        awl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ic.addToWishBorrow();
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
        tg.setCenter(panelC);
        tg.initializeCurr(currArea);
        ic.updateCurr();
    }

    @Override
    public BorderGUINoTextArea getTradeGUI() {
        return tg;
    }
}

