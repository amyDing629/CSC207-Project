package Trade.MeetingSystem.Gui;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;

public interface MPresenter extends Observer {
    void back();

    Model getModel();

    UUID getMeetingID();

    void setMeetingID(UUID meetingID);

    UUID getCurrLogInUser();

    void run();

    List<UUID> getUsers();

    void update(Observable o, Object arg);

    void addObserver(Observer observer);

    Observer getObserver();

}
