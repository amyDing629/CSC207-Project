package User.GUI;

import Inventory.Adaptor.InventoryGUI;
import Trade.Adaptor.BorderGUIBuilder;
import Trade.Adaptor.BorderGUIEngineer;
import Trade.Adaptor.GUIPlan;
import Trade.Adaptor.TradeGUI_Main;
import Trade.UseCase.TradeManager;
import User.Adapter.ClientUserController;
import User.Adapter.IUserPresenter;
import User.PointSystem.PointGUIBuilder;
import User.UseCase.UserManager;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

public class ClientUserGUI implements View {
    UserManager um = new UserManager();
    TradeManager tm = new TradeManager();

    JFrame pFrame; // previous frame
    JFrame frame;// this frame
    String currUser;

    IUserPresenter presenter;
    ClientUserController controller = new ClientUserController();

    public ClientUserGUI(JFrame pFrame, String currUser) {
        this.pFrame = pFrame;
        this.currUser = currUser;
    }

    public void run() {
        frame = new JFrame("Login Success");
        frame.setSize(500, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel welcomeLabel = new JLabel("Welcome: " + currUser);
        welcomeLabel.setPreferredSize(new Dimension(300, 30));
        panel.add(welcomeLabel);
        frame.add(panel);

        placeComponents(frame, panel, currUser);
        frame.setVisible(true);
    }

    private void placeComponents(JFrame frame, JPanel panel, String b) {

        UUID userId = um.nameToUUID(b);
        boolean isFrozen = um.getIsFrozen(userId);


        System.out.println("CUGUI indicator");
        JLabel freezeStatus = new JLabel("Freeze Status: " + isFrozen);
        freezeStatus.setPreferredSize(new Dimension(300, 30));
        panel.add(freezeStatus);

        JLabel tradeLimit = new JLabel("Trade limit: " + tm.getTradeNumber(um.UUIDToName(userId)) + "/" +
                um.getWeekTransactionLimit(um.UUIDToName(userId)));
        tradeLimit.setPreferredSize(new Dimension(300, 30));
        panel.add(tradeLimit);

        JLabel inCompLimit = new JLabel("Incomplete trade limit: " + tm.getIncompleteTransaction(userId) +
                "/" + um.getIncompleteTransactionLimit(um.UUIDToName(userId)));
        inCompLimit.setPreferredSize(new Dimension(300, 30));
        panel.add(inCompLimit);

        JLabel diff = new JLabel("Difference between borrow and lend: "+ um.readDiff(um.UUIDToName(userId)) +
                "/" +um.getDiff(um.UUIDToName(userId)));
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

        JButton pointButton = new JButton("Redeem Points");
        pointButton.setToolTipText("Redeem points for free trades! (The redeemed trade will not count to trade limit!)");
        pointButton.setPreferredSize(new Dimension(300, 30));
        panel.add(pointButton);

        JButton exitButton = new JButton("Log out");
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
            BorderGUIEngineer engineer = new BorderGUIEngineer(builder);
            engineer.constructGUI();
            GUIPlan tg = engineer.getGUI();
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
