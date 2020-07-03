package MeetingSystem;

import java.util.Dictionary;
import java.util.*;
/**
 * A MeetingEditor of a meeting who can edit the meeting.
 */

public class MeetingEditor extends IMeetingAttendee {

    /** This Trader's user id. */
    public Integer userId;


    /** This Trader's times of editing place and/or time of meeting. */
    private int timeOfEdition = 0;

    /**
     * Constructs a new Trader with user id userId.
     * @param userId the user id
     */
    public MeetingEditor (Integer userId) {
        this.userId = userId;
    }

//    /**
//     * Constructs a new Trader with meeting editor builder.
//     * @param meetingEditorBuilder the meeting editor builder
//     */
//    public MeetingSystem.MeetingEditor (MeetingEditorBuilder meetingEditorBuilder) {
//        this.userId = meetingEditorBuilder.getId();
//    }

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

//    public void setTimeOfEdition(Integer time){
//        timeOfEdition = time;
//    }

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


