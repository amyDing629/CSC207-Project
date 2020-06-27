import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * [Use Case class]
 * Interacts with the user to prompt input for Meeting activities (i.e. set up meeting, edit meeting, confirm meeting)
 */

public class MeetingActivities {
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
    public void editMeeting() {}
}

