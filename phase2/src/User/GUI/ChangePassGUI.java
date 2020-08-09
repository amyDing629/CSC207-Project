package User.GUI;

import Inventory.Inventory;
import User.Adapter.UIController;
import Trade.TradeManager;
import User.Entity.ClientUser;
import User.UseCase.AdminActivityManager;
import User.UseCase.ItemApprovalManager;
import User.UseCase.UserManager;

import javax.swing.*;
import java.awt.*;

public class ChangePassGUI {
    UserManager um;
    TradeManager tm;
    ItemApprovalManager iam;
    UIController uc;
    Inventory iv;
    AdminActivityManager aam;
    JFrame pFrame;
    JFrame frame;
    public ChangePassGUI(UIController uc ,JFrame pFrame) {
        this.um = new UserManager();
        this.tm = new TradeManager();
        this.iam= new ItemApprovalManager();
        this.uc=uc;
        this.iv=new Inventory();
        this.aam=new AdminActivityManager();
        this.pFrame=pFrame;
    }
    public void run(String name){
        frame = new JFrame("Change your password");
        frame.setSize(500, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel welcomeLabel = new JLabel("Welcome: " + name);
        welcomeLabel.setPreferredSize(new Dimension(300, 30));
        panel.add(welcomeLabel);
        frame.add(panel);

        ClientUser b = um.getUser(name);

        placeComponents(frame, panel, b);
        frame.setVisible(true);
    }

    private void placeComponents(JFrame frame, JPanel panel, ClientUser b){
        JLabel curPass = new JLabel("Current password:"+ um.getPassword(b));
        curPass.setPreferredSize(new Dimension(300, 30));
        panel.add(curPass);
        JLabel nameLabel = new JLabel("Change your password");
        nameLabel.setPreferredSize(new Dimension(300, 30));
        panel.add(nameLabel);

        JTextField passInput = new JTextField(30);
        passInput.setPreferredSize(new Dimension(300, 30));
        panel.add(passInput);

        JButton submitButton = new JButton("submit");
        submitButton.setPreferredSize(new Dimension(300, 30));
        panel.add(submitButton);

        JButton exit = new JButton("exit");
        exit.setPreferredSize(new Dimension(300, 30));
        panel.add(exit);
        exit.addActionListener(e -> {
            frame.setVisible(false);
            pFrame.setVisible(true);
        });
        submitButton.addActionListener(e -> {
            frame.setVisible(false);
            um.set(b,passInput.getText());
            JOptionPane.showMessageDialog(null, "success changed pass");
        });
    }
}
