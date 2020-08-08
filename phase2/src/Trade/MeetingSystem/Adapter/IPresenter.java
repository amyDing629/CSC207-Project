package Trade.MeetingSystem.Adapter;

import Trade.MeetingSystem.UseCase.Model;

import java.util.List;
import java.util.UUID;

public interface IPresenter {

    void performAction(String inputDateTime, String inputAddress);

    void performAction();

    Model getModel();

    UUID getMeetingID();

    UUID getCurrLogInUser();

    void run();

    List<UUID> getUsers();

}
