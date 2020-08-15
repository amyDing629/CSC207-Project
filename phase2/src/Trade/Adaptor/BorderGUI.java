package Trade.Adaptor;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class BorderGUI implements GUIPlan, BorderLayoutGUI, iPresent, iInitializeInput, iInput {
    private JTextArea curr;
    private JTextArea list;
    private JTextArea msg;
    private HashMap<String, JTextArea> inputs = new HashMap<>();
    private JFrame fr;

    public void setEast(JPanel e)
    {
        fr.getContentPane().add(BorderLayout.EAST, e);
    }

    public void setNorth(JPanel n)
    {
        fr.getContentPane().add(BorderLayout.NORTH, n);
    }

    public void setSouth(JPanel s)
    {
        fr.getContentPane().add(BorderLayout.SOUTH, s);
    }

    public void setWest(JPanel w) { fr.getContentPane().add(BorderLayout.WEST, w); }

    public void setCenter(JPanel c)
    {
        fr.getContentPane().add(BorderLayout.CENTER, c);
    }

    @Override
    public void setFrame(int width, int height, String name) {
        JFrame frame = new JFrame(name);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(width, height);
        fr = frame;
    }

    @Override
    public void run () {
        fr.setVisible(true);

    }

    @Override
    public JFrame getFrame() {
        return fr;
    }

    @Override
    public void setListText(String str) {
        list.setText(str);
        list.setEditable(false);
    }

    @Override
    public void setCurrText(String str) {
        curr.setText(str);
        curr.setEditable(false);
    }

    @Override
    public void setMsgText(String str) {
        msg.setText(str);
        msg.setEditable(false);
    }


    @Override
    public void initializeList(JTextArea ta) {
        list = ta;
        list.setEditable(false);
    }

    @Override
    public void initializeCurr(JTextArea ta) {
        curr = ta;
        curr.setEditable(false);
    }

    @Override
    public void initializeMsg(JTextArea ta) {
        msg = ta;
        msg.setEditable(false);
    }

    @Override
    public String getInput(String key) {
        return inputs.get(key).getText();
    }

    @Override
    public void setInput(String key, String content) {
        inputs.get(key).setText(content);
    }

    @Override
    public void addInput(String key, JTextArea input) {
        inputs.put(key, input);
    }
}
