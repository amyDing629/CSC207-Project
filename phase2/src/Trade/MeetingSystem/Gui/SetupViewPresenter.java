package Trade.MeetingSystem.Gui;

import Trade.MeetingSystem.DateTime;
import Trade.MeetingSystem.MeetingManager;
import Trade.MeetingSystem.MeetingStatus;

import java.time.LocalDateTime;
import java.util.*;


// Supervising controller
public class SetupViewPresenter extends Observable implements IPresenter {
    UUID currLogInUser;
    UUID meetingID;
    MeetingStatus meetingStatus;
    List<UUID> users;

    // Use case
    MeetingManager meetingManager = new MeetingManager();
    Model meetingModel;
    DateTime dt = new DateTime();

    // View
    EditView view;

    // Observer
    Observer observer;


    public SetupViewPresenter(UUID meetingID, UUID currLogInUser, List<UUID> users) {
        this.meetingID = meetingID;
        this.currLogInUser = currLogInUser;
        this.users = users;

        // set Model
        meetingModel = new MeetingModel(meetingID, currLogInUser);

        // set View
        view = new SetupView();

        // get meeting status
        meetingStatus = meetingModel.getMeetingStatus(meetingID);

    }

    @Override
    public void performAction(String inputDateTime, String inputAddress) {
        this.meetingID = performSetUp(inputDateTime, inputAddress, users, currLogInUser);
    }

    @Override
    public void performAction() {
        // do nothing
    }

    private UUID performSetUp(String inputDateTime, String inputAddress, List<UUID> users, UUID currLogInUser) {
        LocalDateTime setDateTime = dt.convertToLocalDateTime(inputDateTime);
        String setPlace = inputAddress.trim();
        meetingID = meetingManager.setUpMeeting((ArrayList<UUID>) users, setDateTime, setPlace, currLogInUser);

        // notify observers
        setChanged();
        notifyObservers(meetingID);

        return meetingID;
    }

    @Override
    public Model getModel() {
        return meetingModel;
    }

    @Override
    public UUID getMeetingID() {
        return meetingID;
    }

    @Override
    public UUID getCurrLogInUser() {
        return currLogInUser;
    }

    @Override
    public List<UUID> getUsers() {
        return users;
    }

    @Override
    public void run() {
        view.setPresenter(this);
        view.open();
    }

    @Override
    public void addObserver(Observer observer) {
        this.observer = observer;
    }

}
