package MeetingSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

/**
 * [Use Case Interact class]
 * This is a meeting's controlling system.
 *
 * The meeting system controller that interacts with the user, makes decisions based on user input instructions, and
 * calls corresponding use case method.
 *
 * Main functions of this controller class:
 *      1. allows setup meeting, once the Trade is set up
 *      2. apply use case method of setting up meeting
 *      3. allows edit meeting, once the
 *      4. allows confirming the meeting
 *      5. update timeOfEdition in MeetingEditor
 */
public class MeetingSystem {

    /**
     * This is the current meeting date-time.
     * Update this variable when
     * - a meeting is setup;
     * - the date-time info of this meeting is edited.
     */
    public LocalDateTime dateTime;
    public String place;
    public static UUID userId;
    public static UUID otherUserId;

    public boolean isPermanent;
    public boolean isFirst;
    public boolean isSetUp;
    public boolean isCancel;

    public static Meeting meeting = null;
//    public static Meeting meeting2 = null;
    public ArrayList<MeetingLogInfo> meetingLog = new ArrayList<MeetingLogInfo>();

    final MeetingSystemMenuPresenter menuPresenter = new MeetingSystemMenuPresenter();

    /**
     * Construct a MeetingSystem.MeetingSystem object with two client users (ids)
     * @param users the users involved in this meeting
     */
    public MeetingSystem(ArrayList<UUID> users, boolean isFirst) {
        userId = users.get(0);
        otherUserId = users.get(1);
        this.isFirst = isFirst;
        isSetUp = false;
        isCancel = false;

    }

    /**
     * Run the Meeting system, which interacts with the user and makes decisions upon meeting progress.
     *
     * 1. allows setting up a meeting, only when there is no meeting stored in Trade (i.e. first meeting)
     * 2. allows editing the meeting / confirming the meeting, only when
     * - the meeting has been set up already;
     * - the meeting has not been cancelled (i.e edit time of each ClientUser < threshold of edition time)
     * @param currLogInUser the user that is currently logging in and use the meeting system
     */
    public void run(UUID currLogInUser) throws IOException {

        // first meeting
        if (isFirst){
            if (meeting == null){
                runSetupSession(currLogInUser);
            } else if (!meeting.getStatus().equals("cancelled")) {
                runEditConfirmSession(currLogInUser);
            }
        } else { // only second (temporary) meeting
            runConfirmSession(currLogInUser);
        }

    }

    public ArrayList<Object> runResult(){
        // return time, place, status
        ArrayList<Object> result = new ArrayList<>(Arrays.asList(dateTime, place));
        String status;
        if (meeting.getStatus().equals("completed")){
            status = "completed";
        }else if (isSetUp){
            status = "setUp";
        }else if (isCancel){
            status = "cancel";
        }else{
            status = "incomplete";
        }
       String meetingStatus = meeting.getStatus();
//        if (meetingStatus.equals("completed") && meeting2 != null){
//            meetingStatus = meeting2.getStatus();
//        }
        result.add(status);

        System.out.println("RESULT:");
        System.out.println("current meeting time: " + dateTime);
        System.out.println("current meeting place: " + place);
        System.out.println("current meeting status: " + meetingStatus);

        return result;

    }

    public Meeting getMeeting(){
        return meeting;
    }


    public Meeting setUpSecondMeeting(Meeting firstMeeting){

        dateTime = firstMeeting.getDateTime().plusMonths(1);
        place = firstMeeting.getPlace();

        Meeting m = MeetingActivities.setUpMeeting(userId, otherUserId, dateTime, place);
        isSetUp = true;
        meeting = m;
        return m;
    }

