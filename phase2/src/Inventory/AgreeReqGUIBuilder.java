package Inventory;

import Trade.BorderGUIBuilder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.UUID;

public class AgreeReqGUIBuilder extends WishBorrowBuilder implements BorderGUIBuilder {

    public AgreeReqGUIBuilder(String currUser, JFrame fr) {
        super(currUser, fr);
    }

    public void buildFrame() {
        tg.setFrame(600, 200, "Agree Requests Session");

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

    public void buildPanelE(){
        JPanel panelE = new JPanel();
        JButton agree = new JButton("agree");
        JButton disagree = new JButton("disagree");
        panelE.setLayout(new BoxLayout(panelE, BoxLayout.Y_AXIS));
        panelE.add(agree);
        panelE.add(disagree);
        tg.setEast(panelE);
        agree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ic.agreeBut();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });


        disagree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ic.disagreeBut();
            }
        });


    }


}
