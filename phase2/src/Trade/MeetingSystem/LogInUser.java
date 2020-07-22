package Trade.MeetingSystem;

import java.io.IOException;
import java.util.UUID;

public interface LogInUser {
    void processServiceSession(UUID currLogInUser) throws IOException;
}
