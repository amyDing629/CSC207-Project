package MeetingSystem;

/**
 *
 * Allows at most 3 times of editing
 */
public class IMeetingAttendee {

    /** This IMeetingAttendee's threshold of times to edit place and/or time of meeting; Set to 3 by default. */
    public final int threshold = 3;

    public int getThreshold(){
        return threshold;
    }
}
