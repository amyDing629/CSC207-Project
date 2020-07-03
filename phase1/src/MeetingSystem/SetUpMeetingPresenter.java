package MeetingSystem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Presenter class of Meeting System, functioned for setting up a meeting only
 */
public class SetUpMeetingPresenter {
    public LocalDateTime dateTime;
    public String place;
//    public Integer userId;
//    public Integer otherUserId;

    /**
     * Feeds the prompts of the Meeting info, including dateTime, place
     */
    public SetUpMeetingPresenter() {

        Scanner user_input = new Scanner(System.in);
        boolean good = false;
        do {
            System.out.print("Enter the date-time: (should be in pattern of \"yyyy-MM-dd HH:mm\") \n");
            String dateTimeStr = user_input.nextLine();

            // valid datetime format + in the future than now
            if (DateTime.isValidFormat(dateTimeStr)) {
                LocalDateTime now = DateTime.getCurrentTime();
                LocalDateTime datetime = DateTime.convertToLocalDateTime(dateTimeStr);
                if (datetime.isAfter(now)) {
                    good = true;
                    dateTime = DateTime.convertToLocalDateTime(dateTimeStr);
                    System.out.println("proposed date-time is: " + dateTime.toString());
                }else{
                    System.out.println("Invalid input date-time! Only future time accepted");
                }
            } else {
                System.out.println("Invalid input format!");
            }
        }
        while (!good);


        System.out.print("Enter the place: ");
        place = user_input.nextLine( );
        System.out.print("Proposed place: " + place + "\n");


        if (user_input.nextLine().equals("close")){
            user_input.close();
        }

    }

    public ArrayList<Object> setUpMeetingPresenterResult(){
        return new ArrayList<>(Arrays.asList(dateTime, place));
    }

}
