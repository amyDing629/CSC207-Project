import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

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
    public static Meeting setUpMeeting(Integer userId, Integer otherUserId, LocalDateTime dateTime, String place) {
        ArrayList<Integer> userIds = new ArrayList<>(Arrays.asList(userId, otherUserId));
//        Meeting meeting = new Meeting(dateTime, place, userIds);
//        return meeting;

        return new Meeting(dateTime, place, userIds);
    }

    /**
     * Interacts with the user to prompt input of editing a meeting:
     * - allow input of time only; place only; time + place
     * - record user id
     * It is the user's choice to input either time only, place only, or time + place.
     */
    public void editTime(Meeting meeting, Integer userId, LocalDateTime dateTime) {
        /*
        If the current number of edits does not exceed the threshold:
        update the new dateTime in meeting, and update the edit history of this user

        If the current number of edits exceeds the threshold:
        cancel the meeting
        */
        MeetingEditor editHistory = meeting.getIdToEditor().get(userId);
        if (!editHistory.editsOverThreshold()) {
            meeting.editMeetingTime(dateTime);
            editHistory.updateTimeOfEdition();
        }else {
            meeting.setStatus();
        }
    }

    public void editPlace(Meeting meeting, Integer userId, String place){
        /*
        If the current number of edits does not exceed the threshold:
        update the new place in meeting, and update the edit history of this user

        If the current number of edits exceeds the threshold:
        cancel the meeting
         */
        MeetingEditor editHistory = meeting.getIdToEditor().get(userId);
        if (!editHistory.editsOverThreshold()) {
            meeting.editMeetingPlace(place);
            editHistory.updateTimeOfEdition();
        }else {
            meeting.setStatus();
        }
    }

    public void editMeeting(Meeting meeting, Integer userId, LocalDateTime dateTime, String place){
        MeetingEditor editHistory = meeting.getIdToEditor().get(userId);
        if (!editHistory.editsOverThreshold()) {
            meeting.editMeetingTime(dateTime);
            meeting.editMeetingPlace(place);
            editHistory.updateTimeOfEdition();
        }else {
            meeting.setStatus();
        }
    }

    /**
     * Allow user to confirm the meeting
     * Update the confirm status history idToConfirmedStatus in this meeting
     */
    public void confirmMeeting(Meeting meeting, Integer userId) {
        HashMap<Integer, Boolean> status = meeting.getConfirmedStatusFull();
        if (status.containsKey(userId)){
            status.put(userId, true);
        }else{System.out.println("Error: mismatch between the input id and id in meeting");}
    }
}

