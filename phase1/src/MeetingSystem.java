import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * [Controller class]
 * confirm the meeting
 * or edit time, place of the Meeting by Trader
 * update timeOfEdition in MeetingEditor
 */
public class MeetingSystem {

    public LocalDateTime dateTime;
    public String place;
    public static Integer userId;
    public static Integer otherUserId;

    public static Meeting meeting;

//    private SetUpMeetingPresenter setUpMeeting = new SetUpMeetingPresenter();

    /**
     * Construct a MeetingSystem object with two ClientUsers
     * @param u1 the ClientUser who sets up the meeting
     * @param u2 the ClientUser who receives the meeting invitation
     */
    public MeetingSystem(ClientUser u1, ClientUser u2){
        userId = u1.getId();
        otherUserId = u2.getId();
    }

    /**
     * Run the Meeting system, which interacts with the user to prompt input of meeting information.
     */
    public void run() {
        // allow input: "exit", "setup meeting", "edit", "confirm"

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//        System.out.println("------------------------------");
//        System.out.print("Meeting Menu: \n " +
//                "1. Enter 'ss': to set up a meeting \n" +
//                "2. Enter 'ee': to edit the meeting \n" +
//                "3. Enter 'cc': to confirm the meeting \n" +
//                "4. Enter 'menu': to print menu"+
//                "5. Enter 'exit' to quit meeting system\n");
//        System.out.println("------------------------------");
        printMenu();

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

                    if(editMeeting.isEdited()){
                        System.out.println("Meeting has been edited!");
                        System.out.println("  " + "- the current proposed time is:" + dateTime.toString());
                        System.out.println("  " + "- the current proposed place is:" + place);
                    }else{
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
                    printMenu();

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


    private void printMenu(){
        System.out.println("------------------------------");
        System.out.print("Meeting Menu: \n " +
                "1. Enter 'ss': to set up a meeting \n" +
                "2. Enter 'ee': to edit the meeting \n" +
                "3. Enter 'cc': to confirm the meeting \n" +
                "4. Enter 'menu': to print menu"+
                "5. Enter 'exit' to quit meeting system\n");
        System.out.println("------------------------------");
    }
}





//public class Password {
//
//    public static void main (String args[]) throws IOException {
//
//        Console c = System.console();
//        if (c == null) {
//            System.err.println("No console.");
//            System.exit(1);
//        }
//
//        String login = c.readLine("Enter your login: ");
//        char [] oldPassword = c.readPassword("Enter your old password: ");
//
//        if (verify(login, oldPassword)) {
//            boolean noMatch;
//            do {
//                char [] newPassword1 = c.readPassword("Enter your new password: ");
//                char [] newPassword2 = c.readPassword("Enter new password again: ");
//                noMatch = ! Arrays.equals(newPassword1, newPassword2);
//                if (noMatch) {
//                    c.format("Passwords don't match. Try again.%n");
//                } else {
//                    change(login, newPassword1);
//                    c.format("Password for %s changed.%n", login);
//                }
//                Arrays.fill(newPassword1, ' ');
//                Arrays.fill(newPassword2, ' ');
//            } while (noMatch);
//        }
//
//        Arrays.fill(oldPassword, ' ');
//    }
//
//    // Dummy change method.
//    static boolean verify(String login, char[] password) {
//        // This method always returns
//        // true in this example.
//        // Modify this method to verify
//        // password according to your rules.
//        return true;
//    }
//
//    // Dummy change method.
//    static void change(String login, char[] password) {
//        // Modify this method to change
//        // password according to your rules.
//    }
//}

//
//    // Dummy change method.
//    static void change(String login, char[] password) {
//        // Modify this method to change
//        // password according to your rules.
//    }
//}
