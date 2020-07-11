package Trade.MeetingSystem;

import java.time.LocalDateTime;
import java.util.UUID;

class EditAgreeSessionPresenter extends MeetingSessionPresenter {

    @Override
    void printIntro() {
        System.out.println("<Edit-Confirm-Meeting Session> \n " +
                "Enter \"ee\" to edit, or enter \"aa\" to agree the proposal, or anything else to quit this session.");
    }

    @Override
    void printExit() {
        System.out.println("Exit Edit-Agree Session.");
    }

    // for edit session
    @Override
    void printSuccessInfo(LocalDateTime dateTime, String place) {
        System.out.println("Success: Meeting has been edited!");
        super.printSuccessInfo(dateTime, place);
    }

    void printEditionTime(UUID currLogInUser, Meeting meeting) {
        System.out.println("user.User " + currLogInUser + " current edit time:" +
                meeting.getEditor(currLogInUser).getTimeOfEdition());
    }

    void cancelRespond(Meeting meeting) {
        System.out.println("Meeting Cancelled!");
        super.printMeetingStatusInfo(meeting);
    }

    void noEditionRespond() {
        System.out.println("Meeting has NOT been edited!");
    }

    // for agree session
    void printSuccessInfo(UUID currLogInUser, Meeting meeting) {
        System.out.println("Success: Meeting has been agreed by " + currLogInUser);
        super.printMeetingStatusInfo(meeting);
    }

    void RepeatedAgreementError() {
        System.out.println("Error: Repeated agreement error");
    }

}
