package MeetingSystem.UseCase;

import MeetingSystem.Entity.Meeting;
import MeetingSystem.MeetingStatus;
import User.UseCase.UserManager;

import java.util.UUID;

/**
 * The display model of meeting
 */
public class MeetingModel implements Model {

    UUID meetingID;
    UUID currLogInUser;

    // use case
    MeetingActionManager meetingActionManager = new MeetingActionManager();
    UserManager userManager = new UserManager();

    public MeetingModel(UUID meetingID, UUID currLogInUser) {
        this.meetingID = meetingID;
        this.currLogInUser = currLogInUser;
    }

    @Override
    public String getCurrUser() {
        return "Welcome to Meeting System! \n" + "User: " + userManager.getUser(currLogInUser).getUsername();
    }

    @Override
    public String getMeetingInfo(UUID meetingID) {
        Meeting m = getMeeting(meetingID);
        DateTime dt = new DateTime();

        if (m == null) {
            return "Meeting ID: " + null + "\n"
                    + "Meeting status: " + MeetingStatus.DNE + "\n"
                    + "Your edited time: " + 0 + "\n"
                    + "DateTime: " + null + "\n"
                    + "Place: " + null + "\n";
        } else {
            return "Meeting ID: " + m.getID() + "\n"
                    + "Meeting status: " + m.getStatus() + "\n"
                    + "Your edited time: " + m.getEditor(currLogInUser).getTimeOfEdition() + "\n"
                    + "DateTime: " + dt.convertLDTtoString(m.getDateTime()) + "\n"
                    + "Place: " + m.getPlace() + "\n";
        }

    }

    @Override
    public Meeting getMeeting(UUID meetingID) {
        if (meetingActionManager.isMeetingIdExist(meetingID)) {
            return meetingActionManager.getMeetingWithId(meetingID);
        } else {
            return null;
        }
    }

    @Override
    public boolean isTimePlaceChanged(UUID meetingID, String inputTime, String inputPlace) {
        DateTime dt = new DateTime();
        Meeting m = getMeeting(meetingID);
        if (m == null) {
            return false;
        } else {
            return !(m.getDateTime().equals(dt.convertToLocalDateTime(inputTime)) && m.getPlace().equals(inputPlace));
        }
    }

    @Override
    public boolean isEditable(UUID meetingID, UUID currLogInUser) {
        Meeting m = getMeeting(meetingID);
        return m.getEditor(currLogInUser).editable();
    }

    @Override
    public MeetingStatus getMeetingStatus(UUID meetingID) {
        Meeting m = getMeeting(meetingID);
        MeetingStatus meetingStatus;

        if (m == null) {
            meetingStatus = MeetingStatus.DNE;
        } else {
            meetingStatus = m.getStatus();
        }

        return meetingStatus;
    }

    @Override
    public boolean otherUserAgreed(UUID meetingID) {
        UUID otherUser = getMeeting(meetingID).getLastEditUser();
        return getMeeting(meetingID).getAgreedStatusFull().get(otherUser);
    }

    @Override
    public boolean isLastUserCurrUser() {
        if (getMeeting(meetingID) == null) {
            return false;
        } else if (getMeeting(meetingID).getLastEditUser() == null) {
            return false;
        }else {
            return getMeeting(meetingID).getLastEditUser().equals(currLogInUser);
        }
    }

    @Override
    public void setMeetingID(UUID meetingID) {
        this.meetingID = meetingID;
    }


}
