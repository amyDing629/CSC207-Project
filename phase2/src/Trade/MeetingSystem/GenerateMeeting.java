package Trade.MeetingSystem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GenerateMeeting {

    // create a totally new meeting
    public Meeting createMeeting(LocalDateTime dateTime, String place, ArrayList<UUID> users) {
        Meeting meeting = new Meeting(UUID.randomUUID(), dateTime, place, users);
        meeting.setLastEditUser(null);
        for (UUID user : users) {
            MeetingEditor meetingEditor = constructMeetingEditor(user, 0);
            meeting.setIdToEditor(user, meetingEditor);
        }

        return meeting;
    }

    // construct a meeting with known meetingID
    public Meeting constructMeeting(UUID uuid, LocalDateTime dateTime, String place, MeetingStatus status,
                                    UUID lastEditUser, ArrayList<UUID> users, HashMap<UUID, Integer> idToEditTime,
                                    HashMap<UUID, Boolean> idToAgreedStatus,
                                    HashMap<UUID, Boolean> idToConfirmedStatus) {
        Meeting meeting = new Meeting(uuid, dateTime, place, users);
        meeting.setStatus(status);
        meeting.setLastEditUser(lastEditUser);
        for (HashMap.Entry<UUID, Integer> entry : idToEditTime.entrySet()) {
            meeting.getEditor(entry.getKey()).setTimeOfEdition(entry.getValue());
        }

        for (Map.Entry<UUID, Boolean> entry : idToAgreedStatus.entrySet()) {
            if (entry.getValue().equals(true)) {
                meeting.setIdToAgree(entry.getKey());
            }
        }

        for (Map.Entry<UUID, Boolean> entry : idToConfirmedStatus.entrySet()) {
            if (entry.getValue().equals(true)) {
                meeting.setIdToConfirm(entry.getKey());
            }
        }

        return meeting;
    }

    public MeetingEditor constructMeetingEditor(UUID uuid, int editionTime) {
        MeetingEditor meetingEditor = new MeetingEditor(uuid);
        meetingEditor.setTimeOfEdition(editionTime);
        return meetingEditor;
    }
}
