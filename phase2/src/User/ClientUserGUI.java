package User;

import Inventory.*;
import Main.UI.UIcontoller;
import Trade.TradeGUI_Main;
import Trade.TradeManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientUserGUI {
    UserManager um;
    TradeManager tm;
    ItemApprovalManager iam;
    UIcontoller uc;
    Inventory iv;
    AdminActivityManager aam;
    JFrame pFrame;
    JFrame frame;
    public ClientUserGUI(UserManager um, TradeManager tm, Inventory iv, ItemApprovalManager iam, AdminActivityManager aam,UIcontoller uc ,JFrame pFrame) {
        this.um = um;
        this.tm = tm;
        this.iam=iam;
        this.uc=uc;
        this.iv=iv;
        this.aam=aam;
        this.pFrame=pFrame;
    }
    public void run(String name){
        frame = new JFrame("Login Success");
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

        JLabel freezeStatus = new JLabel("Freeze Status: "+ um.getIsFrozen(b));
        freezeStatus.setPreferredSize(new Dimension(300, 30));
        panel.add(freezeStatus);

        JLabel tradeLimit = new JLabel("Trade limit: "+ tm.getTradeNumber(b) + "/" + um.getWeekTransactionLimit(b));
        tradeLimit.setPreferredSize(new Dimension(300, 30));
        panel.add(tradeLimit);

        JLabel inCompLimit = new JLabel("Incomplete trade limit: "+ tm.getIncompleteTransaction(b) +
                "/" + um.getIncompleteTransactionLimit(b));
        inCompLimit.setPreferredSize(new Dimension(300, 30));
        panel.add(inCompLimit);

        JLabel diff = new JLabel("Difference between borrow and lend: "+ um.readDiff(b) + "/" +um.getDiff(b));
        diff.setPreferredSize(new Dimension(300, 30));
        panel.add(diff);

        JButton editButton = new JButton("Edit Information");
        editButton.setPreferredSize(new Dimension(300, 30));
        panel.add(editButton);

        JButton tradeButton = new JButton("Trade");
        tradeButton.setPreferredSize(new Dimension(300, 30));
        panel.add(tradeButton);

        JButton inventoryButton = new JButton("Inventory");
        inventoryButton.setPreferredSize(new Dimension(300, 30));
        panel.add(inventoryButton);


        JButton exitButton = new JButton("quit to menu");
        exitButton.setPreferredSize(new Dimension(300, 30));
        panel.add(exitButton);

        editButton.addActionListener(e -> {
            frame.setVisible(false);
            editInfoGUI d = new editInfoGUI(um,tm,iv,iam,uc,aam,frame);
            d.run(um.getUsername(b));
        });

        inventoryButton.addActionListener(e -> {
            frame.setVisible(false);
            InventoryGUI d = new InventoryGUI(b,iv,um,iam,frame);
            d.run();
        });

        exitButton.addActionListener(e -> {
            frame.setVisible(false);
            pFrame.setVisible(true);
        });

        tradeButton.addActionListener(e -> {
            frame.setVisible(false);
            TradeGUI_Main d = new TradeGUI_Main(b,tm,um,iv,frame);
            d.run();
        });
    }
}
