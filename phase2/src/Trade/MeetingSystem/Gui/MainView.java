package Trade.MeetingSystem.Gui;

import Trade.MeetingSystem.MeetingStatus;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

public class MainView {
    // Presenter
    MPresenter presenter;
    private JFrame frame;
    private JPanel panel1;
    private JButton backButton;
    private JButton confirmButton;
    private JTextArea meetingInfoTextArea;
    private JButton helpButton;
    private JButton agreeButton;
    private JButton setUpButton;
    private JButton editButton;
    private JTextArea welcomeTextArea;


    private boolean isFirst; // is first meeting


    public MainView() {
        initComponents();
    }

    private void backActionPerformed(java.awt.event.ActionEvent evt) {
        getPresenter().back();
        frame.setVisible(false);
    }

    public void updateViewFromModel(boolean isFirst) {
        this.isFirst = isFirst;
        Model model = getPresenter().getModel();
        UUID meetingID = getPresenter().getMeetingID();
        MeetingStatus meetingStatus = model.getMeetingStatus(meetingID);

        // update welcome view, meeting info view
        welcomeTextArea.setText(model.getCurrUser());
        meetingInfoTextArea.setText(model.getMeetingInfo(meetingID));

        // TODO: strategy pattern
        if (isFirst) { // first meeting view

            // update Button view
            if (meetingStatus.equals(MeetingStatus.DNE)) {
                // set up session
                setUpButton.setVisible(true);
                editButton.setVisible(false);
                agreeButton.setVisible(false);
                confirmButton.setVisible(false);
            } else if (meetingStatus.equals(MeetingStatus.INCOMPLETE)) {
                setUpButton.setVisible(false);
                editButton.setVisible(!model.otherUserAgreed(meetingID) && !model.isLastUserCurrUser());
                agreeButton.setVisible(!model.isLastUserCurrUser());
                confirmButton.setVisible(false);
            } else if (meetingStatus.equals(MeetingStatus.AGREED)) {
                welcomeTextArea.setText("Meeting Agreed by both users!");
                setUpButton.setVisible(false);
                editButton.setVisible(false);
                agreeButton.setVisible(false);
                confirmButton.setVisible(!model.isLastUserCurrUser());
            } else {
                // cancelled or completed
                setUpButton.setVisible(false);
                editButton.setVisible(false);
                agreeButton.setVisible(false);
                confirmButton.setVisible(false);
            }

            if (model.isLastUserCurrUser()) {
                setUpButton.setVisible(false);
                editButton.setVisible(false);
                agreeButton.setVisible(false);
                confirmButton.setVisible(false);
                welcomeTextArea.setText("Please wait the other user to edit/agree/confirm!");
            }

            if (meetingStatus.equals(MeetingStatus.CANCELLED)) {
                welcomeTextArea.setText("Meeting Cancelled!");
            } else if (meetingStatus.equals(MeetingStatus.COMPLETED)) {
                welcomeTextArea.setText("Meeting Confirmed by both users!");
            }
        } else { // second meeting view

            confirmButton.setVisible(true);

            if (model.isLastUserCurrUser()) {
                setUpButton.setVisible(false);
                editButton.setVisible(false);
                agreeButton.setVisible(false);
                confirmButton.setVisible(false);
                welcomeTextArea.setText("Please wait the other user to confirm!");
            }

            if (meetingStatus.equals(MeetingStatus.COMPLETED)) {
                welcomeTextArea.setText("Meeting Confirmed by both users!");
            }
        }


    }

    public void open() {
        frame.setVisible(true);
    }

    public MPresenter getPresenter() {
        return presenter;
    }

    public void setPresenter(MPresenter presenter) {
        this.presenter = presenter;
    }

    private void initComponents() {
        frame = new JFrame("Meeting System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        frame.getContentPane().add(panel1);
        frame.setVisible(true);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: back to Trade System
                frame.setVisible(false);
                backActionPerformed(e);
            }
        });
        setUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // go to setup view
                SetupViewPresenter setupViewPresenter =
                        new SetupViewPresenter(getPresenter().getMeetingID(), getPresenter().getCurrLogInUser(),
                                getPresenter().getUsers(), getPresenter().getObserver());
                setupViewPresenter.run();
                getPresenter().setMeetingID(setupViewPresenter.getMeetingID()); // TODO: Observer Pattern ?????
                updateViewFromModel(isFirst);
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // go to edit view
                EditionViewPresenter editionViewPresenter =
                        new EditionViewPresenter(getPresenter().getMeetingID(), getPresenter().getCurrLogInUser(),
                                getPresenter().getObserver());
                editionViewPresenter.run();
                updateViewFromModel(isFirst);
            }
        });
        agreeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // go to agree view
                AgreeViewPresenter agreeViewPresenter =
                        new AgreeViewPresenter(getPresenter().getMeetingID(), getPresenter().getCurrLogInUser());
                agreeViewPresenter.run();
                updateViewFromModel(isFirst);
            }
        });
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // go to confirm view
                ConfirmViewPresenter confirmViewPresenter =
                        new ConfirmViewPresenter(getPresenter().getMeetingID(), getPresenter().getCurrLogInUser(),
                                getPresenter().getObserver());
                confirmViewPresenter.run();
                updateViewFromModel(isFirst);
            }
        });
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: go to help view
            }
        });
    }


}
