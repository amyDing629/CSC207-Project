package MeetingSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * [Use Case Interact class]
 * This is a meeting's controlling system.
 *
 * The meeting system controller that interacts with the user, makes decisions based on user input instructions, and
 * calls corresponding use case method.
 *
 * Main functions of this controller class:
 *      1. allows setup meeting, once the Trade is set up [TODO: TradeSystem's Responsibility??????]
 *      2. apply use case method of setting up meeting
 *      3. allows edit meeting, once the
 *      4. allows confirming the meeting
 *      5. update timeOfEdition in MeetingEditor
 */
public class MeetingSystem {

    /**
     * This is the current meeting date-time.
     * Update this variable when
     * - a meeting is setup;
     * - the date-time info of this meeting is edited.
     */
    public LocalDateTime dateTime;
    public String place;
    public static Integer userId;
    public static Integer otherUserId;

    public static Meeting meeting;


    private MeetingSystemMenuPresenter menuPresenter = new MeetingSystemMenuPresenter();

    /**
     * Construct a MeetingSystem.MeetingSystem object with two ClientUsers
     *
     * @param u1 the ClientUser who sets up the meeting
     * @param u2 the ClientUser who receives the meeting invitation
     */
    public MeetingSystem(Integer u1, Integer u2) {
        userId = u1;
        otherUserId = u2;
    }

    public MeetingSystem(ArrayList<Integer> users) {
    }

    /**
     * Run the Meeting system, which interacts with the user and makes decisions upon user input actions.
     *
     * TODO: interact with Trade System
     * 1. allows setting up a meeting, only when there is no meeting stored in Trade (i.e. first meeting)
     * 2. allows editing the meeting / confirming the meeting, only when
     * - the meeting has been set up already;
     * - the meeting has not been cancelled (i.e edit time of each ClientUser < threshold of edition time)
     */
    public void run() {
        // allow input: "exit", "setup meeting", "edit", "confirm"

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        menuPresenter.printMenu("mainMenu");

        try {
            String input = br.readLine();
            while (!input.equals("exit")) { // != compares memory addresses.

                // instruction 1: set up meeting
                if (input.equals("ss")) {
                    SetUpMeetingPresenter setUpMeeting = new SetUpMeetingPresenter();
                    dateTime = setUpMeeting.dateTime;
                    place = setUpMeeting.place;
                    meeting = MeetingActivities.setUpMeeting(userId, otherUserId, dateTime, place);

                    System.out.println("A meeting has been set up!");
                    System.out.println("  " + "- proposed time is:" + dateTime.toString());
                    System.out.println("  " + "- proposed place is:" + place);

                }

                // instruction 2: edit meeting
                input = br.readLine();
                if (input.equals("ee")) {
                    // update time place
                    EditMeetingPresenter editMeeting = new EditMeetingPresenter(dateTime, place);
                    dateTime = editMeeting.dateTime;
                    place = editMeeting.place;

                    if (editMeeting.isEdited()) {
                        System.out.println("Meeting has been edited!");
                        System.out.println("  " + "- the current proposed time is:" + dateTime.toString());
                        System.out.println("  " + "- the current proposed place is:" + place);
                    } else {
                        System.out.println("Meeting has NOT been edited!");
                    }
                }


                // instruction 3: confirm meeting
                input = br.readLine();
                if (input.equals("cc")) {
                    // confirm code
                    System.out.println("TODO: confirmed~");

                }

                // instruction 4: print Meeting System menu
                input = br.readLine();
                if (input.equals("menu")) {
                    menuPresenter.printMenu("mainMenu");

                }


                // other instruction
                input = br.readLine();
                if (!input.equals("ss") && !input.equals("ee") && !input.equals("cc") && !input.equals("menu")) {
                    System.out.println("Error: Invalid Instruction!");
                }

            }
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }

    public static class DateTime {

        public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( "yyyy-MM-dd HH:mm" );
        //    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( "yyyy-MM-dd HH:mm" ).withResolverStyle ( ResolverStyle.STRICT );
        public static LocalDateTime currentTime = LocalDateTime.now();


        public static LocalDateTime getCurrentTime() {
            return currentTime;
        }

        public static DateTimeFormatter getFormat() {
            return formatter;
        }

        @Override
        public String toString() {
            return "MeetingSystem.MeetingSystem.DateTime{" + "currentTime=" + currentTime + '}';
        }

        /**
         * Return if the input date-time string is of Valid Format
         * https://stackoverflow.com/questions/226910/how-to-sanity-check-a-date-in-java
         * @param inputDateTimeString String of input datetime
         * @return true if date-time string is of valid input format
         */
        public static boolean isValidFormat(String inputDateTimeString) throws DateTimeParseException {
            boolean valid = true;
            try {
                formatter.parse(inputDateTimeString);
            } catch (DateTimeParseException e) {
                valid = false;
            }
            return valid;
        }


        /**
         * Convert the date-time string to LocalDateTime object
         * precondition: the inputDateTimeString must be of valid format
         * Read more: https://www.java67.com/2016/04/how-to-convert-string-to-localdatetime-in-java8-example.html#ixzz6PvuyR5EV
         * @param inputDateTimeString String of input datetime
         * @return LocalDateTime object
         */
        public static LocalDateTime convertToLocalDateTime(String inputDateTimeString) {
            return LocalDateTime.parse(inputDateTimeString, formatter);
        }

    }
}