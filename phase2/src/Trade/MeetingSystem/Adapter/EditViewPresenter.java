package Trade.MeetingSystem.Adapter;

import Trade.MeetingSystem.Entity.Meeting;
import Trade.MeetingSystem.MeetingStatus;
import Trade.MeetingSystem.UseCase.DateTime;
import Trade.MeetingSystem.UseCase.MeetingActionManager;
import Trade.MeetingSystem.UseCase.MeetingModel;
import Trade.MeetingSystem.UseCase.Model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;

public class EditViewPresenter extends Observable implements IPresenter {
    UUID currLogInUser;
    UUID meetingID;
    MeetingStatus meetingStatus;

    // Use case
    MeetingActionManager meetingActionManager = new MeetingActionManager();
    Model meetingModel;
    DateTime dt = new DateTime();

    // View
    InputTimePlaceView view;


    public EditViewPresenter(UUID meetingID, UUID currLogInUser, Observer observer) {
        this.meetingID = meetingID;
        this.currLogInUser = currLogInUser;

        // set Model
        meetingModel = new MeetingModel(meetingID, currLogInUser);

        // set View
        view = new EditView();

        // get meeting status
        meetingStatus = meetingModel.getMeetingStatus(meetingID);

        // set observers
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

        assert meetingActionManager.isMeetingIdExist(meetingID);
        Meeting m = meetingActionManager.getMeetingWithId(meetingID);

        meetingActionManager.editMeeting(m, currLogInUser, setDateTime, setPlace);

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
}

