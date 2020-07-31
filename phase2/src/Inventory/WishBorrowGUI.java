package Inventory;

import User.ClientUser;
import User.UserManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WishBorrowGUI {
    InventoryController ic;
    Item currItem;
    InventoryPresenter ip;
    Frame tf;
    Inventory iv;
    UserManager um;
    ClientUser currUser;

    public WishBorrowGUI(ClientUser currUser, Inventory iv, UserManager um, Frame tf){
        ic = new InventoryController(currUser, iv, um);
        ip = new InventoryPresenter(currUser, iv);
        this.iv = iv;
        this.tf = tf;
        this.um = um;
        this.currUser = currUser;


    }

    public void run(){
        JFrame frame = new JFrame("Edit WishLend Session");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 200);

        JPanel panelW = new JPanel();
        JPanel panelC = new JPanel();
        JPanel panelN = new JPanel();
        JPanel panelE = new JPanel();
        JPanel panelS = new JPanel();

        frame.getContentPane().add(BorderLayout.EAST, panelE);
        frame.getContentPane().add(BorderLayout.WEST, panelW);
        frame.getContentPane().add(BorderLayout.CENTER, panelC);
        frame.getContentPane().add(BorderLayout.SOUTH, panelS);
        frame.getContentPane().add(BorderLayout.NORTH, panelN);

        JLabel msg = new JLabel("message:", SwingConstants.LEFT);
        JTextArea msgArea = new JTextArea();
        panelN.add(msg);
        panelN.add(msgArea);

        JLabel wishList = new JLabel("WishLend List");
        JTextArea wishArea = new JTextArea();
        wishArea.setText(ic.printWishLend());
        JScrollPane jsp= new JScrollPane(wishArea);
        panelW.setLayout(new BoxLayout(panelW, BoxLayout.Y_AXIS));
        panelW.add(wishList);
        panelW.add(jsp);

        JLabel currTradeL = new JLabel("item selected");
        JTextArea currArea = new JTextArea();
        panelC.setLayout(new BoxLayout(panelC, BoxLayout.Y_AXIS));
        panelC.add(currTradeL);
        panelC.add(currArea);
        currArea.setText("no item selected");

        JButton add = new JButton("Add");
        JButton delete = new JButton("delete");
        panelE.setLayout(new BoxLayout(panelE, BoxLayout.Y_AXIS));
        panelE.add(delete);
        panelE.add(add);

        JLabel input = new JLabel("input item name");
        JTextArea inputArea = new JTextArea("item name");
        JButton submit = new JButton("submit");
        JButton back = new JButton("return");
        panelS.add(input);
        panelS.add(inputArea);
        panelS.add(submit);
        panelS.add(back);

        frame.setVisible(true);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String itemName = inputArea.getText();
                inputArea.setText("item name");
                if (!ic.getWishLend().contains(itemName)) {
                    msg.setText("wrong input");
                } else {
                    currItem = ic.getItem(itemName);
                    currArea.setText(ip.printItemInfo(currItem));
                    msg.setText("the item has been selected");
                }
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ic.deleteItem(currItem);
                currArea.setText("no item selected");
                msg.setText(currItem.getName()+" has been deleted from the wish borrow list");
                currItem = null;
                wishArea.setText(ic.printWishLend());
            }
        });


        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WishBorrowAddGUI(currUser, iv, um, frame).run();
                currItem = null;
                wishArea.setText(ic.printWishLend());
            }
        });


        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tf.setVisible(true);
                frame.setVisible(false);
            }
        });

    }
}

