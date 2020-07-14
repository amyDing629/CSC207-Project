package Trade.MeetingSystem;

import java.io.IOException;
import java.util.UUID;

/**
 * This is a blueprint of meeting system.
 */
interface IMeetingSystem {
    void run(UUID currLogInUser) throws IOException;

//    void run(UUID currLogInUser, UUID lastEditUser) throws IOException;
}
