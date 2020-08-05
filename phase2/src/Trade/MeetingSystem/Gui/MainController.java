package Trade.MeetingSystem.Gui;

import Trade.MeetingSystem.IDataAccess;
import Trade.MeetingSystem.MeetingManager;
import Trade.MeetingSystem.MeetingStatus;
import Trade.MeetingSystem.ReadWriteMeeting;

import javax.swing.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;

public class MainController implements Observer {
    private final List<UUID> users;
    private final boolean isFirst;
    // data access
    private final IDataAccess dataAccess = new ReadWriteMeeting();
    // main view presenter
    MPresenter mainPresenter;
    // use case
    MeetingManager meetingManager = new MeetingManager();
    private UUID meetingID;
    private MeetingStatus meetingStatus;

    public MainController(List<UUID> users, boolean isFirst, UUID meetingID) {
        this.users = users;
        this.isFirst = isFirst;
        this.meetingID = meetingID;
        this.meetingStatus = MeetingStatus.DNE;
    }

    // automatically choose service
    public void run(UUID currLogInUser, JFrame frame) {
        // first meeting
        if (isFirst) {
            if (meetingID == null) {
                mainPresenter = new MainViewPresenter(null, currLogInUser, users, frame);
            } else {
                mainPresenter = new MainViewPresenter(meetingID, currLogInUser, users, frame);
            }

        } else { // only second (temporary) meeting
            mainPresenter = new MainViewPresenter(meetingID, currLogInUser, users, frame);
        }

        // run the service once
        mainPresenter.run();
        mainPresenter.addObserver(this);


        // update ID and status
        meetingID = mainPresenter.getMeetingID();
        meetingStatus = mainPresenter.getModel().getMeetingStatus(meetingID);
        System.out.println("controller " + meetingID);
        System.out.println("controller " + meetingStatus);

//        // update meeting status reference in this controller
//        System.out.println("run - hasMeeting: "+ dataAccess.hasMeeting(meetingID));
//        if (dataAccess.hasMeeting(meetingID)){
//            this.meetingStatus = dataAccess.searchMeeting(meetingID).getStatus();
//            System.out.println("run - status: " + this.meetingStatus);
//        }

    }

    public MeetingStatus getStatus() {
        return meetingStatus;
    }

    public UUID getMeetingID() {
        return meetingID;
    }

    @Override
    public void update(Observable o, Object arg) {
        this.meetingID = (UUID) arg;
    }

//    @Override
//    public void processServiceSession(UUID currLogInUser, UUID meetingID) throws IOException {
//        if (meetingID == null) {
//            // meeting info not in database -> direct to set-up service
//            this.service.run(currLogInUser, null, null, users);
//            this.meetingID = this.service.getMeetingID();
//
//        } else if (!meetingManager.isMeetingIdExist(meetingID)){
//            System.out.println("Error: This meeting ID does not exist!"); // TODO
//        } else {
//            // meeting info is in database -> direct to other service
//            Meeting meeting = meetingManager.getMeetingWithId(meetingID);
//            this.service.run(currLogInUser, meetingID, meeting.getLastEditUser(), users);
//
//        }
//    }
//
//    // setter injector
//    private void setService(IMeetingSessionService service) {
//        this.service = service;
//    }
}

