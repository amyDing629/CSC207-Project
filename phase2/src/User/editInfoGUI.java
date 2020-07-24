package User;

import Trade.TradeManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class editInfoGUI {
    UserManager a = new UserManager();
    TradeManager c = new TradeManager();
    public void run(String name){
        JFrame frame = new JFrame("Edit User Information");
        frame.setSize(300, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel welcomeLabel = new JLabel("Hello, user, " + name);
        welcomeLabel.setPreferredSize(new Dimension(200, 30));
        panel.add(welcomeLabel);
        frame.add(panel);

        ClientUser b = a.getUser(name);

        placeComponents(frame, panel, b);
        frame.setVisible(true);
    }


    private void placeComponents(JFrame frame, JPanel panel, ClientUser b){

        JLabel isAdmin = new JLabel("Admin: "+ a.getIsAdmin(b));
        isAdmin.setPreferredSize(new Dimension(300, 30));
        panel.add(isAdmin);

        JLabel action = new JLabel("Actions: ");
        action.setPreferredSize(new Dimension(300, 30));
        panel.add(action);

        JButton changePass = new JButton("Change Password");
        changePass.setPreferredSize(new Dimension(300, 30));
        panel.add(changePass);

        JButton freezeSystem = new JButton("ClientUser Freeze System");
        freezeSystem.setPreferredSize(new Dimension(300, 30));
        panel.add(freezeSystem);

        JButton exit = new JButton("exit");
        exit.setPreferredSize(new Dimension(300, 30));
        panel.add(exit);

        exit.addActionListener(e -> {
            frame.setVisible(false);
            ClientUserGUI d = new ClientUserGUI();
            d.run(a.getUsername(b));
        });



    }
}
