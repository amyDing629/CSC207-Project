package Trade;

import Inventory.Inventory;
import User.ClientUser;
import User.ClientUserGUI;
import User.LoginIGUI;
import User.UserManager;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

public class TradeGUI_Main {
    ClientUser currUser;
    UserManager um;
    TradeManager tm;
    Inventory iv;


    /**
     * constructor
     * @param currUser the user that is using the system
     */
    public TradeGUI_Main(ClientUser currUser, TradeManager tm, UserManager um, Inventory iv){
        this.currUser = currUser;
        this.tm = tm;
        this.um = um;
        this.iv = iv;
    }

    public void run(){
        JFrame frame = new JFrame("Select Trade Session");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 250);

        JPanel menu = new JPanel();
        JLabel lb = new JLabel("    Hello, "+um.getUsername(currUser));
        JButton rt = new JButton("request trade");
        JButton ct = new JButton("complete trade");
        JButton at = new JButton("agree/refuse requested trade");
        JButton th = new JButton("view trade history(also frequent traders)");
        JButton re = new JButton("return to login menu");

        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        menu.add(lb, Component.CENTER_ALIGNMENT);
        menu.add(rt, Component.CENTER_ALIGNMENT);
        menu.add(at, Component.CENTER_ALIGNMENT);
        menu.add(ct, Component.CENTER_ALIGNMENT);
        menu.add(th, Component.CENTER_ALIGNMENT);
        menu.add(re, Component.CENTER_ALIGNMENT);

        frame.getContentPane().add(menu);
        frame.setVisible(true);

        rt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new SelectItemToTradeGUI(currUser, tm, um, iv, frame).run();
            }
        });

        at.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new AcceptTradeGUI(currUser, tm, um, frame).run();
            }
        });

        ct.addActionListener(new ActionListener() {
            @Override
            //to do
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new CompleteTradeGUI(currUser, tm, um, frame).run();
            }
        });

        th.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new TradeHistoryGUI(currUser, tm, um, frame).run();
            }
        });

        re.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                //need to connect back to ClientGUI
            }
        });








        
    }








}
