package MeetingSystem.Adapter;

import com.sun.javafx.binding.StringFormatter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpView {
    private JFrame frame;
    private JButton backButton;
    private JTextArea textArea1;
    private JPanel backPanel;
    private JPanel contentPane;

    // presenter
    IPresenter presenter;

    public HelpView() {
        initComponents();
        backButton.addActionListener(e -> {
            frame.dispose();
        });
    }

    private void initComponents() {
        frame = new JFrame("Help");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        backPanel.add(backButton);
        contentPane.add(textArea1);

        frame.getContentPane().add(backPanel);
        frame.getContentPane().add(contentPane);
        frame.setVisible(true);
    }

    private void createUIComponents() {
        textArea1 = new JTextArea();
        textArea1.setEditable(false);
        textArea1.setText(getInstruction());
    }

    private String getInstruction() {
        return "    Instructions for Meeting System\n" +
                "1. Setup/Edit input: \n" +
                "       - Time: (yyyy-MM-dd hh:mm) only future time allowed; must be valid time input\n" +
                "       - Place: valid string input\n" +
                "2. Edit input: \n" +
                "       - as in 1" +
                "       - content to submit must be edited\n" +
                "3. Once the meeting sets up, the two traders can only make actions (i.e. edit, agree, confirm) " +
                "in turns. \n" +
                "4. Each trader can successfully edit each meeting 3 times; the fourth time would cause cancelling " +
                "the meeting and the trade. \n" +
                "5. Meeting Status can be:\n" +
                "       DNE, INCOMPLETE, CANCELLED, AGREED, COMPLETED \n" +
                "       - DNE:          when meeting is not yet set up\n" +
                "       - INCOMPLETE:   when meeting can be edited and/or agreed\n" +
                "       - CANCELLED:    when one user edits over threshold (more than 3 times)\n" +
                "       - AGREED:       when both users agreed the meeting proposal\n" +
                "       - COMPLETED:    when both users confirmed the meeting occurred\n";
    }

    public void setPresenter(IPresenter presenter) {
        this.presenter = presenter;
    }

    public void open() {
        frame.pack();
        frame.setVisible(true);
    }
}
