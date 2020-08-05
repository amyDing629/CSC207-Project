package Trade.MeetingSystem.Gui;

import javax.swing.*;
import java.awt.event.*;

public abstract class EditView extends JDialog {
    IPresenter presenter;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonClear;
    private JTextArea instructionTextArea;
    private JTextArea noteTextArea;
    private JButton buttonBack;
    private JTextField placeTextField;
    private JFormattedTextField timeFormattedTextField;

    public EditView() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClear();
            }
        });

        buttonBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onBack();
            }
        });

        // call onBack() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onBack();
            }
        });

        // call onBack() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onBack();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        setOnOK(timeFormattedTextField, placeTextField);
    }

    abstract void setOnOK(JFormattedTextField timeFormattedTextField, JTextField placeTextField);

    private void onBack() {
        dispose();
    }

    private void onClear() {
        timeFormattedTextField.setText("");
        placeTextField.setText("");
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        instructionTextArea = new JTextArea("Please propose a time and place to meet!");
        instructionTextArea.setEditable(false);

        noteTextArea = new JTextArea("Note: \n " +
                "[Time] only digits allowed \n " +
                "[Address] only letters, digits, period, space allowed");
        noteTextArea.setEditable(false);

        timeFormattedTextField = new JFormattedTextField(new TextFieldFormatter().createFormatter("####-##-## ##:##"));
        timeFormattedTextField.setToolTipText("yyyy-MM-dd hh:mm");

        placeTextField = new JTextField();
        placeTextField.setToolTipText("Must contain letters; \n Optional: digits, period, space");
    }

    IPresenter getPresenter() {
        return presenter;
    }

    void setPresenter(IPresenter presenter) {
        this.presenter = presenter;
    }

    abstract void open();

    JFormattedTextField getTimeFormattedTextField() {
        return timeFormattedTextField;
    }

    JTextField getPlaceTextField() {
        return placeTextField;
    }
}
