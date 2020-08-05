package Trade.MeetingSystem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

/**
 * [Use Case class]
 * This is a set of meeting activities allowing the actual realisation of setting up meeting, editing meeting, agree on
 * meeting proposal, confirming meeting, and cancelling the meeting
 */

public class MeetingManager {

    IDataAccess MeetingDataAccess = new ReadWriteMeeting();

    public boolean isMeetingIdExist(UUID meetingID) {
        return MeetingDataAccess.hasMeeting(meetingID);
    }

    /**
     * Return the Meeting object with given meeting ID
     *
     * @param meetingID the meeting ID of the meeting
     * @return the meeting object
     */
    public Meeting getMeetingWithId(UUID meetingID) {
        return MeetingDataAccess.searchMeeting(meetingID);
    }

    /**
     * Sets up a meeting by constructing one meeting, and returning the meeting that is set up
     *
     * @param userIds  the ids of the users who attend this meeting
     * @param dateTime the proposed the date-time to meet
     * @param place    the proposed the place to meet
     * @return the meeting that is newly constructed
     */
    public UUID setUpMeeting(ArrayList<UUID> userIds, LocalDateTime dateTime, String place, UUID currLogInUser) {
        Meeting meeting = new GenerateMeeting().createMeeting(dateTime, place, userIds);
        meeting.setLastEditUser(currLogInUser);

        MeetingDataAccess.updateFile(meeting);

        return meeting.getID();
    }

    /**
     * Sets up the second meeting exactly one month after the first meeting, and returns the second meeting object
     * itself, by given the first meeting object.
     * This is a shortcut method for setting up the second meeting from outside the meeting system (by trade system)
     *
     * @param firstMeetingID the id of the first Meeting object
     * @return the second meeting's ID
     */
    public UUID setUpSecondMeeting(UUID firstMeetingID) {

        Meeting firstMeeting = getMeetingWithId(firstMeetingID);
        LocalDateTime dateTime = firstMeeting.getDateTime().plusMonths(1);
        String place = firstMeeting.getPlace();
        ArrayList<UUID> users = new ArrayList<>(firstMeeting.getIdToEditor().keySet());

        UUID uuid = setUpMeeting(users, dateTime, place, null);
        return uuid;
    }

    /**
     * Edits the given meeting object with given date object and place string, and records the editing time of the user
     * with given userId. Cancels the meeting if this user edits over the editing threshold.
     *
     * @param meeting       the meeting to be edited
     * @param currLogInUser the user ID of the user who performs the editing action to the meeting
     * @param dateTime      the date-time after this user's edition
     * @param place         the place after this user's edition
     * @return Meeting object after edition
     */
    public Meeting editMeeting(Meeting meeting, UUID currLogInUser, LocalDateTime dateTime, String place) {

        UUID meetingID = meeting.getID();
        assert MeetingDataAccess.hasMeeting(meetingID);

        Meeting m = MeetingDataAccess.searchMeeting(meetingID);
        m.getEditor(currLogInUser).updateTimeOfEdition(); // update time of edition
        m.setLastEditUser(currLogInUser); // update last edit user
        if (!m.getEditor(currLogInUser).editsOverThreshold()) {
            m.editMeeting(dateTime, place); // edits
        } else {
            updateStatus(m); // cancels
        }
        MeetingDataAccess.writeAllMeetingsToCSV(); // write meetingMap to csv

        return m;
    }

    /**
     * Agree the proposal of date and time in the given meeting object by the user with given userId
     *
     * @param meeting       the meeting contains current proposal of date and time to agree
     * @param currLogInUser the user id of the user who performs agreement action
     * @return true if agreement action successes; false if the given userId did not register in this meeting or the
     * user has already agreed this meeting before this agreement process.
     */
    public boolean agreeMeeting(Meeting meeting, UUID currLogInUser) {
        UUID meetingID = meeting.getID();
        assert MeetingDataAccess.hasMeeting(meetingID);
        Meeting m = MeetingDataAccess.searchMeeting(meetingID);

        boolean agreed = false;
        HashMap<UUID, Boolean> status = m.getAgreedStatusFull();

        if (status.containsKey(currLogInUser)) {// if such id exists in this meeting
            // update agreedStatus
            if (!status.get(currLogInUser)) { // if idToStatus is false
                m.setIdToAgree(currLogInUser);
                m.setLastEditUser(currLogInUser); // update last edit user
                agreed = true;
            }
            //update meeting Status
            updateStatus(m);

            MeetingDataAccess.writeAllMeetingsToCSV();
        }

        return agreed;
    }

    /**
     * Performs the confirmation action to the given meeting object by the user with given userId
     *
     * @param meeting       the meeting to confirm
     * @param currLogInUser the user id of the user who performs confirmation action
     * @return true if confirmation action successes; false if the given userId did not register in this meeting or the
     * user has already confirmed this meeting before this confirmation process.
     */
    public boolean confirmMeeting(Meeting meeting, UUID currLogInUser) {
        UUID meetingID = meeting.getID();
        assert MeetingDataAccess.hasMeeting(meetingID);
        Meeting m = MeetingDataAccess.searchMeeting(meetingID);

        boolean confirmed = false;
        HashMap<UUID, Boolean> status = m.getConfirmedStatusFull();

        if (status.containsKey(currLogInUser)) {// if such id exists in this meeting
            // update confirmedStatus
            if (!status.get(currLogInUser)) { // if idToStatus is false
                m.setIdToConfirm(currLogInUser);
                m.setLastEditUser(currLogInUser); // update last edit user
                confirmed = true;
            }
            //update meeting Status
            updateStatus(m);

            MeetingDataAccess.writeAllMeetingsToCSV();
        }

        return confirmed;
    }

    /**
     * Cancels the meeting, and set the meeting status to "cancelled".
     *
     * @param meeting the meeting to be cancelled
     * @return true if the status of meeting is finally be set to "cancelled".
     */
    public boolean cancelMeeting(Meeting meeting) {
        UUID meetingID = meeting.getID();
        assert MeetingDataAccess.hasMeeting(meetingID);
        Meeting m = MeetingDataAccess.searchMeeting(meetingID);
        m.setStatus(MeetingStatus.CANCELLED);
        MeetingDataAccess.writeAllMeetingsToCSV();
        return m.getStatus().equals(MeetingStatus.CANCELLED);
    }

    /**
     * Update the status of the Meeting
     *
     * @param meeting the meeting to be updated
     */
    private void updateStatus(Meeting meeting) {
        ArrayList<Boolean> bothTrue = new ArrayList<>(Arrays.asList(true, true));

        if (meeting.getConfirmedStatuses().equals(bothTrue)) {
            meeting.setStatus(MeetingStatus.COMPLETED);
        } else if (meeting.getAgreedStatuses().equals(bothTrue)) {
            meeting.setStatus(MeetingStatus.AGREED);
        } else if (isMeetingCancelled(meeting)) {
            meeting.setStatus(MeetingStatus.CANCELLED);
        }
    }

    private boolean isMeetingCancelled(Meeting meeting) {
        for (MeetingEditor t : meeting.getIdToEditor().values()) {
            if (t.editsOverThreshold()) {
                return true;
            }
        }
        return false;
    }

}

