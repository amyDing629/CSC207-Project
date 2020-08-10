package MeetingSystem.TradeDemo;

import MeetingSystem.MeetingStatus;

import java.util.*;

public class TradeEntity implements Observer {

    private final int tradeID;

    private final ArrayList<UUID> traderIds = new ArrayList<>(Arrays.asList(
            UUID.fromString("321abe4f-1ea2-4ef9-a5a5-6f3931e7b375"),
            UUID.fromString("123abe4f-1ea2-4ef9-a5a5-6f3931e7b375")));

    private UUID meetingID1; // = UUID.fromString("c6d81d18-e1ca-4b95-a8bb-1dfb7af0911a");
    private UUID meetingID2;

    private MeetingStatus meeting1Status;
    private MeetingStatus meeting2Status;


    public TradeEntity(int tradeID) {
        this.tradeID = tradeID;
        meeting1Status = MeetingStatus.DNE;
        meeting2Status = MeetingStatus.DNE;
    }


    public int getTradeID() {
        return tradeID;
    }

    public ArrayList<UUID> getTraderIds() {
        return traderIds;
    }

    public UUID getMeetingID1() {
        return meetingID1;
    }

    public void setMeetingID1(UUID meetingID1) {
        this.meetingID1 = meetingID1;
    }

    public UUID getMeetingID2() {
        return meetingID2;
    }

    public void setMeetingID2(UUID meetingID2) {
        this.meetingID2 = meetingID2;
    }

    public MeetingStatus getMeeting1Status() {
        return meeting1Status;
    }

    public void setMeeting1Status(MeetingStatus meeting1Status) {
        this.meeting1Status = meeting1Status;
    }

    public MeetingStatus getMeeting2Status() {
        return meeting2Status;
    }

    public void setMeeting2Status(MeetingStatus meeting2Status) {
        this.meeting2Status = meeting2Status;
    }

    @Override
    public void update(Observable o, Object arg) {
        setMeeting1Status((MeetingStatus) arg);
    }

    @Override
    public String toString() {
        return "TradeEntity{" +
                "tradeID=" + tradeID +
                ", traderIds=" + traderIds +
                ", meetingID1=" + meetingID1 +
                ", meetingID2=" + meetingID2 +
                ", meeting1Status=" + meeting1Status +
                ", meeting2Status=" + meeting2Status +
                '}';
    }
}
