package User.GUI;

import Trade.TradeManager;
import User.Adapter.ClientUserController;
import User.Adapter.IUserPresenter;
import User.Adapter.UIController;
import User.UseCase.ApprovalManager;
import User.UseCase.UserManager;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

public class ClientUserGUI implements View {
    UserManager um;
    TradeManager tm;
//    Inventory iv;
//    AdminActivityManager aam;
//    public ClientUserGUI(UserManager um, TradeManager tm, Inventory iv, ItemApprovalManager iam, AdminActivityManager aam,UIController uc ,JFrame pFrame) {
//        this.um = um;
//        this.tm = tm;
//        this.iam=iam;
//        this.uc=uc;
//        this.iv=iv;
//        this.aam=aam;
//        this.pFrame=pFrame;
//    }

    JFrame pFrame; // previous frame
    JFrame frame; // this frame

    IUserPresenter presenter;
    ClientUserController controller;

    public ClientUserGUI(JFrame pFrame) {
        this.pFrame = pFrame;
    }

    public void run() {
        frame = new JFrame("Login Success");
        frame.setSize(500, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel welcomeLabel = new JLabel("Welcome: " + controller.getNameByID(getPresenter().getCurrUser()));
        welcomeLabel.setPreferredSize(new Dimension(300, 30));
        panel.add(welcomeLabel);
        frame.add(panel);

        placeComponents(frame, panel);
        frame.setVisible(true);
    }

    private void placeComponents(JFrame frame, JPanel panel) {

        UUID userId = getPresenter().getCurrUser();
        boolean isFrozen = getPresenter().getUserModel().getIsFrozen(userId);



        JLabel freezeStatus = new JLabel("Freeze Status: " + isFrozen);
        freezeStatus.setPreferredSize(new Dimension(300, 30));
        panel.add(freezeStatus);

        JLabel tradeLimit = new JLabel("Trade limit: " + tm.getTradeNumber(um.UUIDToName(userId)) + "/" + um.getWeekTransactionLimit(um.UUIDToName(userId)));
        tradeLimit.setPreferredSize(new Dimension(300, 30));
        panel.add(tradeLimit);

        JLabel inCompLimit = new JLabel("Incomplete trade limit: " + tm.getIncompleteTransaction(userId) +
                "/" + um.getIncompleteTransactionLimit(um.UUIDToName(userId)));
        inCompLimit.setPreferredSize(new Dimension(300, 30));
        panel.add(inCompLimit);

        JLabel diff = new JLabel("Difference between borrow and lend: "+ um.readDiff(um.UUIDToName(userId)) + "/" +um.getDiff(um.UUIDToName(userId)));
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
//            editInfoGUI d = new editInfoGUI(um,tm,iv,iam,uc,aam,frame);
//            d.run(um.getUsername(b));
        });

        inventoryButton.addActionListener(e -> {
            frame.setVisible(false);
//            InventoryGUI d = new InventoryGUI(b,iv,um,iam,frame);
//            d.run();
        });

        exitButton.addActionListener(e -> {
            frame.setVisible(false);
            pFrame.setVisible(true);
        });

        tradeButton.addActionListener(e -> {
            frame.setVisible(false);
//            TradeGUI_Main d = new TradeGUI_Main(b, tm, um, iv, frame);
//            d.run();
        });
    }

    @Override
    public IUserPresenter getPresenter() {
        return null;
    }

    @Override
    public void setPresenter(IUserPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void updateUIComponent() {

    }
}
