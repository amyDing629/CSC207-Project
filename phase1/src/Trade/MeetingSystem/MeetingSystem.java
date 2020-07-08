package Trade.MeetingSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

/**
 * [Use Case Interactor class]
 * This is a meeting's main interactor.
 *
 * The meeting system controller that interacts with the user, makes decisions based on user input instructions, and
 * calls corresponding use case method.
 *
 * Main.Main functions of this controller class:
 *      1. allows setup meeting, once the trade.Trade is set up
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

    public boolean isFirst;
    public boolean isSetUp;
    public boolean isCancel;

    public Meeting meeting;

    public ArrayList<MeetingLogInfo> meetingLog = new ArrayList<>();


    /**
     * Construct a Trade.MeetingSystem.Trade.MeetingSystem object with two client users (ids)
     * @param users the users involved in this meeting
     * @param isFirst if the input meeting is the first meeting
     * @param meeting the meeting object
     */
    public MeetingSystem(ArrayList<UUID> users, boolean isFirst, Meeting meeting) {
        userId = users.get(0);
        otherUserId = users.get(1);
        this.isFirst = isFirst;
        this.meeting = meeting;
        if (meeting!=null) {
            dateTime = meeting.getDateTime();
            place = meeting.getPlace();
        }
        isSetUp = false;
        isCancel = false;
    }

    /**
     * Run the Meeting system, which interacts with the user and makes decisions upon meeting progress.
     *
     * 1. allows setting up a meeting, only when there is no meeting stored in trade.Trade (i.e. first meeting)
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
            } else if (meeting.getStatus().equals(MeetingStatus.incomplete)) {
                runEditAgreeSession(currLogInUser);
            } else if (meeting.getStatus().equals(MeetingStatus.agreed)) {
                runConfirmSession(currLogInUser);
            }
        } else { // only second (temporary) meeting
            runConfirmSession(currLogInUser);
        }

    }

    /**
     * Returns an arraylist of key results of the current meeting system.
     * The key results contains:
     *      - date-time object
     *      - place string
     *      - status string: "completed", "setUp", "cancel", "incomplete"
     *
     * @return an arraylist containing date-time object, place string, status string.
     */
    public ArrayList<Object> runResult(){
        // return time, place, status
        ArrayList<Object> result = new ArrayList<>(Arrays.asList(dateTime, place));
        String status;
        if (meeting.getStatus().equals(MeetingStatus.completed)){
            status = "completed";
        }else if (isSetUp){
            status = "setUp";
        }else if (isCancel){
            status = "cancelled";
        }else{
            status = "incomplete";
        }
        result.add(status);

        return result;

    }

    /**
     * Returns the current meeting object.
     * This method is used for updating the meeting stored in the trade.Trade system.
     *
     * @return the meeting object
     */
    public Meeting getMeeting(){
        return meeting;
    }


    /**
     * Sets up the second meeting exactly one month after the first meeting, and returns the second meeting object
     * itself, by given the first meeting object.
     * This is a shortcut method for setting up the second meeting from outside the meeting system (by trade system)
     *
     * @param firstMeeting the first Meeting object
     * @return the second meeting object
     */
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

        System.out.println("<Set-Up-Meeting Session> \n " +
                "Enter \"ok\" to continue, or anything else to quit this session.");
        String input = br.readLine();
        try {
            // precondition:
            //  - no meeting has been scheduled yet
            if (input.equals("ok")) {
                SetUpMeetingInputPort setUpMeeting = new SetUpMeetingInputPort();
                dateTime = (LocalDateTime) setUpMeeting.setUpMeetingInputPortResult().get(0);
                place = (String) setUpMeeting.setUpMeetingInputPortResult().get(1);
                meeting = MeetingActivities.setUpMeeting(userId, otherUserId, dateTime, place);
                isSetUp = true;

                // print successful setting-up
                System.out.println("Success: A meeting has been set up!");
                System.out.println("  " + "- proposed time is:" + dateTime.toString());
                System.out.println("  " + "- proposed place is:" + place);

                // create log
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


    private void runEditAgreeSession(UUID currLogInUser) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("<Edit-Confirm-Meeting Session> \n " +
                "Enter \"ee\" to edit, or enter \"aa\" to agree the proposal, or anything else to quit this session.");
        String input = br.readLine();
        try{
            switch (input) {
                case "ee":
                    if (meeting != null && isEditable(currLogInUser)) { // meeting can be edited

                        EditMeetingInputPort editMeeting = new EditMeetingInputPort(dateTime, place);
                        LocalDateTime enteredDateTime = (LocalDateTime) editMeeting.editMeetingInputPortResult().get(0);
                        String enteredPlace = (String) editMeeting.editMeetingInputPortResult().get(1);

                        if (isEdited(enteredDateTime, enteredPlace)) {
                            MeetingActivities.editMeeting(meeting, currLogInUser, dateTime, place);
                            dateTime = enteredDateTime;
                            place = enteredPlace;

                            // print successful edition
                            System.out.println("Success: Meeting has been edited!");
                            System.out.println("  " + "- the current proposed time is:" + dateTime.toString());
                            System.out.println("  " + "- the current proposed place is:" + place);

                            // create log
                            MeetingLogInfo log = new CreateLogRecord().createLogRecord(currLogInUser, "e");
                            meetingLog.add(log);
                            System.out.println("New log added:" + log.toString());

                            // print time of edition of this user
                            System.out.println("user.User " + currLogInUser + " current edit time:" +
                                    meeting.getEditor(currLogInUser).getTimeOfEdition());
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


                case "aa":
                    if (MeetingActivities.agreeMeeting(meeting, currLogInUser)) {
                        // print successful agreement
                        System.out.println("Success: Meeting has been agree by " + currLogInUser);
                        System.out.println("Meeting current status: " + meeting.getStatus());

                        //create log
                        MeetingLogInfo log = new CreateLogRecord().createLogRecord(currLogInUser, "a");
                        meetingLog.add(log);
                        System.out.println("New log added:" + log.toString());
                    } else {
                        System.out.println("Error: agree error");
                    }
                    break;


                default:
                    System.out.println("Exit Edit-Agree Session.");
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isEdited(LocalDateTime enteredDateTime, String enteredPlace){
        return !enteredDateTime.equals(dateTime) || !enteredPlace.equals(place);
    }


    private boolean isEditable(UUID currLogInUser){
        // edits <= 3: editable
        MeetingEditor editor = meeting.getEditor(currLogInUser);
        return !editor.editsOverThreshold();
    }


    private void runConfirmSession(UUID currLogInUser) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("<Confirm-Meeting Session>\n" +
                "Enter \"cc\" to confirm the meeting has been taken place, " +
                "or anything else to quit confirm-meeting session.");
        String input = br.readLine();
        try{
            if (input.equals("cc")){
                if (MeetingActivities.confirmMeeting(meeting, currLogInUser)) {
                    // print successful confirmation
                    System.out.println("Success: Meeting has been confirmed by " + currLogInUser);
                    System.out.println("Meeting current status: " + meeting.getStatus());

                    // create log
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