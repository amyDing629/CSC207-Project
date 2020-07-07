package Trade.MeetingSystem;

import java.util.UUID;

/**
 * A Record of an action of the Client user.User who attends this meeting.
 *
 * Log Action Types:
 *     - setup -- "s"
 *     - edit -- "e"
 *     - confirm -- "c"
 */
public class MeetingLogInfo {

    UUID userId;
    String action;

    public MeetingLogInfo(UUID userId, String action) {
        this.userId = userId;
        this.action = action;
    }

    @Override
    public String toString() {
        return "MeetingLogInfo{" +
                "userId=" + userId +
                ", action='" + action + '\'' +
                '}';
    }
}