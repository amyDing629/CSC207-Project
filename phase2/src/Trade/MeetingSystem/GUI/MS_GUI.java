package Trade.MeetingSystem.GUI;

import javax.swing.*;
import java.awt.*;

// source: https://www.javatpoint.com/java-swing
public class MS_GUI {
    public static void main(String[] args) {
//        new MeetingGUI();
        createAndShowPlaceGUI();
//        JFrame frame = new JFrame("Meeting System");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//
//
//        JButton button = new JButton("Press");
//        button.setBounds(130,100,100, 40);//x axis, y axis, width, height
//        frame.getContentPane().add(button); // Adds Button to content pane of frame
//
//
//        frame.setSize(1500,1500);
//        frame.setLayout(null);//using no layout managers
//        frame.setVisible(true);
    }


    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowPlaceGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("TimePlaceInput");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add contents to the window.
//        frame.add(new DateTimePanel());
//        frame.add(new PlacePanel());
        frame.getContentPane().add(BorderLayout.NORTH, new DateTimePanel());
        frame.getContentPane().add(BorderLayout.SOUTH, new PlacePanel());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}
