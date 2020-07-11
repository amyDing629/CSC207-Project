package Trade.MeetingSystem.MeetingExceptions;

import java.io.IOException;

public class InvalidInstructionException extends IOException {
    public InvalidInstructionException() {
        System.out.println("Error: Invalid instruction!");
    }
}
