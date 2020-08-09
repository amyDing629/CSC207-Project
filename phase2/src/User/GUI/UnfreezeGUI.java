package User.GUI;

import Inventory.Inventory;
import User.Adapter.AdminController;
import User.Adapter.UIController;
import Trade.TradeManager;
import User.UseCase.AdminActivityManager;
import User.UseCase.ApprovalManager;
import User.UseCase.UserManager;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class UnfreezeGUI {
    UserManager um;
    ApprovalManager iam;
    UIController uc;
//    AdminActivityManager aam;
    AdminController ac;
    JFrame pFrame;
    JFrame frame;
    public UnfreezeGUI(UIController uc ,JFrame pFrame) {
        this.uc=uc;
        this.pFrame=pFrame;
    }
    public void run(String name){
        frame = new JFrame("UnFreeze User");
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

        JTextArea textArea = new JTextArea(5, 20);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        panel.add(scrollPane, c);
        iam.addApproval("2","admin5","hahahaha");

        StringBuilder hi= new StringBuilder();
        ArrayList<ArrayList<String>> usa = iam.getUserApproval();
        if(usa.size()==0){
            hi.append("Currently there is no unfreeze ticket\n");
        }
        for (int i = 0; i < usa.size(); i++) {
            hi.append("User").append(i).append(": ").append(usa.get(i).get(1)).append("\n");
            hi.append("Reason: ").append(usa.get(i).get(2)).append("\n");
            hi.append("**************************").append("\n");
        }
        System.out.println(hi.toString());
        textArea.setText(hi.toString());
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
            if(um.getUser(userInput.getText())!=null){
                iam.removeUser(userInput.getText());
                ac.setFreeze(userInput.getText(),false);
                JOptionPane.showMessageDialog(null,"Unfreeze successfully");
                UserFreezeSystem d = new UserFreezeSystem(uc,frame);
                d.run(b);
            }
            else{
                JOptionPane.showMessageDialog(null, "invalid user");
            }
        });
    }
}
