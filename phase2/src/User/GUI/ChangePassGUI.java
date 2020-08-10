package User.GUI;

import User.Adapter.UIController;
import User.Entity.ClientUser;

import javax.swing.*;
import java.awt.*;

public class ChangePassGUI {
    UIController uc;
    JFrame pFrame;
    JFrame frame;
    public ChangePassGUI(UIController uc ,JFrame pFrame) {
        this.uc = uc;
        this.pFrame=pFrame;
    }
    public void run(String name){
        frame = new JFrame("Change your password");
        frame.setSize(500, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel welcomeLabel = new JLabel("Welcome: " + name);
        welcomeLabel.setPreferredSize(new Dimension(300, 30));
        panel.add(welcomeLabel);
        frame.add(panel);

        ClientUser b = uc.getUser(name);

        placeComponents(frame, panel, b);
        frame.setVisible(true);
    }

    private void placeComponents(JFrame frame, JPanel panel, ClientUser b){
        JLabel curPass = new JLabel("Current password:"+ uc.getPassword(b.getUsername()));
        curPass.setPreferredSize(new Dimension(300, 30));
        panel.add(curPass);
        JLabel nameLabel = new JLabel("Change your password");
        nameLabel.setPreferredSize(new Dimension(300, 30));
        panel.add(nameLabel);

        JTextField passInput = new JTextField(30);
        passInput.setPreferredSize(new Dimension(300, 30));
        panel.add(passInput);

        JButton submitButton = new JButton("submit");
        submitButton.setPreferredSize(new Dimension(300, 30));
        panel.add(submitButton);

        JButton exit = new JButton("exit");
        exit.setPreferredSize(new Dimension(300, 30));
        panel.add(exit);
        exit.addActionListener(e -> {
            frame.setVisible(false);
            pFrame.setVisible(true);
        });
        submitButton.addActionListener(e -> {
            frame.setVisible(false);
            uc.setPassword(b.getUsername(),passInput.getText());
            JOptionPane.showMessageDialog(null, "success changed pass");
        });
    }
}
