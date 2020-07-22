package Trade.MeetingSystem.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MeetingGUI extends Frame implements MouseListener {

    // GUI Components
    private JFrame frame;
    private Container sessionScreen;
    private Container menuScreen;

    // Action Buttons
    private JPanel undo;
    private JPanel back;

    // Labels for showing the meeting's current stats
    private JLabel date_time;
    private JLabel place;
    private JLabel status;
    private JLabel currLogInUser;

    public MeetingGUI() {
        // creating a Frame
        JFrame frame = new JFrame("Meeting System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        setLayout(new FlowLayout());


        // creating the MenuBar and adding components
        JMenuBar menuBar = new JMenuBar();
        JMenu back = new JMenu("Back");
        menuBar.add(back);


        // creating the panel at the top and adding components
        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(null);
        JButton backButton = new JButton("Back");
        upperPanel.add(backButton); // Adds Button to content pane of frame

        // creating the panel in the middle and adding components
//        JPanel middlePanel1 = new JPanel(new FlowLayout());
//        JLabel dateTImeLabel = new JLabel("Date-Time: ");
//        middlePanel1.add(dateTImeLabel);
//
//        JTextField textField1 = new JTextField(16);
//        middlePanel1.add(textField1);
//
//        JButton resetButton1 = new JButton("Reset");
//        resetButton1.addActionListener(new );
//        middlePanel1.add(resetButton1);

        //
        JPanel middlePanel2 = new JPanel(new FlowLayout());
        JLabel placeLabel = new JLabel("Place: ");
        JTextField textField2 = new JTextField();
        JButton resetButton2 = new JButton("Reset");
        middlePanel2.add(placeLabel);
        middlePanel2.add(textField2);
        middlePanel2.add(resetButton2);


        //
        JPanel middlePanel3 = new JPanel();
        JButton doneButton = new JButton("Done");
        middlePanel3.add(doneButton);


        // creating the panel at the bottom and adding components
        JPanel lowerPanel = new JPanel();
        JButton continueButton = new JButton("Continue");
        lowerPanel.add(continueButton); // Adds Button to content pane of frame


        // add components to the Frame
        frame.getContentPane().add(upperPanel);
//        frame.getContentPane().add(middlePanel1);
        frame.getContentPane().add(middlePanel2);
//        frame.getContentPane().add(middlePanel3);
        frame.getContentPane().add(lowerPanel);


        frame.setVisible(true);
    }

    /**
     * Gets the frame of the GUI
     *
     * @return the frame of the GUI
     */
    public Frame getFrame() {
        return frame;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

//    /**
//     * This method will update the GUI.
//     */
//    public void updateGUI() {
//        addLevelState();
//        clearBoard();
//        populateBoard();
//        updateStats();
//
//        if (level.gameWin())
//            winScreen();
//
//        if (level.gameLose())
//            loseScreen();
//    }
//
//    /**
//     * Update the stat labels
//     */
//    public void updateStats() {
//        waveNumber.setText("Wave Number: " + level.currentWave());
//        zombiesRemaining.setText("Zombies Remaining: " + level.zombieCount());
//        availableSun.setText("Available Sun: " + level.getSunTotal());
//        state.setText("Turn Number: " + levelIndex);
//    }
//
//    /**
//     * Show a message dialog for completing the level, and close the GUI
//     */
//    public void winScreen() {
//        updateStats();
//        clearBoard();
//        populateBoard();
//        JOptionPane.showMessageDialog(frame, "LEVEL COMPLETE!");
//        System.exit(0);
//    }
//
//    /**
//     * Show a message for losing the level, and close the GUI
//     */
//    public void loseScreen() {
//        updateStats();
//        clearBoard();
//        populateBoard();
//        JOptionPane.showMessageDialog(frame, "GAME OVER!\nZOMBIES ATE YOUR BRAINS!");
//        System.exit(0);
//    }
}
