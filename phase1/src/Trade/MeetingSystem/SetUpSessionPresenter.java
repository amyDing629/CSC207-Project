package Trade.MeetingSystem;

import java.time.LocalDateTime;
import java.util.UUID;

class SetUpSessionPresenter extends MeetingSessionPresenter {
    @Override
    void printIntro() {
        System.out.println("<Set-Up-Meeting Session> \n " +
                "Enter \"ok\" to continue, or anything else to quit this session.");
    }

    @Override
    void printSuccessInfo(LocalDateTime dateTime, String place) {
        System.out.println("Success: A meeting has been set up!");
        super.printSuccessInfo(dateTime, place);
    }

    @Override
    void printExit() {
        System.out.println("Exit Set-Up session.");
    }

    @Override
    void printSuccessInfo(UUID currLogInUser, Meeting meeting) {

    }
}