package MeetingSystem;

public class CreateLogRecord{
    /**
     * create a log record of the user making an action.
     * @param userId the user-id of the user who makes action
     * @param action a String represents the type of action:
     *               - "s" for setup meeting;
     *               - "e" for edit meeting;
     *               - "c" for confirm meeting;
     * @return a Log
     */
    public MeetingLogInfo createLogRecord(Integer userId, String action){
        return new MeetingLogInfo(userId, action);
    }
}

