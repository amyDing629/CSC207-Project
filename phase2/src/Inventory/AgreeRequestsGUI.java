package Inventory;

import User.Entity.ClientUser;
import User.UseCase.ItemApprovalManager;
import User.UseCase.UserManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AgreeRequestsGUI {
    InventoryController ic;
    Item currItem;
    InventoryPresenter ip;
    Frame tf;
    Inventory iv;
    UserManager um;
    ClientUser currUser;
    ItemApprovalManager iam;

    public AgreeRequestsGUI(ClientUser currUser, Inventory iv, UserManager um, ItemApprovalManager iam, Frame tf){
        ic = new InventoryController(currUser, iv, um, iam);
        ip = new InventoryPresenter(currUser, iv);
        this.iv = iv;
        this.tf = tf;
        this.um = um;
        this.currUser = currUser;
        this.iam = iam;


    }

    public void run(){
        JFrame frame = new JFrame("Agree Requests Session");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 200);

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

        JLabel requestList = new JLabel("Request List");
        JTextArea requestArea = new JTextArea();
        requestArea.setText(ic.printRequest());
        JScrollPane jsp= new JScrollPane(requestArea);
        panelW.setLayout(new BoxLayout(panelW, BoxLayout.Y_AXIS));
        panelW.add(requestList);
        panelW.add(jsp);

        JLabel currIt = new JLabel("item selected");
        JTextArea currArea = new JTextArea();
        panelC.setLayout(new BoxLayout(panelC, BoxLayout.Y_AXIS));
        panelC.add(currIt);
        panelC.add(currArea);
        currArea.setText("no item selected");

        JButton agree = new JButton("agree");
        JButton disagree = new JButton("disagree");
        panelE.setLayout(new BoxLayout(panelE, BoxLayout.Y_AXIS));
        panelE.add(agree);
        panelE.add(disagree);

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
                if (!ic.iamCheckInput(itemName)) {
                    msgArea.setText("wrong input");
                } else {
                    currItem = ic.getItemFromIam(itemName);
                    currArea.setText(ip.printItemInfo(currItem));
                    msgArea.setText("the item has been selected");
                }
            }
        });

        agree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currItem == null){
                    msgArea.setText("no item is selected");
                }else{
                    ic.addToWishLend(currItem);
                    currArea.setText("no item selected");
                    msgArea.setText(currItem.getName()+" has been added to user's wishLend list");
                    currItem = null;
                    requestArea.setText(ic.printRequest());
                }
            }
        });


        disagree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currItem == null) {
                    msgArea.setText("no item is selected");
                }else{
                    ic.removeItemFromIam(currItem);
                    currArea.setText("no item selected");
                    currItem = null;
                    requestArea.setText(ic.printRequest());
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
                requestArea.setText(ic.printRequest());
                currItem = null;
                currArea.setText("no item selected");

            }
        });

    }

}
