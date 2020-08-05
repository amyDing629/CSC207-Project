package Trade.MeetingSystem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

// [facade pattern - controller interface]
public interface IMeetingSessionService {


    void run(UUID currLogInUser, UUID meetingID, UUID lastEditUser, ArrayList<UUID> users) throws IOException;

    UUID getMeetingID();


//    void run(UUID currLogInUser, Meeting meeting, UUID lastEditUser, ArrayList<UUID> users) throws IOException;
//    ArrayList<Object> getResults();
//    MeetingLogInfo getLog();
//    MeetingSessionName getSessionName();

}
