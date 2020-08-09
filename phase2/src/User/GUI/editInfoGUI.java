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

public class editInfoGUI {
    UserManager um;
    TradeManager tm;
    ItemApprovalManager iam;
    UIcontoller uc;
    Inventory iv;
    AdminActivityManager aam;
    JFrame frame;
    JFrame PFrame;
    public editInfoGUI(UserManager um, TradeManager tm, Inventory iv, ItemApprovalManager iam, UIcontoller uc, AdminActivityManager aam ,JFrame pFrame) {
        this.um = um;
        this.tm = tm;
        this.iam=iam;
        this.uc=uc;
        this.iv=iv;
        this.aam=aam;
        this.PFrame=pFrame;
    }
    public void run(String name){
        frame = new JFrame("Edit User Information");
        frame.setSize(500, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel welcomeLabel = new JLabel("Hello, user, " + name);
        welcomeLabel.setPreferredSize(new Dimension(300, 30));
        panel.add(welcomeLabel);
        frame.add(panel);

        ClientUser b = um.getUser(name);

        placeComponents(frame, panel, b);
        frame.setVisible(true);
    }


    private void placeComponents(JFrame frame, JPanel panel, ClientUser b){

        JLabel isAdmin = new JLabel("Admin: "+ um.getIsAdmin(b));
        isAdmin.setPreferredSize(new Dimension(300, 30));
        panel.add(isAdmin);

        JLabel action = new JLabel("Actions: ");
        action.setPreferredSize(new Dimension(300, 30));
        panel.add(action);

        JButton changePass = new JButton("Change Password");
        changePass.setPreferredSize(new Dimension(300, 30));
        panel.add(changePass);

        JButton freezeSystem = new JButton("ClientUser Freeze System");
        freezeSystem.setPreferredSize(new Dimension(300, 30));
        panel.add(freezeSystem);

        JButton limitSystem = new JButton("ClientUser Limit System");
        limitSystem.setPreferredSize(new Dimension(300, 30));
        panel.add(limitSystem);

        JButton CreateAdminGUI = new JButton("Create new Admin");
        CreateAdminGUI.setPreferredSize(new Dimension(300, 30));
        panel.add(CreateAdminGUI);

        JButton AddItemSystem = new JButton("Add Item System");
        AddItemSystem.setPreferredSize(new Dimension(300, 30));
        panel.add(AddItemSystem);

        JButton ReverseSystem = new JButton("Add Item System");
        ReverseSystem.setPreferredSize(new Dimension(300, 30));
        panel.add(ReverseSystem);

        JButton exit = new JButton("exit");
        exit.setPreferredSize(new Dimension(300, 30));
        panel.add(exit);
        if(!um.getIsAdmin(b)){
            limitSystem.setEnabled(false);
        }
        exit.addActionListener(e -> {
            frame.setVisible(false);
            PFrame.setVisible(true);
        });
        AddItemSystem.addActionListener(e -> {
            frame.setVisible(false);
            AddItemSystemGUI d = new AddItemSystemGUI(um,tm,iv,iam,aam,uc,frame);
            d.run(um.getUsername(b));
        });
        limitSystem.addActionListener(e -> {
            frame.setVisible(false);
            UserLimitGUI d = new UserLimitGUI(um,tm,iv,iam,aam,uc,frame);
            d.run(um.getUsername(b));
        });
        CreateAdminGUI.addActionListener(e -> {
            frame.setVisible(false);
         CreateAdminGUI d = new CreateAdminGUI(um,tm,iv,iam,aam,uc,frame);
            d.run(um.getUsername(b));
        });
       changePass.addActionListener(e -> {
            frame.setVisible(false);
            ChangePassGUI d = new ChangePassGUI(um,tm,iv,iam,aam,uc,frame);
            d.run(um.getUsername(b));
        });
        freezeSystem.addActionListener(e -> {
            frame.setVisible(false);
            UserFreezeSystem d = new UserFreezeSystem(um,tm,iv,iam,aam,uc,frame);
            d.run(um.getUsername(b));
        });

        ReverseSystem.addActionListener(e -> {
            frame.setVisible(false);
            ReverseSystemGUI d = new ReverseSystemGUI(um,tm,iv,iam,aam,uc,frame);
            d.run(um.getUsername(b));
        });

    }
}
