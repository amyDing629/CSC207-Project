package Inventory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MarketGUI {
    MarketController ic;
    Item currItem;
    Frame itf;

    public MarketGUI(Inventory iv, Frame itf) {
        ic = new MarketController(iv);
        this.itf = itf;
    }

    public void run() {
        //Creating the Frame
        JFrame frame = new JFrame("Market Session");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 200);


        JPanel panelNorth = new JPanel();
        JLabel ail = new JLabel("MESSAGE   ");
        JTextArea jtz = new JTextArea();
        JPanel panelW = new JPanel();
        JLabel il = new JLabel("available item list");
        JTextArea itemList = new JTextArea();
        itemList.setText(ic.printAvailable());
        JScrollPane sp = new JScrollPane(itemList);
        panelNorth.add(ail);
        panelNorth.add(jtz);
        panelW.setLayout(new BoxLayout(panelW, BoxLayout.Y_AXIS));
        panelW.add(il);
        panelW.add(sp);
        //JLabel label = new JLabel("choose an item from the following items: \n"+ip.printAvailable());
        //panel
        JPanel panel = new JPanel();
        JTextArea ta = new JTextArea("type item name here");
        JButton send = new JButton("Send");
        JButton back  = new JButton("return");
        panel.add(ta);
        panel.add(send);
        panel.add(back);
        //itemPanel


        JTextArea currItemInfo = new JTextArea();

        currItemInfo.setText("Current item info:\nno selected item ");
        frame.getContentPane().add(BorderLayout.WEST, panelW);
        frame.getContentPane().add(BorderLayout.CENTER, currItemInfo);
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, panelNorth);
        frame.setVisible(true);


        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String itemName = ta.getText();
                ta.setText("type item name here");
                if (!ic.selectItem(itemName)) {
                    jtz.setText(ic.wrongInput());
                } else {
                    currItem = ic.getItem(itemName);
                    currItemInfo.setText(ic.printItemInfo(currItem));
                    jtz.setText(currItem.getName() + " has been selected");
                }

            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                itf.setVisible(true);

            }
        });

    }
}
