package Inventory;

import Trade.BorderGUIBuilder;
import User.Entity.ClientUser;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.UUID;

public class WishLendBuilder extends WishBorrowBuilder implements BorderGUIBuilder {

    public WishLendBuilder(String currUser, JFrame tf) {
        super(currUser, tf);
    }

    @Override
    public void buildFrame() {
        tg.setFrame(600, 400, "Edit WishLend Session");
    }

    public void buildPanelE() {
        JPanel panelE = new JPanel();
        JLabel itemName = new JLabel("Item Name");
        JTextArea name = new JTextArea("Name");
        JScrollPane  jspN = new JScrollPane(name);
        JLabel itemDes = new JLabel("Item Description");
        JTextArea des = new JTextArea("Description");
        des.setPreferredSize(new Dimension(100, 100));
        JScrollPane jspD = new JScrollPane(des);
        JButton add = new JButton("Request");
        JButton delete = new JButton("Delete");
        JButton editDes = new JButton("Edit description");
        panelE.setLayout(new BoxLayout(panelE, BoxLayout.Y_AXIS));
        panelE.add(itemName);
        panelE.add(jspN);
        panelE.add(itemDes);
        panelE.add(jspD);
        panelE.add(delete);
        panelE.add(add);
        panelE.add(editDes);
        tg.setEast(panelE);
        tg.addInput("Name", name);
        tg.addInput("Description", des);

        delete.addActionListener(e -> ic.delButL());
        add.addActionListener(e -> ic.addButL());
        editDes.addActionListener(e -> ic.editDes());
    }

    public void buildPanelW(){
        JPanel panelW = new JPanel();
        JLabel wishList = new JLabel("Wish-to-Lend List");
        JTextArea wishArea = new JTextArea();
        JScrollPane jsp= new JScrollPane(wishArea);
        panelW.setLayout(new BoxLayout(panelW, BoxLayout.Y_AXIS));
        panelW.add(wishList);
        panelW.add(jsp);
        tg.setWest(panelW);
        tg.initializeList(wishArea);
        ic.updateLstL();
    }

    @Override
    public void buildPanelS() {
        JPanel panelS = new JPanel();
        JLabel input = new JLabel("Input item name");
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

        submit.addActionListener(e -> ic.submitButL());
        back.addActionListener(e -> ic.backBut());
        update.addActionListener(e -> ic.updateButL());


    }

}
