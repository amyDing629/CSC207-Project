package User.GUI;

import User.Adapter.ApprovalController;
import User.Adapter.IUserController;

import javax.swing.*;
import java.awt.*;

public class RequestUnfreezeTicketGUI {
    ApprovalController ac;
    IUserController uc;
    JFrame pFrame;
    JFrame frame;
    /**
     * [Constructor]
     * @param pFrame frame
     * @param uc client user controller
     */
    public RequestUnfreezeTicketGUI(IUserController uc ,JFrame pFrame) {
        this.uc=uc;
        this.pFrame=pFrame;
        ac = new ApprovalController();
    }
    /**
     * @param name  name of user
     * create new frame
     */
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

    /**
     * @param frame new frame
     * @param panel the new panel
     * set new frame
     */
    private void placeComponents(JFrame frame, JPanel panel, String name){

        JLabel textLabel = new JLabel("Please enter the reasons to unfreeze below");
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
            if(!ac.hasUserApproval(name)) {
                ac.addApprovals(name, userInput.getText());
                uc.addAction(name, "Freeze ticket", "");
                JOptionPane.showMessageDialog(null,"Request successfully");
                JOptionPane.showMessageDialog(null,"Please wait for the admin to approve");
            }
            else {
                JOptionPane.showMessageDialog(null, "Requsted, cannot request again");
            }
        });
    }
}
