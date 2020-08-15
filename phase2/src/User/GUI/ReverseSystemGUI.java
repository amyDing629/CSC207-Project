package User.GUI;


import User.Adapter.IUserController;
import User.Entity.ClientUser;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ReverseSystemGUI {
    IUserController uc;
    JFrame pFrame;
    JFrame frame;
    /**
     * [Constructor]
     * @param pFrame frame
     * @param uc client user controller
     */
    public ReverseSystemGUI(IUserController uc ,JFrame pFrame) {
        this.uc=uc;
        this.pFrame=pFrame;
    }
    /**
     * @param name  name of user
     * create new frame
     */
    public void run(String name){
        frame = new JFrame("Reverse System");
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
        StringBuilder hi= new StringBuilder("Username:\n");
        ArrayList<ClientUser> name= (ArrayList<ClientUser>) uc.getUserList();
        if(name.size()==0){
            hi.append("No users currently\n");
        }
        for (ClientUser user : name) {
            hi.append(user.getUsername()).append("\n");
        }
        System.out.println(hi.toString());
        textArea.setText(hi.toString());
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
            if(uc.getUser(userInput.getText())!=null){
                createNext(frame,panel,uc.getUser(userInput.getText()),textArea,exit);
            }
        });
    }
    /**
     * @param panel the new panel
     * @param textArea text area
     * @param exit the exit button
     * set new frame
     */
    public  void createNext(JFrame frame, JPanel panel,ClientUser a,JTextArea textArea,JButton exit){
        JTextArea textArea1 = new JTextArea(5, 20);
        textArea1.setEditable(false);
        JScrollPane scrollPane1 = new JScrollPane(textArea1);
        GridBagConstraints c1 = new GridBagConstraints();
        c1.gridwidth = GridBagConstraints.REMAINDER;
        c1.fill = GridBagConstraints.HORIZONTAL;

        c1.fill = GridBagConstraints.BOTH;
        c1.weightx = 1.0;
        c1.weighty = 1.0;
        panel.add(scrollPane1, c1);
        panel.remove(exit);
        if(!textArea.getText().equals("Currently there is no users")){
            StringBuilder lol= new StringBuilder("Actions: ");
            ArrayList<ArrayList<String>> z=uc.getActions(a.getUsername());
            for (ArrayList<String> strings : z) {
                lol.append(strings.get(1)).append("\n");
            }
            textArea1.setText(lol.toString());
        }else{
            textArea1.setEnabled(false);
        }
        panel.add(textArea1);
        panel.add(exit);
    }
}
