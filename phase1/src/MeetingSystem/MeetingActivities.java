package MeetingSystem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [Use Case class]
 * Allow users to set up meeting, edit meeting, confirm meeting
 */

public class MeetingActivities {
    /**
     * Sets up a meeting by constructing one meeting, and returning the meeting that is set up
     * @param userId the userId of the user who sets up the meeting
     * @param otherUserId the userId of the user who receives the invitation of the meeting
     * @param dateTime the proposed the date-time to meet
     * @param place the proposed the place to meet
     * @return the meeting that is newly constructed
     */
    public static Meeting setUpMeeting(UUID userId, UUID otherUserId, LocalDateTime dateTime, String place) {
        ArrayList<UUID> userIds = new ArrayList<>(Arrays.asList(userId, otherUserId));
        return new Meeting(dateTime, place, userIds);
    }

    /**
     * Edits the given meeting object with given date object and place string, and records the editing time of the user
     * with given userId. Cancels the meeting if this user edits over the editing threshold.
     * @param meeting the meeting to be edited
     * @param userId the user ID of the user who performs the editing action to the meeting
     * @param dateTime the date-time after this user's edition
     * @param place the place after this user's edition
     */
    public static void editMeeting(Meeting meeting, UUID userId, LocalDateTime dateTime, String place){
        MeetingEditor editHistory = meeting.getEditor(userId);
        // first update edition time; then check if this time is over the threshold
        editHistory.updateTimeOfEdition();
        if (!editHistory.editsOverThreshold()) {
            meeting.editMeeting(dateTime, place);
        }else {
            meeting.setStatus();
        }
    }

    /**
     * Agree the proposal of date and time in the given meeting object by the user with given userId
     * @param meeting the meeting contains current proposal of date and time to agree
     * @param userId the user id of the user who performs agreement action
     * @return true if agreement action successes; false if the given userId did not register in this meeting or the
     * user has already agreed this meeting before this agreement process.
     */
    public static boolean agreeMeeting(Meeting meeting, UUID userId) {
        boolean agreed = false;
        HashMap<UUID, Boolean> status = meeting.getAgreedStatusFull();
        if (status.containsKey(userId)){// if such id exists in this meeting
            // update agreedStatus
            if (!status.get(userId)){ // if idToStatus is false
                status.put(userId, true);
                meeting.setIdToAgree(userId);
                agreed = true;
            }else{
                System.out.println("Error: User already confirmed.");
            }

            //update meeting Status
            meeting.setStatus();

        }else{ // if such id does NOT exist in this meeting
            System.out.println("Error: mismatch between the input id and id in meeting");
        }
        return agreed;
    }

    /**
     * Performs the confirmation action to the given meeting object by the user with given userId
     * @param meeting the meeting to confirm
     * @param userId the user id of the user who performs confirmation action
     * @return true if confirmation action successes; false if the given userId did not register in this meeting or the
     * user has already confirmed this meeting before this confirmation process.
     */
    public static boolean confirmMeeting(Meeting meeting, UUID userId) {
        boolean confirmed = false;
        HashMap<UUID, Boolean> status = meeting.getConfirmedStatusFull();
        if (status.containsKey(userId)){// if such id exists in this meeting
            // update confirmedStatus
            if (!status.get(userId)){ // if idToStatus is false
                status.put(userId, true);
                meeting.setIdToConfirm(userId);
                confirmed = true;
            }else{
                System.out.println("Error: User already confirmed.");
            }

            //update meeting Status
            meeting.setStatus();

        }else{ // if such id does NOT exist in this meeting
            System.out.println("Error: mismatch between the input id and id in meeting");
        }
        return confirmed;
    }

    /**
     * Cancels the meeting, and set the meeting status to "cancelled".
     * @param meeting the meeting to be cancelled
     * @return true if the status of meeting is finally be set to "cancelled".
     */
    public static boolean cancelMeeting(Meeting meeting){
        meeting.setStatus(MeetingStatus.CANCELLED);
        return meeting.getStatus().equals(MeetingStatus.CANCELLED);
    }
}

