package Trade.MeetingSystem.Gui;

import javax.swing.*;

public class SetupView extends EditView {

    // controller: validate time and address input
    TimePlaceInputController inputController = new TimePlaceInputController();

    @Override
    void setOnOK(JFormattedTextField timeFormattedTextField, JTextField placeTextField) {
        System.out.println(timeFormattedTextField.getText());
        System.out.println(placeTextField.getText());


        String timeFieldText = timeFormattedTextField.getText();
        String placeFieldText = placeTextField.getText();
        if (inputController.assessInput(timeFieldText, placeFieldText)) {

            // perform set up with inner app
            presenter.performAction(timeFieldText, placeFieldText);
//            UUID meetingID = presenter.getMeetingID();
//
//            // update meeting info view in MainView
//            ArrayList<Object> msg = new ArrayList<>(Arrays.asList(meetingID, timeFieldText, placeFieldText));
//            addObserver(mainView);
//            setChanged();
//            notifyObservers(msg);

            // set fields no longer editable
            timeFormattedTextField.setEnabled(false);
            placeTextField.setEnabled(false);

            // success window
            JOptionPane.showMessageDialog(null, "Success!\n " +
                    "Date Time: " + timeFieldText + "\n" +
                    "Place: " + placeFieldText);

            // jump to main menu
            dispose();

        } else if (!inputController.assessDateTimeInput(timeFieldText)
                && inputController.assessPlaceInput(placeFieldText)) {
            // fail window
            JOptionPane.showMessageDialog(null, "Fail: DateTime Input failed.", "Error", JOptionPane.ERROR_MESSAGE);
            timeFormattedTextField.selectAll();

        } else if (inputController.assessDateTimeInput(timeFieldText)
                && !inputController.assessPlaceInput(placeFieldText)) {
            // fail window
            JOptionPane.showMessageDialog(null, "Fail: Address Input failed.", "Error", JOptionPane.ERROR_MESSAGE);
            timeFormattedTextField.selectAll();

        } else {
            // fail window
            JOptionPane.showMessageDialog(null, "Fail: Both Input failed.", "Error", JOptionPane.ERROR_MESSAGE);
            timeFormattedTextField.selectAll();
        }
    }

    @Override
    public void open() {
        this.pack();
        this.setVisible(true);
    }

}
