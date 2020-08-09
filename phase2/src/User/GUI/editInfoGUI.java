package User.GUI;

import Inventory.Inventory;
import User.Adapter.UIController;
import Trade.TradeManager;
import User.UseCase.AdminActivityManager;
import User.UseCase.ApprovalManager;
import User.UseCase.UserManager;

import javax.swing.*;
import java.awt.*;

public class editInfoGUI {
    UserManager um;
    TradeManager tm;
    ApprovalManager iam;
    UIController uc;
    Inventory iv;
    AdminActivityManager aam;
    JFrame frame;
    JFrame PFrame;
    public editInfoGUI(UIController uc , JFrame pFrame) {
        this.um = new UserManager();
        this.tm = new TradeManager();
        this.iam= new ApprovalManager();
        this.uc=uc;
        this.iv=new Inventory();
        this.aam=new AdminActivityManager();
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


        placeComponents(frame, panel, name);
        frame.setVisible(true);
    }


    private void placeComponents(JFrame frame, JPanel panel, String b){

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
//        AddItemSystem.addActionListener(e -> {
//            frame.setVisible(false);
//            //AddItemSystemGUI d = new AddItemSystemGUI(uc,frame);
//            //d.run(b);
//        });
        limitSystem.addActionListener(e -> {
            frame.setVisible(false);
            UserLimitGUI d = new UserLimitGUI(um,tm,iv,iam,aam,uc,frame);
            d.run(b);
        });
        CreateAdminGUI.addActionListener(e -> {
            frame.setVisible(false);
         CreateAdminGUI d = new CreateAdminGUI(uc,frame);
            d.run(b);
        });
       changePass.addActionListener(e -> {
            frame.setVisible(false);
            ChangePassGUI d = new ChangePassGUI(uc,frame);
            d.run(b);
        });
        freezeSystem.addActionListener(e -> {
            frame.setVisible(false);
            UserFreezeSystem d = new UserFreezeSystem(uc,frame);
            d.run(b);
        });

        ReverseSystem.addActionListener(e -> {
            frame.setVisible(false);
            ReverseSystemGUI d = new ReverseSystemGUI(uc,frame);
            d.run(b);
        });

    }
}