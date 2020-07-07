package Trade;

import Inventory.Item;
import Trade.MeetingSystem.Meeting;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

/**
 * [entity class]
 * abstract class, a trade for users to change items
 */
public abstract class Trade {
    /**
     * first meeting of the trade
     */
    private Meeting meeting;
    /**
     * status of the trade
     * unconfirmed: the trade has been requested, but not yet confirmed
     * incomplete: the trade has been confirmed, but not yet completed
     * complete: the trade is complete
     * cancelled: the trade has been cancelled
     */
    protected String status;
    /**
     * id of the trade. Each trade has unique id.
     */
    private UUID id;
    /**
     * second meeting for the trade.
     */
    private Meeting secondMeeting;
    /**
     * the number of days for borrower to keep the item.
     * -1 means permanent trade.
     */
    private final int duration;
    /**
     * the time the trade is created/requested
     */
    private LocalDateTime createTime; //the time trade is created
    public static int temp = 30;

    /**
     * [constructor]
     * @param duration number of date, -1 means permanent, 30 means temporary.
     * @param time create time ot the trade
     */
    public Trade(int duration, LocalDateTime time){
        this.status = "unconfirmed";
        this.duration = duration;
        createTime  = time;
        id = UUID.randomUUID();
    }

    /**
     * getter for createTime
     * @return createTime
     */
    public LocalDateTime getCreateTime(){
        return createTime;
    }


    /**
     * getter for id of the Trade
     * @return id of the Trade
     */
    public UUID getId(){
        return id;
    }

    public void setId(UUID id){this.id = id;}

    /**
     * getter for status
     * @return status
     */
    public String getStatus(){
        return status;
    }

    /**
     * setter for status
     * @param newStatus unconfirmed, incomplete, complete or cancelled
     */
    public void setStatus(String newStatus){
        status = newStatus;
    }

    /**
     * getter for duration
     * @return whether the trade is temporary or permanent
     */
    public int getDuration(){
        return duration;
    }

    /**
     *
     * @param dateTime time of the meeting
     * @param place place of the meeting
     * @param traderIds the id of the two traders
     * @return the meeting set
     */
    // can input time and place and create new Meeting object based on Meeting constructor
    public Meeting setMeeting(LocalDateTime dateTime, String place, ArrayList<UUID> traderIds){
        this.meeting = new Meeting(dateTime, place, traderIds);

        return meeting;
    }

    /**
     * edit meeting
     * @param mt the new meeting edited
     */
    public void changeMeeting(Meeting mt){
        meeting = mt;

    }

    /**
     * edit second meeting
     * @param mt the new meeting edited
     */
    public void changeSecondMeeting(Meeting mt){
        secondMeeting = mt;
    }

    /**
     *
     * @param dateTime time of the meeting
     * @param place place of the meeting
     * @param traderIds the id of the two traders
     * @return new meeting
     */
    public Meeting setSecondMeeting(LocalDateTime dateTime, String place, ArrayList<UUID> traderIds){
        this.secondMeeting = new Meeting(dateTime, place, traderIds);
        return secondMeeting;
    }

    /**
     * getter for second meeting
     * @return second meeting
     */
    public Meeting getSecondMeeting(){
        return secondMeeting;
    }

    /**
     * getter for first meeting
     * @return first meeting
     */
    public Meeting getMeeting(){
        return meeting;
    }

    /**
     * get a list of users involved in the trade
     * @return a list of users
     */
    public abstract ArrayList<UUID> getUsers();

    /**
     * to string
     * @return trade information in a String format.
     */
    public String toString(){
        return "this is an abstract trade class";
    };

    /**
     * getter for items involved in the trade
     * @return a list of items
     */
    public abstract ArrayList<Item> getItemList();

    /**
     * remove items from users' wishLists after the trade is completed.
     * @throws IOException the trade hasn't been made.
     */
    public abstract void makeTrade() throws IOException;

    /**
     * get whether the trade is a onewayTrade or a twowayTrade
     * @return onewayTrade or twowayTrade
     */
    public abstract String getType();



}
