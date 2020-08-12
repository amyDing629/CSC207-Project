package Inventory.Adaptor;

import Trade.Adaptor.BorderGUIBuilder;
import Trade.Adaptor.BorderGUINoTextArea;
import Trade.Adaptor.BorderGUIWithThreeTextArea;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WishLendBuilder implements BorderGUIBuilder {

    iItemController ic;
    BorderGUIWithThreeTextArea tg;

    public WishLendBuilder(String currUser, JFrame fr){
        tg = new BorderGUIWithThreeTextArea();
        ic = new WishLendController(currUser, tg, fr);
    }

    @Override
    public void buildFrame() {
        tg.setFrame(600, 400, "Edit WishLend Session");
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

    public void buildPanelE() {
        JPanel panelE = new JPanel();
        JLabel itemName = new JLabel("item name");
        JTextArea name = new JTextArea("name");
        JScrollPane  jspN = new JScrollPane(name);
        JLabel itemDes = new JLabel("item description");
        JTextArea des = new JTextArea("description");
        des.setPreferredSize(new Dimension(100, 100));
        JScrollPane jspD = new JScrollPane(des);
        JButton add = new JButton("request");
        JButton delete = new JButton("delete");
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
        tg.addInput("name", name);
        tg.addInput("des", des);
        delete.addActionListener(e -> ic.performActionTwo());
        add.addActionListener(e -> ic.performActionOne());
        editDes.addActionListener(e -> ic.performActionThree());
    }

    public void buildPanelW(){
        JPanel panelW = new JPanel();
        JLabel wishList = new JLabel("Wish Lend List");
        JTextArea wishArea = new JTextArea();
        JScrollPane jsp= new JScrollPane(wishArea);
        panelW.setLayout(new BoxLayout(panelW, BoxLayout.Y_AXIS));
        panelW.add(wishList);
        panelW.add(jsp);
        tg.setWest(panelW);
        tg.initializeList(wishArea);
        ic.updateList();
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
        submit.addActionListener(e -> ic.submitBut());
        back.addActionListener(e -> ic.backBut());
        update.addActionListener(e -> ic.updateBut());


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
