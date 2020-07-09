package Trade.MeetingSystem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Input port of Meeting System, functioned for setting up a meeting only
 */
class SetUpMeetingInputPort {
    private LocalDateTime dateTime;
    private final String place;

    DateTime dt = new DateTime();

    SetUpMeetingInputPortPresenter setUpMeetingInputPortPresenter = new SetUpMeetingInputPortPresenter();

    /**
     * Feeds the prompts of the Meeting info, including dateTime, place
     */
    SetUpMeetingInputPort() {

        Scanner user_input = new Scanner(System.in);
        boolean good = false;
        do {
            setUpMeetingInputPortPresenter.printDateTimeIntro();
            String dateTimeStr = user_input.nextLine();

            // valid datetime format + in the future than now
            if (dt.isValidFormat(dateTimeStr)) {
                LocalDateTime now = dt.getCurrentTime();
                LocalDateTime datetime = dt.convertToLocalDateTime(dateTimeStr);
                if (datetime.isAfter(now)) {
                    good = true;
                    dateTime = dt.convertToLocalDateTime(dateTimeStr);
                    setUpMeetingInputPortPresenter.printTimeSuccess(dateTime);
                } else {
                    setUpMeetingInputPortPresenter.printInvalidDateTimeError();
                }
            } else {
                setUpMeetingInputPortPresenter.printInvalidFormatError();
            }
        }
        while (!good);


        setUpMeetingInputPortPresenter.printPlaceIntro();
        place = user_input.nextLine();
        setUpMeetingInputPortPresenter.printPlaceSuccess(place);


        if (user_input.nextLine().equals("close")) {
            user_input.close();
        }

    }

    ArrayList<Object> setUpMeetingInputPortResult() {
        return new ArrayList<>(Arrays.asList(dateTime, place));
    }

}
