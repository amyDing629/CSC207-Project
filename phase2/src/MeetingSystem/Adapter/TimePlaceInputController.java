package MeetingSystem.Adapter;

import MeetingSystem.UseCase.DateTime;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

public class TimePlaceInputController {

    // use case
    DateTime dt = new DateTime();

    // variable
    LocalDateTime dateTime_input;


    public boolean assessInput(String inputDateTime, String inputAddress) {
        return assessDateTimeInput(inputDateTime) && assessPlaceInput(inputAddress);
    }

    public boolean assessDateTimeInput(String inputDateTime) {
        if (dt.isValidFormat(inputDateTime)) {
            if (dt.isFutureTime(inputDateTime)) {
                // answer acceptable
                dateTime_input = dt.convertToLocalDateTime(inputDateTime);
                return true;
            }
        }
        return false;
    }

    public boolean assessPlaceInput(String inputAddress) {
        Pattern pattern = Pattern.compile("^\\w+([ .\\w]*)$");
        String trimmedInput = inputAddress.trim();
        return pattern.matcher(trimmedInput).matches();
//        return trimmedInput.matches("^\\w+([ .\\w]*)$");
    }


}

