import java.time.LocalDateTime;
import java.util.Scanner;

public class EditMeetingPresenter {
    public LocalDateTime dateTime;
    public String place;

    /**
     * Obtain user prompts of editing time and/or place
     * TODO: record the edition time of each Trader (User)
     */
    public EditMeetingPresenter() {
        Scanner user_input = new Scanner(System.in);

        System.out.print("Enter \"time\" to change a new time, or enter \"place\" to change a new place");
        String input_str = user_input.nextLine();

        boolean close = false;
        do {
            if (input_str.equals("time")) {
                boolean good = false;
                do {
                    System.out.print("Enter the new date-time: (should be in pattern of \"yyyy-MM-dd HH:mm\") \n");
                    String dateTimeStr = user_input.nextLine();
                    // change time: must be valid (valid format + in the future); different from the old input

                    // check valid format
                    if (!DateTime.isValidFormat(dateTimeStr)) {
                        System.out.println("Invalid input format!");
                        break;
                    }

                    // check if time in future
                    LocalDateTime now = DateTime.getCurrentTime();
                    LocalDateTime newDateTime = DateTime.convertToLocalDateTime(dateTimeStr);
                    if (!newDateTime.isAfter(now)) {
                        System.out.println("Invalid input date-time! Only future time accepted");
                        break;
                    }

                    // check if different from the old time input
                    if (newDateTime.isEqual(dateTime)) {
                        good = true;
                        dateTime = newDateTime;
                        System.out.println("New Edition Successful! proposed new date-time is: " + dateTime.toString());
                    } else {
                        System.out.println("Invalid input date-time! Input time is same as the old time");
                        break;
                    }

                } while (!good);

            } else if (input_str.equals("place")) {
                boolean good = false;
                do {
                    System.out.print("Enter the new place: ");
                    String newPlace = user_input.nextLine();
                    if (isNewPlaceEditable(newPlace)) {
                        place = newPlace;
                        good = true;
                    } else {
                        System.out.println("Error: Propose a new place!");
                    }
                } while (!good);
                System.out.print("New Edition Successful! Proposed new place: " + place + "\n");

            } else if (input_str.equals("close")) {
                close = true;
            } else {
                System.out.println("Invalid instruction!");
            }
        } while (close);

    }

    /**
     * Return true iff successfully edited the place (i.e change the old place to a new one);
     * return false on the contrary
     *
     * @param newPlace the new place to change
     * @return true iff successfully edited the place (i.e change the old place to a new one)
     */
    private boolean isNewPlaceEditable(String newPlace) {
        return !newPlace.equals(place);

    }

}