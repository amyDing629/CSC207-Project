package Trade.MeetingSystem;

import java.time.LocalDateTime;

class EditMeetingInputPortPresenter extends MeetingPresenter {
    void invalidInstructionError() {
        System.out.println("Error: Invalid instruction in EditMeetingInputPort!");
    }

    void wentWrongError() {
        System.out.println("Error: Something went wrong within EditMeetingInputPort");
    }

    // Edit time part
    @Override
    void printDateTimeIntro() {
        System.out.print("Enter the new date-time: (\"yyyy-MM-dd HH:mm\") \n");
    }

    void printDateTimeUnchangedError() {
        System.out.println("Error: Invalid input date-time! The same as the old time");
    }

    @Override
    void printTimeSuccess(LocalDateTime dateTime) {
        System.out.print("New Edition Successful!");
        super.printTimeSuccess(dateTime);
    }

    // Edit place part
    @Override
    void printPlaceIntro() {
        System.out.print("Enter the new place: ");
    }

    void printPlaceUnchangedError() {
        System.out.println("Error: Invalid input place! The same as the old place");
    }

    @Override
    void printPlaceSuccess(String place) {
        System.out.print("New Edition Successful!");
        super.printPlaceSuccess(place);
    }
}
