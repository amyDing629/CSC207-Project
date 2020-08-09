package User.GUI;

import Inventory.Inventory;
import User.Adapter.UIController;
import Trade.TradeManager;
import User.UseCase.AdminActivityManager;
import User.UseCase.ItemApprovalManager;
import User.UseCase.UserManager;

import javax.swing.*;
import java.awt.*;

public class UserLimitGUI {
    UserManager um;
    TradeManager tm;
    ItemApprovalManager iam;
    UIController uc;
    Inventory iv;
    AdminActivityManager aam;
    JFrame pFrame;
    JFrame frame;
    public UserLimitGUI(UserManager um, TradeManager tm, Inventory iv, ItemApprovalManager iam, AdminActivityManager aam,UIController uc ,JFrame pFrame) {
        this.um = um;
        this.tm = tm;
        this.iam=iam;
        this.uc=uc;
        this.iv=iv;
        this.aam=aam;
        this.pFrame=pFrame;
    }
    public void run(String name){
        frame = new JFrame("User Limit System");
        frame.setSize(500, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel welcomeLabel = new JLabel("Welcome: " + name);
        welcomeLabel.setPreferredSize(new Dimension(300, 30));
        panel.add(welcomeLabel);
        frame.add(panel);


        placeComponents(frame, panel, name);
        frame.setVisible(true);
    }

    private void placeComponents(JFrame frame, JPanel panel, String b){

        JButton editButton = new JButton("trade limit");
        editButton.setPreferredSize(new Dimension(300, 30));

        JButton tradeButton = new JButton("Incomplete Transaction limit");
        tradeButton.setPreferredSize(new Dimension(300, 30));

        JButton inventoryButton = new JButton("difference between borrowed and lend");
        inventoryButton.setPreferredSize(new Dimension(300, 30));

        JButton exitButton = new JButton("quit to menu");
        exitButton.setPreferredSize(new Dimension(300, 30));

        panel.add(editButton);
        panel.add(tradeButton);
        panel.add(inventoryButton);
        panel.add(exitButton);
        exitButton.addActionListener(e -> {
            frame.setVisible(false);
            pFrame.setVisible(true);
        });
        editButton.addActionListener(e -> {
            frame.setVisible(false);
           TradeLimitGUI d=new TradeLimitGUI(um,tm,iv,iam,aam,uc,frame);
            d.run(b);
        });
        tradeButton.addActionListener(e -> {
            frame.setVisible(false);
            IncompleteLimitGUI d=new IncompleteLimitGUI(uc,frame);
            d.run(b);
        });
        inventoryButton.addActionListener(e -> {
            frame.setVisible(false);
            DiffLimitGUI d=new DiffLimitGUI (uc,frame);
            d.run(b);
        });
    }
}