import java.time.LocalDateTime;
import java.util.Scanner;

public class EditMeetingPresenter {
    public LocalDateTime dateTime;
    public String place;

    /**
     * This is the status of this editing action, representing if the meeting accessed this time is edited or not
     * Note: this edition action is defaulted to be not yet edited
     */
    private boolean edited = false;

    /**
     * Obtain user prompts of editing "time" and/or "place", or closing edition with "close".
     * TODO: record the edition time of each Trader (User)
     */
    public EditMeetingPresenter(LocalDateTime dateTime, String place) {
        // Set the instance variables "dateTime", "place" with  before editing
        this.dateTime = dateTime;
        this.place = place;


        // Obtain User input of edition info
        Scanner user_input = new Scanner(System.in);

        System.out.print("Enter \"time\" to change a new time, or enter \"place\" to change a new place, " +
                "or enter \"close\" to quit edition process \n");
        String input_str = user_input.nextLine();

        while (!input_str.equals("close")) {
            // instruction 1: "time"
            if (input_str.equals("time")) {
                boolean good = false;
                do {
                    System.out.print("Enter the new date-time: (should be in pattern of \"yyyy-MM-dd HH:mm\") \n");
                    String dateTimeStr = user_input.nextLine();
                    // change time: must be valid (valid format + in the future); different from the old input

                    // check valid format
                    if (!DateTime.isValidFormat(dateTimeStr)) {
                        System.out.println("Invalid input format!");
                    } else {
                        // check if time in future
                        LocalDateTime now = DateTime.getCurrentTime();
                        LocalDateTime newDateTime = DateTime.convertToLocalDateTime(dateTimeStr);
                        if (!newDateTime.isAfter(now)) {
                            System.out.println("Invalid input date-time! Only future time accepted");
                        } else {
                            // check if different from the old time input
                            if (!newDateTime.isEqual(dateTime)) {
                                good = true;
                                edited = true;
                                this.dateTime = newDateTime;
                                System.out.println("New Edition Successful! proposed new date-time is: " + this.dateTime.toString());
                            } else {
                                System.out.println("Invalid input date-time! Input time is same as the old time");
                            }

                        }

                    }
                } while (!good);

            }

            // instruction 2: "place"
            input_str = user_input.nextLine();
            if (input_str.equals("place")) {
                boolean good = false;
                do {
                    System.out.print("Enter the new place: ");
                    String newPlace = user_input.nextLine();
                    if (isNewPlaceEditable(newPlace)) {
                        this.place = newPlace;
                        good = true;
                        edited = true;
                    } else {
                        System.out.println("Error: Propose a new place!");
                    }
                } while (!good);
                System.out.print("New Edition Successful! Proposed new place: " + this.place + "\n");

            }


            // other instructions
            input_str = user_input.nextLine();
            if (!(input_str.equals("time") || !input_str.equals("place"))) {
                System.out.println("Invalid instruction!");
            }
        }
    }

    /**
     * Return whether any info in EditMeetingPresenter is successfully edited
     * @return true iff successfully edited the place and/or time
     */
    public boolean isEdited(){
        return edited;
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