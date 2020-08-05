package Trade.MeetingSystem.Gui;

import javax.swing.text.MaskFormatter;

// Use Case
public class TextFieldFormatter {

    // source: https://docs.oracle.com/javase/tutorial/uiswing/components/formattedtextfield.html
    MaskFormatter createFormatter(String s) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(s);
        } catch (java.text.ParseException exc) {
            System.err.println("formatter is bad: " + exc.getMessage());
            System.exit(-1);
        }
        return formatter;
    }
}
