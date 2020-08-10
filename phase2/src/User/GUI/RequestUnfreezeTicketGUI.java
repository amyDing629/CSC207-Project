package User.GUI;

import User.Adapter.ClientUserController;
import User.UseCase.ApprovalManager;

import javax.swing.*;
import java.awt.*;

public class RequestUnfreezeTicketGUI {
    ApprovalManager iam;
    ClientUserController uc;
    JFrame pFrame;
    JFrame frame;
    public RequestUnfreezeTicketGUI(ClientUserController uc ,JFrame pFrame) {
        this.uc=uc;
        this.pFrame=pFrame;
    }
    public void run(String name){
        frame = new JFrame("Freeze User");
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

    private void placeComponents(JFrame frame, JPanel panel, String name){

        JLabel textLabel = new JLabel("Please enter the reasons to unfreeze below");
        textLabel.setPreferredSize(new Dimension(300, 30));
        panel.add(textLabel);


        JTextField userInput = new JTextField(30);
        userInput.setPreferredSize(new Dimension(300, 30));
        panel.add(userInput);

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
            iam.addApprovals(uc.getUser(name),textLabel.getText());
            JOptionPane.showMessageDialog(null,"Request successfully");
            JOptionPane.showMessageDialog(null,"Please wait for the administrator to approve");
            UserFreezeSystem d = new UserFreezeSystem(uc,frame);
            d.run(name);
        });
    }
}
