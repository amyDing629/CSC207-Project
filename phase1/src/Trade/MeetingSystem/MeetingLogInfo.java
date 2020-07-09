package Trade.MeetingSystem;

import java.util.UUID;

/**
 * A Record of an action of the Client user.User who attends this meeting.
 * <p>
 * Log Action Types:
 * - setup -- "s"
 * - edit -- "e"
 * - agree -- "a"
 * - confirm -- "c"
 */
class MeetingLogInfo {

    UUID userId;
    String action;

    MeetingLogInfo(UUID userId, String action) {
        this.userId = userId;
        this.action = action;
    }

    /**
     * Returns the string representation of MeetingLogInfo.
     *
     * @return the string representation of MeetingLogInfo
     */
    @Override
    public String toString() {
        return "MeetingLogInfo{" +
                "userId=" + userId +
                ", action='" + action + '\'' +
                '}';
    }
}