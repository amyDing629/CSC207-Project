package Inventory;

import Trade.BorderGUIBuilder;
import Trade.CompleteTradeGUIBuilder;
import Trade.TradeGUIEngineer;
import Trade.TradeGUIPlan;
import User.Entity.ClientUser;
import User.UseCase.UserManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

public class InventoryGUI {
    private final String currUser;
    private final JFrame ivf;
    private final UserManager um;


    public InventoryGUI(String currUser, JFrame frame){
        this.currUser = currUser;
        this.ivf = frame;
        um = new UserManager();

    };

    public void run(){
        JFrame frame = new JFrame("Inventory Session");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(200, 200);

        JPanel menu = new JPanel();
        JLabel label = new JLabel("    Hello, "+ currUser);
        JButton mk = new JButton("Market");
        JButton wb = new JButton("WishList - Borrow");
        JButton wl = new JButton("WishList - Lend");
        JButton ar = new JButton("Agree Request (Admin Only)");
        JButton back = new JButton("Return");

        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        menu.add(label);
        menu.add(mk);
        menu.add(wb);
        menu.add(wl);
        if (um.getUser(currUser).getIsAdmin()){
            menu.add(ar);
        }
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

        ar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                BorderGUIBuilder builder = new AgreeReqGUIBuilder(currUser, frame);
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
