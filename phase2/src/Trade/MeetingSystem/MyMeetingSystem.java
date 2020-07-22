package Trade.MeetingSystem;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class MyMeetingSystem implements LogInUser {
    private final ArrayList<UUID> users;
    private final boolean isFirst;
    private final ArrayList<MeetingLogInfo> meetingLog = new ArrayList<>();

    MeetingActivities meetingActivities = new MeetingActivities();

    private LocalDateTime dateTime;
    private String place;
    private Meeting meeting;

    private boolean isSetUp;
    private boolean isCancel;

    private IMeetingSessionService service;

    MyMeetingSystem(ArrayList<UUID> users, boolean isFirst, Meeting meeting) {
        this.users = users;
        this.isFirst = isFirst;
        this.meeting = meeting;
        if (meeting != null) {
            dateTime = meeting.getDateTime();
            place = meeting.getPlace();
        }
        isSetUp = false;
        isCancel = false;
    }

    @Override
    public void processServiceSession(UUID currLogInUser) throws IOException {
        if (meeting == null) {
            this.service.run(currLogInUser, meeting, null, users);
        } else {
            this.service.run(currLogInUser, meeting, meeting.getLastEditUser(), users);
        }


    }

    // setter injector
    private void setService(IMeetingSessionService service) {
        this.service = service;
    }

    // automatically choose service
    public void run(UUID currLogInUser) throws IOException {
        // first meeting
        if (isFirst) {
            if (meeting == null) {
                setService(new SetUpSession());
            } else if (meeting.getStatus().equals(MeetingStatus.incomplete)) {
                setService(new EditAgreeSession());
//                this.service = new EditAgreeSession();
            } else if (meeting.getStatus().equals(MeetingStatus.agreed)) {
                setService(new ConfirmSession());
//                this.service = new ConfirmSession();
            }
        } else { // only second (temporary) meeting
            setService(new ConfirmSession());
        }

        processServiceSession(currLogInUser);
        updateSessionInfo(this.service.getSessionName(), currLogInUser);

    }


    private void updateSessionInfo(MeetingSessionName sessionName, UUID currLogInUser) {
        ArrayList<Object> result = this.service.getResults();
        if (sessionName.equals(MeetingSessionName.SETUP)) {
            meeting = (Meeting) result.get(0);
            dateTime = (LocalDateTime) result.get(1);
            place = (String) result.get(2);
            isSetUp = (boolean) result.get(3);
//            MeetingLogInfo log = this.service.getLog();
//            if (log != null) {
//                meetingLog.add(log);
//            }

            if (isSetUp) {
                meetingActivities.updateLastEditUser(currLogInUser, meeting);
            }


        } else if (sessionName.equals(MeetingSessionName.EDIT_AGREE)) {
            meeting = (Meeting) result.get(0);
            dateTime = (LocalDateTime) result.get(1);
            place = (String) result.get(2);
            isCancel = (boolean) result.get(3);
//            MeetingLogInfo log = editAgreeSession.getSessionLog();
//            if (log != null) {
//                meetingLog.add(editAgreeSession.getSessionLog());
//            }

            boolean isEdited = (boolean) result.get(4);
            if (isEdited && !isCancel) {
//                this.lastEditUser = currLogInUser;
                meetingActivities.updateLastEditUser(currLogInUser, meeting);
            }

        } else { // sessionName.equals(MeetingSessionName.CONFIRM)
            meeting = (Meeting) result.get(0);
//            MeetingLogInfo log = confirmSession.getSessionLog();
//            if (log != null) {
//                meetingLog.add(confirmSession.getSessionLog());
        }


        MeetingLogInfo log = this.service.getLog();
        if (log != null) {
            meetingLog.add(log);
        }
    }

    /**
     * Returns an arraylist of key results of the current meeting system.
     * The key results contains:
     * - date-time object
     * - place string
     * - status string: "completed", "setUp", "cancel", "incomplete"
     * - last edit user id
     *
     * @return an arraylist containing date-time object, place string, status string.
     */
    public ArrayList<Object> runResult() {
        // return time, place, status
        ArrayList<Object> result = new ArrayList<>(Arrays.asList(dateTime, place));
        String status;
        if (meeting.getStatus().equals(MeetingStatus.completed)) {
            status = "completed";
        } else if (isSetUp) {
            status = "setUp";
        } else if (isCancel) {
            status = "cancelled";
        } else {
            status = "incomplete";
        }
        result.add(status);

        result.add(meeting.getLastEditUser());

        return result;

    }

    /**
     * Returns the current meeting object.
     * This method is used for updating the meeting stored in the trade.Trade system.
     *
     * @return the meeting object
     */
    public Meeting getMeeting() {
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
    public Meeting setUpSecondMeeting(Meeting firstMeeting) {

        dateTime = firstMeeting.getDateTime().plusMonths(1);
        place = firstMeeting.getPlace();

        Meeting m = meetingActivities.setUpMeeting(users, dateTime, place);
        isSetUp = true;
        meeting = m;
        return m;
    }
}
