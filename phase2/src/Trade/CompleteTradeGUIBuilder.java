package Trade;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CompleteTradeGUIBuilder implements BorderGUIBuilder {
    CTradeController ctc;
    JFrame tf;
    BorderGUIWithThreeTextArea tg;

    public CompleteTradeGUIBuilder(String currUser, JFrame tf) {
        tg = new BorderGUIWithThreeTextArea();
        ctc = new CTradeController(currUser, tg);
        this.tf = tf;
    }

    @Override
    public void buildFrame(){
        tg.setFrame(800, 400, "Complete Trade Session");
    }

    @Override
    public void buildPanelN() {
        ArrayList<Object> rst = new TradeGUIHelper().createMessagePanel();
        tg.initializeMsg((JTextArea)rst.get(1));
        tg.setNorth((JPanel)rst.get(0));

    }

    @Override
    public void buildPanelE() {
        JPanel panelE = new JPanel();
        JButton action = new JButton("Go to Meeting");
        panelE.setLayout(new BoxLayout(panelE, BoxLayout.Y_AXIS));
        panelE.add(action);
        action.addActionListener(e -> {
            ctc.action();
            ctc.closeFrame();
        });
        tg.setEast(panelE);

    }

    @Override
    public void buildPanelW() {
        JPanel panelW =  new JPanel();
        JLabel tradeList = new JLabel("Available Trades");
        JTextArea tradeArea = new JTextArea();
        tg.initializeList(tradeArea);
        ctc.updateBut();
        JScrollPane jsp= new JScrollPane(tradeArea);
        panelW.setLayout(new BoxLayout(panelW, BoxLayout.Y_AXIS));
        panelW.add(tradeList);
        panelW.add(jsp);
        panelW.setPreferredSize(new Dimension(380,370));
        tg.setWest(panelW);
    }

    @Override
    public void buildPanelS() {
        JPanel panelS =  new JPanel();
        JLabel input = new JLabel("Input Trade Number");
        JTextArea inputArea = new JTextArea("Trade Number");
        JButton submit = new JButton("Submit");
        JButton back = new JButton("Back");
        JButton update = new JButton("Update");
        panelS.add(input);
        panelS.add(inputArea);
        panelS.add(submit);
        panelS.add(back);
        panelS.add(update);
        tg.setSouth(panelS);
        tg.addInput("Input", inputArea);
        tg.setInput("Input", "Trade Number");

        submit.addActionListener(e -> {
            String tradeNum = tg.getInput("Input");
            ctc.submitBut(tradeNum);
        });
        back.addActionListener(e -> ctc.backBut(tf));
        update.addActionListener(e -> ctc.updateBut());
    }

    @Override
    public void buildPanelC() {
        JPanel panelC =  new JPanel();
        JLabel currTradeL = new JLabel("Trade Selected");
        JTextArea currArea = new JTextArea();
        panelC.setLayout(new BoxLayout(panelC, BoxLayout.Y_AXIS));
        panelC.add(currTradeL);
        panelC.add(currArea);
        tg.setCenter(panelC);
        tg.initializeCurr(currArea);
        ctc.noTradeSelected();

    }

    public BorderGUIWithThreeTextArea getTradeGUI(){
        return tg;
    }
}


