package Trade.MeetingSystem.Gui;

import Trade.MeetingSystem.DateTime;
import Trade.MeetingSystem.Meeting;
import Trade.MeetingSystem.MeetingManager;
import Trade.MeetingSystem.MeetingStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;

public class EditionViewPresenter extends Observable implements IPresenter {
    UUID currLogInUser;
    UUID meetingID;
    MeetingStatus meetingStatus;

    // Use case
    MeetingManager meetingManager = new MeetingManager();
    Model meetingModel;
    DateTime dt = new DateTime();

    // View
    EditView view;

    // Observer
//    List<Observer> observers;


    public EditionViewPresenter(UUID meetingID, UUID currLogInUser, Observer observer) {
        this.meetingID = meetingID;
        this.currLogInUser = currLogInUser;

        // set Model
        meetingModel = new MeetingModel(meetingID, currLogInUser);

        // set View
        view = new EditionView();

        // get meeting status
        meetingStatus = meetingModel.getMeetingStatus(meetingID);

        // set observers
//        observers = new ArrayList<>();
        addObserver(observer);
    }

    @Override
    public void performAction(String inputDateTime, String inputAddress) {
        performEdition(inputDateTime, inputAddress, currLogInUser);
    }

    @Override
    public void performAction() {
        // do nothing
    }

    private void performEdition(String inputDateTime, String inputAddress, UUID currLogInUser) {
        LocalDateTime setDateTime = dt.convertToLocalDateTime(inputDateTime);
        String setPlace = inputAddress.trim();

        assert meetingManager.isMeetingIdExist(meetingID);
        Meeting m = meetingManager.getMeetingWithId(meetingID);

        meetingManager.editMeeting(m, currLogInUser, setDateTime, setPlace);

        // notify observers
        setChanged();
        notifyObservers(meetingID);
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
        return null;
    }

    @Override
    public void run() {
        view.setPresenter(this);
        view.open();
    }

//    @Override
//    public void addObserver(Observer observer) {
//        addObserver(observer);
////        this.observers.add(observer);
//    }
}

