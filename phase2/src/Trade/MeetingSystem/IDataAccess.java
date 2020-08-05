package Trade.MeetingSystem;

import java.util.Map;
import java.util.UUID;

public interface IDataAccess {
    String meetingDataFile = "src/Trade/MeetingSystem/MeetingData.csv";

    void updateFile(Meeting meeting); // write into the csv, read from the csv

    boolean hasMeeting(UUID meetingID); // read from meetingMap

    Meeting searchMeeting(UUID meetingID); // read from meetingMap

    void readFromCSVFile();

//    void writeHeaderToCSV(String filePath);

//    void writeMeetingToCSV(Meeting meeting, String filePath);

    void writeAllMeetingsToCSV();

    Map<UUID, Meeting> getMap();

}
