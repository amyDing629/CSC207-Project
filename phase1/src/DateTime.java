import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class DateTime {

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
        return "DateTime{" + "currentTime=" + currentTime + '}';
    }

    /**
     * Return if the input date-time string is of Valid Format
     * https://stackoverflow.com/questions/226910/how-to-sanity-check-a-date-in-java
     * @param inputDateTimeString String of input datetime
     * @return
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
        LocalDateTime dateTime = LocalDateTime.parse(inputDateTimeString, formatter);
        return dateTime;
    }

}
