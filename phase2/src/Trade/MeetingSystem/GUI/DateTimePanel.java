package Trade.MeetingSystem.GUI;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/*
 * Reference:
 * https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/TextInputDemoProject/src/components/TextInputDemo.java
 */
public class DateTimePanel extends JPanel implements ActionListener, FocusListener {
    final static int GAP = 10;
    JFormattedTextField dateTimeField;
    boolean dateTimeSet = false;
    Font regularFont, italicFont;
    JLabel dateTimeDisplay;

    public DateTimePanel() {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        JPanel leftHalf = new JPanel() {
            //Don't allow us to stretch vertically.
            public Dimension getMaximumSize() {
                Dimension pref = getPreferredSize();
                return new Dimension(Integer.MAX_VALUE,
                        pref.height);
            }
        };
        leftHalf.setLayout(new BoxLayout(leftHalf, BoxLayout.PAGE_AXIS));
        leftHalf.add(createEntryFields());
        leftHalf.add(createButtons());

        add(leftHalf);
        add(createDateTimeDisplay());
    }

    protected JComponent createButtons() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.TRAILING));

        JButton button = new JButton("Submit Date-Time");
        button.addActionListener(this);
        button.setActionCommand("submit");
        panel.add(button);

        button = new JButton("Clear Date-Time");
        button.addActionListener(this);
        button.setActionCommand("clear");
        panel.add(button);

        return panel;
    }

    /**
     * Called when the user clicks the button or presses
     * Enter in a text field.
     */
    public void actionPerformed(ActionEvent e) {
        if ("clear".equals(e.getActionCommand())) {
            dateTimeSet = false;
            dateTimeField.setValue(null);
        } else {
            dateTimeSet = true;
        }
//        dateTimeSet = "submit".equals(e.getActionCommand());
        updateDisplays();
    }

    protected void updateDisplays() {
        dateTimeDisplay.setText(formatDateTime());
        if (dateTimeSet) {
            dateTimeDisplay.setFont(regularFont);
        } else {
            dateTimeDisplay.setFont(italicFont);
        }
    }

    protected String formatDateTime() {
        if (!dateTimeSet) return "No date-time set.";

        String empty = "";
        String dt = dateTimeField.getText();

//        String year = yearField.getText();
//        String month = monthField.getText();
//        String empty = "";

//        if ((year == null) || empty.equals(year)) {
//            year = "<em>(no yyyy specified)</em>";
//        }
//        if ((month == null) || empty.equals(month)) {
//            month = "<em>(no MM specified)</em>";
//        }

        if ((dt == null) || empty.equals(dt)) {
            dt = "<em>(no date-time specified)</em>";
        }
        StringBuffer sb = new StringBuffer();
        sb.append("<html><p align=center>");
        sb.append(dt);
        sb.append("</p></html>");

        return sb.toString();
    }

    protected JComponent createEntryFields() {
        JPanel panel = new JPanel(new SpringLayout());

        String[] labelStrings = {
                "Date-Time: "
        };

        JLabel[] labels = new JLabel[labelStrings.length];
        JComponent[] fields = new JComponent[labelStrings.length];
        int fieldNum = 0;

        //Create the text field and set it up.
        dateTimeField = new JFormattedTextField(
                createFormatter("####-##-## ##:##"));
        fields[fieldNum++] = dateTimeField;

        //Associate label/field pairs, add everything,
        //and lay it out.
        for (int i = 0; i < labelStrings.length; i++) {
            labels[i] = new JLabel(labelStrings[i],
                    JLabel.TRAILING);
            labels[i].setLabelFor(fields[i]);
            panel.add(labels[i]);
            panel.add(fields[i]);

            //Add listeners to each field.
            JTextField tf = null;
            tf = (JTextField) fields[i];
            tf.addActionListener(this);
            tf.addFocusListener(this);
        }
        SpringUtilities.makeCompactGrid(panel,
                labelStrings.length, 2,
                GAP, GAP, //init x,y
                GAP, GAP / 2);//xpad, ypad
        return panel;
    }


    //A convenience method for creating a MaskFormatter.
    protected MaskFormatter createFormatter(String s) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(s);
        } catch (java.text.ParseException exc) {
            System.err.println("formatter is bad: " + exc.getMessage());
            System.exit(-1);
        }
        return formatter;
    }

    protected JComponent createDateTimeDisplay() {
        JPanel panel = new JPanel(new BorderLayout());
        dateTimeDisplay = new JLabel();
        dateTimeDisplay.setHorizontalAlignment(JLabel.CENTER);
        regularFont = dateTimeDisplay.getFont().deriveFont(Font.PLAIN,
                16.0f);
        italicFont = regularFont.deriveFont(Font.ITALIC);
        updateDisplays();

        //Lay out the panel.
        panel.setBorder(BorderFactory.createEmptyBorder(
                GAP / 2, //top
                0,     //left
                GAP / 2, //bottom
                0));   //right
        panel.add(new JSeparator(JSeparator.VERTICAL),
                BorderLayout.LINE_START);
        panel.add(dateTimeDisplay,
                BorderLayout.CENTER);
        panel.setPreferredSize(new Dimension(200, 150));

        return panel;
    }

    /**
     * Called when one of the fields gets the focus so that
     * we can select the focused field.
     */
    public void focusGained(FocusEvent e) {
        Component c = e.getComponent();
        if (c instanceof JFormattedTextField) {
            selectItLater(c);
        } else if (c instanceof JTextField) {
            ((JTextField) c).selectAll();
        }
    }

    //Workaround for formatted text field focus side effects.
    protected void selectItLater(Component c) {
        if (c instanceof JFormattedTextField) {
            final JFormattedTextField ftf = (JFormattedTextField) c;
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    ftf.selectAll();
                }
            });
        }
    }

    //Needed for FocusListener interface.
    public void focusLost(FocusEvent e) {
    } //ignore
}
