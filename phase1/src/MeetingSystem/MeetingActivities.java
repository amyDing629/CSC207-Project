package MeetingSystem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
        return new Meeting(dateTime, place, userIds);
    }


    public Meeting setUpSecondMeeting(Integer userId, Integer otherUserId, LocalDateTime firstDateTime, String place,
                                       Integer durationDays){
        ArrayList<Integer> ids = new ArrayList<>(Arrays.asList(userId, otherUserId));
        return new Meeting(firstDateTime.plusDays(durationDays), place, ids);
    }



    /**
     * Edits a meeting:
     * - allow input of time only; place only; time + place
     * - record user id
     * It is the user's choice to input either time only, place only, or time + place.
     */
    public static void editMeeting(Meeting meeting, Integer userId, LocalDateTime dateTime, String place){
        MeetingEditor editHistory = meeting.getEditor(userId);
        // first update edition time; then check if this time is over the threshold
        editHistory.updateTimeOfEdition();
        if (!editHistory.editsOverThreshold()) {
            meeting.editMeeting(dateTime);
            meeting.editMeeting(place);
        }else {
            meeting.setStatus();
        }
    }

    /**
     * Allow user to confirm the meeting
     * Update the confirm status history idToConfirmedStatus in this meeting
     */
    public static boolean confirmMeeting(Meeting meeting, Integer userId) {
        boolean confirmed = false;
        HashMap<Integer, Boolean> status = meeting.getConfirmedStatusFull();
        if (status.containsKey(userId)){
            // update confirmedStatus
            if (!status.get(userId)){
                status.put(userId, true);
                meeting.setIdToConfirm(userId);
                confirmed = true;
            }else{
                System.out.println("Error: User already confirmed.");
            }

            //update meeting Status
            meeting.setStatus();

        }else{
            System.out.println("Error: mismatch between the input id and id in meeting");
        }
        return confirmed;
    }

    public static boolean cancelMeeting(Meeting meeting){
        meeting.setStatus("cancelled");
        return meeting.getStatus().equals("cancelled");
    }
}

