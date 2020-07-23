package User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginInterface {
    public static void main(String[] args){
        JFrame frame = new JFrame("Login/Register");
        frame.setSize(330, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);

        placeComponents(frame, panel);
        frame.setVisible(true);
    }

    private static void placeComponents(JFrame frame, JPanel panel){
        JLabel userLabel = new JLabel("User:");
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        JTextField nameInput = new JTextField(20);
        nameInput.setBounds(100,20,80,25);
        panel.add(nameInput);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10,50,80,25);
        panel.add(passwordLabel);

        JPasswordField passwordInput = new JPasswordField(20);
        passwordInput.setBounds(100,50,80,25);
        panel.add(passwordInput);

        JButton registerButton = new JButton("register");
        registerButton.setBounds(190,80,80,25);
        panel.add(registerButton);

        JButton logInButton = new JButton("login");
        logInButton.setBounds(100,80,80,25);
        panel.add(logInButton);

        logInButton.addActionListener(e -> {
            String name = nameInput.getText();
            String password = passwordInput.getText();
            ILoginSystemBoundary presenter = new LoginSystemPresenter();
            boolean response = presenter.login(name, password);
            if(!response)
                JOptionPane.showMessageDialog(null, "invalid user");
            else{
                frame.setVisible(false);
            ClientUserInterface a = new ClientUserInterface();
            a.run(name);
            }
        });

        registerButton.addActionListener(e -> {
            String name = nameInput.getText();
            String password = passwordInput.getText();
            ILoginSystemBoundary presenter = new LoginSystemPresenter();
            presenter.register(name, password);
            JOptionPane.showMessageDialog(null, "success");
        });
    }
}
