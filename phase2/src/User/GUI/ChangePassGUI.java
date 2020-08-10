package User.GUI;

import User.Adapter.ClientUserController;
import User.Entity.ClientUser;

import javax.swing.*;
import java.awt.*;

public class ChangePassGUI {

    ClientUserController uc;
    JFrame pFrame;
    JFrame frame;
    String userName;

    public ChangePassGUI(ClientUserController uc , JFrame pFrame) {
        this.uc = uc;
        this.pFrame=pFrame;
    }
    public void run(String name){
        this.userName = name;

        frame = new JFrame("Change your password");
        frame.setSize(500, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel welcomeLabel = new JLabel("Welcome: " + name);
        welcomeLabel.setPreferredSize(new Dimension(300, 30));
        panel.add(welcomeLabel);
        frame.add(panel);

        placeComponents(frame, panel);
        frame.setVisible(true);
    }

    private void placeComponents(JFrame frame, JPanel panel){
        JLabel curPass = new JLabel("Current password:"+ uc.getPassword(getUserName()));
        curPass.setPreferredSize(new Dimension(300, 30));
        panel.add(curPass);
        JLabel nameLabel = new JLabel("Change your password");
        nameLabel.setPreferredSize(new Dimension(300, 30));
        panel.add(nameLabel);

        JTextField passInput = new JTextField(30);
        passInput.setPreferredSize(new Dimension(300, 30));
        panel.add(passInput);

        JButton submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(300, 30));
        panel.add(submitButton);

        JButton exit = new JButton("Back");
        exit.setPreferredSize(new Dimension(300, 30));
        panel.add(exit);
        exit.addActionListener(e -> {
            frame.setVisible(false);
            pFrame.setVisible(true);
        });
        submitButton.addActionListener(e -> {
//            System.out.println(b.getPassword());
            frame.setVisible(false);
//            System.out.println(passInput.getText());
            uc.setPassword(getUserName(), passInput.getText());
//            b.setPassword(passInput.getText());
//            System.out.println(b.getPassword());
            JOptionPane.showMessageDialog(null, "Successfully changed the password!");
        });
    }

    String getUserName() {
        return userName;
    }
}
