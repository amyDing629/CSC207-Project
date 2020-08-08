//package Trade;
//
//import Inventory.Inventory;
//import Inventory.Item;
//import User.ClientUser;
//import User.UserManager;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class SelectItemToTradeGUI {
//    ClientUser currUser;
//    SelectController sc;
//    Item currItem;
//    TradeManager tm;
//    UserManager um;
//    Inventory iv;
//    Frame tf;
//
//    public SelectItemToTradeGUI(ClientUser currUser, TradeManager tm, UserManager um, Inventory iv, Frame tFrame){
//        this.currUser = currUser;
//        sc = new SelectController(currUser, um, iv);
//        this.tm = tm;
//        this.um = um;
//        this.iv = iv;
//        tf = tFrame;
//
//    }
//
//    public void run(){
//        JFrame frame = new JFrame("Select Item to Trade Session");
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
//        panelW.setPreferredSize(new Dimension(200,370));
//
//        JLabel msg = new JLabel("message:", SwingConstants.LEFT);
//        JTextArea msgArea = new JTextArea();
//        panelN.add(msg);
//        panelN.add(msgArea);
//
//        JLabel wishList = new JLabel("Wish List to Borrow");
//        JTextArea wishArea = new JTextArea();
//        wishArea.setText(sc.getWishList());
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
//        JButton request = new JButton("request trade");
//        panelE.add(request);
//
//        JLabel input = new JLabel("input item name");
//        JTextArea inputArea = new JTextArea("item name");
//        JButton submit = new JButton("submit");
//        JButton back = new JButton("return to trade menu");
//        panelS.add(input);
//        panelS.add(inputArea);
//        panelS.add(submit);
//        panelS.add(back);
//
//        frame.setVisible(true);
//
//        submit.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String itemName = inputArea.getText();
//                inputArea.setText("item name");
//                if (!sc.checkInput(itemName)){
//                    msgArea.setText("wrong input");
//                }else{
//                    currItem = sc.getItem(itemName);
//                    currArea.setText(sc.getItemInfo(itemName));
//                    msgArea.setText("current item has been updated");
//                }
//
//            }
//        });
//
//        request.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                RTradeGUI rtg = new RTradeGUI(currUser, currItem, tm, um, iv);
//                rtg.run();
//            }
//        });
//
//        back.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                tf.setVisible(true);
//                frame.setVisible(false);
//            }
//        });
//    }
//
//
//}
