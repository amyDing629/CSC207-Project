package Inventory;

import User.Entity.ClientUser;
import User.UseCase.ItemApprovalManager;
import User.UseCase.UserManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WishLendGUI {
    InventoryController ic;
    Item currItem;
    InventoryPresenter ip;
    Frame tf;

    public WishLendGUI(ClientUser currUser, Inventory iv, UserManager um, ItemApprovalManager iam, Frame tf){
        ic = new InventoryController(currUser, iv, um, iam);
        ip = new InventoryPresenter(currUser, iv);
        this.tf = tf;


    }

    public void run(){
        JFrame frame = new JFrame("Edit WishLend Session");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 500);

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

        JLabel itemName = new JLabel("item name");
        JTextArea name = new JTextArea("name");
        JScrollPane  jspN = new JScrollPane(name);
        JLabel itemDes = new JLabel("item description");
        JTextArea des = new JTextArea("description");
        des.setPreferredSize(new Dimension(100, 100));
        JScrollPane jspD = new JScrollPane(des);
        JButton add = new JButton("request");
        JButton delete = new JButton("delete");
        JButton create = new JButton("Create item");
        JButton editDes = new JButton("Edit description");
        panelE.setLayout(new BoxLayout(panelE, BoxLayout.Y_AXIS));
        panelE.add(itemName);
        panelE.add(jspN);
        panelE.add(itemDes);
        panelE.add(jspD);
        panelE.add(create);
        panelE.add(delete);
        panelE.add(add);
        panelE.add(editDes);

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
                if (currItem == null) {
                    msg.setText("no item is selected");
                } else {
                    ic.deleteItemL(currItem);
                    currArea.setText("no item selected");
                    msg.setText(currItem.getName() + " has been deleted from the wish borrow list");
                    currItem = null;
                    wishArea.setText(ic.printWishLend());
                }
            }
        });

        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String itemName = name.getText();
                String description = des.getText();
                if (itemName.equals("")) {
                    msg.setText("please enter item name");
                }else if(ic.itemExist(itemName)){
                    msg.setText("the item has already been used");
                }
                else {
                    currItem = ic.createItem(itemName);
                    ic.setDescription(description, currItem);
                    currArea.setText(ip.printItemInfo(currItem));
                    msg.setText(currItem.getName()+" has been created");
                }
            }
        });

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ic.addItem(currItem.getName(), currItem.getDescription());
                currArea.setText("no item selected");
                msg.setText(currItem.getName()+" has been requested, please wait for adminUser to agree");
                currItem = null;
            }
        });

        editDes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currItem == null){
                    msg.setText("no item is selected");
                }else{
                    String description = des.getText();
                    ic.setDescription(description, currItem);
                    msg.setText("Description of "+ currItem.getName()+" has been changed");
                    currArea.setText(ip.printItemInfo(currItem));
                }
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tf.setVisible(true);
                frame.setVisible(false);
            }
        });

        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wishArea.setText(ic.printWishLend());
                currItem = null;
                currArea.setText("no item selected");

            }
        });

    }
}

