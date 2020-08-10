//package User.GUI;
//
//import User.Adapter.ClientUserController;
//import User.Adapter.ClientUserPresenter;
//import User.Adapter.IUserPresenter;
//
//import javax.swing.*;
//import java.awt.*;
//import java.io.FileNotFoundException;
//import java.util.Arrays;
//import java.util.UUID;
//
//public class LoginIGUI implements View {
////    UserManager um;
////    TradeManager tm;
////    ItemApprovalManager iam;
////    UIController uc;
////    Inventory iv;
////    AdminActivityManager aam;
////    public LoginIGUI(UserManager um, TradeManager tm, Inventory iv, ItemApprovalManager iam, UIController uc, AdminActivityManager aam) {
////        this.um = um;
////        this.tm = tm;
////        this.iam = iam;
////        this.uc = uc;
////        this.iv = iv;
////        this.aam = aam;
////    }
//
//    JFrame frame;
//
//    // presenter
//    IUserPresenter presenter = new ClientUserPresenter();
//
//    public void run() {
//        frame = new JFrame("Login/Register");
//        frame.setSize(330, 200);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        JPanel panel = new JPanel();
//        frame.add(panel);
//        placeComponents(frame, panel);
//        frame.setVisible(true);
//    }
//
//    private void placeComponents(JFrame frame, JPanel panel){
//        JLabel userLabel = new JLabel("User:");
//        userLabel.setBounds(10,20,80,25);
//        panel.add(userLabel);
//
//        JTextField nameInput = new JTextField(20);
//        nameInput.setBounds(100,20,80,25);
//        panel.add(nameInput);
//
//        JLabel passwordLabel = new JLabel("Password:");
//        passwordLabel.setBounds(10,50,80,25);
//        panel.add(passwordLabel);
//
//        JPasswordField passwordInput = new JPasswordField(20);
//        passwordInput.setBounds(100,50,80,25);
//        panel.add(passwordInput);
//
//        JButton registerButton = new JButton("register");
//        registerButton.setBounds(190,80,80,25);
//        panel.add(registerButton);
//
//        JButton logInButton = new JButton("login");
//        logInButton.setBounds(100,80,80,25);
//        panel.add(logInButton);
//
//        JButton exploreButton=new JButton("explore");
//        JButton exitButton = new JButton("quit");
//        exitButton.setPreferredSize(new Dimension(300, 30));
//        panel.add(exploreButton);
//        panel.add(exitButton);
//
//
//        logInButton.addActionListener(e -> {
//            String name = nameInput.getText();
//            String password = Arrays.toString(passwordInput.getPassword());
////            boolean response = getPresenter().login(name, password);
//            boolean response = getPresenter().getUserModel().verifyUser(name, password);
//            if (!response)
//                JOptionPane.showMessageDialog(null, "invalid user");
//            else {
//                frame.setVisible(false);
//
//                ClientUserController controller = new ClientUserController();
//                UUID uuid = controller.getIDbyName(name);
//                ClientUserPresenter clientUserPresenter = new ClientUserPresenter(uuid, this);
//                clientUserPresenter.run();
//
//                ClientUserGUI a = new ClientUserGUI(frame);
//                a.run();
//            }
//        });
//
//        registerButton.addActionListener(e -> {
//            String name = nameInput.getText();
//            String password = Arrays.toString(passwordInput.getPassword());
////            getPresenter().register(name, password);
//            try {
//                getPresenter().getUserModel().createClientUser(name, password, false);
//            } catch (FileNotFoundException fileNotFoundException) {
//                fileNotFoundException.printStackTrace();
//            }
//            JOptionPane.showMessageDialog(null, "success");
//        });
//
//
//        exploreButton.addActionListener(e -> {
////            getPresenter().explore();
//        });
//
//        exitButton.addActionListener(e -> {
//
//            // TODO: only access controller or presenter, allow use case to use gateway
////            DataAccessFull adf = new DataAccessFull(um, tm, iv, iam);
////            try {
////                adf.updateFile();
////            } catch (IOException ioException) {
////                ioException.printStackTrace();
////            }
//            frame.setVisible(false);
//        });
//
//    }
//
//    public IUserPresenter getPresenter() {
//        return presenter;
//    }
//
//    public void setPresenter(IUserPresenter presenter) {
//        this.presenter = presenter;
//    }
//
//    @Override
//    public void updateUIComponent() {
//
//    }
//}
