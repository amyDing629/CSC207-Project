package MeetingSystem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MeetingMain {
    // two ClientUsers
    public static ArrayList<Integer> tradeIds = new ArrayList<>(Arrays.asList(1, 2));

    private static Meeting meeting = null;
    private static Meeting meeting2 = null;

    public static void main (String[] args) throws IOException {
        System.out.println("Welcome to meeting system!");

        // the start of the first meeting
        MeetingSystem mt_temp = new MeetingSystem(tradeIds, true);
        boolean turnIsUser1 = true;
        while (meeting == null || meeting.getStatus().equals("incomplete")){
            if (turnIsUser1){
                System.out.println("===========================");
                System.out.println("Login id:" + tradeIds.get(0));

                mt_temp.run(tradeIds.get(0));
                mt_temp.runResult();
                meeting = mt_temp.getMeeting();
                System.out.println(meeting);
                turnIsUser1 = false;
                System.out.println("===========================");
            }else{
                System.out.println("===========================");
                System.out.println("Login id:" + tradeIds.get(1));

                mt_temp.run(tradeIds.get(1));
                mt_temp.runResult();
                meeting = mt_temp.getMeeting();
                turnIsUser1 = true;
                System.out.println("===========================");
            }

        } // here is the end of the first meeting


        // the start of the second meeting
        System.out.println("Welcome to meeting system!");
        System.out.println("== SECOND MEETING ==");
        MeetingSystem mt_temp2 = new MeetingSystem(tradeIds, false);
        meeting2 = mt_temp2.setUpSecondMeeting(meeting);
        boolean turnIsUser1_m2 = true;
        while (meeting2 == null || meeting2.getStatus().equals("incomplete")){
            if (turnIsUser1_m2){
                System.out.println("===========================");
                mt_temp2.run(tradeIds.get(0));
                mt_temp2.runResult();
                meeting2 = mt_temp2.getMeeting();

                turnIsUser1_m2 = false;
                System.out.println("===========================");
            }else{
                System.out.println("===========================");
                mt_temp2.run(tradeIds.get(1));
                mt_temp2.runResult();
                meeting2 = mt_temp2.getMeeting();

                turnIsUser1_m2 = true;
                System.out.println("===========================");
            }

        } // here is the end of the second meeting
























        // If ClientUser A sets up the meeting,
        // only when ClientUser B edits/confirms the meeting,
        // ClientUser A could then edits/confirms the meeting.
        // Otherwise, ClientUser A could only sees the meeting time/place that A proposed
        // and proposed meeting not responded info.
//        String input;
//        while (input.equals("quit MS"))
//        if (meeting == null){
//            mt.runSetupSession();
//        }else{
//            mt.runEditConfirmSession();
//        }

        // hardcode two client user's activities
//        mt.runSetupSession(1);
//        mt.runEditConfirmSession(2);
//        mt.runEditConfirmSession(2);
////        mt.runEditConfirmSession(1);
//        mt.runEditConfirmSession(2);
////        mt.runEditConfirmSession(1);
//
//        mt.runEditConfirmSession(2);



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