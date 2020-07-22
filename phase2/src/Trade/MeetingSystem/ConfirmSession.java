package Trade.MeetingSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

class ConfirmSession implements IMeetingSessionService {

    MeetingLogInfo meetingLog;

    MeetingActivities meetingActivities = new MeetingActivities();

    ConfirmSessionPresenter confirmSessionPresenter = new ConfirmSessionPresenter();

    private Meeting meeting;


    void runConfirmSession(UUID currLogInUser, Meeting meeting) throws IOException {
        // pre-set
        this.meeting = meeting;
        meetingLog = null;

        // show session intro
        confirmSessionPresenter.printIntro(meeting.getDateTime(), meeting.getPlace());

        // allow input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        try {
            if (input.equals("cc")) {
                if (meetingActivities.confirmMeeting(meeting, currLogInUser)) {
                    // print successful confirmation
                    confirmSessionPresenter.printSuccessInfo(currLogInUser, meeting);

                    // create log
                    meetingLog = new CreateLogRecord().createLogRecord(currLogInUser, MeetingAction.CONFIRM);
                } else {
                    confirmSessionPresenter.RepeatedConfirmationError();
                }
            } else {
                confirmSessionPresenter.printExit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    Meeting getConfirmSessionResult() {
        return meeting;
    }

    MeetingLogInfo getSessionLog() {
        return meetingLog;
    }


    @Override
    public void run(UUID currLogInUser, Meeting meeting, UUID lastEditUser, ArrayList<UUID> users) throws IOException {
        runConfirmSession(currLogInUser, meeting);
    }

    @Override
    public ArrayList<Object> getResults() {
        return new ArrayList<>(Collections.singletonList(getConfirmSessionResult()));
    }

    @Override
    public MeetingSessionName getSessionName() {
        return MeetingSessionName.CONFIRM;
    }

    @Override
    public MeetingLogInfo getLog() {
        return getSessionLog();
    }
}
