package MeetingSystem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
//import java.util.HashMap;
//import java.util.ArrayList;

/**
 * [Entity class]
 * A meeting as a part of the Transaction process.
 */

public class Meeting {

    /** This is Meeting's date-time. */
    private LocalDateTime dateTime;

    /** This is Meeting's place. */
    private String place;

    /**
     * This is Meeting's two editors.
     * Each ClientUser in a single meeting has a MeetingEditor to record the number of edits
     */
    private HashMap<Integer, MeetingEditor> idToEditor = new HashMap<>();

    /**
     * This is Meeting's status: "incomplete" (default), "completed", "cancelled";
     *  1. only both Traders are of confirmed in confirmed status, the meeting status should be then set to "completed";
     *  2. as long as one of the Trader exceed their own threshold of timeOfEdition, the meeting status should then be
     *  set to "cancelled";
     */
    public String status = "incomplete";

    /**
     * This is Meeting's confirm status from both Traders respectively:
     * true stands for confirmed,
     * false stands for not yet confirmed
     */
    private HashMap<Integer, Boolean> idToConfirmedStatus = new HashMap<>();

    /**
     * Constructs a new Meeting with proposed date-time to meet dateTime, proposed place to meet place, info of both
     * Traders traders.
     * @param dateTime the date-time proposed to the meeting
     * @param place the place proposed to the meeting
     * @param traderIds the ids of two traders attend to this meeting
     */
    public Meeting (LocalDateTime dateTime, String place, ArrayList<Integer> traderIds) {
        this.dateTime = dateTime;
        this.place = place;
        for (Integer i: traderIds) {
            this.idToEditor.put(i, new MeetingEditor(i));
            this.idToConfirmedStatus.put(i, false);
        }
    }

    /**
     * Returns this Meeting's date-time. (Getter for dateTime)
     * @return the date-time
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Returns this Meeting's place. (Getter for place)
     * @return the place
     */
    public String getPlace() {
        return place;
    }

    /**
     * Returns this Meeting's status. (Getter for status)
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Return this meeting's edit history, Hashmap(userId, MeetingEditor)
     * @return the idToEditor
     */
    public HashMap<Integer, MeetingEditor> getIdToEditor() {
        return idToEditor;
    }

    public void setIdToEditor(HashMap<Integer, MeetingEditor> me) {
        idToEditor = me;
    }

    /**
     * Returns a list of two Traders' the confirmed statuses. (Getter for idToConfirmedStatus)
     * @return the confirmed status of two Traders respectively
     */
    ArrayList<Boolean> getConfirmedStatuses() {
        ArrayList<Boolean> result = new ArrayList<>(idToConfirmedStatus.values());
        return result;
    }


    /**
     * Returns the confirmed statuses for given userId. (Getter for idToConfirmedStatus)
     * @return the confirmed status of given Trader's userId
     */
    private Boolean getConfirmedStatuses(Integer userId) {
        return idToConfirmedStatus.get(userId);
    }

    /**
     * Returns the confirmed statuses with userIds. (Getter for idToConfirmedStatus)
     * @return idToConfirmedStatus itself
     */
    public HashMap<Integer, Boolean> getConfirmedStatusFull() {
        return idToConfirmedStatus;
    }

    public void setConfirmedStatusFull(HashMap<Integer, Boolean> confirmedStatus) {
        idToConfirmedStatus = confirmedStatus;
    }


    /**
     * Edit the Meeting with new date-time to meet dateTime, and new place to meet place. (setter for dateTime + place)
     * @param newDateTime the date-time newly proposed to the meeting
     * @param newPlace the place newly proposed to the meeting
     */
    public void editMeeting (LocalDateTime newDateTime, String newPlace) {
        this.dateTime = newDateTime;
        this.place = newPlace;
    }

    /**
     * Edit the Meeting with new date-time to meet dateTime. (setter for dateTime)
     * @param newDateTime the date-time newly proposed to the meeting
     */
    public void editMeeting (LocalDateTime newDateTime) {
        this.dateTime = newDateTime;
    }

    /**
     * Edit the Meeting with new place to meet place. (Setter for place)
     * @param newPlace the place newly proposed to the meeting
     */
    public void editMeeting (String newPlace) {
        this.place = newPlace;
    }

    /**
     * Update the confirmed status of the Meeting with Trader's userId. (Setter for idToConfirmedStatus)
     * @param userId the place newly proposed to the meeting
     */
    public void setIdToConfirm (Integer userId){
        this.idToConfirmedStatus.replace(userId, true);
    }

    /**
     * Edit the status of the Meeting (hard Setter for status)
     */
    public void setStatus(String status){
        this.status = status;
    }

    /**
     * Update the status of the Meeting (Setter for status)
     */
    public void setStatus (){
        ArrayList<Boolean> bothTrue = new ArrayList<>(Arrays.asList(true, true));

        if (this.getConfirmedStatuses().equals(bothTrue)) {
            this.status = "completed";
        }else if (this.isMeetingCancelled()){
            this.status = "cancelled";
        }
    }

    boolean isMeetingCancelled() {
        for (MeetingEditor t: idToEditor.values()){
            if (t.editsOverThreshold()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Represents a Meeting as a String containing its time and place
     * @return a String of meeting datetime and place, separated by commas.
     */
    @Override
    public String toString() {
        String res = "";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = this.dateTime.format(formatter);
        res += formattedDateTime;

        res += ", ";

        res += this.place;

        return res;
    }

}
