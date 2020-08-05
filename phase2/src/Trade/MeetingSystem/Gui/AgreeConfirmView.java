package Trade.MeetingSystem.Gui;

import javax.swing.*;
import java.awt.event.*;

public abstract class AgreeConfirmView extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextArea questionTextArea;

    // presenter
    private IPresenter presenter;

    public AgreeConfirmView() {
        createUIComponents();

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        setOnOK();
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    private void createUIComponents() {
        questionTextArea = new JTextArea();
        setTextArea(questionTextArea);
    }

    abstract void setTextArea(JTextArea questionTextArea);

    abstract void setOnOK();

    public IPresenter getPresenter() {
        return presenter;
    }

    public void setPresenter(IPresenter presenter) {
        this.presenter = presenter;
    }

    public void open() {
        this.pack();
        this.setVisible(true);
    }
}
