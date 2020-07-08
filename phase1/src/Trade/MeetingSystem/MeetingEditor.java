package Trade.MeetingSystem;

import java.util.UUID;

/**
 * [Entity class]
 * A MeetingEditor of a meeting who can edit the meeting.
 *
 * This class is used as a record of editing time of each user.User who attends to a meeting.
 */

public class MeetingEditor extends IMeetingAttendee {

    /** This MeetingEditor's user id. */
    public UUID userId;


    /** This MeetingEditor's times of editing place and/or time of meeting. */
    private int timeOfEdition = 0;

    /**
     * Constructs a new MeetingEditor with user id userId.
     * @param userId the user id
     */
    public MeetingEditor (UUID userId) {
        this.userId = userId;
    }

    /**
     * Returns this MeetingEditor's user id.
     * @return the user id
     */
    public UUID getUserId() {
        return userId;
    }

    /**
     * Returns this MeetingEditor's number of time for editing time and/or place of meeting.
     * @return the number of time for edition
     */
    public int getTimeOfEdition() {
        return timeOfEdition;
    }

    /**
     * Set the time of edition
     *  ****** TODO: only accessible for trade.Trade System readFile ******
     * @param time the new time of edition
     */
    public void setTimeOfEdition(Integer time){
        timeOfEdition = time;
    }

    /**
     * Update this MeetingEditor's number of time for editing time and/or place of meeting by 1.
     */
    public void updateTimeOfEdition() {
        this.timeOfEdition += 1;
    }

    /**
     * Returns if this MeetingEditor's number of time for editing the meeting has over the threshold.
     * @return true or false
     */
    public boolean editsOverThreshold() {
        return timeOfEdition > getThreshold();
    }

    public String toString(){
        return String.valueOf(timeOfEdition);
    }

}


