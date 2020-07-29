package Inventory;
import User.ClientUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class InvGUI {
    InventoryController ic;
    InventoryPresenter ip;
    Item currItem;

    public InvGUI(ClientUser currUser, Inventory iv) {
        ic = new InventoryController(currUser, iv);
        ip = new InventoryPresenter(currUser, iv);

    }

    public void run() {
        //Creating the Frame
        JFrame frame = new JFrame("Add to WishBorrow Session");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 200);


        JPanel panelNorth = new JPanel();
        JLabel ail = new JLabel("MESSAGE   ");
        JTextArea jtz = new JTextArea();
        jtz.setText(ip.printAvailable());
        JScrollPane sp = new JScrollPane(jtz);
        panelNorth.add(ail);
        panelNorth.add(sp);
        //JLabel label = new JLabel("choose an item from the following items: \n"+ip.printAvailable());
        //panel
        JPanel panel = new JPanel();
        JTextArea ta = new JTextArea("type item name here");
        JButton send = new JButton("Send");
        JButton awl = new JButton("add to wish list");
        panel.add(ta);
        panel.add(send);
        panel.add(awl);
        //itemPanel
        JPanel itemPanel = new JPanel();
        JButton seeInventory = new JButton("see available item list");
        itemPanel.add(seeInventory, SwingConstants.CENTER);


        JTextArea currItemInfo = new JTextArea();

        currItemInfo.setText("Current item info:\nno selected item ");
        frame.getContentPane().add(BorderLayout.CENTER, currItemInfo);
        frame.getContentPane().add(BorderLayout.EAST, itemPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, panelNorth);
        frame.setVisible(true);


        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String itemName = ta.getText();
                ta.setText("type item name here");
                if (!ic.selectItem(itemName)) {
                    jtz.setText(ip.wrongInput());
                } else {
                    currItem = ic.getItem(itemName);
                    currItemInfo.setText(ip.printItemInfo(currItem));
                }

            }
        });

        awl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ic.isOwnItem(currItem)) {
                    jtz.setText(ip.addToWishBorrow(false));
                } else if (ic.isInOwnWishList(currItem)) {
                    jtz.setText(ip.isInWishBorrow());
                } else {
                    ic.moveToWishList(currItem);
                    jtz.setText(ip.addToWishBorrow(true));
                }
            }
        });

        seeInventory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtz.setText(ip.printAvailable());
            }
        });

    }
}
    /*
    private void itemAction() {
        JFrame item = new JFrame(currItem.getName());
        item.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        item.setSize(400, 200);
        JTextArea jtz = new JTextArea();
        jtz.setText(ip.printItemInfo(currItem));
        JButton awl = new JButton("Add to your wish list");
        item.getContentPane().add(BorderLayout.NORTH, jtz);
        item.getContentPane().add(BorderLayout.SOUTH, awl);
        item.setVisible(true);
        awl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ic.isOwnItem(currItem)) {
                    jtz.setText(ip.addToWishBorrow(false));
                }
                else if (ic.isInOwnWishList(currItem)) {
                    jtz.setText(ip.isInWishBorrow());
                } else {
                    ic.moveToWishList(currItem);
                    jtz.setText(ip.addToWishBorrow(true));
                }
            }
        });

    }
}
        /*
            while (true){
                ip.printItemInfo(currItem);
                ip.itemAction();
                try{
                    String line2 = br.readLine();
                    if (line2.equals("1")){
                        if (ic.isOwnItem(currItem)){
                            ip.addToWishBorrow(false);
                            break;
                        }
                        //move back to see inventory
                        if (ic.isInOwnWishList(currItem)){
                            ip.isInWishBorrow();
                        }
                        else{
                            ic.moveToWishList(currItem);
                            ip.addToWishBorrow(true);
                        }
                        break;
                    }else if (line2.equals("2")){
                        break;
                    }else{
                        System.out.println("wrong input, please type again");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("FILE");
        JMenu m2 = new JMenu("Help");
        mb.add(m1);
        mb.add(m2);
        JMenuItem m11 = new JMenuItem("Open");
        JMenuItem m22 = new JMenuItem("Save as");
        m1.add(m11);
        m1.add(m22);

        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("Enter Text");
        JTextField tf = new JTextField(10); // accepts upto 10 characters
        JButton send = new JButton("Send");
        JButton reset = new JButton("Reset");
        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(send);
        panel.add(reset);

        // Text Area at the Center
        JTextArea ta = new JTextArea();

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.CENTER, ta);
        frame.setVisible(true);
    }

}

         */
