package MeetingSystem;

/**
 * A Record of actions of the ClientUsers who attend this meeting.
 *
 * Log Action Types:
 *     - setup -- "s"
 *     - edit -- "e"
 *     - confirm -- "c"
 */
public class MeetingLogInfo {

    Integer userId;
    String action;

    public MeetingLogInfo(Integer userId, String action) {
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