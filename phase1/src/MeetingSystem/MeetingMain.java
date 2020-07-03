package MeetingSystem;

import java.util.HashMap;

public class MeetingMain {
    // two ClientUsers
    public static meetingClientUser u1 = new meetingClientUser(1);
    public static meetingClientUser u2 = new meetingClientUser(2);

    public static void main (String[] args) {
        System.out.println("Please register a meeting!");
        MeetingSystem mt = new MeetingSystem(u1.getId(), u2.getId());
        mt.run();




//
//
//        //============================================================================
//        // TESTING: MeetingSystem.MeetingSystem.DateTime.java
//        //============================================================================
//        String datetime1 = "2020-10-02 12:00";
//        String datetime2 = "2020-02-02 12:00";
//        String datetime3 = "2020-02-02";
//        String datetime4 = "2020-10-02";
//
//        // print current time
//        LocalDateTime now = MeetingSystem.MeetingSystem.DateTime.getCurrentTime();
//        System.out.println(now);
//
//        // check if of valid format
//        System.out.println(MeetingSystem.MeetingSystem.DateTime.isValidFormat(datetime1));
//        System.out.println(MeetingSystem.MeetingSystem.DateTime.isValidFormat(datetime2));
//        System.out.println(MeetingSystem.MeetingSystem.DateTime.isValidFormat(datetime3));
//        System.out.println(MeetingSystem.MeetingSystem.DateTime.isValidFormat(datetime4));
//
//        // convert string to LocalDateTime;
//        // check if input MeetingSystem.MeetingSystem.DateTime in the future
//        LocalDateTime datetime11 = MeetingSystem.MeetingSystem.DateTime.convertToLocalDateTime(datetime1);
//        System.out.println(datetime11.isAfter(now)); // true
//
//        LocalDateTime datetime22 = MeetingSystem.MeetingSystem.DateTime.convertToLocalDateTime(datetime2);
//        System.out.println(datetime22.isAfter(now)); // false


//        HashMap<Integer, MeetingEditor> idToEditor = new HashMap<Integer, MeetingEditor>();
//        MeetingEditor me1 = new MeetingEditor(1);
//        System.out.println("meeting editor: " + me1);
//        MeetingEditor em = idToEditor.put(1, me1);
//        System.out.println("result: " + em);
//
//        HashMap<Integer, String> idToString = new HashMap<Integer, String>();
//        String e = idToString.put(2, "Hey!");
//        System.out.println("result: " + e);



    }

}