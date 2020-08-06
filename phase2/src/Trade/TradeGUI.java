package Trade;

import javax.swing.*;
import java.awt.*;

public class TradeGUI implements TradeGUIPlan {
    private JFrame fr;
    private JTextArea curr;
    private JTextArea list;
    private JTextArea msg;
    private JTextArea input;
    private String inputStr;

    public JFrame getFrame(){
        return fr;
    }

    @Override
    public void setFrame(int width, int height, String name){
        JFrame frame = new JFrame(name);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(width, height);
        fr = frame;

    }

    @Override
    public void setListText(String str) {
        list.setText(str);

    }

    @Override
    public void setCurrText(String str) {
        curr.setText(str);

    }

    @Override
    public void setMsgText(String str) {
        msg.setText(str);

    }

    @Override
    public void initializeInput(JTextArea input) {
        this.input = input;

    }


    @Override
    public void setInputStr(String str) {
        inputStr = str;

    }

    @Override
    public void resetInput() {
        input.setText(inputStr);

    }

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

    public void setWest(JPanel w)
    {
        fr.getContentPane().add(BorderLayout.WEST, w);
        w.setPreferredSize(new Dimension(380,370));
    }

    public void setCenter(JPanel c)
    {
        fr.getContentPane().add(BorderLayout.CENTER, c);
    }

    @Override
    public void initializeList(JTextArea ta) {
        list = ta;
    }

    @Override
    public void initializeCurr(JTextArea ta) {
        curr = ta;
    }

    @Override
    public void initializeMsg(JTextArea ta) {
        msg = ta;
    }

    public void run(){
        fr.setVisible(true);
    }

    public String getInput(){
        return input.getText();

    }
}
