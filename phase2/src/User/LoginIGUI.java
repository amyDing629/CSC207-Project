package User;

import Inventory.Inventory;
import Main.DataAccessFull;
import Main.UI.UIcontoller;
import Trade.TradeManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;

public class LoginIGUI implements View {
    UserManager um;
    TradeManager tm;
    ItemApprovalManager iam;
    UIcontoller uc;
    Inventory iv;
    AdminActivityManager aam;

    JFrame frame;


    // presenter
    ILoginSystemBoundary presenter;

    public LoginIGUI() {
        run();
    }

    public LoginIGUI(UserManager um, TradeManager tm, Inventory iv, ItemApprovalManager iam, UIcontoller uc, AdminActivityManager aam) {
        this.um = um;
        this.tm = tm;
        this.iam = iam;
        this.uc = uc;
        this.iv = iv;
        this.aam = aam;
    }

    public void run(){
        frame = new JFrame("Login/Register");
        frame.setSize(330, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(frame, panel);
        frame.setVisible(true);
    }

    private void placeComponents(JFrame frame, JPanel panel){
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

        JButton exploreButton=new JButton("explore");
        JButton exitButton = new JButton("quit");
        exitButton.setPreferredSize(new Dimension(300, 30));
        panel.add(exploreButton);
        panel.add(exitButton);


        logInButton.addActionListener(e -> {
            String name = nameInput.getText();
            String password = Arrays.toString(passwordInput.getPassword());
            boolean response = getPresenter().login(name, password);
            if (!response)
                JOptionPane.showMessageDialog(null, "invalid user");
            else {
                frame.setVisible(false);
                ClientUserGUI a = new ClientUserGUI(um, tm, iv, iam, aam, uc, frame);
                a.run(name);
            }
        });

        registerButton.addActionListener(e -> {
            String name = nameInput.getText();
            String password = Arrays.toString(passwordInput.getPassword());
            getPresenter().register(name, password);
            JOptionPane.showMessageDialog(null, "success");
        });


        exploreButton.addActionListener(e -> {
            getPresenter().explore();
        });

        exitButton.addActionListener(e -> {

            // TODO: only access controller or presenter, allow use case to use gateway
            DataAccessFull adf = new DataAccessFull(um, tm, iv, iam);
            try {
                adf.updateFile();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            frame.setVisible(false);
        });

    }

    public ILoginSystemBoundary getPresenter() {
        return presenter;
    }

    public void setPresenter(ILoginSystemBoundary presenter) {
        this.presenter = presenter;
    }

    @Override
    public void updateUIComponent() {

    }
}
