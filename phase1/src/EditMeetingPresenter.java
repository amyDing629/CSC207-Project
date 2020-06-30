import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * This is a presenter class for edit meeting action, which interacts with the user and obtains the meeting editing
 * information.
 *
 * The User can interact with prompts and choose to
 *      - edit time
 *      - edit place
 *      - edit both time and place
 */
public class EditMeetingPresenter {

    public LocalDateTime dateTime;
    public String place;

    /**
     * This is the status of this editing action, representing if the meeting accessed this time is edited or not
     * Note: this edition action is defaulted to be not yet edited
     */
    private boolean edited = false;

    /**
     * Obtain user prompts of editing time and/or place.
     * TODO: record the edition time of each Trader (User); print meeting "cancelled" if edit >= threshold
     */
    public EditMeetingPresenter(LocalDateTime dateTime, String place) {
        // Set the instance variables "dateTime", "place" with  before editing
        this.dateTime = dateTime;
        this.place = place;


        // Obtain User input of edition info
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        printMenu();

        try{
            String input = br.readLine();
            label:
            while (!input.equals("..")) {
                // instruction 1: edits time only
                // instruction 2: edits place only
                // instruction 3: edits time and place
                // instruction 4: print edit-meeting menu
                // instruction "..": quit
                // other instructions
                switch (input) {
                    case "1" -> {
                        editTimePresenter();
                        break label;
                    }
                    case "2" -> {
                        editPlacePresenter();
                        break label;
                    }
                    case "3" -> {
                        editTimePresenter();
                        editPlacePresenter();
                        break label;
                    }
                    case "4" -> printMenu();
                    default -> System.out.println("Invalid instruction!");
                }
            }
        }catch (IOException e) {
            System.out.println("Something went wrong within EditMeetingPresenter");
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

    private void editTimePresenter(){
        Scanner user_input = new Scanner(System.in);
        boolean good = false;
        do {
            System.out.print("Enter the new date-time: (should be in pattern of \"yyyy-MM-dd HH:mm\") \n");
            String dateTimeStr = user_input.nextLine();
            // change time: must be valid (valid format + in the future); different from the old input

            // check valid format
            if (!DateTime.isValidFormat(dateTimeStr)) {
                System.out.println("Error: Invalid input format!");
            } else {
                // check if time in future
                LocalDateTime now = DateTime.getCurrentTime();
                LocalDateTime newDateTime = DateTime.convertToLocalDateTime(dateTimeStr);
                if (!newDateTime.isAfter(now)) {
                    System.out.println("Error: Invalid input date-time! Only future time accepted");
                } else {
                    // check if different from the old time input
                    if (!newDateTime.isEqual(dateTime)) {
                        this.dateTime = newDateTime;
//                        System.out.println("New Edition Successful! " +
//                                "proposed new date-time is: " + this.dateTime.toString());
                        good = true;
                        edited = true;
                    } else {
                        System.out.println("Error: Invalid input date-time! Input time is same as the old time");
                    }

                }

            }
        } while (!good);
        System.out.println("New Edition Successful! " +
                "proposed new date-time is: " + this.dateTime.toString());
    }


    private void editPlacePresenter(){
        Scanner user_input = new Scanner(System.in);
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

    private void printMenu(){
        System.out.println("------------------------------");
        System.out.print("Menu: \n " +
                "1. Enter '1': only change time \n" +
                "2. Enter '2': only change place \n" +
                "3. Enter '3': change both time and place \n" +
                "4. Enter '4': print edit-meeting menu \n" +
                "5. Enter '..' to quit edition process \n");
        System.out.println("------------------------------");
    }

}