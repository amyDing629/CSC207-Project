package User.GUI;
//
//import Inventory.UseCase.Inventory;
//import User.Adapter.UIcController;
//import Trade.UseCase.TradeManager;
//import User.UseCase.AdminActivityManager;
//import User.UseCase.ItemApprovalManager;
//import User.UseCase.UserManager;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class AddItemSystemGUI {
//    UserManager um;
//    TradeManager tm;
//    ItemApprovalManager iam;
//    UIController uc;
//    Inventory iv;
//    AdminActivityManager aam;
//    JFrame pFrame;
//    JFrame frame;
//    public AddItemSystemGUI(UIController uc ,JFrame pFrame) {
//        this.um = new UserManager();
//        this.tm = new TradeManager();
//        this.iam= new ItemApprovalManager();
//        this.uc= uc;
//        this.iv= new Inventory();
//        this.aam= new AdminActivityManager();
//        this.pFrame= pFrame;
//    }
//    public void run(String name){
//        frame = new JFrame("User Freeze System");
//        frame.setSize(500, 700);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        JPanel panel = new JPanel();
//        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
//        JLabel welcomeLabel = new JLabel("Welcome: " + name);
//        welcomeLabel.setPreferredSize(new Dimension(300, 30));
//        panel.add(welcomeLabel);
//        frame.add(panel);
//
//        placeComponents(frame, panel, name);
//        frame.setVisible(true);
//    }
//
//    private void placeComponents(JFrame frame, JPanel panel, String b){
//
//        JButton editButton = new JButton("Add item");
//        editButton.setPreferredSize(new Dimension(300, 30));
//
//        JButton tradeButton = new JButton("Approve item from client user");
//        tradeButton.setPreferredSize(new Dimension(300, 30));
//
//        JButton exitButton = new JButton("quit to menu");
//        exitButton.setPreferredSize(new Dimension(300, 30));
//
//        panel.add(editButton);
//        panel.add(tradeButton);
//        panel.add(exitButton);
//        exitButton.addActionListener(e -> {
//            frame.setVisible(false);
//            pFrame.setVisible(true);
//        });
//        editButton.addActionListener(e -> {
//            frame.setVisible(false);
//            AddItemGUI d=new AddItemGUI(um,tm,iv,iam,aam,uc,frame);
//            d.run(b);
//        });
//        tradeButton.addActionListener(e -> {
//            frame.setVisible(false);
//            ApproveItemGUI d=new ApproveItemGUI(uc,frame);
//            d.run(b);
//        });
//    }
//}
