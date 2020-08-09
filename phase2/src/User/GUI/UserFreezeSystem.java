package User.GUI;

import Inventory.Inventory;
import Main.UI.UIcontoller;
import Trade.TradeManager;
import User.Entity.ClientUser;
import User.UseCase.AdminActivityManager;
import User.UseCase.ItemApprovalManager;
import User.UseCase.UserManager;

import javax.swing.*;
import java.awt.*;

public class UserFreezeSystem {
    UserManager um;
    TradeManager tm;
    ItemApprovalManager iam;
    UIcontoller uc;
    Inventory iv;
    AdminActivityManager aam;
    JFrame pFrame;
    JFrame frame;
    public UserFreezeSystem(UserManager um, TradeManager tm, Inventory iv, ItemApprovalManager iam, AdminActivityManager aam,UIcontoller uc ,JFrame pFrame) {
        this.um = um;
        this.tm = tm;
        this.iam=iam;
        this.uc=uc;
        this.iv=iv;
        this.aam=aam;
        this.pFrame=pFrame;
    }
    public void run(String name){
        frame = new JFrame("User Freeze System");
        frame.setSize(500, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel welcomeLabel = new JLabel("Welcome: " + name);
        welcomeLabel.setPreferredSize(new Dimension(300, 30));
        panel.add(welcomeLabel);
        frame.add(panel);

        ClientUser b =um.getUser(name);

        placeComponents(frame, panel, b);
        frame.setVisible(true);
    }

    private void placeComponents(JFrame frame, JPanel panel, ClientUser b){

        JLabel freezeStatus = new JLabel("Freeze Status: "+ um.getIsFrozen(b));
        freezeStatus.setPreferredSize(new Dimension(300, 30));
        panel.add(freezeStatus);


        JButton editButton = new JButton("request to remove freeze");
        editButton.setPreferredSize(new Dimension(300, 30));

        JButton tradeButton = new JButton("Freeze User");
        tradeButton.setPreferredSize(new Dimension(300, 30));

        JButton inventoryButton = new JButton("Unfreeze User");
        inventoryButton.setPreferredSize(new Dimension(300, 30));

        JButton exitButton = new JButton("quit to menu");
        exitButton.setPreferredSize(new Dimension(300, 30));

        if(!um.getIsAdmin(b)){
            tradeButton.setEnabled(false);
            inventoryButton.setEnabled(false);
        }
        if(!um.getIsFrozen(b)){
            editButton.setEnabled(false);
        }
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
            RequestUnfreezeTicketGUI d=new RequestUnfreezeTicketGUI(um,tm,iv,iam,aam,uc,frame);
            d.run(um.getUsername(b));
        });
        tradeButton.addActionListener(e -> {
            frame.setVisible(false);
            FreeUserGUI d=new FreeUserGUI(um,tm,iv,iam,aam,uc,frame);
            d.run(um.getUsername(b));
        });
        inventoryButton.addActionListener(e -> {
            frame.setVisible(false);
            UnfreezeGUI d=new UnfreezeGUI(um,tm,iv,iam,aam,uc,frame);
            d.run(um.getUsername(b));
        });
    }
}
