package Trade.MeetingSystem.Gui;

import javax.swing.*;

public class AgreeView extends OKCancelView {
    @Override
    void setTextArea(JTextArea questionTextArea) {
        questionTextArea.setText("Do you agree the current proposal? \n " +
                "Click \"OK\" to agree, click \"Cancel\" to go back to Meeting menu.");
        questionTextArea.setEditable(false);
    }

    @Override
    void setOnOK() {
        getPresenter().performAction();
        JOptionPane.showMessageDialog(null, "Agree Successfully!\n ");
    }

}
