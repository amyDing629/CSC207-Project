package Trade.MeetingSystem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public interface IMeetingSessionService {

    void run(UUID currLogInUser, Meeting meeting, UUID lastEditUser, ArrayList<UUID> users) throws IOException;

    ArrayList<Object> getResults();

    MeetingSessionName getSessionName();

    MeetingLogInfo getLog();
}
