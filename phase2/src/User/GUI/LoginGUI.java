package User.GUI;

import Inventory.Adaptor.MarketBuilder;
import Main.DataAccessFull;
import Trade.Adaptor.BorderGUIBuilder;
import Trade.Adaptor.TradeGUIEngineer;
import Trade.Adaptor.TradeGUIPlan;
import User.Adapter.ClientUserController;
import User.Adapter.ClientUserPresenter;
import User.Adapter.LoginSystemPresenter;
import User.Entity.ClientUser;
import User.UseCase.UserManager;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

public class LoginGUI{

    JFrame frame;
    LoginSystemPresenter lsp;
    // presenter

    public void run() {
        lsp = new LoginSystemPresenter(frame);
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

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(190,80,80,25);
        panel.add(registerButton);

        JButton logInButton = new JButton("Login");
        logInButton.setBounds(100,80,80,25);
        panel.add(logInButton);

        JButton exploreButton = new JButton("Explore");
        exploreButton.setToolTipText("Mandatory 3: Explore the market as a visitor");
        panel.add(exploreButton);

        JButton exitButton = new JButton("Quit");
        exitButton.setPreferredSize(new Dimension(300, 30));
        panel.add(exitButton);
        UserManager um=new UserManager();

        logInButton.addActionListener(e -> {
            String name = nameInput.getText();
            String password = new String(passwordInput.getPassword());
            boolean response = lsp.login(name, password);
            System.out.println(response);
            //boolean response = lsp.getUserModel().verifyUser(name, password);
            if (!response)
                JOptionPane.showMessageDialog(null,
                        "Invalid Login! Please check the username and/or password!");
            else {
                frame.setVisible(false);

                ClientUserController controller = new ClientUserController();
                UUID uuid = controller.getIDbyName(name);
                ClientUserPresenter clientUserPresenter = new ClientUserPresenter(uuid, frame);
                clientUserPresenter.run();

                ClientUserGUI a = new ClientUserGUI(frame, name);
                a.run();
            }
        });

        registerButton.addActionListener(e -> {
            String name = nameInput.getText();
            String password = new String(passwordInput.getPassword());
            if(lsp.register(name, password)){
                JOptionPane.showMessageDialog(null, "Success");
            }
            else{
                JOptionPane.showMessageDialog(null,
                        "This username has already been registered! Please register with a new one!");
            }

            // TODO: delete
            //lsp.getUserModel().createClientUser(name, password, false);
            for(ClientUser i:um.getUserList()){
                System.out.println(i.toString());
            }
        });


        exploreButton.addActionListener(e -> {
            frame.setVisible(false);
            BorderGUIBuilder builder = new MarketBuilder(frame);
            TradeGUIEngineer engineer = new TradeGUIEngineer(builder);
            engineer.constructGUI();
            TradeGUIPlan tg = engineer.getGUI();
            tg.run();
        });

        exitButton.addActionListener(e -> {
            frame.setVisible(false);
            DataAccessFull adf = new DataAccessFull();
            adf.updateFile();

        });



    }

}
