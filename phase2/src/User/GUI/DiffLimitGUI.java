package User.GUI;

import Inventory.Inventory;
import User.Adapter.UIcontoller;
import Trade.TradeManager;
import User.Entity.ClientUser;
import User.UseCase.AdminActivityManager;
import User.UseCase.ItemApprovalManager;
import User.UseCase.UserManager;

import javax.swing.*;
import java.awt.*;

public class DiffLimitGUI {
    UserManager um;
    TradeManager tm;
    ItemApprovalManager iam;
    UIcontoller uc;
    Inventory iv;
    AdminActivityManager aam;
    JFrame pFrame;
    JFrame frame;
    public DiffLimitGUI(UIcontoller uc ,JFrame pFrame) {
        this.um = new UserManager();
        this.tm = new TradeManager();
        this.iam= new ItemApprovalManager();
        this.uc=uc;
        this.iv=new Inventory();
        this.aam=new AdminActivityManager();
        this.pFrame=pFrame;
    }
    public void run(String name){
        frame = new JFrame("Trade limit");
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

        JLabel textLabel = new JLabel("Please enter the user's username and the limit below");
        textLabel.setPreferredSize(new Dimension(300, 30));
        panel.add(textLabel);


        JTextField userInput = new JTextField(30);
        userInput.setPreferredSize(new Dimension(300, 30));
        panel.add(userInput);

        JTextField userInput1 = new JTextField(30);
        userInput1.setPreferredSize(new Dimension(300, 30));
        panel.add(userInput1);

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
            aam.setDiff(um.getUser(userInput.getText()),Integer.parseInt(userInput1.getText()));
            JOptionPane.showMessageDialog(null, "success changed limit");
            UserFreezeSystem d = new UserFreezeSystem(uc,frame);
            d.run(b);
        });
    }
}
