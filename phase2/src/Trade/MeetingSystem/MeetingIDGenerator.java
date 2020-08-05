package Trade.MeetingSystem;

import java.util.UUID;

public class MeetingIDGenerator {

    public UUID generateID() {
        UUID uniqueID = UUID.randomUUID();
        return uniqueID;
    }

}
