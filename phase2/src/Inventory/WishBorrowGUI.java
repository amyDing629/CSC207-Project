//package Inventory;
//
//import User.ClientUser;
//import User.ItemApprovalManager;
//import User.UserManager;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class WishBorrowGUI {
//    InventoryController ic;
//    Item currItem;
//    InventoryPresenter ip;
//    Frame tf;
//    Inventory iv;
//    UserManager um;
//    ClientUser currUser;
//    ItemApprovalManager iam;
//
//    public WishBorrowGUI(ClientUser currUser, Inventory iv, UserManager um, ItemApprovalManager iam, Frame tf){
//        ic = new InventoryController(currUser, iv, um, iam);
//        ip = new InventoryPresenter(currUser, iv);
//        this.iv = iv;
//        this.tf = tf;
//        this.um = um;
//        this.currUser = currUser;
//        this.iam = iam;
//
//
//    }
//
//    public void run(){
//        JFrame frame = new JFrame("Edit WishBorrow Session");
//        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        frame.setSize(600, 200);
//
//        JPanel panelW = new JPanel();
//        JPanel panelC = new JPanel();
//        JPanel panelN = new JPanel();
//        JPanel panelE = new JPanel();
//        JPanel panelS = new JPanel();
//
//        frame.getContentPane().add(BorderLayout.EAST, panelE);
//        frame.getContentPane().add(BorderLayout.WEST, panelW);
//        frame.getContentPane().add(BorderLayout.CENTER, panelC);
//        frame.getContentPane().add(BorderLayout.SOUTH, panelS);
//        frame.getContentPane().add(BorderLayout.NORTH, panelN);
//
//        JLabel msg = new JLabel("message:", SwingConstants.LEFT);
//        JTextArea msgArea = new JTextArea();
//        panelN.add(msg);
//        panelN.add(msgArea);
//
//        JLabel wishList = new JLabel("WishBorrow List");
//        JTextArea wishArea = new JTextArea();
//        wishArea.setText(ic.printWishBorrow());
//        JScrollPane jsp= new JScrollPane(wishArea);
//        panelW.setLayout(new BoxLayout(panelW, BoxLayout.Y_AXIS));
//        panelW.add(wishList);
//        panelW.add(jsp);
//
//        JLabel currTradeL = new JLabel("item selected");
//        JTextArea currArea = new JTextArea();
//        panelC.setLayout(new BoxLayout(panelC, BoxLayout.Y_AXIS));
//        panelC.add(currTradeL);
//        panelC.add(currArea);
//        currArea.setText("no item selected");
//
//        JButton add = new JButton("Add");
//        JButton delete = new JButton("delete");
//        panelE.setLayout(new BoxLayout(panelE, BoxLayout.Y_AXIS));
//        panelE.add(add);
//        panelE.add(delete);
//
//        JLabel input = new JLabel("input item name");
//        JTextArea inputArea = new JTextArea("item name");
//        JButton submit = new JButton("submit");
//        JButton back = new JButton("return");
//        JButton update = new JButton("update");
//        panelS.add(input);
//        panelS.add(inputArea);
//        panelS.add(submit);
//        panelS.add(back);
//        panelS.add(update);
//
//        frame.setVisible(true);
//
//        submit.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String itemName = inputArea.getText();
//                inputArea.setText("item name");
//                if (!ic.getWishBorrow().contains(itemName)) {
//                    msgArea.setText("wrong input");
//                } else {
//                    currItem = ic.getItem(itemName);
//                    currArea.setText(ip.printItemInfo(currItem));
//                    msgArea.setText("the item has been selected");
//                }
//            }
//        });
//
//        delete.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (currItem == null){
//                    msgArea.setText("Fail deleting the item since no item is selected");
//                }else{
//                    ic.deleteItemB(currItem);
//                    currArea.setText("no item selected");
//                    msgArea.setText(currItem.getName()+" has been deleted from the wish borrow list");
//                    currItem = null;
//                    wishArea.setText(ic.printWishBorrow());
//                }
//            }
//        });
//
//
//        add.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                new WishBorrowAddGUI(currUser, iv, um, iam, frame).run();
//                currItem = null;
//                wishArea.setText(ic.printWishBorrow());
//                frame.setVisible(false);
//            }
//        });
//
//
//        back.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                tf.setVisible(true);
//                frame.setVisible(false);
//            }
//        });
//
//        update.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                wishArea.setText(ic.printWishBorrow());
//                currItem = null;
//                currArea.setText("no item selected");
//
//            }
//        });
//
//    }
//}

