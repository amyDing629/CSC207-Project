package Trade.MeetingSystem.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDateTime;
import java.time.YearMonth;

public class DateTime_FlowLayout extends Panel implements ActionListener, ItemListener {
    JPanel panel = new JPanel();

    DateTime_FlowLayout() {
//        final JFrame frame = new JFrame("JSplitPane Example");
//        // Display the window.
//        frame.setSize(300, 300);
//        frame.setVisible(true);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        // set flow layout for the frame
//        frame.getContentPane().setLayout(new FlowLayout());


        String[] yyyy = new String[5];
        int currYear = LocalDateTime.now().getYear();
        for (int i = 0; i < yyyy.length; i++) {
            yyyy[i] = String.valueOf(currYear + i);
        }
        JComboBox<String> yyyyBox = new JComboBox<>(yyyy);
        Panel panel1 = new Panel();
        panel1.add(yyyyBox);


        String[] MM = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        JComboBox<String> MMBox = new JComboBox<String>(MM);
        Panel panel2 = new Panel();
        panel2.add(MMBox);

        String[] dd = new String[30];
        for (int i = 1; i < 31; i++) {
            dd[i - 1] = String.valueOf(i);
        }
        JComboBox<String> ddBox = new JComboBox<>(dd);
        Panel panel3 = new Panel();
        panel3.add(ddBox);


//        String[] dd = {};
//        for(int i = 0, i < getDaysInMonthYear(), i++){
//
//        }
//        JComboBox ddBox = new JComboBox(dd);
//        Panel panel3 = new Panel();
//        panel3.add(ddBox);


        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel1, panel2);
        // JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
        // panel1, panel2);
//        frame.getContentPane().add(splitPane);
//
//        panel.setLayout(new FlowLayout());
//        // creating the panel in the middle and adding components
//        JLabel dateTImeLabel = new JLabel("Date-Time: ");
//        JPopupMenu yyyyPopupMenu = new JPopupMenu("yyyy");
//        JPopupMenu MMPopupMenu = new JPopupMenu("MM");
//        JPopupMenu ddPopupMenu = new JPopupMenu("DD");
//        JPopupMenu hhPopupMenu = new JPopupMenu("hh");
//        JPopupMenu mmPopupMenu = new JPopupMenu("mm");
//        int yyyy = LocalDateTime.now().getYear();
//        int i = 0;
//        while (i <= 5){ // the closet 5 years
//            yyyyPopupMenu.add(new JMenuItem(String.valueOf(yyyy+i)));
//            i++;
//        }
//
//        i = 1;
//        while (i <= 30){
//            ddPopupMenu.add(new JMenuItem(String.valueOf(i)));
//            i++;
//        }
//        JButton resetButton = new JButton("Reset");
//        panel.add(dateTImeLabel);
//        panel.add(yyyyPopupMenu);
//        panel.add(MMPopupMenu);
//        panel.add(ddPopupMenu);
//        panel.add(hhPopupMenu);
//        panel.add(mmPopupMenu);
//        panel.add(resetButton);
    }

    /*
    Return an int of number of days in a particular yyyy-MM.
    Source: https://stackoverflow.com/questions/8940438/number-of-days-in-particular-month-of-particular-year
     */
    private int getDaysInMonthYear(int yyyy, int MM) {
        YearMonth yearMonthObject = YearMonth.of(yyyy, MM);
        return yearMonthObject.lengthOfMonth();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}
