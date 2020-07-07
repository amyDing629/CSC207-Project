package Trade.MeetingSystem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
    private HashMap<UUID, MeetingEditor> idToEditor = new HashMap<>();

    /**
     * This is Meeting's status: INCOMPLETE (default), AGREED, COMPLETED, CANCELLED;
     *  1. only both MeetingEditors are of agreed in agreed status, the meeting status should be then set to "agreed";
     *  2. only both MeetingEditors are of confirmed in confirmed status, the meeting status should be then set to "completed";
     *  3. as long as one of the MeetingEditor exceed their own threshold of timeOfEdition, the meeting status should then be
     *  set to "cancelled";
     */
    public MeetingStatus status = MeetingStatus.INCOMPLETE;

    /**
     * This is Meeting's agree status from both MeetingEditors respectively:
     * true stands for confirmed,
     * false stands for not yet agreed the proposed time and/or place
     */
    private HashMap<UUID, Boolean> idToAgreedStatus = new HashMap<>();

    /**
     * This is Meeting's confirm status from both MeetingEditors respectively:
     * true stands for confirmed,
     * false stands for not yet confirmed
     */
    private HashMap<UUID, Boolean> idToConfirmedStatus = new HashMap<>();

    /**
     * Constructs a new Meeting with proposed date-time to meet dateTime, proposed place to meet place, info of both
     * MeetingEditors traderIds.
     * @param dateTime the date-time proposed to the meeting
     * @param place the place proposed to the meeting
     * @param traderIds the ids of two MeetingEditors attend to this meeting
     */
    public Meeting (LocalDateTime dateTime, String place, ArrayList<UUID> traderIds) {
        this.dateTime = dateTime;
        this.place = place;
        for (UUID i: traderIds) {
            this.idToEditor.put(i, new MeetingEditor(i));
            this.idToAgreedStatus.put(i, false);
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
    public MeetingStatus getStatus() {
        return status;
    }

    /**
     * Return this meeting's edit history, Hashmap(userId, MeetingEditor)
     * @return the idToEditor
     */
    public HashMap<UUID, MeetingEditor> getIdToEditor() {
        return idToEditor;
    }

    /**
     * Returns the MeetingEditor object by given user ID
     * @param userId the user ID of type UUID
     * @return the MeetingEditor of given ID
     */
    public MeetingEditor getEditor(UUID userId) {
        return idToEditor.get(userId);
    }

    /**
     * Replace the hashmap idToEditor with a new one
     * @param me the new hashmap of idToEditor
     */
    public void setIdToEditor(HashMap<UUID, MeetingEditor> me) {
        idToEditor = me;
    }

    /**
     * Returns a list of two MeetingEditors' the agreed statuses. (Getter for idToAgreedStatus)
     * @return the agreed status of two MeetingEditors respectively
     */
    ArrayList<Boolean> getAgreedStatuses() {
        return new ArrayList<>(idToAgreedStatus.values());
    }

    /**
     * Returns a list of two MeetingEditors' the confirmed statuses. (Getter for idToConfirmedStatus)
     * @return the confirmed status of two MeetingEditors respectively
     */
    ArrayList<Boolean> getConfirmedStatuses() {
        return new ArrayList<>(idToConfirmedStatus.values());
    }

    /**
     * Returns the confirmed statuses for given userId. (Getter for idToConfirmedStatus)
     * @return the confirmed status of given MeetingEditors's userId
     */
    public Boolean getConfirmedStatuses(UUID userId) {
        return idToConfirmedStatus.get(userId);
    }
    /**
     * Returns the agreed statuses with userIds. (Getter for idToAgreedStatus)
     * @return idToAgreedStatus itself
     */
    public HashMap<UUID, Boolean> getAgreedStatusFull() {
        return idToAgreedStatus;
    }

    /**
     * Replaces the the hashmap of agree statuses with a new one
     * @param agreedStatus the new hashmap of agree statuses
     */
    public void setAgreedStatusFull(HashMap<UUID, Boolean> agreedStatus) {
        idToAgreedStatus = agreedStatus;
    }
    /**
     * Returns the confirmed statuses with userIds. (Getter for idToConfirmedStatus)
     * @return idToConfirmedStatus itself
     */
    public HashMap<UUID, Boolean> getConfirmedStatusFull() {
        return idToConfirmedStatus;
    }

    /**
     * Replaces the the hashmap of confirm statuses with a new one
     * @param confirmedStatus the new hashmap of confirm statuses
     */
    public void setConfirmedStatusFull(HashMap<UUID, Boolean> confirmedStatus) {
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
     * Update the agreed status of the Meeting with MeetingEditor's userId. (Setter for idToAgreedStatus)
     * @param userId the user id of the user who agrees the proposal
     */
    public void setIdToAgree (UUID userId){
        this.idToAgreedStatus.replace(userId, true);
    }

    /**
     * Update the confirmed status of the Meeting with MeetingEditor's userId. (Setter for idToConfirmedStatus)
     * @param userId the user id of the user who confirms the meeting has taken placed
     */
    public void setIdToConfirm (UUID userId){
        this.idToConfirmedStatus.replace(userId, true);
    }

    /**
     * Edit the status of the Meeting (hard Setter for status)
     */
    public void setStatus(MeetingStatus status){
        this.status = status;
    }

    /**
     * Update the status of the Meeting (Setter for status)
     */
    public void setStatus (){
        ArrayList<Boolean> bothTrue = new ArrayList<>(Arrays.asList(true, true));

        if (this.getConfirmedStatuses().equals(bothTrue)) {
            this.status = MeetingStatus.COMPLETED;
        }else if (this.getAgreedStatuses().equals(bothTrue)){
            this.status = MeetingStatus.AGREED;
        }else if (this.isMeetingCancelled()){
            this.status = MeetingStatus.CANCELLED;
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