    /**
     * Run this session only when the meeting is not yet been set up.
     * @param currLogInUser the user id of the ClientUser who sets up the meeting
     * @throws IOException unpredicted situation error
     */
    private void runSetupSession (UUID currLogInUser) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter \"exit\" to quit set-up-meeting session, or enter \"ok\" to continue.");
        String input = br.readLine();
        try {
            // instruction 1: set up meeting
            // precondition:
            //  - no meeting has been scheduled yet
            if (input.equals("ok")) {
                SetUpMeetingPresenter setUpMeeting = new SetUpMeetingPresenter();
                dateTime = setUpMeeting.dateTime;
                place = setUpMeeting.place;
                meeting = MeetingActivities.setUpMeeting(userId, otherUserId, dateTime, place);
                isSetUp = true;

                System.out.println("Success: A meeting has been set up!");
                System.out.println("  " + "- proposed time is:" + dateTime.toString());
                System.out.println("  " + "- proposed place is:" + place);

                MeetingLogInfo log = new CreateLogRecord().createLogRecord(currLogInUser, "s");
                meetingLog.add(log);
                System.out.println("New log added:" + log.toString());
            } else {
                System.out.println("Exit Set-Up session.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void runEditConfirmSession(UUID currLogInUser) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter \"exit\" to quit edit-confirm-meeting session, or enter \"ee\" to edit, or enter \"cc\" to confirm.");
        String input = br.readLine();
        try{
            switch (input) {
                case "ee":
                    if (meeting != null && isEditable(currLogInUser)) { // meeting can be edited

                        System.out.println("isOverThreshold: " + meeting.getEditor(currLogInUser).editsOverThreshold());
                        System.out.println("isEditable: " + isEditable(currLogInUser));

                        EditMeetingPresenter editMeeting = new EditMeetingPresenter(dateTime, place);
                        LocalDateTime enteredDateTime = (LocalDateTime) editMeeting.editMeetingPresenterResult().get(0);
                        String enteredPlace = (String) editMeeting.editMeetingPresenterResult().get(1);

                        if (isEdited(enteredDateTime, enteredPlace)) {
                            MeetingActivities.editMeeting(meeting, currLogInUser, dateTime, place);
                            dateTime = editMeeting.dateTime;
                            place = editMeeting.place;

                            System.out.println("Success: Meeting has been edited!");
                            System.out.println("  " + "- the current proposed time is:" + dateTime.toString());
                            System.out.println("  " + "- the current proposed place is:" + place);

                            MeetingLogInfo log = new CreateLogRecord().createLogRecord(currLogInUser, "e");
                            meetingLog.add(log);
                            System.out.println("New log added:" + log.toString());

                            System.out.println("User " + currLogInUser + " current edit time:" + meeting.getEditor(currLogInUser).getTimeOfEdition());
                        } else {
                            System.out.println("Meeting has NOT been edited!");
                        }
                    } else if (meeting == null) { // meeting can not be edited
                        System.out.println("Error: Meeting has not been set up. Cannot be edited.");
                    } else { // not editable -> cancels the meeting
                        boolean cancelled = MeetingActivities.cancelMeeting(meeting);
                        assert cancelled;
                        System.out.println("Meeting Cancelled!");
                        System.out.println("Meeting current status: " + meeting.getStatus());
                        isCancel = true;
                    }
                    break;


                case "cc":
//                if (meeting.getStatus().equals("incomplete")){ ;
//                }
                    if (MeetingActivities.confirmMeeting(meeting, currLogInUser)) {
                        System.out.println("Success: Meeting has been confirmed by " + currLogInUser);
                        System.out.println("Meeting current status: " + meeting.getStatus());

                        MeetingLogInfo log = new CreateLogRecord().createLogRecord(currLogInUser, "c");
                        meetingLog.add(log);
                        System.out.println("New log added:" + log.toString());
                    } else {
                        System.out.println("Error: confirm error");
                    }
                    break;


                case "exit":
                    System.out.println("Exit Edit-Confirm Session.");
                    break;

                default:
                    throw new IllegalStateException("Unexpected value: " + input);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isEdited(LocalDateTime enteredDateTime, String enteredPlace){
        return !enteredDateTime.equals(dateTime) || !enteredPlace.equals(place);
    }


    private boolean isEditable(UUID currLogInUser){
        MeetingEditor editor = meeting.getEditor(currLogInUser);
        return !editor.editsOverThreshold();
    }


    private void runConfirmSession(UUID currLogInUser) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter \"cc\" to confirm, or anything else to quit confirm-meeting session.");
        String input = br.readLine();
        try{
            if (input.equals("cc")){
                if (MeetingActivities.confirmMeeting(meeting, currLogInUser)) {
                    System.out.println("Success: Meeting has been confirmed by " + currLogInUser);
                    System.out.println("Meeting current status: " + meeting.getStatus());

                    MeetingLogInfo log = new CreateLogRecord().createLogRecord(currLogInUser, "c");
                    meetingLog.add(log);
                    System.out.println("New log added:" + log.toString());
                } else {
                    System.out.println("Error: confirm error");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


// ===========================================================================================
//    public void run2() throws IOException {
//        // allow input: "exit", "setup meeting", "edit", "confirm"
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        menuPresenter.printMenu("mainMenu");
//
//        String input = br.readLine();
//        try {
//            while (!input.equals("exit")) { // != compares memory addresses.
//
//                // instruction 1: set up meeting
//                if (input.equals("ss")) {
//                    SetUpMeetingPresenter setUpMeeting = new SetUpMeetingPresenter();
//                    dateTime = setUpMeeting.dateTime;
//                    place = setUpMeeting.place;
//                    meeting = MeetingActivities.setUpMeeting(userId, otherUserId, dateTime, place);
//
//                    System.out.println("A meeting has been set up!");
//                    System.out.println("  " + "- proposed time is:" + dateTime.toString());
//                    System.out.println("  " + "- proposed place is:" + place);
//
//                }
//
//                // instruction 2: edit meeting
//                input = br.readLine();
//                if (input.equals("ee")) {
//                    // update time place
//                    EditMeetingPresenter editMeeting = new EditMeetingPresenter(dateTime, place);
//                    dateTime = editMeeting.dateTime;
//                    place = editMeeting.place;
//
//                    if (editMeeting.isEdited()) {
//                        System.out.println("Meeting has been edited!");
//                        System.out.println("  " + "- the current proposed time is:" + dateTime.toString());
//                        System.out.println("  " + "- the current proposed place is:" + place);
//                    } else {
//                        System.out.println("Meeting has NOT been edited!");
//                    }
//                }
//
//
//                // instruction 3: confirm meeting
//                input = br.readLine();
//                if (input.equals("cc")) {
//                    // confirm code
//                    System.out.println(" confirmed~");
//
//                }
//
//                // instruction 4: print Meeting System menu
//                input = br.readLine();
//                if (input.equals("menu")) {
//                    menuPresenter.printMenu("mainMenu");
//
//                }
//
//
//                // other instruction
//                input = br.readLine();
//                if (!input.equals("ss") && !input.equals("ee") && !input.equals("cc") && !input.equals("menu")) {
//                    System.out.println("Error: Invalid Instruction!");
//                }
//
//            }
//        } catch (IOException e) {
//            System.out.println("Something went wrong");
//        }
//    }
//}
