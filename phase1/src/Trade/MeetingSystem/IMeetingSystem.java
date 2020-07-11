package Trade.MeetingSystem;

import java.io.IOException;
import java.util.UUID;

interface IMeetingSystem {
    void run(UUID currLogInUser) throws IOException;
}
