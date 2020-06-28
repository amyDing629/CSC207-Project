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
 * update timeOfEdition in Trader
 */
public class MeetingSystem {

//    private User u1 = new User(1);
//    private User u2 = new User(2);

    public LocalDateTime dateTime;
    public String place;
    public Integer userId;
    public Integer otherUserId;

    private MeetingEditor t1 = new MeetingEditor(1);
    private MeetingEditor t2 = new MeetingEditor(2);
//    private SetUpMeetingPresenter setUpMeeting = new SetUpMeetingPresenter();


    /**
     * Interacts with the user to prompt input of student and course information.
     */
    public void run() {
        // allow input: "exit", "setup meeting", "edit", "confirm"

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Type 'exit' to quit or 'setup meeting' to set up a meeting.");
        try {
            String input = br.readLine();
            while (!input.equals("exit")) { // != compares memory addresses.

                // set up meeting
                if (input.equals("setup meeting")) {
                    SetUpMeetingPresenter setUpMeeting = new SetUpMeetingPresenter();
                    dateTime = setUpMeeting.dateTime;
                    place = setUpMeeting.place;



                    System.out.println("A meeting has been set up!");
                    System.out.println("  " + "- proposed time is:" + dateTime.toString());
                    System.out.println("  " + "- proposed place is:" + place);

                }

                // edit meeting
                input = br.readLine();
                if (input.equals("edit meeting")) {
                    // update time place
                    EditMeetingPresenter editMeeting = new EditMeetingPresenter();
                    LocalDateTime time = editMeeting.dateTime;
                    String place = editMeeting.place;



                    System.out.println("A meeting has been edited!");
                    System.out.println("  " + "- the new proposed time is:" + time.toString());
                    System.out.println("  " + "- the new proposed place is:" + place);

                }

            }
//            System.out.println(temp);
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }

//        MeetingIterator prompts = new MeetingIterator();
//        ArrayList<String> temp = new ArrayList<>();
//        int curr = 0;
//
//
//        System.out.println("Type 'exit' to quit or 'ok' to continue.");
//        try {
//            String input = br.readLine();
//            while (!input.equals("exit")) { // != compares memory addresses.
//                if (prompts.hasNext()) {
//                    System.out.println(prompts.next());
//                }
//                input = br.readLine();
//                if (!input.equals("exit")) {
//                    temp.add(input);
//                    curr++;
//                }
//            }
//            System.out.println(temp);
//        } catch (IOException e) {
//            System.out.println("Something went wrong");
//        }





//        try {
//            if(temp.get(0) != null) {
//                u1.addMeeting(temp);
//                Course c = new Course(temp.get(curr - 1));
//                sm1.enrolAllInCourse(c);
//                System.out.println(sm1);
//            }
//        } catch (IndexOutOfBoundsException e) {
//            System.out.println("Empty enrolment");
//        }


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
