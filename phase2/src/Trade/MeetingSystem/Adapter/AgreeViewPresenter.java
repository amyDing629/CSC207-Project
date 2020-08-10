package Trade.MeetingSystem.Adapter;

import Trade.MeetingSystem.Entity.Meeting;
import Trade.MeetingSystem.MeetingStatus;
import Trade.MeetingSystem.UseCase.MeetingActionManager;
import Trade.MeetingSystem.UseCase.MeetingManager;
import Trade.MeetingSystem.UseCase.MeetingModel;
import Trade.MeetingSystem.UseCase.Model;

import java.util.List;
import java.util.UUID;

public class AgreeViewPresenter implements IPresenter {

    UUID currLogInUser;
    UUID meetingID;
    MeetingStatus meetingStatus;

    // Use case
    MeetingManager meetingManager = new MeetingActionManager();
    Model meetingModel;

    // View
    OKCancelView view;


    public AgreeViewPresenter(UUID meetingID, UUID currLogInUser) {

        this.meetingID = meetingID;
        this.currLogInUser = currLogInUser;

        // set Model
        meetingModel = new MeetingModel(meetingID, currLogInUser);

        // set View
        view = new AgreeView();

        // get meeting status
        meetingStatus = meetingModel.getMeetingStatus(meetingID);

    }

    @Override
    public void performAction(String inputDateTime, String inputAddress) {
        // do nothing
    }

    @Override
    public void performAction() {
        performAgree(currLogInUser);
    }

    private void performAgree(UUID currLogInUser) {

        assert meetingManager.isMeetingIdExist(meetingID);
        Meeting m = meetingManager.getMeetingWithId(meetingID);

        meetingManager.agreeMeeting(m, currLogInUser);
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
