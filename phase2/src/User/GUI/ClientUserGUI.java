package User.GUI;

import Inventory.InventoryGUI;
import Trade.*;
import User.Adapter.ClientUserController;
import User.Adapter.IUserPresenter;
import User.Adapter.UIController;
import User.PointSystem.PointGUIBuilder;
import User.UseCase.ApprovalManager;
import User.UseCase.UserManager;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

public class ClientUserGUI implements View {
//    UserManager um;
    TradeManager tm;
    UIController uc;
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
        String b = controller.getNameByID(getPresenter().getCurrUser());
        JLabel welcomeLabel = new JLabel("Welcome: " + controller.getNameByID(getPresenter().getCurrUser()));
        welcomeLabel.setPreferredSize(new Dimension(300, 30));
        panel.add(welcomeLabel);
        frame.add(panel);

        placeComponents(frame, panel, b);
        frame.setVisible(true);
    }

    private void placeComponents(JFrame frame, JPanel panel, String b ) {

        UUID userId = getPresenter().getCurrUser();
        boolean isFrozen = getPresenter().getUserModel().getIsFrozen(userId);



        JLabel freezeStatus = new JLabel("Freeze Status: " + isFrozen);
        freezeStatus.setPreferredSize(new Dimension(300, 30));
        panel.add(freezeStatus);

        JLabel tradeLimit = new JLabel("Trade limit: " + tm.getTradeNumber(uc.UUIDToName(userId)) + "/" + uc.getWeekTransactionLimit(uc.UUIDToName(userId)));
        tradeLimit.setPreferredSize(new Dimension(300, 30));
        panel.add(tradeLimit);

        JLabel inCompLimit = new JLabel("Incomplete trade limit: " + tm.getIncompleteTransaction(userId) +
                "/" + uc.getIncompleteTransactionLimit(uc.UUIDToName(userId)));
        inCompLimit.setPreferredSize(new Dimension(300, 30));
        panel.add(inCompLimit);

        JLabel diff = new JLabel("Difference between borrow and lend: "+ uc.readDiff(uc.UUIDToName(userId)) + "/" +uc.getDiff(uc.UUIDToName(userId)));
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

        JButton pointButton = new JButton("point system");
        inventoryButton.setPreferredSize(new Dimension(300, 30));
        panel.add(inventoryButton);


        JButton exitButton = new JButton("quit to menu");
        exitButton.setPreferredSize(new Dimension(300, 30));
        panel.add(exitButton);

        editButton.addActionListener(e -> {
            frame.setVisible(false);
            editInfoGUI d = new editInfoGUI(controller, frame);
            d.run(b);
        });

        pointButton.addActionListener(e -> {
            frame.setVisible(false);
            BorderGUIBuilder builder = new PointGUIBuilder(b, frame);
            TradeGUIEngineer engineer = new TradeGUIEngineer(builder);
            engineer.constructGUI();
            TradeGUIPlan tg = engineer.getGUI();
            tg.run();
        });

        inventoryButton.addActionListener(e -> {
            frame.setVisible(false);
            InventoryGUI d = new InventoryGUI(b ,frame);
            d.run();
        });

        exitButton.addActionListener(e -> {
            frame.setVisible(false);
            pFrame.setVisible(true);
        });

        tradeButton.addActionListener(e -> {
            frame.setVisible(false);
            TradeGUI_Main d = new TradeGUI_Main(b, frame);
            d.run();
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
