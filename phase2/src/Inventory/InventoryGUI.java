package Inventory;

import User.ClientUser;
import User.ItemApprovalManager;
import User.UserManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryGUI {
    private final ClientUser currUser;
    private final UserManager um;
    private final Inventory iv;
    private final JFrame ivf;
    private final ItemApprovalManager iam;


    public InventoryGUI(ClientUser currUser, Inventory iv, UserManager um, ItemApprovalManager iam, JFrame frame){
        this.currUser = currUser;
        this.um = um;
        this.iv = iv;
        this.ivf = frame;
        this.iam = iam;

    };

    public void run(){
        JFrame frame = new JFrame("Inventory Session");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(200, 200);

        JPanel menu = new JPanel();
        JLabel label = new JLabel("    Hello, "+um.getUsername(currUser));
        JButton wb = new JButton("WishBorrow");
        JButton wl = new JButton("WishLend");
        JButton back = new JButton("return");

        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        menu.add(label);
        menu.add(wb);
        menu.add(wl);
        menu.add(back);

        frame.getContentPane().add(menu);
        frame.setVisible(true);

        wb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new WishBorrowGUI(currUser, iv, um, iam, frame).run();
            }
        });

        wl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new WishLendGUI(currUser, iv, um, iam, frame).run();

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
