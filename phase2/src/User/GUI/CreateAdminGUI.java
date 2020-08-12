package User.GUI;

import User.Adapter.AdminController;
import User.Adapter.ClientUserController;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class CreateAdminGUI {
    ClientUserController uc;
    AdminController ac;
    JFrame pFrame;
    JFrame frame;
    public CreateAdminGUI(ClientUserController uc , JFrame pFrame) {
        this.uc=uc;
        ac= new AdminController();
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
            if(userInput1.getText().equals("")|| uc.checkUser(userInput.getText())) {

               /* System.out.println("--------------------");
                System.out.println(userInput.getText());
                System.out.println(userInput1.getText());
                System.out.println(userInput1.getText().equals(""));
                System.out.println(uc.checkUser(userInput.getText()));
                System.out.println("--------------------");
                ac.createAdmin(userInput.getText(), userInput1.getText());
                */
                JOptionPane.showMessageDialog(null, "Success created admin");
            }
            else{
                String error="";
                if(userInput1.getText().equals("")){
                    error+="Password can't not be empty";
                }
                if(!error.equals("")){
                    error+=" and ";
                }
                if(!uc.checkUser(userInput.getText())){
                    error+="username already exist";
                }
                JOptionPane.showMessageDialog(null, error);
            }
        });
    }
}
