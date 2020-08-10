package User.GUI;
//
//import Inventory.UseCase.Inventory;
//import Inventory.Adaptor.InventoryController;
//import Inventory.Entity.Item;
//import User.Adapter.UIController;
//import Trade.UseCase.TradeManager;
//import User.Entity.ClientUser;
//import User.UseCase.AdminActivityManager;
//import User.UseCase.ItemApprovalManager;
//import User.UseCase.UserManager;
//
//import javax.swing.*;
//import java.awt.*;
//import java.util.ArrayList;
//
//public class ApproveItemGUI {
//    UserManager um;
//    TradeManager tm;
//    ItemApprovalManager iam;
//    UIController uc;
//    Inventory iv;
//    AdminActivityManager aam;
//    JFrame pFrame;
//    JFrame frame;
//    InventoryController ic;
//
//    public ApproveItemGUI(UIController uc ,JFrame pFrame) {
//        this.um = new UserManager();
//        this.tm = new TradeManager();
//        this.iam= new ItemApprovalManager();
//        this.uc = uc;
//        this.iv= new Inventory();
//        this.aam = new AdminActivityManager();
//        this.pFrame=pFrame;
//    }
//    public void run(String name){
//        frame = new JFrame("Freeze User");
//        frame.setSize(500, 700);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        JPanel panel = new JPanel();
//        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
//        JLabel welcomeLabel = new JLabel("Welcome: " + name);
//        welcomeLabel.setPreferredSize(new Dimension(300, 30));
//        panel.add(welcomeLabel);
//        frame.add(panel);
//
//        ClientUser b = um.getUser(name);
//
//        placeComponents(frame, panel, b);
//        frame.setVisible(true);
//    }
//
//    private void placeComponents(JFrame frame, JPanel panel, ClientUser b){
//        ic=new InventoryController(b,iv,um,iam);
//        JTextArea textArea = new JTextArea(5, 20);
//        textArea.setEditable(false);
//        JScrollPane scrollPane = new JScrollPane(textArea);
//        GridBagConstraints c = new GridBagConstraints();
//        c.gridwidth = GridBagConstraints.REMAINDER;
//        c.fill = GridBagConstraints.HORIZONTAL;
//
//        c.fill = GridBagConstraints.BOTH;
//        c.weightx = 1.0;
//        c.weighty = 1.0;
//        panel.add(scrollPane, c);
//        StringBuilder hi= new StringBuilder();
//        ArrayList<ArrayList<String>> usa = iam.getItemApproval();
//        if(usa.size()==0){
//            hi.append("Currently there is no approves\n");
//        }
//
//        for (int i = 0; i < usa.size(); i++) {
//            hi.append("Item ").append(i).append(": ").append(usa.get(i).get(1)).append("\n");
//            hi.append("Description: ").append(usa.get(i).get(2)).append("\n");
//            hi.append("Owner: ").append(usa.get(i).get(3)).append("\n");
//            hi.append("**************************").append("\n");
//        }
//        System.out.println(hi.toString());
//        textArea.setText(hi.toString());
//        JTextField userInput = new JTextField(30);
//        userInput.setPreferredSize(new Dimension(300, 30));
//        panel.add(userInput);
//
//        JButton submitButton = new JButton("submit");
//        submitButton.setPreferredSize(new Dimension(300, 30));
//        panel.add(submitButton);
//
//        JButton exit = new JButton("exit");
//        exit.setPreferredSize(new Dimension(300, 30));
//        panel.add(exit);
//        exit.addActionListener(e -> {
//            frame.setVisible(false);
//            pFrame.setVisible(true);
//        });
//        submitButton.addActionListener(e -> {
//            int there=0;
//            for (ArrayList<String> strings : usa) {
//                if (strings.get(1).equals(userInput.getText())) {
//                    Item z = new Item(strings.get(1), strings.get(3));
//                    z.setDescription(strings.get(2));
//                    iv.addItem(z);
//                    there = 1;
//                    um.getUser(strings.get(3)).addWishes(strings.get(1));
//                    iam.removeItem(strings.get(1));
//                    JOptionPane.showMessageDialog(null, "item add successfully");
//                }
//            }
//            if(there==0){
//                JOptionPane.showMessageDialog(null,"item name not found");
//            }
//        });
//    }
//}
