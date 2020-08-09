package Trade.MeetingSystem.Adapter;

import Trade.MeetingSystem.UseCase.DateTime;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

public class TimePlaceInputController {

    // use case
    DateTime dt = new DateTime();

    // variable
    LocalDateTime dateTime_input;


    boolean assessInput(String inputDateTime, String inputAddress) {
        return assessDateTimeInput(inputDateTime) && assessPlaceInput(inputAddress);
    }

    boolean assessDateTimeInput(String inputDateTime) {
        if (dt.isValidFormat(inputDateTime)) {
            if (dt.isFutureTime(inputDateTime)) {
                // answer acceptable
                dateTime_input = dt.convertToLocalDateTime(inputDateTime);
                return true;
            }
        }
        return false;
    }

    boolean assessPlaceInput(String inputAddress) {
        Pattern pattern = Pattern.compile("^\\w+([ .\\w]*)$");
        String trimmedInput = inputAddress.trim();
        return pattern.matcher(trimmedInput).matches();
//        return trimmedInput.matches("^\\w+([ .\\w]*)$");
    }


}

