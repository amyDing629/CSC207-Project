package Inventory;

import Trade.BorderGUIBuilder;
import Trade.CompleteTradeGUIBuilder;
import Trade.TradeGUIEngineer;
import Trade.TradeGUIPlan;
import User.ClientUser;
import User.ItemApprovalManager;
import User.UserManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryGUI {
    private final ClientUser currUser;
    private final JFrame ivf;


    public InventoryGUI(ClientUser currUser, JFrame frame){
        this.currUser = currUser;
        this.ivf = frame;

    };

    public void run(){
        JFrame frame = new JFrame("Inventory Session");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(200, 200);

        JPanel menu = new JPanel();
        JLabel label = new JLabel("    Hello, "+ currUser.getUsername());
        JButton mk = new JButton("market");
        JButton wb = new JButton("WishBorrow");
        JButton wl = new JButton("WishLend");
        JButton back = new JButton("return");

        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        menu.add(label);
        menu.add(mk);
        menu.add(wb);
        menu.add(wl);
        menu.add(back);

        frame.getContentPane().add(menu);
        frame.setVisible(true);

        mk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                BorderGUIBuilder builder = new MarketBuilder(frame);
                TradeGUIEngineer engineer = new TradeGUIEngineer(builder);
                engineer.constructGUI();
                TradeGUIPlan tg = engineer.getGUI();
                tg.run();
            }
        });

        wb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                BorderGUIBuilder builder = new WishBorrowBuilder(currUser, frame);
                TradeGUIEngineer engineer = new TradeGUIEngineer(builder);
                engineer.constructGUI();
                TradeGUIPlan tg = engineer.getGUI();
                tg.run();
            }
        });

        wl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                BorderGUIBuilder builder = new WishLendBuilder(currUser, frame);
                TradeGUIEngineer engineer = new TradeGUIEngineer(builder);
                engineer.constructGUI();
                TradeGUIPlan tg = engineer.getGUI();
                tg.run();

            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                ivf.setVisible(true);
            }
        });
    }
}
