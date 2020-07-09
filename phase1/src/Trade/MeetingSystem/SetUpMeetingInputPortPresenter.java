package Trade.MeetingSystem;

class SetUpMeetingInputPortPresenter extends MeetingPresenter {

    @Override
    void printDateTimeIntro() {
        System.out.print("Enter the date-time: (\"yyyy-MM-dd HH:mm\") \n");
    }

    @Override
    void printPlaceIntro() {
        System.out.print("Enter the place: ");
    }


}
