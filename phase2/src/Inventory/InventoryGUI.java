package Inventory;

import Trade.AcceptTradeGUI;
import Trade.SelectItemToTradeGUI;
import User.ClientUser;
import User.UserManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryGUI {
    private ClientUser currUser;
    private UserManager um;
    private Inventory iv;
    private JFrame ivf;


    public InventoryGUI(ClientUser currUser, Inventory iv, UserManager um, JFrame frame){
        this.currUser = currUser;
        this.um = um;
        this.iv = iv;
        this.ivf = frame;

    };

    public void run(){
        JFrame frame = new JFrame("Inventory Session");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 250);

        JPanel menu = new JPanel();
        JLabel label = new JLabel("    Hello, "+um.getUsername(currUser));
        JButton wb = new JButton("WishBorrow");
        JButton wl = new JButton("WishLend");
        JButton back = new JButton("return");

        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        menu.add(wb);
        menu.add(wl);

        frame.getContentPane().add(menu);
        frame.setVisible(true);

        wb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new WishBorrowGUI(currUser, iv, um, frame).run();
            }
        });

        wl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new WishLendGUI(currUser, iv, um, frame).run();

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
