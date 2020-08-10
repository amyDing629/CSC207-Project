package Inventory;

import Trade.BorderGUIBuilder;
import Trade.BorderGUINoTextArea;
import Trade.BorderGUIWithThreeTextArea;
import User.Entity.ClientUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.UUID;

public class WishBorrowBuilder implements BorderGUIBuilder {
    InventoryController ic;
    BorderGUIWithThreeTextArea tg;

    public WishBorrowBuilder(String currUser, JFrame fr){
        tg = new BorderGUIWithThreeTextArea();
        ic = new InventoryController(currUser, tg, fr);
    }

    @Override
    public void buildFrame() {
        tg.setFrame(600, 200, "Edit WishBorrow Session");

    }

    @Override
    public void buildPanelN() {
        JPanel panelN = new JPanel();
        JLabel msg = new JLabel("message:", SwingConstants.LEFT);
        JTextArea msgArea = new JTextArea();
        panelN.add(msg);
        panelN.add(msgArea);
        tg.initializeMsg(msgArea);
        tg.setNorth(panelN);



    }

    public void buildPanelE(){
        JPanel panelE = new JPanel();
        JButton add = new JButton("Add");
        JButton delete = new JButton("delete");
        panelE.setLayout(new BoxLayout(panelE, BoxLayout.Y_AXIS));
        panelE.add(add);
        panelE.add(delete);
        tg.setEast(panelE);

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { ic.delButB(); }});

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { ic.addButB(); }});
    }

    @Override
    public void buildPanelW() {
        JPanel panelW = new JPanel();
        JLabel wishList = new JLabel("Wish Borrow List");
        JTextArea wishArea = new JTextArea();
        JScrollPane jsp= new JScrollPane(wishArea);
        panelW.setLayout(new BoxLayout(panelW, BoxLayout.Y_AXIS));
        panelW.add(wishList);
        panelW.add(jsp);
        tg.setWest(panelW);
        tg.initializeList(wishArea);
        ic.updateLstB();
    }

    @Override
    public void buildPanelS() {
        JPanel panelS = new JPanel();
        JLabel input = new JLabel("input item name");
        JTextArea inputArea = new JTextArea("item name");
        JButton submit = new JButton("submit");
        JButton back = new JButton("return");
        JButton update = new JButton("update");
        panelS.add(input);
        panelS.add(inputArea);
        panelS.add(submit);
        panelS.add(back);
        panelS.add(update);
        tg.setSouth(panelS);
        tg.addInput("input", inputArea);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { ic.submitButB(); }});
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { ic.backBut(); }});
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { ic.updateButB(); }});
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
