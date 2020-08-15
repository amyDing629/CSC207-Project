package User.GUI;

import User.Adapter.AdminController;
import User.Adapter.ClientUserController;
import User.Adapter.IAdminController;
import User.Adapter.IUserController;

import javax.swing.*;
import java.awt.*;

public class ExStandardGUI {

    IUserController uc;
    IAdminController ac;
    JFrame pFrame;
    JFrame frame;

    /**
     * [Constructor]
     * @param pFrame frame
     * @param uc client user controller
     */
    public ExStandardGUI(IUserController uc ,JFrame pFrame) {
        this.uc=uc;
        this.pFrame=pFrame;
        this.ac = new AdminController();
    }

    /**
     * @param name name of user
     * create new frame
     */
    public void run(String name) {
        frame = new JFrame("Redeem Standard");
        frame.setSize(500, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel welcomeLabel = new JLabel("Welcome: " + name);
        welcomeLabel.setPreferredSize(new Dimension(300, 30));
        panel.add(welcomeLabel);
        frame.add(panel);

        placeComponents(frame, panel, name);
        frame.setVisible(true);
    }

    /**
     * @param frame new frame
     * @param panel the new panel
     * @param b name of user
     * set new frame
     */
    public void placeComponents(JFrame frame, JPanel panel, String b){

        JLabel textLabel = new JLabel("Please enter points needed for 1 bonus trade");
        textLabel.setPreferredSize(new Dimension(300, 30));
        panel.add(textLabel);

        JTextField userInput = new JTextField(30);
        userInput.setPreferredSize(new Dimension(300, 30));
        panel.add(userInput);

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
            frame.setVisible(false);
            ac.setExchangeStandard(Integer.parseInt(userInput.getText()));
            JOptionPane.showMessageDialog(null, "standard change succeed");
            UserFreezeSystem d = new UserFreezeSystem(uc,frame);
            d.run(b);
        });
    }
}
