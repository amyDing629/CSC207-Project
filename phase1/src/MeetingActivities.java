import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * [Use Case class]
 * Interacts with the user to prompt input for Meeting activities (i.e. set up meeting, edit meeting, confirm meeting)
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
        Meeting meeting = new Meeting(dateTime, place, userIds);
        return meeting;
    }

    /**
     * Interacts with the user to prompt input of setting up a meeting: allow input, construct a meeting
     */
    public void setUpMeeting(User u1) {
//        try{
//            String str1 = temp.get(0);
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//            LocalDateTime dateTime = LocalDateTime.parse(str1, formatter);
//            // Read more: https://www.java67.com/2016/04/how-to-convert-string-to-localdatetime-in-java8-example.html#ixzz6PvuyR5EV
//
//            String place = temp.get(1);
//
//            Integer otherUserId = Integer.valueOf(temp.get(2));
//
//            u1.initiateMeeting(dateTime, place, otherUserId);
//            System.out.println(u1);
//
//        } catch (IndexOutOfBoundsException e) {
//            System.out.println("Empty enrolment");
//        }
    }


    /**
     * Interacts with the user to prompt input of editing a meeting:
     * - allow input of time only; place only; time+place
     * - record user id
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
        }else{
            meeting.setStatus();
        }
    }
}

