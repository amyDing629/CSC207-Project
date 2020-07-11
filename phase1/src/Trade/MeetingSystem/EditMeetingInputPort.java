package Trade.MeetingSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This is a input port for edit meeting action, which interacts with the user and obtains the meeting editing
 * information.
 * <p>
 * The user.User can interact with prompts and choose to
 * - edit time
 * - edit place
 * - edit both time and place
 */
class EditMeetingInputPort {

    private LocalDateTime dateTime;
    private String place;

    DateTime dt = new DateTime();

    EditMeetingInputPortPresenter editMeetingInputPortPresenter = new EditMeetingInputPortPresenter();


    /**
     * Obtain user prompts of editing time and/or place.
     */
    EditMeetingInputPort(LocalDateTime dateTime, String place) {
        // Set the instance variables "dateTime", "place" with  before editing
        this.dateTime = dateTime;
        this.place = place;

        // Obtain user.User input of edition info
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
//            msMenuPresenter.printMenu(MeetingMenuName.EDIT);
            editMeetingInputPortPresenter.printEditMenu();

            String input = br.readLine();
            while (!input.equals("..")) {
                // instruction 1: edits time only
                // instruction 2: edits place only
                // instruction 3: edits time and place
                // instruction 4: print edit-meeting menu
                // instruction "..": quit
                // other instructions
                switch (input) {
                    case "1": editTimeInputPort();
                    case "2": editPlaceInputPort();
                    case "3": {
                        editTimeInputPort();
                        editPlaceInputPort();
                    }
                    default: {
                        {
                            editMeetingInputPortPresenter.invalidInstructionError();
                            input = br.readLine();
                        }
                        editMeetingInputPortPresenter.printEditMenu();
                    }
                }
            }
        }catch (IOException e) {
            editMeetingInputPortPresenter.wentWrongError();

        }
    }


    private void editTimeInputPort(){
        Scanner user_input = new Scanner(System.in);
        boolean good = false;
        do {
            editMeetingInputPortPresenter.printDateTimeIntro();
            String dateTimeStr = user_input.nextLine();
            // change time: must be valid (valid format + in the future); different from the old input

            // check valid format
            if (!dt.isValidFormat(dateTimeStr)) {
                editMeetingInputPortPresenter.printInvalidFormatError();
            } else {
                // check if time in future
                LocalDateTime now = dt.getCurrentTime();
                LocalDateTime newDateTime = dt.convertToLocalDateTime(dateTimeStr);
                if (!newDateTime.isAfter(now)) {
                    editMeetingInputPortPresenter.printInvalidDateTimeError();
                } else {
                    // check if different from the old time input
                    if (!newDateTime.isEqual(dateTime)) {
                        this.dateTime = newDateTime;
                        good = true;
                    } else {
                        editMeetingInputPortPresenter.printDateTimeUnchangedError();

                    }
                }
            }
        } while (!good);
        editMeetingInputPortPresenter.printTimeSuccess(dateTime);
    }


    private void editPlaceInputPort(){
        Scanner user_input = new Scanner(System.in);
        boolean good = false;
        do {
            editMeetingInputPortPresenter.printPlaceIntro();
            String newPlace = user_input.nextLine();
            if (isNewPlaceEditable(newPlace)) {
                this.place = newPlace;
                good = true;
            } else {
                editMeetingInputPortPresenter.printPlaceUnchangedError();
            }
        } while (!good);
        editMeetingInputPortPresenter.printPlaceSuccess(place);
    }

    private boolean isNewPlaceEditable(String newPlace) {
        return !newPlace.equals(place);

    }

    /**
     * Returns the results, including time and place, of this input port
     *
     * @return an arraylist of the date-time, place
     */
    ArrayList<Object> editMeetingInputPortResult() {
        return new ArrayList<>(Arrays.asList(dateTime, place));
    }

}