package User;

import Trade.TradeManager;

import javax.swing.*;
import java.awt.*;

public class ClientUserInterface {
    UserManager a = new UserManager();
    TradeManager c = new TradeManager();
    public void run(String name){
        JFrame frame = new JFrame("Login Success");
        frame.setSize(500, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel welcomeLabel = new JLabel("Welcome: " + name);
        welcomeLabel.setPreferredSize(new Dimension(300, 30));
        panel.add(welcomeLabel);
        frame.add(panel);

        ClientUser b = a.getUser(name);

        placeComponents(frame, panel, b);
        frame.setVisible(true);
    }

    private void placeComponents(JFrame frame, JPanel panel, ClientUser b){

        JLabel freezeStatus = new JLabel("Freeze Status: "+ a.getIsFrozen(b));
        freezeStatus.setPreferredSize(new Dimension(300, 30));
        panel.add(freezeStatus);

        JLabel tradeLimit = new JLabel("Trade limit: "+ c.getTradeNumber(b) + "/" + a.getWeekTransactionLimit(b));
        tradeLimit.setPreferredSize(new Dimension(300, 30));
        panel.add(tradeLimit);

        JLabel inCompLimit = new JLabel("Incomplete trade limit: "+ c.getIncompleteTransaction(b) +
                "/" + a.getIncompleteTransactionLimit(b));
        inCompLimit.setPreferredSize(new Dimension(300, 30));
        panel.add(inCompLimit);

        JLabel diff = new JLabel("Difference between borrow and lend: "+ a.readDiff(b) + "/" + a.getDiff(b));
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

        JButton marketButton = new JButton("Market");
        marketButton.setPreferredSize(new Dimension(300, 30));
        panel.add(marketButton);

        JButton exitButton = new JButton("quit to menu");
        exitButton.setPreferredSize(new Dimension(300, 30));
        panel.add(exitButton);
    }
}
