package Trade.Adaptor.AcceptTrade;

import Trade.Adaptor.BorderGUIBuilder;
import Trade.Adaptor.BorderGUI;

import javax.swing.*;
import java.awt.*;

public class AcceptTradeGUIBuilder implements BorderGUIBuilder {
    BorderGUI tg;
    AcceptTradeController atc;
    JFrame tf;

    public AcceptTradeGUIBuilder(String user, JFrame tf){
        tg = new BorderGUI();
        atc = new AcceptTradeController(user, tg);
        this.tf = tf;
    }

    @Override
    public void buildFrame(){
        tg.setFrame(800, 400, "Accept Trade Session");
    }

    @Override
    public void buildPanelN() {
        JPanel panelN = new JPanel();
        JLabel msg = new JLabel("Message:", SwingConstants.LEFT);
        JTextArea msgArea = new JTextArea();
        msgArea.setEditable(false);
        panelN.add(msg);
        panelN.add(msgArea);
        tg.initializeMsg(msgArea);
        tg.setNorth(panelN);

    }

    @Override
    public void buildPanelE() {
        JPanel panelE = new JPanel();
        JButton agree = new JButton("Agree");
        JButton refuse = new JButton("Refuse");
        panelE.setLayout(new BoxLayout(panelE, BoxLayout.Y_AXIS));
        panelE.add(agree);
        panelE.add(refuse);
        tg.setEast(panelE);
        agree.addActionListener(e -> atc.agreeBut(true));
        refuse.addActionListener(e -> atc.agreeBut(false));
    }

    @Override
    public void buildPanelW() {
        JPanel panelW =  new JPanel();
        JLabel tradeList = new JLabel("Available Trades");
        JTextArea tradeArea = new JTextArea();
        tg.initializeList(tradeArea);
        atc.updateBut();
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
        JTextArea inputArea = new JTextArea("Trade Number", 1, 10);
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
            System.out.println(tradeNum);
            atc.submitBut(tradeNum);
        });
        back.addActionListener(e -> atc.backBut(tf));
        update.addActionListener(e -> atc.updateBut());
    }

    @Override
    public void buildPanelC() {
        JPanel panelC =  new JPanel();
        JLabel currTradeL = new JLabel("Trade Selected");
        JTextArea currArea = new JTextArea();
        currArea.setEditable(false);

        panelC.setLayout(new BoxLayout(panelC, BoxLayout.Y_AXIS));
        panelC.add(currTradeL);
        panelC.add(currArea);
        tg.setCenter(panelC);
        tg.initializeCurr(currArea);
        atc.noTradeSelected();

    }

    public BorderGUI getTradeGUI(){
        return tg;
    }
}
