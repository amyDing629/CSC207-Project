import java.util.Dictionary;
/**
 * A Trader who attends to a Meeting.
 */

class Trader {

    /** This Trader's user id. */
    public int userId;

    /** This Trader's threshold of times to edit place and/or time of meeting; Set to 3 by default. */
    private int threshold = 3;

    /** This Trader's times of editing place and/or time of meeting. */
    private int timeOfEdition = 0;

    /**
     * Constructs a new Trader with user id userId.
     * @param userId the user id
     */
    public Trader(int userId) {
        this.userId = userId;
    }

    /**
     * Returns this Trader's user id.
     * @return the user id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Returns this Trader's number of time for editing time and/or place of meeting.
     * @return the number of time for edition
     */
    public int getTimeOfEdition() {
        return timeOfEdition;
    }

    /**
     * Update this Trader's number of time for editing time and/or place of meeting by 1.
     */
    public void updateTimeOfEdition() {
        this.timeOfEdition += 1;
    }

    /**
     * Returns if this Trader's number of time for editing the meeting has over the threshold.
     * @return true or false
     */
    public boolean editsOverThreshold() {
        return timeOfEdition >= threshold;
    }

}
