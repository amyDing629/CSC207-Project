import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class Trade {
    private Meeting meeting;
    protected String status;
    private static int numOfTrade;
    private int id;
    private Meeting secondMeeting;
    private final int duration;
    private LocalDateTime createTime; //the time trade is created

    /**
     * Constructor
     * @param duration number of date, -1 means permanent
     */
    public Trade(int duration, LocalDateTime time){
        this.status = "disagreed";
        this.duration = duration;
        numOfTrade++;
        id = numOfTrade;
        createTime  = time;
    }

    public LocalDateTime getCreateTime(){
        return createTime;
    }

    public void setCreateTime(LocalDateTime time){
        createTime = time;
    }

    /**
     * return id of the Trade when print Trade
     * @return id of the Trade
     */
    public int getId(){
        return id;
    }

    public void setId(int id){this.id = id;}

    /**
     * getter for status
     * @return whether the trade is unconfirmed, confirmed and completed
     */
    public String getStatus(){
        return status;
    }

    /**
     * setter for status
     * @param newStatus new status of the trade
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
    public Meeting setMeeting(LocalDateTime dateTime, String place, ArrayList<Integer> traderIds){
        this.meeting = new Meeting(dateTime, place, traderIds);
        return meeting;
    }

    /**
     *
     * @param dateTime time of the meeting
     * @param place place of the meeting
     * @param traderIds the id of the two traders
     * @return the meeting set
     */
    public Meeting setSecondMeeting(LocalDateTime dateTime, String place, ArrayList<Integer> traderIds){
        this.secondMeeting = new Meeting(dateTime, place, traderIds);
        return secondMeeting;
    }

    public Meeting getSecondMeeting(){
        return secondMeeting;
    }

    /**
     * getter for meeting
     * @return the Meeting object inside the trade
     */
    public Meeting getMeeting(){
        return meeting;
    }

    /**
     * change meeting information, use Meeting's methods
     * add Message information in the messageList
     */
    public void changeMeeting(){
    }

    abstract ArrayList<Integer> getUsers();

    public String toString(){
        return "this is an abstract trade class";
    };

    public abstract ArrayList<Item> getItemList();



}
