package Trade.MeetingSystem.MeetingExceptions;

import java.io.IOException;

public class InvalidInstructionException extends IOException {
    /**
     * the exception for the invalid instruction
     */
    public InvalidInstructionException() {
        System.out.println("Error: Invalid instruction!");
    }
}
