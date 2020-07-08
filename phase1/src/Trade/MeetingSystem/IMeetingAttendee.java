package Trade.MeetingSystem;

/**
 * [Entity class]
 * This is a meeting attendee.
 *
 * Allows at most 3 times of editing
 */
public class IMeetingAttendee {

    /** This IMeetingAttendee's threshold of times to edit place and/or time of meeting; Set to 3 by default. */
    public final int threshold = 3;


    /**
     * Returns the threshold of editing time to the meeting.
     * @return an int of the threshold of editing time
     */
    public int getThreshold(){
        return threshold;
    }
}
