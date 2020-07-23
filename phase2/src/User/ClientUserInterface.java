package User;

import javax.swing.*;
import java.awt.*;

public class ClientUserInterface {
    public void run(){
        JFrame frame = new JFrame("Login Success");
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.add(panel);

        placeComponents(frame, panel);
        frame.setVisible(true);
    }

    private static void placeComponents(JFrame frame, JPanel panel){

        JButton editButton = new JButton("Edit Information");
        editButton.setPreferredSize(new Dimension(200, 30));
        panel.add(editButton);

        JButton tradeButton = new JButton("Trade");
        tradeButton.setPreferredSize(new Dimension(200, 30));
        panel.add(tradeButton);

        JButton inventoryButton = new JButton("Inventory");
        inventoryButton.setPreferredSize(new Dimension(200, 30));
        panel.add(inventoryButton);

        JButton marketButton = new JButton("Market");
        marketButton.setPreferredSize(new Dimension(200, 30));
        panel.add(marketButton);

        JButton exitButton = new JButton("quit to menu");
        exitButton.setPreferredSize(new Dimension(200, 30));
        panel.add(exitButton);
    }
}
