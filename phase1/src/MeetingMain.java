import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentLinkedDeque;

public class MeetingMain {
    public static ClientUser u1 = new ClientUser(1);
    public static ClientUser u2 = new ClientUser(2);

    public static void main (String[] args) {
        System.out.println("Please register a meeting!");
        MeetingSystem mt = new MeetingSystem(u1, u2);
        mt.run();


        //============================================================================
        // TESTING: DateTime.java
        //============================================================================
        String datetime1 = "2020-10-02 12:00";
        String datetime2 = "2020-02-02 12:00";
        String datetime3 = "2020-02-02";
        String datetime4 = "2020-10-02";

        // print current time
        LocalDateTime now = DateTime.getCurrentTime();
        System.out.println(now);

        // check if of valid format
        System.out.println(DateTime.isValidFormat(datetime1));
        System.out.println(DateTime.isValidFormat(datetime2));
        System.out.println(DateTime.isValidFormat(datetime3));
        System.out.println(DateTime.isValidFormat(datetime4));

        // convert string to LocalDateTime;
        // check if input DateTime in the future
        LocalDateTime datetime11 = DateTime.convertToLocalDateTime(datetime1);
        System.out.println(datetime11.isAfter(now)); // true

        LocalDateTime datetime22 = DateTime.convertToLocalDateTime(datetime2);
        System.out.println(datetime22.isAfter(now)); // false




    }

}