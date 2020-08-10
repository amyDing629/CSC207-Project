package User.GUI;

import User.Adapter.AdminController;
import User.Adapter.UIController;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class CreateAdminGUI {
    UIController uc;
    AdminController ac;
    JFrame pFrame;
    JFrame frame;
    public CreateAdminGUI(UIController uc ,JFrame pFrame) {
        this.uc=uc;
        this.pFrame=pFrame;
    }
    public void run(String name){
        frame = new JFrame("Create Admin Session");
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

    private void placeComponents(JFrame frame, JPanel panel, String b){

        JLabel textLabel = new JLabel("Please enter the user's username below");
        textLabel.setPreferredSize(new Dimension(300, 30));
        panel.add(textLabel);


        JTextField userInput = new JTextField(30);
        userInput.setPreferredSize(new Dimension(300, 30));
        panel.add(userInput);

        JTextField userInput1 = new JTextField(30);
        userInput1.setPreferredSize(new Dimension(300, 30));
        panel.add(userInput1);


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
            try {
                ac.createAdmin(userInput.getText(),userInput1.getText());
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "successfully created");
            UserFreezeSystem d = new UserFreezeSystem(uc,frame);
            d.run(b);
        });
    }
}
